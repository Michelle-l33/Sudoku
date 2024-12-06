
//Paper Citation
//Khemani, Chanchal & Doshi, Jay & Duseja, Juhi & Shah, Krapi & 
//Udmale, Sandeep & Sambhe, Vijay. (2019). Solving Rubik’s Cube Using 
//Graph Theory: ICCI-2017. 10.1007/978-981-13-1132-1_24. 



//implement the non 9x9 grid size option - grid can be any size
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solver{
    private int[][] grid;
    private final int gridSize;
    private final int subGridSize;
    protected Graph graph;

    public Solver(int gridSize, int subGridSize, int [][] OriginalGrid){
        this.gridSize = gridSize;
        this.subGridSize = subGridSize;
        this.grid = OriginalGrid; // creates the grid for the puzzle;
        this.graph = new Graph(); // creates graph for the specified puzzle
        
    }

    //Restraints:
    // No duplicates in row
    // No duplicates in column
    // No duplicates in subgrid

    //BUILD GRAPH METHOD
    public void build() {
        for (int row = 0; row < this.gridSize; row++) {
            for (int col = 0; col < this.gridSize; col++) {
                int vertex = row * this.gridSize + col;
                this.graph.addVertex(vertex);
                //connects edges in rows
                for (int c = 0; c < this.gridSize; c++) {
                    if (c != col) {
                        this.graph.addEdge(vertex, row * this.gridSize + c);
                    }
                }
                //connects edges in cols
                for (int r = 0; r < this.gridSize; r++) {
                    if (r != row) {
                        this.graph.addEdge(vertex, r * this.gridSize + col);
                    }
                }
                // connects edges in the subgrids
                int subGridRowStart = (row / this.subGridSize) * this.subGridSize;
                int subGridColStart = (col / this.subGridSize) * this.subGridSize;
                for (int r = subGridRowStart; r < subGridRowStart + this.subGridSize; r++) {
                    for (int c = subGridColStart; c < subGridColStart + this.subGridSize; c++) {
                        int subGridVertex = r * this.gridSize + c;
                        if (subGridVertex != vertex) {
                            this.graph.addEdge(vertex, subGridVertex);
                        }
                    }
                }

            }
        }
    }

    //SOlVE BY BFS METHOD
    // SOLVE BY BFS METHOD
    public List<int[][]> BFS() {
        List<int[][]> solutions = new ArrayList<>(); // To store all solutions
        Queue<int[][]> queue = new LinkedList<>(); // BFS uses a queue

        // Add the initial grid to the queue
        queue.add(copy(this.grid));

        while (!queue.isEmpty()) {
            int[][] current = queue.poll();

            // Find the next empty cell
            int[] emptyCell = findEmptyCell(current);
            if (emptyCell == null) {
                // No empty cell found, it's a solved grid
                solutions.add(copy(current));
                continue;
            }

            int row = emptyCell[0];
            int col = emptyCell[1];

            // Try placing each number in the empty cell
            for (int num = 1; num <= this.gridSize; num++) {
                if (canPlaceInGrid(current, row, col, num)) {
                    // Create a new grid and place the number
                    int[][] newGrid = copy(current);
                    newGrid[row][col] = num;
                    queue.add(newGrid); // Add to the queue for further exploration
                }
            }
        }

        return solutions; // Return all valid solutions
    }

    private int[] findEmptyCell(int[][] grid) {
        for (int row = 0; row < this.gridSize; row++) {
            for (int col = 0; col < this.gridSize; col++) {
                if (grid[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }
        return null; // No empty cell found
    }

    private boolean canPlaceInGrid(int[][] grid, int row, int col, int num) {
        // Check row
        for (int i = 0; i < this.gridSize; i++) {
            if (grid[row][i] == num) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < this.gridSize; i++) {
            if (grid[i][col] == num) {
                return false;
            }
        }

        // Check subgrid
        int subGridRow = (row / this.subGridSize) * this.subGridSize;
        int subGridCol = (col / this.subGridSize) * this.subGridSize;
        for (int r = subGridRow; r < subGridRow + this.subGridSize; r++) {
            for (int c = subGridCol; c < subGridCol + this.subGridSize; c++) {
                if (grid[r][c] == num) {
                    return false;
                }
            }
        }

        return true; // Placement is valid
    }

    //SOLVE BY DLS METHOD
    public List<int[][]> DFS(){
        List<int[][]> solutions = new ArrayList<>();
        solveByDFS(0, 0, solutions);
        return solutions;
    }

    public void solveByDFS(int row, int col, List<int[][]> solutions) {
        // if end, finished
        if (row == this.gridSize) {
            solutions.add(copy(this.grid));
            return;
        }

        // Calculate the next cell position
        int nextRow = (col == this.gridSize - 1) ? row + 1 : row;
        int nextCol = (col + 1) % this.gridSize;

        // move to next cell if filled
        if (grid[row][col] != 0) {
            solveByDFS(nextRow, nextCol, solutions);
            return;
        }

        // try numbers from 1 to size of grid
        for (int num = 1; num <= this.gridSize; num++) {
            if (canPlace(row, col, num)) {
                this.grid[row][col] = num; // Place the number
                solveByDFS(nextRow, nextCol,solutions); // Continue to next cell
                this.grid[row][col] = 0; // Backtrack
            }
        }
        //no solution
        
    }

    //CHECK IF SOLVED METHOD (maybe check if each column / row = total factorial but addition;)
    //like if grid size is 9, total = 9+8+7+6+5+4+3+2+1 = 45 and if total of a col/row =45 then it is solved

    //CHECK IF ABLE TO PLACE NUM THERE METHOD utilizes graphs adj list to check
    public boolean canPlace(int row, int col, int num){
        int vertex = row * this.gridSize + col;
        for (int neighbor : graph.getAdjVert(vertex)) {
            int neighborRow = neighbor / this.gridSize;
            int neighborCol = neighbor % this.gridSize;
            if (this.grid[neighborRow][neighborCol] == num) {
                return false;
            }
        }
        return true;
    }


    //COPY GRID METHOD TO MAKE CHANGES THAT DONT AFFECT ORIGINAL
    private int[][] copy(int[][] grid){
        int [][] copyGrid = new int[this.gridSize][this.gridSize];
        for (int i =0; i< this.gridSize;i++){
            for(int j = 0; j<this.gridSize; j++){
                copyGrid[i][j]= grid[i][j];
            }
        }
        return copyGrid;
    }

   
}
