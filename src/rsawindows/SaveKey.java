package rsawindows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;

import javax.swing.Action;

public class SaveKey extends JDialog {
	String a = Newkey.textArea_1.getText();
	String b = Newkey.textArea.getText();
	String n=Bigint.bignumtostr(Rsawindows.n);
	private JTextField textField;
	private final Action action = new SwingAction();
	JLabel label = new JLabel("\u4FDD\u5B58\u6210\u529F");

	/**
	 * Launch the application.
	 */
	public static void main() {
		try {
			SaveKey dialog = new SaveKey();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SaveKey() {
		setBounds(100, 100, 450, 138);
		getContentPane().setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("\u4FDD\u5B58\u7684\u94A5\u5319\u540D");
			lblNewLabel.setBounds(10, 10, 90, 15);
			getContentPane().add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setBounds(10, 35, 414, 21);
			getContentPane().add(textField);
			textField.setColumns(10);
		}
		{
			
			label.setEnabled(false);
			label.setBounds(190, 10, 54, 15);
			getContentPane().add(label);
			{
				JButton okButton = new JButton("OK");
				okButton.setBounds(360, 66, 64, 23);
				getContentPane().add(okButton);
				okButton.setAction(action);
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			label.setVisible(false);
		}
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "确定");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			File file = new File(textField.getText() + "_PCKEY.txt");
			try {
				FileWriter out = new FileWriter(file);
				String s = a;
				s += "\r\n";
				out.write(s);
				String s1=n;
				out.write(s1);
				out.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			label.setVisible(true);
		
		File file1 = new File(textField.getText() + "_PRKEY.txt");
		try {
			FileWriter out = new FileWriter(file1);
			String s2 = b;
			s2+="\r\n";
			String s1=n;
			out.write(s2);
			out.write(s1);
			out.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		label.setVisible(true);
		
	}

	}
}
