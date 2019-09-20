package com.gkozlenko.math;

import java.util.*;

/**
 * Expression Tokenizer
 *
 * Break up the expression string into valuable tokens
 */
@SuppressWarnings("WeakerAccess")
public final class ExpressionTokenizer {

    private static final int GROUP_NONE = 0;
    private static final int GROUP_SPACE = 1;
    private static final int GROUP_OPERATOR = 2;
    private static final int GROUP_VALUE = 3;

    private ExpressionTokenizer() {
        // Disable default constructor
    }

    public static LinkedList<Token> tokenize(String expression) throws UnknownTokenException {
        LinkedList<Token> tokens = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        int spaces = 0;
        int lastGroup = GROUP_NONE;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            int group = getGroup(c);

            if (group != lastGroup) {
                if (lastGroup == GROUP_SPACE) {
                    tokens.add(new SpaceToken(i - spaces));
                    spaces = 0;
                } else if (lastGroup == GROUP_VALUE) {
                    tokens.add(createValueToken(i - sb.length(), sb.toString()));
                    sb.setLength(0);
                }
            }

            if (group == GROUP_SPACE) {
                spaces++;
            } else if (group == GROUP_VALUE) {
                sb.append(c);
            } else if (group == GROUP_OPERATOR) {
                tokens.add(createOperatorToken(i, c));
            }
            lastGroup = group;
        }

        if (spaces > 0) {
            tokens.add(new SpaceToken(expression.length() - spaces));
        } else if (sb.length() > 0) {
            tokens.add(createValueToken(expression.length() - sb.length(), sb.toString()));
        }

        // Make negative values
        ListIterator<Token> iter = tokens.listIterator();
        Token lastToken = null;
        while (iter.hasNext()) {
            Token token = iter.next();
            if (lastToken instanceof SubtractionOperatorToken && token instanceof NumberToken) {
                // Remove value token and subtraction operation
                iter.remove();
                iter.previous();
                iter.remove();
                // Create new token with negative value
                iter.add(createValueToken(lastToken.getPosition(), '-' + token.getToken()));
            }
            lastToken = token;
        }

        // Remove space tokens
        iter = tokens.listIterator();
        while (iter.hasNext()) {
            Token token = iter.next();
            if (token instanceof SpaceToken) {
                iter.remove();
            }
        }

        return tokens;
    }

    private static int getGroup(char c) {
        if (Character.isWhitespace(c)) {
            return GROUP_SPACE;
        } else if (c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/') {
            return GROUP_OPERATOR;
        } else {
            return GROUP_VALUE;
        }
    }

    private static Token createOperatorToken(int position, char c) {
        switch (c) {
            case '(': {
                return new OpenGroupToken(position);
            }
            case ')': {
                return new CloseGroupToken(position);
            }
            case '+': {
                return new AdditionOperatorToken(position);
            }
            case '-': {
                return new SubtractionOperatorToken(position);
            }
            case '*': {
                return new MultiplicationOperatorToken(position);
            }
            case '/': {
                return new DivisionOperatorToken(position);
            }
            default: {
                throw new RuntimeException("Unknown operator: " + c);
            }
        }
    }

    private static Token createValueToken(int position, String value) throws UnknownTokenException {
        if (value.charAt(0) == ':') {
            return new ParameterToken(position, value);
        } else {
            try {
                Number number;
                if (value.contains(".")) {
                    number = Double.valueOf(value);
                } else {
                    number = Long.valueOf(value);
                }
                return new NumberToken(position, number);
            } catch (NumberFormatException ex) {
                throw new UnknownTokenException(position, value);
            }
        }
    }

}
