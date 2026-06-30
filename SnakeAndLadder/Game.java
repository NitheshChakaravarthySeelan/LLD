package SnakeAndLadder;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import SnakeAndLadder.enums.GameStatus;
import SnakeAndLadder.model.Board;
import SnakeAndLadder.model.Dice;
import SnakeAndLadder.model.Jump;
import SnakeAndLadder.model.Player;

// .poll() removes the head of the queue like the .remove() fn in the python.

public class Game {
    private final Board board;
    private final Queue<Player> players;
    private final Dice dice;
    private GameStatus status;
    private Player winner;

    public Game(Builder builder) {
        this.board = builder.board;
        this.players = builder.players;
        this.dice = builder.dice;
        this.status = GameStatus.STARTING;
    }

    public void play() {
        if (this.players.size() < 2) {
            System.out.println("Need more players to start");
            return;
        }

        this.status = GameStatus.RUNNING;
        System.out.println("The game is started");

        while (this.status == GameStatus.RUNNING) {
            Player currPlayer = this.players.poll();
            takeTurn(currPlayer);

            // if the game is not over or the player does not roll 6 then we need to re add them
            this.players.add(currPlayer);
        }

        if (this.winner != null) {
            System.out.println("Congrats to " + winner.getName() + " for winning!");
        }
        return;
    }
    
    private void takeTurn(Player player) {
        int roll = dice.roll();

        int currPos = player.getPosition();
        int nxtPos = currPos + roll;

        if (nxtPos > board.getSize()) {
            return;
        }

        // if the nxtPos leads to a winner we need to make the player winner and change the state
        if (nxtPos == board.getSize()) {
            player.setPosition(nxtPos);
            this.winner = player;
            this.status = GameStatus.FINISHED;
            System.out.println("The winner is " + player.getName());
            return;
        }

        // if not the above condition it will be like a empty cell or a jump cell
        int finalPos = board.getNextPosition(nxtPos);
        player.setPosition(finalPos);
        // if rolled a 6 player gets another turn
        takeTurn(player);
    }
    public static class Builder {
        private Board board;
        private Queue<Player> players;
        private Dice dice;

        public Builder setBoard(int boardSize, List<Jump> entities){
            this.board = new Board(boardSize, entities);
            return this;
        }

        public Builder setPlayers(List<String> playerName) {
            this.players = new LinkedList<>();
            for (String name: playerName) {
                this.players.add(new Player(name));
            }
            return this;
        }

        // we will pass in the whole dice in the main.java
        public Builder setDice(Dice dice) {
            this.dice = dice;
            return this;
        }

        public Game build() {
            if (board == null || players == null || dice == null) {
                throw new IllegalArgumentException();
            }
            return new Game(this);
        }
    }
}