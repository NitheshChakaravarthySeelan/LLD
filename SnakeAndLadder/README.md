# Snake and Ladder - Low Level Design (LLD)

This is a comprehensive Object-Oriented implementation of the classic Snake and Ladder game in Java. This document provides a quick overview and revision points, perfect for revisiting before an interview.

## Core Entities and Responsibilities

### 1. `Board`
- Represents the physical board of the game (e.g., 100 cells).
- Contains the `size` of the board.
- Manages the cells that have Snakes or Ladders using a `Map<Integer, Integer> snakesAndLadder`. This maps the start position of a jump to its end position.
- Exposes `getNextPosition(position)` which resolves any chain of jumps or returns the same position if it's a normal cell.
- Throws an exception if a Snake/Ladder starts or ends outside the board boundaries or if multiple entities share the same cell.

### 2. `Jump` (Abstract/Base Class)
- Represents an entity that moves a player from a `start` position to an `end` position.
- Base class for both `Snake` and `Ladder`.
- Contains `start` and `end` coordinates.

### 3. `Snake` (extends `Jump`)
- Represents a downward jump.
- Contains validation logic: `head` (start) must be strictly greater than `tail` (end).

### 4. `Ladder` (extends `Jump`)
- Represents an upward jump.
- Contains validation logic: `bottom` (start) must be strictly less than `top` (end).

### 5. `Player`
- Represents a user playing the game.
- Contains the player's `name` and their current `position` on the board.
- Provides getters and setters for the position.

### 6. `Dice`
- Simulates a real-world dice.
- Configurable `minValue` and `maxValue` (usually 1 and 6).
- The `roll()` method generates a random integer between min and max bounds.

### 7. `Game`
- Central controller/orchestrator of the game.
- Manages the `Board`, a `Queue<Player>` (to handle turns in a round-robin fashion), and the `Dice`.
- Maintains the current `status` of the game using the `GameStatus` enum (`STARTING`, `RUNNING`, `FINISHED`).
- Uses the **Builder Pattern** for object creation, ensuring the Game is properly configured with a Board, Players, and Dice before it starts.
- Handles the core game loop in the `play()` method:
  - Dequeues the current player.
  - Rolls the dice and calculates the next position.
  - Checks for out-of-bounds moves.
  - Resolves any Snakes or Ladders via the board.
  - If a player rolls a 6, they get an extra turn (simulated using recursion or looping in `takeTurn`).
  - Checks if the player reaches the end of the board to declare the winner.
  - Re-enqueues the player if the game is not yet won.

## Design Patterns Used

1. **Builder Pattern**
   - *Where:* `Game.Builder`
   - *Why:* The `Game` class requires multiple complex components (Board, Players, Dice) to be initialized correctly. The Builder pattern simplifies construction, provides a fluent API, and prevents incomplete instantiation of a Game object.

2. **Inheritance & Polymorphism**
   - *Where:* `Jump` class and its subclasses `Snake` and `Ladder`.
   - *Why:* Both snakes and ladders share the same core behavior (moving a player from A to B). By using a common base class, the `Board` only needs to know about `Jump` objects, satisfying the **Liskov Substitution Principle (LSP)** and **Open-Closed Principle (OCP)**. 

## Key Interview Talking Points

- **Queue for Turns:** We use a `Queue` (specifically a `LinkedList`) for players. We `poll()` the current player, process their turn, and if they haven't won, we `add()` them back to the end of the queue. This is a very clean way to manage turn-based games without keeping track of a current player index.
- **Handling 6s:** If a player rolls a 6, they get another turn. In this implementation, this is handled gracefully in `takeTurn(Player player)` by recursively calling itself or updating state without putting the player back in the queue yet.
- **Extensibility:** 
  - To add a new type of entity (e.g., a "Frog" that jumps exactly 3 spaces), you simply extend `Jump` and add it to the game setup without modifying the `Board` or `Game` loop.
  - Multiple dice can be supported by altering the `Dice` class to roll multiple times and return the sum, keeping the `Game` class decoupled from the dice logic.
- **Validation:** Validation is done inside constructors (e.g., checking if Snake head > tail, checking if Board entities are within bounds) ensuring objects are always in a valid state (**Fail-fast**).

## How to Run

1. Compile the java files into a `target` directory:
   ```bash
   javac -d target model/*.java enums/*.java Game.java Main.java
   ```
2. Run the `Main` class:
   ```bash
   java -cp target SnakeAndLadder.Main
   ```
