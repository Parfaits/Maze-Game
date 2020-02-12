package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
    private int length;
    private int width;
    private MazeElement[][] maze;
    private MazeElement[][] mazeMask;
    private Player player;
    private Cat cat1;
    private Cat cat2;
    private Cat cat3;
    private int[] cheesePosition;
    private Random random = new Random();

    public Maze(int length, int width) {
        this.length = length;
        this.width = width;
        this.maze = new MazeElement[width][length];
        this.mazeMask = new MazeElement[width][length];
        this.cheesePosition = new int[2];
        initMaze();
        mazeGenerate();
        checkCorners();
        mazeMaskGenerate();
        materializeParticipants();
    }

    private void checkCorners() {
        while (maze[1][1] == MazeElement.WALL
                || maze[1][length-2] == MazeElement.WALL
                || maze[width-2][1] == MazeElement.WALL
                || maze[width-2][length-2] == MazeElement.WALL) {
            initMaze();
            mazeGenerate();
        }
    }

    private void materializeParticipants() {
        assert !(maze[1][1] == MazeElement.WALL
                || maze[1][length-2] == MazeElement.WALL
                || maze[width-2][1] == MazeElement.WALL
                || maze[width-2][length-2] == MazeElement.WALL);

        player = new Player(1, 1, false);
        cat1 = new Cat(1, length-2);
        cat2 = new Cat(width-2, 1);
        cat3 = new Cat(width-2, length-2);

        maze[1][1] = MazeElement.PLAYER;
        maze[1][length-2] = MazeElement.CAT;
        maze[width-2][1] = MazeElement.CAT;
        maze[width-2][length-2] = MazeElement.CAT;

        int randX = random.nextInt(length-2);
        int randY = random.nextInt(width-2);
        while (maze[randY][randX] == MazeElement.WALL
                || (randX == 1 && randY == 1)
                || (randX == 1 && randY == length-2)
                || (randX == width-2 && randY == 1) 
                || (randX == width-2 && randY == length-2)) {
            randX = random.nextInt(length-2);
            randY = random.nextInt(width-2);
        }
        cheesePosition[0] = randX;
        cheesePosition[1] = randY;
        maze[randX][randY] = MazeElement.CHEESE;
    }

    private void mazeMaskGenerate() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (i == 0 || i == width-1 || j == 0 || j == length-1) {
                    mazeMask[i][j] = MazeElement.WALL;
                } else {
                    mazeMask[i][j] = MazeElement.HIDDEN;
                }
            }
        }
    }

    private void initMaze() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                maze[i][j] = MazeElement.WALL;
            }
        }
    }

    private void mazeGenerate() {
        List<int[]> wallPositions = new ArrayList<>();
        int x = 1;
        int y = 1;
        maze[x][y] = MazeElement.PASSAGE;
        wallPositions.add(new int[]{x, y+1});
        wallPositions.add(new int[]{x+1, y});
        while (!wallPositions.isEmpty()) {
            int randNum = random.nextInt(wallPositions.size());
            int[] wallAndOppositeCell =  wallPositions.get(randNum);
            if (wallAndOppositeCell[0] > 0 && wallAndOppositeCell[1] > 0 && wallAndOppositeCell[0] < length-1 && wallAndOppositeCell[1] < width-1) {
                if (
                        (maze[wallAndOppositeCell[0]][wallAndOppositeCell[1] + 1] == MazeElement.PASSAGE && maze[wallAndOppositeCell[0]][wallAndOppositeCell[1] - 1] ==
                        MazeElement.WALL && maze[wallAndOppositeCell[0] + 1][wallAndOppositeCell[1]] == MazeElement.WALL && maze[wallAndOppositeCell[0] - 1][wallAndOppositeCell[1]] == MazeElement.WALL)

                || (maze[wallAndOppositeCell[0]][wallAndOppositeCell[1] - 1] == MazeElement.PASSAGE && maze[wallAndOppositeCell[0]][wallAndOppositeCell[1] + 1] ==
                        MazeElement.WALL && maze[wallAndOppositeCell[0] + 1][wallAndOppositeCell[1]] == MazeElement.WALL && maze[wallAndOppositeCell[0] - 1][wallAndOppositeCell[1]] == MazeElement.WALL)

                || (maze[wallAndOppositeCell[0] + 1][wallAndOppositeCell[1]] == MazeElement.PASSAGE && maze[wallAndOppositeCell[0] - 1][wallAndOppositeCell[1]] ==
                        MazeElement.WALL && maze[wallAndOppositeCell[0]][wallAndOppositeCell[1] + 1] == MazeElement.WALL && maze[wallAndOppositeCell[0]][wallAndOppositeCell[1] - 1] == MazeElement.WALL)

                || (maze[wallAndOppositeCell[0] - 1][wallAndOppositeCell[1]] == MazeElement.PASSAGE && maze[wallAndOppositeCell[0] + 1][wallAndOppositeCell[1]] ==
                        MazeElement.WALL && maze[wallAndOppositeCell[0]][wallAndOppositeCell[1] + 1] == MazeElement.WALL && maze[wallAndOppositeCell[0]][wallAndOppositeCell[1] - 1] == MazeElement.WALL)


                ) {

                    maze[wallAndOppositeCell[0]][wallAndOppositeCell[1]] = MazeElement.PASSAGE;

                    if (maze[wallAndOppositeCell[0]][wallAndOppositeCell[1] + 1] == MazeElement.WALL) {
                        wallPositions.add(new int[]{wallAndOppositeCell[0], wallAndOppositeCell[1] + 1});
                    }
                    if (maze[wallAndOppositeCell[0]][wallAndOppositeCell[1] - 1] == MazeElement.WALL) {
                        wallPositions.add(new int[]{wallAndOppositeCell[0], wallAndOppositeCell[1] - 1});
                    }
                    if (maze[wallAndOppositeCell[0] + 1][wallAndOppositeCell[1]] == MazeElement.WALL) {
                        wallPositions.add(new int[]{wallAndOppositeCell[0] + 1, wallAndOppositeCell[1]});
                    }
                    if (maze[wallAndOppositeCell[0] - 1][wallAndOppositeCell[1]] == MazeElement.WALL) {
                        wallPositions.add(new int[]{wallAndOppositeCell[0] - 1, wallAndOppositeCell[1]});
                    }
                }
                wallPositions.remove(wallAndOppositeCell);
            }
            wallPositions.remove(wallAndOppositeCell);
        }
    }

    public int[] getCheesePosition() {
        return cheesePosition;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public MazeElement[][] getMaze() {
        return maze;
    }

    public MazeElement[][] getMazeMask() {
        return mazeMask;
    }
}
