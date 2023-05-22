import java.awt.Point;
import java.util.*;


interface IPlayersFinder {

    /**
     * Search for players locations at the given photo
     * @param photo
     *     Two dimension array of photo contents
     *     Will contain between 1 and 50 elements, inclusive
     * @param team
     *     Identifier of the team
     * @param threshold
     *     Minimum area for an element
     *     Will be between 1 and 10000, inclusive
     * @return
     *     Array of players locations of the given team
     */
    public java.awt.Point[] findPlayers(String[] photo, int team, int threshold);
}


public class PlayerFinder implements IPlayersFinder {
    
    static int counter = 0;//counts number of adjacent player from a certain team
    static int x = 0;
    static int y = 0;
    static int org_x = 0;//x-coord of the left edge of the bounding box
    static int org_y = 0;//y-coord of the upper edge of the bounding box
    static int horizontal = 1;//max width
    static int vertical = 1;//max length

    static void BFS(char[][] arr, int i, int j, int team) { // Breadth-first search
        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || arr[i][j] != (char)(team + '0'))
            return;

        if (j > x) {
            x = j;
            horizontal++;
        }
        if (j < org_x) {
            horizontal++;
            org_x = j;
        }
        if (i > y) {
            y = i;
            vertical++;
        }
        if (i < org_y) {
            vertical++;
            org_y = i;
        }
        
        arr[i][j] = '@';
        counter++;
        BFS(arr, i + 1, j, team);//CHECK DOWN
        BFS(arr, i, j + 1, team);//CHECK RIGHT
        BFS(arr, i - 1, j, team);//CHECK UP
        BFS(arr, i, j - 1, team);//CHECKK LEFT
    }

    public java.awt.Point[] findPlayers(String[] photo, int team, int threshold) {
        int index = 0;
        int count = 0;
        
        Point[] coordinates = new Point[50];
        for (int i = 0; i < 50; i++)
            coordinates[i] = new Point(0, 0);

        char[][] arr = new char[photo.length][];
        for (int i = 0; i < photo.length; i++) {
            arr[i] = photo[i].toCharArray();
        }
        
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == (char)(team + '0')) {
                    horizontal = 1;
                    vertical = 1;
                    org_x = j;
                    org_y = i;
                    x = j;
                    y = i;
                    BFS(arr, i, j, team);
                    if (counter * 4 >= threshold) {
                        count++;
                        coordinates[index].x = horizontal + 2 * org_x;//x = width + 2 * j
                        coordinates[index++].y = vertical + 2 * org_y;//y = length + 2 * i
                    }
                    counter = 0;

                }
            }
        }

        Point[] co_center = new Point[count];
        for (int i = 0; i < count; i++)
            co_center[i] = new Point(coordinates[i]);
        arrange(co_center);
        
        return co_center;
    }
//Ordering the Array ascendingly
    static void arrange(Point[] center) {
        int size = center.length;

        for (int i = 0; i < size - 1; i++)
            for (int j = 0; j < size - i - 1; j++)
                if (center[j].x > center[j + 1].x) {

                    Point temp = center[j];
                    center[j] = center[j + 1];
                    center[j + 1] = temp;
                }
        //CHECK IF THERE ARE EQUAL POINTS THEN ARRANGE ACCORDING TO Y-COORD
        for (int i = 0; i < size - 1; i++)
            for (int j = 0; j < size - i - 1; j++)
                if (center[j].x == center[j + 1].x && center[j].y > center[j + 1].y) {
                    Point temp = center[j];
                    center[j] = center[j + 1];
                    center[j + 1] = temp;
                }
    }
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int rows, columns, team, threshold;
        
        String input = sc.nextLine();
        String[] in = input.split(", ");
        rows = Integer.parseInt(in[0]);
        columns = Integer.parseInt(in[1]);
        
        String[] photo = new String[rows];
        for (int i = 0; i < rows; i++)
            photo[i] = sc.next();
        
        team = sc.nextInt();
        threshold = sc.nextInt();
        
        
        IPlayersFinder object = new PlayerFinder();
        Point[] finalArray = object.findPlayers(photo, team, threshold);
        
//Printing arranged Array of centers points
        System.out.print("[");
        for (int i = 0; i < finalArray.length; i++) {
            System.out.printf("(%d, %d)", finalArray[i].x, finalArray[i].y);
            if (i < finalArray.length - 1)
                System.out.print(", ");
        }
        System.out.print("]");
    }
}
