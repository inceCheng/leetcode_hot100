package com.chg.hot100;

/**
 * 74. 搜索二维矩阵
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix[0][0] > target || matrix[matrix.length - 1][matrix[0].length - 1] < target) {
            return false;
        }
        int i = 0;
        int j = matrix[0].length - 1;
        while (i <= matrix.length - 1 && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
