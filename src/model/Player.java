package model;

import javax.xml.transform.sax.SAXSource;
import java.util.Arrays;

/**
 * This class gets and sets the position of the player.
 * It also allows player movement upon key input.
 * It also checks to see if the player position == cat position.
 */
public class Player {
    private int xPos;
    private int yPos;
    private boolean isDead;

    // Constructor
    Player(int xPos, int yPos, boolean isDead) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isDead = isDead;
    }


    // player can change a state depending on a condition
    void setState(boolean isDead){
        this.isDead = isDead;
    }

    public boolean getState(){
        return isDead;
    }

    int getXPos() {
        return xPos;
    }

    int getYPos() {
        return yPos;
    }

    // player has an origin position - reset player position
    public void setPostionPlayer(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    // gets position of the player in [x, y] form
    public int[] getPlayerPosition(){
        int[] pos = {getXPos(), getYPos()};
        return pos;
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
                System.out.println("Please try one of the following to update player position: w(up), s(down), a(left), d(right)");
                break;
        }
    }

    boolean isValidMove(MazeElement[][] board, String move) {
        switch (move) {
            case "w":
                if (board[yPos - 1][xPos] != MazeElement.WALL) {
                    System.out.println("bro why");
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

    // If player position == cat position player isDead true.
    public boolean checkCurrentPosition(boolean isDead, int[] catPos){
        return getPlayerPosition() == catPos;
    }

    // Prints the position of the player.
    public void printPlayerPos(){
        System.out.println("Player position is : " + Arrays.toString(getPlayerPosition()));
    }

    boolean isDead() {
        return isDead;
    }
}