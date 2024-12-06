
//Paper Citation
//Khemani, Chanchal & Doshi, Jay & Duseja, Juhi & Shah, Krapi & 
//Udmale, Sandeep & Sambhe, Vijay. (2019). Solving Rubik’s Cube Using 
//Graph Theory: ICCI-2017. 10.1007/978-981-13-1132-1_24. 
//https://stackoverflow.com/questions/71563852/solve-sudoku-using-iterative-breadth-first-search



//implement the non 9x9 grid size option - grid can be any size
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//main solver class
public class Solver{
    private int[][] grid;
    private final int gridSize;
    private final int subGridSize;
    protected Graph graph;

    public Solver(int gridSize, int subGridSize, int [][] OriginalGrid){
        this.gridSize = gridSize; //utilizes grid size and sub grid size in order to work for any size puzzle
        this.subGridSize = subGridSize;
        this.grid = OriginalGrid; // creates the grid for the puzzle;
        this.graph = new Graph(); // creates graph for the specified puzzle
        
    }

    //Restraints:
    // No duplicates in row
    // No duplicates in column
    // No duplicates in subgrid

    //BUILD GRAPH METHOD
    // chat helped with unique vertex - was originally using reg nums
    public void build() {
        for (int row = 0; row < this.gridSize; row++) { //for each row
            for (int col = 0; col < this.gridSize; col++) { // and each col
                int vertex = row * this.gridSize + col; // create a unique vertex
                this.graph.addVertex(vertex); // add vertex to graph
                //connects edges in col
                for (int c = 0; c < this.gridSize; c++) {
                    if (c != col) { // makes sure it doesnt put edge from itself to itself
                        this.graph.addEdge(vertex, row * this.gridSize + c);
                    }
                }
                //connects edges in row
                for (int r = 0; r < this.gridSize; r++) {
                    if (r != row) { // same as col
                        this.graph.addEdge(vertex, r * this.gridSize + col);
                    }
                }
                // connects edges in the subgrids
                int subGridRowStart = (row / this.subGridSize) * this.subGridSize; // uses subgrid size to interact with the right subgrid
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
        int vertex = row * this.gridSize + col;
    
    for (int neighbor : this.graph.getAdjVert(vertex)) {
        int r = neighbor / this.gridSize;
        int c = neighbor % this.gridSize;
        if (grid[r][c] == num) {
            return false;
        }
    }
    return true;
    }

    //SOLVE BY DLS METHOD
    // help from chatGPT
    public List<int[][]> DLS(int limit){
        List<int[][]> solutions = new ArrayList<>();
        solveByDLS(0, 0, 0,limit, solutions);
        return solutions;
    }

    public void solveByDLS(int row, int col, int depth, int maxDepth, List<int[][]> solutions) {
        // If depth limit is reached, terminate search
        if (depth > maxDepth) return;
    
        // if end, finished
        if (row == gridSize) {
            solutions.add(copy(this.grid));
            return;
        }
    
        // Calculate the next cell position
        int nextRow = (col == this.gridSize - 1) ? row + 1 : row;
        int nextCol = (col + 1) % this.gridSize;
    
        // Move to next cell if filled
        if (grid[row][col] != 0) {
            solveByDLS(nextRow, nextCol, depth + 1, maxDepth, solutions);
            return;
        }
    
        // Try numbers from 1 to size of grid
        for (int num = 1; num <= this.gridSize; num++) {
            if (canPlace(row, col, num)) {
                this.grid[row][col] = num; // Place the number
                solveByDLS(nextRow, nextCol, depth + 1, maxDepth, solutions); // Continue to next cell
                this.grid[row][col] = 0; // Backtrack
            }
        }
    }

    //CHECK IF SOLVED METHOD (maybe check if each column / row = total factorial but addition;)
    //like if grid size is 9, total = 9+8+7+6+5+4+3+2+1 = 45 and if total of a col/row =45 then it is solved

    //CHECK IF ABLE TO PLACE NUM THERE METHOD utilizes graphs adj list to check
    //chatgpt helped transform from checking by grid to checking by adjacent vertices
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
        for (int i =0; i< this.gridSize;i++){ //row
            for(int j = 0; j<this.gridSize; j++){ //col
                copyGrid[i][j]= grid[i][j];
            }
        }
        return copyGrid;
    }

   
}
