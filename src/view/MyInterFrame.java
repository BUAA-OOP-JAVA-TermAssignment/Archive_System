package view;

import controller.StyleCtrl;

import javax.swing.*;
import java.awt.*;

public abstract class MyInterFrame extends JInternalFrame {
    public MyInterFrame() {
        super();
        // 设置小窗可以关闭，并将关闭默认操作设置为隐藏
        this.setClosable(true);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        // 设置内部采用绝对布局模式，之后如果有自适应的需求，可以修改
        this.setLayout(null);
        // 设置可以最大化
        this.setMaximizable(true);
        this.setMinimumSize(new Dimension(200, 150));
        // 设置可以最小化
        this.setIconifiable(true);
        // 设置可以调整大小
        this.setResizable(true);
        // 设置默认大小，请在子类中覆盖
        this.setSize(600, 400);


        //TODO:打算尝试在小窗口中使用GroupLayout，能实现不错的效果，有没有GridBagLayout那么复杂，但也可以不用，丑点就丑点了
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARK);
        MyFrame testFrame = new MyFrame() {
        };
        MyInterFrame testInterFrame = new MyInterFrame() {
        };

        testFrame.getTable().add(testInterFrame);
        testInterFrame.setVisible(true);

        testFrame.setVisible(true);
    }
}
