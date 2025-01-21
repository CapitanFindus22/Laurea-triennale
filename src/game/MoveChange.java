package game;

import java.awt.BorderLayout;
import java.awt.Color;
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
 * A custom JDialog that let the player choose to swap a move or not when the
 * pokémon has 4 moves and wants to learn a new one
 */
public final class MoveChange extends JDialog {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the window
	 * 
	 * @param player   the pokémon of the player
	 * @param moveName the new move
	 */
	public MoveChange(PlayerPokemon player, String moveName) {

		MoveChange main = this;

		this.setTitle("Cambio mosse");
		this.setPreferredSize(new Dimension(500, 300));
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setBackground(new Color(196, 207, 161));

		// New move display
		JLabel topText = new JLabel(player.getName() + " vuole imparare " + moveName, JLabel.CENTER);
		topText.setFont(new Font("Lucida Console", Font.BOLD, 16));
		topText.setPreferredSize(new Dimension(460, 100));

		// List of the known moves
		JComboBox<String> list = new JComboBox<>(

				new String[] {

						"Nessuna", player.getMoves()[0].getName(), player.getMoves()[1].getName(),
						player.getMoves()[2].getName(), player.getMoves()[3].getName()

				});

		list.setFont(new Font("Monospaced", Font.BOLD, 18));
		list.setFocusable(false);
		list.setBackground(new Color(196, 207, 161));
		((JLabel) list.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		JLabel middleText = new JLabel("Scegli la mossa da rimuovere: ", JLabel.CENTER);
		middleText.setFont(new Font("Monospaced", Font.BOLD, 18));
		middleText.setBackground(new Color(196, 207, 161));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 200));
		panel.setBackground(new Color(196, 207, 161));

		// Confirm button
		JButton ok = new JButton("OK");
		ok.setFont(new Font("Monospaced", Font.BOLD, 17));

		// The choice of the player
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (list.getSelectedIndex()) {

				case 1:

					player.changeFirstMove(new Move(moveName));

					break;

				case 2:

					player.changeSecondMove(new Move(moveName));

					break;

				case 3:

					player.changeThirdMove(new Move(moveName));

					break;

				case 4:

					player.changeFourthMove(new Move(moveName));

					break;

				default:

					player.addInPastMoves(moveName);

					break;

				}

				main.dispose();

			}

		});

		panel.add(middleText);
		panel.add(list);

		this.add(topText, BorderLayout.NORTH);
		this.add(panel, BorderLayout.CENTER);
		this.add(ok, BorderLayout.SOUTH);

		pack();
		this.setModal(true);
		this.setVisible(true);

	}

}
