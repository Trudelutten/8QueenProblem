package solver;

/**
 * @author Jonathan Stang
 * @author Trude Hjelmeland
 */

public class Main{
    public static void main(String[] args) {
        String input;

        try {
            input = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error1: Need argument as Algebraic Notation: a4 ");
            System.out.println("use letter a to h and number 1 to 8");
            return;
        } catch (Exception e) {
            System.out.println("Error: Weird exception");
            e.printStackTrace(new java.io.PrintStream(System.out));
            return;
        }

        if (input.length() != 2) {
            System.out.println("Error2: Need argument as Algebraic Notation: a4 ");
            System.out.println("use letter a to h and number 1 to 8");
            return;
        } 

        int[] initialQueen = algebraicNotation(input);

        EightQueenSolver solver = new EightQueenSolver(initialQueen);
        
        solver.solve();
        solver.showBoard();
    }

    /**
     * Convert letter in algebraicnotation to correct 
     * rownumber and coloumnumber
     * @param input String containing algebraicnotation, ex. a4 
     * @return int[] with rowNum and colNum, ex. [0, 4]
     */
    public static int[] algebraicNotation(String input) {
        String cord1 = "" + input.charAt(0); // a letter
        String cord2 = "" + input.charAt(1); // a number

        int initialColNumber = -1;
        switch (cord1.toLowerCase()) {
            case "a": initialColNumber = 0;
            break;
            case "b": initialColNumber = 1;
            break;
            case "c": initialColNumber = 2;
            break;
            case "d": initialColNumber = 3;
            break;
            case "e": initialColNumber = 4;
            break;
            case "f": initialColNumber = 5;
            break;
            case "g": initialColNumber = 6;
            break;
            case "h": initialColNumber = 7;
            break;
            default:
            System.out.println("Please enter a co-ordinate following the algebraic notation, like a4 in the range a1 to h8");
            System.exit(-1);
        }

        int initialRowNumber = -1;
        switch (cord2) {
            case "1": initialRowNumber = 7;
            break;
            case "2": initialRowNumber = 6;
            break;
            case "3": initialRowNumber = 5;
            break;
            case "4": initialRowNumber = 4;
            break;
            case "5": initialRowNumber = 3;
            break;
            case "6": initialRowNumber = 2;
            break;
            case "7": initialRowNumber = 1;
            break;
            case "8": initialRowNumber = 0;
            break;
            default: System.out.println("Please enter a co-ordinate following the algebraic notation, like a4 in the range a1 to h8 ");
            System.exit(-1);
        }  
        return new int[]{initialRowNumber, initialColNumber};
    }    
}