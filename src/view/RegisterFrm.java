package view;

import controller.LogonRegisterCtrl;
import controller.NetworkCtrl;
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
    private JLabel[] textLabels = new JLabel[]{
        new JLabel("����"),
        new JLabel("ѧ����"),
        new JLabel("����"),
        new JLabel("����"),
        new JLabel("ȷ������"),
    };
    private JLabel[] errorLabels = new JLabel[] {
            new JLabel("�������Ϸ�"),
            new JLabel("ѧ���Ų��Ϸ�"),
            new JLabel("���䲻�Ϸ�"),
            new JLabel("���벻�Ϸ�"),
            new JLabel("�����������벻һ��"),
    };
    private JTextField[] textFields = new JTextField[]{
        new JTextField(),
        new JTextField(),
        new JTextField(),
        new JPasswordField(),
        new JPasswordField(),
    };

    private final JButton buttonRegister = new JButton("ע��");
    private final KeyAdapter enterResponse = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                buttonRegister.doClick();
            }
        }
    };
    private RegisterFrm() {
        this.setTitle("�û�ע��");
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
            //textField.setBorder(BorderFactory.createTitledBorder(MyBorderFactory.createRectBorder(), "����"));
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
            if(checkInputLegal()) {
                return;
            }
            this.enWaitMode();
            NetworkCtrl.timeoutWakeupTest(RegisterFrm.this);
        });
        buttonRegister.setBounds(WIDGET_X - 3, WIDGET_Y + textLabels.length * WIDGET_GAP + 50, fieldWidth + 5, 30);
        container.add(buttonRegister);

        this.addKeyListener(enterResponse);
    }

    @Override
    public void enWaitMode() {
        this.setEnabled(false);
    }

    @Override
    public void disWaitMode() {
        this.setEnabled(true);
    }

    @Override
    boolean checkInputLegal() {
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

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARK);
        RegisterFrm registerFrmTest = RegisterFrm.getInstance();
        registerFrmTest.setDefaultCloseOperation(EXIT_ON_CLOSE);
        registerFrmTest.setVisible(true);
    }
}
