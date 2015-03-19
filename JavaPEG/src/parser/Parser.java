package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public abstract class Parser {
	@Retention(RetentionPolicy.RUNTIME)
	public @interface SymbolID {
		int value();
	}

	public static File $filechooser(String current) throws FileNotFoundException {
		return $filechooser(current, null, (String[]) null);
	}

	public static File $filechooser(String current, String description, String... extensions) throws FileNotFoundException {
		JFileChooser chooser = new JFileChooser(current);
		if (extensions != null) chooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
		return (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) ? null : chooser.getSelectedFile();
	}

	private Reader reader;
	private int mask;
	private char[] buf;
	private int[][] cache;
	private int base;
	private int last;
	private int lock;
	private int line;
	private int slot;
	protected int $pos;
	public String $file = "";

	@SymbolID(0)
	public abstract void $open(Object in, int line);

	protected void $open(Object in, int line, int slot) {
		if (in instanceof File) {
			$file = ((File) in).getName() + ':';
			try {
				in = new FileReader((File) in);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		} else if (in instanceof InputStream) in = new InputStreamReader((InputStream) in);
		if (in instanceof Reader) {
			reader = (Reader) in;
			mask = 1023;
			buf = new char[mask + 1];
			last = 1;
		} else {
			reader = null;
			mask = -1;
			String s = (in == null) ? "" : in.toString();
			in = null;
			last = s.length() + 1;
			buf = new char[last + 1];
			s.getChars(0, last - 1, buf, 1);
		}
		cache = new int[buf.length][];
		lock = 0;
		base = $pos = 1;
		this.line = line;
		this.slot = slot;
	}

	public void $close() {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
			}
			reader = null;
		}
		buf = null;
		cache = null;
	}

	private boolean ensure(int $p) {
		if ($p <= last) return true;
		if (reader == null) return false;
		if ($p - base > mask) {
			int mask2 = (mask << 1) + 1;
			while ($p - base > mask2)
				mask2 += mask2 + 1;
			char[] buf2 = new char[mask2 + 1];
			int[][] cache2 = new int[mask2 + 1][];
			int base_m = base & mask;
			int last_m = last & mask;
			if (last_m < base_m) {
				int l = (last & mask2) - last_m;
				System.arraycopy(buf, 0, buf2, l, last_m);
				System.arraycopy(cache, 0, cache2, l, last_m + 1);
				last_m = mask + 1;
			}
			System.arraycopy(buf, base_m, buf2, base & mask2, last_m -= base_m);
			buf = buf2;
			if (base_m + last_m <= mask) ++last_m;
			System.arraycopy(cache, base_m, cache2, base & mask2, last_m);
			cache = cache2;
			mask = mask2;
		}
		do {
			int base_m = base & mask;
			int last_m = last & mask;
			int l = (base_m <= last_m ? mask + 1 : base_m - 1) - last_m;
			try {
				l = reader.read(buf, last_m, l);
				if (l < 0) {
					reader.close();
					reader = null;
					return false;
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			last += l;
		} while (last < $p);
		return true;
	}

	protected int $cache(int id) {
		if (last < $pos) return 0;
		int[] slot = cache[$pos & mask];
		return slot == null ? 0 : slot[id];
	}

	protected void $ok(int id, int $p) {
		int[] s = cache[$p & mask];
		if (s == null) cache[$p & mask] = s = new int[slot];
		s[id] = $pos;
	}

	protected boolean $ng(int id, int $p) {
		int[] s = cache[$p &= mask];
		if (s == null) cache[$p] = s = new int[slot];
		s[id] = -1;
		return false;
	}

	protected int $lock(int delta) {
		int l = lock;
		if ((lock += delta) == 0 || l == 0) {
			l = base & mask;
			int r = $pos & mask;
			if (r < l) {
				Arrays.fill(cache, l, mask + 1, null);
				l = 0;
			}
			Arrays.fill(cache, l, r, null);
			line = $line();
			base = $pos;
		}
		return $pos;
	}

	protected String $image(int left) {
		left &= mask;
		int right = $pos & mask;
		String s = left <= right ? new String(buf, left, right - left) : new StringBuilder(right + mask + 1 - left).append(buf, left, mask + 1 - left).append(buf, 0, right).toString();
		$lock(-1);
		return s;
	}

	public int $line() {
		int l = line;
		for (int i = base; i < $pos; ++i)
			if (buf[i & mask] == '\n') ++l;
		return l;
	}

	public String toString() {
		return '(' + $file + $line() + ')';
	}

	public void $dump() {
		System.err.println("--------");
		for (int i = base; i < $pos; ++i)
			System.err.print(buf[i & mask]);
		System.err.println();
		System.err.print("<====== Error at ");
		System.err.println(this);
		int l = 0;
		for (int i = $pos; i < last; ++i) {
			char c = buf[i & mask];
			System.err.print(c);
			if (c == '\n' && ++l == 10) break;
		}
		System.err.println("--------");
	}

	public class ParseError extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public ParseError(Throwable cause) {
			super("Action error at " + Parser.this, cause);
			$dump();
		}

		public ParseError() {
			super("Syntax error at " + Parser.this);
			$dump();
		}
	}

	protected boolean $any() {
		if (ensure(++$pos)) return true;
		--$pos;
		return false;
	}

	protected boolean $string(String string) {
		int l = string.length();
		if (!ensure($pos + l)) {
			return false;
		}
		for (int i = 0; i < l; ++i) {
			if (buf[($pos + i) & mask] != string.charAt(i)) return false;
		}
		$pos += l;
		return true;
	}

	protected boolean $stringi(String string) {
		int l = string.length();
		if (!ensure($pos + l)) {
			return false;
		}
		for (int i = 0; i < l; ++i) {
			if (Character.toLowerCase(buf[($pos + i) & mask]) != string.charAt(i)) return false;
		}
		$pos += l;
		return true;
	}

	protected boolean $char(Pattern pattern) {
		if (!ensure($pos + 1) || !pattern.matcher(String.valueOf(buf[$pos & mask])).matches()) return false;
		++$pos;
		return true;
	}
}
