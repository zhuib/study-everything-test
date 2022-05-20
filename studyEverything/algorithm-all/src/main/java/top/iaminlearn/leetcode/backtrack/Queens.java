package top.iaminlearn.leetcode.backtrack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date: 2022/3/17 23:58
 */
public class Queens {

    @Test
    public void que() {
        solveNQueens(4).forEach((q)->{
            System.out.println(q);
        });
    }

    List<List<String>> res = new ArrayList<>();

    /* �������̵ı߳�n���������кϷ��ķ��� */
    public List<List<String>> solveNQueens(int n) {
        // "." ��ʾ�գ�"Q"��ʾ�ʺ󣬳�ʼ������
        char[][] board = new char[n][n];
        for (char[] c : board) {
            Arrays.fill(c, '.');
        }
        backtrack(board, 0);
        return res;
    }

    public void backtrack(char[][] board, int row) {
        // ÿһ�ж��ɹ������˻ʺ󣬼�¼���
        if (row == board.length) {
            res.add(charToList(board));
            return;
        }

        int n = board[row].length;
        // �ڵ�ǰ�е�ÿһ�ж����ܷ��ûʺ�
        for (int col = 0; col < n; col++) {
            // �ų������໥�����ĸ���
            if (!isValid(board, row, col)) {
                continue;
            }
            // ��ѡ��
            board[row][col] = 'Q';
            // ������һ�зŻʺ�
            backtrack(board, row + 1);
            // ����ѡ��
            board[row][col] = '.';
        }
    }

    /* �ж��Ƿ������ board[row][col] ���ûʺ� */
    public boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        // ������Ƿ��лʺ��ͻ
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // ������Ϸ��Ƿ��лʺ��ͻ
        for (int i = row - 1, j = col + 1; i >=0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // ������Ϸ��Ƿ��лʺ��ͻ
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public List charToList(char[][] board) {
        List<String> list = new ArrayList<>();

        for (char[] c : board) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }

    @Test
    public void twoLine() {
        int n = 3;
        char[][] board = new char[n][n];
        for (char[] c : board) {
            Arrays.fill(c, '.');
        }
        for (Object o : charToList(board)) {
            System.out.println(o);
        }
    }

    @Test
    public void code() {
        int a = 1,count = 0;
        while (a != 0){
            count += a & 1;
            a >>= 1;
        }
        System.out.println(count);
    }
}

