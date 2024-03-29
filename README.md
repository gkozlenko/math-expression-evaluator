# math-expression-evaluator

[![build status](https://github.com/gkozlenko/math-expression-evaluator/actions/workflows/build.yml/badge.svg)](https://github.com/gkozlenko/math-expression-evaluator/actions/workflows/build.yml)
[![test coverage](https://gkozlenko.github.io/math-expression-evaluator/badges/jacoco.svg)](https://github.com/gkozlenko/math-expression-evaluator/actions/workflows/build.yml)
[![license](https://img.shields.io/github/license/gkozlenko/math-expression-evaluator.svg)](https://github.com/gkozlenko/math-expression-evaluator/blob/master/LICENSE)

Parses, compiles and evaluates mathematical expressions

## Examples

```java
// Import class
import com.gkozlenko.math.Expression;

// Compile the expression
Expression expression = Expression.parse("(10 - :x) * (5 + :y)");

// Set parameters
expression.setParameter(":x", 2).setParameter(":y", 3);

// Calculate
System.out.println(expression.calculate()); // Outputs 64

// Set other parameters
expression.setParameter(":x", 4.5).setParameter(":y", -1.5);

// Calculate again
System.out.println(expression.calculate()); // Outputs 19.25
```

## Supported symbols

| Symbol | Explanation | Example |
| :---: | --- | --- |
| **+** | Addition operator | 1 + 2 results 3 |
| **-** | Subtraction operator | 10 - 3 results 7 |
| **\*** | Multiplication operator | 5 * 2 results 10 |
| **/** | Division operator | 6 / 3 results 2 |
| **(** | Opening parenthesis | (5 - 2) * 2 results 6 |
| **)** | Closing parenthesis | (5 - 2) * 2 results 6 |
| **:** | Parameter prefix | :name |
