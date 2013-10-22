package XsAndOs.XsAndOs_Obj;

import java.util.Scanner;

public class Game {

    private static int playersQuantity;
    public int moves = 0;

    Game(){}

    public void setPlayersQuantity() {

        System.out.println("Введите количество игроков - 1(против ИИ) или 2");

        Scanner scan = new Scanner(System.in);

        while(true){
            String playersStr = scan.nextLine().trim();

            if(playersStr.matches("[1-2]{1}")) {
                this.playersQuantity = Integer.parseInt(playersStr);
                return;
            } else {
                System.out.println("Ошибка ввода! Введите 1 или 2.");
            }
        }
    }

    public int getPlayersQuantity(){
        return playersQuantity;
    }


    public boolean checkWinner(Grid grid){
        char winner;

        grid.showGrid();

        winner = grid.checkGridFullness(moves);
        if(winner == 'X' || winner == 'O'){
            System.out.println("Победитель " + winner + "!!!");
            return true;
        } else if(winner == 'N') {
            System.out.println("Ничья");
            return true;
        }
        return false;
    }

    private int[][] history = new int[9][2];

    public boolean applyMove() {
        System.out.println("Подтвердите свой ход - Y/N (Yes/No)");

        Scanner scan = new Scanner(System.in);

        while(true){
            String response = scan.nextLine().trim().toLowerCase();

            if(response.matches("[y|n]{1}")) {
                if(response.equals("y")) {
                    return true;
                } else if (response.equals("n")) {
                    return false;
                }
            } else {
                System.out.println("Ошибка ввода! Введите Y или N!");
            }
        }
    }

    public void setHistory(int i, int j) {
        history[moves-1][0] = i;
        history[moves-1][1] = j;
    }

    public int[] getHistory(){
        return history [moves-1];
    }

    public void rewindHistory(Grid grid) {
        for(int i=1; i<3; i++) {
            grid.grid[history[moves-1][0]][history[moves-1][1]] = ' ';
            history[moves-1][0] = -1;
            history[moves-1][1] = -1;
            moves--;
        }

    }

}

