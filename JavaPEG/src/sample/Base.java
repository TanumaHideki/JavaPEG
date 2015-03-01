package sample;
public class Base extends parser.Parser {
	public Base(Object in, int line) {$open(in, line);}
	public Base(Object in) {$open(in, 1);}
	public Base() {}
	public static void main(String[] args) throws Exception {
		new Base(System.in).main();
	}
	@SymbolID(0)
	public void S() {
		if (!S$()) throw new ParseError();
	}
	protected boolean S$() {
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
			S$(); // ~S?
			$ok(0, $p);
			break;
		} while ($r = $ng(0, $pos = $p));
		return $r;
	}
	@SymbolID(1)
	public void main() {
		try {
			int value;
			$0: for (;;) {
				int $1 = $pos;
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
	@SymbolID(2)
	public int sum() {
		try {
			int lhs, rhs;
			lhs = term(); // term
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
					if (!$string("+")) continue; // "+"
					if (!term$()) continue; // term
					$pos = $1;
					$pos += 1; // "+"
					rhs = term(); // term
					lhs += rhs;
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string("-")) continue; // "-"
					if (!term$()) continue; // term
					$pos = $1;
					$pos += 1; // "-"
					rhs = term(); // term
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
	protected boolean sum$() {
		int $p = $cache(2);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			if (!term$()) continue; // term
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!S$()) continue; // ~S
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string("+")) continue; // "+"
					if (!term$()) continue; // term
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string("-")) continue; // "-"
					if (!term$()) continue; // term
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
	public int term() {
		try {
			int lhs, rhs;
			lhs = unary(); // unary
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
					if (!$string("*")) continue; // "*"
					if (!unary$()) continue; // unary
					$pos = $1;
					$pos += 1; // "*"
					rhs = unary(); // unary
					lhs *= rhs;
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string("/")) continue; // "/"
					if (!unary$()) continue; // unary
					$pos = $1;
					$pos += 1; // "/"
					rhs = unary(); // unary
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
	protected boolean term$() {
		int $p = $cache(3);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			if (!unary$()) continue; // unary
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!S$()) continue; // ~S
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string("*")) continue; // "*"
					if (!unary$()) continue; // unary
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string("/")) continue; // "/"
					if (!unary$()) continue; // unary
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			$ok(3, $p);
			break;
		} while ($r = $ng(3, $pos = $p));
		return $r;
	}
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
					if (!element$()) continue; // element
					$pos = $1;
					$pos += 1; // "-"
					S$(); // ~S?
					value = element(); // element
					continue $0;
				} while (false);
				$pos = $1;
				value = element(); // element
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
					if (!element$()) continue; // element
					continue $1;
				} while (false);
				$pos = $0;
				$0 = 0;
			} while (false);
			if ($0 == 0) {
				if (!element$()) continue; // element
			}
			$ok(4, $p);
			break;
		} while ($r = $ng(4, $pos = $p));
		return $r;
	}
	@SymbolID(5)
	public int element() {
		try {
			int $0 = $pos;
			do {
				if (!$char($CHAR[1])) continue; // [0-9]
				while ($char($CHAR[1])) ; // [0-9]*
				$pos = $0;
				String image;
				int $1 = $lock(1); // =<
				++$pos; // [0-9]
				while ($char($CHAR[1])) ; // [0-9]*
				image = $image($1); // >
				return Integer.parseInt(image);
			} while (false);
			$pos = $0;
			int value;
			if (!$string("(")) throw new ParseError(); // "("
			value = sum(); // sum
			if (!$string(")")) throw new ParseError(); // ")"
			return value;
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean element$() {
		int $p = $cache(5);
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
				if (!sum$()) continue; // sum
				if (!$string(")")) continue; // ")"
			}
			$ok(5, $p);
			break;
		} while ($r = $ng(5, $pos = $p));
		return $r;
	}
	@SymbolID(6)
	public void $open(Object in, int line) {$open(in, line, 6);}
	private static final java.util.regex.Pattern[] $CHAR = new java.util.regex.Pattern[]{
		java.util.regex.Pattern.compile("[ \t\r\n]"),
		java.util.regex.Pattern.compile("[0-9]")
	};
}
