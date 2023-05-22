package com.mycompany.doublelinkedlist;

import java.util.*;
import javax.management.RuntimeErrorException;

interface ILinkedList {
    public void add(int index, int element);
    public void add(int element);
    public int get(int index);
    public void set(int index, int element);
    public void clear();
    public boolean isEmpty();
    public void remove(int index);
    public int size();
    public ILinkedList sublist(int fromIndex, int toIndex);
    public boolean contains(int o);
    public void printList();
}
class Node {
    int item;
    Node next;
    Node prev;
}
public class DoubleLinkedList implements ILinkedList {
    Node Head = null;
    Node Last = null;
    int length = 0;
    @Override
    public void add(int element) {
        Node new_node = new Node();
        new_node.item = element;
        Last = new_node;
        if (Head == null)
            Head = new_node;
        else {
            Node n = Head;
            while (n.next != null)
                n = n.next;
            n.next = new_node;
            Last.prev = n;
        }
        length++;
    }
    @Override
    public void add(int index, int element) throws RuntimeErrorException {
        if (index < 0)
            throw new RuntimeException();
        Node new_node = new Node();
        new_node.item = element;
        if (index == 0) {
            if (Head == null)
                Head = new_node;
            else {
                new_node.next = Head;
                Head.prev = new_node;
                Head = new_node;
            }
        } else {
            Node n = Head;
            int i = 1;
            while (n.next != null && i < index) {
                n = n.next;
                i++;
            }
            if (i < index)
                throw new RuntimeException();
            new_node.next = n.next;
            new_node.prev = n;
            n.next = new_node;
            n.next.prev = new_node;
        }
        length++;
        printList();
    }

    @Override
    public int get(int index) throws RuntimeErrorException {

        Node new_node = Head;
        if (Head != null && index >= 0) {
            int i = 0;
            while (new_node.next != null && i < index) {
                new_node = new_node.next;
                i++;
            }
            if (i < index)
                throw new RuntimeException();
            else
                return new_node.item;
        } else
            throw new RuntimeException();
    }

    @Override
    public void set(int index, int element) throws RuntimeErrorException {
        if (index < 0 || Head == null)
            throw new RuntimeException();
        Node new_node = new Node();
        new_node.item = element;
        if (index == 0) {
            new_node.next = Head.next;
            Head = new_node;
        } else {
            Node n = Head;
            int i = 1;
            while (n.next != null && i < index) {
                n = n.next;
                i++;
            }
            if (i < index || n.next == null)
                throw new RuntimeException();
            new_node.next = n.next.next;
            new_node.prev = n;
            n.next = new_node;

        }
        printList();
    }

    @Override
    public void clear() {
        Head = null;
        Last = null;
        length = 0;
        System.out.print("[]");
    }
    @Override
    public boolean isEmpty() {
        return Head == null;
    }
    @Override
    public void remove(int index) throws RuntimeErrorException {
        if (index < 0)
            throw new RuntimeException();

        Node n = Head;
        if (index == 0) {
            if (Head == null)
                throw new RuntimeException();
            else {
                n = Head.next;
                Head = n;
            }
        } else {
            int i = 1;
            while (n.next != null && i < index) {
                n = n.next;
                i++;
            }
            if (i < index || n.next == null)
                throw new RuntimeException();
            else {
                n.next = n.next.next;
            }
        }
        printList();
        length--;
    }
    @Override
    public int size() {
        Node new_node = Head;
        int count = 0;
        if (Head == null)
            return 0;
        while (new_node.next != null) {
            new_node = new_node.next;
            count++;
        }
        return ++count;
    }
    @Override
    public boolean contains(int o) {
        Node new_node = Head;
        if (new_node == null)
            return false;
        while (new_node != null) {
            if (new_node.item == o) {
                return true;
            } else
                new_node = new_node.next;
        }
        return false;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) throws RuntimeErrorException {
        ILinkedList sub = new DoubleLinkedList();
        Node from = Head;
        Node to = Head;
        if (toIndex < fromIndex || fromIndex < 0 || toIndex < 0 || fromIndex >= length || toIndex >= length)
            throw new RuntimeException();
        else {
            for (int i = 0; i < fromIndex; i++) {
                from = from.next;
                if (from == null)
                    throw new RuntimeException();
            }
            for (int i = 0; i < toIndex; i++) {
                to = to.next;
                if (to == null)
                    throw new RuntimeException();
            }

            while (from != to) {
                sub.add(from.item);
                from = from.next;
            }
            sub.add(from.item);
            return sub;
        }
    }
    @Override
    public void printList() {
        Node new_node = Head;
        System.out.print("[");
        while (new_node != null) {
            System.out.print(new_node.item);
            if (new_node.next != null)
                System.out.print(", ");
            new_node = new_node.next;
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().replaceAll("\\[|\\]", "");
        String[] sin = s.split(", ");
        int[] in = new int[sin.length];
        if (sin.length == 1 && sin[0].isEmpty())
            in = new int[] {};
        else
            for (int i = 0; i < sin.length; ++i)
                in [i] = Integer.parseInt(sin[i]);

        ILinkedList list = new DoubleLinkedList();
        for (int i = 0; i < in.length; i++) {
            list.add(in [i]);
        }
        String op = scanner.next();
        ILinkedList subList = new DoubleLinkedList();
        int item;
        int index;
        try {
            switch (op) {
            case "add":
                item = scanner.nextInt();
                list.add(item);
                list.printList();
                break;
            case "addToIndex":
                index = scanner.nextInt();
                item = scanner.nextInt();
                list.add(index, item);
                break;
            case "get":
                index = scanner.nextInt();
                System.out.println(list.get(index));
                break;
            case "set":
                index = scanner.nextInt();
                item = scanner.nextInt();
                list.set(index, item);
                break;
            case "size":
                System.out.print(list.size());
                break;
            case "clear":
                list.clear();
                break;
            case "remove":
                index = scanner.nextInt();
                list.remove(index);
                break;
            case "isEmpty":
                System.out.print((list.isEmpty() == true) ? "True" : "False");
                break;
            case "contains":
                item = scanner.nextInt();
                System.out.print(list.contains(item) ? "True" : "False");
                break;
            case "sublist":
                int index1 = scanner.nextInt();
                int index2 = scanner.nextInt();
                subList = list.sublist(index1, index2);
                subList.printList();
                break;
            default:
                System.out.print("Error");
                break;
            }
        } catch (RuntimeException e) {
            System.out.println("Error");
        }
    }
}