class Solution {
    public void solveSudoku(char[][] board) {
        char[][] copy = new char[board.length][];
        for (int i = 0; i < board.length; i++)
            copy[i] = board[i].clone();

        backtrack(copy, 0);

        for (int i = 0; i < board.length; i++)
            board[i] = copy[i].clone();
    }

    boolean backtrack(char[][] board, int n) {
        if (n == 81)
            return true;

        int r = n / 9;
        int c = n % 9;
        if (board[r][c] != '.')
            return backtrack(board, n + 1);
        else {
            for (int i = 0; i < 9; i++) {
                char nextValue = (char) (49 + i);
                if (!isValid(board, r, c, nextValue))
                    continue;
                board[r][c] = nextValue;

                if (backtrack(board, n + 1))
                    return true;
                board[r][c] = '.';
            }
        }

        return false;
    }

    boolean isValid(char[][] board, int r, int c, char value) {
        for (int i = 0; i < 9; i++) {
            if (board[i][c] == value)
                return false;
        }

        for (int i = 0; i < 9; i++) {
            if (board[r][i] == value)
                return false;
        }

        int sr = (r / 3) * 3;
        int sc = (c / 3) * 3;
        for (int dr = 0; dr < 3; dr++) {
            for (int dc = 0; dc < 3; dc++) {
                if (board[sr + dr][sc + dc] == value)
                    return false;
            }
        }

        return true;
    }
}