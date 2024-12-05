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

        // Create Solver instances for each puzzle
        Solver easySolver = new Solver(9, 3, easyPuzzle);
        Solver mediumSolver = new Solver(9, 3, mediumPuzzle);
        Solver hardSolver = new Solver(9, 3, hardPuzzle);


        // Solve the puzzles using DFS
        System.out.println("Testing Easy Sudoku Puzzle:");
        List<int[][]> easySolutions = easySolver.DFS();
        printSolutions(easySolutions);

        System.out.println("\nTesting Medium Sudoku Puzzle:");
        List<int[][]> mediumSolutions = mediumSolver.DFS();
        printSolutions(mediumSolutions);

        System.out.println("\nTesting Hard Sudoku Puzzle:");
        hardSolver.build();
        List<int[][]> hardSolutions = hardSolver.DFS();
        printSolutions(hardSolutions);
    }

       /*  // Solve the puzzles using BFS
        System.out.println("Testing Easy Sudoku Puzzle (BFS):");
        List<int[][]> easySolutionsbfs = easySolver.BFS();
        printSolutions(easySolutionsbfs);

        System.out.println("\nTesting Medium Sudoku Puzzle (BFS):");
        List<int[][]> mediumSolutionsbfs = mediumSolver.BFS();
        printSolutions(mediumSolutionsbfs);

        System.out.println("\nTesting Hard Sudoku Puzzle (BFS):");
        hardSolver.build();
        List<int[][]> hardSolutionsbfs = hardSolver.BFS();
        printSolutions(hardSolutionsbfs);
    } */

    // Method to print all solutions
    private static void printSolutions(List<int[][]> solutions) {
        if (solutions.isEmpty()) {
            System.out.println("No solution found.");
        } else {
            for (int[][] solution : solutions) {
                printGrid(solution);
                System.out.println();
            }
        }
    }

    // Method to print a Sudoku grid
    private static void printGrid(int[][] grid) {
        for (int i = 0; i <9 ; i++) {
            for (int j = 0; j <9 ; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
