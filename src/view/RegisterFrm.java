package view;

import style.MyBorderFactory;
import style.StyleCtrl;

import javax.swing.*;
import java.awt.*;

public class RegisterFrm extends MyBootFrame{

    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 550;
    private static final int WIDGET_X = 80;
    private static final int WIDGET_Y = 50;
    private static final int WIDGET_GAP = 60;
    private static final int FIELD_HEIGHT = 30;
    private JLabel[] textLabels = new JLabel[]{
        new JLabel("姓名"),
        new JLabel("学工号"),
        new JLabel("邮箱"),
        new JLabel("密码"),
        new JLabel("确认密码"),
    };
    private JLabel[] errorLabels = new JLabel[5];
    private JTextField[] textFields = new JTextField[]{
        new JTextField(),
        new JTextField(),
        new JTextField(),
        new JPasswordField(),
        new JPasswordField(),
    };
    public RegisterFrm() {
        this.setTitle("用户注册");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);

        int y = WIDGET_Y;


        Container container = this.getContentPane();


        for(JLabel label : textLabels) {
            label.setBounds(WIDGET_X, y, 100, 30);
            label.setVerticalAlignment(JLabel.BOTTOM);
            label.setHorizontalAlignment(JLabel.LEFT);
            container.add(label);
            y += WIDGET_GAP;
        }

        int fieldWidth = this.getWidth() - 2 * WIDGET_X - 5;
        y = WIDGET_Y + 30;
        for(JTextField textField : textFields) {
            //textField.setBorder(BorderFactory.createTitledBorder(MyBorderFactory.createRectBorder(), "姓名"));
            textField.setBounds(WIDGET_X - 5, y, fieldWidth, FIELD_HEIGHT);
            container.add(textField);
            y += WIDGET_GAP;
        }




    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARCULA);

        RegisterFrm registerFrmTest = new RegisterFrm();
        registerFrmTest.setVisible(true);
    }
}
