package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import i_o.Entry;

public class GameOver extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameOver(int streak, String pokemonName) {
		
		JLabel roundStreak = new JLabel("Round vinti: " + streak,JLabel.CENTER);
		roundStreak.setFont(new Font("Lucida Console",Font.BOLD,35));
		roundStreak.setPreferredSize(new Dimension(900,200));
		
		JLabel gameOver = new JLabel("GAME OVER",JLabel.CENTER);
		gameOver.setFont(new Font("Lucida Console",Font.BOLD,50));
		
		JDialog gameOverScreen = new JDialog();
		gameOverScreen.setLayout(new BorderLayout());
		gameOverScreen.add(roundStreak,BorderLayout.NORTH);
		gameOverScreen.add(gameOver,BorderLayout.CENTER);
		gameOverScreen.setSize(900, 600);
		gameOverScreen.setTitle("Game Over");
		gameOverScreen.setResizable(false);
		
		if(Entry.checkNewRecord(streak)) {
			
			JPanel record = new JPanel();
			record.setPreferredSize(new Dimension(900,150));
			
			JLabel newRecordLabel = new JLabel("<html><center>Nuovo Record!!</center><br>Inserisci il tuo nome</html>",JLabel.LEADING);
			newRecordLabel.setFont(new Font("Lucida Console",Font.BOLD,20));
			newRecordLabel.setPreferredSize(new Dimension(300,100));
			
			JTextField name = new JTextField();
			name.setFont(new Font("Lucida Console",Font.BOLD,20));
			name.setPreferredSize(new Dimension(300,50));
			
			JButton save = new JButton("Salva");
			save.setPreferredSize(new Dimension(100,50));
			
			save.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					Entry.saveNewRecord(name.getText(),pokemonName,streak);
					gameOverScreen.dispose();
					
				}
				
			});
			
			record.add(newRecordLabel,BorderLayout.NORTH);
			record.add(name,BorderLayout.CENTER);
			record.add(save,BorderLayout.SOUTH);
			
			gameOverScreen.add(record,BorderLayout.SOUTH);
			
		}
		
		gameOverScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gameOverScreen.setModal(true);
		gameOverScreen.setLocationRelativeTo(null);
		gameOverScreen.setVisible(true);
		
		
	}
	
	
}
