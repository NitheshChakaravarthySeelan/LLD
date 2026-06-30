package SnakeAndLadder.model;

public class Ladder extends Jump {

    public Ladder(int bottom, int up) {
        super(bottom, up);

        if (bottom >= up) {
            throw new IllegalArgumentException("The bottom should be less than up");
        }
    }
}