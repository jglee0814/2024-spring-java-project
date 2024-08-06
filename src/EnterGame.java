import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class EnterGame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel MainImage;
	private JButton SelectPlayerBtn;
	private JButton RuleBtn;
	private JButton ExitBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterGame frame = new EnterGame();
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
	public EnterGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Main image
		MainImage = new JLabel("");
		MainImage.setIcon(new ImageIcon(EnterGame.class.getResource("/Images/main.png")));
		MainImage.setBounds(0, 0, 400, 400);
		contentPane.add(MainImage);
		
		// Buttons
		SelectPlayerBtn = new JButton("Select Number of Players");
		SelectPlayerBtn.setFocusable(false);
		SelectPlayerBtn.setBackground(new Color(255, 255, 255));
		SelectPlayerBtn.setFont(new Font("Calibri", Font.BOLD, 17));
		SelectPlayerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NumPlayers select = new NumPlayers(EnterGame.this);
				select.setVisible(true);
			}
		});
		SelectPlayerBtn.setBounds(81, 414, 231, 42);
		contentPane.add(SelectPlayerBtn);
		
		RuleBtn = new JButton("Rules");
		RuleBtn.setFocusable(false);
		RuleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rules rules = new Rules();
				rules.setVisible(true);
			}
		});
		RuleBtn.setBackground(new Color(255, 255, 255));
		RuleBtn.setFont(new Font("Calibri", Font.BOLD, 17));
		RuleBtn.setBounds(81, 466, 231, 42);
		contentPane.add(RuleBtn);
		
		ExitBtn = new JButton("Exit");
		ExitBtn.setFocusable(false);
		ExitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		ExitBtn.setBackground(new Color(255, 255, 255));
		ExitBtn.setFont(new Font("Calibri", Font.BOLD, 17));
		ExitBtn.setBounds(81, 518, 231, 42);
		contentPane.add(ExitBtn);
	}
}
