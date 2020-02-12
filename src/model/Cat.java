package model;

import java.util.Arrays;
import java.util.Random;

public class Cat {
    private int xPos;
    private int yPos;

    public Cat(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    private int getXPos() {
        return xPos;
    }

    private int getYPos() {
        return yPos;
    }

    // cat has an origin position - reset cat position
    public void setPostionCat(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    // gets position of the cat in [x, y] form
    public int[] getCatPosition(){
        int[] pos = {getXPos(), getYPos()};
        return pos;
    }

    // generates random numbers from 0-3 to satisfy one of the four conditions in cat movement
    public int generateRandomNum(){
        Random rand = new Random();
        int upperbound = 3;
        int random = rand.nextInt(upperbound);
        return random;
    }

    // saves position of cat's x and y coordinates upon calling random - use setPosition() to reset position.
    public void movement(int randNum){
        switch (randNum) {
            case 0:
                yPos += 1;
                break;

            case 1:
                yPos -= 1;
                break;

            case 2:
                xPos -= 1;
                break;

            case 3:
                xPos += 1;
                break;

            default:
                System.out.println("Please try one of the following to update cat position: 0(up), 1(down), 2(left), 3(right)");
                break;
        }
    }

    // Prints the position of the cat.
    public void printCatPos(){
        System.out.println("Cat position is : " + Arrays.toString(getCatPosition()));
    }
}
