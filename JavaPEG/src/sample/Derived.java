package sample;
import java.util.*;
public class Derived extends Base {
	public Derived(Object in, int line) {$open(in, line);}
	public Derived(Object in) {$open(in, 1);}
	public Derived() {}
	public static void main(String[] args) throws Exception {
		new Derived(System.in).$main();
	}
	HashMap<String, Integer> var = new HashMap<String, Integer>();
	@SymbolID(5)
	public String ID() {
		try {
			String image;
			int $0 = $lock(1); // = <
			if (!$char($CHAR[0])) throw new ParseError(); // [A-Za-z_]
			while ($char($CHAR[1])) ; // [0-9A-Za-z_]*
			image = $image($0); // >
			return image;
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean ID$() {
		int $p = $cache(5);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			if (!$char($CHAR[0])) continue; // [A-Za-z_]
			while ($char($CHAR[1])) ; // [0-9A-Za-z_]*
			$ok(5, $p);
			break;
		} while ($r = $ng(5, $pos = $p));
		return $r;
	}
	public void $main() {
		try {
			String id;
			int value;
			$0: for (;;) {
				int $1 = $pos;
				do {
					__$(); // ~__?
					if (!ID$()) continue; // ID
					__$(); // ~__?
					if (!$string("=")) continue; // "="
					if (!_sum_$()) continue; // _sum_
					if (!$string(";")) continue; // ";"
					$pos = $1;
					__$(); // ~__?
					id = ID(); // ID
					__$(); // ~__?
					$pos += 1; // "="
					value = _sum_(); // _sum_
					$pos += 1; // ";"
					var.put(id, value);
					System.out.println(id + " = " + value);
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!_sum_$()) continue; // _sum_
					if (!$string(";")) continue; // ";"
					$pos = $1;
					value = _sum_(); // _sum_
					$pos += 1; // ";"
					System.out.println(value);
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			__$(); // ~__?
			if (!$string(";")) throw new ParseError(); // ";"
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	@Override
	@SymbolID(3)
	public int _unary_() {
		try {
			int value;
			__$(); // ~__?
			$0: do {
				int $1 = $pos;
				do {
					if (!$string("-")) continue; // "-"
					__$(); // ~__?
					if (!element2$()) continue; // element2
					$pos = $1;
					$pos += 1; // "-"
					__$(); // ~__?
					value = element2(); // element2
					value = -value;
					continue $0;
				} while (false);
				$pos = $1;
				value = element2(); // element2
			} while (false);
			__$(); // ~__?
			return value;
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean _unary_$() {
		int $p = $cache(3);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			__$(); // ~__?
			int $0 = $pos;
			$1: do {
				do {
					if (!$string("-")) continue; // "-"
					__$(); // ~__?
					if (!element2$()) continue; // element2
					continue $1;
				} while (false);
				$pos = $0;
				$0 = 0;
			} while (false);
			if ($0 == 0) {
				if (!element2$()) continue; // element2
			}
			__$(); // ~__?
			$ok(3, $p);
			break;
		} while ($r = $ng(3, $pos = $p));
		return $r;
	}
	@SymbolID(6)
	public int element2() {
		try {
			int $0 = $pos;
			do {
				if (!ID$()) continue; // ID
				$pos = $0;
				String id;
				id = ID(); // ID
				return var.get(id);
			} while (false);
			$pos = $0;
			int value;
			value = element(); // element
			return value;
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean element2$() {
		int $p = $cache(6);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			int $0 = $pos;
			$1: do {
				do {
					if (!ID$()) continue; // ID
					continue $1;
				} while (false);
				$pos = $0;
				$0 = 0;
			} while (false);
			if ($0 == 0) {
				if (!element$()) continue; // element
			}
			$ok(6, $p);
			break;
		} while ($r = $ng(6, $pos = $p));
		return $r;
	}
	@SymbolID(7)
	public void $open(Object in, int line) {$open(in, line, 7);}
	private static final java.util.regex.Pattern[] $CHAR = new java.util.regex.Pattern[]{
		java.util.regex.Pattern.compile("[A-Za-z_]"),
		java.util.regex.Pattern.compile("[0-9A-Za-z_]")
	};
}
