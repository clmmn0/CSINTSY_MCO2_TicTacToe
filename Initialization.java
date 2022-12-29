import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Initialization implements ActionListener {
	private JFrame menu;
	private JComboBox cb;
	private JLabel lbl;
	private JButton btn;
  private int nFirstPlayer = 0;
	private String[] levels = {"Random", "Level 1", "Level 2"};
	
	public Initialization(int n) {
		// sets the first player
		setFirstPlayer(n); 			

		// initializes window
		menu = new JFrame("Tic-Tac-Toe");
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(300, 200);			
		menu.setResizable(false);		
		menu.setLocationRelativeTo(null);	
		menu.setLayout(new GridLayout(3, 1));	
		
		// adds label
		lbl = new JLabel("Choose level of agent:", SwingConstants.CENTER);
		menu.add(lbl);
    
		// allows player to choose level
		cb = new JComboBox(levels);
		cb.setSelectedIndex(0);
		cb.addActionListener(this);
		
		cb.setMaximumSize(cb.getPreferredSize());
		cb.setAlignmentX(Component.CENTER_ALIGNMENT);
		menu.add(cb);

		// start game button
		btn = new JButton("Start");			
		btn.addActionListener(this);
		menu.add(btn);

		menu.setVisible(true);
	}
	
	/*
	 *	Sets which player makes the first move
	 */
	public void setFirstPlayer(int n){
        if(n == 0)
            nFirstPlayer = 1;
        else
            nFirstPlayer = 0;
    }

	public void actionPerformed(ActionEvent e) {
			String choice = (String)cb.getSelectedItem();
			
			if(e.getActionCommand().equals("Start")) {
					menu.dispose();
					
					switch(choice) {
						case "Random": {		// random level
							boardDisplay game = new boardDisplay(0, nFirstPlayer);
							break;
						}
						case "Level 1": {		// level 1
							boardDisplay game = new boardDisplay(1, nFirstPlayer);
							break;
						}
						case "Level 2": {		// level 2
							boardDisplay game = new boardDisplay(2, nFirstPlayer);
							break;
						}
					}
					
			}
	}
}