package view;

import controller.LogonRegisterCtrl;
import controller.NetworkCtrl;
import style.StyleCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
    private JLabel[] errorLabels = new JLabel[] {
            new JLabel("姓名不合法"),
            new JLabel("学工号不合法"),
            new JLabel("邮箱不合法"),
            new JLabel("密码不合法"),
            new JLabel("两次输入密码不一致"),
    };
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
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LogonRegisterCtrl.changeRegToLog();
            }
        });
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        int y = WIDGET_Y;


        Container container = this.getContentPane();


        for(JLabel label : textLabels) {
            label.setBounds(WIDGET_X, y, 100, 30);
            label.setVerticalAlignment(JLabel.BOTTOM);
            label.setHorizontalAlignment(JLabel.LEFT);
            container.add(label);
            y += WIDGET_GAP;
        }

        int fieldWidth = this.getWidth() - 2 * WIDGET_X - 3;
        y = WIDGET_Y + 30;
        for(JTextField textField : textFields) {
            //textField.setBorder(BorderFactory.createTitledBorder(MyBorderFactory.createRectBorder(), "姓名"));
            textField.setBounds(WIDGET_X - 3, y, fieldWidth, FIELD_HEIGHT);
            container.add(textField);
            y += WIDGET_GAP;
        }

        y = WIDGET_Y;
        fieldWidth -= 5;
        for(JLabel label : errorLabels) {
            label.setBounds(WIDGET_X, y, fieldWidth, 30);
            label.setVerticalAlignment(JLabel.BOTTOM);
            label.setHorizontalAlignment(JLabel.RIGHT);
            label.setForeground(Color.RED);
            label.setVisible(false);
            container.add(label);
            y += WIDGET_GAP;
        }

        JButton buttonRegister = new JButton("注册");
        buttonRegister.addActionListener(activeEvent -> {
            this.enWaitMode();
            NetworkCtrl.timeoutWakeupTest(RegisterFrm.this);
        });
        buttonRegister.setBounds(WIDGET_X - 3, WIDGET_Y + textLabels.length * WIDGET_GAP + 50, fieldWidth + 5, 30);
        container.add(buttonRegister);
    }

    @Override
    public void enWaitMode() {
        this.setEnabled(false);
    }

    @Override
    public void disWaitMode() {
        this.setEnabled(true);
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARK);
        RegisterFrm registerFrmTest = new RegisterFrm();
        registerFrmTest.setVisible(true);
    }
}
