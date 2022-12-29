import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class boardDisplay extends JFrame implements ActionListener {

    private Container contents;
    private JPanel whole = new JPanel();
    private JButton[][] squares = new JButton[3][3];
    public static char[][] board = new char[3][3]; 		// game board in 2D array
    public String PLAYER = "X";												// for display purposes
    public String COMPUTER = "O";
    public int level;
		public int level1Cnt = 0; 					// used for level1()	
		public int nFirstPlayer = 0;		 		// variable that indicates who the first player is	
    public char winner = '0';						// '0' = no winner yet
																				// 'X' = X wins
																				// 'O' = O wins
																				// 'T' = tie					

    public  boardDisplay(int level, int n) {
			super("TicTacToe");

			contents = getContentPane();
			contents.setLayout(new GridLayout(3, 3)); 	// creates a 3x3 board

			initScreen();
			initGame();
			this.level = level;

			//size and display
			setSize(300, 300);
			setResizable(false);
			setLocationRelativeTo(null);
			setVisible(true);
			setContentPane(whole);

			setFirstMove(n); // sets the first move of the first player
    }

		/*
		 *	Initializes game window
		 */
    public void initScreen(){
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                squares[i][j] = new JButton();
                contents.add(squares[i][j]);
                squares[i][j].setBackground(Color.WHITE);
                squares[i][j].addActionListener(this);
            }
        }

        whole.setLayout(new BorderLayout()); // creates a smooth board layout
        whole.add(contents, BorderLayout.CENTER); // puts the buttons in the middle of the window
    }

    /*
     * 	Initialize the game-board contents and the current states
     */
    public  void initGame() {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                board[row][col] = ' ';  // all cells empty
            }
        }

    }

	/*
	 *	Sets which player makes the first move
	 */
	public void setFirstMove(int n){
		nFirstPlayer = n;

		if(nFirstPlayer == 1){
			COMPUTER = "X";
			PLAYER = "O";
			if (level == 0) // random level
                randomLevel();
            else if (level == 1) { // level 1
                levelOne();
                level1Cnt++;
            }
            
            else if (level == 2) { // level 2
            	bestMove();
            }
		}
	}

	/*
	 * 	checks if the three parameters are equal
	 */
	public boolean areEqual (char a, char b, char c) {
			if (a == b && b == c && a != ' ')
					return true;
			else
					return false;
	}

	/*
	 * checks if there is a winner or if it is a tie
	 * X - X is the winner
	 * O - O is the winner
	 * T - it is a tie
	 */
	public char checkWinner(){
			char winner = '0';		// no winner
			int emptySpots = 0;
			
			// horizontal
			for (int i = 0; i < 3; i++) {
				if (areEqual(board[i][0], board[i][1], board[i][2]))
					winner = board[i][0];
			}
			
			// vertical
			for (int i = 0; i < 3; i++) {
				if (areEqual(board[0][i], board[1][i], board[2][i]))
					winner = board[0][i];
			}
			
			// diagonal
			if (areEqual(board[0][0], board[1][1], board[2][2])) {
					winner = board[0][0];
				}
			if (areEqual(board[2][0], board[1][1], board[0][2])) {
					winner = board[2][0];
				}

			// checks if there are still unoccupied spots
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j] == ' ')
								emptySpots++;
				}
			}
			
			// tie
			if (winner == '0' && emptySpots == 0)
				return 'T';
		
			else 
				return winner;	
	}

			/*
     *	Computer chooses random spot as next move
     */
		public void randomLevel() {
        Random rand = new Random();
        int randRow;
        int randCol;

        do {
					// generates random numbers for index of row and column
					randRow = rand.nextInt(3);
					randCol = rand.nextInt(3);
        } while ((board[randRow][randCol] == 'X') || (board[randRow][randCol] == 'O'));

        setSquare(randRow, randCol);
    }

	/*
   *	Checks if there is a square which upon being clicked would result to the 
	 *	computer's win.
   * 	This would also be used to avoid the human player from winning.
   */
	public boolean winningSquareOne(char pChar){
			boolean moveDone = false;

			// horizontal
			if(moveDone == false) {
					for (int i = 0; i < 3; i++) {
							if (board[i][0] == pChar && board[i][1] == pChar && board[i][2] == ' ') {
									setSquare(i, 2);
									return true;
							} else if (board[i][0] == pChar && board[i][2] == pChar && board[i][1] == ' ') {
									setSquare(i, 1);
									return true;
							} else if (board[i][2] == pChar && board[i][1] == pChar && board[i][0] == ' ') {
									setSquare(i, 0);
									return true;
							}
					}
			}

			// vertical
			if(moveDone == false) {
					for (int i = 0; i < 3; i++) {
							if (board[0][i] == pChar && board[1][i] == pChar && board[2][i] == ' ') {
									setSquare(2, i);
									return true;
							}
							else if (board[0][i] == pChar && board[2][i] == pChar && board[1][i] == ' ') {
									setSquare(1, i);
									return true;
							}
							else if (board[2][i] == pChar && board[1][i] == pChar  && board[0][i] == ' ') {
									setSquare(0, i);
									return true;
							}
					}
			}

			// diagonal
			if(moveDone == false) {
					// diagonal
					if (board[1][1] == pChar && board[2][2] == pChar && board[0][0] == ' ') {
							setSquare(0,0);
							return true;
					}
					else if (board[0][0] == pChar && board[2][2] == pChar && board[1][1] == ' ') {
							setSquare(1,1);
							return true;
					}
					else if (board[1][1] == pChar && board[0][0] == pChar && board[2][2] == ' ') {
							setSquare(2,2);
							return true;
					}
					else if (board[2][0] == pChar && board[1][1] == pChar && board[0][2] == ' ') {
							setSquare(0, 2);
							return true;
					}
					else if (board[0][2] == pChar && board[1][1] == pChar && board[2][0] == ' ') {
							setSquare(2, 0);
							return true;
					}
					else if (board[2][0] == pChar && board[0][2] == pChar && board[1][1] == ' ') {
							setSquare(1, 1);
							return true;
					}
			}

			return moveDone;
	}

    /*
     *	Checks the best move given that there are two available squares beside a 
		 *	specific player
     */
    public boolean levelOneMove(char currentChar){
        boolean moveDone = false;
        char pChar = ' '; 	// means that the square is available

        // diagonal
        if(moveDone == false) {
            if (board[1][1] == currentChar && board[0][0] == pChar && board[2][2] == pChar) {
                setSquare(0,0);
                return true;
            }
            else if (board[2][2] == currentChar && board[0][0] == pChar && board[1][1] == pChar) {
                setSquare(1,1);
                return true;
            }
            else if (board[0][0] == currentChar && board[1][1] == pChar && board[2][2] == pChar) {
                setSquare(1,1);
                return true;
            }
            else if (board[1][1] == currentChar && board[2][0] == pChar && board[0][2] == pChar) {
                setSquare(0, 2);
                return true;
            }
            else if (board[2][0] == currentChar && board[0][2] == pChar && board[1][1] == pChar) {
                setSquare(1, 1);
                return true;
            }
            else if (board[0][2] == currentChar && board[2][0] == pChar && board[1][1] == pChar) {
                setSquare(1, 1);
                return true;
            }
        }

        // horizontal
        if(moveDone == false) {
            for (int i = 0; i < 3; i++) {
                if (board[i][0] == currentChar && board[i][1] == pChar && board[i][2] == pChar) {
                    setSquare(i, 1);
                    return true;
                } else if (board[i][2] == currentChar && board[i][1] == pChar && board[i][0] == pChar) {
                    setSquare(i, 1);
                    return true;
                } else if (board[i][1] == currentChar && board[i][0] == pChar && board[i][2] == pChar) {
                    setSquare(i, 0);
                    return true;
                }
            }
        }

        // vertical
        if(moveDone == false) {
            for (int i = 0; i < 3; i++) {
                if (board[0][i] == currentChar && board[1][i] == pChar && board[2][i] == pChar) {
                    setSquare(1, i);
                    return true;
                }
                else if (board[2][i] == currentChar && board[1][i] == pChar && board[0][i] == pChar) {
                    setSquare(1, i);
                    return true;
                }
                else if (board[1][i] == currentChar && board[0][i] == pChar && board[2][i] == pChar) {
                    setSquare(0, i);
                    return true;
                }
            }
        }

        return moveDone;
    }

    /*
     *	The main method for a level 1 agent
     */
    public void levelOne(){
        boolean moveDone = false; // used to check if the computer has taken a move

        // Executes if this is the computer's first move
        if (level1Cnt == 0){
                if (board[1][1] == ' ') // middle tile is first taken since it has the most number of winning combinations
                    setSquare(1, 1);
                else
                    setSquare(0, 2); // the upper right tile is taken next because it has the second most number of winning combinations together with the other 3 corner squares
            }

        // Executes if this is not the computer's first move
        else if(level1Cnt != 0){
            // checks if there is a square that upon being clicked would result to a win of the computer.
            if(moveDone == false) {
                moveDone = winningSquareOne(COMPUTER.charAt(0));
            }

            // checks if the next move of the human player would result to a win, so that the computer can stop it
            if(moveDone == false) {
                moveDone = winningSquareOne(PLAYER.charAt(0));
            }

            // looks for the best move based on the computer's current board configuration
            if(moveDone == false) {
                moveDone = levelOneMove(COMPUTER.charAt(0));
            }

            // looks for the best move based on the human player's current board configuration
            if (moveDone == false) {
                moveDone = levelOneMove(PLAYER.charAt(0));
            }

            // just looks for an available square, since the computer did not find any best possible move based on the current board configuration
            if(moveDone == false) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (moveDone == false && board[i][j] == ' '){
                            setSquare(i, j);
                            moveDone = true;
                            i = 3; // this ends the 2 for-loops
                            j = 3;
                        }
                    }
                }
            }
        }

    }

    /*
     *	Formats the display of the recently clicked square
     */
    public void setSquare(int row, int col){
        squares[row][col].setText(COMPUTER); // executes if a square is clicked
        board[row][col] = COMPUTER.charAt(0);

        squares[row][col].setFont(new Font("Arial", Font.BOLD, 60)); // sets font size and style
        squares[row][col].setEnabled(false);
    }

    /*
     * Computer chooses its best move
     */
    public void bestMove() {
    	double bestScore = Double.NEGATIVE_INFINITY;
    	int row = 1;
    	int col = 1;
    	
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			// checks if spot is available
    			if (board[i][j] == ' ') {
    		        board[i][j] = COMPUTER.charAt(0);					// temporarily places move to check if it is the best move
    		        double score = minimax(board, 0, false);
    		        board[i][j] = ' ';												// undos move
    		        
    		        if (score > bestScore) {
    		          bestScore = score;
    		          row = i;
    		          col = j;
    		        }
    		    }
    		}
    	}
    	setSquare(row, col);
    }

   	/*
     * MiniMax Algorithm
     */
		public double minimax (char[][] board, int depth, boolean isMaximizing) {
    	char result = checkWinner();
    	
    	// if not a terminal state, check other moves
    	if (result != '0') {
    		if (result == COMPUTER.charAt(0))
    			return 1;
    		else if (result == PLAYER.charAt(0))
    			return -1;
    		else if (result == 'T')
    			return 0;
    	}
    	
    	if (isMaximizing) {
    		double bestScore = Double.NEGATIVE_INFINITY;
    		
    		for (int i = 0; i < 3; i++) {
    			for (int j = 0; j < 3; j++) {
						// checks if spot is available
    				if (board[i][j] == ' ') {
    					board[i][j] = COMPUTER.charAt(0);									// temporarily places move to check if it is the best move
    					double score = minimax(board, depth + 1, false);	
    					board[i][j] = ' ';																// undos move
    					bestScore = Math.max(score, bestScore);
    				}
    			}
    		}
    		return bestScore;
    	}
    	
    	else {
    		double bestScore = Double.POSITIVE_INFINITY;
    		
    		for (int i = 0; i < 3; i++) {
    			for (int j = 0; j < 3; j++) {
						// checks if spot is available
    				if (board[i][j] == ' ') {
    					board[i][j] = PLAYER.charAt(0);									// temporarily places move to check if it is the best move
    					double score = minimax(board, depth + 1, true);
    					board[i][j] = ' ';															// undos move
    					bestScore = Math.min(score, bestScore);
    				}
    			}
    		}
    		return bestScore;
    	}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			// checks if the player made a move or clicked a spot
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(source == squares[i][j]){
						processClick(i, j);
						}
					}
				}
		}

		public void processClick(int i, int j){
			squares[i][j].setText(PLAYER); // executes if a square is clicked
			squares[i][j].setFont(new Font("Arial", Font.BOLD, 60)); // sets font size and style
			squares[i][j].setEnabled(false);
			board[i][j] = PLAYER.charAt(0);
				
			winner = checkWinner();
			
			// checks if game has ended upon player's move
			if(winner != '0'){ 
					this.dispose();
					resultDisplay rd = new resultDisplay(winner, nFirstPlayer);
			}
			//Computer's turn to move
			else {
				// random level
				if (winner == '0' && level == 0)
						randomLevel();
						
				// level 1
				else if (winner == '0' && level == 1) {
						levelOne();
						level1Cnt++;
				}
			
				// level 2
				else if (winner == '0' && level == 2) {
					bestMove();
				}
				
				winner = checkWinner();

				// checks if game has ended upon computer's move
				if (winner != '0') {
						this.dispose();
						resultDisplay rd = new resultDisplay(winner, nFirstPlayer);
				}
			}
    }
}
