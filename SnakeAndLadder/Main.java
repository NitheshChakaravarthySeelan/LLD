package SnakeAndLadder;

import java.util.ArrayList;
import java.util.List;

import SnakeAndLadder.model.Dice;
import SnakeAndLadder.model.Jump;
import SnakeAndLadder.model.Ladder;
import SnakeAndLadder.model.Snake;

public class Main {
    public static void main(String[] args) {
        // Create Snakes
        List<Jump> entities = new ArrayList<>();
        entities.add(new Snake(99, 54));
        entities.add(new Snake(70, 55));
        entities.add(new Snake(52, 42));
        entities.add(new Snake(25, 2));
        entities.add(new Snake(95, 72));

        // Create Ladders
        entities.add(new Ladder(6, 25));
        entities.add(new Ladder(11, 40));
        entities.add(new Ladder(60, 85));
        entities.add(new Ladder(46, 90));
        entities.add(new Ladder(17, 69));

        // Create Players
        List<String> players = new ArrayList<>();
        players.add("Alice");
        players.add("Bob");
        players.add("Charlie");

        // Create Dice
        Dice dice = new Dice(1, 6);

        // Build Game
        Game game = new Game.Builder()
                .setBoard(100, entities)
                .setPlayers(players)
                .setDice(dice)
                .build();

        // Play Game
        game.play();
    }
}
