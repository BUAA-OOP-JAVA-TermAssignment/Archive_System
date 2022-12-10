package view;

import controller.LogonRegisterCtrl;
import style.StyleCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterFrm extends MyBootFrame{
    private static volatile RegisterFrm registerFrm = null;

    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 550;
    private static final int WIDGET_X = 80;
    private static final int WIDGET_Y = 50;
    private static final int WIDGET_GAP = 60;
    private static final int FIELD_HEIGHT = 30;
    private final JLabel[] textLabels = new JLabel[]{
        new JLabel("姓名"),
        new JLabel("学工号"),
        new JLabel("邮箱"),
        new JLabel("密码"),
        new JLabel("确认密码"),
    };
    private final JLabel[] errorLabels = new JLabel[] {
            new JLabel("姓名不合法"),
            new JLabel("学工号不合法"),
            new JLabel("邮箱不合法"),
            new JLabel("密码不合法"),
            new JLabel("两次输入密码不一致"),
    };
    private final JTextField[] textFields = new JTextField[]{
        new JTextField(),
        new JTextField(),
        new JTextField(),
        new JPasswordField(),
        new JPasswordField(),
    };

    private final JButton buttonRegister = new JButton("注册");
    private final KeyAdapter enterResponse = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                buttonRegister.doClick();
            }
        }
    };



    private final JLabel msgLabel = new JLabel("等待服务器响应...");

    private RegisterFrm() {
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
            textField.addKeyListener(enterResponse);
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


        buttonRegister.addActionListener(activeEvent -> {
            System.out.println("Register : register clicked");
            if(checkInputLegalAndSend()) {
                return;
            }
            this.enWaitMode();
        });
        buttonRegister.setBounds(WIDGET_X - 3, WIDGET_Y + textLabels.length * WIDGET_GAP + 50, fieldWidth + 5, 30);
        container.add(buttonRegister);


        msgLabel.setBounds(WIDGET_X, WIDGET_Y + textLabels.length * WIDGET_GAP + 25, 300, 30);
        msgLabel.setVisible(false);
        container.add(msgLabel);

        this.addKeyListener(enterResponse);
    }

    @Override
    public void enWaitMode() {
        this.buttonRegister.setEnabled(false);
    }

    @Override
    public void disWaitMode() {
        this.buttonRegister.setEnabled(true);
    }

    @Override
    boolean checkInputLegalAndSend() {
        boolean isInputIllegal = false;

        boolean isNameError = false;
        boolean isIdError = false;
        boolean isEmailError = false;
        boolean isPasswordError = false;
        boolean isPwdNotSame = false;

        String name = textFields[0].getText();
        String id = textFields[1].getText();
        String email = textFields[2].getText();
        String password = textFields[3].getText();
        //System.out.println(password);

        if(!isLegalName(name)) {
            isNameError = true;
            isInputIllegal = true;
        }

        if(!isLegalId(id)) {
            isIdError = true;
            isInputIllegal = true;
        }

        if(!isLegalEmail(email)) {
            isEmailError = true;
            isInputIllegal = true;
        }

        if(!isLegalPassword(password)) {
            isPasswordError = true;
            isInputIllegal = true;
        }

        if(!isSamePassword(password, textFields[4].getText())) {
            isPwdNotSame = true;
            isInputIllegal = true;
        }

        errorLabels[0].setVisible(isNameError);
        errorLabels[1].setVisible(isIdError);
        errorLabels[2].setVisible(isEmailError);
        errorLabels[3].setVisible(isPasswordError);
        errorLabels[4].setVisible(isPwdNotSame);

        if(!isInputIllegal)
            LogonRegisterCtrl.tryRegister(name, id, password, email);

        return isInputIllegal;
    }

    public static RegisterFrm getInstance() {
        if(registerFrm == null) {
            synchronized (RegisterFrm.class) {
                if(registerFrm == null) {
                    registerFrm = new RegisterFrm();
                }
            }
        }

        return registerFrm;
    }

    public void connectError() {
        msgLabel.setText("注册请求发送失败，请稍后重试");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
    }

    public void timeoutError() {
        msgLabel.setText("服务器连接超时，请稍后重试");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
    }

    public void registerFailed() {
        msgLabel.setText("注册失败");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
    }

    public void sendMsgNotice() {
        msgLabel.setText("等待服务器响应...");
        msgLabel.setForeground(Color.YELLOW);
        msgLabel.setVisible(true);
    }

    public void registerSuccess() {
        msgLabel.setText("注册成功");
        msgLabel.setForeground(Color.GREEN);
        msgLabel.setVisible(true);
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARK);
        RegisterFrm registerFrmTest = RegisterFrm.getInstance();
        registerFrmTest.setDefaultCloseOperation(EXIT_ON_CLOSE);
        registerFrmTest.setVisible(true);
    }
}
