import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Rules extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    
    // resizing icons
    ImageIcon originalIcon;
    Image img;
    Image resizedImg;
    ImageIcon resizedIcon;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Rules dialog = new Rules();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Rules() {
    	getContentPane().setFont(new Font("Calibri", Font.PLAIN, 15));
        setTitle("Rules");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(255, 255, 255));
        
        //========================Tab TextArea==============================

        // Setup tab
        JTextArea setupText = createTextArea("\n The game board consists of a 7x7 board. Each player has a set of 4 fences(size: 1*2 or 2*1) that can be used to obstruct the opponent’s path.");
        JScrollPane SetupPane = new JScrollPane(setupText);
        tabbedPane.addTab("Setup", SetupPane);

        // Objective tab
        JTextArea objectiveText = createTextArea("\n The goal is to be the first to move your pawn to the opposite side of the board from your starting position.");
        JScrollPane ObjectviePane = new JScrollPane(objectiveText);
        tabbedPane.addTab("Objective", ObjectviePane);

        // Movement tab
        JTextArea movementText = createTextArea("\n On your turn, you can either move your pawn one square vertically or horizontally, or place a fence. \n\n Pawns cannot move diagonally, and two fences must be placed adjecently.");
        JScrollPane MovementPane = new JScrollPane(movementText);
        tabbedPane.addTab("Movement", MovementPane);

        // Fence Placement tab
        JTextArea fencePlacementText = createTextArea("\n At most 4 fences can be placed to complicate your opponent's progress towards their goal. They must be placed between two sets of two squares, and can be placed horizontally or vertically. \n\n Fences cannot be placed to completely block a player’s path to the goal; there must always be at least one open route.");
        JScrollPane FencePane = new JScrollPane(fencePlacementText);
        tabbedPane.addTab("Fence Placement", FencePane);

        // Winning tab
        JTextArea winningText = createTextArea("\n The game ends when one player's pawn reaches any square on the opposite side of the board from their starting position.");
        JScrollPane WinningPane = new JScrollPane(winningText);
        
        JLabel lblNewLabel = new JLabel("New label");
        FencePane.setColumnHeaderView(lblNewLabel);
        tabbedPane.addTab("Winning", WinningPane);
        
        //======================Tab Icons================================
        
        // 1. set up
        JLabel BoardIcon = new JLabel("");
        BoardIcon.setOpaque(true);
        BoardIcon.setBackground(new Color(255, 255, 255));
        BoardIcon.setHorizontalAlignment(SwingConstants.CENTER);
        
        // get image and resize
        originalIcon = new ImageIcon(Rules.class.getResource("/Images/board.png"));
        img = originalIcon.getImage();
        resizedImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);  // Adjust width and height as needed
        resizedIcon = new ImageIcon(resizedImg);
        BoardIcon.setIcon(resizedIcon);
        SetupPane.setColumnHeaderView(BoardIcon);
        
        // 2. objective
        JLabel ObjectiveIcon = new JLabel("");
        ObjectiveIcon.setOpaque(true);
        ObjectiveIcon.setBackground(new Color(255, 255, 255));
        ObjectiveIcon.setHorizontalAlignment(SwingConstants.CENTER);
        ObjectviePane.setColumnHeaderView(ObjectiveIcon);
        
        originalIcon = new ImageIcon(Rules.class.getResource("/Images/objective.png"));
        img = originalIcon.getImage();
        resizedImg = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);  // Adjust width and height as needed
        resizedIcon = new ImageIcon(resizedImg);
        ObjectiveIcon.setIcon(resizedIcon);
        
        // 3. Movement
        JLabel PawnIcon = new JLabel("");
        PawnIcon.setVerticalAlignment(SwingConstants.BOTTOM);
        PawnIcon.setOpaque(true);
        PawnIcon.setBackground(new Color(255, 255, 255));
        PawnIcon.setHorizontalAlignment(SwingConstants.CENTER);
        MovementPane.setColumnHeaderView(PawnIcon);
        
        originalIcon = new ImageIcon(Rules.class.getResource("/Images/pawn.png"));
        img = originalIcon.getImage();
        resizedImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);  // Adjust width and height as needed
        resizedIcon = new ImageIcon(resizedImg);
        PawnIcon.setIcon(resizedIcon);
        
        // 4. Fence
        JLabel FenceIcon = new JLabel("");
        FenceIcon.setOpaque(true);
        FenceIcon.setBackground(new Color(255, 255, 255));
        FenceIcon.setHorizontalAlignment(SwingConstants.CENTER);
        FencePane.setColumnHeaderView(FenceIcon);
        
        originalIcon = new ImageIcon(Rules.class.getResource("/Images/fence.png"));
        img = originalIcon.getImage();
        resizedImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);  // Adjust width and height as needed
        resizedIcon = new ImageIcon(resizedImg);
        FenceIcon.setIcon(resizedIcon);
  
        
        // 5. Winning
        JLabel winIcon = new JLabel("");
        winIcon.setOpaque(true);
        winIcon.setBackground(new Color(255, 255, 255));
        winIcon.setHorizontalAlignment(SwingConstants.CENTER);
        
        // getting image
        originalIcon = new ImageIcon(Rules.class.getResource("/Images/win.png"));
        img = originalIcon.getImage();
        resizedImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);  // Adjust width and height as needed
        resizedIcon = new ImageIcon(resizedImg);
        winIcon.setIcon(resizedIcon);
        WinningPane.setColumnHeaderView(winIcon);

        contentPanel.add(tabbedPane);

        // Button panel
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton CloseBtn = new JButton("Close");
        CloseBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        CloseBtn.setFont(new Font("Calibri", Font.PLAIN, 14));
        CloseBtn.setBackground(new Color(255, 255, 255));
        CloseBtn.setActionCommand("Cancel");
        buttonPane.add(CloseBtn);
    }

    private JTextArea createTextArea(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Calibri", Font.BOLD, 16));
        textArea.setBackground(new Color(255, 255, 255));
        return textArea;
    }
}
