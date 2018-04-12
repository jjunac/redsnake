# The Redsnake Language Specification
### Redsnake 1.0

## Table of Contents

- [The Redsnake Language Specification](#the-redsnake-language-specification)
        - [Redsnake 1.0](#redsnake-10)
    - [Table of Contents](#table-of-contents)
    - [Grammar and Lexical structure](#grammar-and-lexical-structure)
    - [Types and Variables](#types-and-variables)
        - [Kinds of Types and operations](#kinds-of-types-and-operations)
            - [Common operations](#common-operations)
            - [Number Types](#number-types)
                - [Integer Type](#integer-type)
                - [Floating-Point Type](#floating-point-type)
                - [Boolean type](#boolean-type)
            - [String type](#string-type)
            - [List type](#list-type)
        - [Type Variables](#type-variables)
            - [Implicit Conversions](#implicit-conversions)
            - [Built-in Implicit Conversion Rules](#built-in-implicit-conversion-rules)
            - [Defining an Implicit Conversion Rule](#defining-an-implicit-conversion-rule)
    - [Expressions](#expressions)
        - [Common operations](#common-operations)
        - [From-string operation](#from-string-operation)
    - [Statements](#statements)
        - [If Statement](#if-statement)
        - [While statement](#while-statement)
        - [For statement](#for-statement)
        - [Function statement](#function-statement)
    - [Operator precedence](#operator-precedence)

## Grammar and Lexical structure

For more information on grammar and lexical structure, please visit our [GitHub](https://github.com/Taken0711/redsnake) where the source code and some examples are hosted.

## Types and Variables

Redsnake is a strongly typed language but you don't declared variable type. Type are inferred statically by the interpreter, which provide a light synthax.

### Kinds of Types and operations

For now, there are only a limited of types in Redsnake. Operations can be applied only on specific type. For instance, in the expression `x + y` where `x` is an Integer, `y` must be an Integer to, or a type that can be converted implicitally into an Integer.

#### Common operations

| Operation | Result                         |
| :-------: | ------------------------------ |
| `x == y`  | Test the equality of x and y   |
| `x != y`  | Test the inequality of x and y |

#### Number Types

All the numbers types are compatibles, and support the following operations, sorted by ascending priority.

| Operation | Result                                         |
| :-------: | ---------------------------------------------- |
| `x < y`   | Compare the value of x and y                   |
| `x > y`   | Compare the value of x and y                   |
| `x <= y`  | Compare the value of x and y                   |
| `x >= y`  | Compare the value of x and y                   |
| `x + y`   | Sum of x and y                                 |
| `x - y`   | Difference of x and y                          |
| `x * y`   | Product of x and y                             |
| `x / y`   | Quotient of x and y                            |
| `x % y`   | Remainder of the Euclidean division of x and y |
| `-x`      | x negated                                      |
| `x ** y`  | x to the power y                               |


##### Integer Type

Mathematical set of integers which value is coded on 32-bits. The value is in the range [ -2147483648, 2147483647].

##### Floating-Point Type

Single precision floating-type, coded on 32-bit using IEEE 754 format. For more information, please visit [Wikipedia](https://en.wikipedia.org/wiki/Single-precision_floating-point_format).

##### Boolean type

Represents the logical values `true` and `false`. Behave like the values 0 and 1 respectively.
The boolean operations are:

| Operation                     | Result                      |
| :---------------------------: | --------------------------- |
| `x && y`                      | Conditionnal-and of x and y |
| <code>x &#124;&#124; y</code> | Conditionnal-or of x and y  |
| `!x`                          | Logical complement of x     |

#### String type

Sequence of value that represent Unicode characters.
The string operations are:

| Operation | Result                                             |
| :-------: | -------------------------------------------------- |
| `x + y`   | Concatenation of x and y                           |
| `x * y`   | Repeats y times the string x. y must be an integer |

#### List type

A list of items. The items can be of different types. List can lso be used as queue and stack. 
The list operations are:

| Operation | Result                                           |
| :-------: | ------------------------------------------------ |
| `x + y`   | Concatenation of x and y                         |
| `x - y`   | Remove of x all the items that also appears in y |
| `x * y`   | Repeats y times the list x. y must be an integer |

### Type Variables

Types are not specified by the user, the interpreter infer them statically. But the interpreter can also do implicit conversion.

#### Implicit Conversions

Redsnake works with a register of implicit conversion. If an operation isn't possible, the interpreter try to find a rule to convert one of the type into a type compatible with the operation.

Here is a use case of the conversion system:

```
print 1 + ”1”
```

 * The system doesn't know the operation `int + string`. It resolves the possible operations:
     * The system knows how to do `int + int`
     * The system knows how to do `int + string`
 * Then the system try to convert the variables:
     * No rule for `string` to `int` conversion
     * There is a rule to convert from `int` to `string`
 * The system apply the conversion on `1` and execute the operation `"1" + "1"`

_NB: The operation `print "1" + 1` would have given the same result._

If there isn't a match at one of the steps, an error is thrown.

During the research for the possible conversion between a built-in rule and a user-defined rule, the user-defined one is prioritary. If there is still an ambiguity after that, an error is thrown.

#### Built-in Implicit Conversion Rules

| From       | To        | Description                                                 |
| :--------: | :-------: | ----------------------------------------------------------- |
| Any number | `boolean` | The value of the test `x != 0`                              |
| `int`      | `string`  | String representation of the integer                        |
| `float`    | `string`  | String representation of the floating-point                 |
| `boolean`  | `int`     | 1 if the boolean is `true`, 0 otherwise                     |
| `boolean`  | `string`  | `"true"` or `"false"` according to the value of the boolean |
| `list`     | `string`  | String representation of the list                           |

#### Defining an Implicit Conversion Rule

_TODO_

## Expressions

### Common operations

Common operations, as `+`, `*`, _etc_ are decribed in the [type specific section](#kinds-of-types-and-operations)

### From-string operation

What makes Redsnake singular is its light syntax and very implicit typing. In this way, Redsnake offers a from string operator `$`. That operator try to parse a string into a type, according to the possbiles from-string rules.

For instance, in the code below:

```
print 1 + $"1"
```

 * The system resolves all the from-string rules and select the suitable one. If there is 2 rules matching, an error is thrown.
 * Here the from-string-to-integer is matching, so the `"1"` is converted to and integer `1`
 * The operation `int + int` can be done

NB: If after the from-string operation the types aren't matching, the system try to do an implicit conversion. For more information see [Implicit Conversions](#implicit-conversions)

## Statements

### If Statement

Execute one of the block according to the value on the condition. The value is evaluated as a `boolean`, so [Implicit Conversions](#implicit-conversions) apply.

### While statement

Repeats the statement list until the expression is false. As the [If Statement](#if-statement), the condition is a `boolean` and [Implicit Conversions](#implicit-conversions) also apply.

### For statement

_TODO_

### Function statement

_TODO_

## Operator precedence

| Operator                  | Description                             |
| :-----------------------: | --------------------------------------- |
| `if`, `else`              | Conditional expression                  |
| `=`                       | Assignment                              |
| <code>&#124;&#124;</code> | Boolean OR                              |
| `&&`                      | Boolean AND                             |
| `<`, `>`, `<=`, `<=`      | Comparisons                             |
| `==`, `!=`                | Tests                                   |
| `+`, `-`                  | Addition and susbtraction               |
| `*`, `/`, `%`             | Multiplication, division and remainder  |
| `-`     , `$`             | Negative                and from-string |
| `**`                      | Exponentiation                          |
| `x[index]`, `x(args...)`  | Subscription and call                   |
| `[expressions...]`        | List instantiation                      |
