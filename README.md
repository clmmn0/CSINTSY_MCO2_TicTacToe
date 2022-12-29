# CSINTSY_MCO2_TicTacToe

A Java-based program implementation of the game TicTacToe which uses an intelligent agent. The agent has three levels of intelligence: random, level 1, and level 2 which are explained below:
- Random: the agent performs random moves. It does not use any logical algorithm, nor any heuristic approach.
- Level 1: the agent bases its moves on a hard coded table wherein it checks which possible move could be the best move given the current board configuration. It does not take into consideration future moves, but only focuses on the current state of the board.
- Level 2: the agent determines its best move by using the heuristic approach, Minimax Algorithm. Using this algorithm, the agent recursively checks all possible states until it reaches a terminal state. Once it reaches a terminal state, it will return the score (1 if the computer wins; -1 if the player wins; and 0 if it is a draw) as it backtracks. The possible state with the best score will be determined by comparing each possible state’s score with one another. Once all possible states are checked, the best move remains and is chosen by the agent.

# How to Run the Program
1. Clone this repository.
2. Navigate to the cloned version of this repository.
3. Open the command prompt.
4. Type and run `javac Main.java` in the command prompt. An instance of the Main class must be created upon running this prompt.
5. Type and run `java Main` in the command prompt. 
6. Wait for a window to pop up showing the game.
7. Choose the intelligence level of the agent.
8. Play the game.

You may also run the program using applications like Visual Studio Code.
