package com.gkozlenko.math;

import java.util.LinkedList;

/**
 * Expression Validator
 * <p>
 * Validates the correctness of the token chain
 */
public class ExpressionValidator {

    private ExpressionValidator() {
        // Disable default constructor
    }

    public static void validate(LinkedList<Token> tokens) throws UnexpectedTokenException {
        validateBrackets(tokens);
    }

    private static void validateBrackets(LinkedList<Token> tokens) throws UnexpectedTokenException {
        LinkedList<Token> brackets = new LinkedList<>();
        for (Token token : tokens) {
            if (token instanceof OpenGroupToken) {
                brackets.add(token);
            } else if (token instanceof CloseGroupToken) {
                if (brackets.isEmpty()) {
                    throw new UnexpectedTokenException(token.getPosition(), token.getToken());
                }
                brackets.removeLast();
            }
        }

        if (!brackets.isEmpty()) {
            Token token = brackets.getFirst();
            throw new UnexpectedTokenException(token.getPosition(), token.getToken());
        }
    }

}
