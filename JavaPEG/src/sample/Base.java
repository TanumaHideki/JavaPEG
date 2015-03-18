package sample;
public class Base extends parser.Parser {
	public Base(Object in, int line) {$open(in, line);}
	public Base(Object in) {$open(in, 1);}
	public Base() {}
	public static void main(String[] args) throws Exception {
		new Base(System.in).$main();
	}
	@SymbolID(0)
	public void __() {
		if (!__$()) throw new ParseError();
	}
	protected boolean __$() {
		int $p = $cache(0);
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
					if (!$char($CHAR[0])) continue; // [ \t\r\n]
					continue $1;
				} while (false);
				$pos = $0;
				$0 = 0;
			} while (false);
			if ($0 == 0) {
				if (!$string("//")) continue; // "//"
				$2: for (;;) {
					int $3 = $pos;
					do {
						if ($string("\n")) continue; // !"\n"
						if (!$any()) continue; // .
						continue $2;
					} while (false);
					$pos = $3;
					break;
				}
			}
			while ($char($CHAR[0])) ; // [ \t\r\n]*
			__$(); // ~__?
			$ok(0, $p);
			break;
		} while ($r = $ng(0, $pos = $p));
		return $r;
	}
	public void $main() {
		try {
			int value;
			$0: for (;;) {
				int $1 = $pos;
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
	@SymbolID(1)
	public int _sum_() {
		try {
			int lhs, rhs;
			lhs = _term_(); // _term_
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!$string("+")) continue; // "+"
					if (!_term_$()) continue; // _term_
					$pos = $1;
					$pos += 1; // "+"
					rhs = _term_(); // _term_
					lhs += rhs;
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string("-")) continue; // "-"
					if (!_term_$()) continue; // _term_
					$pos = $1;
					$pos += 1; // "-"
					rhs = _term_(); // _term_
					lhs -= rhs;
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			return lhs;
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean _sum_$() {
		int $p = $cache(1);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			if (!_term_$()) continue; // _term_
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!$string("+")) continue; // "+"
					if (!_term_$()) continue; // _term_
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string("-")) continue; // "-"
					if (!_term_$()) continue; // _term_
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			$ok(1, $p);
			break;
		} while ($r = $ng(1, $pos = $p));
		return $r;
	}
	@SymbolID(2)
	public int _term_() {
		try {
			int lhs, rhs;
			lhs = _unary_(); // _unary_
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!$string("*")) continue; // "*"
					if (!_unary_$()) continue; // _unary_
					$pos = $1;
					$pos += 1; // "*"
					rhs = _unary_(); // _unary_
					lhs *= rhs;
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string("/")) continue; // "/"
					if (!_unary_$()) continue; // _unary_
					$pos = $1;
					$pos += 1; // "/"
					rhs = _unary_(); // _unary_
					lhs /= rhs;
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			return lhs;
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean _term_$() {
		int $p = $cache(2);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			if (!_unary_$()) continue; // _unary_
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!$string("*")) continue; // "*"
					if (!_unary_$()) continue; // _unary_
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string("/")) continue; // "/"
					if (!_unary_$()) continue; // _unary_
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			$ok(2, $p);
			break;
		} while ($r = $ng(2, $pos = $p));
		return $r;
	}
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
					if (!element$()) continue; // element
					$pos = $1;
					$pos += 1; // "-"
					__$(); // ~__?
					value = element(); // element
					value = -value;
					continue $0;
				} while (false);
				$pos = $1;
				value = element(); // element
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
					if (!element$()) continue; // element
					continue $1;
				} while (false);
				$pos = $0;
				$0 = 0;
			} while (false);
			if ($0 == 0) {
				if (!element$()) continue; // element
			}
			__$(); // ~__?
			$ok(3, $p);
			break;
		} while ($r = $ng(3, $pos = $p));
		return $r;
	}
	@SymbolID(4)
	public int element() {
		try {
			int $0 = $pos;
			do {
				if (!$char($CHAR[1])) continue; // [0-9]
				while ($char($CHAR[1])) ; // [0-9]*
				$pos = $0;
				String image;
				int $1 = $lock(1); // = <
				++$pos; // [0-9]
				while ($char($CHAR[1])) ; // [0-9]*
				image = $image($1); // >
				return Integer.parseInt(image);
			} while (false);
			$pos = $0;
			int value;
			if (!$string("(")) throw new ParseError(); // "("
			value = _sum_(); // _sum_
			if (!$string(")")) throw new ParseError(); // ")"
			return value;
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean element$() {
		int $p = $cache(4);
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
					if (!$char($CHAR[1])) continue; // [0-9]
					while ($char($CHAR[1])) ; // [0-9]*
					continue $1;
				} while (false);
				$pos = $0;
				$0 = 0;
			} while (false);
			if ($0 == 0) {
				if (!$string("(")) continue; // "("
				if (!_sum_$()) continue; // _sum_
				if (!$string(")")) continue; // ")"
			}
			$ok(4, $p);
			break;
		} while ($r = $ng(4, $pos = $p));
		return $r;
	}
	@SymbolID(5)
	public void $open(Object in, int line) {$open(in, line, 5);}
	private static final java.util.regex.Pattern[] $CHAR = new java.util.regex.Pattern[]{
		java.util.regex.Pattern.compile("[ \t\r\n]"),
		java.util.regex.Pattern.compile("[0-9]")
	};
}
