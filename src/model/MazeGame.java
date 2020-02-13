package model;

import java.util.Random;

public class MazeGame {

    private MazeElement[][] board;
    private MazeElement[][] boardMask;
    private int length;
    private int width;
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

    public void init(int length, int width) {
        this.length = length;
        this.width = width;
        Maze maze = new Maze(length, width);
        board = maze.getMaze();
        boardMask = maze.getMazeMask();
        wins = 0;
        maxWins = 5;

        materializeParticipants();
        initializeBoardMask();
    }

    private void initializeBoardMask() {
        boardMask[1][1] = MazeElement.PLAYER;
        boardMask[1][length-2] = MazeElement.CAT;
        boardMask[width-2][1] = MazeElement.CAT;
        boardMask[width-2][length-2] = MazeElement.CAT;
        boardMask[cheesePosition[0]][cheesePosition[1]] = MazeElement.CHEESE;

        tomCurrentCell = MazeElement.HIDDEN;
        joeCurrentCell = MazeElement.HIDDEN;
        chadCurrentCell = MazeElement.HIDDEN;
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
    }

    // TODO: 2020-02-12 Player.class && Cat.class
    public void handleCommands(String command) {
        assert isMoveValid(command);
        if (command.equalsIgnoreCase("w")
                || command.equalsIgnoreCase("a")
                || command.equalsIgnoreCase("s")
                || command.equalsIgnoreCase("d")) {

            player.move(board, command);
            tom.move(board, boardMask);
            joe.move(board, boardMask);
            chad.move(board, boardMask);
        } else if (command.equalsIgnoreCase("c")) {
            maxWins = 1;
        } else {
            System.err.println("swag");
        }

        updatePlayerInBoardMask();
//        updateCatsInBoardMask();
    }

    @Deprecated
    private void updateCatsInBoardMask() {
        int tomY = tom.getYPos();
        int tomX = tom.getXPos();
        int joeY = joe.getYPos();
        int joeX = joe.getXPos();
        int chadY = chad.getYPos();
        int chadX = chad.getXPos();

        if (tomX == cheesePosition[0] && tomY == cheesePosition[1]) {
            boardMask[tomY][tomX] = MazeElement.CAT;
            tomCurrentCell = MazeElement.CHEESE;
        } else if (board[tomY][tomX] == MazeElement.PASSAGE) {
            boardMask[tomY][tomX] = MazeElement.CAT;
            tomCurrentCell = MazeElement.PASSAGE;
        } else {
            boardMask[tomY][tomX] = MazeElement.CAT;
            tomCurrentCell = MazeElement.HIDDEN;
        }
        if (joeX == cheesePosition[0] && joeY == cheesePosition[1]) {
            boardMask[joeY][joeX] = MazeElement.CAT;
            joeCurrentCell = MazeElement.CHEESE;
        }
        if (chadX == cheesePosition[0] && chadY == cheesePosition[1]) {
            boardMask[chadY][chadX] = MazeElement.CAT;
            chadCurrentCell = MazeElement.CHEESE;
        }

    }

    private void updatePlayerInBoardMask() {
        int playerY = player.getYPos();
        int playerX = player.getXPos();

        boardMask[playerY][playerX] = board[playerY][playerX];

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

    // TODO: 2020-02-12 Player.class
    public boolean isMoveValid(String direction) {
        return player.isValidMove(board, direction);
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

    public boolean isPlayerDead() {
        return player.isDead();
    }

    public void setWins(byte wins) {
        this.wins = wins;
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
