# Reference

## Grammar elements
 * Terminal elements
  * __"_string_"__ - basic string  
  _e.g._ __`"if" expression "then" statement`__
  * __[_pattern_]__ - character pattern  
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
 * Postfix modifier
  * ___element_ ?__ - optional
  * __( _group_ )?__ - optional for group
  * ___element_ \*__ - zero or more
  * __( _group_ )\*__ - zero or more for group
  * ___element_ +__ - one or more
  * __( _group_ )+__ - one or more for group
 * Prefix modifier
  * __& _element___ - and predicate
  * __& ( _group_ )__ - and predicate for group
  * __! _element___ - not predicate
  * __! ( _group_ )__ - not predicate for group
 * Action elements
  * ___image_ = < _elements_ >__ - retrieve image string
  * __: _JavaCode___ - embedded action code

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
  * _Element_\* ( '__`/`__' _Element_\* )\*
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
