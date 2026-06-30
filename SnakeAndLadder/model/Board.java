package SnakeAndLadder.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final int size;
    private final Map<Integer, Integer> snakesAndLadder;
    
    public Board(int size, List<Jump> entities) {
        this.size = size;

        this.snakesAndLadder = new HashMap<>();

        for (Jump entity: entities) {
            int start = entity.getStart();
            int end = entity.getEnd();

            if (start < 1 || start > size || end < 1 || end > size) {
                throw new IllegalArgumentException();
            }
            // if there is both the same snake and ladder sharing the same cell its false
            if (snakesAndLadder.containsKey(entities)) {
                throw new IllegalArgumentException();
            }

            snakesAndLadder.put(start, end);
        }
    }

    public int getSize() {
        return size;
    }

    public int getNextPosition(int position) {
        return snakesAndLadder.getOrDefault(position, position);
    }
}