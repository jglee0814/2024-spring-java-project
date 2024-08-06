import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinnerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel winnerIcon;
    private BorderedLabel winnerLabel;
    private JButton startMenuBtn;
    private JButton exitBtn;
    private JLabel congratsIconL;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WinnerFrame frame = new WinnerFrame();
                    frame.setTitle("Winner");
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
    public WinnerFrame() {
    	this(null, null);
    }
    
    // overload
    public WinnerFrame(Pawn winnerPawn, GameBoard mainBoard) {
        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 468, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.WHITE);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Winner Icon
        winnerIcon = new JLabel("");
        winnerIcon.setBounds(192, 10, 90, 90);
        ImageIcon originalIcon = new ImageIcon(WinnerFrame.class.getResource("/Images/winner.png"));
        Image img = originalIcon.getImage();
        Image resizedImg = img.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        winnerIcon.setIcon(resizedIcon);
        winnerIcon.setOpaque(true);
        winnerIcon.setHorizontalAlignment(SwingConstants.CENTER);
        winnerIcon.setBackground(Color.WHITE);
        contentPane.add(winnerIcon);

        // Congratulation Icons
        congratsIconL = new JLabel("");
        congratsIconL.setBounds(84, 31, 70, 70);
        originalIcon = new ImageIcon(WinnerFrame.class.getResource("/Images/congratsL.png"));
        img = originalIcon.getImage();
        resizedImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImg);
        congratsIconL.setIcon(resizedIcon);
        congratsIconL.setOpaque(true);
        congratsIconL.setHorizontalAlignment(SwingConstants.CENTER);
        congratsIconL.setBackground(Color.WHITE);
        contentPane.add(congratsIconL);
        
        JLabel congratsIconR = new JLabel("");
        congratsIconR.setBounds(313, 31, 70, 70);
        originalIcon = new ImageIcon(WinnerFrame.class.getResource("/Images/congratsR.png"));
        img = originalIcon.getImage();
        resizedImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImg);
        congratsIconR.setIcon(resizedIcon);
        congratsIconR.setOpaque(true);
        congratsIconR.setHorizontalAlignment(SwingConstants.CENTER);
        congratsIconR.setBackground(Color.WHITE);
        contentPane.add(congratsIconR);

        
        // Winner label to show winner name
        winnerLabel = new BorderedLabel(winnerPawn.getName() + " has won!");
        winnerLabel.setForeground(Color.YELLOW);
        winnerLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerLabel.setBounds(70, 80, 350, 70);
        contentPane.add(winnerLabel);
        
        // Buttons
        startMenuBtn = new JButton("Back to start");
        startMenuBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		EnterGame enter = new EnterGame();
                enter.setVisible(true);
                dispose();
                mainBoard.dispose();
        	}
        });
        startMenuBtn.setBackground(Color.WHITE);
        startMenuBtn.setFocusable(false);
        startMenuBtn.setFont(new Font("굴림", Font.BOLD, 15));
        startMenuBtn.setBounds(160, 160, 153, 33);
        contentPane.add(startMenuBtn);

        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        exitBtn.setBackground(Color.WHITE);
        exitBtn.setFocusable(false);
        exitBtn.setFont(new Font("굴림", Font.BOLD, 15));
        exitBtn.setBounds(160, 203, 153, 33);
        contentPane.add(exitBtn);
    }

    /**
     * Custom JLabel with a border around the text.
     */
    public class BorderedLabel extends JLabel {
        public BorderedLabel(String text) {
            super(text);
            setOpaque(false); // Ensures the background is not painted by default
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Anti-aliasing for better text quality
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Clear the background
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, getWidth(), getHeight());

            // Draw the outline
            TextLayout textLayout = new TextLayout(getText(), getFont(), g2d.getFontRenderContext());
            Shape outline = textLayout.getOutline(null);

            // Calculate the position to center the text
            Rectangle bounds = outline.getBounds();
            int x = (getWidth() - bounds.width) / 2 - bounds.x;
            int y = (getHeight() - bounds.height) / 2 - bounds.y;

            // Draw the outline
            g2d.setStroke(new BasicStroke(2f));
            g2d.setColor(Color.RED);
            g2d.translate(x, y);
            g2d.draw(outline);

            // Draw the text
            g2d.setColor(getForeground());
            g2d.fill(outline);

            g2d.dispose();
        }
    }
}
