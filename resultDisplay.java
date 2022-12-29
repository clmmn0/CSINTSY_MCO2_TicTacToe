import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class resultDisplay implements ActionListener {
    private JFrame screen;
    private JLabel lbl = new JLabel("msg", SwingConstants.CENTER);
    private JButton btn;
    private int nFirstPlayer = 0;

    public resultDisplay(char result, int n) {
        nFirstPlayer = n; // variable that determines the first player

        screen = new JFrame("Tic-Tac-Toe");
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screen.setSize(300, 200);				
        screen.setResizable(false);				
        screen.setLocationRelativeTo(null);			

        screen.setLayout(new GridLayout(3, 1));		

        setWinner(result); // sets the winner of the game

        screen.add(lbl);

        btn = new JButton("Play Again");
        btn.addActionListener(this);
        screen.add(btn);

        btn = new JButton("Exit");
        btn.addActionListener(this);
        screen.add(btn);

        screen.setVisible(true);
    }

    /*
     *	Sets the winner of the game
     */
    public void setWinner(char result){
        if(nFirstPlayer == 0){
            switch(result) {
                // player wins
                case 'X': {
                    lbl.setText("You won!");
                    break;
                }

                // computer wins
                case 'O': {
                    lbl.setText("You lost!");
                    break;
                }

                // draw
                case 'T': {
                    lbl.setText("Draw!");
                    break;
                }
            }
        }

        else{
            switch(result) {
                // computer wins
                case 'X': {
                    lbl.setText("You lost!");
                    break;
                }

                // player wins
                case 'O': {
                    lbl.setText("You won!");
                    break;
                }

                // draw
                case 'T': {
                    lbl.setText("Draw!");
                    break;
                }
            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Play Again")) {
            Initialization app = new Initialization(nFirstPlayer);
            screen.dispose();
        }

        else if(e.getActionCommand().equals("Exit")) {
            System.exit(1);
        }

    }
}
