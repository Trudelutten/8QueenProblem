package solver;

/** Solves the 8 queen problem.
 * @author Jonathan Stang
 * @author Trude Hjelmeland
*/
public class EightQueenSolver {
    private int[][] queens = new int[][]{
    { 0, 0 },
    { 0, 0 },
    { 0, 0 },
    { 0, 0 },
    { 0, 0 },
    { 0, 0 },
    { 0, 0 },
    { 0, 0 }};
    
    // Rows and columns that have queens
    boolean[] takenRows = new boolean[] {false, false, false, false, false, false, false, false};
    boolean[] takenColumns = new boolean[] {false, false, false, false, false, false, false, false};

    /**
     * 
     * @param initialQueen int Array[2] where coordinates starts at (0,0) in top left corner of chess board.
     */
    public EightQueenSolver( int[] initialQueen) {
        queens[0] = initialQueen;

        // First queen takes its place
        takenRows[ queens[0][0] ] = true;
        takenColumns[ queens[0][1] ] = true;
    }

    /**
     * Initialize the solving process. 
     * Result is one solution.
     */
    public void solve() {
        placedAQueen(1);
    }

/**
 * A recursive method that tries to place each next queen on a position  
 * that does not conflict with previously placed queens.
 * If the last queen has founds a place, we have our solution, and every recursively 
 * called methods returns true back to the original. 
 * The queen positions are stored externally in the variable queens[][];
 * @param ithQueen
 * @return true whe a queen has found a place on the board
 */
    private boolean placedAQueen(int ithQueen) {
        boolean ithQueenPlaced = false;
        boolean allGood = false;

        // FIND EMPTY ROW
        int i = 0; 
        while (takenRows[i] == true){
            i++;
            if (i>7) {
                return false;
            }
        } 

        // Work through each column:
        int j = 0;

        while(!allGood) {
            
            // FIND EMPTY COLUMN
            while (takenColumns[j] == true){
                j++;
                if (j>7) { // Failed to find any spots to put queen
                    return false;
                }
            } 


            // CHECK DIAGONAL ATTACKS
            if(! onAnyDiagonals(ithQueen, i, j) ) {
                queens[ithQueen][0] = i;
                queens[ithQueen][1] = j;
                ithQueenPlaced = true;
                takenRows[i] = true;
                takenColumns[j] = true;
            } else {
                j++;
                if (j>7) { // Failed to find any spots to put queen
                    return false;
                }
            }
            
            
            // RECURSIVELY PLACE NEXT QUEENS
            if(ithQueenPlaced == true && ithQueen == 7) {
                allGood = true;
            }
            
            // IF QUEEN IS PLACED --> RECURSION
            if (ithQueenPlaced == true && ithQueen < 7){  // Queen placed, but not last queen
                // Recursion happens here:
                boolean returnSuccess = placedAQueen(ithQueen+1); // Returns true when all later queens are placed
                if (!returnSuccess) { // If later queen failed to find a place, this queen tries to find a new place
                    ithQueenPlaced = false;
                    takenColumns[j] = false;
                    takenRows[i] = false;
                    j++;
                    if (j>7) { // Failed to find any spots to put queen
                        return false;
                    }
                } else { // Last queen is placed
                    allGood = true;
                }
            }
        }

        return true;
    }

    /**
     * Checks whether the ithQueen's possible position is on the diagonal of any other queen
     * @param ithQueen
     * @param row // ithQueen's row positon
     * @param column // ithQueen's column position
     * @return true if any queen is on the diagonal of the ithQueen
     */
    public boolean onAnyDiagonals(int ithQueen, int row, int column) {
        for (int i = 0; i < ithQueen; i++) {
            if (onDiagonal(new int[]{row,column}, queens[i])) {
                return true;
            }
        }
        return false;
    }


    /**
     * Takes positions of two queens and checks if they are on each others diagonal.
     * @param place1 int Array[2] with coordinates of queen1
     * @param place2  
     * @return true if on diagonal
     */
    public boolean onDiagonal(int[] place1, int[] place2) {
        boolean diagonalCheck;
        int deltaRow = Math.abs(place1[0] - place2[0]);
        int deltaCol = Math.abs(place1[1] - place2[1]);
        if (deltaRow == deltaCol) {
            diagonalCheck = true; //Diagonal occupied 
        } else {
            diagonalCheck = false; //Diagonal free
        }

        return diagonalCheck;
    }

    public void showBoard() {
        char[][] board = new char[8][8];

        //Initalize an empty board
        for (int i= 0; i < 8; i++) {
            char[] line = {'.','.','.','.','.','.','.','.'};
            board[i] = line;
        }

        for (int[] queen: queens) {
            board[ queen[0] ][ queen[1] ] = 'Q';
        }

        // PRINT TO TERMINAL
        System.out.println("  A B C D E F G H");
        System.out.println("  ---------------");

        int i = 8;
        for (char[] line: board) {
            System.out.print(i+ "|");
            for (char element: line) {
                System.out.print(element+ " ");
            }
            System.out.print("|" + i);

            i--;
            System.out.println();
        }
        System.out.println("  ---------------");
        System.out.println("  A B C D E F G H");

    }
}