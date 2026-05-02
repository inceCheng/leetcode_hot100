package com.chg.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N 皇后
 */
public class SolveNQueens {
    List<List<String>> res = new ArrayList<>();
    boolean[] cols;
    boolean[] diags1;
    boolean[] diags2;

    public List<List<String>> solveNQueens(int n) {

        cols = new boolean[n];
        diags1 = new boolean[2 * n - 1];
        diags2 = new boolean[2 * n - 1];
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        backtrace(board, 0, n);
        return res;
    }

    private void backtrace(char[][] board, int row, int n) {
        if (row == n) {
            res.add(constructBoard(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;

            if (cols[col] || diags1[d1] || diags2[d2]) {
                continue;
            }
            board[row][col] = 'Q';
            cols[col] = true;
            diags1[d1] = true;
            diags2[d2] = true;

            backtrace(board, row + 1, n);

            board[row][col] = '.';
            cols[col] = false;
            diags1[d1] = false;
            diags2[d2] = false;
        }
    }


    private List<String> constructBoard(char[][] board) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            list.add(new String(board[i]));
        }
        return list;
    }

}
