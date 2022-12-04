package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOnFrm {
    // todo: 登录不需要继承MyFrame
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
        //todo：爆红先不管，一些变量没加进来，主要看看后续能否简化
        javax.swing.ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        userNameTxt = new javax.swing.JFormattedTextField();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        jrb_student = new javax.swing.JRadioButton();
        jrb_admin = new javax.swing.JRadioButton();
        javax.swing.JButton jb_logOn = new javax.swing.JButton();
        javax.swing.JButton jb_reset = new javax.swing.JButton();
        passwordTxt = new javax.swing.JPasswordField();
        //todo：离谱缩进前面都懂了
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("\u7528\u6237\u767b\u5f55");
        setResizable(false);

        //todo：以下的路径都需要重新配图片
        jLabel1.setFont(new java.awt.Font("隶书", 1, 24));
        jLabel1
                .setIcon(new javax.swing.ImageIcon(
                        "D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\sys.png")); // NOI18N
        jLabel1.setText("\u5b66\u751f\u9009\u8bfe\u7cfb\u7edf");

        jLabel2
                .setIcon(new javax.swing.ImageIcon(
                        "D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\userName.png")); // NOI18N
        jLabel2.setText("\u8d26\u53f7");

        jLabel3
                .setIcon(new javax.swing.ImageIcon(
                        "D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\password.png")); // NOI18N
        jLabel3.setText("\u5bc6\u7801");

        buttonGroup1.add(jrb_student);
        jrb_student.setText("\u5b66\u751f");
        jrb_student
                .setIcon(new javax.swing.ImageIcon(
                        "D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\student.png")); // NOI18N

        buttonGroup1.add(jrb_admin);
        jrb_admin.setText("\u7ba1\u7406\u5458");
        jrb_admin
                .setIcon(new javax.swing.ImageIcon(
                        "D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\admin.png")); // NOI18N

        jb_logOn
                .setIcon(new javax.swing.ImageIcon(
                        "D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\login.png")); // NOI18N
        jb_logOn.setText("\u767b\u5f55");
        jb_logOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_logOnActionPerformed(evt);
            }
        });

        jb_reset
                .setIcon(new javax.swing.ImageIcon(
                        "D:\\Workspaces\\MyEclipse 8.5\\CourseSys\\src\\com\\jakey\\view\\image\\reset.png")); // NOI18N
        jb_reset.setText("\u91cd\u7f6e");
        jb_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_resetActionPerformed(evt);
            }
        });
    }
}
