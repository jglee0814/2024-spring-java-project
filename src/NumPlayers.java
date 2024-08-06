import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class NumPlayers extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel HelpLabel;
	private JButton TwoBtn;
	private EnterGame mainFrame; // for: when ok button is entered, close EnterGame window.
	
	// to check which button the user has clicked.
	private int selected_num = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NumPlayers dialog = new NumPlayers();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public NumPlayers() {
	    this(null); // Call the other constructor with null, handle null in the constructor
	}
	
	// overload
	public NumPlayers(EnterGame mainFrame) {
		this.mainFrame = mainFrame; // 
		getContentPane().setBackground(SystemColor.textHighlightText);
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 296, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		HelpLabel = new JLabel("Number of Players");
		HelpLabel.setForeground(Color.DARK_GRAY);
		HelpLabel.setFont(new Font("Bahnschrift", Font.BOLD, 26));
		HelpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		HelpLabel.setBounds(12, 33, 246, 30);
		contentPanel.add(HelpLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			// Action listener for okButton
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	// No button selected
				        if (selected_num == 0) {
				            JOptionPane.showMessageDialog(null, "Please select the number of players.", "Select again", JOptionPane.ERROR_MESSAGE);
				        } 
				        // Button selected
				        else {
				            GameManager gameManager = new GameManager(selected_num);
				            GameBoard board = new GameBoard(gameManager);
				            
				            // Confirmation
				            int val = JOptionPane.showConfirmDialog(null, "You chose " + selected_num + " players to play.", "Selection confirmation", JOptionPane.YES_NO_OPTION);
				            
				            // Yes
				            if (val == 0) {
				                board.setVisible(true);
				                dispose();
				                if (mainFrame != null) {
				                    mainFrame.dispose();
				                }
				            }
				        }
				    }
				});

				okButton.setFocusable(false);
				okButton.setBackground(SystemColor.window);
				okButton.setFont(new Font("굴림", Font.BOLD, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFocusable(false);
				cancelButton.setBackground(SystemColor.window);
				cancelButton.setFont(new Font("굴림", Font.BOLD, 12));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
			
		ImageIcon originalIcon = new ImageIcon(Rules.class.getResource("/Images/players.png"));
        Image img = originalIcon.getImage();
        Image resizedImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);  // Adjust width and height as needed
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        
        // To make the user only select one button.
        ButtonGroup G = new ButtonGroup();
        
        TwoBtn = new JButton("2");
        TwoBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		selected_num = 2;
        	}
        });
        TwoBtn.setFont(new Font("굴림", Font.BOLD, 15));
        TwoBtn.setBackground(Color.WHITE);
        TwoBtn.setIcon(new ImageIcon(NumPlayers.class.getResource("/Images/player2.png")));
        TwoBtn.setBounds(83, 75, 118, 40);
        contentPanel.add(TwoBtn);
        G.add(TwoBtn);
        TwoBtn.setFocusable(false);
        {
        	JButton ThreeBtn = new JButton("3");
        	ThreeBtn.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			selected_num = 3;
        		}
        	});
        	ThreeBtn.setIcon(new ImageIcon(NumPlayers.class.getResource("/Images/player3.png")));
        	ThreeBtn.setFont(new Font("굴림", Font.BOLD, 15));
        	ThreeBtn.setBackground(Color.WHITE);
        	ThreeBtn.setBounds(83, 125, 118, 40);
        	contentPanel.add(ThreeBtn);
        	G.add(ThreeBtn);
        	ThreeBtn.setFocusable(false);
        	
        }
        {
        	JButton FourBtn = new JButton("4");
        	FourBtn.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			selected_num = 4;
        		}
        	});
        	FourBtn.setIcon(new ImageIcon(NumPlayers.class.getResource("/Images/player4.png")));
        	FourBtn.setFont(new Font("굴림", Font.BOLD, 15));
        	FourBtn.setBackground(Color.WHITE);
        	FourBtn.setBounds(83, 175, 118, 40);
        	contentPanel.add(FourBtn);
        	G.add(FourBtn);
        	FourBtn.setFocusable(false);
        }
        
        
        
 		
 		
	}
}
