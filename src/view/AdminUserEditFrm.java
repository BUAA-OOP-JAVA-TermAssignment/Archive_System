package view;

import controller.AdminMainCtrl;
import message.AdminUserRequestMsg;
import style.MyColors;
import style.MyFonts;
import message.AdminUserEditMsg;
import style.StyleCtrl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class AdminUserEditFrm extends MyInterFrame {


    public final static int WIDTH_TABLE = 1350;
    public final static int HEIGHT_ROW = 60;
    public final static int HEIGHT_DETAIL = 50;
    public final static int HEIGHT_BODY = 500;
    public final static int WIDTH_BASE = 25;
    public final static int TABLE_X = 100;
    public final static int TABLE_Y = 100;

    public final JLabel msgLabel = new JLabel();
    private JPanel jPanelTableHeader = new JPanel();
    private JPanel jPanelTableBody = new JPanel();
    private JPanel[] jPanelsUsers = new JPanel[]{
            new JPanel(),
            new JPanel(),
            new JPanel(),
            new JPanel(),
            new JPanel(),
            new JPanel(),
            new JPanel(),
            new JPanel(),
            new JPanel(),
            new JPanel()
    };

    private int usersNum = 0;
    private int pagesNum = 0;
    private int whichPage = 0;

    private static volatile AdminUserEditFrm adminEditFrm = null;
    private static Container container;

    private AdminUserEditFrm() {
        super();

        initComponents();
    }

    public static AdminUserEditFrm createAdminEditFrm() {
        if (adminEditFrm == null) {
            synchronized (AdminUserEditFrm.class) {
                if (adminEditFrm == null) {
                    adminEditFrm = new AdminUserEditFrm();
                }
            }
        }

        return adminEditFrm;
    }

    private void initComponents() {
        initTitle();
        initFrame();
        initTableHeader();
        if(AdminMainCtrl.tryLoad()){
            initTableBody();
        } else {
            System.out.println("没有数据");
            //TODO:暂无数据
        }
    }

    private void initTitle() {
        //TODO:有一个主页面最上方的title捏
    }

    private void initFrame() {
        this.setTitle("管理员个人中心");
        container = this.getContentPane();
    }

    private void initTableHeader() {
        jPanelTableHeader.setVisible(true);
        jPanelTableHeader.setLayout(null);
        jPanelTableHeader.setBackground(Color.LIGHT_GRAY);
        jPanelTableHeader.setSize(WIDTH_TABLE, HEIGHT_ROW);
        jPanelTableHeader.setLocation(TABLE_X, TABLE_Y);
        container.add(jPanelTableHeader);
        initHeaderLabel();
    }

    private void initTableBody() {
        jPanelTableBody.setVisible(true);
        jPanelTableBody.setLayout(null);
        jPanelTableBody.setBackground(Color.PINK);
        jPanelTableBody.setSize(WIDTH_TABLE, HEIGHT_BODY);
        jPanelTableBody.setLocation(TABLE_X, TABLE_Y + HEIGHT_ROW);
        initBodyContent();
        container.add(jPanelTableBody);

    }

    private void initHeaderLabel() {
        int y = 0;
        JLabel jLabelIndex = new JLabel();
        jLabelIndex.setText("序号");
        jLabelIndex.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelIndex.setBounds(WIDTH_BASE, 0, y + 2 * WIDTH_BASE, HEIGHT_ROW);
        jLabelIndex.setVisible(true);
        jLabelIndex.setVerticalAlignment(SwingConstants.CENTER);
        jLabelIndex.setHorizontalAlignment(SwingConstants.CENTER);
        y += 3 * WIDTH_BASE;
        jPanelTableHeader.add(jLabelIndex);

        JLabel jLabelID = new JLabel();
        jLabelID.setText("学号");
        jLabelID.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelID.setBounds(y, 0, 4 * WIDTH_BASE, HEIGHT_ROW);
        jLabelID.setVisible(true);
        jLabelID.setVerticalAlignment(SwingConstants.CENTER);
        jLabelID.setHorizontalAlignment(SwingConstants.CENTER);
        y += 4 * WIDTH_BASE;
        jPanelTableHeader.add(jLabelID);

        JLabel jLabelName = new JLabel();
        jLabelName.setText("姓名");
        jLabelName.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelName.setBounds(y, 0, 10 * WIDTH_BASE, HEIGHT_ROW);
        jLabelName.setVisible(true);
        jLabelName.setVerticalAlignment(SwingConstants.CENTER);
        jLabelName.setHorizontalAlignment(SwingConstants.CENTER);
        y += 10 * WIDTH_BASE;
        jPanelTableHeader.add(jLabelName);

        JLabel jLabelEmail = new JLabel();
        jLabelEmail.setText("邮箱");
        jLabelEmail.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelEmail.setBounds(y, 0, 11 * WIDTH_BASE, HEIGHT_ROW);
        jLabelEmail.setVisible(true);
        jLabelEmail.setVerticalAlignment(SwingConstants.CENTER);
        jLabelEmail.setHorizontalAlignment(SwingConstants.CENTER);
        y += 11 * WIDTH_BASE;
        jPanelTableHeader.add(jLabelEmail);

        JLabel jLabelDownloadCnt = new JLabel();
        jLabelDownloadCnt.setText("下载量");
        jLabelDownloadCnt.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelDownloadCnt.setBounds(y, 0, 3 * WIDTH_BASE, HEIGHT_ROW);
        jLabelDownloadCnt.setVisible(true);
        jLabelDownloadCnt.setVerticalAlignment(SwingConstants.CENTER);
        jLabelDownloadCnt.setHorizontalAlignment(SwingConstants.CENTER);
        y += 3 * WIDTH_BASE;
        jPanelTableHeader.add(jLabelDownloadCnt);

        JLabel jLabelDate = new JLabel();
        jLabelDate.setText("最近登录时间");
        jLabelDate.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelDate.setBounds(y, 0, 10 * WIDTH_BASE, HEIGHT_ROW);
        jLabelDate.setVisible(true);
        jLabelDate.setVerticalAlignment(SwingConstants.CENTER);
        jLabelDate.setHorizontalAlignment(SwingConstants.CENTER);
        y += 10 * WIDTH_BASE;
        jPanelTableHeader.add(jLabelDate);

        JLabel jLabelOption = new JLabel();
        jLabelOption.setText("操作选项");
        jLabelOption.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelOption.setBounds(y, 0, 6 * WIDTH_BASE, HEIGHT_ROW);
        jLabelOption.setVisible(true);
        jLabelOption.setVerticalAlignment(SwingConstants.CENTER);
        jLabelOption.setHorizontalAlignment(SwingConstants.CENTER);
        jPanelTableHeader.add(jLabelOption);

    }

    private void initBodyContent() {

        usersNum = 1;//AdminUserRequestMsg.getUserNum();
        if (usersNum == 0) {
            //TODO: 一个暂无数据的标

        } else {
            initDetailPanels();
            for (int i = 0; i < 10 && i < usersNum; i++) {
                jPanelsUsers[i].setVisible(true);
                addDetailMsg(i);
            }

        }
    }

    /**
     * 用来初始化用户信息的每一个横行的样式
     */
    private void initDetailPanels() {
        for (int i = 0; i < 10; i++) {
            container.add(jPanelsUsers[i]);
            jPanelsUsers[i].setLayout(null);
            if (i % 2 == 0) {
                jPanelsUsers[i].setBackground(Color.LIGHT_GRAY);
            } else {
                jPanelsUsers[i].setBackground(Color.WHITE);
            }
            jPanelsUsers[i].setSize(WIDTH_TABLE, HEIGHT_DETAIL);
            jPanelsUsers[i].setLocation(TABLE_X, TABLE_Y + HEIGHT_ROW + i * HEIGHT_DETAIL);
        }
    }

    private void addDetailMsg(int i) {
        int y = 0;
        JTextField jTextFieldIndex = new JTextField();
        jTextFieldIndex.setEditable(false);
        jTextFieldIndex.setOpaque(false);
        jTextFieldIndex.setText(String.valueOf((i + whichPage * 10)));
        jTextFieldIndex.setFont(MyFonts.TEXT_FONT_18);
        jTextFieldIndex.setBounds(WIDTH_BASE, 0, y + 2 * WIDTH_BASE, HEIGHT_ROW);
        jTextFieldIndex.setVisible(true);
        jTextFieldIndex.setHorizontalAlignment(SwingConstants.CENTER);
        y += 3 * WIDTH_BASE;
        jPanelsUsers[i].add(jTextFieldIndex);

        JTextField jTextFieldID = new JTextField();
        jTextFieldID.setEditable(false);
        jTextFieldID.setOpaque(false);
        //jTextFieldID.setText(AdminUserRequestMsg);
        jTextFieldID.setFont(MyFonts.TEXT_FONT_18);
        jTextFieldID.setBounds(y, 0, 4 * WIDTH_BASE, HEIGHT_ROW);
        jTextFieldID.setVisible(true);
        //jTextFieldID.setVerticalAlignment(SwingConstants.CENTER);
        jTextFieldID.setHorizontalAlignment(SwingConstants.CENTER);
        y += 4 * WIDTH_BASE;
        jPanelsUsers[i].add(jTextFieldID);

        JTextField jTextFieldName = new JTextField();
        jTextFieldName.setEditable(false);
        jTextFieldName.setOpaque(false);
        //jTextFieldName.setText(adminUserEditMsg.getUserName());
        jTextFieldName.setFont(MyFonts.TEXT_FONT_18);
        jTextFieldName.setBounds(y, 0, 10 * WIDTH_BASE, HEIGHT_ROW);
        jTextFieldName.setVisible(true);
        //jTextFieldName.setVerticalAlignment(SwingConstants.CENTER);
        jTextFieldName.setHorizontalAlignment(SwingConstants.CENTER);
        y += 10 * WIDTH_BASE;
        jPanelsUsers[i].add(jTextFieldName);

        JTextField jTextFieldEmail = new JTextField();
        jTextFieldEmail.setEditable(false);
        jTextFieldEmail.setOpaque(false);
       // jTextFieldEmail.setText(adminUserEditMsg.getUserEmail(adminUserEditMsg.getUserInfo(i)));
        jTextFieldEmail.setFont(MyFonts.TEXT_FONT_18);
        jTextFieldEmail.setBounds(y, 0, 11 * WIDTH_BASE, HEIGHT_ROW);
        jTextFieldEmail.setVisible(true);
        //jTextFieldEmail.setVerticalAlignment(SwingConstants.CENTER);
        jTextFieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
        y += 11 * WIDTH_BASE;
        jPanelsUsers[i].add(jTextFieldEmail);

        JTextField jTextFieldDownloadCnt = new JTextField();
        jTextFieldDownloadCnt.setEditable(false);
        jTextFieldDownloadCnt.setOpaque(false);
       // jTextFieldDownloadCnt.setText(String.valueOf(adminUserEditMsg.getUserDownloadCnt(adminUserEditMsg.getUserInfo(i))));
        jTextFieldDownloadCnt.setFont(MyFonts.TEXT_FONT_18);
        jTextFieldDownloadCnt.setBounds(y, 0, 3 * WIDTH_BASE, HEIGHT_ROW);
        //jTextFieldDownloadCnt.setVerticalAlignment(SwingConstants.CENTER);
        jTextFieldDownloadCnt.setHorizontalAlignment(SwingConstants.CENTER);
        y += 3 * WIDTH_BASE;
        jPanelsUsers[i].add(jTextFieldDownloadCnt);
        jTextFieldDownloadCnt.setVisible(true);

        JTextField jTextFieldDate = new JTextField();
        jTextFieldDate.setEditable(false);
        jTextFieldDate.setOpaque(false);
       // jTextFieldDate.setText(adminUserEditMsg.getUserDate(adminUserEditMsg.getUserInfo(i)));
        jTextFieldDate.setFont(MyFonts.TEXT_FONT_18);
        jTextFieldDate.setBounds(y, 0, 10 * WIDTH_BASE, HEIGHT_ROW);
        jTextFieldDate.setVisible(true);
        //jTextFieldDate.setVerticalAlignment(SwingConstants.CENTER);
        jTextFieldDate.setHorizontalAlignment(SwingConstants.CENTER);
        y += 10 * WIDTH_BASE;
        jPanelsUsers[i].add(jTextFieldDate);

        AtomicReference<String> oldEmail;
        JButton jButtonChangePassword = new JButton();
        JButton jButtonYes = new JButton();
        JButton jButtonReset = new JButton();
        JButton jButtonNo = new JButton();
        JButton jButtonChangeEmail = new JButton();

        jButtonYes.setVisible(false);
        jButtonYes.setText("确认修改");
        jButtonYes.setBounds(y, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
        jButtonYes.setVerticalAlignment(SwingConstants.CENTER);
        jButtonYes.addActionListener(evt -> {
            System.out.println("AdminMainFrm : Click yes button");
            String newEmail = jTextFieldEmail.getText();
            if(MyBootFrame.isLegalEmail(newEmail)){

            } else {
                jTextFieldEmail.setForeground(Color.RED);
            }
        });
        jPanelsUsers[i].add(jButtonYes);


        jButtonReset.setVisible(true);
        jButtonReset.setText("重置次数");
        jButtonReset.setBounds(y, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
        jButtonReset.setVerticalAlignment(SwingConstants.CENTER);
        jButtonReset.addActionListener(evt -> {
            System.out.println("AdminMainFrm : Click reset button");
            jTextFieldDownloadCnt.setText("0");
        //    adminUserEditMsg.changeDownloadCnt(0);
        });
        y+=4*WIDTH_BASE;
        jPanelsUsers[i].add(jButtonReset);


        jButtonNo.setVisible(false);
        jButtonNo.setText("取消修改");
        jButtonNo.setBounds(y, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
        jButtonNo.setVerticalAlignment(SwingConstants.CENTER);
        jButtonNo.addActionListener(evt -> {
            System.out.println("AdminMainFrm : Click cancel button");
            jButtonReset.setVisible(true);
            jButtonChangePassword.setVisible(true);
            jButtonYes.setVisible(false);
            jButtonNo.setVisible(false);
        //    jTextFieldEmail.setText(String.valueOf(oldEmail));
            jTextFieldEmail.setEditable(false);
        });
        jPanelsUsers[i].add(jButtonNo);


        jButtonChangePassword.setVisible(true);
        jButtonChangePassword.setText("修改密码");
        jButtonChangePassword.setBounds(y, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
        jButtonChangePassword.setVerticalAlignment(SwingConstants.CENTER);
        jButtonChangePassword.addActionListener(evt -> {
            System.out.println("AdminMainFrm : Click change password button");
            //TODO
        });
        y+=4*WIDTH_BASE;
        jPanelsUsers[i].add(jButtonChangePassword);



        jButtonChangeEmail.setVisible(true);
        jButtonChangeEmail.setText("修改邮箱");
        jButtonChangeEmail.setBounds(y, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
        jButtonChangeEmail.setVerticalAlignment(SwingConstants.CENTER);
        jButtonChangeEmail.setVisible(true);
        jButtonChangeEmail.addActionListener(evt -> {
            System.out.println("AdminMainFrm : Click change email button");
            jTextFieldEmail.setEditable(true);
            jButtonReset.setVisible(false);
            jButtonChangePassword.setVisible(false);
            jButtonNo.setVisible(true);
            jButtonYes.setVisible(true);
       //     oldEmail.set(jTextFieldEmail.getText());
        });

        jPanelsUsers[i].add(jButtonChangeEmail);




//        JTextField jTextFieldOption = new JTextField();
//        jTextFieldOption.setOpaque(false);
//        jTextFieldOption.setText("操作选项");
//        jTextFieldOption.setFont(MyFonts.TEXT_FONT_18);
//        jTextFieldOption.setBounds(y, 0, 6 * WIDTH_BASE, HEIGHT_ROW);
//        jTextFieldOption.setVisible(true);
//        //jTextFieldOption.setVerticalAlignment(SwingConstants.CENTER);
//        jTextFieldOption.setHorizontalAlignment(SwingConstants.CENTER);
//        jPanelsUsers[i].add(jTextFieldOption);
    }


    public void sendMsgNotice() {
        msgLabel.setText("正在与数据库连接...");
        msgLabel.setForeground(Color.YELLOW);
        msgLabel.setVisible(true);
    }

    public void timeoutError() {
        msgLabel.setText("服务器连接超时，请稍后重试");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARCULA);
        MyFrame myFrameTest = new MyFrame() {
        };
        AdminUserEditFrm adminMainFrm1 = AdminUserEditFrm.createAdminEditFrm();
        myFrameTest.getTable().add(adminMainFrm1);
        adminMainFrm1.setVisible(true);
        myFrameTest.setVisible(true);
    }
}
