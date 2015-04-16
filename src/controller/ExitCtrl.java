package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitCtrl implements ActionListener {
  public ExitCtrl() {}

  @Override
  public void actionPerformed(ActionEvent event) {
    System.exit(0);
  }
}
