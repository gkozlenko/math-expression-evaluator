package com.gkozlenko.math;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Math Expression
 */
public class Expression {

    private LinkedList<Token> tokens;

    private Node root;

    private Map<String, Number> parameters = new HashMap<>();

    private Expression(LinkedList<Token> tokens, Node root) {
        this.tokens = tokens;
        this.root = root;
    }

    /**
     * Compile the expression
     *
     * @param expression Expression string
     * @return Expression object
     * @throws UnexpectedTokenException
     * @throws InvalidExpressionException
     */
    @SuppressWarnings("WeakerAccess")
    public static Expression compile(String expression) throws UnexpectedTokenException, InvalidExpressionException {
        LinkedList<Token> tokens = ExpressionTokenizer.tokenize(expression);
        ExpressionValidator.validate(tokens);
        Node root = ExpressionBuilder.build(tokens);

        return new Expression(tokens, root);
    }

    /**
     * Calculate the expression
     *
     * @return Expression result
     * @throws UndefinedParameterException
     */
    @SuppressWarnings("WeakerAccess")
    public Number calculate() throws UndefinedParameterException {
        Number result = root.calculate(parameters);
        if (result.longValue() == result.doubleValue()) {
            return result.longValue();
        } else {
            return result.doubleValue();
        }
    }

    /**
     * Set parameter value
     *
     * @param parameter Parameter name
     * @param value     Parameter value
     * @return Expression object
     */
    @SuppressWarnings("WeakerAccess")
    public Expression setParameter(String parameter, Number value) {
        if (parameter.charAt(0) != ':') {
            parameter = ":" + parameter;
        }
        parameters.put(parameter, value);

        return this;
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
