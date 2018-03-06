package rsawindows;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Action;

public class encoding extends JDialog {
	static JTextArea textArea = new JTextArea();
	private final JPanel contentPanel = new JPanel();
	private final JButton btnNewButton = new JButton("New button");
	private final Action action = new SwingAction();
	JLabel label = new JLabel("\u4FDD\u5B58\u6210\u529F");
	/**
	 * Launch the application.
	 */
	public static void main(String tmp) {
		try {
			encoding dialog = new encoding();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		textArea.setText(tmp);
	}

	/**
	 * Create the dialog.
	 */
	public encoding() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u52A0\u5BC6\u540E\u7684\u4FE1\u606F\u4E3A");
			label.setBounds(10, 10, 104, 15);
			contentPanel.add(label);
		}
		{
			
			textArea.setLineWrap(true);
			textArea.setBounds(10, 35, 414, 184);
			contentPanel.add(textArea);
		}
		{
			btnNewButton.setAction(action);
			btnNewButton.setBounds(320, 228, 104, 23);
			contentPanel.add(btnNewButton);
		}
		label.setBounds(255, 232, 54, 15);
		contentPanel.add(label);
		label.setVisible(false);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "保存到本地");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			Date date=new Date();
			  DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
			  String time=format.format(date);
			File file = new File( Rsawindows.textField.getText()+"_"+time+ "_ciphertext.txt");
			try {
				FileWriter out = new FileWriter(file);
				String s = textArea.getText();
				out.write(s);
				out.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			label.setVisible(true);
		}
	}
}
