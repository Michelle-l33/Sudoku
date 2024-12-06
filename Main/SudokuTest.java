import java.util.List;

public class SudokuTest {
    public static void main(String[] args) {
        // Easy Sudoku Puzzle (Solved: 1 Solution)
        int[][] easyPuzzle = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        // Medium Sudoku Puzzle (Solved: 1 Solution)
        int[][] mediumPuzzle = {
                {0, 0, 0, 0, 0, 0, 0, 5, 0},
                {0, 0, 3, 6, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 4, 5, 7, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8},
                {0, 0, 8, 5, 0, 0, 0, 1, 0},
                {0, 9, 0, 0, 0, 0, 4, 0, 0}
        };

        // Hard Sudoku Puzzle (Solved: 1 Solution)
        int[][] hardPuzzle = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 0, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        int[][] small = {
            {1, 0, 0, 0},
            {0, 0, 2, 0},
            {0, 1, 0, 0},
            {0, 0, 0, 3}
        };


        //IF YOU WANT TO DESTROY YOUR COMPUTER - YOU CAN TRY THIS - JUST UNCOMMENT ALL FOLLOWING LARGE TESTS
        /* int[][] large = {
            {1,  0,  0,  0,  5,  0,  0,  0,  9,  0,  0,  0, 13,  0,  0,  0},
            {0,  0,  3,  0,  0,  0,  7,  0,  0,  0, 11,  0,  0,  0, 15,  0},
            {0,  0,  0,  4,  0,  0,  0,  8,  0,  0,  0, 12,  0,  0,  0,  6},
            {0,  0,  0,  0,  0, 14,  0,  0,  0,  0,  2,  0,  0,  0,  0, 10},
            
            {5,  0,  0,  0,  1,  0,  0,  0,  3,  0,  0,  0,  7,  0,  0,  0},
            {0,  0, 10,  0,  0,  0, 14,  0,  0,  0,  4,  0,  0,  0,  2,  0},
            {0,  0,  0, 13,  0,  0,  0,  2,  0,  0,  0,  5,  0,  0,  0,  8},
            {0,  9,  0,  0,  0,  0, 11,  0,  0,  0, 12,  0,  0,  0, 14,  0},
            
            {7,  0,  0,  0,  3,  0,  0,  0, 15,  0,  0,  0,  1,  0,  0,  0},
            {0,  0,  4,  0,  0,  0,  9,  0,  0,  0,  8,  0,  0,  0,  3,  0},
            {0,  0,  0,  5,  0,  0,  0,  6,  0,  0,  0, 10,  0,  0,  0,  7},
            {0, 14,  0,  0,  0,  0,  1,  0,  0,  0,  9,  0,  0,  0,  4,  0},
            
            {9,  0,  0,  0, 11,  0,  0,  0,  6,  0,  0,  0,  8,  0,  0,  0},
            {0,  0,  7,  0,  0,  0,  5,  0,  0,  0,  3,  0,  0,  0,  1,  0},
            {0,  0,  0,  6,  0,  0,  0, 10,  0,  0,  0,  2,  0,  0,  0, 11},
            {0,  0,  0,  0, 13,  0,  0,  0, 14,  0,  0,  0,  4,  0,  0,  0}
        }; */
        

    
        // Create Solver instances for each puzzle
        Solver easySolver = new Solver(easyPuzzle[0].length, (int) Math.sqrt(easyPuzzle[0].length), easyPuzzle);
        Solver mediumSolver = new Solver(mediumPuzzle[0].length, (int) Math.sqrt(mediumPuzzle[0].length), mediumPuzzle);
        Solver hardSolver = new Solver(hardPuzzle[0].length, (int) Math.sqrt(hardPuzzle[0].length), hardPuzzle);
        Solver smallSolver = new Solver(small[0].length,(int)Math.sqrt(small[0].length),small);
        //Solver largeSolver = new Solver(large[0].length,(int)Math.sqrt(large[0].length),large);


        // Solve the puzzles using DFS
        System.out.println("Testing Easy Sudoku Puzzle:");
        easySolver.build();
        long easyStartdfs = System.nanoTime();
        List<int[][]> easySolutions = easySolver.DFS();
        long easyEnddfs = System.nanoTime();
        long easyTimedfs =easyEnddfs-easyStartdfs;
        printSolutions(easySolutions);
        

        System.out.println("\nTesting Medium Sudoku Puzzle:");
        mediumSolver.build();
        long mediumStartdfs = System.nanoTime();
        List<int[][]> mediumSolutions = mediumSolver.DFS();
        long mediumEnddfs = System.nanoTime();
        long mediumTimedfs = mediumEnddfs-mediumStartdfs;
        printSolutions(mediumSolutions);

        System.out.println("\nTesting Hard Sudoku Puzzle:");
        hardSolver.build();
        long hardStartdfs = System.nanoTime();
        List<int[][]> hardSolutions = hardSolver.DFS();
        long hardEnddfs = System.nanoTime();
        long hardTimedfs = System.nanoTime();
        printSolutions(hardSolutions);

        System.out.println("\nTesting Small Sudoku Puzzle:");
        smallSolver.build();
        long smallStartdfs = System.nanoTime();
        List<int[][]> smallSolutions = smallSolver.DFS();
        long smallEnddfs = System.nanoTime();
        long smallTimedfs = smallEnddfs-smallStartdfs;
        printSolutions(smallSolutions);

       /*System.out.println("\nTesting Large Sudoku Puzzle:");
        largeSolver.build();
        long largeStartdfs = System.nanoTime();
        List<int[][]> largeSolutions = largeSolver.DFS();
        long largeEnddfs = System.nanoTime();
        long largeTimedfs = largeEnddfs-largeStartdfs;
        printSolutions(largeSolutions);*/
    
    

       // Solve the puzzles using BFS
        System.out.println("Testing Easy Sudoku Puzzle (BFS):");
        long easyStartbfs = System.nanoTime();
        List<int[][]> easySolutionsbfs = easySolver.BFS();
        long easyEndbfs = System.nanoTime();
        long easyTimebfs = easyEndbfs-easyStartbfs;
        printSolutions(easySolutionsbfs);

        System.out.println("\nTesting Medium Sudoku Puzzle (BFS):");
        long mediumStartbfs = System.nanoTime();
        List<int[][]> mediumSolutionsbfs = mediumSolver.BFS();
        long mediumEndbfs=System.nanoTime();
        long mediumTimebfs = mediumEndbfs-mediumStartbfs;
        printSolutions(mediumSolutionsbfs);

        System.out.println("\nTesting Hard Sudoku Puzzle (BFS):");
        //hardSolver.build();
        long hardStartbfs = System.nanoTime();
        List<int[][]> hardSolutionsbfs = hardSolver.BFS();
        long hardEndbfs = System.nanoTime();
        long hardTimebfs = hardEndbfs-hardStartbfs;
        printSolutions(hardSolutionsbfs);

        System.out.println("\nTesting Small Sudoku Puzzle (BFS):");
        long smallStartbfs = System.nanoTime();
        List<int[][]> smallSolutionsbsf = smallSolver.BFS();
        long smallEndbfs = System.nanoTime();
        long smallTimebfs = smallEndbfs-smallStartbfs;
        printSolutions(smallSolutionsbsf);

        /*System.out.println("\nTesting Large Sudoku Puzzle (BFS):");
        long largeStartbfs = System.nanoTime();
        List<int[][]> largeSolutionsbsf = largeSolver.BFS();
        long largeEndbfs = System.nanoTime();
        long largeTimebfs = largeEndbfs-largeStartbfs;
        printSolutions(largeSolutionsbsf);*/

       System.out.print("\nDFS Times: \n");
       System.out.print("Easy: "+ easyTimedfs+ " nanoseconds \n");
       System.out.print("Medium: "+ mediumTimedfs+ " nanoseconds \n");
       System.out.print("Hard: "+ hardTimedfs+ " nanoseconds \n");
       System.out.print("Small: "+ smallTimedfs+ " nanoseconds \n");
       //System.out.print("Large: "+ largeTimedfs+ "nanoseconds \n");

       System.out.print("\nBFS Times: \n");
       System.out.print("Easy: "+ easyTimebfs+ " nanoseconds \n");
       System.out.print("Medium: "+ mediumTimebfs+ " nanoseconds \n");
       System.out.print("Hard: "+ hardTimebfs+ " nanoseconds \n");
       System.out.print("Small: "+ smallTimebfs+ " nanoseconds \n");
       //System.out.print("Large: "+ largeTimebfs+ "nanoseconds \n");

    } 

    // Method to print all solutions
    private static void printSolutions(List<int[][]> solutions) {
        if (solutions.isEmpty()) {
            System.out.println("No solution found.");
        } else {
            for (int[][] solution : solutions) {
                printGrid(solution, solution[0].length);
                System.out.println();
            }
        }
    }

    // Method to print a Sudoku grid
    private static void printGrid(int[][] grid, int gridSize) {
        for (int i = 0; i <gridSize ; i++) {
            for (int j = 0; j <gridSize ; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
