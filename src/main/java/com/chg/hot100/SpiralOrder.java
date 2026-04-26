package com.chg.hot100;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {

    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length <= 0) {
            return list;
        }
        // 定义边界
        // 上、下
        int top = 0, bottom = matrix.length - 1;
        // 左、右
        int left = 0, right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            // 上边从左往右
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            // 右边从上到西
            for (int i = top + 1; i <= bottom; i++) {
                list.add(matrix[i][right]);
            }
            if (top < bottom && left < right) {
                // 下面从右到左
                for (int i = right - 1; i >= left; i--) {
                    list.add(matrix[bottom][i]);
                }
                // 左边从下到上
                for (int i = bottom - 1; i > top; i--) {
                    list.add(matrix[i][left]);
                }
            }
            // 缩圈
            top++;
            bottom--;
            left++;
            right--;
        }
        return list;
    }
}
