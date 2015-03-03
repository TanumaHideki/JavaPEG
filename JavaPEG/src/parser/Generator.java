package parser;
public class Generator extends GeneratorHelper {
	public Generator(Object in, int line) {$open(in, line);}
	public Generator(Object in) {$open(in, 1);}
	public Generator() {}
	public static void main(String[] args) throws Exception {
		new Generator($filechooser(".", "Grammar file", "peg")).main();
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
	public String ID() {
		try {
			String image;
			int $0 = $lock(1); // =<
			if (!$char($CHAR[1])) throw new ParseError(); // [$A-Za-z_]
			while ($char($CHAR[2])) ; // [$0-9A-Za-z_]*
			image = $image($0); // >
			return image;
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
			if (!$char($CHAR[1])) continue; // [$A-Za-z_]
			while ($char($CHAR[2])) ; // [$0-9A-Za-z_]*
			$ok(1, $p);
			break;
		} while ($r = $ng(1, $pos = $p));
		return $r;
	}
	@SymbolID(2)
	public String PARAM() {
		try {
			String image;
			int $0 = $lock(1); // =<
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
			int $0 = $lock(1); // =<
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
	public void elem() {
		try {
			int $0 = $pos;
			do {
				if (!S$()) continue; // ~S
				$pos = $0;
				S$(); // ~S
				return;
			} while (false);
			$pos = $0;
			do {
				if (!ID$()) continue; // ID
				int $1 = $pos;
				$2: do {
					do {
						S$(); // ~S?
						if (!$string("=")) continue; // "="
						S$(); // ~S?
						if (!$string("<")) continue; // "<"
						while (elem$()); // elem*
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
							S$(); // ~S?
							if (!$string("=")) continue; // "="
							S$(); // ~S?
							if (!ID$()) continue; // ID
							continue $4;
						} while (false);
						$pos = $3;
					} while (false);
					PARAM$(); // PARAM?
					S$(); // ~S?
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
						S$(); // ~S?
						if (!$string("=")) continue; // "="
						S$(); // ~S?
						if (!$string("<")) continue; // "<"
						while (elem$()); // elem*
						if (!$string(">")) continue; // ">"
						$pos = $8;
						S$(); // ~S?
						$pos += 1; // "="
						S$(); // ~S?
						pos = symbol.image();
						$pos += 1; // "<"
						for (int $9 = $pos; elem$(); $9 = $pos) {
							$pos = $9;
							elem(); // elem*
						}
						$pos += 1; // ">"
						symbol.image(id, pos);
						continue $7;
					} while (false);
					$pos = $8;
					$10: do {
						int $11 = $pos;
						do {
							S$(); // ~S?
							if (!$string("=")) continue; // "="
							S$(); // ~S?
							if (!ID$()) continue; // ID
							$pos = $11;
							value = id + " = ";
							S$(); // ~S?
							$pos += 1; // "="
							S$(); // ~S?
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
					S$(); // ~S?
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
				S$(); // ~S?
				if (!ID$()) continue; // ID
				S$(); // ~S?
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
				S$(); // ~S?
				id = ID(); // ID
				S$(); // ~S?
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
				S$(); // ~S?
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
				S$(); // ~S?
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
				S$(); // ~S?
				int $23 = $pos;
				$24: do {
					do {
						if (!$string("?")) continue; // "?"
						continue $24;
					} while (false);
					$pos = $23;
					do {
						if (!$string("*")) continue; // "*"
						continue $24;
					} while (false);
					$pos = $23;
					do {
						if (!$string("+")) continue; // "+"
						continue $24;
					} while (false);
					$pos = $23;
				} while (false);
				$pos = $0;
				String image;
				int length;
				int $25 = $lock(1); // =<
				length = STRING(); // STRING
				image = $image($25); // >
				S$(); // ~S?
				$26: do {
					int $27 = $pos;
					do {
						if (!$string("?")) continue; // "?"
						$pos = $27;
						$pos += 1; // "?"
						symbol.pred_option("$string(" + image + ')', image);
						continue $26;
					} while (false);
					$pos = $27;
					do {
						if (!$string("*")) continue; // "*"
						$pos = $27;
						$pos += 1; // "*"
						symbol.pred_more("$string(" + image + ')', image);
						continue $26;
					} while (false);
					$pos = $27;
					do {
						if (!$string("+")) continue; // "+"
						$pos = $27;
						$pos += 1; // "+"
						symbol.pred("$string(" + image + ')', "$pos += " + length, image);
						symbol.pred_more("$string(" + image + ')', image);
						continue $26;
					} while (false);
					$pos = $27;
					symbol.pred("$string(" + image + ')', "$pos += " + length, image);
				} while (false);
				return;
			} while (false);
			$pos = $0;
			do {
				if (!CHAR$()) continue; // CHAR
				S$(); // ~S?
				int $28 = $pos;
				$29: do {
					do {
						if (!$string("?")) continue; // "?"
						continue $29;
					} while (false);
					$pos = $28;
					do {
						if (!$string("*")) continue; // "*"
						continue $29;
					} while (false);
					$pos = $28;
					do {
						if (!$string("+")) continue; // "+"
						continue $29;
					} while (false);
					$pos = $28;
				} while (false);
				$pos = $0;
				String image;
				image = CHAR(); // CHAR
				S$(); // ~S?
				$30: do {
					int $31 = $pos;
					do {
						if (!$string("?")) continue; // "?"
						$pos = $31;
						$pos += 1; // "?"
						symbol.pred_option(pred_char(image), image);
						continue $30;
					} while (false);
					$pos = $31;
					do {
						if (!$string("*")) continue; // "*"
						$pos = $31;
						$pos += 1; // "*"
						symbol.pred_more(pred_char(image), image);
						continue $30;
					} while (false);
					$pos = $31;
					do {
						if (!$string("+")) continue; // "+"
						$pos = $31;
						$pos += 1; // "+"
						symbol.pred(pred_char(image), "++$pos", image);
						symbol.pred_more(pred_char(image), image);
						continue $30;
					} while (false);
					$pos = $31;
					symbol.pred(pred_char(image), "++$pos", image);
				} while (false);
				return;
			} while (false);
			$pos = $0;
			do {
				if (!$string("&")) continue; // "&"
				S$(); // ~S?
				int $32 = $pos;
				$33: do {
					do {
						if (!ID$()) continue; // ID
						continue $33;
					} while (false);
					$pos = $32;
					do {
						if (!$string(".")) continue; // "."
						continue $33;
					} while (false);
					$pos = $32;
					do {
						if (!STRING$()) continue; // ~STRING
						continue $33;
					} while (false);
					$pos = $32;
					do {
						if (!CHAR$()) continue; // CHAR
						continue $33;
					} while (false);
					$pos = $32;
					$32 = 0;
				} while (false);
				if ($32 == 0) {
					if (!$string("(")) continue; // "("
					if (!par$()) continue; // par
					if (!$string(")")) continue; // ")"
				}
				$pos = $0;
				String image;
				$pos += 1; // "&"
				S$(); // ~S?
				$34: do {
					int $35 = $pos;
					do {
						if (!ID$()) continue; // ID
						$pos = $35;
						image = ID(); // ID
						symbol.pred_and(image + "$()", image);
						continue $34;
					} while (false);
					$pos = $35;
					do {
						if (!$string(".")) continue; // "."
						$pos = $35;
						$pos += 1; // "."
						symbol.pred_and("$any()", ".");
						continue $34;
					} while (false);
					$pos = $35;
					do {
						if (!STRING$()) continue; // ~STRING
						$pos = $35;
						int $36 = $lock(1); // =<
						STRING$(); // ~STRING
						image = $image($36); // >
						symbol.pred_and("$string(" + image + ')', image);
						continue $34;
					} while (false);
					$pos = $35;
					do {
						if (!CHAR$()) continue; // CHAR
						$pos = $35;
						image = CHAR(); // CHAR
						symbol.pred_and(pred_char(image), image);
						continue $34;
					} while (false);
					$pos = $35;
					symbol.par();
					$pos += 1; // "("
					par(); // par
					$pos += 1; // ")"
					symbol.par_and();
				} while (false);
				return;
			} while (false);
			$pos = $0;
			do {
				if (!$string("!")) continue; // "!"
				S$(); // ~S?
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
					if (!par$()) continue; // par
					if (!$string(")")) continue; // ")"
				}
				$pos = $0;
				String image;
				$pos += 1; // "!"
				S$(); // ~S?
				$39: do {
					int $40 = $pos;
					do {
						if (!ID$()) continue; // ID
						$pos = $40;
						image = ID(); // ID
						symbol.pred_not(image + "$()", image);
						continue $39;
					} while (false);
					$pos = $40;
					do {
						if (!$string(".")) continue; // "."
						$pos = $40;
						$pos += 1; // "."
						symbol.pred_not("$any()", ".");
						continue $39;
					} while (false);
					$pos = $40;
					do {
						if (!STRING$()) continue; // ~STRING
						$pos = $40;
						int $41 = $lock(1); // =<
						STRING$(); // ~STRING
						image = $image($41); // >
						symbol.pred_not("$string(" + image + ')', image);
						continue $39;
					} while (false);
					$pos = $40;
					do {
						if (!CHAR$()) continue; // CHAR
						$pos = $40;
						image = CHAR(); // CHAR
						symbol.pred_not(pred_char(image), image);
						continue $39;
					} while (false);
					$pos = $40;
					symbol.par();
					$pos += 1; // "("
					par(); // par
					$pos += 1; // ")"
					symbol.par_not();
				} while (false);
				return;
			} while (false);
			$pos = $0;
			do {
				if (!$string("(")) continue; // "("
				if (!par$()) continue; // par
				if (!$string(")")) continue; // ")"
				S$(); // ~S?
				int $42 = $pos;
				$43: do {
					do {
						if (!$string("?")) continue; // "?"
						continue $43;
					} while (false);
					$pos = $42;
					do {
						if (!$string("*")) continue; // "*"
						continue $43;
					} while (false);
					$pos = $42;
					do {
						if (!$string("+")) continue; // "+"
						continue $43;
					} while (false);
					$pos = $42;
				} while (false);
				$pos = $0;
				symbol.par();
				$pos += 1; // "("
				par(); // par
				$pos += 1; // ")"
				S$(); // ~S?
				$44: do {
					int $45 = $pos;
					do {
						if (!$string("?")) continue; // "?"
						$pos = $45;
						$pos += 1; // "?"
						symbol.par_option();
						continue $44;
					} while (false);
					$pos = $45;
					do {
						if (!$string("*")) continue; // "*"
						$pos = $45;
						$pos += 1; // "*"
						symbol.par_more();
						continue $44;
					} while (false);
					$pos = $45;
					do {
						if (!$string("+")) continue; // "+"
						$pos = $45;
						$pos += 1; // "+"
						symbol.par_plus();
						continue $44;
					} while (false);
					$pos = $45;
					symbol.par_end();
				} while (false);
				return;
			} while (false);
			$pos = $0;
			String code;
			if (!$string(":")) throw new ParseError(); // ":"
			$46: do {
				int $47 = $pos;
				do {
					if (!$string(" ")) continue; // " "
					$pos = $47;
					$pos += 1; // " "
					continue $46;
				} while (false);
				$pos = $47;
				do {
					if (!$string("\t")) continue; // "\t"
					$pos = $47;
					$pos += 1; // "\t"
					continue $46;
				} while (false);
				$pos = $47;
			} while (false);
			int $48 = $lock(1); // =<
			$49: for (;;) {
				int $50 = $pos;
				do {
					if ($string("\n")) continue; // !"\n"
					if (!$any()) continue; // .
					$pos = $50;
					++$pos; // .
					continue $49;
				} while (false);
				$pos = $50;
				break;
			}
			code = $image($48); // >
			symbol.code(code);
		} catch (ParseError e) {
			throw e;
		} catch (Exception e) {
			throw new ParseError(e);
		}
	}
	protected boolean elem$() {
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
					if (!S$()) continue; // ~S
					continue $1;
				} while (false);
				$pos = $0;
				do {
					if (!ID$()) continue; // ID
					int $2 = $pos;
					$3: do {
						do {
							S$(); // ~S?
							if (!$string("=")) continue; // "="
							S$(); // ~S?
							if (!$string("<")) continue; // "<"
							while (elem$()); // elem*
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
								S$(); // ~S?
								if (!$string("=")) continue; // "="
								S$(); // ~S?
								if (!ID$()) continue; // ID
								continue $5;
							} while (false);
							$pos = $4;
						} while (false);
						PARAM$(); // PARAM?
						S$(); // ~S?
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
					S$(); // ~S?
					if (!ID$()) continue; // ID
					S$(); // ~S?
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
					S$(); // ~S?
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
					S$(); // ~S?
					int $12 = $pos;
					$13: do {
						do {
							if (!$string("?")) continue; // "?"
							continue $13;
						} while (false);
						$pos = $12;
						do {
							if (!$string("*")) continue; // "*"
							continue $13;
						} while (false);
						$pos = $12;
						do {
							if (!$string("+")) continue; // "+"
							continue $13;
						} while (false);
						$pos = $12;
					} while (false);
					continue $1;
				} while (false);
				$pos = $0;
				do {
					if (!CHAR$()) continue; // CHAR
					S$(); // ~S?
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
					if (!$string("&")) continue; // "&"
					S$(); // ~S?
					int $16 = $pos;
					$17: do {
						do {
							if (!ID$()) continue; // ID
							continue $17;
						} while (false);
						$pos = $16;
						do {
							if (!$string(".")) continue; // "."
							continue $17;
						} while (false);
						$pos = $16;
						do {
							if (!STRING$()) continue; // ~STRING
							continue $17;
						} while (false);
						$pos = $16;
						do {
							if (!CHAR$()) continue; // CHAR
							continue $17;
						} while (false);
						$pos = $16;
						$16 = 0;
					} while (false);
					if ($16 == 0) {
						if (!$string("(")) continue; // "("
						if (!par$()) continue; // par
						if (!$string(")")) continue; // ")"
					}
					continue $1;
				} while (false);
				$pos = $0;
				do {
					if (!$string("!")) continue; // "!"
					S$(); // ~S?
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
						if (!par$()) continue; // par
						if (!$string(")")) continue; // ")"
					}
					continue $1;
				} while (false);
				$pos = $0;
				do {
					if (!$string("(")) continue; // "("
					if (!par$()) continue; // par
					if (!$string(")")) continue; // ")"
					S$(); // ~S?
					int $20 = $pos;
					$21: do {
						do {
							if (!$string("?")) continue; // "?"
							continue $21;
						} while (false);
						$pos = $20;
						do {
							if (!$string("*")) continue; // "*"
							continue $21;
						} while (false);
						$pos = $20;
						do {
							if (!$string("+")) continue; // "+"
							continue $21;
						} while (false);
						$pos = $20;
					} while (false);
					continue $1;
				} while (false);
				$pos = $0;
				$0 = 0;
			} while (false);
			if ($0 == 0) {
				if (!$string(":")) continue; // ":"
				int $22 = $pos;
				$23: do {
					do {
						if (!$string(" ")) continue; // " "
						continue $23;
					} while (false);
					$pos = $22;
					do {
						if (!$string("\t")) continue; // "\t"
						continue $23;
					} while (false);
					$pos = $22;
				} while (false);
				$24: for (;;) {
					int $25 = $pos;
					do {
						if ($string("\n")) continue; // !"\n"
						if (!$any()) continue; // .
						continue $24;
					} while (false);
					$pos = $25;
					break;
				}
			}
			$ok(5, $p);
			break;
		} while ($r = $ng(5, $pos = $p));
		return $r;
	}
	@SymbolID(6)
	public void par() {
		try {
			$0: for (;;) {
				int $1 = $pos;
				do {
					if (!elem$()) continue; // elem
					$pos = $1;
					elem(); // elem
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
	protected boolean par$() {
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
					if (!elem$()) continue; // elem
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
					S$(); // ~S?
					if (!$string(".")) continue; // "."
					S$(); // ~S?
					if (!TypeArguments$()) continue; // ~TypeArguments
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			$2: for (;;) {
				int $3 = $pos;
				do {
					S$(); // ~S?
					if (!$string("[")) continue; // "["
					S$(); // ~S?
					if (!$string("]")) continue; // "]"
					continue $2;
				} while (false);
				$pos = $3;
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
					S$(); // ~S?
					if (!$string("<")) continue; // "<"
					if (!TypeArgument$()) continue; // ~TypeArgument
					$2: for (;;) {
						int $3 = $pos;
						do {
							if (!$string(",")) continue; // ","
							if (!TypeArgument$()) continue; // ~TypeArgument
							continue $2;
						} while (false);
						$pos = $3;
						break;
					}
					if (!$string(">")) continue; // ">"
					continue $1;
				} while (false);
				$pos = $0;
			} while (false);
			$ok(8, $p);
			break;
		} while ($r = $ng(8, $pos = $p));
		return $r;
	}
	@SymbolID(9)
	public void TypeArgument() {
		if (!TypeArgument$()) throw new ParseError();
	}
	protected boolean TypeArgument$() {
		int $p = $cache(9);
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
					if (!$string("?")) continue; // "?"
					int $2 = $pos;
					$3: do {
						do {
							S$(); // ~S?
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
							if (!S$()) continue; // ~S
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
			S$(); // ~S?
			$ok(9, $p);
			break;
		} while ($r = $ng(9, $pos = $p));
		return $r;
	}
	@SymbolID(10)
	public void main() {
		try {
			String command, arg, code;
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
					$2: for (;;) {
						int $3 = $pos;
						do {
							if (!S$()) continue; // ~S
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
					S$(); // ~S?
					$pos += 1; // "="
					$6: for (;;) {
						int $7 = $pos;
						do {
							if (!S$()) continue; // ~S
							$pos = $7;
							S$(); // ~S
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
							int $10 = $lock(1); // =<
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
							int $13 = $lock(1); // =<
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
					int $20 = $lock(1); // =<
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
					S$(); // ~S?
					Type$(); // ~Type?
					S$(); // ~S?
					if (!$string(">")) continue; // ">"
					S$(); // ~S?
					if (!ID$()) continue; // ID
					PARAM$(); // PARAM?
					if (!par$()) continue; // par
					$pos = $24;
					$pos += 1; // "<"
					S$(); // ~S?
					int $25 = $lock(1); // =<
					Type$(); // ~Type?
					type = $image($25); // >
					S$(); // ~S?
					$pos += 1; // ">"
					S$(); // ~S?
					id = ID(); // ID
					param = "()";
					int $26 = $pos;
					if (PARAM$()) {
						$pos = $26;
						param = PARAM(); // PARAM?
					}
					symbol = new Symbol(type, id, param);
					par(); // par
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
	protected boolean main$() {
		int $p = $cache(10);
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
					$2: for (;;) {
						int $3 = $pos;
						do {
							if (!S$()) continue; // ~S
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
					continue $0;
				} while (false);
				$pos = $1;
				do {
					if (!$string(":")) continue; // ":"
					int $6 = $pos;
					$7: do {
						do {
							if (!$string(" ")) continue; // " "
							continue $7;
						} while (false);
						$pos = $6;
						do {
							if (!$string("\t")) continue; // "\t"
							continue $7;
						} while (false);
						$pos = $6;
					} while (false);
					$8: for (;;) {
						int $9 = $pos;
						do {
							if ($string("\n")) continue; // !"\n"
							if (!$any()) continue; // .
							continue $8;
						} while (false);
						$pos = $9;
						break;
					}
					continue $0;
				} while (false);
				$pos = $1;
				break;
			}
			$10: for (;;) {
				int $11 = $pos;
				do {
					if (!$string("<")) continue; // "<"
					S$(); // ~S?
					Type$(); // ~Type?
					S$(); // ~S?
					if (!$string(">")) continue; // ">"
					S$(); // ~S?
					if (!ID$()) continue; // ID
					PARAM$(); // PARAM?
					if (!par$()) continue; // par
					continue $10;
				} while (false);
				$pos = $11;
				break;
			}
			if ($any()) continue; // !.
			$ok(10, $p);
			break;
		} while ($r = $ng(10, $pos = $p));
		return $r;
	}
	@SymbolID(11)
	public void $open(Object in, int line) {$open(in, line, 11);}
	private static final java.util.regex.Pattern[] $CHAR = new java.util.regex.Pattern[]{
		java.util.regex.Pattern.compile("[ \t\r\n]"),
		java.util.regex.Pattern.compile("[$A-Za-z_]"),
		java.util.regex.Pattern.compile("[$0-9A-Za-z_]"),
		java.util.regex.Pattern.compile("[0-3]"),
		java.util.regex.Pattern.compile("[0-7]"),
		java.util.regex.Pattern.compile("[0-9]")
	};
}
