package sample;
import java.util.*;
public class Derived extends Base {
	public Derived(Object in, int line) {$open(in, line);}
	public Derived(Object in) {$open(in, 1);}
	public Derived() {}
	public static void main(String[] args) throws Exception {
		new Derived(System.in).main();
	}
	HashMap<String, Integer> var = new HashMap<String, Integer>();
	@SymbolID(6)
	public String ID() {
		try {
			String image;
			int $0 = $lock(1); // =<
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
		int $p = $cache(6);
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
			$ok(6, $p);
			break;
		} while ($r = $ng(6, $pos = $p));
		return $r;
	}
	@Override
	@SymbolID(1)
	public void main() {
		try {
			String id;
			int value;
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!S$()) continue; // ~S
					$pos = $1;
					S$(); // ~S
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!ID$()) continue; // ID
					S$(); // ~S?
					if (!$string("=")) continue; // "="
					if (!sum$()) continue; // sum
					if (!$string(";")) continue; // ";"
					$pos = $1;
					id = ID(); // ID
					S$(); // ~S?
					$pos += 1; // "="
					value = sum(); // sum
					$pos += 1; // ";"
					var.put(id, value);
					System.out.println(id + " = " + value);
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!sum$()) continue; // sum
					if (!$string(";")) continue; // ";"
					$pos = $1;
					value = sum(); // sum
					$pos += 1; // ";"
					System.out.println(value);
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			S$(); // ~S?
			if (!$string(";")) throw new ParseError(); // ";"
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean main$() {
		int $p = $cache(1);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!S$()) continue; // ~S
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!ID$()) continue; // ID
					S$(); // ~S?
					if (!$string("=")) continue; // "="
					if (!sum$()) continue; // sum
					if (!$string(";")) continue; // ";"
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!sum$()) continue; // sum
					if (!$string(";")) continue; // ";"
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			S$(); // ~S?
			if (!$string(";")) continue; // ";"
			$ok(1, $p);
			break;
		} while ($r = $ng(1, $pos = $p));
		return $r;
	}
	@Override
	@SymbolID(4)
	public int unary() {
		try {
			int value;
			S$(); // ~S?
			$0: do {
				int $1 = $pos;
				do {
					if (!$string("-")) continue; // "-"
					S$(); // ~S?
					if (!element2$()) continue; // element2
					$pos = $1;
					$pos += 1; // "-"
					S$(); // ~S?
					value = element2(); // element2
					continue $0;
				} while (false);
				$pos = $1;
				value = element2(); // element2
			} while (false);
			return value;
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean unary$() {
		int $p = $cache(4);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			S$(); // ~S?
			int $0 = $pos;
			$1: do {
				do {
					if (!$string("-")) continue; // "-"
					S$(); // ~S?
					if (!element2$()) continue; // element2
					continue $1;
				} while (false);
				$pos = $0;
				$0 = 0;
			} while (false);
			if ($0 == 0) {
				if (!element2$()) continue; // element2
			}
			$ok(4, $p);
			break;
		} while ($r = $ng(4, $pos = $p));
		return $r;
	}
	@SymbolID(7)
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
		int $p = $cache(7);
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
			$ok(7, $p);
			break;
		} while ($r = $ng(7, $pos = $p));
		return $r;
	}
	@SymbolID(8)
	public void $open(Object in, int line) {$open(in, line, 8);}
	private static final java.util.regex.Pattern[] $CHAR = new java.util.regex.Pattern[]{
		java.util.regex.Pattern.compile("[A-Za-z_]"),
		java.util.regex.Pattern.compile("[0-9A-Za-z_]")
	};
}
