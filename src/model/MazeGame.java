package model;

import java.util.Random;

public class MazeGame {
    private MazeElement[][] board;
    private MazeElement[][] boardMask;
    private int length;
    private int width;
    private Player player;
    private Cat cat1;
    private Cat cat2;
    private Cat cat3;
    private Random random = new Random();

    public void init(int length, int width) {
        Maze maze = new Maze(length, width);
        this.board = maze.getMaze();
        this.boardMask = maze.getMazeMask();
        this.length = maze.getLength();
        this.width = maze.getWidth();

        materializeParticipants();
    }

    private void materializeParticipants() {
        assert !(board[1][1] == MazeElement.WALL
                || board[1][length-2] == MazeElement.WALL
                || board[width-2][1] == MazeElement.WALL
                || board[width-2][length-2] == MazeElement.WALL);

        player = new Player(1, 1, false);
        cat1 = new Cat(1, length-2);
        cat2 = new Cat(width-2, 1);
        cat3 = new Cat(width-2, length-2);

        board[1][1] = MazeElement.PLAYER;
        board[1][length-2] = MazeElement.CAT;
        board[width-2][1] = MazeElement.CAT;
        board[width-2][length-2] = MazeElement.CAT;

        int randX = random.nextInt(length-2);
        int randY = random.nextInt(width-2);
        while (board[randY][randX] == MazeElement.WALL
                || (randX == 1 && randY == 1)
                || (randX == 1 && randY == length-2)
                || (randX == width-2 && randY == 1)
                || (randX == width-2 && randY == length-2)) {
            randX = random.nextInt(length-2);
            randY = random.nextInt(width-2);
        }

        board[randX][randY] = MazeElement.CHEESE;
    }

    public boolean isPlayerDead() {
        return player.isDead();
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
