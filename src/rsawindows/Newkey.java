package rsawindows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.math.BigInteger;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.DialogEvent;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.Action;

public class Newkey extends JDialog {
	private final Action action = new SwingAction();
	static JTextArea textArea = new JTextArea();
	static JTextArea textArea_1 = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String A, String B) {
		try {
			Newkey dialog = new Newkey(A, B);

			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Newkey(String a, String b) {
		setBounds(100, 100, 450, 423);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 341, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);

			JButton btntxt = new JButton("\u4FDD\u5B58\u4E3ATXT");
			btntxt.setAction(action);
			buttonPane.add(btntxt);
		}

		JLabel lblNewLabel = new JLabel("\u79C1\u94A5\u4E3A");
		lblNewLabel.setBounds(10, 86, 108, 21);
		getContentPane().add(lblNewLabel);

		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBounds(10, 110, 414, 221);
		getContentPane().add(textArea);
		textArea.setText(b);

		JLabel lblNewLabel_1 = new JLabel("\u516C\u94A5\u4E3A");
		lblNewLabel_1.setBounds(10, 29, 54, 15);
		getContentPane().add(lblNewLabel_1);

		textArea_1.setEditable(false);
		textArea_1.setBounds(10, 52, 414, 21);
		getContentPane().add(textArea_1);
		textArea_1.setText(a);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "保存到本地");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			SaveKey.main();
		}
	}
}
