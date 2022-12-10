package view;

import controller.LogonRegisterCtrl;
import style.StyleCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LogOnFrm extends MyBootFrame {
    // ���������ã��õ���ģʽ����Ҫ������
    private static final LogOnFrm logOnFrm = new LogOnFrm();
    public static final int NO_CHOSEN = 0, GUEST = 1, ADMIN = 2;
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
            new JLabel("��������ȷ��ѧ����"),
            new JLabel("���벻�Ϸ�"),
            new JLabel("��ѡ���½���"),
    };

    private final KeyAdapter enterResponse = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                jButtonLogOn.doClick();
            }
        }
    };

    private final JLabel msgLabel = new JLabel("�ȴ���������Ӧ...");
    private LogOnFrm() {
        initComponents();
        this.addKeyListener(enterResponse);
    }

    private void initComponents() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel jPanelSelectUserType = new JPanel();
        // ��½���������û����ֵ�label
        JLabel jLabelOfUserName = new JLabel();
        // ��½���������û������label
        JLabel jLabelOfPassword = new JLabel();
        JLabel jLabelOfUserType = new JLabel();

        JButton jButtonRegister = new JButton();

    //todo�����µ�·������Ҫ������ͼƬ
        jLabelOfUserName.setIcon(new ImageIcon("XXX")); // NOI18N
        jLabelOfUserName.setText("ѧ����");

        jLabelOfPassword.setIcon(new ImageIcon("XXX")); // NOI18N
        jLabelOfPassword.setText("����");

        jLabelOfUserType.setIcon(new ImageIcon("XXX"));
        jLabelOfUserType.setText("�û�����");
        jComboBoxSelectUserType.addItem("-��ѡ��-");
        jComboBoxSelectUserType.addItem("�ÿ�");
        jComboBoxSelectUserType.addItem("����Ա");

        jPanelSelectUserType.add(jLabelOfUserType);
        jPanelSelectUserType.add(jComboBoxSelectUserType);


        jButtonLogOn.setIcon(new ImageIcon("XXX")); // NOI18N
        jButtonLogOn.setText("��¼");
        jButtonLogOn.addActionListener(evt -> {
            System.out.println("LogOnFrm : Click log on button");
            msgLabel.setVisible(false);
            if(checkInputLegalAndSend()) {
                return;
            }
            this.enWaitMode();
            sendMsgNotice();
            //NetworkCtrl.timeoutWakeupTest(LogOnFrm.this);
        });

        jButtonRegister.setIcon(new ImageIcon("XXX")); // NOI18N
        jButtonRegister.setText("ע��...");
        jButtonRegister.addActionListener(evt -> {
            System.out.println("LogOnFrm : Click register button");
            LogonRegisterCtrl.changeLogToReg();
        });

        this.setTitle("�û���¼");
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

        msgLabel.setBounds(WIDGET_X,4 * WIDGET_GAP + 5, 200, 30);
        this.add(msgLabel);
        this.msgLabel.setVisible(false);

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
        this.jButtonLogOn.setEnabled(false);
    }

    @Override
    public void disWaitMode() {
        this.jButtonLogOn.setEnabled(true);
    }

    @Override
    boolean checkInputLegalAndSend() {
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

        //errorLabels[1].setText("���벻�Ϸ�");
        errorLabels[0].setVisible(isIdError);
        errorLabels[1].setVisible(isPasswordError);
        errorLabels[2].setVisible(isNotChosen);

        if(!isInputIllegal)
            LogonRegisterCtrl.tryLogon(jComboBoxSelectUserType.getSelectedIndex(), id, password);
        return isInputIllegal;
    }


    private boolean isChosen() {
        return (jComboBoxSelectUserType.getSelectedIndex() != NO_CHOSEN);
    }

    public void idOrPasswordError() {
        msgLabel.setText("�û������������");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);

        //errorLabels[1].setText("�������");
        //jPasswordField.setText("");
    }


    public void connectError() {
        msgLabel.setText("��½������ʧ�ܣ����Ժ�����");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
    }

    public void timeoutError() {
        msgLabel.setText("���������ӳ�ʱ�����Ժ�����");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
    }

    public void sendMsgNotice() {
        msgLabel.setText("�ȴ���������Ӧ...");
        msgLabel.setForeground(Color.YELLOW);
        msgLabel.setVisible(true);
    }

    public void logonSuccess() {
        msgLabel.setText("��¼�ɹ�");
        msgLabel.setForeground(Color.GREEN);
        msgLabel.setVisible(true);
    }

    public void registerSuccess() {
        msgLabel.setText("ע��ɹ�");
        msgLabel.setForeground(Color.GREEN);
        msgLabel.setVisible(true);
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARK);

        LogOnFrm LogOnFrmTest = new LogOnFrm();
        LogOnFrmTest.setVisible(true);
    }


    public static LogOnFrm getInstance() {
        return logOnFrm;
    }

    public JTextField getjTextField() {
        return jTextField;
    }

    public JPasswordField getjPasswordField() {
        return jPasswordField;
    }

    public JComboBox<String> getjComboBoxSelectUserType() {
        return jComboBoxSelectUserType;
    }
}
