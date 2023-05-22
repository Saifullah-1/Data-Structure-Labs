package com.mycompany.arrayqueue;
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
  public void printQueue();
}

public class ArrayQueue implements IQueue {

    static int size;
    int front;
    int rear;
    int length;
    Object[] arr;

    public ArrayQueue(int size) {
        this.front = 0;
        this.rear = size - 1;
        this.length = 0;
        this.arr = new Object[size];
    }
    
    
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
        size++;
        rear = (rear + 1) % size;
        arr[rear] = item;
        length++;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) 
            throw new RuntimeException();
        front++;
        length--;
        return arr[front];
    }

    @Override
    public boolean isEmpty() {return length == 0;}

    @Override
    public int size() {return length;}
    
    @Override
    public void printQueue(){
        if(length == 0){
            System.out.print("[]");
            return;
        }
        System.out.print("[");
        for (int k = rear; k >= front; --k) {            
            System.out.print(arr[k]);
            if (k > front) 
                System.out.print(", ");
        }
        System.out.print("]");
    }
    
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().replaceAll("\\[|\\]", "");
        String[] s = str.split(", ");
        int[] array = new int[s.length];
        IQueue queue;
        if(s.length == 1 && s[0].isEmpty()){
            queue = new ArrayQueue(1);
            array = new int[]{};
        }else{
            queue = new ArrayQueue(s.length + 1);
            for (int i = s.length - 1; i >= 0; i--) {
                array[i] = Integer.parseInt(s[i]);
                queue.enqueue(array[i]);
            }
        }
        String op = scanner.next();
        
        switch (op) {
            case "enqueue":
                int x = scanner.nextInt();
                queue.enqueue(x);
                queue.printQueue();
                break;
                
            case "dequeue":
                queue.dequeue();
                queue.printQueue();
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