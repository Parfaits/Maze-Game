package model;

import java.util.Random;

public class MazeGame {

    // TODO: 2020-02-13 implement player die logic
    // TODO: 2020-02-13 Check all [][] if the values are placed in the right brackets [y][x] [width][length] 

    private MazeElement[][] board;
    private MazeElement[][] boardMask;
    private int width;
    private int length;
    private Player player;
    private Cat tom;
    private Cat joe;
    private Cat chad;
    private int[] cheesePosition;
    private MazeElement tomCurrentCell;
    private MazeElement joeCurrentCell;
    private MazeElement chadCurrentCell;
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
        tom = new Cat(1, length-2);
        joe = new Cat(width-2, 1);
        chad = new Cat(width-2, length-2);

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

        cheesePosition = new int[]{randY, randX};
        board[randY][randX] = MazeElement.CHEESE;
        boardMask[randY][randX] = MazeElement.CHEESE;
    }

    // TODO: 2020-02-12 Player.class && Cat.class
    public void handleMovementCommands(String command) {
        assert isMoveValidPlayer(command);
        player.move(board, command);
        tom.move(board);
        joe.move(board);
        chad.move(board);

        updatePlayerInBoardMask();
        updateCatsInBoardMask();
    }


    private void updateCatsInBoardMask() {
        assert isMoveValidTom();
        tom.move(board);
        assert isMoveValidJoe();
        joe.move(board);
        assert isMoveValidChad();
        chad.move(board);

    }

    public void updatePlayerInBoardMask() {
        int playerY = player.getYPos();
        int playerX = player.getXPos();

//        boardMask[playerY][playerX] = board[playerY][playerX];

        if (playerX+1 != length-1) {
            boardMask[playerY][playerX+1] = board[playerY][playerX+1];
        }
        if (playerX-1 != 0) {
            boardMask[playerY][playerX-1] = board[playerY][playerX-1];
        }
        if (playerY+1 != width-1) {
            boardMask[playerY+1][playerX] = board[playerY+1][playerX];
        }
        if (playerY-1 != 0) {
            boardMask[playerY-1][playerX] = board[playerY-1][playerX];
        }
        if (playerX-1 != 0 && playerY-1 != 0) {
            boardMask[playerY-1][playerX-1] = board[playerY-1][playerX-1];
        }
        if (playerX+1 != length-1 && playerY+1 != width-1) {
            boardMask[playerY+1][playerX+1] = board[playerY+1][playerX+1];
        }
        if (playerX+1 != length-1 && playerY-1 != 0) {
            boardMask[playerY-1][playerX+1] = board[playerY-1][playerX+1];
        }
        if (playerX-1 != 0 && playerY+1 != width-1) {
            boardMask[playerY+1][playerX-1] = board[playerY+1][playerX-1];
        }
    }

    public boolean isPlayerWin() {
        if (player.getYPos() == cheesePosition[0] && player.getXPos() == cheesePosition[1]) {
            wins++;
            return true;
        }
        return false;
    }

    public boolean isPlayerDead(){
        if (player.getYPos() == tom.getYPos() && player.getXPos() == tom.getXPos()){
            return true;
        }else if (player.getYPos() == joe.getYPos() && player.getXPos() == joe.getXPos()){
            return true;
        }else if (player.getYPos() == chad.getYPos() && player.getXPos() == chad.getXPos()){
            return true;
        }
        return false;
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

    public boolean isMoveValidTom() {
        return tom.isValidMove(board);
    }

    public boolean isMoveValidJoe() {
        return joe.isValidMove(board);
    }

    public boolean isMoveValidChad() {
        return chad.isValidMove(board);
    }

    MazeElement getTomCurrentCell() {
        return tomCurrentCell;
    }

    MazeElement getJoeCurrentCell() {
        return joeCurrentCell;
    }

    MazeElement getChadCurrentCell() {
        return chadCurrentCell;
    }

    public void setMaxWins(byte maxWins) {
        this.maxWins = maxWins;
    }

    public boolean isGameEnd() {
        return wins == maxWins;
    }

//    public boolean isPlayerDead() {
//        return player.isDead();
//    }

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

    public int[] getCheesePosition() {
        return cheesePosition;
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
