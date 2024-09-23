import java.util.*;
import java.io.*;

public class Labyrinth2 {
    static int Rows;
    static int Columns;
    static int row;
    static int column;
    static int[][] LAB; // Labyrinth.
    static int[] Row = new int[5]; // 4 production rules – shifts in X and Y.
    static int[] Column = new int[5];
    static String Test;
    static int L; // Move’s number. Starts from 2.
    static boolean YES;

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
        
            boolean telos = false;
            if(stack.size() != size) {
                System.out.println("Error!!!");
                telos = true;
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

            //Storin the agent's position in LAB[][]
            L = 2;
            LAB[row - 1][column - 1] = L;
            

            // Printing the labyrinth
            System.out.println();
            System.out.println("PART 1. Data");
            System.out.println("  " + "1.1. Labyrinth");
            for (int t = 0; t < Rows; t++) {
                System.out.print("    ");
                for (int tt = 0; tt < Columns; tt++) {
                    
                    System.out.print(LAB[t][tt] + " ");
                    if (tt == Columns - 1) {
                        System.out.println();
                    }
                }
            }

            //Printing initial position
            System.out.println();
            System.out.println("  1.2. Initial position X=" + row + ", Y=" + column + ", L=" + L);
            System.out.println();
            
            // Forming four production rules.
            Column[1] = -1;
            Row[1] = 0; // Go Left.

            Column[2] = 0;
            Row[2] = -1; // Go Down.

            Column[3] = 1;
            Row[3] = 0; // Go Right. 2

            Column[4] = 0;
            Row[4] = 1; // Go Up.

            // 4. Invoking the TRY procedure.
            YES = false;
            

            System.out.println();
            System.out.println("PART 2. Trace");

            TRY(row - 1, column - 1, L);
            

            

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

    
    
    static public void TRY(int row, int column, int L){
        int walls;
        int curi=row;
        int curj=column;
        int new_curi=curi;
        int new_curj=curj;
        int tempcuri1=0;
        int tempcurj1=0;
        int tempcuri2=0;
        int tempcurj2=0;

        int TRIAL=0;
        
        boolean moved=false;
        boolean end=false;
        boolean backtrack=false;
            
        while (end==false){
                
            walls=0;
            tempcuri1=0;
            tempcurj1=0;
            tempcuri2=0;
            tempcurj2=0;
            
            

            // go left
            if (curj!=0 && moved==false) {
                if (LAB[curi][curj-1]==0 || (backtrack==true && LAB[curi][curj-1]>1)){
                    
                    if (backtrack==false){
                        curj=curj-1;
                        L++;
                        LAB[curi][curj]=L;  
                    } 
                    else{
                        curi=new_curi;
                        curj=new_curj;
                        
                    }   
                    moved=true;
                    TRIAL++;
                    System.out.println("  ."+ TRIAL + ")" + "Moved Left to (" + (curj+1) + "," + (Rows-curi) + ")") ;
                    
                }
            }

            if (moved==false){
                TRIAL++;
                System.out.println("  ."+TRIAL + ")" + "Could not move Left to (" + (curj) + "," + (Rows-curi) + ")");
            }


            // go down 
            if (curi!=Rows-1 && moved==false) {
                if (LAB[curi+1][curj]==0 || (backtrack==true && LAB[curi+1][curj]>1)){
                    
                    if (backtrack==false){
                        curi=curi+1;
                        L++;
                        LAB[curi][curj]=L;
                        
                    } 
                    else{
                        curi=new_curi;
                        curj=new_curj;
                    }   
                    moved=true;  
                    TRIAL++;
                    System.out.println("  ."+TRIAL + ")" + "Moved Down to (" + (curj+1) + "," + (Rows-curi) + ")") ;
                    
                }
            }


            if (moved==false){ 
                TRIAL++;
                System.out.println("  ."+TRIAL + ")" + "Could not move Down to (" + (curj+1) + "," + (Rows-1-curi) + ")");
            }


            // go right
            if (curj!=Columns-1 && moved==false) {
                if (LAB[curi][curj+1]==0 || (backtrack==true && LAB[curi][curj+1]>1) ){
                    
                    if (backtrack==false){
                        curj=curj+1;
                        L++;
                        LAB[curi][curj]=L;
                        
                    }
                    else{
                        curi=new_curi;
                        curj=new_curj;
                    }   
                    moved=true;
                    TRIAL++;
                    System.out.println("  ."+TRIAL + ")" + "Moved Right to (" + (curj+1) + "," + (Rows-curi) + ")") ;
                    
                }
            }

            if(moved==false){
                TRIAL++;
                System.out.println("  ."+TRIAL + ")" + "Could not move Right to (" + (curj+2) + "," + (Rows-curi) + ")") ;
            }


            // go up
            if (curi!=0 && moved==false) {
                if (LAB[curi-1][curj]==0 || (backtrack==true && LAB[curi-1][curj]>1)){
                    
                    if (backtrack==false){
                        curi=curi-1;
                        L++;
                        LAB[curi][curj]=L;
                        
                    }
                    else{
                        curi=new_curi;
                        curj=new_curj;
                    }   
                    moved=true;
                    TRIAL++;
                    
                    System.out.println("  ."+TRIAL + ")" + "Moved Up to (" + (curj+1) + "," + (Rows-curi) + ")") ;
                } 

                
            }

            if (moved==false ) {
                TRIAL++;
                System.out.println("  ."+TRIAL + ")" +"Could not move Up to (" + (curj+1) + "," + (Rows-curi+1) + ")");
            }


            
            backtrack=false;
            moved=false;

            // Checking if we are out
            if (curi==0 || curi==Rows-1 || curj==0 ||curj==Columns-1 ){
                end = true;
                YES=true;
                
            }


            //Checking if we are at a deadend
            
            if (curi!=0) {
                if (LAB[curi-1][curj]!=0) {
                    walls++;
                }
            }
            if (curi!=Rows-1) {
                if (LAB[curi+1][curj]!=0){
                    walls++;
                } 
            }
            if (curj!=0) {
                if (LAB[curi][curj-1]!=0){
                    walls++;
                }
            }
            if (curj!=Columns-1) {
                if (LAB[curi][curj+1]!=0){
                    walls++;
                }
            }

                
            //if we are at a deadend :
            
            
            //We mark with "-1"
            if (walls==4){
                backtrack=true;
                if (Test.equals("V1")){
                    LAB[curi][curj]=-1;
                }
                else{
                    LAB[curi][curj]=0;
                }

                //We decrease L by 1
                if (L>2){
                    L--;
                }

                //We search for the previous coordinates in order to  go back
                if (curi!=0 ) {
                    if (LAB[curi-1][curj]>1){
                        tempcuri1=curi-1;
                    } 
    
                }
                if (curi!=Rows-1 ) {
                    if (LAB[curi+1][curj]>1){
                        tempcuri2=curi+1;
                    }
                }

                if (tempcuri1!=0||tempcuri2!=0){
                    if (LAB[tempcuri1][curj] > LAB[tempcuri2][curj]){
                        new_curi= tempcuri1;
                    }
                    else{
                        new_curi= tempcuri2;
                        new_curj=curj;
                    }
                    moved=true;
                }

                if (curj!=0 ) {
                    if (LAB[curi][curj-1]>1){
                        tempcurj1=curj-1;
                    }
                }
                if (curj!=Columns-1) {
                    if (LAB[curi][curj+1]>1){
                        tempcurj2=curj+1;
                    }
                }


                if (tempcurj1!=0||tempcurj2!=0 && moved==false){
                    if (LAB[curi][tempcurj1] > LAB[curi][tempcurj2]){
                        new_curj= tempcurj1;
                    }
                    else{
                        new_curj= tempcurj2;
                        new_curi=curi;
                    }
                }
                

                
            }
            moved=false;
        }
            
        return;
    }	
    
    

	        
}