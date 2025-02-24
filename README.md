# Tucil1_13523071
IQ Puzzler Pro Solver using Brute Force Algorithm

## 1. Program Description
This program is an IQ Puzzler Pro Solver that utilizes a brute force backtracking algorithm to find solutions for the IQ Puzzler Pro puzzle. The solver supports two modes:
- DEFAULT Mode: Uses a standard, fully empty board.
- CUSTOM Mode: Allows users to define their own board configurations, including blocked cells and fillable areas.

The program reads input from a `.txt` file specifying the board configuration and puzzle pieces. If a solution exists, the program displays the solution in the terminal and provides an option to save it as a text file and an image.

## 2. Requirements and Installation
### Requirements:
- Java Development Kit (JDK) version 11 or higher installed.
- No additional libraries are required; the program uses standard Java libraries.

### Installation:
1. Install the JDK from https://www.oracle.com/java/technologies/javase-jdk11-downloads.html or use OpenJDK.
2. Clone this repository or download the source code.

```
git clone https://github.com/your-repo/iq-puzzler-solver.git](https://github.com/adndax/Tucil1_13523071.git
```

## 3. Compile the Program
1. Navigate to the project directory.
2. Compile the Java files using the following command:

```
javac -d bin -sourcepath src src/Main.java
```

- `-d bin` specifies the output directory for compiled files.
- `src/` contains the source code files.

## 4. How to Run and Use the Program
1. Ensure the compiled files are in the `bin/` directory.
2. Run the program with:

```
java -cp bin Main
```

### Display
<img width="474" alt="Screenshot 2025-02-24 at 12 10 12" src="https://github.com/user-attachments/assets/b2f07ece-9db3-428b-b25d-0f59e0d83469" />

### Input File Format

```
N M P
MODE
puzzle_piece_1
puzzle_piece_2
...
```

## 5. Author
Adinda Putri
13523071

