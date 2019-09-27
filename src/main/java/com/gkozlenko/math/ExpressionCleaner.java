package com.gkozlenko.math;

import java.util.LinkedList;

/**
 * Expression Cleaner
 * <p>
 * Clean the expression - remove unnecessary brackets
 */
final class ExpressionCleaner {

    static void clean(LinkedList<Token> tokens) {
        // Remove brackets wrapping the whole expression
        boolean removed;
        do {
            removed = removeBracketsWrappingExpression(tokens);
        } while (removed);

        LinkedList<Token> tmp = new LinkedList<>(tokens);
        LinkedList<Token> brackets = new LinkedList<>();
        tokens.clear();

        boolean inBrackets = false;
        int bracketsLevel = 0;
        for (Token token : tmp) {
            if (inBrackets) {
                if (token instanceof OpenGroupToken) {
                    bracketsLevel++;
                } else if (token instanceof CloseGroupToken) {
                    bracketsLevel--;
                }
                if (bracketsLevel == 0) {
                    inBrackets = false;
                    if (brackets.size() == 1) {
                        // Remove brackets around one token, e.g: (10)
                        tokens.removeLast();
                        tokens.addAll(brackets);
                    } else {
                        // Clean brackets recursively
                        clean(brackets);
                        tokens.addAll(brackets);
                        tokens.add(token);
                    }
                } else {
                    brackets.add(token);
                }
            } else {
                tokens.add(token);
                if (token instanceof OpenGroupToken) {
                    inBrackets = true;
                    bracketsLevel = 1;
                    brackets.clear();
                }
            }
        }
    }

    static boolean removeBracketsWrappingExpression(LinkedList<Token> tokens) {
        if (tokens.size() < 3) {
            return false;
        }

        if (!(tokens.getFirst() instanceof OpenGroupToken) || !(tokens.getLast() instanceof CloseGroupToken)) {
            return false;
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
            // Remove squares
            tokens.removeFirst();
            tokens.removeLast();

            return true;
        }

        return false;
    }

}
