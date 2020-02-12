package model;

public class Player {
    private int xPos;
    private int yPos;
    private boolean isDead;

    Player(int xPos, int yPos, boolean isDead) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isDead = isDead;
    }

    void setPostion(int x, int y) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }
}
