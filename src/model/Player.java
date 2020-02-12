package model;

public class Player {
    private int xPos;
    private int yPos;
    private boolean state;

    public Player(int xPos, int yPos, boolean state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.state = state;
    }

    public void setPostion(int x, int y) {
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
