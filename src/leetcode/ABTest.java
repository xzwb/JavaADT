package leetcode;

import javax.swing.*;
import java.awt.*;

public class ABTest {
    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(250, 250);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 创建面板
        JPanel jPanel = new JPanel();
        JTextField noteName = new JTextField("dddd");
        noteName.setColumns(20);

        JLabel jLabel = new JLabel("添加到新笔记:");
        jPanel.add(jLabel);
        jPanel.add(noteName);

        jf.setContentPane(jPanel);
        jf.setVisible(true);
    }
}
