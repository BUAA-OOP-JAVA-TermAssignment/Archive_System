package view;

import style.StyleCtrl;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

abstract public class MyFrame extends JFrame implements WaitModeAble{
    final private JPanel panel = new JPanel();
    final private JDesktopPane table = new JDesktopPane();
    final private JMenuBar menuBar = new JMenuBar();

    @Override
    public void enWaitMode() {
        this.setEnabled(false);
    }

    @Override
    public void disWaitMode() {
        this.setEnabled(true);
    }

    public MyFrame() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("北航文献管理系统");
        this.setResizable(true);
        this.setMinimumSize(new Dimension(1000, 600));

        // 使用关闭器关闭
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


        // 设置菜单栏
        this.setJMenuBar(menuBar);

        JMenu menuApplication = new JMenu("应用");
        JMenu menuAccount = new JMenu("账户");
        menuBar.add(menuApplication);
        menuBar.add(menuAccount);


        // 设置容器面板，采用Boarder布局，但无所谓，这个东西没有getter外部访问不到
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout());
        this.setContentPane(panel);


        //设置内部桌面，通过在内部桌面添加内容实现操作
        panel.add(table);
        table.setVisible(true);
        //TODO:设置一个可爱的图标

    }

    public JDesktopPane getTable() {
        return table;
    }

    @Override
    public JMenuBar getJMenuBar() {
        return menuBar;
    }

    public static void main(String[] args) {
        StyleCtrl.init();

        MyFrame testFrm = new MyFrame() {
        };
        testFrm.setDefaultCloseOperation(EXIT_ON_CLOSE);


        testFrm.setVisible(true);
    }
}