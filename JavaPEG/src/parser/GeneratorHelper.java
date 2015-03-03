package parser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class GeneratorHelper extends Parser {
	String indent = "";
	ArrayList<Class<?>> types = new ArrayList<Class<?>>();
	ArrayList<Object> args = new ArrayList<Object>();
	String pkg = "";
	Method[] methods = null;
	int new_id = -1;
	ArrayList<String> table = new ArrayList<String>();
	protected Symbol symbol;

	public static void $out(String file) throws FileNotFoundException {
		System.err.println("Output file:  " + file);
		System.setOut(new PrintStream(new FileOutputStream(file)));
	}

	protected void indent() {
		indent += '\t';
	}

	protected void indent(Runnable body, Object... args) {
		indent();
		body.run();
		dedent(args);
	}

	protected void dedent(Object... args) {
		indent = indent.substring(1);
		if (args.length > 0) print(args);
	}

	protected void print(Object... args) {
		System.out.print(indent);
		for (Object s : args)
			System.out.print(s);
		System.out.print('\n');
	}

	public void $package(String name) {
		System.err.println("Package:  " + name);
		print("package ", pkg = name, ';');
	}

	public void $import(String name) {
		print("import ", name, ';');
	}

	public void $class(String name) throws Exception {
		$class(name, "", "");
	}

	public void $class(String name, String extend) throws Exception {
		$class(name, extend, "");
	}

	public void $class(String name, String extend, String impl) throws Exception {
		if (extend.isEmpty()) extend = Parser.class.getName();
		if (!impl.isEmpty()) impl = " implements " + impl;
		System.err.println("Grammar class:  " + name + " extends " + extend + impl);
		print("public class ", name, " extends ", extend, impl, " {");
		indent();
		print("public ", name, "(Object in, int line) {$open(in, line);}");
		print("public ", name, "(Object in) {$open(in, 1);}");
		print("public ", name, "() {}");
		if (!pkg.isEmpty() && extend.indexOf('.') < 0) extend = pkg + '.' + extend;
		Class<?> parser = Class.forName(extend);
		methods = parser.getMethods();
		new_id = parser.getMethod("$open", new Class<?>[] { Object.class, int.class }).getAnnotation(SymbolID.class).value() - 1;
	}

	protected void arg_string(String arg) {
		types.add(String.class);
		args.add(arg);
	}

	protected void arg_int(String arg) {
		types.add(int.class);
		args.add(Integer.parseInt(arg));
	}

	protected void call(String name) {
		try {
			getClass().getMethod('$' + name, types.toArray(new Class<?>[types.size()])).invoke(this, args.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			types.clear();
			args.clear();
		}
	}

	protected void code(String code) {
		print(code.replace("\r", ""));
	}

	String pos() {
		String pos = symbol.unique();
		print("int ", pos, " = $pos;");
		return pos;
	}

	void pos(String pos) {
		print("$pos = ", pos, ';');
	}

	void do_indent(Consumer<String> block) {
		String label = symbol.unique();
		print(label, ": do {");
		indent(() -> block.accept(label), "} while (false);");
	}

	void do_indent(Runnable block) {
		print("do {");
		indent(() -> block.run(), "} while (false);");
	}

	void for_indent(String pre, Consumer<String> block, String post) {
		String label = symbol.unique();
		print(label, ": for (;;) {", pre);
		indent(() -> {
			block.accept(label);
			print("break;");
		}, '}', post);
	}

	void for_indent(Consumer<String> block) {
		for_indent("", block, "");
	}

	interface Elem {
		void eval();

		void main();

		void check();
	}

	interface Option extends Elem {
		default void main() {
			eval();
		}

		default void check() {
		}
	}

	class Choice extends ArrayDeque<Elem> implements Elem {
		private static final long serialVersionUID = 1L;
		public boolean option = true;

		public void addLast(Elem elem) {
			option &= elem instanceof Option;
			super.addLast(elem);
		}

		public void eval() {
			forEach(e -> e.eval());
		}

		public void main() {
			forEach(e -> e.main());
		}

		public void check() {
			forEach(e -> e.check());
		}

		void eval(String pos, String label) {
			do_indent(() -> {
				check();
				pos(pos);
				eval();
				print("continue ", label, ';');
			});
			pos(pos);
		}

		void check(String pos, String label) {
			do_indent(() -> {
				check();
				print("continue ", label, ';');
			});
			pos(pos);
		}

		void and(String pos) {
			do_indent(() -> {
				check();
				pos(pos);
				print(pos, " = 0;");
			});
			print("if (", pos, " != 0)");
		}
	}

	class Par extends ArrayDeque<Choice> implements Elem {
		private static final long serialVersionUID = 1L;
		{
			choice();
		}

		void choice() {
			addLast(new Choice());
		}

		void forEach(Consumer<Choice> action, Consumer<Choice> last) {
			stream().limit(size() - 1).forEach(e -> action.accept(e));
			last.accept(getLast());
		}

		public void eval() {
			if (size() == 1) {
				getFirst().eval();
				return;
			}
			do_indent(label -> {
				String pos = pos();
				forEach(c -> c.eval(pos, label), c -> c.eval());
			});
		}

		public void main() {
			if (size() == 1) {
				getFirst().main();
				return;
			}
			do_indent(label -> {
				String pos = pos();
				forEach(c -> c.eval(pos, label), c -> c.main());
			});
		}

		public void check() {
			if (size() == 1) {
				getFirst().check();
				return;
			}
			String pos = pos();
			do_indent(label -> {
				forEach(c -> c.check(pos, label), c -> {
					if (!c.option) print(pos, " = 0;");
				});
			});
			if (!getLast().option) {
				print("if (", pos, " == 0) {");
				indent(() -> getLast().check(), '}');
			}
		}

		Par option() {
			if (!getLast().option) choice();
			return this;
		}

		Elem more() {
			return new Elem() {
				public void eval() {
					for_indent(label -> {
						String pos = pos();
						forEach(c -> c.eval(pos, label));
					});
				}

				public void main() {
					eval();
				}

				public void check() {
					for_indent(label -> {
						String pos = pos();
						forEach(c -> c.check(pos, label));
					});
				}
			};
		}

		Elem and() {
			return new Elem() {
				public void eval() {
				}

				public void main() {
					String pos = pos();
					forEach(c -> c.and(pos));
					print("throw new ParseError();");
				}

				public void check() {
					String pos = pos();
					if (size() == 1) {
						getFirst().check();
						pos(pos);
						return;
					}
					forEach(c -> c.and(pos), c -> {
						print('{');
						indent(() -> {
							pos(pos);
							c.check();
							pos(pos);
						}, '}');
					});
				}
			};
		}

		Elem not() {
			return new Elem() {
				public void eval() {
				}

				public void main() {
					String pos = pos();
					forEach(c -> {
						do_indent(() -> {
							c.check();
							print("throw new ParseError()");
						});
						pos(pos);
					});
				}

				public void check() {
					String pos = pos();
					forEach(c -> {
						do_indent(() -> {
							c.check();
							print(pos, " = 0;");
						});
						print("if (", pos, " == 0) continue;");
						pos(pos);
					});
				}
			};
		}
	}

	protected class Symbol extends ArrayDeque<Par> {
		private static final long serialVersionUID = 1L;
		String type, name, param;
		int symbol_id = -1;
		int unique;

		public String unique() {
			return "$" + unique++;
		}

		public Symbol(String type, String name, String param) {
			this.type = type;
			this.name = name;
			this.param = param;
			if (methods != null) for (Method m : methods) {
				if (!m.getName().equals(name)) continue;
				SymbolID a = m.getAnnotation(SymbolID.class);
				if (a != null) {
					symbol_id = a.value();
					break;
				}
			}
			if (symbol_id < 0) symbol_id = ++new_id;
			par();
		}

		public void main() {
			unique = 0;
			if (symbol_id != new_id) print("@Override");
			print("@SymbolID(", symbol_id, ')');
			if (type.isEmpty()) {
				print("public void ", name, param, " {");
				indent(() -> print("if (!", name, "$()) throw new ParseError();"), '}');
			} else {
				print("public ", type, ' ', name, param, " {");
				indent(() -> {
					print("try {");
					indent(() -> {
						if (getFirst().size() == 1) {
							getFirst().main();
							return;
						}
						String pos = pos();
						getFirst().forEach(c -> {
							do_indent(() -> {
								c.check();
								pos(pos);
								c.eval();
								if (type.equals("void")) print("return;");
							});
							pos(pos);
						}, c -> c.main());
					}, "} catch (ParseError e) {");
					print("\tthrow e;");
					print("} catch (Exception e) {");
					print("\tthrow new ParseError(e);");
					print('}');
				}, '}');
			}
			unique = 0;
			print("protected boolean ", name, "$() {");
			indent(() -> {
				print("int $p = $cache(", symbol_id, ");");
				print("if ($p > 0) {");
				print("\t$pos = $p;");
				print("\treturn true;");
				print('}');
				print("if ($p < 0) return false;");
				print("$p = $pos;");
				print("boolean $r = true;");
				print("do {");
				indent(() -> {
					getFirst().check();
					print("$ok(", symbol_id, ", $p);");
					print("break;");
				}, "} while ($r = $ng(", symbol_id, ", $pos = $p));");
				print("return $r;");
			}, '}');
		}

		public void par() {
			addLast(new Par());
		}

		public void choice() {
			getLast().choice();
		}

		public void par_end() {
			Par par = removeLast();
			getLast().getLast().addLast(par);
		}

		public void par_option() {
			Par par = removeLast();
			getLast().getLast().addLast(par.option());
		}

		public void par_more() {
			Par par = removeLast();
			getLast().getLast().addLast(par.more());
		}

		public void par_plus() {
			Par par = removeLast();
			getLast().getLast().addLast(par);
			getLast().getLast().addLast(par.more());
		}

		public void par_and() {
			Par par = removeLast();
			getLast().getLast().addLast(par.and());
		}

		public void par_not() {
			Par par = removeLast();
			getLast().getLast().addLast(par.not());
		}

		public void code(String code) {
			String image = code.replace("\r", "");
			getLast().getLast().addLast((Option) () -> print(image));
		}

		public String[] image() {
			String[] pos = new String[1];
			getLast().getLast().addLast((Option) () -> {
				print("int ", pos[0] = unique(), " = $lock(1); // =<");
			});
			return pos;
		}

		public void image(String image, String[] pos) {
			getLast().getLast().addLast((Option) () -> print(image, " = $image(", pos[0], "); // >"));
		}

		public void id(String value, String id, String param) {
			getLast().getLast().addLast(new Elem() {
				public void eval() {
					print(value, id, param, "; // ", id);
				}

				public void main() {
					eval();
				}

				public void check() {
					print("if (!", id, "$()) continue; // ", id);
				}
			});
		}

		public void id_option(String value, String id, String param) {
			getLast().getLast().addLast(new Elem() {
				public void eval() {
					String pos = pos();
					print("if (", id, "$()) {");
					indent(() -> {
						pos(pos);
						print(value, id, param, "; // ", id, '?');
					});
					print('}');
				}

				public void main() {
					eval();
				}

				public void check() {
					print(id, "$(); // ", id, '?');
				}
			});
		}

		public void id_more(String value, String id, String param) {
			getLast().getLast().addLast(new Elem() {
				public void eval() {
					String pos = unique();
					print("for (int ", pos, " = $pos; ", id, "$(); ", pos, " = $pos) {");
					indent(() -> {
						pos(pos);
						print(value, id, param, "; // ", id, '*');
					});
					print('}');
				}

				public void main() {
					eval();
				}

				public void check() {
					print("while (", id, "$()); // ", id, '*');
				}
			});
		}

		public void pred(String pred, String eval, String rem) {
			getLast().getLast().addLast(new Elem() {
				public void eval() {
					print(eval, "; // ", rem);
				}

				public void main() {
					print("if (!", pred, ") throw new ParseError(); // ", rem);
				}

				public void check() {
					print("if (!", pred, ") continue; // ", rem);
				}
			});
		}

		public void pred_option(String pred, String rem) {
			getLast().getLast().addLast(new Elem() {
				public void eval() {
					print(pred, "; // ", rem, '?');
				}

				public void main() {
					eval();
				}

				public void check() {
					eval();
				}
			});
		}

		public void pred_more(String pred, String rem) {
			getLast().getLast().addLast(new Elem() {
				public void eval() {
					print("while (", pred, ") ; // ", rem, '*');
				}

				public void main() {
					eval();
				}

				public void check() {
					eval();
				}
			});
		}

		public void pred_and(String pred, String rem) {
			getLast().getLast().addLast(new Elem() {
				public void eval() {
				}

				public void main() {
					String pos = pos();
					print("if (!", pred, ") throw new ParseError(); // &", rem);
					pos(pos);
				}

				public void check() {
					String pos = pos();
					print("if (!", pred, ") continue; // &", rem);
					pos(pos);
				}
			});
		}

		public void pred_not(String pred, String rem) {
			getLast().getLast().addLast(new Elem() {
				public void eval() {
				}

				public void main() {
					print("if (", pred, ") throw new ParseError(); // !", rem);
				}

				public void check() {
					print("if (", pred, ") continue; // !", rem);
				}
			});
		}
	}

	protected String pred_char(String pattern) {
		int i = table.indexOf(pattern);
		if (i < 0) table.add(i = table.size(), pattern);
		return "$char($CHAR[" + i + "])";
	}

	protected void end() {
		if (indent.isEmpty()) return;
		print("@SymbolID(", ++new_id, ')');
		print("public void $open(Object in, int line) {$open(in, line, ", new_id, ");}");
		if (!table.isEmpty()) {
			print("private static final java.util.regex.Pattern[] $CHAR = new java.util.regex.Pattern[]{");
			indent(() -> {
				print(table.stream().map(s -> "java.util.regex.Pattern.compile(\"" + s + "\")").collect(Collectors.joining(",\n" + indent)));
			}, "};");
		}
		dedent('}');
		System.out.flush();
	}
}
