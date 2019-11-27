# JavaPEG
PEG scannerless parser generator for Java 8

### What's this?
* A Java parser generator like [JavaCC](https://javacc.org/) based on [Parsing Expression Grammar](http://en.wikipedia.org/wiki/Parsing_expression_grammar).
* Outputs pure Java scannerless parser with cache functionality.
* Enables hybrid grammar description by parser class inheritance.
* [Eclipse plugin](https://github.com/TanumaHideki/JavaPEG/blob/master/JavaPEG/JavaPEGPlugin.zip) to coloring grammar is available.
* Grammar reference is available [__here__](https://github.com/TanumaHideki/JavaPEG/blob/master/Reference.md).

### Requirements:
* Parser generator requires Java 8 to execute.
* Output parser class may run on Java 7 unless it includes Java 8 features in action code.

### Contents:
* [JavaPEGPlugin.zip](https://github.com/TanumaHideki/JavaPEG/blob/master/JavaPEG/JavaPEGPlugin.zip) --- Eclipse plugin  
    _To install: put jar file in 'plugins' folder._
* package parser
 * [Generator.java](https://github.com/TanumaHideki/JavaPEG/blob/master/JavaPEG/src/parser/Generator.java) --- Parser generator
 * [GeneratorHelper.java](https://github.com/TanumaHideki/JavaPEG/blob/master/JavaPEG/src/parser/GeneratorHelper.java) --- Helper class for parser generator
 * [Parser.java](https://github.com/TanumaHideki/JavaPEG/blob/master/JavaPEG/src/parser/Parser.java) --- Runtime helper class inherited by output parser
 * [Generator.peg](https://github.com/TanumaHideki/JavaPEG/blob/master/JavaPEG/src/parser/Generator.peg) --- Grammar file to create Generator.java
* package parser.bootstrap
 * [LL1Generator.jj](https://github.com/TanumaHideki/JavaPEG/blob/master/JavaPEG/src/parser/bootstrap/LL1Generator.jj) --- JavaCC grammar to create Generator.java
* package sample
 * [Base.peg](https://github.com/TanumaHideki/JavaPEG/blob/master/JavaPEG/src/sample/Base.peg) --- Simple calculator sample grammar
 * [Base.java](https://github.com/TanumaHideki/JavaPEG/blob/master/JavaPEG/src/sample/Base.java) --- Generated parser
 * [Derived.peg](https://github.com/TanumaHideki/JavaPEG/blob/master/JavaPEG/src/sample/Derived.peg) --- Variable assignment extension grammar
 * [Derived.java](https://github.com/TanumaHideki/JavaPEG/blob/master/JavaPEG/src/sample/Derived.java) --- Generated parser extends Base.java

### How to use:
 * Just invoke main function of [Generator.java](https://github.com/TanumaHideki/JavaPEG/blob/master/JavaPEG/src/parser/Generator.java), then file chooser dialog will appear.
 * Choosing grammar file starts the generation.
 * In Eclipse environment, do refresh (F5) and then you can see the created Java file.
 * When generating derived parser (_e.g._ Derived.java), base parser (_e.g._ Base.java) must be created beforehand.

### Unpolished features:
* The generator does not fine check for input grammar.
* Left recursion is not checked and may result in stack overflow.
* Inappropriate grammar like `( symbol? )*` is not checked and may result in infinite loop at runtime.
