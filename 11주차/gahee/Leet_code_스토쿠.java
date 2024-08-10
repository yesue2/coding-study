
class Solution {
    
    public void solveSudoku(char[][] board) {
        solve(board, 0, 0);
    }

    private boolean solve(char[][] board, int row, int col) {
        // Entire board has been filled
        if (row == board.length) {
            return true;
        }

        // Move to next row
        if (col == board[0].length) {
            return solve(board, row + 1, 0);
        }

        // Skip cells
        if (board[row][col] != '.') {
            return solve(board, row, col + 1);
        }

        for (char c = '1'; c <= '9'; c++) {
            if (isValid(board, row, col, c)) {
                board[row][col] = c;
                if (solve(board, row, col + 1)) {
                    return true;
                }
                board[row][col] = '.';
            }
        }

        return false;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {

        for (int i = 0; i < board.length; i++) {

            if (board[i][col] == c) {
                return false;
            }

            if (board[row][i] == c) {
                return false;
            }

            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) {
                return false;
            }
        }

        return true;
    }
}