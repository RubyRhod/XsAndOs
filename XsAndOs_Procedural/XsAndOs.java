package XsAndOs.XsAndOs_procedural;

import java.util.Scanner;

public class XsAndOs {

    protected final static int GRID_SIZE = 3; // playing grid size
    private final static char DEFAULT_VALUE = ' ';
    private final static int MAX_INPUT_VALUE = 3;
    private final static int MIN_INPUT_VALUE = 1;
    private final static int INPUT_ADJUSTMENT_VALUE = 1;
    static char[][] grid = new char[GRID_SIZE][GRID_SIZE];
    static int moves = 0; // move counter
    static int players = 0;

    static void showGrid(){
        System.out.println();
        for (int row = 0; row < GRID_SIZE; row++){
            for (int column = 0; column < GRID_SIZE; column++){
                System.out.print("[" + grid[row][column] + "] ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[] getInput() {

        String[] playerInput;
        int[] coords = new int[2];
        Scanner scanner = new Scanner(System.in);
        boolean inputCorrect = false;

        while (!inputCorrect) {
            playerInput = scanner.nextLine().trim().split(" ");

            if(playerInput.length == 2){

                if(playerInput[0].matches("[" + MIN_INPUT_VALUE + "-" + MAX_INPUT_VALUE + "]{1}") &&
                        playerInput[1].matches("[" + MIN_INPUT_VALUE + "-" + MAX_INPUT_VALUE + "]{1}") ) {
                    int i = Integer.parseInt(playerInput[0].trim()) - INPUT_ADJUSTMENT_VALUE;
                    int j = Integer.parseInt(playerInput[1].trim()) - INPUT_ADJUSTMENT_VALUE;

                    coords[0] = i;
                    coords[1] = j;
                    inputCorrect = true;
                }
                else{
                    System.out.println("Ошибка ввода! Введены значения, отличные от 1-3.");
                }
            }
            else{
                System.out.println("Ошибка ввода! Введено больше или меньше 2х координат.");
            }
        }
        return coords;
    }

    static void inputValue(char player){

        System.out.println("Ходит " + player);
        System.out.println("Введите координаты ячейки по вертикали (1-3) и по горизонтали(1-3), разделяя их пробелом.");

        int[] coords = new int[2];

        boolean freeCell = true;

        while (freeCell){

            coords = getInput();
            int i = coords[0];
            int j = coords[1];

            if (grid[i][j] == DEFAULT_VALUE) {
                grid[i][j] = player;
                freeCell = false;
            }
            else {
                System.out.println("Эта клетка занята\n" +
                        "Выберите другую клетку для хода: ");
            }
        }
        moves++;
    }

    static char checkGrid(){
        if(grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]){
            if(grid[0][0] != DEFAULT_VALUE){
                return grid[0][0];
            }
        }
        if(grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]){
            if(grid[2][0] != DEFAULT_VALUE){
                return grid[2][0];
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

    static boolean checkWinner(char player){
        char winner;

        showGrid();

        winner = checkGrid();
        if(winner == 'X' || winner == 'O'){
            System.out.println("Победитель " + winner + "!!!");
            return true;
        } else if(winner == 'N') {
            System.out.println("Ничья");
            return true;
        }
        return false;
    }

    public static void main(String[] args){

        char winner = DEFAULT_VALUE;

        for (int i = 0; i < GRID_SIZE; i++){
            for (int j = 0; j < GRID_SIZE; j++){
                grid[i][j] = DEFAULT_VALUE;
            }
        }

        System.out.println("Введите количество игроков - 1(против ИИ) или 2");

        Scanner scan = new Scanner(System.in);

        while(true){
            String playersStr = scan.nextLine().trim();

            if(playersStr.matches("[1-2]{1}")) {
                players = Integer.parseInt(playersStr);
                break;
            } else {
                System.out.println("Ошибка ввода! Введите 1 или 2.");
            }
        }

        showGrid();

        while(true){
            inputValue('X');
            if(checkWinner('X')){break;}

            if (players == 2) {inputValue('O');}
            else              {AI.aiMove();}
            if(checkWinner('O')){break;}
        }
    }
}
