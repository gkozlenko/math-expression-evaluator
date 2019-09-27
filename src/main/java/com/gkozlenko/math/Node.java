package com.gkozlenko.math;

class Node {

    private Node leftChild;

    private Node rightChild;

    private Token token;

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

}
