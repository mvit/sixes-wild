package boundary;

import model.BuilderModel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import controller.BuilderNewLevelCtrl;
import controller.ExitCtrl;

public class BuilderMainScreen extends JPanel {
	BuilderApplication app;
	BuilderModel model;

	public BuilderMainScreen(BuilderApplication app, BuilderModel model) {
		this.app = app;
		this.model = model;

		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new BuilderNewLevelCtrl(app, model));

		JButton btnOpen = new JButton("Open");

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ExitCtrl());

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(234)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnQuit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
						.addComponent(btnOpen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
						.addComponent(btnNew, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(276))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnQuit, GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
					.addGap(80))
		);
		setLayout(groupLayout);
	}
}
