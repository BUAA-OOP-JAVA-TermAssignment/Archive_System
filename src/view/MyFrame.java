package view;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import controller.StyleCtrl;


import javax.swing.*;

abstract public class MyFrame extends JFrame {
    public MyFrame() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("北航文献管理系统");
        this.setLocationRelativeTo(null);

        this.setLayout(null);

        // 因为设置了点叉关闭，所以页面必须要有返回键。
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //TODO:设置一个可爱的图标

        //TODO:添加个人中心头像及进入按钮

        JMenu menu_0 = new JMenu("文件");
        menu_0.setIcon(null);
        menu_0.setBounds(0, 0, 20, 40);
        this.getContentPane().add(menu_0);
    }

    public static void main(String[] args) {
        StyleCtrl.init();

        MyFrame testFrm = new MyFrame() {
        };

        testFrm.setVisible(true);
    }
}