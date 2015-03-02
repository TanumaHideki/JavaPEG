# JavaPEG
PEG scannerless parser generator for Java 8

### What's this?
* A Java parser generator like [JavaCC](https://javacc.java.net/) based on [Parsing Expression Grammar](http://en.wikipedia.org/wiki/Parsing_expression_grammar).
* Outputs pure Java scannerless parser with cache functionality.
* Enables hybrid grammar description by parser class inheritance.
* Eclipse plugin to coloring grammar is available.

### Requirement:
* Parser generator requires Java 8 to execute.
* Output parser class may run on Java 7 unless it includes Java 8 features in action code.

### Unpolished features:
* Not enough grammar checking.
* Left recursion is not checked and may result in stack overflow.
* Inappropriate grammar like `( symbol? )*` is not checked and may result in infinite loop.

### Contents:
* JavaPEGPlugin.zip --- Eclipse plugin  
    _To install: put jar file in 'plugins' folder._
* package parser
 * Generator.java --- Parser generator
 * GeneratorHelper.java --- Helper class for parser generator
 * Parser.java --- Runtime helper class inherited by output parser
 * Generator.peg --- Grammar file to create Generator.java
* package parser.bootstrap
 * LL1Generator.jj --- JavaCC grammar to create Generator.java
* package sample
 * Base.peg --- Simple calculator sample grammar
 * Base.java --- Generated parser
 * Derived.peg --- Variable assignment extension grammar
 * Derived.java --- Generated parser extends Base.java

### How to use:
 * Just invoke main function of Generator.java, then file chooser dialog will appear.
 * Choosing grammar file starts the generation.
 * In Eclipse environment, do refresh (F5) and then you can see the created Java file.
 * When generating derived parser, base parser must be created beforehand.
 * Grammar reference is available [__here__](https://github.com/TanumaHideki/JavaPEG/blob/master/Reference.md).
