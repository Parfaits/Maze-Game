package model;

/**
 * This class gets and sets the position of the player.
 * It also allows player movement upon key input.
 * It also checks to see if the player position == cat position.
 */
class Player {
    private int xPos;
    private int yPos;
    private boolean isDead;

    Player(int xPos, int yPos, boolean isDead) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isDead = isDead;
    }

    // saves position of player's x and y coordinates upon keyInput - use setPosition() to reset position.

    void move(MazeElement[][] board, String keyInput){
        switch (keyInput) {
            case "w":
                if (board[yPos-1][xPos] != MazeElement.WALL){
                    if (board[yPos-1][xPos] == MazeElement.CAT) {
                        isDead = true;
                    }
                    board[yPos-1][xPos] = MazeElement.PLAYER;
                    board[yPos][xPos] = MazeElement.PASSAGE;
                    yPos--;
                }
                break;

            case "s":
                if (board[yPos+1][xPos] != MazeElement.WALL){
                    if (board[yPos+1][xPos] == MazeElement.CAT) {
                        isDead = true;
                    }
                    board[yPos+1][xPos] = MazeElement.PLAYER;
                    board[yPos][xPos] = MazeElement.PASSAGE;
                    yPos++;
                }
                break;

            case "a":
                if (board[yPos][xPos-1] != MazeElement.WALL){
                    if (board[yPos][xPos-1] == MazeElement.CAT) {
                        isDead = true;
                    }
                    board[yPos][xPos-1] = MazeElement.PLAYER;
                    board[yPos][xPos] = MazeElement.PASSAGE;
                    xPos--;
                }
                break;

            case "d":
                if (board[yPos][xPos+1] != MazeElement.WALL){
                    if (board[yPos][xPos+1] == MazeElement.CAT) {
                        isDead = true;
                    }
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

    int getXPos() {
        return xPos;
    }

    int getYPos() {
        return yPos;
    }

    int[] getPlayerPosition(){
        return new int[]{getXPos(), getYPos()};
    }

    boolean isDead() {
        return isDead;
    }

    void setDead() {
        isDead = true;
    }
}