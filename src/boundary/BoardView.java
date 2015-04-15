package boundary;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;

import model.Board;


public class BoardView extends JPanel {
	/**
	 * Actual view for the playable grid in-game.
	 * @author Bailey Sheridan
	 */
	private static final long serialVersionUID = 1L;
	Board board;

	public BoardView(Board board){
		this.board = board;

		setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, Color.DARK_GRAY));
		setLayout(new GridLayout(9, 9, 2, 2));

		// untested
		for (int x = 0; x < Board.width; x++) {
			for (int y = 0; y < Board.height; y++) {
				add(new JLabel("" + (board.grid[x][y]).tile.number));
			}
		}
	}
}
