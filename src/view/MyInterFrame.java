package view;

import controller.StyleCtrl;

import javax.swing.*;

public abstract class MyInterFrame extends JInternalFrame {
    public MyInterFrame() {
        super();
        this.setClosable(true);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1280, 720);
    }

    public static void main(String[] args) {
        StyleCtrl.init();
        MyFrame testFrame = new MyFrame() {
        };
        MyInterFrame testInterFrame = new MyInterFrame() {
        };

        JDesktopPane table = new JDesktopPane();
        testFrame.getContentPane().add(table);
        table.setBounds(0, 0, 1000, 1000);
        testInterFrame.setBounds(0, 0, 600, 600);
        table.setVisible(true);
        testInterFrame.setVisible(true);
        table.add(testInterFrame);

        testFrame.setVisible(true);
    }
}
