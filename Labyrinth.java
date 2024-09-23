import java.util.*;
import java.io.*;

public class Labyrinth {
    static int Rows; // Rows
    static int Columns; // Columns
    static int row;
    static int column;
    static int[][] LAB; // Labyrinth.
    static int[] Row = new int[5]; // 4 production – shifts in X and Y.
    static int[] Column = new int[5];
    static int L; // Move’s number. Starts from 2. Visited positions are marked.
    static int TRIAL; // Number of trials. To compare effectiveness.
    static boolean YES;
    static boolean backtrack = false;
    static String Test;

    public static void main(String args[]) throws IOException{
        try {
            Scanner scanner = new Scanner(System.in);

            // Reading M and N from the user
            System.out.println("Enter the number of rows (M):");
            Rows = scanner.nextInt();
            System.out.println("Enter the number of columns (N):");
            Columns = scanner.nextInt();
            System.out.println("Which test would you like to run (V1/V2)?");
            Test = scanner.next();

            // Initializing LAB array based on user input
            LAB = new int[Rows][Columns];

            // Reading data from the txt file.
            Scanner myReader = new Scanner(new FileInputStream(args[0]));
            
            // Storing agent’s position.
            String c = myReader.next();
            String r = myReader.next();
            column = Integer.parseInt(c);
            row = Integer.parseInt(r);
            
            // Storing the labyrinth in LAB[][].
            int size = Rows*Columns;
            int i = 0;
            Stack<Integer> stack = new Stack<>();
            while(i < size) {
                String ITEM = myReader.next();
                int item = Integer.parseInt(ITEM);
                stack.push(item);
                i++;
            }
        
            
            if(stack.size() != size) {
                System.out.println("Error!!!");
            } else {
                int k = Rows - 1;
                while(k >= 0) {
                    int j = Columns - 1;
                    while(j >= 0) {
                        int a = stack.pop();
                        LAB[k][j] = a;
                        j--;
                    }
                    k--;
                }
            }  

            L = 2;
            LAB[row - 1][column - 1] = L;

            // Printing the labyrinth
            System.out.println();
            System.out.println("PART 1. Data");
            System.out.println("  " + "1.1. Labyrinth");
            for (int t = 0; t < Rows; t++) {
                for (int tt = 0; tt < Columns; tt++) {
                    System.out.print("    " + LAB[t][tt] + " ");
                    if (tt == Columns - 1) {
                        System.out.println();
                    }
                }
            }
            System.out.println();
            System.out.println("  1.2. Initial position X=" + row + ", Y=" + column + ", L=" + L);
            System.out.println();
            
            // Forming four production rules.
            Column[1] = -1;
            Row[1] = 0; // Go Left.

            Column[2] = 0;
            Row[2] = 1; // Go Down.

            Column[3] = 1;
            Row[3] = 0; // Go Right. 2

            Column[4] = 0;
            Row[4] = -1; // Go Up.

            // 4. Invoking the TRY procedure.
            YES = false;
            TRIAL = 0;

            System.out.println();
            System.out.println("PART 2. Trace");

            TRY(row - 1, column - 1);


            // 6. Printing result.
            System.out.println();
            System.out.println("Part 3.");

            if (YES) {
                System.out.println();
                System.out.println("  3.1. Path is found");
                System.out.println();
                System.out.println("  3.2. Path graphically :");
                System.out.println();
                System.out.print(" ");
                String nodes = "Nodes: ";
                String [] n =new String[1000];
                int max=0;

                for (int t = 0; t < Rows; t++) {
                    for (int tt = 0; tt < Columns; tt++) {
                        if(LAB[t][tt]<0 || LAB[t][tt]>9)
                            System.out.print(LAB[t][tt] + " ");
                        else{
                            System.out.print(" " + LAB[t][tt] + " ");
                        }
                        if (tt == Columns - 1) {
                            
                            System.out.println();
                            System.out.print(" ");
                        }

                        if (LAB[t][tt]>max){
                            max=LAB[t][tt];
                        }
                    }
                }


                
                int a=0;
                int num=2;
                while (num<=max) {
                    for (int t = 0; t < Rows; t++) {
                        for (int tt = 0; tt < Columns; tt++) {
                            if(LAB[t][tt]==num){
                                num++;
                                n[a] = "[X=" + (tt+1) + ",Y=" + (Rows-t) + "], ";
                                a++;
                                
                            }
                        }
                    }
                }
                

                for (int b = 0; b <a ; b++){
                    nodes+=n[b];
                }

                System.out.println();
                System.out.println("  3.3 " + nodes);


            } else {
                System.out.println("Path does not exist");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
        	System.out.println("An error occurred.");
        	e.printStackTrace();
        }
    }



    static int X, Y;
    static void TRY(int X, int Y) {
        int K = 0, U, V; // The number of a production rule, Agent’s new position
        if (X == 0 || X == Rows - 1 || Y == 0 || Y == Columns - 1)
            YES = true; // TERM(DATA) = true on the border
        else {
            do {
                K++; // Next rule. Loop over production rules
                U = X + Row[K]; V = Y + Column[K]; // Agent’s new position
                if (LAB[U][V] == 0) { // If a cell is free
                    TRIAL++; // Number of trials
                    backtrack=false;

                    System.out.print("  "+ String.format("%04d", TRIAL) + ")");
                    repeat("-", K,backtrack);
                    System.out.print(" " + pav + "R" + (K) + ".");
                    System.out.print(" U = " + (V+1) + ", V = " + (Rows-U) + " L:L+1 =" + (L+1)+ " Free. LAB[" + (V+1) + "," + (Rows-U) + "]:=" + (L+1) + ".");
                    System.out.print("\n");

                    L++; LAB[U][V] = L; // Marking the cell
                    TRY(U, V); // Recursive call
                    if (!YES) { // If failure
                        backtrack=true;
                        
                        if (Test.equals("V1")){
                            LAB[U][V]=-1;
                        }
                        else{
                            LAB[U][V]=0;
                        }

                        L--;
                        repeat("-", K, backtrack);
                        System.out.print("     -"+ pav + "Backtrack from U = " + (V+1) + ", V = " + (Rows-U) + " L:L+1 =" + (L+1) +  "  LAB[" + (V+1) + "," + (Rows-U) + "]:=" + LAB[U][V] + ".");
                        System.out.print("\n");

                    }
                }
                else{
                    backtrack=false;
                    TRIAL++;
                    System.out.print("  "+ String.format("%04d", TRIAL) + ")");
                    if (LAB[U][V]==1){
                        repeat("-", K,backtrack);
                        System.out.print(" " + pav + "R" + (K) + ".");
                        System.out.print(" Line = " + (V+1) + ", Column = " + (Rows-U) + " Wall.");
                        System.out.print("\n");
                    }
                    else if (LAB[U][V]==-1 || LAB [U][V]>1){
                        repeat("-", K,backtrack);
                        System.out.print(" " + pav + "R" + (K) + ".");
                        System.out.print(" Line = " + (V+1) + ", Column = " + (Rows-U) + " Thread.");
                        System.out.print("\n");
                    }

                    
                }
            } while (!YES && K < 4);
        }
    }

    static int pavles=-1;
    static String pav=" ";

    static void repeat(String str, int K, boolean backtrack) {

        StringBuilder sb = new StringBuilder();

        if (K==1 && !backtrack){
            pavles++;
            for (int i = 0; i < pavles; i++) {
                sb.append(str);
            }
        }

        if (backtrack) {
            pavles--;
            for (int i = 0; i < pavles; i++) {
                sb.append(str);
            }
        }
        
        if (K>1 && !backtrack){
            for (int i = 0; i < pavles; i++) {
                sb.append(str);
            }
        }
        pav = sb.toString();
    }
    
    
}