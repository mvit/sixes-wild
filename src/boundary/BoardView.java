import javax.swing.JPanel;

public class BoardView extends JPanel {
  Board board;

  public BoardView(Board board) {
    this.board = board;
  }

  @Override
  protected paintComponent(Graphics g) {
    // TODO: paint grid
  }
}
