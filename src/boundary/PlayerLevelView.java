import javax.swing.JPanel;

public class PlayerLevelView extends JPanel {
  PlayerModel model;
  BoardView boardView;

  public PlayerLevelView(PlayerModel model) {
    this.model = model;
    boardView = new BoardView(model.board);
  }
}
