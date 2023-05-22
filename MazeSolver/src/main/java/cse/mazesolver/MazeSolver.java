package cse.mazesolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.management.RuntimeErrorException;
import javax.xml.transform.Source;


interface IMazeSolver{
	/**
	* Read the maze file and solve it using Breadth First Search
	* @param maze maze file
	* @return the coordinates of the found path from point 'S'
	*
	to point 'E' inclusive, or null if no path is found.
	*/
	public int[][] solveBFS(File maze);
	/**
	* Read the maze file and solve it using Depth First Search
	* @param maze maze file
	* @return the coordinates of the found path from point 'S'
	*
	to point 'E' inclusive, or null if no path is found.
	*/
	public int[][] solveDFS(File maze);
}

public class MazeSolver implements IMazeSolver{
	/*
	 * startRow & startCol are the index of the beginning cell 'S'
	 */
	private int startRow, startCol;
	/*
	 * endRow & endCol are the index of the target cell 'E'
	 */
	private int endRow, endCol;
	int[][] Maze;
	private int N, M;
	
	public static void main(String[] args){
			try (Scanner sc = new Scanner(System.in)) {
				File maze = new File("maze.txt");
				MazeSolver obj = new MazeSolver();
				System.out.print("BFS or DFS ? : ");
				
				switch (sc.next()) {
				    case "BFS":
				    	try {
				    		int[][] arr1 = obj.solveBFS(maze);
				    		obj.printPath(arr1);
						} catch (NullPointerException e) {
							System.out.print("No Path Found");
							System.exit(0);
						}
				        break;
				        
				    case "DFS":
				    	try {
				    		int[][] arr2 = obj.solveDFS(maze);
				    		obj.printPath(arr2);
						} catch (NullPointerException e) {
							System.out.print("No Path Found");
							System.exit(0);
						}
				        break;
				        
				    default:
				        System.err.println("ERROR!...INVALID CHOICE...PLEASE TRY AGAIN!!");
				        System.exit(0);
				}
			}catch (RuntimeErrorException e) {
				System.err.print("ERROR");
				System.exit(0);
			}
	}
	
	private void readFile(File maze) {
		Scanner inFile = null;
        try {
            inFile = new Scanner(maze);
            N = inFile.nextInt();
            M = inFile.nextInt();
            if (N == 0 && M == 0) {
				throw new RuntimeErrorException(null);
			}
            Maze = new int[N][M];
            boolean found_S = false;
            boolean found_E = false;
            System.out.println(N+" "+M);
            for (int i = 0; i < N; i++) {
				String str = inFile.next();
				for (int j = 0; j < M; j++) {
					char ch = str.charAt(j);
					switch (ch) {
					case 'S': 
						found_S = true;
						startRow = i;
						startCol = j;
						Maze[i][j] = 0;
						break;
						
					case 'E':
						found_E = true;
						endRow = i;
						endCol = j;
						Maze[i][j] = 0;
						break;
						
					case '.':
						Maze[i][j] = 0;
						break;
						
					default:
					/*
					 *  case '#'
					*/
						Maze[i][j] = 1;
						break;
					}
				}
			}
            if (!found_E || !found_S) {
				throw new RuntimeErrorException(null);
			}
        } catch (FileNotFoundException e) {
            System.out.println("Error! File Not Found...Please Make Sure That Maze File Is In The Project File.");
            System.exit(0);
        }
	}
	
    @Override
	public int[][] solveBFS(File maze) {
            readFile(maze);
            LinkedListQueue queue = new LinkedListQueue();
            boolean[][] isVisited = new boolean[N][M];
            int[][] rows = new int[N][M];
            int[][] colms = new int[N][M];
            /*
             * rows array contains the index of the parent's row.
             * colms array contains the index of the parent's column.
             * RESET rows and columns arrays to -1.
             */
            for(int i = 0; i < N; ++i) {
            	for(int j = 0; j < M; ++j) {
            		rows[i][j] = -1;
            		colms[i][j] = -1; 
            	}
            }
            queue.enqueue(startRow * M + startCol);
            /*
             * node's code = (node's row * number of columns + node's column)
             * This operation gives each node a unique code.
             * We can retrieve its row and column as following :
             * row = node's code / number of columns
             * column = node's code % number of columns
             */
            isVisited[startRow][startCol] = true;
            
            while (!queue.isEmpty()) {
//            	queue.printQueue();System.out.println();
				int Node = queue.dequeue();
				int R = Node / M;
				int C = Node % M;
				//System.out.println(R + " " + C);
				if (R == endRow && C == endCol) {
					return getPath(rows, colms);
				}
				LinkedList sons = getSons(R, C);
				//System.out.println("SONS SIZE >> " + sons.size());
				for(int i = 0; i < sons.size(); ++i) {
					int son = sons.get(i);
					//System.out.println("Son >> " +son);
					int nextRow = son / M;
	                int nextCol = son % M;
	                
	                if (!isVisited[nextRow][nextCol]) {
						queue.enqueue(nextRow * M + nextCol);
						isVisited[nextRow][nextCol] = true;
						rows[nextRow][nextCol] = R;
						colms[nextRow][nextCol] = C;
					}
				}
            }
            return null;
	}
	@Override
	public int[][] solveDFS(File maze) {
		readFile(maze);
        Stack stack = new Stack();
        boolean[][] isVisited = new boolean[N][M];
        int[][] rows = new int[N][M];
        int[][] colms = new int[N][M];
        
        for(int i = 0; i < N; ++i) {
        	for(int j = 0; j < M; ++j) {
        		rows[i][j] = -1;
        		colms[i][j] = -1; 
        	}
        }
        stack.push(startRow * M + startCol);
        isVisited[startRow][startCol] = true;
        
        while (!stack.isEmpty()) {
//        	stack.printStack();System.out.println();
			int Node = stack.peek();
			int R = Node / M;
			int C = Node % M;
			if (R == endRow && C == endCol) {
				return getPath(rows, colms);
			}
			/*
	    	 * UP >> DOWN >> LEFT >> RIGHT
	    	 */
			if (R - 1 >= 0 && R - 1 < N && C >= 0 && C < M && Maze[R - 1][C] != 1 && !isVisited[R - 1][C]) {
				stack.push((R - 1) * M + C);
				isVisited[R - 1][C] = true;
				rows[R - 1][C] = R;
				colms[R - 1][C] = C;
			}else if(R + 1 >= 0 && R + 1 < N && C >= 0 && C < M && Maze[R + 1][C] != 1 && !isVisited[R + 1][C]) {
				stack.push((R + 1) * M + C);
				isVisited[R + 1][C] = true;
				rows[R + 1][C] = R;
				colms[R + 1][C] = C;
			}else if(R >= 0 && R < N && C - 1 >= 0 && C - 1 < M && Maze[R][C - 1] != 1 && !isVisited[R][C - 1]) {
				stack.push(R * M + (C - 1));
				isVisited[R][C - 1] = true;
				rows[R][C - 1] = R;
				colms[R][C - 1] = C;
			}else if(R >= 0 && R < N && C + 1 >= 0 && C + 1 < M && Maze[R][C + 1] != 1 && !isVisited[R][C + 1]) {
				stack.push(R * M + (C + 1));
				isVisited[R][C + 1] = true;
				rows[R][C + 1] = R;
				colms[R][C + 1] = C;
			}else {
				stack.pop();
			}
        }
        return null;
        
	}
    
    private int[][] getPath(int[][] r, int[][] c) {
		int path = 1;
		int R = endRow;
		int C = endCol;
		/*
		 * Calculating Path Length 
		 */
		while (r[R][C] != -1) {
			path++;
			int x1 = r[R][C];
			int x2 = c[R][C];
			R = x1;
			C = x2;
		}
		int[][] Path = new int[path][2];
		R = endRow;
		C = endCol;
		
		for (int i = path - 1; i >= 0; --i) {
			Path[i][0] = R;
			Path[i][1] = C;
			int x1 = r[R][C];
			int x2 = c[R][C];
			R = x1;
			C = x2;
		}
		return Path;
	}
    
    private LinkedList getSons(int r, int c) {
    	/*
    	 * UP >> DOWN >> LEFT >> RIGHT
    	 */
    	LinkedList sons = new LinkedList();
    	if (r - 1 >= 0 && r - 1 < N && c >= 0 && c < M && Maze[r - 1][c] != 1) {
			sons.add((r - 1) * M + c);
		}
    	if (r + 1 >= 0 && r + 1 < N && c >= 0 && c < M && Maze[r + 1][c] != 1) {
			sons.add((r + 1) * M + c);
		}
    	if (r >= 0 && r < N && c - 1 >= 0 && c - 1 < M && Maze[r][c - 1] != 1) {
			sons.add(r * M + (c - 1));
		}
    	if (r >= 0 && r < N && c + 1 >= 0 && c + 1 < M && Maze[r][c + 1] != 1) {
			sons.add(r * M + (c + 1));
		}
    	return sons;
    }
    
    private void printPath(int[][] pathArr) {
    	for (int i = 0; i < pathArr.length; i++) {
			System.out.printf("{%d, %d}", pathArr[i][0], pathArr[i][1]);
			if (i < pathArr.length - 1)
				System.out.print(", ");
		}
    }
}

/*---------------------------------------------------Stack-----------------------------------------------*/
class Node{
    private int item;
    private Node next;
    public int getItem(){return this.item;}
    public void setItem(int newItem){this.item = newItem;}
    public Node getNext(){return this.next;}
    public void setNext(Node newNext){this.next = newNext;}
}

class Stack {
    private Node top;
    private int capacity;
    
    public Stack() {
        this.top = null;
        this.capacity = 0;
    }
    
    public int pop() {
        if(isEmpty())
            throw new RuntimeException();
        Node newNode = new Node();
        newNode.setItem(top.getItem());
        top = top.getNext();
        newNode.setNext(null);
        capacity--;
        return newNode.getItem();
    }

    public int peek() {
        if(isEmpty())
            throw new RuntimeException();
        return top.getItem();
    }

    public void push(int element) {
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

    public boolean isEmpty() {return top == null;}

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
}

/*-----------------------------------------------------Queue----------------------------------------------*/
class LinkedListQueue {
	private static Node front = null;
	private static Node rear = null;
	private static int length = 0;

    public void enqueue(int item) {
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

    public int dequeue() {
        if (isEmpty())
            throw new RuntimeException();
        
        int o = front.getItem();
        
        if (front == rear){
            rear = null;
            front = null;
        }else
            front = front.getNext();
        length--;
        return o;
    }

    public boolean isEmpty() {return front == null;}

    public int size() {return length;}
    
    public void printQueue(){
        if(length == 0){
            System.out.print("[]");
            return;
        }
            
        Node newNode = new Node();
        newNode = front;
        System.out.print("[");
        while (newNode != null) {
        	System.out.print(newNode.getItem());
            newNode = newNode.getNext();
            if (newNode != null) 
				System.out.print(", ");
        }
        System.out.print("]");
    }
}
/*---------------------------------------------------Linked List-------------------------------------------*/
class LinkedList {
	private Node head = null;
	private static int len = 0;

	public void add(int element) {
		Node n = new Node();
		n.setItem(element);
	    n.setNext(null);

	    if (head == null) {
	    	head = n;
		} else {
			Node last = head;
		    while (last.getNext() != null) {
		    	last = last.getNext();
		    }
		    last.setNext(n);
		}
	    len++;
	}

	public void add(int index, int element) {
		if (index >= len || index < 0)
			throw new RuntimeErrorException(null, "Error");
		else {
			Node n = new Node();
		    n.setItem(element);
		    n.setNext(null);
		    Node current = head;
		    if (len == 0)
		    	add(element);
		    else if (index == 0) {
		   		n.setNext(head);
		   		head = n;
		   	} else {
		   		for (int i = 1; i < index; i++) {
		    		current = current.getNext();
		    	}
		    	n.setNext(current.getNext());;
		    	current.setNext(n);
		    }
		   	len++;
		}
	}

	public int get(int index) throws IndexOutOfBoundsException{
		Node current = head;
		if (index >= len || index < 0) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		} else {
		    for (int i = 0; i < index; i++) {
		    	current = current.getNext();
		    }
		}
		return current.getItem();
	}

	public int size() {
		Node ind = head;
		int count = 0;
		while (ind != null) {
			count++;
		    ind = ind.getNext();
		}
		return count;
	}
}