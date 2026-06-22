# Low-Level Design (LLD) — My Machine Coding Practice

A personal collection of Low-Level Design problems I'm solving to prepare for machine coding rounds. Each problem is implemented in Java with a focus on OOP principles, SOLID design, and practical design patterns.

---

## What is Low-Level Design?

LLD is the process of translating high-level requirements into detailed class structures, interfaces, methods, and their interactions using object-oriented design principles. It sits between High-Level Design (architecture, scalability) and actual implementation.

![LLD vs HLD vs DSA](https://img.shields.io/badge/Focus-Classes%2C%20Patterns%2C%20SOLID-blue)
![Machine Coding](https://img.shields.io/badge/Format-90%20min%20working%20code-green)

**Machine Coding Round** = You get a problem, 60–120 minutes, produce **working, runnable code** with clean design. No databases, no distributed systems — just in-memory objects and well-structured code.

---

## Learning Roadmap

### Phase 1: OOP Fundamentals
- Classes & Objects, Enums, Interfaces
- Encapsulation, Abstraction, Inheritance, Polymorphism
- Association, Aggregation, Composition, Dependency

### Phase 2: SOLID Principles
| Principle | Meaning |
|-----------|---------|
| **S**ingle Responsibility | One class = one reason to change |
| **O**pen-Closed | Open for extension, closed for modification |
| **L**iskov Substitution | Subtypes must be replaceable for their base types |
| **I**nterface Segregation | Many specific interfaces > one general interface |
| **D**ependency Inversion | Depend on abstractions, not concretions |

### Phase 3: Design Patterns (Top 10 for Interviews)
| Pattern | Category | Used In |
|---------|----------|---------|
| **Strategy** | Behavioral | Parking fee calc, pricing, sorting |
| **State** | Behavioral | Vending Machine, Elevator, Order lifecycle |
| **Factory** | Creational | Vehicle creation, payment processors |
| **Observer** | Behavioral | Notifications, Pub-Sub systems |
| **Singleton** | Creational | Logger, Config, Cache |
| **Builder** | Creational | Complex object construction |
| **Adapter** | Structural | Third-party integrations |
| **Decorator** | Structural | Adding features dynamically |
| **Command** | Behavioral | Undo/Redo, Task queues |
| **Chain of Resp.** | Behavioral | Validators, Loggers, Middleware |

### Phase 4: Practice Problems

#### Tier 1 — Must Practice (Asked Everywhere)
| Problem | Key Patterns | Difficulty |
|---------|-------------|------------|
| Parking Lot | Strategy, Factory, Singleton | Easy |
| Vending Machine | State, Strategy | Easy |
| Snake & Ladder | Observer, Command | Medium |
| Splitwise | Strategy, Command | Medium |
| Elevator System | State, Strategy | Medium |
| BookMyShow (Movie Ticket) | Observer, Factory | Medium |
| Tic-Tac-Toe | Strategy | Easy |
| Task Management System | — | Easy |

#### Tier 2 — Frequently Asked
| Problem | Key Patterns | Difficulty |
|---------|-------------|------------|
| Logging Framework | Singleton, Chain of Resp | Medium |
| ATM Machine | State, Strategy | Medium |
| LRU Cache | — | Medium |
| Pub-Sub System | Observer | Medium |
| Library Management | Factory, Observer | Medium |
| Chess Game | Strategy, Command | Hard |
| Restaurant Ordering | State, Observer | Medium |
| Amazon Locker | Strategy | Medium |

#### Tier 3 — Advanced (Senior/SDE-3)
| Problem | Key Patterns | Difficulty |
|---------|-------------|------------|
| Rate Limiter | Strategy (Token Bucket, Sliding Window) | Hard |
| Notification Service | Factory, Template, Observer | Hard |
| Digital Wallet (Paytm) | Strategy, State | Hard |
| Food Delivery (Zomato) | State, Strategy, Observer | Hard |
| Auction System | Observer, State | Hard |
| Car Rental System | Strategy, Factory | Medium |
| Airline Management | Strategy, Observer | Hard |

---

## Folder Structure Convention

Every problem follows this consistent structure:

```
ProblemName/
├── README.md                  # Problem statement, design decisions, UML
├── Main.java                  # Driver/Demo — runs all flows
│
├── model/                     # Core entities (pure data, no business logic)
│   ├── Entity1.java
│   ├── Entity2.java
│   └── ...
│
├── enums/                     # Type-safe constants
│   ├── TypeEnum.java
│   └── StatusEnum.java
│
├── factory/                   # Object creation (Factory pattern)
│   └── EntityFactory.java
│
├── strategy/                  # Interchangeable algorithms
│   ├── SomeStrategy.java      # Interface
│   ├── StrategyA.java
│   └── StrategyB.java
│
├── state/                     # State transitions
│   ├── State.java             # Interface
│   ├── ConcreteState1.java
│   └── ConcreteState2.java
│
└── service/                   # Business logic orchestration
    └── SomeService.java
```

**Key rule**: Every package/folder = single responsibility. Models hold data, strategies swap behavior, services orchestrate.

---

## Step-by-Step Approach for Any LLD Problem

```
Time: 90 minutes
├── 1. Read & Clarify Requirements  (5–10 min)
├── 2. Identify Core Entities        (5 min)
├── 3. Design Class Structure        (10–15 min) ← Most critical
├── 4. Implement Core Models         (15–20 min)
├── 5. Implement Business Logic      (25–30 min)
├── 6. Write Driver/Demo             (10 min)
└── 7. Handle Edge Cases & Polish    (5–10 min)
```

### Step 1: Clarify Requirements
Ask the interviewer:
- What are the **core features** vs nice-to-haves?
- Who are the **users/actors**?
- Do we need **concurrency**?
- Is **persistence** expected or just in-memory?

### Step 2: Identify Entities
Find the **nouns** in the problem statement — they become your classes.

Example (Parking Lot):
- Vehicle, Car, Bike, Truck
- ParkingSpot, ParkingFloor, ParkingLot
- EntryGate, ExitGate, ParkingTicket
- Payment

### Step 3: Design Class Structure
Before writing code, sketch the design. This saves 30+ minutes of refactoring.

#### Draw a UML Class Diagram
Include in each problem's `README.md`:

```
┌─────────────────────┐          ┌─────────────────────┐
│    ParkingTicket     │          │      Vehicle        │
├─────────────────────┤          ├─────────────────────┤
│ - ticketId: String  │          │ - licenseNo: String │
│ - entryTime: Date   │◄─────────│ - vehicleSize: Enum │
│ - spotId: String    │  1     * ├─────────────────────┤
│ - floorId: String   │          │ + assignSpot()      │
│ - fee: double       │          └────────┬────────────┘
└─────────────────────┘                   │
                                    ┌──────┴──────┐
                                    │              │
                              ┌─────┴─────┐  ┌────┴─────┐
                              │    Car    │  │   Bike   │
                              └───────────┘  └──────────┘
```

**UML Relationships to know:**
- `◄──` Inheritance (extends)
- `<|..` Interface implementation
- `◆──` Composition (strong — part dies with whole)
- `◇──` Aggregation (weak — part exists independently)
- `──>` Association / Directional reference

### Step 4: Implement Core Models
- Data classes first (no logic)
- Enums for types/states
- Get relationships and constructors right

### Step 5: Implement Business Logic
- Services orchestrate the flow
- Strategies encapsulate varying algorithms
- State pattern for lifecycle management
- Keep methods small (< 20 lines), focused on one thing

### Step 6: Driver/Demo Code
A `Main.java` that:
- Sets up the system
- Runs the happy path
- Demonstrates edge cases
- Prints outputs so the interviewer sees it runs

### Step 7: Polish
- Handle invalid inputs
- Edge cases (full lot, insufficient balance, duplicate IDs)
- No magic strings/numbers — use enums
- Favor **composition over inheritance**
- Open/Closed — can you add a feature without modifying existing code?

---

## What Interviewers Evaluate

| Criteria | Weight | They Check |
|----------|--------|------------|
| Code Design & Structure | 30% | Class responsibilities, project structure, naming, readability |
| Working Functionality | 25% | Does it compile? Does it run? Are all features implemented? |
| Design Patterns & SOLID | 20% | Are patterns used appropriately? Is code extensible? |
| Extensibility | 15% | Can a new feature be added by writing new classes (not modifying existing ones)? |
| Edge Cases & Error Handling | 10% | Null checks, boundary conditions, graceful error messages |

---

## UML Diagramming Guide

For each problem, include these diagrams in the `README.md`:

### 1. Use Case Diagram (optional)
Shows actors and their interactions:
```
     ┌──────────────┐
     │    User      │
     └──────┬───────┘
            │
     ┌──────┴───────┐
     │ Park Vehicle │
     ├──────────────┤
     │  Pay & Exit  │
     └──────────────┘
```

### 2. Class Diagram (recommended)
Shows classes, attributes, methods, and relationships. Use text-based UML or an image.

### 3. Sequence Diagram (for complex flows)
Shows object interaction over time:
```
User  EntryGate  ParkingLot  ParkingSpot
 │       │           │           │
 ├──────>│           │           │
 │       ├──────────>│           │
 │       │           ├──────────>│
 │       │           │           ├── occupy()
 │       │           │<──────────│
 │       │<──────────│           │
 │<──────│           │           │
```

Tools: Draw.io, PlantUML, Mermaid.js, or just ASCII art.

---

## List of Problems

| # | Problem | Company Tags | Status |
|---|---------|-------------|--------|
| 1 | [Parking Lot](./ParkingSystem/) | Flipkart, Amazon, Uber, Google | ✅ Done |
| 2 | [Vending Machine](./VendingMachine/) | Google, Amazon, Microsoft | ✅ Done |
| 3 | Snake & Ladder | Amazon, Adobe, Microsoft | 📝 Planned |
| 4 | Splitwise | Flipkart, Cred | 📝 Planned |
| 5 | Elevator System | Amazon, Microsoft, Uber | 📝 Planned |
| 6 | Movie Ticket Booking | BookMyShow, Amazon, Paytm | 📝 Planned |
| 7 | Tic-Tac-Toe | Microsoft, Amazon | 📝 Planned |
| 8 | Logging Framework | Multiple | 📝 Planned |
| 9 | ATM Machine | Multiple | 📝 Planned |
| 10 | Pub-Sub System | Multiple | 📝 Planned |
| 11 | Library Management | Multiple | 📝 Planned |
| 12 | Chess Game | Amazon, Adobe, Facebook | 📝 Planned |
| 13 | Rate Limiter | Stripe, Google, Amazon | 📝 Planned |
| 14 | Notification Service | Amazon, Uber, LinkedIn | 📝 Planned |
| 15 | Digital Wallet | Paytm, PhonePe, Razorpay | 📝 Planned |

---

## How Each Problem README is Structured

Every problem's README follows this template:

```markdown
# Problem Name

## Problem Statement
Brief description of what to build.

## Requirements
- Feature 1
- Feature 2
- ...

## Entities Identified
- Entity1, Entity2, ...

## Class Diagram
[UML diagram — text or image]

## Design Patterns Used
- Pattern1: Why and where
- Pattern2: Why and where

## Project Structure
```
folder/
├── Main.java
├── model/
├── enums/
└── ...
```

## Key Design Decisions
- Why this pattern over another
- Trade-offs considered

## How to Run
Instructions to compile and run.

## Future Improvements
- What to add next
- How to extend
```

---

## Recommended Resources

### Books
- **Head First Design Patterns** — Best for learning patterns intuitively
- **Clean Code** by Robert C. Martin — Writing readable, maintainable code
- **Effective Java** by Joshua Bloch — Java-specific best practices

### Online
- [Refactoring Guru](https://refactoring.guru/) — Design patterns with examples
- [AlgoMaster LLD](https://algomaster.io/learn/lld) — Structured LLD curriculum
- [Work@Tech Machine Coding](https://workat.tech/machine-coding) — Practice problems
- [Low Level Design Mastery](https://www.lowleveldesignmastery.com/) — Guides + playground
- [LLDCoding](https://www.lldcoding.com/) — Interview-focused LLD training

### YouTube Playlists
- [Udit Agarwal — LLD Series](https://youtube.com/playlist?list=PL564gOx0bCLqTolRIHIsR2JPv11w8LESW)
- [Kushal Sharma — Machine Coding](https://youtube.com/playlist?list=PLliXPok7Zonm0trweRA2UeSTTLVYiPzNG)
- [Concept && Coding — LLD](https://youtube.com/@ConceptandCoding)

### GitHub Repos
- [ashishps1/awesome-low-level-design](https://github.com/ashishps1/awesome-low-level-design) (24k+ stars)
- [kumaransg/LLD](https://github.com/kumaransg/LLD) — Real interview problems
- [prasadgujar/low-level-design-primer](https://github.com/prasadgujar/low-level-design-primer)


