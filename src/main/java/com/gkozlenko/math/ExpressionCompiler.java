package com.gkozlenko.math;

import java.util.LinkedList;

/**
 * Expression Compiler
 * <p>
 * Compiles a string expression and returns the expression object
 */
@SuppressWarnings("WeakerAccess")
final public class ExpressionCompiler {

    private ExpressionCompiler() {
        // Disable default constructor
    }

    public static Expression compile(String expression) throws UnexpectedTokenException, InvalidExpressionException {
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
        Node root = ExpressionBuilder.build(tokens);

        return new Expression(tokens, root);
    }

}
