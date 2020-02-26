package model;

import java.util.Random;

/**
 * Class handles all the game's logic. Such as,
 * updating the board and it's mask (a mask is the layer on top of the board
 * which hides the view of the board from the player)
 * All it needs is a user input and will do the updates, then the TextUI can get the boards.
* */

public class MazeGame {

    // TODO: 2020-02-13 Check all [][] if the values are placed in the right brackets [y][x] [width][length] 

    private MazeElement[][] board;
    private MazeElement[][] boardMask;
    private int width;
    private int length;
    private Player player;
    private Cat tom;
    private Cat joe;
    private Cat chad;
    private static int[] cheesePosition;
    private byte wins;
    private byte maxWins;

    private Random random = new Random();

    public MazeGame() {
        this.wins = 0;
        this.maxWins = 5;
    }

    public void init(int width, int length) {
        this.width = width;
        this.length = length;
        Maze maze = new Maze(width, length);
        board = maze.getMaze();
        boardMask = maze.getMazeMask();

        materializeParticipants();
    }

    private void materializeParticipants() {
        assert !(board[1][1] == MazeElement.WALL
                || board[1][length-2] == MazeElement.WALL
                || board[width-2][1] == MazeElement.WALL
                || board[width-2][length-2] == MazeElement.WALL);

        player = new Player(1, 1, false);
        tom = new Cat(1, width-2);
        joe = new Cat(length-2, 1);
        chad = new Cat(length-2, width-2);

        board[1][1] = MazeElement.PLAYER;
        board[1][length-2] = MazeElement.CAT;
        board[width-2][1] = MazeElement.CAT;
        board[width-2][length-2] = MazeElement.CAT;

        int randY = random.nextInt(width-2);
        int randX = random.nextInt(length-2);
        while (board[randY][randX] == MazeElement.WALL
                || (randX == 1 && randY == 1)) {
            randY = random.nextInt(width-2);
            randX = random.nextInt(length-2);
        }

        cheesePosition = new int[]{randX, randY};
        board[randY][randX] = MazeElement.CHEESE;
    }

    public void handleMovementCommands(String command) {
        assert isMoveValidPlayer(command);
        player.move(board, command);
        tom.move(board, player);
        joe.move(board, player);
        chad.move(board, player);

        updatePlayerInBoardMask();
    }


    public void updatePlayerInBoardMask() {
        int playerY = player.getYPos();
        int playerX = player.getXPos();


        boardMask[playerY][playerX+1] = board[playerY][playerX+1];
        boardMask[playerY][playerX-1] = board[playerY][playerX-1];
        boardMask[playerY+1][playerX] = board[playerY+1][playerX];
        boardMask[playerY-1][playerX] = board[playerY-1][playerX];
        boardMask[playerY-1][playerX-1] = board[playerY-1][playerX-1];
        boardMask[playerY+1][playerX+1] = board[playerY+1][playerX+1];
        boardMask[playerY-1][playerX+1] = board[playerY-1][playerX+1];
        boardMask[playerY+1][playerX-1] = board[playerY+1][playerX-1];
    }

    public boolean isPlayerWin() {
        if (player.getYPos() == cheesePosition[1] && player.getXPos() == cheesePosition[0]) {
            wins++;
            return true;
        }
        return false;
    }

    public boolean isPlayerDead(){
        return player.isDead();
    }

    public byte getWins() {
        return wins;
    }

    public byte getMaxWins() {
        return maxWins;
    }

    public boolean isMoveValidPlayer(String direction) {
        return player.isValidMove(board, direction);
    }

    public void setMaxWins(byte maxWins) {
        this.maxWins = maxWins;
    }

    public boolean isGameEnd() {
        return wins == maxWins;
    }

    public static int[] getCheesePosition() {
        return cheesePosition;
    }

    public int[] getPlayerPosition() {
        return player.getPlayerPosition();
    }

    public int[] getTomPosition() {
        return tom.getCatPosition();
    }

    public int[] getJoePosition() {
        return joe.getCatPosition();
    }

    public int[] getChadPosition() {
        return chad.getCatPosition();
    }

    public MazeElement[][] getBoard() {
        return board;
    }

    public MazeElement[][] getBoardMask() {
        return boardMask;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
