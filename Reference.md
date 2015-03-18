# JavaPEG Reference

## Grammar elements
_notes:_  
Symbol name can include hyphens (`-`) which are replaced by underlines (`_`) in the output code.  
Symbol name starts with dollar (`$`) means it's start symbol and never called in the grammar. This specification is to reduce the output code and the cache memory for packrat parsing.
 * Terminal elements
  * __"_string_"__ - basic string  
  _e.g._ __`"if" expression "then" statement`__
  * __[_pattern_]__ - character pattern, actually regular expression in Java  
  _e.g._ __`[A-Za-z_] [0-9A-Za-z_]*`__
  * __.__ (dot) - any character, not EOF  
  _e.g._ __`"//" ( !"\n" . )*`__
 * Nonterminal elements
  * ___symbol___ - basic symbol
  * ___value_ = _symbol___ - retrieve result
  * ___symbol_(_parameter_)__ - call with parameter (no space between symbol and left parenthesis)
  * ___value_ = _symbol_(_parameter)___ - call with parameter retrieving result
  * __~_symbol___ - skip symbol ignoring inner action
 * Grouping
  * __( _elements_ )__ - sequence
  * __( _elements_ / _elements_ ... )__ - ordered choice
 * Postfix modifiers
  * ___element_ ?__ - optional
  * __( _elements_ )?__ - optional for sequence
  * ___element_ \*__ - zero or more
  * __( _elements_ )\*__ - zero or more for sequence
  * ___element_ +__ - one or more
  * __( _elements_ )+__ - one or more for sequence
 * Prefix modifiers
  * __& _element___ - and predicate
  * __& ( _elements_ )__ - and predicate for sequence
  * __! _element___ - not predicate
  * __! ( _elements_ )__ - not predicate for sequence
 * Action elements
  * ___image_ = < _elements_ >__ - retrieve image string  
  _note:_ __`image = < a / b >`__ occurs error, __`image = < ( a / b ) >`__ is right.
  * __: *java_code*__ - embedded action code
 * Comment
  * __// *comment*__ - comment

## Grammar structure
 * _Grammar_ ::=
  * ( _Header_ / _Code_ )\*
  * _SymbolDefinition_\*
 * _Header_ ::=
  * _Command_ '__`=`__' ( _StringParameter_ / _IntegerParameter_ )\* '__`;`__'
 * _Code_ ::=
  * '__`:`__' _ALineOfJavaCode_
 * _SymbolDefinition_ ::=
  * '__`<`__' _Type_? '__`>`__' _SymbolName_ ( '__`(`__' _Parameter_ '__`)`__' )?  
    _note:_ Omitting _Type_ means this symbol has no action inside and output code can be shorten.
  * _Element_\* ( '__`/`__' _Element_\* )\*  
    _note1:_ Block elements parted by choice operators (/) have different variable scope and the variable declaration must be put at each block elements.  
    _note2:_ If the symbol returns a value, a return statement must be put at the end of each block elements.
 * _Element_ ::=
  * ( _VariableName_ '__`=`__' )? _SymbolName_ ( '__`(`__' _Parameter_ '__`)`__' )? ( '__`?`__' / '__`*`__' / '__`+`__' )?
  * / '__`~`__' _SymbolName_ ( '__`?`__' / '__`*`__' / '__`+`__' )?
  * / '__`"`__' _String_ '__`"`__' ( '__`?`__' / '__`*`__' / '__`+`__' )?
  * / '__`[`__' _Pattern_ '__`]`__' ( '__`?`__' / '__`*`__' / '__`+`__' )?
  * / '__`.`__' ( '__`?`__' / '__`*`__' / '__`+`__' )?
  * / _StringVariableName_ '__`=`__' '__`<`__' _Element_\* '__`>`__'
  * / '__`:`__' _ALineOfJavaCode_
  * / '__`(`__' _Element_\* ( '__`/`__' _Element_\* )\* '__`)`__' ( '__`?`__' / '__`*`__' / '__`+`__' )?
  * / ( '__`&`__' / '__`!`__' ) _SymbolName_
  * / ( '__`&`__' / '__`!`__' ) '__`"`__' _String_ '__`"`__'
  * / ( '__`&`__' / '__`!`__' ) '__`[`__' _Pattern_ '__`]`__'
  * / ( '__`&`__' / '__`!`__' ) '__`.`__'
  * / ( '__`&`__' / '__`!`__' ) '__`(`__' _Element_\* ( '__`/`__' _Element_\* )\* '__`)`__'
 * _Comment_ ::=
  * '__`//`__' _ALineOfComment_

## Header commands
_note:_  
Actually the header commands are implemented as methods named '$'+_command_ and invoked by Java reflection.
So, extended generator may implement new header commands likewise.
 * __out__ = "*output_file_name*" ;
  * Redirect result output to a file.
 * __package__ = "*package_name*" ;
  * Set package of output grammar.
 * __import__ = "*import_directive*" ;
  * Create import declaration.
 * __class__ = "*class_name*" ;
  * Set class name of output grammar.
 * __class__ = "*class_name*" "*superclass_name*" ;
  * Set class name and superclass of output grammar.
  * The superclass should be a subclass of [Parser class](https://github.com/TanumaHideki/JavaPEG/blob/master/JavaPEG/src/parser/Parser.java) and may be a helper class or a base grammar.
 * __class__ = "*class_name*" "*superclass_name*" "*interfaces*" ;
  * Set class name, superclass and interfaces of output grammar.
  * If no need for superclass, then can be omitted as empty string "".
