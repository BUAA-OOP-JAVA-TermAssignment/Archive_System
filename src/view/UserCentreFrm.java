package view;

import controller.LogonRegisterCtrl;
import data.UserData;
import style.MyFonts;
import style.StyleCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EventListener;
import java.util.concurrent.atomic.AtomicReference;

public class UserCentreFrm extends MyInterFrame{
    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 550;
    private static final int WIDGET_X = 20;
    private static final int WIDGET_Y = 50;
    private static final int WIDGET_GAP = 400;
    private static final int FIELD_HEIGHT = 40;

    private String userName = "����Ҫдʮ���ֲ�����";
    private String id = "20374090";
    private String email = "3232572736@qq.com";
    private String password = "123456_ABCD";
    private String downloadNum = null;
    private String oldPassword;




    public UserCentreFrm(){
        UserData userData = UserData.getInstance();
//        if(userData.getUserName().equals("�ο�12138")){
//            //TODO:һ����ʾδ������ɵĺ���
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

        this.setTitle("������Ϣ");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        int y = WIDGET_Y;


        Container container = this.getContentPane();

        JLabel jLabelUserName = new JLabel();
        jLabelUserName.setText(this.userName);
        jLabelUserName.setBounds(WIDGET_X,20,WIDGET_GAP,50);
        jLabelUserName.setFont(MyFonts.TITLE_FONT_36);
        container.add(jLabelUserName);

        JLabel jLabelUserId = new JLabel();
        jLabelUserId.setText("ѧ���ţ�"+this.id);
        jLabelUserId.setBounds(WIDGET_X,20+FIELD_HEIGHT,WIDGET_GAP,50);
        jLabelUserId.setFont(MyFonts.SUB_TITLE_FONT_24);
        container.add(jLabelUserId);

        JLabel jLabelEmail = new JLabel();
        jLabelEmail.setText("ע�����䣺"+this.email);
        jLabelEmail.setBounds(WIDGET_X,20+2*FIELD_HEIGHT,WIDGET_GAP,50);
        jLabelEmail.setFont(MyFonts.SUB_TITLE_FONT_24);
        container.add(jLabelEmail);

        JLabel jLabelPassword = new JLabel();
        jLabelPassword.setText("�����룺");
        jLabelPassword.setBounds(WIDGET_X,20+3*FIELD_HEIGHT,WIDGET_GAP/2,50);
        jLabelPassword.setFont(MyFonts.SUB_TITLE_FONT_24);
        jLabelPassword.setVisible(false);
        container.add(jLabelPassword);

        JLabel jLabelRePassword = new JLabel();
        jLabelRePassword.setText("ȷ�����룺");
        jLabelRePassword.setBounds(WIDGET_X,20+4*FIELD_HEIGHT,WIDGET_GAP,50);
        jLabelRePassword.setFont(MyFonts.SUB_TITLE_FONT_24);
        jLabelRePassword.setVisible(false);
        container.add(jLabelRePassword);

        JPasswordField jPasswordFieldNew = new JPasswordField();
        jPasswordFieldNew.setBounds(WIDGET_X+WIDGET_GAP/2,30+3*FIELD_HEIGHT,WIDGET_GAP/2,30);
        jPasswordFieldNew.setVisible(false);
        container.add(jPasswordFieldNew);

        JPasswordField jPasswordFieldRenew = new JPasswordField();
        jPasswordFieldRenew.setBounds(WIDGET_X+WIDGET_GAP/2,30+4*FIELD_HEIGHT,WIDGET_GAP/2,30);
        jPasswordFieldRenew.setVisible(false);
        container.add(jPasswordFieldRenew);


        JFrame passwordWin = new JFrame();
        passwordWin.setLayout(null);
        passwordWin.setResizable(false);
        passwordWin.setSize(300,200);

        JLabel jLabelInput = new JLabel();
        jLabelInput.setText("�������ʼ���룺");
        jLabelInput.setBounds(20,10,WIDGET_GAP,30);
        jLabelInput.setFont(MyFonts.TEXT_FONT_18);
        passwordWin.add(jLabelInput);

        JPasswordField jPasswordField = new JPasswordField();
        jPasswordField.setBounds(2*WIDGET_X,FIELD_HEIGHT,WIDGET_GAP/2,30);

        JButton jButtonChangePassword = new JButton();
        jButtonChangePassword.setText("�޸�����");
        jButtonChangePassword.setBounds(2*WIDGET_X,4*FIELD_HEIGHT,WIDGET_GAP/2,30);
        container.add(jButtonChangePassword);
        jButtonChangePassword.addActionListener(evt -> {
            System.out.println("UserCentreFrm : Click change password button");
            this.enWaitMode();
            passwordWin.setVisible(true);
        });

        JButton jButtonCancelPassword = new JButton();
        JButton jButtonContinueChange = new JButton();
        jButtonContinueChange.setText("ȷ���޸�");
        jButtonContinueChange.setBounds(20,40+5*FIELD_HEIGHT,WIDGET_GAP/2-20,30);
        jButtonContinueChange.setVisible(false);
        jButtonContinueChange.addActionListener(evt ->{
            System.out.println("UserCentre : Click continue change password button");
            String password0 = new String(jPasswordFieldNew.getPassword());
            String password1 = new String(jPasswordFieldRenew.getPassword());
            if(MyBootFrame.isLegalPassword(password0)){
                if(password0.matches(password1)){
                    jButtonChangePassword.setVisible(true);
                    jButtonContinueChange.setVisible(false);
                    jButtonCancelPassword.setVisible(false);
                    jLabelPassword.setVisible(false);
                    jLabelRePassword.setVisible(false);
                    jPasswordFieldNew.setVisible(false);
                    jPasswordFieldRenew.setVisible(false);
                    jPasswordFieldNew.setText("");
                    jPasswordFieldRenew.setText("");
                } else {
                    //TODO:ERROR1
                }
            } else {
                //TODO:ERROR2
            }

        });
        container.add(jButtonContinueChange);


        jButtonCancelPassword.setText("ȡ���޸�");
        jButtonCancelPassword.setVisible(false);
        jButtonCancelPassword.setBounds(WIDGET_GAP/2+20,40+5*FIELD_HEIGHT,WIDGET_GAP/2-20,30);
        jButtonCancelPassword.addActionListener(evt ->{
            System.out.println("UserCentre : Click cancel change password button");
            jButtonChangePassword.setVisible(true);
            jButtonContinueChange.setVisible(false);
            jButtonCancelPassword.setVisible(false);
            jLabelPassword.setVisible(false);
            jLabelRePassword.setVisible(false);
            jPasswordFieldNew.setVisible(false);
            jPasswordFieldRenew.setVisible(false);
            jPasswordFieldNew.setText("");
            jPasswordFieldRenew.setText("");
        });
        container.add(jButtonCancelPassword);

        JButton jButtonYes = new JButton();
        jButtonYes.setText("ȷ��");
        jButtonYes.setBounds(30,80,100,30);
        jButtonYes.addActionListener(evt -> {
            System.out.println("UserCentreFrmWin : Click yes button");
            oldPassword = new String(jPasswordField.getPassword());
            if(oldPassword.matches(this.password)){
                jButtonChangePassword.setVisible(false);
                jLabelPassword.setVisible(true);
                jLabelRePassword.setVisible(true);
                jButtonContinueChange.setVisible(true);
                jButtonCancelPassword.setVisible(true);
                jPasswordFieldNew.setVisible(true);
                jPasswordFieldRenew.setVisible(true);
                jPasswordField.setText("");
                passwordWin.setVisible(false);
                passwordWin.dispose();
            } else {
                //TODO :��һ��������ʾ
            }
        });

        JButton jButtonNo = new JButton();
        jButtonNo.setText("����");
        jButtonNo.setBounds(150,80,100,30);
        jButtonNo.addActionListener(evt -> {
            System.out.println("UserCentreFrmWin : Click no button");

        });


        passwordWin.add(jPasswordField);
        //passwordWin.add(jButtonNo);
        passwordWin.add(jButtonYes);
        passwordWin.add(jButtonNo);
        passwordWin.setVisible(false);

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