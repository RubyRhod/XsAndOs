package XsAndOs.XsAndOs_Obj;

import java.util.Scanner;

public class Player {

    public char moveSignature;
    private int maxInputValue = 3;
    private final int MIN_INPUT_VALUE = 1;
    private final int INPUT_ADJUSTMENT_VALUE = 1;

    Player(char moveSignature) {

        this.moveSignature = moveSignature;

    }

    Player() {}

    private int[] getCoordinates() {

            String[] playerInput;
            int[] coordinates = new int[2];
            Scanner scanner = new Scanner(System.in);
            boolean inputCorrect = false;

            while (!inputCorrect) {
                playerInput = scanner.nextLine().trim().split(" ");

                if(playerInput.length == 2){

                    if(playerInput[0].matches("[" + MIN_INPUT_VALUE + "-" + maxInputValue + "]{1}") &&
                            playerInput[1].matches("[" + MIN_INPUT_VALUE + "-" + maxInputValue + "]{1}") ) {
                        int i = Integer.parseInt(playerInput[0].trim()) - INPUT_ADJUSTMENT_VALUE;
                        int j = Integer.parseInt(playerInput[1].trim()) - INPUT_ADJUSTMENT_VALUE;

                        coordinates[0] = i;
                        coordinates[1] = j;
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
            return coordinates;
        }

    void move(Grid grid, Game game){

        System.out.println("Ходит " + moveSignature);
        System.out.println("Введите координаты ячейки по вертикали (1-3) и по горизонтали(1-3), разделяя их пробелом.");

        int[] coords = new int[2];

        boolean freeCell = true;
        int i = 0, j = 0;
        while (freeCell){

            coords = getCoordinates();
            i = coords[0];
            j = coords[1];

            if (grid.grid[i][j] == grid.DEFAULT_VALUE) {
                grid.grid[i][j] = moveSignature;
                freeCell = false;
            }
            else {
                System.out.println("Эта клетка занята\n" +
                        "Выберите другую клетку для хода: ");
            }
        }
        game.moves++;
        game.setHistory(i, j);
    }

}