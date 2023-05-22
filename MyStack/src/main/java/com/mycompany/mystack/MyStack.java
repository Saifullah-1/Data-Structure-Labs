package com.mycompany.mystack;

import java.util.Scanner;

interface IStack {
  
  /*** Removes the element at the top of stack and returnsthat element.
  * @return top of stack element, or through exception if empty
  */
  
  public Object pop();
  
  /*** Get the element at the top of stack without removing it from stack.
  * @return top of stack element, or through exception if empty
  */
  
  public Object peek();
  
  /*** Pushes an item onto the top of this stack.
  * @param object to insert*
  */
  
  public void push(Object element);
  
  /*** Tests if this stack is empty
  * @return true if stack empty
  */
  public boolean isEmpty();
  
  public int size();
}


public class MyStack implements IStack {
    private Node top;
    private int capacity;
    
    public MyStack() {
        this.top = null;
        this.capacity = 0;
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
    public Object pop() {
        if(isEmpty())
            throw new RuntimeException();
        Node newNode = new Node();
        newNode.setItem(top.getItem());
        top = top.getNext();
        newNode.setNext(null);
        capacity--;
        return newNode.getItem();
    }

    @Override
    public Object peek() {
        if(isEmpty())
            throw new RuntimeException();
        return top.getItem();
    }

    @Override
    public void push(Object element) {
        Node newNode = new Node();
        newNode.setItem(element);
        if (isEmpty()) 
            top = newNode;
        else{
            newNode.setNext(top);
            top = newNode;
        }
        capacity++;
    }

    @Override
    public boolean isEmpty() {return top == null;}

    @Override
    public int size() {return capacity;}
    
    public void printStack(){
        Node n = top;
        System.out.print("[");
        while(n != null){
            System.out.print(n.getItem());
            n = n.getNext();
            if(n != null)
                System.out.print(", ");
        }
        System.out.print("]");
    }
    
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        MyStack stack = new MyStack();
        String[] str = scanner.nextLine().replaceAll("\\[|\\]", "").split(", ");
        if (!str[0].equals("")) {
            for (int i = str.length - 1; i >= 0; --i)
                stack.push(Integer.valueOf(str[i]));
        }
        
        String op = scanner.next();
        
        switch (op) {
            case "push":
                int item = scanner.nextInt();
                stack.push(item);
                stack.printStack();
                break;
                
            case "pop":
                stack.pop();
                stack.printStack();
                break;
                
            case "peek":
                System.out.print(stack.peek());
                break;
                
            case "size":
                System.out.print(stack.size());
                break;
                
            case "isEmpty":
                
                System.out.print((stack.isEmpty() == true) ? "True" : "False");
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