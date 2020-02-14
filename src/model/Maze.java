package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
    private int width;
    private int length;
    private MazeElement[][] maze;
    private MazeElement[][] mazeMask;
    private Random random = new Random();

    public Maze(int width, int length) {
        this.width = width;
        this.length = length;
        this.maze = new MazeElement[width][length];
        this.mazeMask = new MazeElement[width][length];
        initMaze();
        mazeGenerate();
        checkCorners();
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
