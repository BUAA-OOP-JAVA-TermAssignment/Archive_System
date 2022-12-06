package view;

import controller.Closer;
import controller.LogonRegisterCtrl;
import controller.NetworkCtrl;
import style.StyleCtrl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LogOnFrm extends MyBootFrame {
    private static final int NO_CHOSEN = 0, GUEST = 1, ADMIN = 2;
    private static final int FRAME_WIDTH = 350;
    private static final int FRAME_HEIGHT = 450;
    private static final int WIDGET_X = 40;
    private static final int WIDGET_Y = 60;
    private static final int WIDGET_GAP = 60;
    private static final int FIELD_HEIGHT = 30;

    private final JTextField jTextField = new JTextField();
    private final JPasswordField jPasswordField = new JPasswordField();
    private final JComboBox<String> jComboBoxSelectUserType = new JComboBox<String>();
    private final JButton jButtonLogOn = new JButton();

    private JLabel[] errorLabels = new JLabel[] {
            new JLabel("请输入正确的学工号"),
            new JLabel("密码不合法"),
            new JLabel("请选择登陆身份"),
    };

    private final KeyAdapter enterResponse = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                jButtonLogOn.doClick();
            }
        }
    };
    public LogOnFrm() {
        initComponents();
        this.addKeyListener(enterResponse);
    }

    private void initComponents() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel jPanelSelectUserType = new JPanel();
        // 登陆界面输入用户名字的label
        JLabel jLabelOfUserName = new JLabel();
        // 登陆界面输入用户密码的label
        JLabel jLabelOfPassword = new JLabel();
        JLabel jLabelOfUserType = new JLabel();

        JButton jButtonRegister = new JButton();

    //todo：以下的路径都需要重新配图片
        jLabelOfUserName.setIcon(new ImageIcon("XXX")); // NOI18N
        jLabelOfUserName.setText("学工号");

        jLabelOfPassword.setIcon(new ImageIcon("XXX")); // NOI18N
        jLabelOfPassword.setText("密码");

        jLabelOfUserType.setIcon(new ImageIcon("XXX"));
        jLabelOfUserType.setText("用户类型");
        jComboBoxSelectUserType.addItem("-请选择-");
        jComboBoxSelectUserType.addItem("访客");
        jComboBoxSelectUserType.addItem("管理员");

        jPanelSelectUserType.add(jLabelOfUserType);
        jPanelSelectUserType.add(jComboBoxSelectUserType);


        jButtonLogOn.setIcon(new ImageIcon("XXX")); // NOI18N
        jButtonLogOn.setText("登录");
        jButtonLogOn.addActionListener(evt -> {
            System.out.println("LogOnFrm : Click log on button");
            if(checkInputLegal()) {
                return;
            }
            this.enWaitMode();
            NetworkCtrl.timeoutWakeupTest(LogOnFrm.this);
        });

        jButtonRegister.setIcon(new ImageIcon("XXX")); // NOI18N
        jButtonRegister.setText("注册");
        jButtonRegister.addActionListener(evt -> {
            System.out.println("LogOnFrm : Click register button");
            LogonRegisterCtrl.changeLogToReg();
        });

        this.setTitle("用户登录");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);

        int y = WIDGET_Y;
        int fieldWidth = this.getWidth() - 2 * WIDGET_X - 3;

        jTextField.addKeyListener(enterResponse);
        jPasswordField.addKeyListener(enterResponse);
        jComboBoxSelectUserType.addKeyListener(enterResponse);

        Container container = this.getContentPane();
        addLabels(jLabelOfUserName,container,WIDGET_GAP);
        addLabels(jLabelOfPassword,container,2*WIDGET_GAP);
        addLabels(jLabelOfUserType,container,3*WIDGET_GAP);
        addContent(jTextField,container,fieldWidth,WIDGET_GAP+30);
        addContent(jPasswordField,container,fieldWidth,2*WIDGET_GAP+30);
        jComboBoxSelectUserType.setBounds(WIDGET_X,3*WIDGET_GAP+30,fieldWidth,FIELD_HEIGHT);
        this.add(jComboBoxSelectUserType);

        jButtonRegister.setBounds(FRAME_WIDTH/2 + 5,4*WIDGET_GAP+30,(FRAME_WIDTH-2*WIDGET_X)/2-8, FIELD_HEIGHT);
        jButtonLogOn.setBounds(WIDGET_X - 5,4*WIDGET_GAP+30,(FRAME_WIDTH-2*WIDGET_X)/2-8, FIELD_HEIGHT);
        this.add(jButtonRegister);
        this.add(jButtonLogOn);


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


    }

    private void addLabels(JLabel label, Container container, int y){
        label.setBounds(WIDGET_X, y, 100, 30);
        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.LEFT);
        container.add(label);
    }

    private void addContent(JTextField textField, Container container,int x,int y){
        textField.setBounds(WIDGET_X, y, x, FIELD_HEIGHT);
        container.add(textField);
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

        boolean isIdError = false;
        boolean isPasswordError = false;
        boolean isNotChosen = false;

        String id = jTextField.getText();
        String password = jPasswordField.getText();


        if(!isLegalId(id)) {
            isIdError = true;
            isInputIllegal = true;
        }

        if(!isLegalPassword(password)) {
            isPasswordError = true;
            isInputIllegal = true;
        }

        if(!isChosen()) {
            isNotChosen = true;
            isInputIllegal = true;
        }

        errorLabels[1].setText("密码不合法");
        errorLabels[0].setVisible(isIdError);
        errorLabels[1].setVisible(isPasswordError);
        errorLabels[2].setVisible(isNotChosen);

        return isInputIllegal;
    }


    private boolean isChosen() {
        return (jComboBoxSelectUserType.getSelectedIndex() != NO_CHOSEN);
    }

    public void passwordError() {
        errorLabels[0].setVisible(false);
        errorLabels[1].setText("密码错误");
        errorLabels[0].setVisible(true);
        errorLabels[0].setVisible(false);

        jPasswordField.setText("");
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARK);

        LogOnFrm LogOnFrmTest = new LogOnFrm();
        LogOnFrmTest.setVisible(true);
    }

}
