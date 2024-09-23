# Depth-first-search-in-a-labyrinth

The number of islands, n, causes exponential growth, 2^n, in BACKTRACK1. 
To demonstrate this, modify your program to run interactively two cases – both the variant V1 and
V2. Your program should ask, first, which test to run, and, second, which variant to run: either
V1 (marking ‘–1’) or V2 (marking ‘0’). Therefore use a binary flag in your program. Then run
it for V1 and subsequently for V2. Note the difference in traces. Testing V1 and V2
demonstrates your understanding that the number of steps in V2 depends exponentially on the
number of islands.


PART 1. Data

 1.1. Labyrinth
 
 Y, V
 
 ^
 
 7 | 1 1 1 1 1 0 1
 
 6 | 0 0 0 0 0 0 0
 
 5 | 1 1 1 1 0 1 1
 
 4 | 1 0 0 0 0 1 1
 
 3 | 1 0 1 0 1 1 1
 
 2 | 1 0 0 0 1 1 1
 
 1 | 1 1 1 1 1 1 1
 
 -------------------------------> X, U
 
 1 2 3 4 5 6 7
 
1.2. Initial position X=5, Y=4. L=2.

PART 2. Trace

 1) R1. U=4, V=4. Free. L:=L+1=3. LAB[4,4]:=3.
 2) -R1. U=3, V=4. Free. L:=L+1=4. LAB[3,4]:=4.
 3) --R1. U=2, V=4. Free. L:=L+1=5. LAB[2,4]:=5.
 4) ---R1. U=1, V=4. Wall.
 5) ---R2. U=2, V=3. Free. L:=L+1=6. LAB[2,3]:=6.
 6) ----R1. U=1, V=3. Wall.
 7) ----R2. U=2, V=2. Free. L:=L+1=7. LAB[2,2]:=7.
 8) -----R1. U=1, V=2. Wall.
 9) -----R2. U=2, V=1. Wall.
 10) -----R3. U=3, V=2. Free. L:=L+1=8. LAB[3,2]:=8.
 11) ------R1. U=2, V=2. Thread.
 12) ------R2. U=3, V=1. Wall.
 13) ------R3. U=4, V=2. Free. L:=L+1=9. LAB[4,2]:=9.
 14) -------R1. U=3, V=2. Thread.
 15) -------R2. U=4, V=1. Wall.
 16) -------R3. U=5, V=2. Wall.
 17) -------R4. U=4, V=3. Free. L:=L+1=10. LAB[4,3]:=10.
 18) --------R1. U=3, V=3. Wall.
 19) --------R2. U=4, V=2. Thread.
 20) --------R3. U=5, V=3. Wall.
 21) --------R4. U=4, V=4. Thread.
      
 --------Backtrack from X=4, Y=3, L=10. LAB[4,3]:=-1. L:=L-1=9.
     
 -------Backtrack from X=4, Y=2, L=9. LAB[4,2]:=-1. L:=L-1=8.
 
 22) ------R4. U=3, V=3. Wall.
     
 ------Backtrack from X=3, Y=2, L=8. LAB[3,2]:=-1. L:=L-1=7.
 
 23) -----R4. U=2, V=3. Thread.
     
 -----Backtrack from X=2, Y=2, L=7. LAB[2,2]:=-1. L:=L-1=6.
 
 24) ----R3. U=3, V=3. Wall.
     
 25) ----R4. U=2, V=4. Thread.
     
 ----Backtrack from X=2, Y=3, L=6. LAB[2,3]:=-1. L:=L-1=5.
 
 26) ---R3. U=3, V=4. Thread.
 27) ---R4. U=2, V=5. Wall.
     
 ---Backtrack from X=2, Y=4, L=5. LAB[2,4]:=-1. L:=L-1=4.
 
 28) --R2. U=3, V=3. Wall.
 29) --R3. U=4, V=4. Thread.
 30) --R4. U=3, V=5. Wall.
     
 --Backtrack from X=3, Y=4, L=4. LAB[3,4]:=-1. L:=L-1=3.*8
 
 31) -R2. U=4, V=3. Thread.
 32) -R3. U=5, V=4. Thread.
 33) -R4. U=4, V=5. Wall.
     
 -Backtrack from X=4, Y=4, L=3. LAB[4,4]:=-1. L:=L-1=2.
 
 34) R2. U=5, V=3. Wall.
 35) R3. U=6, V=4. Wall.
 36) R4. U=5, V=5. Free. L:=L+1=3. LAB[5,5]:=3.
 37) -R1. U=4, V=5. Wall.
 38) -R2. U=5, V=4. Thread.
 39) -R3. U=6, V=5. Wall.
40) -R4. U=5, V=6. Free. L:=L+1=4. LAB[5,6]:=4.
 41) --R1. U=4, V=6. Free. L:=L+1=5. LAB[4,6]:=5.
 42) ---R1. U=3, V=6. Free. L:=L+1=6. LAB[3,6]:=6.
 43) ----R1. U=2, V=6. Free. L:=L+1=7. LAB[2,6]:=7.
 44) -----R1. U=1, V=6. Free. L:=L+1=8. LAB[1,6]:=8. Terminal.
     
PART 3. Results

 3.1. Path is found.
 
 3.2. Path graphically:
 
 Y, V
 
 7 | 1 1 1 1 1 0 1
 
 6 | 8 7 6 5 4 0 0
 
 5 | 1 1 1 1 3 1 1
 
 4 | 1 -1 -1 -1 2 1 1
 
 3 | 1 -1 1 -1 1 1 1
 
 2 | 1 -1 -1 -1 1 1 1
 
 1 | 1 1 1 1 1 1 1
 
 -------------------------------> X, U
 
 1 2 3 4 5 6 7
 
 3.3. Rules: R4, R4, R1, R1, R1, R1.
 
 3.4. Nodes: [X=5,Y=4], [X=5,Y=5], [X=5,Y=6], [X=4,Y=6], [X=3,Y=6], [X=2,Y=6],
[X=1,Y=6].
