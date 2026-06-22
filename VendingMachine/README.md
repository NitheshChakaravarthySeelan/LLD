# Vending Machine - Low Level Design (LLD)

This repository contains the Low-Level Design (LLD) and implementation of a modern Vending Machine in Java. This project serves as an excellent resource for understanding core Object-Oriented Programming (OOP) concepts, SOLID principles, and crucial Design Patterns.

## 🎯 Problem Statement
Design a Vending Machine that supports the following functionalities:
1. It has an inventory of products with varying prices and quantities.
2. It accepts different denominations of money (Notes/Coins).
3. A user can select a product, and if the inserted money is sufficient, it dispenses the product and returns the change.
4. A user can cancel the transaction at any point before dispensing, and the machine will refund the full amount.
5. The machine behaves differently depending on its current state (e.g., you cannot dispense a product while it is waiting for money).

## 🏗️ Design Patterns Used

### 1. State Design Pattern (Core)
The State Pattern is the heart of this system. A Vending Machine is a classic finite state machine. Instead of using complex `if-else` blocks to determine what should happen when a button is pressed, the behavior is delegated to specific state classes:
- **`IdleState`**: The machine is waiting for a user to interact.
- **`HasMoneyState`**: The user has inserted money, and the machine is waiting for product selection or more money.
- **`DispensingState`**: The machine is processing the transaction and dropping the item.

### 2. Single Responsibility Principle (SRP)
Every class has one, and only one, reason to change:
- `Inventory`: Strictly manages the stock (adding, removing, checking availability).
- `Vault`: Manages the permanent physical cash inside the machine.
- `Transaction`: Manages the *current* user's session (their cart/bag and currently inserted money).
- `VendingMachine`: Acts as the central orchestrator (Context) but delegates actual work to the state and transaction modules.

## 📂 Recommended Project Structure

To make this codebase easy for others to read and learn from, the code is structured into logical packages (domains):

```text
VendingMachine/
├── VendingMachine.java      # Main orchestrator / Context class
├── Inventory.java           # Manages product stock
├── Product.java             # POJO representing an item
├── Slot.java                # Represents a physical slot holding a Product
├── Bag.java                 # Represents the user's shopping cart for the current session
│
├── state/                   # STATE PATTERN IMPLEMENTATION
│   ├── State.java           # Interface defining all possible user actions
│   ├── IdleState.java       
│   ├── HasMoneyState.java   
│   └── DispenseState.java   
│
├── transactions/            # TRANSACTION & MONEY MANAGEMENT
│   ├── Transaction.java     # Current session money and selected items
│   └── Vault.java           # Permanent machine cash storage
│
└── enums/                   # CONSTANTS
    ├── Note.java            # Denominations of money (10, 20, 50, 100)
    └── Status.java          
```

## 🧠 How to Read This Codebase
If you are learning from this repository, we recommend reading the files in this order:
1. **The Entities**: Start with `Product.java`, `Slot.java`, and `Note.java`. Understand the basic building blocks.
2. **The Storage**: Read `Inventory.java` and `Vault.java`. See how the machine holds physical items and money.
3. **The State Interface**: Look at `state/State.java`. Understand the "verbs" (what can a user do?).
4. **The Orchestrator**: Read `VendingMachine.java`. See how it holds references to `Inventory`, `Vault`, and the `currentState`.
5. **The Implementations**: Finally, read `IdleState.java` and others to see how the state handles user input and transitions the machine.

## 🚀 Future Improvements
- Add `Coin` support alongside `Note`.
- Implement an admin interface to refill the inventory.
- Implement Card payment strategies (Strategy Pattern).
