package view;

import controller.UserCentreCtrl;
import data.UserData;
import style.MyFonts;
import style.StyleCtrl;

import javax.swing.*;
import java.awt.*;

public class UserCentreFrm extends MyInterFrame{
    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 550;
    private static final int WIDGET_X = 20;
    private static final int WIDGET_Y = 50;
    private static final int WIDGET_GAP = 400;
    private static final int FIELD_HEIGHT = 40;

    private static volatile UserCentreFrm userCentreFrm = null;

    private String userName = "这里要写十个字差三个";
    private String id = "20374090";
    private String email = "3232572736@qq.com";
    private String password = "123456_ABCD";
    private int downloadNum = 0;
    private String date;
    private String oldPassword;




    private UserCentreFrm(){
        UserData userData = UserData.getInstance();
        if(userData.getUserName().equals("这里要写十个字差三个")){
            //TODO:一个提示未加载完成的函数，洲：我觉得不用提示就等加载完了再显示就行
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

        this.setTitle("个人信息");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        int y = WIDGET_Y;


        Container container = this.getContentPane();

        JLabel jWrongPasswordType = new JLabel();
        jWrongPasswordType.setText("密码不合法");
        jWrongPasswordType.setForeground(Color.RED);
        jWrongPasswordType.setBounds(WIDGET_X,20+4*FIELD_HEIGHT,WIDGET_GAP,50);
        jWrongPasswordType.setHorizontalAlignment(JLabel.RIGHT);
        jWrongPasswordType.setVerticalAlignment(JLabel.CENTER);
        jWrongPasswordType.setVisible(false);
        container.add(jWrongPasswordType);

        JLabel jWrongRePassword = new JLabel();
        jWrongRePassword.setText("密码不一致");
        jWrongRePassword.setForeground(Color.RED);
        jWrongRePassword.setBounds(WIDGET_X,20+5*FIELD_HEIGHT,WIDGET_GAP,50);
        jWrongRePassword.setHorizontalAlignment(JLabel.RIGHT);
        jWrongRePassword.setVerticalAlignment(JLabel.CENTER);
        jWrongRePassword.setVisible(false);
        container.add(jWrongRePassword);

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

        JLabel jLabelTip = new JLabel();
        jLabelTip.setText("<html>注意：密码应该为8-16位，只能由大小写字母，下划线和数字中的一种或几种组成，要求以字母开头</html>");
        jLabelTip.setBounds(WIDGET_X,20+3*FIELD_HEIGHT,WIDGET_GAP,50);
        //jLabelTip.setFont(MyFonts.TEXT_FONT_18);
        jLabelTip.setVisible(false);
        container.add(jLabelTip);

        JLabel jLabelPassword = new JLabel();
        jLabelPassword.setText("新密码：");
        jLabelPassword.setBounds(WIDGET_X,20+4*FIELD_HEIGHT,WIDGET_GAP/2,50);
        jLabelPassword.setFont(MyFonts.SUB_TITLE_FONT_24);
        jLabelPassword.setVisible(false);
        container.add(jLabelPassword);

        JLabel jLabelRePassword = new JLabel();
        jLabelRePassword.setText("确认密码：");
        jLabelRePassword.setBounds(WIDGET_X,20+5*FIELD_HEIGHT,WIDGET_GAP,50);
        jLabelRePassword.setFont(MyFonts.SUB_TITLE_FONT_24);
        jLabelRePassword.setVisible(false);
        container.add(jLabelRePassword);

        JPasswordField jPasswordFieldNew = new JPasswordField();
        jPasswordFieldNew.setBounds(140,30+4*FIELD_HEIGHT,WIDGET_GAP/2,30);
        jPasswordFieldNew.setVisible(false);
        container.add(jPasswordFieldNew);

        JPasswordField jPasswordFieldRenew = new JPasswordField();
        jPasswordFieldRenew.setBounds(140,30+5*FIELD_HEIGHT,WIDGET_GAP/2,30);
        jPasswordFieldRenew.setVisible(false);
        container.add(jPasswordFieldRenew);


        JFrame passwordWin = new JFrame();
        passwordWin.setLayout(null);
        passwordWin.setResizable(false);
        passwordWin.setSize(300,200);


        JLabel jErrorWrongPassword = new JLabel();
        jErrorWrongPassword.setText("密码输入错误");
        jErrorWrongPassword.setForeground(Color.RED);
        jErrorWrongPassword.setBounds(WIDGET_X,10,300-3*WIDGET_X,30);
        jErrorWrongPassword.setHorizontalAlignment(JLabel.RIGHT);
        jErrorWrongPassword.setVisible(false);
        passwordWin.add(jErrorWrongPassword);

        JLabel jLabelInput = new JLabel();
        jLabelInput.setText("请输入初始密码：");
        jLabelInput.setBounds(WIDGET_X,10,WIDGET_GAP,30);
        passwordWin.add(jLabelInput);

        JPasswordField jPasswordField = new JPasswordField();
        jPasswordField.setBounds(WIDGET_X,FIELD_HEIGHT,240,30);

        JButton jButtonChangePassword = new JButton();
        jButtonChangePassword.setText("修改密码");
        jButtonChangePassword.setBounds(2*WIDGET_X,4*FIELD_HEIGHT,WIDGET_GAP/2,30);
        container.add(jButtonChangePassword);
        jButtonChangePassword.addActionListener(evt -> {
            System.out.println("UserCentreFrm : Click change password button");
            this.enWaitMode();
            passwordWin.setVisible(true);
        });

        // 确认使用新密码的函数
        JButton jButtonCancelPassword = new JButton();
        JButton jButtonContinueChange = new JButton();
        jButtonContinueChange.setText("确认修改");
        jButtonContinueChange.setBounds(20,40+6*FIELD_HEIGHT,WIDGET_GAP/2-20,30);
        jButtonContinueChange.setVisible(false);
        jButtonContinueChange.addActionListener(evt ->{
            System.out.println("UserCentre : Click continue change password button");
            String password0 = new String(jPasswordFieldNew.getPassword());
            String password1 = new String(jPasswordFieldRenew.getPassword());
            if(MyBootFrame.isLegalPassword(password0)){
                if(password0.matches(password1)){
                    if(UserCentreCtrl.Change(userName,id,email,password0,downloadNum,date)){
                        jButtonChangePassword.setVisible(true);
                        jButtonContinueChange.setVisible(false);
                        jButtonCancelPassword.setVisible(false);
                        jLabelPassword.setVisible(false);
                        jLabelRePassword.setVisible(false);
                        jPasswordFieldNew.setVisible(false);
                        jPasswordFieldRenew.setVisible(false);
                        jPasswordFieldNew.setText("");
                        jPasswordFieldRenew.setText("");
                        jWrongPasswordType.setVisible(false);
                        jWrongRePassword.setVisible(false);
                        jLabelTip.setVisible(false);
                    }

                } else {
                    jWrongRePassword.setVisible(true);
                    jPasswordFieldRenew.setText("");
                }
            } else {
                jWrongPasswordType.setVisible(true);
                jPasswordFieldNew.setText("");
                jPasswordFieldRenew.setText("");
            }

        });
        container.add(jButtonContinueChange);

        // 关闭修改密码界面的函数
        jButtonCancelPassword.setText("取消修改");
        jButtonCancelPassword.setVisible(false);
        jButtonCancelPassword.setBounds(WIDGET_GAP/2+20,40+6*FIELD_HEIGHT,WIDGET_GAP/2-20,30);
        jButtonCancelPassword.addActionListener(evt ->{
            System.out.println("UserCentre : Click cancel change password button");
            jButtonChangePassword.setVisible(true);
            jButtonContinueChange.setVisible(false);
            jButtonCancelPassword.setVisible(false);
            jLabelPassword.setVisible(false);
            jLabelRePassword.setVisible(false);
            jPasswordFieldNew.setVisible(false);
            jPasswordFieldRenew.setVisible(false);
            jWrongPasswordType.setVisible(false);
            jWrongRePassword.setVisible(false);
            jLabelTip.setVisible(false);
            jPasswordFieldNew.setText("");
            jPasswordFieldRenew.setText("");
        });
        container.add(jButtonCancelPassword);


        // 输入旧密码确认的函数
        //TODO：需要加回车逻辑
        JButton jButtonYes = new JButton();
        jButtonYes.setText("确认");
        jButtonYes.setBounds(30,80,100,30);
        jButtonYes.addActionListener(evt -> {
            System.out.println("UserCentreFrmWin : Click yes button");
            oldPassword = new String(jPasswordField.getPassword());
            if(oldPassword.matches(this.password)){
                jErrorWrongPassword.setVisible(false);
                jButtonChangePassword.setVisible(false);
                jLabelPassword.setVisible(true);
                jLabelRePassword.setVisible(true);
                jButtonContinueChange.setVisible(true);
                jButtonCancelPassword.setVisible(true);
                jPasswordFieldNew.setVisible(true);
                jPasswordFieldRenew.setVisible(true);
                jLabelTip.setVisible(true);
                jPasswordField.setText("");
                passwordWin.setVisible(false);
                passwordWin.dispose();
            } else {
                jErrorWrongPassword.setVisible(true);
                jPasswordField.setText("");
            }
        });

        JButton jButtonNo = new JButton();
        jButtonNo.setText("重置");
        jButtonNo.setBounds(150,80,100,30);
        jButtonNo.addActionListener(evt -> {
            System.out.println("UserCentreFrmWin : Click no button");
            jPasswordField.setText("");
            jErrorWrongPassword.setVisible(false);
        });


        passwordWin.add(jPasswordField);
        passwordWin.add(jButtonYes);
        passwordWin.add(jButtonNo);
        passwordWin.setVisible(false);

    }

    public static UserCentreFrm getInstance() {
        if(userCentreFrm == null) {
            synchronized (UserCentreFrm.class) {
                if(userCentreFrm == null) {
                    userCentreFrm = new UserCentreFrm();
                }
            }
        }

        return userCentreFrm;
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

        UserCentreFrm userCentreFrmTest = getInstance();
        myFrameTest.getTable().add(userCentreFrmTest);
        userCentreFrmTest.setVisible(true);
        myFrameTest.setVisible(true);
    }
}
