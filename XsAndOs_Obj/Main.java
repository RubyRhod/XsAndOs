package XsAndOs.XsAndOs_Obj;

public class Main {

    public static void main(String args[]) {

        Game game = new Game();

        Grid grid = new Grid();

        game.setPlayersQuantity();

        Player player1 = new Player('X');
        Player player2;

        if(game.getPlayersQuantity() == 1) {
            player2 = new AI('O');
        }
        else {
            player2 = new Player('O');
        }

        while(true){
            player1.move(grid, game);
            if(game.checkWinner(grid)) {
                break;
            }

            player2.move(grid, game);
            if(game.checkWinner(grid)) {
                break;
            }

            if(!game.applyMove()) {
                game.rewindHistory(grid);
                grid.showGrid();
            }

        }

    }
}