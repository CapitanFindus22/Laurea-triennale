package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import move.Move;
import pokemon.PlayerPokemon;

/**
 * A custom JDialog that let the player choose to swap a move or not 
 * when the pokemon has 4 moves and wants to learn a new one
 */
public final class MoveChange extends JDialog {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the window
	 * 
	 * @param p The pokemon of the player
	 * @param moveName The new move
	 */
	public MoveChange(PlayerPokemon p, String moveName) {
		
		MoveChange main = this;
		
		this.setTitle("Cambio mosse");
		this.setPreferredSize(new Dimension(400,250));
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//New move display
		JLabel topText = new JLabel(p.getName() + " vuole imparare " + moveName,JLabel.CENTER);
		topText.setFont(new Font("Lucida Console",Font.BOLD,16));
		topText.setPreferredSize(new Dimension(400,100));
		
		//List of the known moves
		JComboBox<String> list = new JComboBox<>(

				new String[] {
						
						"Nessuna",
						p.getMoves()[0].getName(),	
						p.getMoves()[1].getName(),	
						p.getMoves()[2].getName(),	
						p.getMoves()[3].getName()
							
					});
		
		list.setFont(new Font("Monospaced",Font.PLAIN,14));
		((JLabel)list.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel middleText = new JLabel("Scegli la mossa da rimuovere: ",JLabel.CENTER);
		middleText.setFont(new Font("Monospaced",Font.BOLD,16));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400,200));

		//Confirm button
		JButton ok = new JButton("OK");
		
		//The choice of the player
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				switch(list.getSelectedIndex()) {
				
				case 1:
					
					p.changeFirstMove(new Move(moveName));
					
					break;
					
				case 2:
					
					p.changeSecondMove(new Move(moveName));
					
					break;
					
				case 3:
					
					p.changeThirdMove(new Move(moveName));
					
					break;
					
				case 4:
					
					p.changeFourthMove(new Move(moveName));
					
					break;
				
				default:
					
					p.addInPastMoves(moveName);
					
					break;
					
				}
				
				main.dispose();
				
			}
			
		});
		
		panel.add(middleText);
		panel.add(list);
		
		this.add(topText,BorderLayout.NORTH);
		this.add(panel,BorderLayout.CENTER);
		this.add(ok,BorderLayout.SOUTH);
			
		pack();
		this.setModal(true);
		this.setVisible(true);
		
	}
	
}
