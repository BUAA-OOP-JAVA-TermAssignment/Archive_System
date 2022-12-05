package view;

import style.StyleCtrl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOnFrm extends MyBootFrame {
    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 350;
    private static final int WIDGET_X = 80;
    private static final int WIDGET_Y = 50;
    private static final int WIDGET_GAP = 60;
    private static final int FIELD_HEIGHT = 30;
    public LogOnFrm() {
        Font font = new Font("Dialog", Font.PLAIN, 12);
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, font);
            }
        }
        initComponents();
    }

    private void initComponents() {
        JPanel jPanelSelectUserType = new JPanel();
        JComboBox<String> jComboBoxSelectUserType = new JComboBox<String>();
        // 登陆界面输入用户名字的label
        JLabel jLabelOfUserName = new JLabel();
        // 登陆界面输入用户密码的label
        JLabel jLabelOfPassword = new JLabel();

        JTextField jTextField = new JTextField();
        JPasswordField jPasswordField = new JPasswordField();
        JLabel jLabelOfUserType = new JLabel();
        JButton jButtonLogOn = new JButton();
        JButton jButtonReset = new JButton();


    //todo：以下的路径都需要重新配图片

        jLabelOfUserName.setIcon(new ImageIcon("XXX")); // NOI18N
        jLabelOfUserName.setText("用户");

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
        jButtonLogOn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("Click log on button");
            }
        });

        jButtonReset.setIcon(new ImageIcon("XXX")); // NOI18N
        jButtonReset.setText("重置");
        jButtonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("LogOn:Click reset button");
            }
        });

        this.setTitle("用户登录");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);

        Container container = this.getContentPane();
        addLabels(jLabelOfUserName,container,WIDGET_GAP);
        addLabels(jLabelOfPassword,container,2*WIDGET_GAP);
        addLabels(jLabelOfUserType,container,3*WIDGET_GAP);
        addContent(jTextField,container,this.getWidth() - 2 * WIDGET_X - 5,WIDGET_GAP+30);
        addContent(jPasswordField,container,this.getWidth() - 2 * WIDGET_X - 5,2*WIDGET_GAP+30);
        jComboBoxSelectUserType.setBounds(WIDGET_X-5,3*WIDGET_GAP+30,this.getWidth() - 2 * WIDGET_X - 5,FIELD_HEIGHT);
        this.add(jComboBoxSelectUserType);

        jButtonLogOn.setBounds(WIDGET_X - 5,4*WIDGET_GAP+30,this.getWidth() - 4 * WIDGET_X - 5, FIELD_HEIGHT);
        jButtonReset.setBounds(FRAME_WIDTH/2 + 5,4*WIDGET_GAP+30,this.getWidth() - 4 * WIDGET_X - 5, FIELD_HEIGHT);
        this.add(jButtonReset);
        this.add(jButtonLogOn);


    }

    private void addLabels(JLabel label, Container container, int y){
        label.setBounds(WIDGET_X, y, 100, 30);
        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.LEFT);
        container.add(label);
    }

    private void addContent(JTextField textField, Container container,int x,int y){
        textField.setBounds(WIDGET_X - 5, y, x, FIELD_HEIGHT);
        container.add(textField);
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARCULA);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogOnFrm().setVisible(true);
            }
        });
    }
}
