package XsAndOs.XsAndOs_procedural;

public class AI {

    static int moveCount = 0;
    static int moveVariant = 0;

    private static void step(int i, int j){
        XsAndOs.grid[i][j] = 'O';
        XsAndOs.moves++;
    }

    // First XO.AI step.
    static void firstMove() {
        if(XsAndOs.grid[1][1] == ' ') {
            step(1,1);
        }
        else {
            moveVariant = 0 + (int)(Math.random() * ((3 - 0) + 1));
            switch (moveVariant) {
                case 0: step(0,0);
                    break;
                case 1: step(0,2);
                    break;
                case 2: step(2,2);
                    break;
                case 3: step(2,0);
                    break;
            }
        }
    }

    static boolean victoryCheck(char id){  //Checking probable victory after next step
        // Checking diagonal "\"
        if(XsAndOs.grid[0][0] == id && XsAndOs.grid[1][1] == id && XsAndOs.grid[2][2] == ' '){
            step(2,2);
            return true;
        }
        if(XsAndOs.grid[0][0] == id && XsAndOs.grid[1][1] == ' ' && XsAndOs.grid[2][2] == id){
            step(1,1);
            return true;
        }
        if(XsAndOs.grid[0][0] == ' ' && XsAndOs.grid[1][1] == id && XsAndOs.grid[2][2] == id){
            step(0,0);
            return true;
        }
        // Checking diagonal "/"
        if(XsAndOs.grid[2][0] == id && XsAndOs.grid[1][1] == id && XsAndOs.grid[0][2] == ' '){
            step(0,2);
            return true;
        }
        if(XsAndOs.grid[2][0] == id && XsAndOs.grid[1][1] == ' ' && XsAndOs.grid[0][2] == id){
            step(1,1);
            return true;
        }
        if(XsAndOs.grid[2][0] == ' ' && XsAndOs.grid[1][1] == id && XsAndOs.grid[0][2] == id){
            step(2,0);
            return true;
        }
        // Checking rows
        for(int i = 0; i<3; i++){
            if(XsAndOs.grid[i][0] == id && XsAndOs.grid[i][1] == id && XsAndOs.grid[i][2] == ' '){
                step(i,2);
                return true;
            }
            if(XsAndOs.grid[i][0] == id && XsAndOs.grid[i][1] == ' ' && XsAndOs.grid[i][2] == id){
                step(i,1);
                return true;
            }
            if(XsAndOs.grid[i][0] == ' ' && XsAndOs.grid[i][1] == id && XsAndOs.grid[i][2] == id){
                step(i,0);
                return true;
            }
        }
        // Checking columns
        for(int i = 0; i<3; i++){
            if(XsAndOs.grid[0][i] == id && XsAndOs.grid[1][i] == id && XsAndOs.grid[2][i] == ' '){
                step(2, i);
                return true;
            }
            if(XsAndOs.grid[0][i] == id && XsAndOs.grid[1][i] == ' ' && XsAndOs.grid[2][i] == id){
                step(1,i);
                return true;
            }
            if(XsAndOs.grid[0][i] == ' ' && XsAndOs.grid[1][i] == id && XsAndOs.grid[2][i] == id){
                step(0,i);
                return true;
            }
        }
        return false;
    }

    // Checking variant 2.1
    // [ ][ ][x]     [x][ ][ ]
    // [ ][o][ ]  or [ ][o][ ]
    // [x][ ][ ]     [ ][ ][x]
    static boolean check2_1(){
        // checking diagonal "\"
        if((XsAndOs.grid[0][0] == 'X' && XsAndOs.grid[1][1] == 'O' && XsAndOs.grid[2][2] == 'X')||
        (XsAndOs.grid[0][2] == 'X' && XsAndOs.grid[1][1] == 'O' && XsAndOs.grid[2][0] == 'X'))
        {
            moveVariant = 0 + (int)(Math.random() * ((3 - 0) + 1));
            switch (moveVariant) {
                case 0: step(0,1);
                    break;
                case 1: step(1,0);
                    break;
                case 2: step(2,1);
                    break;
                case 3: step(1,2);
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
    static boolean check2_2(){
        // checking diagonal "\"
        if((XsAndOs.grid[0][0] == 'X' && XsAndOs.grid[1][1] == 'X' && XsAndOs.grid[2][2] == 'O')||
                (XsAndOs.grid[0][0] == 'O' && XsAndOs.grid[1][1] == 'X' && XsAndOs.grid[2][2] == 'X'))
        {
            moveVariant = 0 + (int)(Math.random() * ((1 - 0) + 1));
            switch (moveVariant) {
                case 0: step(2,0);
                    break;
                case 1: step(0,2);
                    break;
            }
            return true;
        }

        // checking diagonal "/"
        if((XsAndOs.grid[0][2] == 'X' && XsAndOs.grid[1][1] == 'X' && XsAndOs.grid[2][0] == 'O')||
                (XsAndOs.grid[0][2] == 'O' && XsAndOs.grid[1][1] == 'X' && XsAndOs.grid[2][0] == 'X'))
        {
            moveVariant = 0 + (int)(Math.random() * ((1 - 0) + 1));
            switch (moveVariant) {
                case 0: step(0,0);
                    break;
                case 1: step(2,2);
                    break;
            }
            return true;
        }
        return false;
    }


    static void aiMove(){
        if (moveCount ==0){
            firstMove();
            moveCount++;
        }
        else {
            if(victoryCheck('O')) {
                return;
            }
            else if(victoryCheck('X')) {
                return;
            }
            else if(check2_1()){
                return;
            }
            else if(check2_2()){
                return;
            }
            else {
             outerfor:   for (int i = 0;  i < XsAndOs.GRID_SIZE ; i++){
                    for (int j = 0;  j < XsAndOs.GRID_SIZE ; j++){
                        if(XsAndOs.grid[i][j] == ' ') {
                            step(i,j);
                            break outerfor;

                        }
                    }
                }
            }
        }
    }
}
