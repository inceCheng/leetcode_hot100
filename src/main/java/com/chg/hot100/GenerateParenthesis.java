package com.chg.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 */
public class GenerateParenthesis {
    // n 个括号对，返回可能的有效括号对组合
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    private void dfs(List<String> res, StringBuilder path, int left, int right, int n) {
        if (path.length() == n * 2) {
            res.add(path.toString());
            return;
        }
        if (left < n) {
            path.append("(");
            dfs(res, path, left + 1, right, n);
            path.deleteCharAt(path.length() - 1);
        }
        if (right < left) {
            path.append(")");
            dfs(res, path, left, right + 1, n);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
