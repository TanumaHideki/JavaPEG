package parser;
public class Generator extends GeneratorHelper {
	public Generator(Object in, int line) {$open(in, line);}
	public Generator(Object in) {$open(in, 1);}
	public Generator() {}
	public static void main(String[] args) throws Exception {
		new Generator($filechooser(".", "Grammar file", "peg")).$main();
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
	@SymbolID(1)
	public String ID() {
		try {
			String image;
			int $0 = $lock(1); // = <
			if (!$char($CHAR[1])) throw new ParseError(); // [A-Za-z_-]
			while ($char($CHAR[2])) ; // [$0-9A-Za-z_-]*
			image = $image($0); // >
			return image.replace('-', '_');
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean ID$() {
		int $p = $cache(1);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			if (!$char($CHAR[1])) continue; // [A-Za-z_-]
			while ($char($CHAR[2])) ; // [$0-9A-Za-z_-]*
			$ok(1, $p);
			break;
		} while ($r = $ng(1, $pos = $p));
		return $r;
	}
	@SymbolID(2)
	public String PARAM() {
		try {
			String image;
			int $0 = $lock(1); // = <
			if (!$string("(")) throw new ParseError(); // "("
			$1: for (;;) {
				int $2 = $pos;
				do {
					if (!PARAM$()) continue; // ~PARAM
					$pos = $2;
					PARAM$(); // ~PARAM
					continue $1;
				} while (false);
				$pos = $2;
				do {
					if ($string(")")) continue; // !")"
					if (!$any()) continue; // .
					$pos = $2;
					++$pos; // .
					continue $1;
				} while (false);
				$pos = $2;
				break;
			}
			if (!$string(")")) throw new ParseError(); // ")"
			image = $image($0); // >
			return image;
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean PARAM$() {
		int $p = $cache(2);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			if (!$string("(")) continue; // "("
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!PARAM$()) continue; // ~PARAM
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if ($string(")")) continue; // !")"
					if (!$any()) continue; // .
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			if (!$string(")")) continue; // ")"
			$ok(2, $p);
			break;
		} while ($r = $ng(2, $pos = $p));
		return $r;
	}
	@SymbolID(3)
	public int STRING() {
		try {
			int length = 0;
			if (!$string("\"")) throw new ParseError(); // "\""
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!$string("\\")) continue; // "\\"
					int $2 = $pos;
					$3: do {
						do {
							if (!$string("u")) continue; // "u"
							while ($string("u")) ; // "u"*
							if (!$any()) continue; // .
							if (!$any()) continue; // .
							if (!$any()) continue; // .
							if (!$any()) continue; // .
							continue $3;
						} while (false);
						$pos = $2;
						do {
							$char($CHAR[3]); // [0-3]?
							if (!$char($CHAR[4])) continue; // [0-7]
							$char($CHAR[4]); // [0-7]?
							continue $3;
						} while (false);
						$pos = $2;
						$2 = 0;
					} while (false);
					if ($2 == 0) {
						if (!$any()) continue; // .
					}
					$pos = $1;
					$pos += 1; // "\\"
					$4: do {
						int $5 = $pos;
						do {
							if (!$string("u")) continue; // "u"
							while ($string("u")) ; // "u"*
							if (!$any()) continue; // .
							if (!$any()) continue; // .
							if (!$any()) continue; // .
							if (!$any()) continue; // .
							$pos = $5;
							$pos += 1; // "u"
							while ($string("u")) ; // "u"*
							++$pos; // .
							++$pos; // .
							++$pos; // .
							++$pos; // .
							continue $4;
						} while (false);
						$pos = $5;
						do {
							$char($CHAR[3]); // [0-3]?
							if (!$char($CHAR[4])) continue; // [0-7]
							$char($CHAR[4]); // [0-7]?
							$pos = $5;
							$char($CHAR[3]); // [0-3]?
							++$pos; // [0-7]
							$char($CHAR[4]); // [0-7]?
							continue $4;
						} while (false);
						$pos = $5;
						++$pos; // .
					} while (false);
					++length;
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if ($string("\"")) continue; // !"\""
					if (!$any()) continue; // .
					$pos = $1;
					++$pos; // .
					++length;
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			if (!$string("\"")) throw new ParseError(); // "\""
			return length;
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean STRING$() {
		int $p = $cache(3);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			if (!$string("\"")) continue; // "\""
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!$string("\\")) continue; // "\\"
					int $2 = $pos;
					$3: do {
						do {
							if (!$string("u")) continue; // "u"
							while ($string("u")) ; // "u"*
							if (!$any()) continue; // .
							if (!$any()) continue; // .
							if (!$any()) continue; // .
							if (!$any()) continue; // .
							continue $3;
						} while (false);
						$pos = $2;
						do {
							$char($CHAR[3]); // [0-3]?
							if (!$char($CHAR[4])) continue; // [0-7]
							$char($CHAR[4]); // [0-7]?
							continue $3;
						} while (false);
						$pos = $2;
						$2 = 0;
					} while (false);
					if ($2 == 0) {
						if (!$any()) continue; // .
					}
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if ($string("\"")) continue; // !"\""
					if (!$any()) continue; // .
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			if (!$string("\"")) continue; // "\""
			$ok(3, $p);
			break;
		} while ($r = $ng(3, $pos = $p));
		return $r;
	}
	@SymbolID(4)
	public String CHAR() {
		try {
			String image;
			int $0 = $lock(1); // = <
			if (!$string("[")) throw new ParseError(); // "["
			$1: for (;;) {
				int $2 = $pos;
				do {
					if (!$string("\\")) continue; // "\\"
					int $3 = $pos;
					$4: do {
						do {
							if (!$string("\\[")) continue; // "\\["
							continue $4;
						} while (false);
						$pos = $3;
						do {
							if (!$string("\\]")) continue; // "\\]"
							continue $4;
						} while (false);
						$pos = $3;
						$3 = 0;
					} while (false);
					if ($3 == 0) {
						if (!$any()) continue; // .
					}
					$pos = $2;
					$pos += 1; // "\\"
					$5: do {
						int $6 = $pos;
						do {
							if (!$string("\\[")) continue; // "\\["
							$pos = $6;
							$pos += 2; // "\\["
							continue $5;
						} while (false);
						$pos = $6;
						do {
							if (!$string("\\]")) continue; // "\\]"
							$pos = $6;
							$pos += 2; // "\\]"
							continue $5;
						} while (false);
						$pos = $6;
						++$pos; // .
					} while (false);
					continue $1;
				} while (false);
				$pos = $2;
				do {
					if (!CHAR$()) continue; // ~CHAR
					$pos = $2;
					CHAR$(); // ~CHAR
					continue $1;
				} while (false);
				$pos = $2;
				do {
					if ($string("]")) continue; // !"]"
					if (!$any()) continue; // .
					$pos = $2;
					++$pos; // .
					continue $1;
				} while (false);
				$pos = $2;
				break;
			}
			if (!$string("]")) throw new ParseError(); // "]"
			image = $image($0); // >
			return image;
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean CHAR$() {
		int $p = $cache(4);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			if (!$string("[")) continue; // "["
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!$string("\\")) continue; // "\\"
					int $2 = $pos;
					$3: do {
						do {
							if (!$string("\\[")) continue; // "\\["
							continue $3;
						} while (false);
						$pos = $2;
						do {
							if (!$string("\\]")) continue; // "\\]"
							continue $3;
						} while (false);
						$pos = $2;
						$2 = 0;
					} while (false);
					if ($2 == 0) {
						if (!$any()) continue; // .
					}
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!CHAR$()) continue; // ~CHAR
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if ($string("]")) continue; // !"]"
					if (!$any()) continue; // .
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			if (!$string("]")) continue; // "]"
			$ok(4, $p);
			break;
		} while ($r = $ng(4, $pos = $p));
		return $r;
	}
	@SymbolID(5)
	public void __elem() {
		try {
			int $0 = $pos;
			do {
				if (!__$()) continue; // ~__
				$pos = $0;
				__$(); // ~__
				return;
			} while (false);
			$pos = $0;
			do {
				if (!ID$()) continue; // ID
				int $1 = $pos;
				$2: do {
					do {
						__$(); // ~__?
						if (!$string("=")) continue; // "="
						__$(); // ~__?
						if (!$string("<")) continue; // "<"
						while (__elem$()); // __elem*
						if (!$string(">")) continue; // ">"
						continue $2;
					} while (false);
					$pos = $1;
					$1 = 0;
				} while (false);
				if ($1 == 0) {
					int $3 = $pos;
					$4: do {
						do {
							__$(); // ~__?
							if (!$string("=")) continue; // "="
							__$(); // ~__?
							if (!ID$()) continue; // ID
							continue $4;
						} while (false);
						$pos = $3;
					} while (false);
					PARAM$(); // PARAM?
					__$(); // ~__?
					int $5 = $pos;
					$6: do {
						do {
							if (!$string("?")) continue; // "?"
							continue $6;
						} while (false);
						$pos = $5;
						do {
							if (!$string("*")) continue; // "*"
							continue $6;
						} while (false);
						$pos = $5;
						do {
							if (!$string("+")) continue; // "+"
							continue $6;
						} while (false);
						$pos = $5;
					} while (false);
				}
				$pos = $0;
				String value = "", id, param = "()";
				String[] pos;
				id = ID(); // ID
				$7: do {
					int $8 = $pos;
					do {
						__$(); // ~__?
						if (!$string("=")) continue; // "="
						__$(); // ~__?
						if (!$string("<")) continue; // "<"
						while (__elem$()); // __elem*
						if (!$string(">")) continue; // ">"
						$pos = $8;
						__$(); // ~__?
						$pos += 1; // "="
						__$(); // ~__?
						pos = symbol.image();
						$pos += 1; // "<"
						for (int $9 = $pos; __elem$(); $9 = $pos) {
							$pos = $9;
							__elem(); // __elem*
						}
						$pos += 1; // ">"
						symbol.image(id, pos);
						continue $7;
					} while (false);
					$pos = $8;
					$10: do {
						int $11 = $pos;
						do {
							__$(); // ~__?
							if (!$string("=")) continue; // "="
							__$(); // ~__?
							if (!ID$()) continue; // ID
							$pos = $11;
							value = id + " = ";
							__$(); // ~__?
							$pos += 1; // "="
							__$(); // ~__?
							id = ID(); // ID
							continue $10;
						} while (false);
						$pos = $11;
					} while (false);
					int $12 = $pos;
					if (PARAM$()) {
						$pos = $12;
						param = PARAM(); // PARAM?
					}
					__$(); // ~__?
					$13: do {
						int $14 = $pos;
						do {
							if (!$string("?")) continue; // "?"
							$pos = $14;
							$pos += 1; // "?"
							symbol.id_option(value, id, param);
							continue $13;
						} while (false);
						$pos = $14;
						do {
							if (!$string("*")) continue; // "*"
							$pos = $14;
							$pos += 1; // "*"
							symbol.id_more(value, id, param);
							continue $13;
						} while (false);
						$pos = $14;
						do {
							if (!$string("+")) continue; // "+"
							$pos = $14;
							$pos += 1; // "+"
							symbol.id(value, id, param);
							symbol.id_more(value, id, param);
							continue $13;
						} while (false);
						$pos = $14;
						symbol.id(value, id, param);
					} while (false);
				} while (false);
				return;
			} while (false);
			$pos = $0;
			do {
				if (!$string("~")) continue; // "~"
				__$(); // ~__?
				if (!ID$()) continue; // ID
				__$(); // ~__?
				int $15 = $pos;
				$16: do {
					do {
						if (!$string("?")) continue; // "?"
						continue $16;
					} while (false);
					$pos = $15;
					do {
						if (!$string("*")) continue; // "*"
						continue $16;
					} while (false);
					$pos = $15;
					do {
						if (!$string("+")) continue; // "+"
						continue $16;
					} while (false);
					$pos = $15;
				} while (false);
				$pos = $0;
				String id;
				$pos += 1; // "~"
				__$(); // ~__?
				id = ID(); // ID
				__$(); // ~__?
				$17: do {
					int $18 = $pos;
					do {
						if (!$string("?")) continue; // "?"
						$pos = $18;
						$pos += 1; // "?"
						symbol.pred_option(id + "$()", '~' + id);
						continue $17;
					} while (false);
					$pos = $18;
					do {
						if (!$string("*")) continue; // "*"
						$pos = $18;
						$pos += 1; // "*"
						symbol.pred_more(id + "$()", '~' + id);
						continue $17;
					} while (false);
					$pos = $18;
					do {
						if (!$string("+")) continue; // "+"
						$pos = $18;
						$pos += 1; // "+"
						symbol.pred(id + "$()", id + "$()", '~' + id);
						symbol.pred_more(id + "$()", '~' + id);
						continue $17;
					} while (false);
					$pos = $18;
					symbol.pred(id + "$()", id + "$()", '~' + id);
				} while (false);
				return;
			} while (false);
			$pos = $0;
			do {
				if (!$string(".")) continue; // "."
				__$(); // ~__?
				int $19 = $pos;
				$20: do {
					do {
						if (!$string("?")) continue; // "?"
						continue $20;
					} while (false);
					$pos = $19;
					do {
						if (!$string("*")) continue; // "*"
						continue $20;
					} while (false);
					$pos = $19;
					do {
						if (!$string("+")) continue; // "+"
						continue $20;
					} while (false);
					$pos = $19;
				} while (false);
				$pos = $0;
				$pos += 1; // "."
				__$(); // ~__?
				$21: do {
					int $22 = $pos;
					do {
						if (!$string("?")) continue; // "?"
						$pos = $22;
						$pos += 1; // "?"
						symbol.pred_option("$any()", ".");
						continue $21;
					} while (false);
					$pos = $22;
					do {
						if (!$string("*")) continue; // "*"
						$pos = $22;
						$pos += 1; // "*"
						symbol.pred_more("$any()", ".");
						continue $21;
					} while (false);
					$pos = $22;
					do {
						if (!$string("+")) continue; // "+"
						$pos = $22;
						$pos += 1; // "+"
						symbol.pred("$any()", "++$pos", ".");
						symbol.pred_more("$any()", ".");
						continue $21;
					} while (false);
					$pos = $22;
					symbol.pred("$any()", "++$pos", ".");
				} while (false);
				return;
			} while (false);
			$pos = $0;
			do {
				if (!STRING$()) continue; // STRING
				int $23 = $pos;
				$24: do {
					do {
						if (!$string("i")) continue; // "i"
						continue $24;
					} while (false);
					$pos = $23;
				} while (false);
				__$(); // ~__?
				int $25 = $pos;
				$26: do {
					do {
						if (!$string("?")) continue; // "?"
						continue $26;
					} while (false);
					$pos = $25;
					do {
						if (!$string("*")) continue; // "*"
						continue $26;
					} while (false);
					$pos = $25;
					do {
						if (!$string("+")) continue; // "+"
						continue $26;
					} while (false);
					$pos = $25;
				} while (false);
				$pos = $0;
				String image, imagei, f = "$string(";
				int length;
				int $27 = $lock(1); // = <
				int $28 = $lock(1); // = <
				length = STRING(); // STRING
				image = $image($28); // >
				$29: do {
					int $30 = $pos;
					do {
						if (!$string("i")) continue; // "i"
						$pos = $30;
						$pos += 1; // "i"
						f = "$stringi(";
						image = image.toLowerCase();
						continue $29;
					} while (false);
					$pos = $30;
				} while (false);
				imagei = $image($27); // >
				__$(); // ~__?
				$31: do {
					int $32 = $pos;
					do {
						if (!$string("?")) continue; // "?"
						$pos = $32;
						$pos += 1; // "?"
						symbol.pred_option(f + image + ')', imagei);
						continue $31;
					} while (false);
					$pos = $32;
					do {
						if (!$string("*")) continue; // "*"
						$pos = $32;
						$pos += 1; // "*"
						symbol.pred_more(f + image + ')', imagei);
						continue $31;
					} while (false);
					$pos = $32;
					do {
						if (!$string("+")) continue; // "+"
						$pos = $32;
						$pos += 1; // "+"
						symbol.pred(f + image + ')', "$pos += " + length, imagei);
						symbol.pred_more(f + image + ')', imagei);
						continue $31;
					} while (false);
					$pos = $32;
					symbol.pred(f + image + ')', "$pos += " + length, imagei);
				} while (false);
				return;
			} while (false);
			$pos = $0;
			do {
				if (!CHAR$()) continue; // CHAR
				__$(); // ~__?
				int $33 = $pos;
				$34: do {
					do {
						if (!$string("?")) continue; // "?"
						continue $34;
					} while (false);
					$pos = $33;
					do {
						if (!$string("*")) continue; // "*"
						continue $34;
					} while (false);
					$pos = $33;
					do {
						if (!$string("+")) continue; // "+"
						continue $34;
					} while (false);
					$pos = $33;
				} while (false);
				$pos = $0;
				String image;
				image = CHAR(); // CHAR
				__$(); // ~__?
				$35: do {
					int $36 = $pos;
					do {
						if (!$string("?")) continue; // "?"
						$pos = $36;
						$pos += 1; // "?"
						symbol.pred_option(pred_char(image), image);
						continue $35;
					} while (false);
					$pos = $36;
					do {
						if (!$string("*")) continue; // "*"
						$pos = $36;
						$pos += 1; // "*"
						symbol.pred_more(pred_char(image), image);
						continue $35;
					} while (false);
					$pos = $36;
					do {
						if (!$string("+")) continue; // "+"
						$pos = $36;
						$pos += 1; // "+"
						symbol.pred(pred_char(image), "++$pos", image);
						symbol.pred_more(pred_char(image), image);
						continue $35;
					} while (false);
					$pos = $36;
					symbol.pred(pred_char(image), "++$pos", image);
				} while (false);
				return;
			} while (false);
			$pos = $0;
			do {
				if (!$string("&")) continue; // "&"
				__$(); // ~__?
				int $37 = $pos;
				$38: do {
					do {
						if (!ID$()) continue; // ID
						continue $38;
					} while (false);
					$pos = $37;
					do {
						if (!$string(".")) continue; // "."
						continue $38;
					} while (false);
					$pos = $37;
					do {
						if (!STRING$()) continue; // ~STRING
						int $39 = $pos;
						$40: do {
							do {
								if (!$string("i")) continue; // "i"
								continue $40;
							} while (false);
							$pos = $39;
						} while (false);
						continue $38;
					} while (false);
					$pos = $37;
					do {
						if (!CHAR$()) continue; // CHAR
						continue $38;
					} while (false);
					$pos = $37;
					$37 = 0;
				} while (false);
				if ($37 == 0) {
					if (!$string("(")) continue; // "("
					if (!_par_$()) continue; // _par_
					if (!$string(")")) continue; // ")"
				}
				$pos = $0;
				String image;
				$pos += 1; // "&"
				__$(); // ~__?
				$41: do {
					int $42 = $pos;
					do {
						if (!ID$()) continue; // ID
						$pos = $42;
						image = ID(); // ID
						symbol.pred_and(image + "$()", image);
						continue $41;
					} while (false);
					$pos = $42;
					do {
						if (!$string(".")) continue; // "."
						$pos = $42;
						$pos += 1; // "."
						symbol.pred_and("$any()", ".");
						continue $41;
					} while (false);
					$pos = $42;
					do {
						if (!STRING$()) continue; // ~STRING
						int $43 = $pos;
						$44: do {
							do {
								if (!$string("i")) continue; // "i"
								continue $44;
							} while (false);
							$pos = $43;
						} while (false);
						$pos = $42;
						String imagei, f = "$string(";
						int $45 = $lock(1); // = <
						int $46 = $lock(1); // = <
						STRING$(); // ~STRING
						image = $image($46); // >
						$47: do {
							int $48 = $pos;
							do {
								if (!$string("i")) continue; // "i"
								$pos = $48;
								$pos += 1; // "i"
								f = "$stringi(";
								image = image.toLowerCase();
								continue $47;
							} while (false);
							$pos = $48;
						} while (false);
						imagei = $image($45); // >
						symbol.pred_and(f + image + ')', imagei);
						continue $41;
					} while (false);
					$pos = $42;
					do {
						if (!CHAR$()) continue; // CHAR
						$pos = $42;
						image = CHAR(); // CHAR
						symbol.pred_and(pred_char(image), image);
						continue $41;
					} while (false);
					$pos = $42;
					symbol.par();
					$pos += 1; // "("
					_par_(); // _par_
					$pos += 1; // ")"
					symbol.par_and();
				} while (false);
				return;
			} while (false);
			$pos = $0;
			do {
				if (!$string("!")) continue; // "!"
				__$(); // ~__?
				int $49 = $pos;
				$50: do {
					do {
						if (!ID$()) continue; // ID
						continue $50;
					} while (false);
					$pos = $49;
					do {
						if (!$string(".")) continue; // "."
						continue $50;
					} while (false);
					$pos = $49;
					do {
						if (!STRING$()) continue; // ~STRING
						int $51 = $pos;
						$52: do {
							do {
								if (!$string("i")) continue; // "i"
								continue $52;
							} while (false);
							$pos = $51;
						} while (false);
						continue $50;
					} while (false);
					$pos = $49;
					do {
						if (!CHAR$()) continue; // CHAR
						continue $50;
					} while (false);
					$pos = $49;
					$49 = 0;
				} while (false);
				if ($49 == 0) {
					if (!$string("(")) continue; // "("
					if (!_par_$()) continue; // _par_
					if (!$string(")")) continue; // ")"
				}
				$pos = $0;
				String image;
				$pos += 1; // "!"
				__$(); // ~__?
				$53: do {
					int $54 = $pos;
					do {
						if (!ID$()) continue; // ID
						$pos = $54;
						image = ID(); // ID
						symbol.pred_not(image + "$()", image);
						continue $53;
					} while (false);
					$pos = $54;
					do {
						if (!$string(".")) continue; // "."
						$pos = $54;
						$pos += 1; // "."
						symbol.pred_not("$any()", ".");
						continue $53;
					} while (false);
					$pos = $54;
					do {
						if (!STRING$()) continue; // ~STRING
						int $55 = $pos;
						$56: do {
							do {
								if (!$string("i")) continue; // "i"
								continue $56;
							} while (false);
							$pos = $55;
						} while (false);
						$pos = $54;
						String imagei, f = "$string(";
						int $57 = $lock(1); // = <
						int $58 = $lock(1); // = <
						STRING$(); // ~STRING
						image = $image($58); // >
						$59: do {
							int $60 = $pos;
							do {
								if (!$string("i")) continue; // "i"
								$pos = $60;
								$pos += 1; // "i"
								f = "$stringi(";
								image = image.toLowerCase();
								continue $59;
							} while (false);
							$pos = $60;
						} while (false);
						imagei = $image($57); // >
						symbol.pred_not(f + image + ')', imagei);
						continue $53;
					} while (false);
					$pos = $54;
					do {
						if (!CHAR$()) continue; // CHAR
						$pos = $54;
						image = CHAR(); // CHAR
						symbol.pred_not(pred_char(image), image);
						continue $53;
					} while (false);
					$pos = $54;
					symbol.par();
					$pos += 1; // "("
					_par_(); // _par_
					$pos += 1; // ")"
					symbol.par_not();
				} while (false);
				return;
			} while (false);
			$pos = $0;
			do {
				if (!$string("(")) continue; // "("
				if (!_par_$()) continue; // _par_
				if (!$string(")")) continue; // ")"
				__$(); // ~__?
				int $61 = $pos;
				$62: do {
					do {
						if (!$string("?")) continue; // "?"
						continue $62;
					} while (false);
					$pos = $61;
					do {
						if (!$string("*")) continue; // "*"
						continue $62;
					} while (false);
					$pos = $61;
					do {
						if (!$string("+")) continue; // "+"
						continue $62;
					} while (false);
					$pos = $61;
				} while (false);
				$pos = $0;
				symbol.par();
				$pos += 1; // "("
				_par_(); // _par_
				$pos += 1; // ")"
				__$(); // ~__?
				$63: do {
					int $64 = $pos;
					do {
						if (!$string("?")) continue; // "?"
						$pos = $64;
						$pos += 1; // "?"
						symbol.par_option();
						continue $63;
					} while (false);
					$pos = $64;
					do {
						if (!$string("*")) continue; // "*"
						$pos = $64;
						$pos += 1; // "*"
						symbol.par_more();
						continue $63;
					} while (false);
					$pos = $64;
					do {
						if (!$string("+")) continue; // "+"
						$pos = $64;
						$pos += 1; // "+"
						symbol.par_plus();
						continue $63;
					} while (false);
					$pos = $64;
					symbol.par_end();
				} while (false);
				return;
			} while (false);
			$pos = $0;
			String code;
			if (!$string(":")) throw new ParseError(); // ":"
			$65: do {
				int $66 = $pos;
				do {
					if (!$string(" ")) continue; // " "
					$pos = $66;
					$pos += 1; // " "
					continue $65;
				} while (false);
				$pos = $66;
				do {
					if (!$string("\t")) continue; // "\t"
					$pos = $66;
					$pos += 1; // "\t"
					continue $65;
				} while (false);
				$pos = $66;
			} while (false);
			int $67 = $lock(1); // = <
			$68: for (;;) {
				int $69 = $pos;
				do {
					if ($string("\n")) continue; // !"\n"
					if (!$any()) continue; // .
					$pos = $69;
					++$pos; // .
					continue $68;
				} while (false);
				$pos = $69;
				break;
			}
			code = $image($67); // >
			symbol.code(code);
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean __elem$() {
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
					if (!__$()) continue; // ~__
					continue $1;
				} while (false);
				$pos = $0;
				do {
					if (!ID$()) continue; // ID
					int $2 = $pos;
					$3: do {
						do {
							__$(); // ~__?
							if (!$string("=")) continue; // "="
							__$(); // ~__?
							if (!$string("<")) continue; // "<"
							while (__elem$()); // __elem*
							if (!$string(">")) continue; // ">"
							continue $3;
						} while (false);
						$pos = $2;
						$2 = 0;
					} while (false);
					if ($2 == 0) {
						int $4 = $pos;
						$5: do {
							do {
								__$(); // ~__?
								if (!$string("=")) continue; // "="
								__$(); // ~__?
								if (!ID$()) continue; // ID
								continue $5;
							} while (false);
							$pos = $4;
						} while (false);
						PARAM$(); // PARAM?
						__$(); // ~__?
						int $6 = $pos;
						$7: do {
							do {
								if (!$string("?")) continue; // "?"
								continue $7;
							} while (false);
							$pos = $6;
							do {
								if (!$string("*")) continue; // "*"
								continue $7;
							} while (false);
							$pos = $6;
							do {
								if (!$string("+")) continue; // "+"
								continue $7;
							} while (false);
							$pos = $6;
						} while (false);
					}
					continue $1;
				} while (false);
				$pos = $0;
				do {
					if (!$string("~")) continue; // "~"
					__$(); // ~__?
					if (!ID$()) continue; // ID
					__$(); // ~__?
					int $8 = $pos;
					$9: do {
						do {
							if (!$string("?")) continue; // "?"
							continue $9;
						} while (false);
						$pos = $8;
						do {
							if (!$string("*")) continue; // "*"
							continue $9;
						} while (false);
						$pos = $8;
						do {
							if (!$string("+")) continue; // "+"
							continue $9;
						} while (false);
						$pos = $8;
					} while (false);
					continue $1;
				} while (false);
				$pos = $0;
				do {
					if (!$string(".")) continue; // "."
					__$(); // ~__?
					int $10 = $pos;
					$11: do {
						do {
							if (!$string("?")) continue; // "?"
							continue $11;
						} while (false);
						$pos = $10;
						do {
							if (!$string("*")) continue; // "*"
							continue $11;
						} while (false);
						$pos = $10;
						do {
							if (!$string("+")) continue; // "+"
							continue $11;
						} while (false);
						$pos = $10;
					} while (false);
					continue $1;
				} while (false);
				$pos = $0;
				do {
					if (!STRING$()) continue; // STRING
					int $12 = $pos;
					$13: do {
						do {
							if (!$string("i")) continue; // "i"
							continue $13;
						} while (false);
						$pos = $12;
					} while (false);
					__$(); // ~__?
					int $14 = $pos;
					$15: do {
						do {
							if (!$string("?")) continue; // "?"
							continue $15;
						} while (false);
						$pos = $14;
						do {
							if (!$string("*")) continue; // "*"
							continue $15;
						} while (false);
						$pos = $14;
						do {
							if (!$string("+")) continue; // "+"
							continue $15;
						} while (false);
						$pos = $14;
					} while (false);
					continue $1;
				} while (false);
				$pos = $0;
				do {
					if (!CHAR$()) continue; // CHAR
					__$(); // ~__?
					int $16 = $pos;
					$17: do {
						do {
							if (!$string("?")) continue; // "?"
							continue $17;
						} while (false);
						$pos = $16;
						do {
							if (!$string("*")) continue; // "*"
							continue $17;
						} while (false);
						$pos = $16;
						do {
							if (!$string("+")) continue; // "+"
							continue $17;
						} while (false);
						$pos = $16;
					} while (false);
					continue $1;
				} while (false);
				$pos = $0;
				do {
					if (!$string("&")) continue; // "&"
					__$(); // ~__?
					int $18 = $pos;
					$19: do {
						do {
							if (!ID$()) continue; // ID
							continue $19;
						} while (false);
						$pos = $18;
						do {
							if (!$string(".")) continue; // "."
							continue $19;
						} while (false);
						$pos = $18;
						do {
							if (!STRING$()) continue; // ~STRING
							int $20 = $pos;
							$21: do {
								do {
									if (!$string("i")) continue; // "i"
									continue $21;
								} while (false);
								$pos = $20;
							} while (false);
							continue $19;
						} while (false);
						$pos = $18;
						do {
							if (!CHAR$()) continue; // CHAR
							continue $19;
						} while (false);
						$pos = $18;
						$18 = 0;
					} while (false);
					if ($18 == 0) {
						if (!$string("(")) continue; // "("
						if (!_par_$()) continue; // _par_
						if (!$string(")")) continue; // ")"
					}
					continue $1;
				} while (false);
				$pos = $0;
				do {
					if (!$string("!")) continue; // "!"
					__$(); // ~__?
					int $22 = $pos;
					$23: do {
						do {
							if (!ID$()) continue; // ID
							continue $23;
						} while (false);
						$pos = $22;
						do {
							if (!$string(".")) continue; // "."
							continue $23;
						} while (false);
						$pos = $22;
						do {
							if (!STRING$()) continue; // ~STRING
							int $24 = $pos;
							$25: do {
								do {
									if (!$string("i")) continue; // "i"
									continue $25;
								} while (false);
								$pos = $24;
							} while (false);
							continue $23;
						} while (false);
						$pos = $22;
						do {
							if (!CHAR$()) continue; // CHAR
							continue $23;
						} while (false);
						$pos = $22;
						$22 = 0;
					} while (false);
					if ($22 == 0) {
						if (!$string("(")) continue; // "("
						if (!_par_$()) continue; // _par_
						if (!$string(")")) continue; // ")"
					}
					continue $1;
				} while (false);
				$pos = $0;
				do {
					if (!$string("(")) continue; // "("
					if (!_par_$()) continue; // _par_
					if (!$string(")")) continue; // ")"
					__$(); // ~__?
					int $26 = $pos;
					$27: do {
						do {
							if (!$string("?")) continue; // "?"
							continue $27;
						} while (false);
						$pos = $26;
						do {
							if (!$string("*")) continue; // "*"
							continue $27;
						} while (false);
						$pos = $26;
						do {
							if (!$string("+")) continue; // "+"
							continue $27;
						} while (false);
						$pos = $26;
					} while (false);
					continue $1;
				} while (false);
				$pos = $0;
				$0 = 0;
			} while (false);
			if ($0 == 0) {
				if (!$string(":")) continue; // ":"
				int $28 = $pos;
				$29: do {
					do {
						if (!$string(" ")) continue; // " "
						continue $29;
					} while (false);
					$pos = $28;
					do {
						if (!$string("\t")) continue; // "\t"
						continue $29;
					} while (false);
					$pos = $28;
				} while (false);
				$30: for (;;) {
					int $31 = $pos;
					do {
						if ($string("\n")) continue; // !"\n"
						if (!$any()) continue; // .
						continue $30;
					} while (false);
					$pos = $31;
					break;
				}
			}
			$ok(5, $p);
			break;
		} while ($r = $ng(5, $pos = $p));
		return $r;
	}
	@SymbolID(6)
	public void _par_() {
		try {
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!__elem$()) continue; // __elem
					$pos = $1;
					__elem(); // __elem
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string("/")) continue; // "/"
					$pos = $1;
					$pos += 1; // "/"
					symbol.choice();
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean _par_$() {
		int $p = $cache(6);
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
					if (!__elem$()) continue; // __elem
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string("/")) continue; // "/"
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			$ok(6, $p);
			break;
		} while ($r = $ng(6, $pos = $p));
		return $r;
	}
	@SymbolID(7)
	public void Type() {
		if (!Type$()) throw new ParseError();
	}
	protected boolean Type$() {
		int $p = $cache(7);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			if (!TypeArguments$()) continue; // ~TypeArguments
			$0: for (;;) {
				int $1 = $pos;
				do {
					__$(); // ~__?
					if (!$string("[")) continue; // "["
					__$(); // ~__?
					if (!$string("]")) continue; // "]"
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			$ok(7, $p);
			break;
		} while ($r = $ng(7, $pos = $p));
		return $r;
	}
	@SymbolID(8)
	public void TypeArguments() {
		if (!TypeArguments$()) throw new ParseError();
	}
	protected boolean TypeArguments$() {
		int $p = $cache(8);
		if ($p > 0) {
			$pos = $p;
			return true;
		}
		if ($p < 0) return false;
		$p = $pos;
		boolean $r = true;
		do {
			if (!ID$()) continue; // ~ID
			int $0 = $pos;
			$1: do {
				do {
					__$(); // ~__?
					if (!$string("<")) continue; // "<"
					if (!_TypeArgument_$()) continue; // ~_TypeArgument_
					if (!$string(">")) continue; // ">"
					continue $1;
				} while (false);
				$pos = $0;
			} while (false);
			int $2 = $pos;
			$3: do {
				do {
					__$(); // ~__?
					if (!$string(".")) continue; // "."
					__$(); // ~__?
					if (!TypeArguments$()) continue; // ~TypeArguments
					continue $3;
				} while (false);
				$pos = $2;
			} while (false);
			$ok(8, $p);
			break;
		} while ($r = $ng(8, $pos = $p));
		return $r;
	}
	@SymbolID(9)
	public void _TypeArgument_() {
		if (!_TypeArgument_$()) throw new ParseError();
	}
	protected boolean _TypeArgument_$() {
		int $p = $cache(9);
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
					if (!$string("?")) continue; // "?"
					__$(); // ~__?
					int $2 = $pos;
					$3: do {
						do {
							int $4 = $pos;
							$5: do {
								do {
									if (!$string("extends")) continue; // "extends"
									continue $5;
								} while (false);
								$pos = $4;
								$4 = 0;
							} while (false);
							if ($4 == 0) {
								if (!$string("super")) continue; // "super"
							}
							if (!__$()) continue; // ~__
							if (!Type$()) continue; // ~Type
							continue $3;
						} while (false);
						$pos = $2;
					} while (false);
					continue $1;
				} while (false);
				$pos = $0;
				$0 = 0;
			} while (false);
			if ($0 == 0) {
				if (!Type$()) continue; // ~Type
			}
			__$(); // ~__?
			int $6 = $pos;
			$7: do {
				do {
					if (!$string(",")) continue; // ","
					if (!_TypeArgument_$()) continue; // ~_TypeArgument_
					continue $7;
				} while (false);
				$pos = $6;
			} while (false);
			$ok(9, $p);
			break;
		} while ($r = $ng(9, $pos = $p));
		return $r;
	}
	public void $main() {
		try {
			String command, arg, code;
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!__$()) continue; // ~__
					$pos = $1;
					__$(); // ~__
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!ID$()) continue; // ID
					__$(); // ~__?
					if (!$string("=")) continue; // "="
					$2: for (;;) {
						int $3 = $pos;
						do {
							if (!__$()) continue; // ~__
							continue $2;
						} while (false);
						$pos = $3;
						do {
							if (!$string("\"")) continue; // "\""
							$4: for (;;) {
								int $5 = $pos;
								do {
									if ($string("\"")) continue; // !"\""
									if (!$any()) continue; // .
									continue $4;
								} while (false);
								$pos = $5;
								break;
							}
							if (!$string("\"")) continue; // "\""
							continue $2;
						} while (false);
						$pos = $3;
						do {
							$string("-"); // "-"?
							if (!$char($CHAR[5])) continue; // [0-9]
							while ($char($CHAR[5])) ; // [0-9]*
							continue $2;
						} while (false);
						$pos = $3;
						break;
					}
					if (!$string(";")) continue; // ";"
					$pos = $1;
					command = ID(); // ID
					__$(); // ~__?
					$pos += 1; // "="
					$6: for (;;) {
						int $7 = $pos;
						do {
							if (!__$()) continue; // ~__
							$pos = $7;
							__$(); // ~__
							continue $6;
						} while (false);
						$pos = $7;
						do {
							if (!$string("\"")) continue; // "\""
							$8: for (;;) {
								int $9 = $pos;
								do {
									if ($string("\"")) continue; // !"\""
									if (!$any()) continue; // .
									continue $8;
								} while (false);
								$pos = $9;
								break;
							}
							if (!$string("\"")) continue; // "\""
							$pos = $7;
							$pos += 1; // "\""
							int $10 = $lock(1); // = <
							$11: for (;;) {
								int $12 = $pos;
								do {
									if ($string("\"")) continue; // !"\""
									if (!$any()) continue; // .
									$pos = $12;
									++$pos; // .
									continue $11;
								} while (false);
								$pos = $12;
								break;
							}
							arg = $image($10); // >
							$pos += 1; // "\""
							arg_string(arg);
							continue $6;
						} while (false);
						$pos = $7;
						do {
							$string("-"); // "-"?
							if (!$char($CHAR[5])) continue; // [0-9]
							while ($char($CHAR[5])) ; // [0-9]*
							$pos = $7;
							int $13 = $lock(1); // = <
							$string("-"); // "-"?
							++$pos; // [0-9]
							while ($char($CHAR[5])) ; // [0-9]*
							arg = $image($13); // >
							arg_int(arg);
							continue $6;
						} while (false);
						$pos = $7;
						break;
					}
					$pos += 1; // ";"
					call(command);
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string(":")) continue; // ":"
					int $14 = $pos;
					$15: do {
						do {
							if (!$string(" ")) continue; // " "
							continue $15;
						} while (false);
						$pos = $14;
						do {
							if (!$string("\t")) continue; // "\t"
							continue $15;
						} while (false);
						$pos = $14;
					} while (false);
					$16: for (;;) {
						int $17 = $pos;
						do {
							if ($string("\n")) continue; // !"\n"
							if (!$any()) continue; // .
							continue $16;
						} while (false);
						$pos = $17;
						break;
					}
					$pos = $1;
					$pos += 1; // ":"
					$18: do {
						int $19 = $pos;
						do {
							if (!$string(" ")) continue; // " "
							$pos = $19;
							$pos += 1; // " "
							continue $18;
						} while (false);
						$pos = $19;
						do {
							if (!$string("\t")) continue; // "\t"
							$pos = $19;
							$pos += 1; // "\t"
							continue $18;
						} while (false);
						$pos = $19;
					} while (false);
					int $20 = $lock(1); // = <
					$21: for (;;) {
						int $22 = $pos;
						do {
							if ($string("\n")) continue; // !"\n"
							if (!$any()) continue; // .
							$pos = $22;
							++$pos; // .
							continue $21;
						} while (false);
						$pos = $22;
						break;
					}
					code = $image($20); // >
					code(code);
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			String type, id, param;
			$23: for (;;) {
				int $24 = $pos;
				do {
					if (!$string("<")) continue; // "<"
					__$(); // ~__?
					Type$(); // ~Type?
					__$(); // ~__?
					if (!$string(">")) continue; // ">"
					__$(); // ~__?
					if (!$char($CHAR[6])) continue; // [$A-Za-z_-]
					while ($char($CHAR[2])) ; // [$0-9A-Za-z_-]*
					PARAM$(); // PARAM?
					if (!_par_$()) continue; // _par_
					$pos = $24;
					$pos += 1; // "<"
					__$(); // ~__?
					int $25 = $lock(1); // = <
					Type$(); // ~Type?
					type = $image($25); // >
					__$(); // ~__?
					$pos += 1; // ">"
					__$(); // ~__?
					int $26 = $lock(1); // = <
					++$pos; // [$A-Za-z_-]
					while ($char($CHAR[2])) ; // [$0-9A-Za-z_-]*
					id = $image($26); // >
					param = "()";
					int $27 = $pos;
					if (PARAM$()) {
						$pos = $27;
						param = PARAM(); // PARAM?
					}
					symbol = new Symbol(type, id.replace('-', '_'), param);
					_par_(); // _par_
					symbol.main();
					continue $23;
				} while (false);
				$pos = $24;
				break;
			}
			if ($any()) throw new ParseError(); // !.
			end();
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	@SymbolID(10)
	public void $open(Object in, int line) {$open(in, line, 10);}
	private static final java.util.regex.Pattern[] $CHAR = new java.util.regex.Pattern[]{
		java.util.regex.Pattern.compile("[ \t\r\n]"),
		java.util.regex.Pattern.compile("[A-Za-z_-]"),
		java.util.regex.Pattern.compile("[$0-9A-Za-z_-]"),
		java.util.regex.Pattern.compile("[0-3]"),
		java.util.regex.Pattern.compile("[0-7]"),
		java.util.regex.Pattern.compile("[0-9]"),
		java.util.regex.Pattern.compile("[$A-Za-z_-]")
	};
}
