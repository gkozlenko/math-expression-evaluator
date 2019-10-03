# math-expression-evaluator
Parses, compiles and evaluates mathematical expressions

## Examples

```java
// Import class
import com.gkozlenko.math.Expression;

// Compile the expression
Expression expression = Expression.compile("(10 - :x) * (5 + :y)");

// Set parameters
expression.setParameter(":x", 2).setParameter(":y", 3);

// Calculate
System.out.println(expression.calculate()); // Outputs 64

// Set another parameters
expression.setParameter(":x", 4.5).setParameter(":y", -1.5);

// Calculate again
System.out.println(expression.calculate()); // Outputs 19.25
```

## Supported symbols

| Symbol | Explanation | Example |
| :---: | --- |
| **+** | Addition operator | 1 + 2 results 3 |
| **-** | Subtraction operator | 10 - 3 results 7 |
| **\*** | Multiplication operator | 5 * 2 results 10 |
| **/** | Division operator | 6 / 3 results 2 |
| **(** | Opening parenthesis | (5 - 2) * 2 results 6 |
| **)** | Closing parenthesis | (5 - 2) * 2 results 6 |
| **:** | Parameter prefix | :name |
