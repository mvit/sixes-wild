package boundary;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Panel;
import model.PlayerModel;
import controller.PlayerLoadLevelCtrl;

public class PlayerLevelSelectView extends JPanel {
	PlayerApplication app;
	PlayerModel model;

	/**
	 * Create the panel.
	 */
	public PlayerLevelSelectView(PlayerApplication app, PlayerModel model) {
		this.app = app;
		this.model = model;

		//Create Two Sub Panels
		JPanel levelSelectPanel = new JPanel();

		JPanel levelPreviewPanel = new JPanel();

		//Make Main panel Group Layout

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(levelPreviewPanel, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(levelSelectPanel, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(levelPreviewPanel, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
						.addComponent(levelSelectPanel, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		setLayout(groupLayout);

		//Set Level Select Panel, add buttons

		levelSelectPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JButton btnLevel1 = new JButton("Level 1");
		levelSelectPanel.add(btnLevel1);

		JButton btnLevel2 = new JButton("Level 2");
		levelSelectPanel.add(btnLevel2);

		JButton btnLevel3 = new JButton("Level 3");
		levelSelectPanel.add(btnLevel3);

		JButton btnLevel4 = new JButton("Level 4");
		levelSelectPanel.add(btnLevel4);

		JButton btnLevel5 = new JButton("Level 5");
		levelSelectPanel.add(btnLevel5);

		JButton btnLevel6 = new JButton("Level 6");
		levelSelectPanel.add(btnLevel6);

		JButton btnLevel7 = new JButton("Level 7");
		levelSelectPanel.add(btnLevel7);

		JButton btnLevel8 = new JButton("Level 8");
		levelSelectPanel.add(btnLevel8);

		JButton btnLevel9 = new JButton("Level 9");
		levelSelectPanel.add(btnLevel9);

		JButton btnLevel10 = new JButton("Level 10");
		levelSelectPanel.add(btnLevel10);

		JButton btnLevel11 = new JButton("Level 11");
		levelSelectPanel.add(btnLevel11);

		JButton btnLevel12 = new JButton("Level 12");
		levelSelectPanel.add(btnLevel12);

		JButton btnLevel13 = new JButton("Level 13");
		levelSelectPanel.add(btnLevel13);

		JButton btnLevel14 = new JButton("Level 14");
		levelSelectPanel.add(btnLevel14);

		JButton btnLevel15 = new JButton("Level 15");
		levelSelectPanel.add(btnLevel15);

		JButton btnLevel16 = new JButton("Level 16");
		levelSelectPanel.add(btnLevel16);
		//Level Preview Pane

		JButton btnPlayLevel = new JButton("Play Level");

		btnPlayLevel.addActionListener(new PlayerLoadLevelCtrl(app, model));

		JLabel lblLevel = new JLabel("Level 1");
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblScore = new JLabel("Score: ");

		JLabel lblType = new JLabel("Type:");

		JPanel levelPreviewLoadPanel = new JPanel();

		GroupLayout gl_levelPreviewPanel = new GroupLayout(levelPreviewPanel);
		gl_levelPreviewPanel.setHorizontalGroup(
			gl_levelPreviewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_levelPreviewPanel.createSequentialGroup()
					.addGroup(gl_levelPreviewPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_levelPreviewPanel.createSequentialGroup()
							.addGap(102)
							.addComponent(lblLevel))
						.addGroup(gl_levelPreviewPanel.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_levelPreviewPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblType)
								.addComponent(lblScore))))
					.addContainerGap(79, Short.MAX_VALUE))
				.addComponent(levelPreviewLoadPanel, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
				.addGroup(gl_levelPreviewPanel.createSequentialGroup()
					.addGap(67)
					.addComponent(btnPlayLevel)
					.addContainerGap(79, Short.MAX_VALUE))
		);
		gl_levelPreviewPanel.setVerticalGroup(
			gl_levelPreviewPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_levelPreviewPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_levelPreviewPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_levelPreviewPanel.createSequentialGroup()
							.addComponent(lblLevel)
							.addGap(24))
						.addGroup(gl_levelPreviewPanel.createSequentialGroup()
							.addComponent(lblType)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(lblScore)
					.addGap(11)
					.addComponent(levelPreviewLoadPanel, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnPlayLevel)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		levelPreviewPanel.setLayout(gl_levelPreviewPanel);



	}
}
