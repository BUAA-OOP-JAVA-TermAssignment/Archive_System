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
import java.util.Scanner;

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

    public boolean isLegalId(String number){
        int n = number.length();
        if(8 == n && number.matches("\\d+")){
            int num1 = Integer.parseInt(number.substring(0,2));
            if(num1 < 17 || num1 > 22){ return false;}
            int num2 = Integer.parseInt(number.substring(2,4));
            if(num2 < 1 || num2 >43) {
                return false;
            }
            int num3 = Integer.parseInt(number.substring(4,5));
            if(num3 < 1 || num3 > 9) {
                return false;
            }
            int num4 = Integer.parseInt(number.substring(5,8));
            return num4 >= 1;
        } else if(9 == n && number.matches("[ZBS]Y\\d+")){
            int num2 = Integer.parseInt(number.substring(4,6));
            if(num2 < 1 || num2 >43) {
                return false;
            }
            int num3 = Integer.parseInt(number.substring(6,7));
            if(num3 < 1 || num3 > 9) {
                return false;
            }
            int num4 = Integer.parseInt(number.substring(7,9));
            if(num4 < 1) {
                return false;
            }
            if((number.startsWith("ZY"))|| (number.startsWith("SY"))){
                int num1 = Integer.parseInt(number.substring(2,4));
                return num1 >= 19 && num1 <= 22;
            } else if(number.startsWith("BY")){
                int num1 = Integer.parseInt(number.substring(2,4));
                return num1 >= 17 && num1 <= 22;
            } else {
                return false;
            }
        } else if(5 == n && number.matches("\\d+")){
            int num1 = Integer.parseInt(number);
            return num1 >= 1;
        } else {
            return false;
        }

    }

    private static boolean isLegalEmail(String email){
        return email.matches("\\w+@\\w+(\\.\\w+)+");
    }

    private static boolean isLegalPassword(String password){
        return password.matches("^([A-Z]|[a-z])([0-9]|[A-Z]|[a-z]|_){7,15}$");
    }

    private static boolean isLegalName(String name){
        return name.matches("^(([\\u4e00-\\u9fa5]){1,5}·?([\\u4e00-\\u9fa5])+){1,10}$");
    }

    private static boolean isSamePassword(String rePassword,String password){
        return rePassword.matches(password);
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARK);
        RegisterFrm registerFrmTest = new RegisterFrm();
        registerFrmTest.setVisible(true);
    }
}
