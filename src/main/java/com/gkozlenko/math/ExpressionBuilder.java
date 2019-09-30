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
        // Copy tokens in order not to change them
        LinkedList<Token> copiedTokens = new LinkedList<>(tokens);

        // Remove wrapping bracket
        removeBracketsWrappingExpression(copiedTokens);

        // Corner cases
        if (copiedTokens.isEmpty()) {
            return null;
        } else if (copiedTokens.size() == 1) {
            return new Node(copiedTokens.getFirst());
        }

        // Find the low priority operator
        OperatorToken operator = getRightmostLowestPriorityOperator(copiedTokens);
        if (operator == null) {
            throw new InvalidExpressionException();
        }

        // Divide expression into two parts
        LinkedList<Token> leftTokens = new LinkedList<>();
        LinkedList<Token> rightTokens = new LinkedList<>();
        LinkedList<Token> otherTokens = leftTokens;
        for (Token token : copiedTokens) {
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
     * Remove bracket wrapping the expression
     */
    private static void removeBracketsWrappingExpression(LinkedList<Token> tokens) {
        if (tokens.size() < 3) {
            return;
        }

        if (!(tokens.getFirst() instanceof OpenGroupToken) || !(tokens.getLast() instanceof CloseGroupToken)) {
            return;
        }

        LinkedList<Token> brackets = new LinkedList<>();
        for (Token token : tokens) {
            if (token instanceof OpenGroupToken) {
                brackets.add(token);
            } else if (token != tokens.getLast() && token instanceof CloseGroupToken) {
                brackets.removeLast();
            }
        }

        if (!brackets.isEmpty() && brackets.getFirst() == tokens.getFirst()) {
            // Remove brackets
            tokens.removeFirst();
            tokens.removeLast();

            // Try to remove wrapping brackets once again
            removeBracketsWrappingExpression(tokens);
        }
    }

    /**
     * Get the rightmost low priority operator on the same level in the expression
     */
    private static OperatorToken getRightmostLowestPriorityOperator(LinkedList<Token> tokens) {
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
                    if (operator == null || tmp.getPriority() >= operator.getPriority()) {
                        operator = tmp;
                    }
                }
            }
        }

        return operator;
    }

}
