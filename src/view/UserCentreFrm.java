package view;

import style.StyleCtrl;

import javax.swing.*;

public class UserCentreFrm extends MyInterFrame{
    private String userName;
    private String id;
    private String email;
    private String password;
    private String downloadNum;


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

    public static void main(String[] args){
        StyleCtrl.setStyle(StyleCtrl.DARCULA);
        GuestMainFrm guestMainFrmTest = new GuestMainFrm();
        guestMainFrmTest.setVisible(true);
    }
}
