package com.gkozlenko.math;

import java.util.LinkedList;

/**
 * Expression Validator
 * <p>
 * Validates the correctness of the token chain
 */
final class ExpressionValidator {

    private ExpressionValidator() {
        // Disable default constructor
    }

    static void validate(LinkedList<Token> tokens) throws InvalidExpressionException, UnexpectedTokenException {
        if (tokens.isEmpty()) {
            throw new InvalidExpressionException();
        }
        validateBrackets(tokens);
        validateSequence(tokens);
    }

    private static void validateBrackets(LinkedList<Token> tokens) throws UnexpectedTokenException {
        LinkedList<Token> brackets = new LinkedList<>();
        for (Token token : tokens) {
            if (token instanceof OpenGroupToken) {
                brackets.add(token);
            } else if (token instanceof CloseGroupToken) {
                if (brackets.isEmpty()) {
                    throw new UnexpectedTokenException(token);
                }
                brackets.removeLast();
            }
        }

        if (!brackets.isEmpty()) {
            Token token = brackets.getFirst();
            throw new UnexpectedTokenException(token);
        }
    }

    private static void validateSequence(LinkedList<Token> tokens) throws UnexpectedTokenException {
        Token lastToken = null;
        for (Token token : tokens) {
            if (lastToken != null) {
                if (lastToken instanceof OpenGroupToken) {
                    if (!(token instanceof OpenGroupToken) && !(token instanceof ValueToken)) {
                        throw new UnexpectedTokenException(token);
                    }
                } else if (lastToken instanceof CloseGroupToken) {
                    if (!(token instanceof CloseGroupToken) && !(token instanceof OperatorToken)) {
                        throw new UnexpectedTokenException(token);
                    }
                } else if (lastToken instanceof OperatorToken) {
                    if (!(token instanceof OpenGroupToken) && !(token instanceof ValueToken)) {
                        throw new UnexpectedTokenException(token);
                    }
                } else {
                    if (!(token instanceof CloseGroupToken) && !(token instanceof OperatorToken)) {
                        throw new UnexpectedTokenException(token);
                    }
                }
            }
            lastToken = token;
        }

        // Check the last token
        Token token = tokens.getLast();
        if (token instanceof OpenGroupToken || token instanceof OperatorToken) {
            throw new UnexpectedTokenException(token);
        }
    }

}
