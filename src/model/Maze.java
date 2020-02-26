package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class generates a maze with an outer wall using pirm's algorithm.
 * The four corners of the maze will never be covered with a wall because checkCorners()
 * generates a new one. Also, the maze is checked for 2x2 walls inside the outer walls
 * and is regenerated and checkCorners() again.
 * The maze is also generated cycles depending on NUM_WALLS_TO_REMOVE
 * In the end there should be no 2x2 walls, no 2x2 passage, the 4 corners should be accessible
 * and the maze has cycles.
 * */

public class Maze {
    private static final int NUM_WALLS_TO_REMOVE = 3;
    private int width;
    private int length;
    private MazeElement[][] maze;
    private MazeElement[][] mazeMask;
    private Random random = new Random();

    public Maze(int width, int length) {
        this.width = width;
        this.length = length;
        assert width > 3 && length > 3;
        this.maze = new MazeElement[width][length];
        this.mazeMask = new MazeElement[width][length];
        initMaze();
        mazeGenerate();
        checkCorners();
        check2x2Walls();
        for (int i = 0; i < NUM_WALLS_TO_REMOVE; i++) {
            createCycles();
        }
        mazeMaskGenerate();
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

    private void check2x2Walls() {
        for (int i = 2; i < width-3; i++) {
            for (int j = 2; j < length-3; j++) {
                if (maze[i][j] == MazeElement.WALL
                        && ((maze[i][j+1] == MazeElement.WALL
                        && maze[i+1][j+1] == MazeElement.WALL
                        && maze[i+1][j] == MazeElement.WALL)

                        || (maze[i][j+1] == MazeElement.WALL
                        && maze[i-1][j+1] == MazeElement.WALL
                        && maze[i-1][j] == MazeElement.WALL)

                        || (maze[i][j-1] == MazeElement.WALL
                        && maze[i-1][j-1] == MazeElement.WALL
                        && maze[i-1][j] == MazeElement.WALL)

                        || (maze[i][j-1] == MazeElement.WALL
                        && maze[i+1][j-1] == MazeElement.WALL
                        && maze[i+1][j] == MazeElement.WALL)
                            )
                ) {
                    initMaze();
                    mazeGenerate();
                    checkCorners();
                    i = 2;
                    j = 2;
                }
            }
        }
    }

    private void createCycles() {
        int upperBoundY = width - 2 + 1;
        int upperBoundX = length - 2 + 1;
        int lowerBound = 1;
        int randY = random.nextInt(upperBoundY - lowerBound) + lowerBound;
        int randX = random.nextInt(upperBoundX - lowerBound) + lowerBound;
        while (maze[randY][randX] != MazeElement.WALL
                || ((maze[randY][randX+1] == MazeElement.PASSAGE
                && maze[randY+1][randX+1] == MazeElement.PASSAGE
                && maze[randY+1][randX] == MazeElement.PASSAGE)

                || (maze[randY][randX+1] == MazeElement.PASSAGE
                && maze[randY-1][randX+1] == MazeElement.PASSAGE
                && maze[randY-1][randX] == MazeElement.PASSAGE)

                || (maze[randY][randX-1] == MazeElement.PASSAGE
                && maze[randY-1][randX-1] == MazeElement.PASSAGE
                && maze[randY-1][randX] == MazeElement.PASSAGE)

                || (maze[randY][randX-1] == MazeElement.PASSAGE
                && maze[randY+1][randX-1] == MazeElement.PASSAGE
                && maze[randY+1][randX] == MazeElement.PASSAGE)
                    )
        ) {
           randY = random.nextInt(upperBoundY - lowerBound) + lowerBound;
           randX = random.nextInt(upperBoundX - lowerBound) + lowerBound;
        }
        maze[randY][randX] = MazeElement.PASSAGE;
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
            int[] wall =  wallPositions.get(randNum);

            if (wall[0] > 0 && wall[1] > 0 && wall[0] < length-1 && wall[1] < width-1) {

                if (
                        (maze[wall[1] + 1][wall[0]] == MazeElement.PASSAGE && maze[wall[1] - 1][wall[0]] ==
                        MazeElement.WALL && maze[wall[1]][wall[0] + 1] == MazeElement.WALL && maze[wall[1]][wall[0] - 1] == MazeElement.WALL)

                || (maze[wall[1] - 1][wall[0]] == MazeElement.PASSAGE && maze[wall[1] + 1][wall[0]] ==
                        MazeElement.WALL && maze[wall[1]][wall[0] + 1] == MazeElement.WALL && maze[wall[1]][wall[0] - 1] == MazeElement.WALL)

                || (maze[wall[1]][wall[0] + 1] == MazeElement.PASSAGE && maze[wall[1]][wall[0] - 1] ==
                        MazeElement.WALL && maze[wall[1] + 1][wall[0]] == MazeElement.WALL && maze[wall[1] - 1][wall[0]] == MazeElement.WALL)

                || (maze[wall[1]][wall[0] - 1] == MazeElement.PASSAGE && maze[wall[1]][wall[0] + 1] ==
                        MazeElement.WALL && maze[wall[1] + 1][wall[0]] == MazeElement.WALL && maze[wall[1] - 1][wall[0]] == MazeElement.WALL)


                ) {
                    
                    maze[wall[1]][wall[0]] = MazeElement.PASSAGE;

                    if (maze[wall[1] + 1][wall[0]] == MazeElement.WALL) {
                        wallPositions.add(new int[]{wall[0], wall[1] + 1});
                    }
                    if (maze[wall[1] - 1][wall[0]] == MazeElement.WALL) {
                        wallPositions.add(new int[]{wall[0], wall[1] - 1});
                    }
                    if (maze[wall[1]][wall[0] + 1] == MazeElement.WALL) {
                        wallPositions.add(new int[]{wall[0] + 1, wall[1]});
                    }
                    if (maze[wall[1]][wall[0] - 1] == MazeElement.WALL) {
                        wallPositions.add(new int[]{wall[0] - 1, wall[1]});
                    }
                }
            }
            wallPositions.remove(wall);
        }
    }

    MazeElement[][] getMaze() {
        return maze;
    }

    MazeElement[][] getMazeMask() {
        return mazeMask;
    }
}
