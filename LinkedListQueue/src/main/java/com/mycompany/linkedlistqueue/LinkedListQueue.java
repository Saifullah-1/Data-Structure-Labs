package com.mycompany.linkedlistqueue;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IQueue {
  public void enqueue(Object item);
  public Object dequeue();
  public boolean isEmpty();
  public int size();
}

public class LinkedListQueue implements IQueue {

    static Node front = null;
    static Node rear = null;
    static int length = 0;
    
    public static void main(String[] args) {
        try {
            menu();
        } catch (RuntimeException e) {
            System.out.print("Error");
            System.exit(0);
        }
    }

    @Override
    public void enqueue(Object item) {
        Node newNode = new Node();
        newNode.setItem(item);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        }else{
            rear.setNext(newNode);
            rear = newNode;
        }
        length++;
    }

    @Override
    public Object dequeue() {
        if (isEmpty())
            throw new RuntimeException();
        
        Object o = rear.getItem();
        
        if (front == rear){
            rear = null;
            front = null;
        }else
            front = front.getNext();
        length--;
        return o;
    }

    @Override
    public boolean isEmpty() {return front == null;}

    @Override
    public int size() {return length;}
    
    public static void printQueue(){
        if(length == 0){
            System.out.print("[]");
            return;
        }
            
        Node newNode = new Node();
        newNode = front;
        Object[] arr = new Object[LinkedListQueue.length];
        int i = 0;
        
        while (newNode != null) {            
            arr[i++] = newNode.getItem();
            newNode = newNode.getNext();
        }
        
        System.out.print("[");
        for (int k = arr.length - 1; k >= 0; --k) {            
            System.out.print(arr[k]);
            if (k > 0) 
                System.out.print(", ");
        }
        System.out.print("]");
    }
    
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().replaceAll("\\[|\\]", "");
        String[] s = str.split(", ");
        int[] arr = new int[s.length];
        IQueue queue = new LinkedListQueue();
        
        if(s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else{
            for (int i = s.length - 1; i >= 0; i--) {
                arr[i] = Integer.parseInt(s[i]);
                queue.enqueue(arr[i]);
            }
        }
        
        String op = scanner.next();
        
        switch (op) {
            case "enqueue":
                int x = scanner.nextInt();
                queue.enqueue(x);
                printQueue();
                break;
                
            case "dequeue":
                queue.dequeue();
                printQueue();
                break;
                
            case "isEmpty":
                System.out.print(queue.isEmpty() == true ? "True" : "False");
                break;
                
            case "size":
                System.out.print(queue.size());
                break;
                
            default:
                throw new RuntimeException();
        }
    }
}

class Node{
    private Object item;
    private Node next;
    public Object getItem(){return this.item;}
    public void setItem(Object newItem){this.item = newItem;}
    public Node getNext(){return this.next;}
    public void setNext(Node newNext){this.next = newNext;}
}