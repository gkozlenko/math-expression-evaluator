package com.gkozlenko.math;

import java.util.LinkedList;

/**
 * Math Expression
 */
public class Expression {

    private LinkedList<Token> tokens;

    private Node root;

    Expression(LinkedList<Token> tokens, Node root) {
        this.tokens = tokens;
        this.root = root;
    }

    @Override
    public String toString() {
        if (tokens.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            // Remove a space before the close bracket
            if (token instanceof CloseGroupToken && sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
                sb.setLength(sb.length() - 1);
            }

            sb.append(token.getToken());

            // Add a space after some tokens
            if (token instanceof OperatorToken || token instanceof ValueToken || token instanceof CloseGroupToken) {
                sb.append(' ');
            }
        }

        return sb.substring(0, sb.length() - 1);
    }

}
