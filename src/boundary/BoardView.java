
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;


public class BoardView extends JPanel{
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
		
		//have not tested!
		for(int i = 0; i<Board.width; i++)
		{
			for(int j = 0; j<Board.height; j++)
			{
				add(new JLabel("" + (board.grid[i][j]).tile.number));
			}
		}
	}

}
