import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JButton;

public class BFS {
    private JButton[][] hFences; // horizontal fences
    private JButton[][] vFences; // vertical fences

    public BFS(JButton[][] hFences, JButton[][] vFences) {
        this.hFences = hFences;
        this.vFences = vFences;
    }

    // check if there's open route for player 3 and 4 when placing fences.
    public boolean isValidPath34(int startRow, int startCol, int goalRow) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[hFences.length + 1][vFences[0].length + 1];
        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            if (row == goalRow) return true; // reached the opposite line

            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : directions) {
                int nRow = row + dir[0];
                int nCol = col + dir[1];

                if (nRow >= 0 && nRow < visited.length && nCol >= 0 && nCol < visited[0].length && !visited[nRow][nCol]) {
                    if (dir[0] == 1 && (row == hFences.length || hFences[row][col].isEnabled())) { // downward
                        queue.offer(new int[]{nRow, nCol});
                        visited[nRow][nCol] = true;
                    } else if (dir[0] == -1 && (nRow == hFences.length || hFences[nRow][nCol].isEnabled())) { // upward
                        queue.offer(new int[]{nRow, nCol});
                        visited[nRow][nCol] = true;
                    } else if (dir[1] == 1 && (col == vFences[0].length || vFences[row][col].isEnabled())) { // to right
                        queue.offer(new int[]{nRow, nCol});
                        visited[nRow][nCol] = true;
                    } else if (dir[1] == -1 && (nCol == vFences[0].length || vFences[nRow][nCol].isEnabled())) { // to left
                        queue.offer(new int[]{nRow, nCol});
                        visited[nRow][nCol] = true;
                    }
                }
            }
        }
        return false;
    }

 // check if there's open route for player 1 and 2 when placing fences.
    public boolean isValidPath12(int startRow, int startCol, int goalCol) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[hFences.length + 1][vFences[0].length + 1];
        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            if (col == goalCol) return true; // reached the opposite line

            int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : directions) {
                int nRow = row + dir[0];
                int nCol = col + dir[1];

                if (nRow >= 0 && nRow < visited.length && nCol >= 0 && nCol < visited[0].length && !visited[nRow][nCol]) {
                    if (dir[0] == 1 && (row == hFences.length || hFences[row][col].isEnabled())) { // downward
                        queue.offer(new int[]{nRow, nCol});
                        visited[nRow][nCol] = true;
                    } else if (dir[0] == -1 && (nRow == hFences.length || hFences[nRow][nCol].isEnabled())) { // upward
                        queue.offer(new int[]{nRow, nCol});
                        visited[nRow][nCol] = true;
                    } else if (dir[1] == 1 && (col == vFences[0].length || vFences[row][col].isEnabled())) { // to right
                        queue.offer(new int[]{nRow, nCol});
                        visited[nRow][nCol] = true;
                    } else if (dir[1] == -1 && (nCol == vFences[0].length || vFences[nRow][nCol].isEnabled())) { // to left
                        queue.offer(new int[]{nRow, nCol});
                        visited[nRow][nCol] = true;
                    }
                }
            }
        }
        return false;
    }
}