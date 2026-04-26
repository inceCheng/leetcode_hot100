package com.chg.hot100;

public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix[0][0] > target || matrix[matrix.length - 1][matrix[0].length - 1] < target) {
            return false;
        }
        // 从矩阵的左上角开始寻找
        // 当前值小于 target，则向下找
        // 当前值大于 target，则向左找
        int i = 0;
        int j = matrix[0].length - 1;
        int row = matrix.length - 1;
        while (i <= row && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }
}
