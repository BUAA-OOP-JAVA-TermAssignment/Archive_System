package view;

import controller.LogonRegisterCtrl;
import controller.NetworkCtrl;
import data.UserData;
import style.MyFonts;
import style.StyleCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserCentreFrm extends MyInterFrame{
    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 550;
    private static final int WIDGET_X = 20;
    private static final int WIDGET_Y = 50;
    private static final int WIDGET_GAP = 400;
    private static final int FIELD_HEIGHT = 40;

    private String userName = "这里要写十个字差三个";
    private String id = "20374090";
    private String email = "3232572736@qq.com";
    private String password = "123456_ABCD";
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
//        if(userData.getUserName().equals("游客12138")){
//            //TODO:一个提示未加载完成的函数
//        } else{
//            this.userName = userData.getUserName();
//            this.id = userData.getId();
//            this.email = userData.getEmail();
//            this.password = userData.getPassword();
//            this.downloadNum = userData.getDownloadNum();
            initComponents();
//        }
    }

    private void initComponents(){

        this.setTitle("个人信息");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        int y = WIDGET_Y;

        Container container = this.getContentPane();

        JLabel jLabelUserName = new JLabel();
        jLabelUserName.setText(this.userName);
        jLabelUserName.setBounds(WIDGET_X,20,WIDGET_GAP,50);
        jLabelUserName.setFont(MyFonts.TITLE_FONT_36);
        container.add(jLabelUserName);

        JLabel jLabelUserId = new JLabel();
        jLabelUserId.setText("学工号："+this.id);
        jLabelUserId.setBounds(WIDGET_X,20+FIELD_HEIGHT,WIDGET_GAP,50);
        jLabelUserId.setFont(MyFonts.SUB_TITLE_FONT_24);
        container.add(jLabelUserId);

        JLabel jLabelEmail = new JLabel();
        jLabelEmail.setText("注册邮箱："+this.email);
        jLabelEmail.setBounds(WIDGET_X,20+2*FIELD_HEIGHT,WIDGET_GAP,50);
        jLabelEmail.setFont(MyFonts.SUB_TITLE_FONT_24);
        container.add(jLabelEmail);

        JLabel jLabelPassword = new JLabel();
        jLabelPassword.setText("修改密码：");
        jLabelPassword.setBounds(WIDGET_X,20+3*FIELD_HEIGHT,WIDGET_GAP,50);
        jLabelPassword.setFont(MyFonts.SUB_TITLE_FONT_24);
        jLabelPassword.setVisible(false);
        container.add(jLabelPassword);

        JLabel jLabelRePassword = new JLabel();
        jLabelRePassword.setText("确认密码：");
        jLabelRePassword.setBounds(WIDGET_X,20+4*FIELD_HEIGHT,WIDGET_GAP,50);
        jLabelRePassword.setFont(MyFonts.SUB_TITLE_FONT_24);
        jLabelRePassword.setVisible(false);
        container.add(jLabelRePassword);

        JButton jButtonChangePassword = new JButton();
        jButtonChangePassword.setText("修改密码");
        jButtonChangePassword.setBounds(2*WIDGET_X,4*FIELD_HEIGHT,WIDGET_GAP/2,30);
        container.add(jButtonChangePassword);



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
