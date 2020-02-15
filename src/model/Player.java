package model;

/**
 * This class gets and sets the position of the player.
 * It also allows player movement upon key input.
 * It also checks to see if the player position == cat position.
 */
class Player {
    private int xPos;
    private int yPos;

    // Constructor
    Player(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    int getXPos() {
        return xPos;
    }

    int getYPos() {
        return yPos;
    }

    // gets position of the player in [x, y] form
    int[] getPlayerPosition(){
        return new int[]{getXPos(), getYPos()};
    }

    // saves position of player's x and y coordinates upon keyInput - use setPosition() to reset position.
    void move(MazeElement[][] board, String keyInput){
        switch (keyInput) {
            case "w":
                if (board[yPos-1][xPos] != MazeElement.WALL){
                    board[yPos-1][xPos] = MazeElement.PLAYER;
                    board[yPos][xPos] = MazeElement.PASSAGE;
                    yPos--;
                }
                break;

            case "s":
                if (board[yPos+1][xPos] != MazeElement.WALL){
                    board[yPos+1][xPos] = MazeElement.PLAYER;
                    board[yPos][xPos] = MazeElement.PASSAGE;
                    yPos++;
                }
                break;

            case "a":
                if (board[yPos][xPos-1] != MazeElement.WALL){
                    board[yPos][xPos-1] = MazeElement.PLAYER;
                    board[yPos][xPos] = MazeElement.PASSAGE;
                    xPos--;
                }
                break;

            case "d":
                if (board[yPos][xPos+1] != MazeElement.WALL){
                    board[yPos][xPos+1] = MazeElement.PLAYER;
                    board[yPos][xPos] = MazeElement.PASSAGE;
                    xPos++;
                }
                break;

            default:
                break;
        }
    }

    boolean isValidMove(MazeElement[][] board, String move) {
        switch (move) {
            case "w":
                if (board[yPos - 1][xPos] != MazeElement.WALL) {
                    return true;
                }
                break;

            case "s":
                if (board[yPos + 1][xPos] != MazeElement.WALL) {
                    return true;
                }
                break;

            case "a":
                if (board[yPos][xPos - 1] != MazeElement.WALL) {
                    return true;

                }
                break;

            case "d":
                if (board[yPos][xPos + 1] != MazeElement.WALL) {
                    return true;
                }
                break;

            default:
                return false;
        }

        return false;
    }
}