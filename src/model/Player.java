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
    public Player(int xPos, int yPos, boolean isDead) {
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

    private int getXPos() {
        return xPos;
    }

    private int getYPos() {
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
    public void movement(String keyInput){
        switch (keyInput) {
            case "w":
                yPos += 1;
                break;

            case "s":
                yPos -= 1;
                break;

            case "a":
                xPos -= 1;
                break;

            case "d":
                xPos += 1;
                break;

            default:
                System.out.println("Please try one of the following to update player position: w(up), s(down), a(left), d(right)");
                break;
        }
    }


    // If player position == cat position player isDead true.
    public boolean checkCurrentPosition(boolean isDead, int[] catPos){
        return getPlayerPosition() == catPos;
    }

    // Prints the position of the player.
    public void printPlayerPos(){
        System.out.println("Player position is : " + Arrays.toString(getPlayerPosition()));
    }

}