package model;

public class Cat {
    private int xPos;
    private int yPos;

    public Cat(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    // player has an origin position
    public void setPostion(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    private int getXPos() {
        return xPos;
    }

    private int getYPos() {
        return yPos;
    }

    // cat can move up - call random on it
    public int moveUP(){
        return getYPos() + 1;
    }

    // cat can move down - call random on it
    public int moveDown(){
        return getYPos() - 1;
    }

    // cat can move left - call random on it
    public int moveLeft(){
        return getXPos() - 1;
    }

    // cat can move right - call random on it
    public int moveRight(){
        return getXPos() + 1;
    }

    public void printCat(){
        System.out.println("This is position x " + getXPos());
        System.out.println("This is position y " + getYPos());
    }
}
