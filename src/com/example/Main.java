package com.example;

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(1000);
        try {
            for (int i = 1; i < 2000; i++) {
                if (i == 1000)
                    continue;
                tree.insert(i);
            }

            long startTimeInsert = System.nanoTime();
            for (int i = -1000; i < 0; i++) {
                tree.insert(i);
            }
            for (int i = 2000; i < 3001; i++) {
                tree.insert(i);
            }
            long timeInsert = System.nanoTime() - startTimeInsert;

            long startTimeDelete = System.nanoTime();
            for (int i = -1000; i < 0; i++) {
                tree.delete(i);
            }
            for (int i = 2000; i < 3001; i++) {
                tree.delete(i);
            }
            long timeDelete = System.nanoTime() - startTimeDelete;

            long startTimeSum = System.nanoTime();
            tree.sum();
            long sumTime = System.nanoTime() - startTimeSum;

            long startTimeIndex = System.nanoTime();
            for (int i = 1; i < 2000; i++) {
                tree.indexAt(i);
            }
            long timeIndex = System.nanoTime() - startTimeIndex;

            System.out.println("Binary Tree time: ");
            System.out.println("Insert(2000) time: " + timeInsert + " ns");
            System.out.println("Delete(2000) time: " + timeDelete + " ns");
            System.out.println("Sum time: " + sumTime + " ns");
            System.out.println("Index(2000) time: " + timeIndex + " ns");
        } catch (NullPointerException exception) {
            System.out.println("There is no such a value");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}