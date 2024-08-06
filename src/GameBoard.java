import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GameBoard extends JFrame {
    private static final int SIZE = 7;
    private JButton[][] spaces = new JButton[SIZE][SIZE];
    private JButton[][] hFences = new JButton[SIZE-1][SIZE];
    private JButton[][] vFences = new JButton[SIZE][SIZE-1];
    private JMenuBar menuBar;
    private JMenu GameStatus;
    private JMenu System;
    private JMenuItem RulesMenu;
    private JMenuItem StartMenu;
    private JMenuItem ExitMenu;
    private static GameManager gameManager;
    private JPanel boardPanel;
    private boolean isMovePawnSelected = false;
    private boolean isPlaceFenceSelected = false;
    private JLabel CurrentPlayerLabel;
    private JButton movePawnButton;
    private JButton placeFenceButton;

    public GameBoard(GameManager gameManager) {
        this.gameManager = gameManager;

        /*====================set up GUI==================*/
        setTitle("Quoridor Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 650);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Initializations    
        setDisabledButtonTextColor();
        initializeMenuBar();
        CurrentPlayerLabel = new JLabel("Current Player: " + gameManager.getCurPawn().getName());
        updateCurrentPlayerDisplay();
        initializeBoard();
        initializePawns(); // initialize pawns' positions
        initializeControlPanel(); // initialize control panel with buttons
        
        ActionListener buttonListener = createButtonListener();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GameBoard(new GameManager(4)).setVisible(true));
    }

    private void initializeBoard() {
    	// since the size of fence and the space are different, use GridBagLayout.
        boardPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); 
        
        // set space, horizontal fences, vertical fences
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
            	// spaces
                spaces[i][j] = new JButton();
                spaces[i][j].addActionListener(createButtonListener());
                spaces[i][j].setBackground(new Color(0xd3d3d3));
                gbc.gridx = 2 * j;
                gbc.gridy = 2 * i;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = gbc.weighty = 1;
                boardPanel.add(spaces[i][j], gbc);
                
                // vertical fences
                if (j < SIZE - 1) {
                    vFences[i][j] = new JButton();
                    vFences[i][j].addActionListener(createButtonListener());
                    vFences[i][j].setPreferredSize(new Dimension(10, 50));
                    vFences[i][j].setBackground(Color.BLACK);
                    gbc.gridx = 2 * j + 1;
                    gbc.gridy = 2 * i;
                    gbc.weightx = 0.1;
                    gbc.weighty = 1;
                    boardPanel.add(vFences[i][j], gbc);
                }
                
                // horizontal fences
                if (i < SIZE - 1) {
                    hFences[i][j] = new JButton();
                    hFences[i][j].addActionListener(createButtonListener());
                    hFences[i][j].setPreferredSize(new Dimension(50, 10));
                    hFences[i][j].setBackground(Color.BLACK);
                    gbc.gridx = 2 * j;
                    gbc.gridy = 2 * i + 1;
                    gbc.weightx = 1;
                    gbc.weighty = 0.1;
                    boardPanel.add(hFences[i][j], gbc);
                }
            }
        }

        getContentPane().add(boardPanel, BorderLayout.CENTER); // 게임 보드를 중앙에 배치
    }
    
    // initialize each pawn's position
    private void initializePawns() {
        for (int i = 0; i < gameManager.getPlayer_num(); i++) {
            spaces[gameManager.startingPos[i][0]][gameManager.startingPos[i][1]].setText("P" + (i + 1));
            spaces[gameManager.startingPos[i][0]][gameManager.startingPos[i][1]].setEnabled(false);
            spaces[gameManager.startingPos[i][0]][gameManager.startingPos[i][1]].setForeground(Color.BLACK);
        }
    }
    
    // initialize control panel
    private void initializeControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBackground(new Color(0xd3d3d3));

        movePawnButton = new JButton("Move Pawn");
        movePawnButton.setFocusable(false);
        movePawnButton.setBackground(new Color(0xffffff));
        movePawnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isMovePawnSelected = true;
                isPlaceFenceSelected = false;
                updateButtonStates(movePawnButton);
            }
        });

        placeFenceButton = new JButton("Place Fence");
        placeFenceButton.setFocusable(false);
        placeFenceButton.setBackground(new Color(0xffffff));
        placeFenceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isMovePawnSelected = false;
                isPlaceFenceSelected = true;
                updateButtonStates(placeFenceButton);
            }
        });

        controlPanel.add(movePawnButton);
        controlPanel.add(placeFenceButton);
        controlPanel.add(CurrentPlayerLabel);
        getContentPane().add(controlPanel, BorderLayout.NORTH);
    }
    
    private void initializeMenuBar() {
    	menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        GameStatus = new JMenu("Game Status");
        menuBar.add(GameStatus);
        
        System = new JMenu("Program");
        menuBar.add(System);

        RulesMenu = new JMenuItem("Rules");
        RulesMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rules rules = new Rules();
                rules.setVisible(true);
            }
        });
        System.add(RulesMenu);

        StartMenu = new JMenuItem("Back to game start window");
        StartMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to restart?\nThe current game will not be stored.", "Restart Confirmation", JOptionPane.YES_NO_OPTION);
                if (selection==0) {
                    EnterGame enter = new EnterGame();
                    enter.setVisible(true);
                    dispose();
                }
            }
        });
        System.add(StartMenu);

        ExitMenu = new JMenuItem("Exit program");
        ExitMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?\nThe current game will not be stored.", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (selection==0) java.lang.System.exit(0);
            }
        });
        System.add(ExitMenu);
    }

    // =================Button Listener============
    private ActionListener createButtonListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                // Move Pawn
                if (isMovePawnSelected) {
                    if (isSpaceButton(clickedButton)) {
                        int[] indices = findButtonIndex(spaces, clickedButton); // find the index of the clicked space button
                        ClickSpace(indices[0], indices[1]); // when selected space is valid
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid move. Please select a valid space.");
                    }
                } 
                // Place Fence
                else if (isPlaceFenceSelected) {
                    if (isFenceButton(clickedButton)) {
                        int[] indices = findButtonIndex(vFences, clickedButton); // find the index of the clicked fence button
                        
                        // when the button clicked is a vertical fence.
                        if (indices[0] != -1) { 
                            ClickFence(indices[0], indices[1], false); 
                            
                         // when the button clicked is a horizontal fence.
                        } else {
                            indices = findButtonIndex(hFences, clickedButton);
                            ClickFence(indices[0], indices[1], true);
                        }
                    }
                } 
                
                // the player hasn't selected any movement button but clicked space button or fence button.
                else {
                    JOptionPane.showMessageDialog(null, "Please select 'Move Pawn' or 'Place Fence' before making a move.");
                }
            }
        };
    }

    private boolean isSpaceButton(JButton button) {
        int[] indices = findButtonIndex(spaces, button);
        return indices[0] != -1; // True if found, otherwise false
    }

    private boolean isFenceButton(JButton button) {
        int[] indices = findButtonIndex(vFences, button);
        if (indices[0] != -1) {
            return true;
        }
        indices = findButtonIndex(hFences, button);
        return indices[0] != -1;
    }
    
    
    // ================Moving Pawn==================
    private void ClickSpace(int row, int col) {
        JButton currentButton = spaces[gameManager.getCurPawn().getRow()][gameManager.getCurPawn().getCol()];
        if (gameManager.checkValidMove(row, col, hFences, vFences)) {
            currentButton.setText("");  // remove the pawn before move
            currentButton.setEnabled(true);
            JButton newButton = spaces[row][col];
            newButton.setText(gameManager.getCurPawn().getName()); // move the pawn
            newButton.setEnabled(false); // enable the pawn to prevent other players clicking it.
            gameManager.getCurPawn().setRow(row);
            gameManager.getCurPawn().setCol(col);
            
            // check if there's a winner
            if (checkWinner(row, col)) { // winner exists
            	WinnerFrame WFrame = new WinnerFrame(gameManager.getCurPawn(), GameBoard.this);
            	WFrame.setVisible(true);
            }
            else { // winner does not exist
            	gameManager.nextPlayer(); // move to next player
                updateCurrentPlayerDisplay();
                resetButtonStates();
            }
        }
    }

    // =====================Place Fence======================
    
    private JButton lastFenceButton = null; // to indicate the last button pushed.
    private boolean lastFenceIsHorizontal; // if the last fence is either horizontal or vertical

    private void ClickFence(int row, int col, boolean isHorizontal) {
        JButton currentButton = isHorizontal ? hFences[row][col] : vFences[row][col];

        if (lastFenceButton != null) {// the player selected two fence buttons
            // calculate the coordinates and orientation of the last fence
            int[] lastIndices = findButtonIndex(lastFenceIsHorizontal ? hFences : vFences, lastFenceButton);
            int lastRow = lastIndices[0];
            int lastCol = lastIndices[1];

            // try to place the fence
            if (gameManager.placeFence(lastRow, lastCol, row, col, lastFenceIsHorizontal, isHorizontal, hFences, vFences)) {
            	// update fence color / disable
                lastFenceButton.setBackground(new Color(0xffffff)); 
                currentButton.setBackground(new Color(0xffffff));
                lastFenceButton.setEnabled(false);
                currentButton.setEnabled(false);
                
                gameManager.getCurPawn().fenceNum--; // decrease the remaining fence number by 1
                gameManager.nextPlayer(); // move to the next player
                updateCurrentPlayerDisplay();
                lastFenceButton = null; // reset after successful placement
                resetButtonStates();
            } else {
                lastFenceButton = null;
            }
        } 
        // player has selected only one button
        else {
            lastFenceButton = currentButton;
            lastFenceIsHorizontal = isHorizontal; // Remember the orientation for comparison
        }
    }

    // For each turn, updates the number of remaining fences and update current player label
    private void updateCurrentPlayerDisplay() {
        GameStatus.removeAll();
        
        // number of fences
        for (int i = 0; i < gameManager.getPlayer_num(); i++) {
            JMenuItem playerItem = new JMenuItem("Player " + (i + 1) + ": " + gameManager.getPawns()[i].fenceNum + " fences left");
            GameStatus.add(playerItem);
        }
        
        // current player
        CurrentPlayerLabel.setText("Current Player: " + gameManager.getCurPawn().getName());

        GameStatus.revalidate();
        GameStatus.repaint();
    }
    
    // when a button is clicked, find the index of the button among arrays
    private int[] findButtonIndex(JButton[][] buttons, JButton target) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (buttons[i][j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1}; // Return {-1, -1} if not found
    }
    
    // to show selected button by the player in that turn.
    private void updateButtonStates(JButton activeButton) {
        if (activeButton == movePawnButton) {
            movePawnButton.setBackground(new Color(0xccffcc)); // Light green - selected
            placeFenceButton.setBackground(new Color(0xffffff)); // White - not selected
        } else if (activeButton == placeFenceButton) {
            placeFenceButton.setBackground(new Color(0xccffcc)); // Light green
            movePawnButton.setBackground(new Color(0xffffff)); // White
        }
    }
    
    // when one turn has end, reset button states
    private void resetButtonStates() {
        movePawnButton.setBackground(new Color(0xffffff));
        placeFenceButton.setBackground(new Color(0xffffff));
        isMovePawnSelected = false;
        isPlaceFenceSelected = false;
    }
    
    // set the text color of the disabled button into BLACK
    public static void setDisabledButtonTextColor() {
        UIManager.put("Button.disabledText", Color.BLACK);
    }
    
    // check whether there's a winner
    public boolean checkWinner(int row, int col) {
    	int idx = gameManager.getCurPawn().getIdx();
        if (idx < 2) {
        	if (row == 6 - gameManager.startingPos[idx][0]) {
        		return true;
        	}
        }
        else if (idx >= 2) {
        	if (col == 6 - gameManager.startingPos[idx][1]) {
        		return false;
        	}
        }
        
        return false;
    }
}