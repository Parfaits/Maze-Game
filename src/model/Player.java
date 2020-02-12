package model;

// player has an origin position
// player can move up : W
// player can move down : S
// player can move left : A
// player can move right : D

public class Player {
    private int xPos;
    private int yPos;
    private boolean isDead;

    Player(int xPos, int yPos, boolean isDead) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isDead = isDead;
    }

    private int getXPos() {
        return xPos;
    }

    private int getYPos() {
        return yPos;
    }

    void setPostion(int x, int y) {
        this.xPos = xPos;
        this.yPos = yPos;
    }


    public int moveUP(){
        return getYPos() + 1;
    }

    public int moveDown(){
        return getYPos() - 1;
    }

    public int moveLeft(){
        return getXPos() - 1;
    }

    public int moveRight(){
        return getXPos() + 1;
    }

    public void printPlayer(){
        System.out.println("This is position x " + getXPos());
        System.out.println("This is position y " + getYPos());
    }

}