package byog.lab5;

public class Position {
    private int xPos;
    private int yPos;

    public Position(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public Position() {
        xPos = 0;
        yPos = 0;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }
}
