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
        this.setTitle("�������׹���ϵͳ");
        this.setResizable(true);
        this.setMinimumSize(new Dimension(1000, 600));

        // ʹ�ùر����ر�
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);


        // ���ò˵���
        this.setJMenuBar(menuBar);

        JMenu menuApplication = new JMenu("Ӧ��");
        JMenu menuAccount = new JMenu("�˻�");
        menuBar.add(menuApplication);
        menuBar.add(menuAccount);


        // ����������壬����Boarder���֣�������ν���������û��getter�ⲿ���ʲ���
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout());
        this.setContentPane(panel);


        //�����ڲ����棬ͨ�����ڲ������������ʵ�ֲ���
        panel.add(table);
        table.setVisible(true);
        //TODO:����һ���ɰ���ͼ��

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