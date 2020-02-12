package model;

public class Player {
    private int xPos;
    private int yPos;
    private boolean isDead;

    public Player(int xPos, int yPos, boolean isDead) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.isDead = isDead;
    }

    // player has an origin position - reset player position
    public void setPostion(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    // player can change a state depending on a condition
    void setState(boolean isDead){
        this.isDead = isDead;
    }

    private int getXPos() {
        return xPos;
    }

    private int getYPos() {
        return yPos;
    }

    public boolean getState(){
        return isDead;
    }

    // saves position of player's x and y coordinates upon keyInput
    public void movement(String keyInput){
        if (keyInput.equals("w")){
            yPos += 1;
        }

        if (keyInput.equals("s")){
            yPos += getYPos() - 1;
        }

        if (keyInput.equals("a")){
            xPos =+ -1;
        }

        if (keyInput.equals("d")){
            xPos += 1;
        }
    }


    public void printPlayerPos(){
        System.out.println("This is position x " + getXPos());
        System.out.println("This is position y " + getYPos());
    }

}