package com.example;

public class BinaryTree {
    private class Node {
        public Node child1;
        public Node child2;
        public Node parent;
        public int data;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node parent) {
            this.data = data;
            this.parent = parent;
        }

        public Node() {
        }

        public Node getChild1() {
            return child1;
        }

        public void setChild1(Node child1) {
            this.child1 = child1;
        }

        public Node getChild2() {
            return child2;
        }

        public void setChild2(Node child2) {
            this.child2 = child2;
        }

        public int getData() {
            return data;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    public Node root;
    public int length;

    public BinaryTree(int root) {
        this.root = new Node(root, null);
        this.length = 1;
    }

    public Node getRoot() {
        return root;
    }

    private void setRoot(Node root) {
        this.root = root;
    }

    public int getLength() {
        return length;
    }

    private void setLength(int length) {
        this.length = length;
    }

    public void insert(int data) {
        insert(data, null, this.getRoot());
    }

    private void insert(int data, Node prev, Node iter) {
        if (iter == null) {
            iter = new Node(data, prev);
            if (prev.getData() < iter.getData()) {
                prev.setChild2(iter);
            } else {
                prev.setChild1(iter);
            }
            this.setLength(this.getLength() + 1);
        } else if (iter.getData() == data) {
            throw new IllegalArgumentException("Such element has already existed");
        } else if (iter.getData() > data) {
            insert(data, iter, iter.getChild1());
        } else {
            insert(data, iter, iter.getChild2());
        }
    }

    public void delete(int data) {
        delete(data, this.getRoot());
    }

    private void delete(int data, Node iter) {
        if (iter != null) {
            if (iter.getData() > data) {
                delete(data, iter.getChild1());
            } else if (iter.getData() < data) {
                delete(data, iter.getChild2());
            } else {
                Node curTree;
                if (iter == this.getRoot()) {
                    throw new IllegalArgumentException("You can't delete root!!!");
                }

                else if(iter.getChild1() == null && iter.getChild2() == null && iter.getParent() != null){
                    if(iter.getParent().getData() < data){
                        iter.getParent().setChild2(null);
                    }
                    else if(iter.getParent().getData() > data){
                        iter.getParent().setChild1(null);
                    }
                }

                else if(iter.getChild1() != null && iter.getChild2() == null){
                    iter.getChild1().setParent(iter.getParent());
                    if(iter.getParent().getData() < data){
                        iter.getParent().setChild2(iter.getChild1());
                    }
                    else if(iter.getParent().getData() > data){
                        iter.getParent().setChild1(iter.getChild1());
                    }
                }

                else if(iter.getChild1() == null && iter.getChild2() != null){
                    iter.getChild2().setParent(iter.getParent());
                    if(iter.getParent().getData() < data){
                        iter.getParent().setChild2(iter.getChild2());
                    }
                    else if(iter.getParent().getData() > data){
                        iter.getParent().setChild1(iter.getChild2());
                    }
                }

                else if(iter.getChild1() != null && iter.getChild2() != null){
                    curTree = iter.getChild2();

                    while(curTree.getChild1() != null){
                        curTree = curTree.getChild1();
                    }

                    if(curTree.getParent() == iter){
                        curTree.setChild1(iter.getChild1());
                        iter.getChild1().setParent(curTree);
                        curTree.setParent(iter.getParent());
                        if(iter == iter.getParent().getChild1()){
                            iter.getParent().setChild1(curTree);
                        }
                        else if(iter == iter.getParent().getChild2()){
                            iter.getParent().setChild2(curTree);
                        }
                    }

                    else {
                        if(curTree.getChild2() != null){
                            curTree.getChild2().setParent(curTree.getParent());
                        }
                        curTree.getParent().setChild1(curTree.getChild2());
                        curTree.setChild2(iter.getChild2());
                        curTree.setChild1(iter.getChild1());
                        iter.getChild1().setParent(curTree);
                        iter.getChild2().setParent(curTree);
                        curTree.setParent(iter.getParent());
                        if(iter == iter.getParent().getChild1()){
                            iter.getParent().setChild1(curTree);
                        }
                        else if(iter == iter.getParent().getChild2()){
                            iter.getParent().setChild2(curTree);
                        }
                    }
                }
                this.setLength(this.getLength() - 1);
            }
        } else {
            throw new IllegalArgumentException("There is no such an element");
        }
    }

    public int sum() {
        return sum(this.getRoot());
    }

    private int sum(Node iter) {
        int left, right;
        if (iter != null) {
            left = iter.getChild1() != null ? sum(iter.getChild1()) : 0;
            right = iter.getChild2() != null ? sum(iter.getChild2()) : 0;
            return left + right + iter.getData();
        }
        return 0;
    }

    public int indexAt(int value) throws NullPointerException {
        int count = 0;
        Node iter = this.getRoot();
        while (iter.getData() != value) {
            count++;
            if (iter.getData() > value) {
                iter = iter.getChild1();
            } else if (iter.getData() < value) {
                iter = iter.getChild2();
            }
        }
        return count;
    }
}