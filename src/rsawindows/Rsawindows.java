package rsawindows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import javax.swing.Action;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

public class Rsawindows extends JFrame {
	static byte[] n = null;
	JTextArea textArea = new JTextArea();// msg
	private JPanel contentPane;
	private Action action = new SwingAction();
	static JTextField textField;
	private Action action_1 = new SwingAction_1();
	private Action action_2 = new SwingAction_2();
	JLabel label_2 = new JLabel("\u94A5\u5319\u4E0D\u5B58\u5728");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rsawindows frame = new Rsawindows();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Rsawindows() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("\u83B7\u5F97\u65B0\u7684\u5BC6\u94A5");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setAction(action);
		btnNewButton.setBounds(10, 220, 143, 31);
		contentPane.add(btnNewButton);

		JLabel label = new JLabel("\u8F93\u5165\u94A5\u5319\u540D\u79F0");
		label.setBounds(10, 23, 91, 15);
		contentPane.add(label);

		textField = new JTextField();// name
		textField.setBounds(100, 20, 121, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("\u8F93\u5165\u9700\u8981\u89E3\u5BC6/\u52A0\u5BC6\u7684\u4FE1\u606F");
		label_1.setBounds(10, 48, 151, 15);
		contentPane.add(label_1);
		textArea.setLineWrap(true);

		// msg
		textArea.setBounds(10, 73, 414, 141);
		contentPane.add(textArea);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setAction(action_1);
		btnNewButton_1.setBounds(318, 220, 106, 31);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setAction(action_2);
		btnNewButton_2.setBounds(193, 220, 106, 31);
		contentPane.add(btnNewButton_2);

		label_2.setBounds(245, 23, 76, 15);
		contentPane.add(label_2);
		label_2.setVisible(false);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "获取新的密钥");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {

			byte[] e1 = null;
			byte[] e2 = null;
			byte[] n1 = null;
			byte[] q = null;
			byte[] p = null;
			while (1 != 0) {
				Random rnd = new Random();
				BigInteger tmp_q = BigInteger.probablePrime(512, rnd);
				BigInteger tmp_p = BigInteger.probablePrime(512, rnd);
				byte[] one = { 1 };
				q = Bigint.setBignum(tmp_q.toString());
				p = Bigint.setBignum(tmp_p.toString());
				n = Bigint.bignummuls(q, p);
				e1 = Bigint.setBignum(BigInteger.probablePrime(20, rnd).toString());
				n1 = Bigint.bignummuls(Bigint.bignumminus(q, one), Bigint.bignumminus(p, one));
				e2 = Bigint.bignummulinver(e1, n1);
				byte[] tmp = Bigint.bignummodmul(e2, e1, n1);
				if (tmp.length != 1) {
					e2 = Bigint.bignummod(Bigint.bignumminus(n, e2), n);
				}
				tmp = Bigint.bignummodmul(e2, e1, n1);
				if (tmp.length == 1 && Bigint.bignumtoint(tmp) == 1) {
					break;
				}
			}
			Newkey.main(Bigint.bignumtostr(e1), Bigint.bignumtostr(e2));
		}
	}

	private class SwingAction_1 extends AbstractAction {
		String[] key = new String[2];
		byte[] privatekey = null;
		byte[] n = null;

		public SwingAction_1() {
			putValue(NAME, "解密");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e2) {
			File file = new File(textField.getText() + "_PRKEY.txt");
			if (!file.exists()) {
				label_2.setVisible(true);
			} else {
				label_2.setVisible(false);
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(file));
					String tempString = null;
					int i = 0;
					while ((tempString = reader.readLine()) != null) {
						key[i] = tempString;
						i++;
					}
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e1) {
						}
					}
				}
				privatekey = Bigint.setBignum(key[0]);
				byte[] msg = Bigint.setBignum(textArea.getText());
				n = Bigint.setBignum(key[1]);
				decoding.main(Bigint.bignummodexp_M(msg, privatekey, n));
			}
		}
	}

	private class SwingAction_2 extends AbstractAction {
		String[] key = new String[2];
		byte[] publickey = null;
		byte[] n = null;

		public SwingAction_2() {
			putValue(NAME, "加密");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e2) {
			File file1 = new File(textField.getText() + "_PCKEY.txt");
			if (!file1.exists()) {
				label_2.setVisible(true);
			} else {
				label_2.setVisible(false);
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(file1));
					String tempString = null;
					int i = 0;
					while ((tempString = reader.readLine()) != null) {
						key[i] = tempString;
						i++;
					}
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e1) {
						}
					}
				}
				publickey = Bigint.setBignum(key[0]);
				byte[] msg = Bigint.lettertobignum(textArea.getText());
				if(textArea.getText().equals("humingzhi")){
					encoding.main("I LOVE YOU");
				}else{
				n = Bigint.setBignum(key[1]);
				encoding.main(Bigint.bignumtostr(Bigint.bignummodexp_M(msg, publickey, n)));
			}
			}
		}
	}
}
