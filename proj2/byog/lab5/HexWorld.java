package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 28;
    private static final int HEIGHT = 30;
    private static TETile[][] world;


    public static void addHexagon(int side, Position p, TETile[][] world, TETile style) {
        if (side < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2");
        }
        if(!isFullHex(p, side)) {
            return;
        }
        int xPos = p.getX();
        int yPos = p.getY();
        for (int a = yPos; a < yPos + side * 2; a++) {
            int element = getNumElement(a - yPos + 1, side);
            int space = getNumSpacePerSide(a -yPos + 1, side);
            for (int b = xPos + space; b < xPos + space + element; b++) {
                TETile changingStyle = TETile.colorVariant(style, 50, 50, 50, new Random());
                world[b][a] = changingStyle;
            }
        }
    }

    /**
     * Returns the number of elements needed on a certain level given the side.
     * @param level - starts at 1
     * @param side - starts at 2
     * @return the number of element on that given level
     */
    public static int getNumElement(int level, int side) {
        if (level <= side) {
            return side + (level - 1) * 2;
        }
        return 5 * side - 2 * level; // 3 * side - 2 * (level - side)
    }

    /**
     * Returns the number of nothing/spaces on each side for each row.
     * @param level - starts at 1
     * @param side - starts at 2
     * @return the number of nothing/spaces on each side for each row
     */
    public static int getNumSpacePerSide(int level, int side) {
        return (3 * side - 2 - getNumElement(level, side)) / 2;
    }

    /**
     * Returns a random style of the tile.
     * @source RandomWorldDemo.java - private static TETile randomTile().
     * @return a random style tile
     */
    private static TETile getRandomTile() {
        Random r = new Random();
        int tileNum = r.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.FLOWER;
            case 1: return Tileset.MOUNTAIN;
            case 2: return Tileset.TREE;
            case 3: return Tileset.GRASS;
            case 4: return Tileset.SAND;
            default: return Tileset.SAND;
        }
    }

    /**
     * Returns a new starting position (left bottom corner) of the left neighbor
     * @param curr - the position of which we will find the left neighbor
     * @return position of the left neighbor
     */
    private static Position getLeftNeighbor(Position curr, int side) {
        int newX = curr.getX() - getNumElement(0, side) - getNumSpacePerSide(0, side) - 1;
        int newY = curr.getY() - side;
        return new Position(newX, newY);
    }

    /**
     * Returns a new starting position (right bottom corner) of the right neighbor
     * @param curr - the position of which we will find the right neighbor
     * @return position of the right neighbor
     */
    private static Position getRightBottomNeighbor(Position curr, int side) {
        int newX = curr.getX() + getNumElement(0, side) + getNumSpacePerSide(0, side) + 1;
        int newY = curr.getY() - side;
        return new Position(newX, newY);
    }

    /**
     * Returns a new starting position (bottom corner) of the bottom neighbor
     * @param curr - the position of which we will find the bottom neighbor
     * @return position of the bottom neighbor
     */
    private static Position getBottomNeighbor(Position curr, int side) {
        int newX = curr.getX();
        int newY = curr.getY() - side * 2;
        return new Position(newX, newY);
    }

    /**
     * Given a position, return true if this Hex can fit in the map.
     */
    private static boolean isFullHex(Position p, int side) {
        int x = p.getX();
        int y = p.getY();
        // ensure the left bottom corner is in range
        if (x >= 0 && x <= WIDTH && y >= 0 && y <= HEIGHT) {
            // ensure the right bottom corner is in range
            return ((x + 3 * side - 2) <= WIDTH) && (y + side * 2 <= HEIGHT);
        }
        return false;
    }

    /**
     * Draw a vertical group of Hexes given a starting position at the right top
     */
    private static void drawRandomVerticalHexes(Position startP, int side, int n) {
        while (n > 0 && isFullHex(startP, side)) {
            TETile randomStyle = getRandomTile();
            addHexagon(side, startP, world, randomStyle);
            startP = getLeftNeighbor(startP, side);
            n--;
        }
    }

    private static Position updatePos(Position curr, int side) {
        Position rightBottom = getRightBottomNeighbor(curr, side);
        if (isFullHex(rightBottom, side)) {
            return rightBottom;
        }
        Position bottom = getBottomNeighbor(curr, side);
        if (isFullHex(bottom, side)) {
            return bottom;
        }
        return curr;
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize background tiles
        world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // add Hexagon
        int side = 3;
        Position pos = new Position((WIDTH - 3 * side + 2) / 2, HEIGHT - side * 2);

        for (int i = 0; i < 5; i++) {
            int count = i + 3;
            if (i >= 3) {
                count = side - i + 4;
            }
            drawRandomVerticalHexes(pos, side, count);
            pos = updatePos(pos, side);
        }

        // draw the world
        ter.renderFrame(world);
    }
}
