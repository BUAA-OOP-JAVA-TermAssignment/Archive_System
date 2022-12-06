package view;

import controller.LogonRegisterCtrl;
import controller.NetworkCtrl;
import data.UserData;
import style.StyleCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserCentreFrm extends MyInterFrame{
    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 400;
    private static final int WIDGET_X = 80;
    private static final int WIDGET_Y = 50;
    private static final int WIDGET_GAP = 60;
    private static final int FIELD_HEIGHT = 30;

    private String userName = null;
    private String id = null;
    private String email = null;
    private String password = null;
    private String downloadNum = null;


    private JLabel[] textLabels = new JLabel[]{
            new JLabel("姓名"),
            new JLabel("学工号"),
            new JLabel("邮箱"),
            new JLabel("密码"),
            new JLabel("确认密码"),
    };

    private JTextField[] textFields = new JTextField[]{
            new JTextField(),
            new JTextField(),
            new JTextField(),
            new JPasswordField(),
            new JPasswordField(),
    };

    public UserCentreFrm(){
        UserData userData = UserData.getInstance();
        if(userData.getUserName().equals("游客12138")){
            //TODO:一个提示未加载完成的函数
        } else{
            this.userName = userData.getUserName();
            this.id = userData.getId();
            this.email = userData.getEmail();
            this.password = userData.getPassword();
            this.downloadNum = userData.getDownloadNum();
            initComponents();
        }
    }

    private void initComponents(){

        JLabel jLabelUserName = new JLabel();
        jLabelUserName.setText(this.userName);
        jLabelUserName.setBounds(WIDGET_X,20,100,50);

        this.setTitle("个人信息");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

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


        JButton buttonRegister = new JButton("注册");
        buttonRegister.addActionListener(activeEvent -> {
            this.enWaitMode();

        });
        buttonRegister.setBounds(WIDGET_X - 3, WIDGET_Y + textLabels.length * WIDGET_GAP + 50, fieldWidth + 5, 30);
        container.add(buttonRegister);
    }


    public void enWaitMode() {
        this.setEnabled(false);
    }


    public void disWaitMode() {
        this.setEnabled(true);
    }

    public static void main(String[] args){
        StyleCtrl.setStyle(StyleCtrl.DARCULA);
        MyFrame myFrameTest = new MyFrame() {
        };

        UserCentreFrm userCentreFrmTest = new UserCentreFrm();
        myFrameTest.getTable().add(userCentreFrmTest);
        userCentreFrmTest.setVisible(true);
        myFrameTest.setVisible(true);
    }
}
