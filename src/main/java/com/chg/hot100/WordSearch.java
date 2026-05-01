package com.chg.hot100;

/**
 * 79. 单词搜索
 * 在给定的二维字符网格中查找是否存在给定的单词
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        // 遍历网格中每一个字符，如果他与 word 中的第一个字母相同，那么就以此为起点，上下左右四个方向进行深度遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = '#';
        boolean found = dfs(board, word, i + 1, j, index + 1) ||
                dfs(board, word, i - 1, j, index + 1) ||
                dfs(board, word, i, j + 1, index + 1) ||
                dfs(board, word, i, j - 1, index + 1);
        board[i][j] = temp;
        return found;
    }

}
