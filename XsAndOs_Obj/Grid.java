package XsAndOs.XsAndOs_Obj;

import java.util.Scanner;

public class Grid {

    public int gridSize;
    public final static char DEFAULT_VALUE = ' ';
    char[][] grid;

    Grid () {
        this.gridSize = 3;
        grid = new char[gridSize][gridSize];
        gridInit();
        showGrid();
    }

    public void gridInit() {

        for (int i = 0; i < gridSize; i++){
            for (int j = 0; j < gridSize; j++){
                grid[i][j] = DEFAULT_VALUE;
            }
        }
    }

    public void showGrid(){

        System.out.println();
        for (int row = 0; row < gridSize; row++){
            for (int column = 0; column < gridSize; column++){
                System.out.print("[" + grid[row][column] + "] ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public char checkGridFullness(int moves){

        for(int k = 0; k<3; k+=2) {
            if(grid[1][1] == grid[0][k] && grid[1][1] == grid[2][2-k]) {
                if(grid[1][1] != DEFAULT_VALUE){
                    return grid[1][1];
                }
            }
        }

        for(int i = 0; i<3; i++){
            if(grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]){
                if(grid[i][0] != DEFAULT_VALUE){
                    return grid[i][0];
                }
            }
        }
        for(int i = 0; i<3; i++){
            if(grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]){
                if(grid[0][i] != DEFAULT_VALUE){
                    return grid[0][i];
                }
            }
        }
        if(moves == 9){
            return 'N';
        }
        return DEFAULT_VALUE;
    }

}
