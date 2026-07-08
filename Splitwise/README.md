# Splitwise - Low Level Design (LLD)

This repository contains the Low-Level Design and Implementation of a simplified **Splitwise** application in Java. Splitwise is an expense-sharing application that allows users to add expenses and split them among a group of people. It maintains a ledger (balance sheet) of who owes whom and provides a way to settle debts.

## Problem Statement

Design and implement a simplified version of Splitwise. The application should be able to manage users, groups, and expenses. It should allow splitting an expense in different ways and keep track of user balances.

### Core Requirements
1. **Users:** Create users with a unique ID, name, and email.
2. **Groups:** Users can form groups to share expenses.
3. **Expenses:** Users can add expenses. An expense can be split in multiple ways.
4. **Split Types:** 
   - **EQUAL:** The expense is divided equally among all participants.
   - **EXACT:** Each participant owes a specific exact amount.
   - **PERCENTAGE:** Each participant owes a specific percentage of the total amount.
5. **Balance Sheet:** The system must maintain an ongoing global balance sheet tracking exactly how much each user owes every other user.
6. **Simplify Debts:** The system should be able to minimize the number of transactions needed to settle all debts within a specific group, executing an on-the-fly greedy algorithm to generate simplified transactions.
7. **Settle Up:** Users should be able to settle their balances (pay back what they owe).

## Architecture and Design Patterns

This LLD utilizes several standard Object-Oriented Design Patterns to ensure the code is modular, extensible, and maintainable.

### 1. Strategy Pattern
The logic for splitting an expense (Equal, Exact, Percentage) varies. Instead of writing massive `if-else` blocks in the core service, we utilize the **Strategy Pattern**.
*   `SplitStrategy` (Interface): Defines a generic `calculateSplit` method.
*   `EqualSplitStrategy`, `ExactSplitStrategy`, `PercentageSplitStrategy` (Concrete classes): Implement the specific mathematical logic for each split type.

### 2. Factory Pattern
To cleanly instantiate the correct `SplitStrategy` based on the user's input (`StrategyType` enum), a **Factory Pattern** is used.
*   `SplitStrategyFactory`: Takes a `StrategyType` and returns the corresponding `SplitStrategy` instance.

### 3. Facade Pattern
The `SplitwiseService` acts as a facade. It provides simplified, high-level methods (`addExpense`, `settleUp`, `printBalances`) for the client (`Main` class), abstracting away the complex interactions between the Models, Strategies, and the Balance Sheet.

## Core Entities / Models

*   **`User`**: Represents an individual using the application.
*   **`Group`**: Represents a collection of users sharing expenses.
*   **`Expences`**: Represents a single transaction paid by one user and shared by multiple.
*   **`Split`**: Represents a user and the exact amount they owe for a particular expense.
*   **`BalanceSheet`**: Central ledger storing a Map of `User -> (User -> Amount)`. Tracks raw debts globally.
*   **`Transaction`**: Represents a simplified cash transfer (`debtor`, `creditor`, `amount`) generated dynamically by the `Simplify Debts` algorithm.

## Directory Structure
```
Splitwise/
├── enums/
│   └── StrategyType.java
├── factory/
│   └── SplitStrategyFactory.java
├── model/
│   ├── BalanceSheet.java
│   ├── Expences.java
│   ├── Group.java
│   ├── Split.java
│   ├── Transaction.java
│   └── User.java
├── service/
│   └── SplitwiseService.java
├── strategy/
│   ├── EqualSplitStrategy.java
│   ├── ExactSplitStrategy.java
│   ├── PercentageSplitStrategy.java
│   └── SplitStrategy.java
└── Main.java
```

## How to Run

Compile all Java files and run the `Main` class.

From the `Splitwise` directory, you can run the following command in your terminal:
```bash
javac -d . $(find . -name "*.java") && java Splitwise.Main
```

### Example Execution Flow
1. Users Alice, Bob, and Charlie are created and added to a group.
2. **Equal Expense:** Alice pays $300, split equally. Bob and Charlie each owe Alice $100.
3. **Exact Expense:** Bob pays $200. Exact split: Alice=$50, Bob=$50, Charlie=$100.
4. **Percentage Expense:** Charlie pays $100. Percentage split: Alice=20%, Bob=30%, Charlie=50%.
5. **Print Balances:** Displays the raw global net balances across all users.
6. **Simplify Debts:** Call `simplifyGroupDebts(groupId)` to dynamically extract group expenses and generate the minimum number of transactions needed to settle the group using a greedy matching algorithm.
7. **Settle Up:** Users can settle balances with each other, adjusting the global ledger automatically.

---
*This is a standard Low Level Design practice implementation useful for grasping OOPs concepts, Design Patterns, and clean coding principles for tech interviews.*
