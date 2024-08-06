import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GameManager {
    private int player_num;
    private Pawn[] pawns;
    private Pawn curPawn;
    private boolean[][] board; // current board status
    public int startingPos[][] = {{6, 3}, {0,3}, {3, 0}, {3, 6}}; // To place the pawns when the game starts
    private int currentPlayerIndex = 0;

    public GameManager(int player_num) {
        super();
        this.player_num = player_num;
        pawns = new Pawn[player_num];
        board = new boolean[7][7]; // Temporary board

        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], true); // Fill each row separately
        } // At first, all spaces are available for pawns.
        
        // Update initial positions for each pawn
        for (int i = 0; i < player_num; i++) {
            pawns[i] = new Pawn(startingPos[i][0], startingPos[i][1], i);
        }
        
        // Start from P1
        setCurPawn(pawns[0]);
    }
    
    // To check whether we can place selected fences, and update fence
    public boolean placeFence(int fence1row, int fence1col, int fence2row, int fence2col, boolean isFence1Horizontal, boolean isFence2Horizontal, JButton[][] hFences, JButton[][] vFences) {
    	
        // Check for same orientation and adjacency
        if (isFence1Horizontal != isFence2Horizontal || Math.abs(fence1row - fence2row) + Math.abs(fence1col - fence2col) != 1) {
            JOptionPane.showMessageDialog(null, "Fences must be placed adjacently.");
            return false;
        }

        // Update the actual fence state
        if (isFence1Horizontal) {
            hFences[fence1row][fence1col].setEnabled(false);
            hFences[fence2row][fence2col].setEnabled(false);
        } else {
            vFences[fence1row][fence1col].setEnabled(false);
            vFences[fence2row][fence2col].setEnabled(false);
        }

        // Check if any player is completely blocked
        for (int i = 0; i < player_num; i++) {
            BFS bfs = new BFS(hFences, vFences);
            
            if (i >= 2) { // P3, P4
                int goalRow = 6 - startingPos[i][1];
                // invalid
                if (!bfs.isValidPath34(pawns[i].getRow(), pawns[i].getCol(), goalRow)) {
                    JOptionPane.showMessageDialog(null, "Cannot block the path completely.");
                    
                    // restore fences
                    if (isFence1Horizontal) {
                        hFences[fence1row][fence1col].setEnabled(true);
                        hFences[fence2row][fence2col].setEnabled(true);
                    } else {
                        vFences[fence1row][fence1col].setEnabled(true);
                        vFences[fence2row][fence2col].setEnabled(true);
                    }
                    return false;
                }
            } else {  // P1, P2
                int goalCol = 6 - startingPos[i][0];
                if (!bfs.isValidPath12(pawns[i].getRow(), pawns[i].getCol(), goalCol)) {
                    JOptionPane.showMessageDialog(null, "Cannot block the path completely.");
                    // Inavlid
                    if (isFence1Horizontal) {
                        hFences[fence1row][fence1col].setEnabled(true);
                        hFences[fence2row][fence2col].setEnabled(true);
                    } else {
                        vFences[fence1row][fence1col].setEnabled(true);
                        vFences[fence2row][fence2col].setEnabled(true);
                    }
                    return false;
                }
            }
        }

        return true;
    }



    // Move to next player
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % player_num;  // Update index
        setCurPawn(pawns[currentPlayerIndex]);  // Update current player
    }

    public Pawn[] getPawns() {
        return pawns;
    }

    public void setPawns(Pawn[] pawns) {
        this.pawns = pawns;
    }

    public int getPlayer_num() {
        return player_num;
    }

    public void setPlayer_num(int player_num) {
        this.player_num = player_num;
    }

    public Pawn getCurPawn() {
        return curPawn;
    }

    public void setCurPawn(Pawn curPawn) {
        this.curPawn = curPawn;
    }

    // Check whether a pawn movement is valid or not.
    public boolean checkValidMove(int row, int col, JButton hFences[][], JButton vFences[][]) {
        int cur_row = getCurPawn().getRow();
        int cur_col = getCurPawn().getCol();

        // Invalid move
        if (Math.abs(row - cur_row) > 1 || Math.abs(col - cur_col) > 1 || (Math.abs(row - cur_row) + Math.abs(col - cur_col) != 1)) {
            JOptionPane.showMessageDialog(null, "You can only move to an adjacent space.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        

        // Check for horizontal fences when moving vertically
        if (cur_col == col) {
            int fenceIndex = Math.min(cur_row, row);  // Determine which fence to check
            // Check if the fence button's background is white, indicating a fence is present
            //java.lang.System.out.println(hFences[cur_row][fenceIndex].getBackground().equals(new Color(0xffffff)));
            if (!hFences[fenceIndex][col].isEnabled()) {
                JOptionPane.showMessageDialog(null, "There is a fence blocking this move.", "Blocked by Fence", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        // Check for vertical fences when moving horizontally
        if (cur_row == row) {
            int fenceIndex = Math.min(cur_col, col);  // Determine which fence to check
            // Check if the fence button's background is white, indicating a fence is present
            if (!vFences[row][fenceIndex].isEnabled()) {
                JOptionPane.showMessageDialog(null, "There is a fence blocking this move.", "Blocked by Fence", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        // Valid move        
        return true;
    }



    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
    
}
