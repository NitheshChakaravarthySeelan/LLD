package SnakeAndLadder.model;

public class Snake extends Jump{
    
    public Snake(int head, int tail) {
        super(head, tail);

        if (head <= tail) {
            throw new IllegalArgumentException("Head must be greater than tail");
        }
    }
}
