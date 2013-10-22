package XsAndOs.XsAndOs_Obj;

public class AI extends Player {

    int moveVariant = 0;

    AI(char moveSignature) {
        super(moveSignature);
    }

    @Override
    void move(Grid grid, Game game){
        if (game.moves == 1){
            firstMove(grid, game);
        }
        else {
            if(nextStepVictoryCheck(grid, game, super.moveSignature)) {
                return;
            }
            else if(nextStepVictoryCheck(grid, game, 'X')) {
                return;
            }
            else if(check2_1(grid, game)){
                return;
            }
            else if(check2_2(grid, game)){
                return;
            }
            else {
                outerfor:   for (int i = 0;  i < grid.gridSize ; i++){
                    for (int j = 0;  j < grid.gridSize ; j++){
                        if(grid.grid[i][j] == ' ') {
                            step(grid, game, i, j);
                            break outerfor;

                        }
                    }
                }
            }
        }
    }

    private static void step(Grid grid, Game game, int i, int j){
        grid.grid[i][j] = 'O';
        game.moves++;
        game.setHistory(i, j);
    }

    // First XO.AI step.
    void firstMove(Grid grid, Game game) {
        if(grid.grid[1][1] == ' ') {
            step(grid, game, 1, 1);
        }
        else {
            moveVariant = 0 + (int)(Math.random() * ((3 - 0) + 1));
            switch (moveVariant) {
                case 0: step(grid, game, 0, 0);
                    break;
                case 1: step(grid, game, 0, 2);
                    break;
                case 2: step(grid, game, 2, 2);
                    break;
                case 3: step(grid, game, 2, 0);
                    break;
            }
        }
    }

    boolean nextStepVictoryCheck(Grid grid, Game game, char moveSignature){  //Checking probable victory after next step
        // Checking diagonal "\"
        if(grid.grid[0][0] == moveSignature && grid.grid[1][1] == moveSignature && grid.grid[2][2] == ' '){
            step(grid, game, 2, 2);
            return true;
        }
        if(grid.grid[0][0] == moveSignature && grid.grid[1][1] == ' ' && grid.grid[2][2] == moveSignature){
            step(grid, game, 1, 1);
            return true;
        }
        if(grid.grid[0][0] == ' ' && grid.grid[1][1] == moveSignature && grid.grid[2][2] == moveSignature){
            step(grid, game, 0, 0);
            return true;
        }
        // Checking diagonal "/"
        if(grid.grid[2][0] == moveSignature && grid.grid[1][1] == moveSignature && grid.grid[0][2] == ' '){
            step(grid, game, 0, 2);
            return true;
        }
        if(grid.grid[2][0] == moveSignature && grid.grid[1][1] == ' ' && grid.grid[0][2] == moveSignature){
            step(grid, game, 1, 1);
            return true;
        }
        if(grid.grid[2][0] == ' ' && grid.grid[1][1] == moveSignature && grid.grid[0][2] == moveSignature){
            step(grid, game, 2, 0);
            return true;
        }
        // Checking rows
        for(int i = 0; i<3; i++){
            if(grid.grid[i][0] == moveSignature && grid.grid[i][1] == moveSignature && grid.grid[i][2] == ' '){
                step(grid, game, i, 2);
                return true;
            }
            if(grid.grid[i][0] == moveSignature && grid.grid[i][1] == ' ' && grid.grid[i][2] == moveSignature){
                step(grid, game, i, 1);
                return true;
            }
            if(grid.grid[i][0] == ' ' && grid.grid[i][1] == moveSignature && grid.grid[i][2] == moveSignature){
                step(grid, game, i, 0);
                return true;
            }
        }
        // Checking columns
        for(int i = 0; i<3; i++){
            if(grid.grid[0][i] == moveSignature && grid.grid[1][i] == moveSignature && grid.grid[2][i] == ' '){
                step(grid, game, 2, i);
                return true;
            }
            if(grid.grid[0][i] == moveSignature && grid.grid[1][i] == ' ' && grid.grid[2][i] == moveSignature){
                step(grid, game, 1, i);
                return true;
            }
            if(grid.grid[0][i] == ' ' && grid.grid[1][i] == moveSignature && grid.grid[2][i] == moveSignature){
                step(grid, game, 0, i);
                return true;
            }
        }
        return false;
    }

    // Checking variant 2.1
    // [ ][ ][x]     [x][ ][ ]
    // [ ][o][ ]  or [ ][o][ ]
    // [x][ ][ ]     [ ][ ][x]
    boolean check2_1(Grid grid, Game game){
        // checking diagonal "\"
        if((grid.grid[0][0] == 'X' && grid.grid[1][1] == 'O' && grid.grid[2][2] == 'X')||
                (grid.grid[0][2] == 'X' && grid.grid[1][1] == 'O' && grid.grid[2][0] == 'X'))
        {
            moveVariant = 0 + (int)(Math.random() * ((3 - 0) + 1));
            switch (moveVariant) {
                case 0: step(grid, game, 0, 1);
                    break;
                case 1: step(grid, game, 1, 0);
                    break;
                case 2: step(grid, game, 2, 1);
                    break;
                case 3: step(grid, game, 1, 2);
                    break;
            }
            return true;
        }
        return false;
    }

    // Checking variant 2.2
    // [x][ ][ ]     [o][ ][ ]
    // [ ][x][ ]  or [ ][x][ ] and mirrored variants
    // [ ][ ][o]     [ ][ ][x]
    boolean check2_2(Grid grid, Game game){
        // checking diagonal "\"
        if((grid.grid[0][0] == 'X' && grid.grid[1][1] == 'X' && grid.grid[2][2] == 'O')||
                (grid.grid[0][0] == 'O' && grid.grid[1][1] == 'X' && grid.grid[2][2] == 'X'))
        {
            moveVariant = 0 + (int)(Math.random() * ((1 - 0) + 1));
            switch (moveVariant) {
                case 0: step(grid, game,2, 0);
                    break;
                case 1: step(grid, game,0, 2);
                    break;
            }
            return true;
        }

        // checking diagonal "/"
        if((grid.grid[0][2] == 'X' && grid.grid[1][1] == 'X' && grid.grid[2][0] == 'O')||
                (grid.grid[0][2] == 'O' && grid.grid[1][1] == 'X' && grid.grid[2][0] == 'X'))
        {
            moveVariant = 0 + (int)(Math.random() * ((1 - 0) + 1));
            switch (moveVariant) {
                case 0: step(grid, game,0, 0);
                    break;
                case 1: step(grid, game,2, 2);
                    break;
            }
            return true;
        }
        return false;
    }


}
