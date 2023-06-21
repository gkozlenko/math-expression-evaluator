package com.gkozlenko.math;

import java.util.Map;

final class Node {

    private Node leftChild;

    private Node rightChild;

    private final Token token;

    Node(Token token) {
        this.token = token;
    }

    Node getLeftChild() {
        return leftChild;
    }

    void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    Node getRightChild() {
        return rightChild;
    }

    void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    Token getToken() {
        return token;
    }

    Number calculate(Map<String, Number> parameters) throws UndefinedParameterException {
        if (token instanceof OperatorToken) {
            return ((OperatorToken) token).calculate(leftChild.calculate(parameters), rightChild.calculate(parameters));
        } else if (token instanceof NumberToken) {
            return ((NumberToken) token).getNumber();
        } else if (token instanceof ParameterToken) {
            if (parameters.containsKey(token.getToken())) {
                return parameters.get(token.getToken());
            } else {
                throw new UndefinedParameterException((ParameterToken) token);
            }
        }

        return null;
    }

}
