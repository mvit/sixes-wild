

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JComboBox;

public class LevelBuildWindow extends JFrame {
	public LevelBuildWindow() {
		getContentPane().setLayout(null);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.setBounds(67, 30, 71, 29);
		getContentPane().add(btnOpen);
		
		JButton button_1 = new JButton(" ");
		button_1.setBounds(180, 30, 37, 29);
		getContentPane().add(button_1);
		
		JButton button_4 = new JButton(" ");
		button_4.setBounds(222, 30, 37, 29);
		getContentPane().add(button_4);
		
		JButton btnNewButton_7 = new JButton(" ");
		btnNewButton_7.setBounds(264, 30, 37, 29);
		getContentPane().add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton(" ");
		btnNewButton_8.setBounds(306, 30, 37, 29);
		getContentPane().add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton(" ");
		btnNewButton_9.setBounds(348, 30, 37, 29);
		getContentPane().add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton(" ");
		btnNewButton_10.setBounds(390, 30, 37, 29);
		getContentPane().add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton(" ");
		btnNewButton_11.setBounds(432, 30, 37, 29);
		getContentPane().add(btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton(" ");
		btnNewButton_12.setBounds(474, 30, 37, 29);
		getContentPane().add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton(" ");
		btnNewButton_13.setBounds(516, 30, 37, 29);
		getContentPane().add(btnNewButton_13);
		
		JLabel label = new JLabel("1");
		label.setBounds(588, 34, 9, 20);
		getContentPane().add(label);
		
		JSlider slider = new JSlider();
		slider.setBounds(602, 31, 200, 26);
		getContentPane().add(slider);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(70, 64, 65, 29);
		getContentPane().add(btnSave);
		
		JButton button_2 = new JButton(" ");
		button_2.setBounds(180, 64, 37, 29);
		getContentPane().add(button_2);
		
		JButton btnNewButton_14 = new JButton(" ");
		btnNewButton_14.setBounds(222, 64, 37, 29);
		getContentPane().add(btnNewButton_14);
		
		JButton btnNewButton_17 = new JButton(" ");
		btnNewButton_17.setBounds(264, 64, 37, 29);
		getContentPane().add(btnNewButton_17);
		
		JButton button_37 = new JButton(" ");
		button_37.setBounds(306, 64, 37, 29);
		getContentPane().add(button_37);
		
		JButton button_36 = new JButton(" ");
		button_36.setBounds(348, 64, 37, 29);
		getContentPane().add(button_36);
		
		JButton button_35 = new JButton(" ");
		button_35.setBounds(390, 64, 37, 29);
		getContentPane().add(button_35);
		
		JButton button_34 = new JButton(" ");
		button_34.setBounds(432, 64, 37, 29);
		getContentPane().add(button_34);
		
		JButton button_33 = new JButton(" ");
		button_33.setBounds(474, 64, 37, 29);
		getContentPane().add(button_33);
		
		JButton button_32 = new JButton(" ");
		button_32.setBounds(516, 64, 37, 29);
		getContentPane().add(button_32);
		
		JLabel label_1 = new JLabel("2");
		label_1.setBounds(588, 68, 9, 20);
		getContentPane().add(label_1);
		
		JSlider slider_1 = new JSlider();
		slider_1.setBounds(602, 65, 200, 26);
		getContentPane().add(slider_1);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.setBounds(106, 115, 69, 29);
		getContentPane().add(btnRedo);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.setBounds(30, 115, 71, 29);
		getContentPane().add(btnUndo);
		
		JButton button_3 = new JButton(" ");
		button_3.setBounds(180, 98, 37, 29);
		getContentPane().add(button_3);
		
		JButton btnNewButton_15 = new JButton(" ");
		btnNewButton_15.setBounds(222, 98, 37, 29);
		getContentPane().add(btnNewButton_15);
		
		JButton button_8 = new JButton(" ");
		button_8.setBounds(264, 98, 37, 29);
		getContentPane().add(button_8);
		
		JButton button_38 = new JButton(" ");
		button_38.setBounds(306, 98, 37, 29);
		getContentPane().add(button_38);
		
		JButton button_55 = new JButton(" ");
		button_55.setBounds(348, 98, 37, 29);
		getContentPane().add(button_55);
		
		JButton button_56 = new JButton(" ");
		button_56.setBounds(390, 98, 37, 29);
		getContentPane().add(button_56);
		
		JButton button_58 = new JButton(" ");
		button_58.setBounds(432, 98, 37, 29);
		getContentPane().add(button_58);
		
		JButton button_44 = new JButton(" ");
		button_44.setBounds(474, 98, 37, 29);
		getContentPane().add(button_44);
		
		JButton button_31 = new JButton(" ");
		button_31.setBounds(516, 98, 37, 29);
		getContentPane().add(button_31);
		
		JLabel label_2 = new JLabel("3");
		label_2.setBounds(588, 102, 9, 20);
		getContentPane().add(label_2);
		
		JSlider slider_2 = new JSlider();
		slider_2.setBounds(602, 99, 200, 26);
		getContentPane().add(slider_2);
		
		JButton btnNewButton_1 = new JButton(" ");
		btnNewButton_1.setBounds(180, 132, 37, 29);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_16 = new JButton(" ");
		btnNewButton_16.setBounds(222, 132, 37, 29);
		getContentPane().add(btnNewButton_16);
		
		JButton button_9 = new JButton(" ");
		button_9.setBounds(264, 132, 37, 29);
		getContentPane().add(button_9);
		
		JButton button_53 = new JButton(" ");
		button_53.setBounds(306, 132, 37, 29);
		getContentPane().add(button_53);
		
		JButton button_39 = new JButton(" ");
		button_39.setBounds(348, 132, 37, 29);
		getContentPane().add(button_39);
		
		JButton button_57 = new JButton(" ");
		button_57.setBounds(390, 132, 37, 29);
		getContentPane().add(button_57);
		
		JButton button_43 = new JButton(" ");
		button_43.setBounds(432, 132, 37, 29);
		getContentPane().add(button_43);
		
		JButton button_62 = new JButton(" ");
		button_62.setBounds(474, 132, 37, 29);
		getContentPane().add(button_62);
		
		JButton button_30 = new JButton(" ");
		button_30.setBounds(516, 132, 37, 29);
		getContentPane().add(button_30);
		
		JLabel label_3 = new JLabel("4");
		label_3.setBounds(588, 136, 9, 20);
		getContentPane().add(label_3);
		
		JSlider slider_3 = new JSlider();
		slider_3.setBounds(602, 133, 200, 26);
		getContentPane().add(slider_3);
		
		JButton btnNewButton = new JButton("Inert");
		btnNewButton.setBounds(32, 183, 67, 29);
		getContentPane().add(btnNewButton);
		
		JButton btnNum = new JButton("Num");
		btnNum.setBounds(107, 183, 67, 29);
		getContentPane().add(btnNum);
		
		JButton btnNewButton_2 = new JButton(" ");
		btnNewButton_2.setBounds(180, 166, 37, 29);
		getContentPane().add(btnNewButton_2);
		
		JButton button_5 = new JButton(" ");
		button_5.setBounds(222, 166, 37, 29);
		getContentPane().add(button_5);
		
		JButton button_10 = new JButton(" ");
		button_10.setBounds(264, 166, 37, 29);
		getContentPane().add(button_10);
		
		JButton button_52 = new JButton(" ");
		button_52.setBounds(306, 166, 37, 29);
		getContentPane().add(button_52);
		
		JButton button_54 = new JButton(" ");
		button_54.setBounds(348, 166, 37, 29);
		getContentPane().add(button_54);
		
		JButton button_40 = new JButton(" ");
		button_40.setBounds(390, 166, 37, 29);
		getContentPane().add(button_40);
		
		JButton button_59 = new JButton(" ");
		button_59.setBounds(432, 166, 37, 29);
		getContentPane().add(button_59);
		
		JButton button_61 = new JButton(" ");
		button_61.setBounds(474, 166, 37, 29);
		getContentPane().add(button_61);
		
		JButton button_29 = new JButton(" ");
		button_29.setBounds(516, 166, 37, 29);
		getContentPane().add(button_29);
		
		JLabel label_4 = new JLabel("5");
		label_4.setBounds(588, 170, 9, 20);
		getContentPane().add(label_4);
		
		JSlider slider_4 = new JSlider();
		slider_4.setBounds(602, 167, 200, 26);
		getContentPane().add(slider_4);
		
		JButton btnNewButton_3 = new JButton(" ");
		btnNewButton_3.setBounds(180, 200, 37, 29);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_18 = new JButton(" ");
		btnNewButton_18.setBounds(222, 200, 37, 29);
		getContentPane().add(btnNewButton_18);
		
		JButton button_11 = new JButton(" ");
		button_11.setBounds(264, 200, 37, 29);
		getContentPane().add(button_11);
		
		JButton button_51 = new JButton(" ");
		button_51.setBounds(306, 200, 37, 29);
		getContentPane().add(button_51);
		
		JButton button_45 = new JButton(" ");
		button_45.setBounds(348, 200, 37, 29);
		getContentPane().add(button_45);
		
		JButton button_50 = new JButton(" ");
		button_50.setBounds(390, 200, 37, 29);
		getContentPane().add(button_50);
		
		JButton button_41 = new JButton(" ");
		button_41.setBounds(432, 200, 37, 29);
		getContentPane().add(button_41);
		
		JButton button_60 = new JButton(" ");
		button_60.setBounds(474, 200, 37, 29);
		getContentPane().add(button_60);
		
		JButton button_28 = new JButton(" ");
		button_28.setBounds(516, 200, 37, 29);
		getContentPane().add(button_28);
		
		JLabel label_5 = new JLabel("6");
		label_5.setBounds(588, 204, 9, 20);
		getContentPane().add(label_5);
		
		JSlider slider_5 = new JSlider();
		slider_5.setBounds(602, 201, 200, 26);
		getContentPane().add(slider_5);
		
		JButton button = new JButton("6");
		button.setBounds(45, 251, 41, 29);
		getContentPane().add(button);
		
		JButton btnV = new JButton("V");
		btnV.setBounds(119, 251, 43, 29);
		getContentPane().add(btnV);
		
		JButton btnNewButton_4 = new JButton(" ");
		btnNewButton_4.setBounds(180, 234, 37, 29);
		getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_19 = new JButton(" ");
		btnNewButton_19.setBounds(222, 234, 37, 29);
		getContentPane().add(btnNewButton_19);
		
		JButton button_12 = new JButton(" ");
		button_12.setBounds(264, 234, 37, 29);
		getContentPane().add(button_12);
		
		JButton button_46 = new JButton(" ");
		button_46.setBounds(306, 234, 37, 29);
		getContentPane().add(button_46);
		
		JButton button_47 = new JButton(" ");
		button_47.setBounds(348, 234, 37, 29);
		getContentPane().add(button_47);
		
		JButton button_48 = new JButton(" ");
		button_48.setBounds(390, 234, 37, 29);
		getContentPane().add(button_48);
		
		JButton button_49 = new JButton(" ");
		button_49.setBounds(432, 234, 37, 29);
		getContentPane().add(button_49);
		
		JButton button_42 = new JButton(" ");
		button_42.setBounds(474, 234, 37, 29);
		getContentPane().add(button_42);
		
		JButton button_27 = new JButton(" ");
		button_27.setBounds(516, 234, 37, 29);
		getContentPane().add(button_27);
		
		JButton btnNewButton_5 = new JButton(" ");
		btnNewButton_5.setBounds(180, 268, 37, 29);
		getContentPane().add(btnNewButton_5);
		
		JButton button_6 = new JButton(" ");
		button_6.setBounds(222, 268, 37, 29);
		getContentPane().add(button_6);
		
		JButton button_13 = new JButton(" ");
		button_13.setBounds(264, 268, 37, 29);
		getContentPane().add(button_13);
		
		JButton button_16 = new JButton(" ");
		button_16.setBounds(306, 268, 37, 29);
		getContentPane().add(button_16);
		
		JButton button_22 = new JButton(" ");
		button_22.setBounds(348, 268, 37, 29);
		getContentPane().add(button_22);
		
		JButton button_23 = new JButton(" ");
		button_23.setBounds(390, 268, 37, 29);
		getContentPane().add(button_23);
		
		JButton button_24 = new JButton(" ");
		button_24.setBounds(432, 268, 37, 29);
		getContentPane().add(button_24);
		
		JButton button_26 = new JButton(" ");
		button_26.setBounds(474, 268, 37, 29);
		getContentPane().add(button_26);
		
		JButton button_25 = new JButton(" ");
		button_25.setBounds(516, 268, 37, 29);
		getContentPane().add(button_25);
		
		JButton btnNewButton_6 = new JButton(" ");
		btnNewButton_6.setBounds(180, 302, 37, 29);
		getContentPane().add(btnNewButton_6);
		
		JButton button_7 = new JButton(" ");
		button_7.setBounds(222, 302, 37, 29);
		getContentPane().add(button_7);
		
		JButton button_14 = new JButton(" ");
		button_14.setBounds(264, 302, 37, 29);
		getContentPane().add(button_14);
		
		JButton button_15 = new JButton(" ");
		button_15.setBounds(306, 302, 37, 29);
		getContentPane().add(button_15);
		
		JButton button_18 = new JButton(" ");
		button_18.setBounds(348, 302, 37, 29);
		getContentPane().add(button_18);
		
		JButton button_19 = new JButton(" ");
		button_19.setBounds(390, 302, 37, 29);
		getContentPane().add(button_19);
		
		JButton button_20 = new JButton(" ");
		button_20.setBounds(432, 302, 37, 29);
		getContentPane().add(button_20);
		
		JButton button_21 = new JButton(" ");
		button_21.setBounds(474, 302, 37, 29);
		getContentPane().add(button_21);
		
		JButton button_17 = new JButton(" ");
		button_17.setBounds(516, 302, 37, 29);
		getContentPane().add(button_17);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(180, 0, 373, 26);
		getContentPane().add(comboBox);
	}
}
