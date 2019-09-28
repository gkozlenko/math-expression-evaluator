package com.gkozlenko.math;

import java.util.LinkedList;

/**
 * Expression Builder
 * <p>
 * Builds the execution three
 */
final class ExpressionBuilder {

    private ExpressionBuilder() {
        // Disable default constructor
    }

    static Node build(LinkedList<Token> tokens) throws InvalidExpressionException {
        // Remove wrapping brackets
        ExpressionCleaner.removeBracketsWrappingExpression(tokens);

        // Corner cases
        if (tokens.isEmpty()) {
            return null;
        } else if (tokens.size() == 1) {
            return new Node(tokens.getFirst());
        }

        // Find the low priority operator
        OperatorToken operator = getLowestPriorityOperator(tokens);
        if (operator == null) {
            throw new InvalidExpressionException();
        }

        // Divide expression into two parts
        LinkedList<Token> leftTokens = new LinkedList<>();
        LinkedList<Token> rightTokens = new LinkedList<>();
        LinkedList<Token> otherTokens = leftTokens;
        for (Token token : tokens) {
            if (token == operator) {
                otherTokens = rightTokens;
            } else {
                otherTokens.add(token);
            }
        }

        // Build a tree recursively
        Node root = new Node(operator);
        root.setLeftChild(build(leftTokens));
        root.setRightChild(build(rightTokens));

        return root;
    }

    /**
     * Get the low priority operator on the same level in the expression
     */
    private static OperatorToken getLowestPriorityOperator(LinkedList<Token> tokens) {
        OperatorToken operator = null;

        boolean inBrackets = false;
        int bracketsLevel = 0;
        for (Token token : tokens) {
            if (inBrackets) {
                // Skip expression in brackets
                if (token instanceof OpenGroupToken) {
                    bracketsLevel++;
                } else if (token instanceof CloseGroupToken) {
                    bracketsLevel--;
                }
                if (bracketsLevel == 0) {
                    inBrackets = false;
                }
            } else {
                if (token instanceof OpenGroupToken) {
                    inBrackets = true;
                    bracketsLevel = 1;
                } else if (token instanceof OperatorToken) {
                    OperatorToken tmp = (OperatorToken) token;
                    // Compare operators priority
                    if (operator == null || tmp.getPriority() > operator.getPriority()) {
                        operator = tmp;
                    }
                }
            }
        }

        return operator;
    }

}
