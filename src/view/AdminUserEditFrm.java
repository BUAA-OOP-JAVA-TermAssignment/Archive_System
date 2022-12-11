package view;

import controller.AdminMainCtrl;
import data.AdminData;
import style.MyFonts;
import style.StyleCtrl;

import javax.swing.*;
import java.awt.*;


public class AdminUserEditFrm extends MyInterFrame {

    private static final int WIDGET_X = 20;
    private static final int WIDGET_GAP = 400;
    private static final int FIELD_HEIGHT = 40;


    public final static int WIDTH_TABLE = 1350;
    public final static int HEIGHT_ROW = 60;
    public final static int HEIGHT_DETAIL = 50;
    public final static int HEIGHT_BODY = 550;
    public final static int WIDTH_BASE = 25;
    public final static int TABLE_X = 100;
    public final static int TABLE_Y = 100;

    public final JLabel msgLabel = new JLabel();
    private final JPanel jPanelTableHeader = new JPanel();
    private final JPanel jPanelTableBody = new JPanel();
    private final JPanel[] jPanelsUsers = new JPanel[]{
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
    private AdminData adminData = AdminData.getInstance();

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

    /**
     * 初始化加载模块
     */

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

    /**
     * 初始化管理员主界面最上方的标题
     */

    private void initTitle() {
        //TODO:有一个主页面最上方的title捏
    }

    /**
     * 初始化整个框架
     */

    private void initFrame() {
        this.setTitle("管理员个人中心");
        container = this.getContentPane();
    }

    /**
     * 用来加载用户信息的表头
     */

    private void initTableHeader() {
        jPanelTableHeader.setVisible(true);
        jPanelTableHeader.setLayout(null);
        jPanelTableHeader.setBackground(Color.LIGHT_GRAY);
        jPanelTableHeader.setSize(WIDTH_TABLE, HEIGHT_ROW);
        jPanelTableHeader.setLocation(TABLE_X, TABLE_Y);
        container.add(jPanelTableHeader);
        initHeaderLabel();
    }

    /**
     * 用来加载整体的用户信息主体内容
     */
    private void initTableBody() {
        jPanelTableBody.setVisible(true);
        jPanelTableBody.setLayout(null);
        jPanelTableBody.setBackground(Color.PINK);
        jPanelTableBody.setSize(WIDTH_TABLE, HEIGHT_BODY);
        jPanelTableBody.setLocation(TABLE_X, TABLE_Y + HEIGHT_ROW);
        initBodyContent();
        intiBodyPage();
        container.add(jPanelTableBody);

    }

    /**
     * 用来加载展示用户信息的表头内容
     */

    private void initHeaderLabel() {
        int x = 0;
        JLabel jLabelIndex = new JLabel();
        jLabelIndex.setText("序号");
        jLabelIndex.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelIndex.setBounds(WIDTH_BASE, 0, x + 2 * WIDTH_BASE, HEIGHT_ROW);
        jLabelIndex.setVisible(true);
        jLabelIndex.setVerticalAlignment(SwingConstants.CENTER);
        jLabelIndex.setHorizontalAlignment(SwingConstants.CENTER);
        x += 3 * WIDTH_BASE;
        jPanelTableHeader.add(jLabelIndex);

        JLabel jLabelID = new JLabel();
        jLabelID.setText("学号");
        jLabelID.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelID.setBounds(x, 0, 4 * WIDTH_BASE, HEIGHT_ROW);
        jLabelID.setVisible(true);
        jLabelID.setVerticalAlignment(SwingConstants.CENTER);
        jLabelID.setHorizontalAlignment(SwingConstants.CENTER);
        x += 4 * WIDTH_BASE;
        jPanelTableHeader.add(jLabelID);

        JLabel jLabelName = new JLabel();
        jLabelName.setText("姓名");
        jLabelName.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelName.setBounds(x, 0, 10 * WIDTH_BASE, HEIGHT_ROW);
        jLabelName.setVisible(true);
        jLabelName.setVerticalAlignment(SwingConstants.CENTER);
        jLabelName.setHorizontalAlignment(SwingConstants.CENTER);
        x += 10 * WIDTH_BASE;
        jPanelTableHeader.add(jLabelName);

        JLabel jLabelEmail = new JLabel();
        jLabelEmail.setText("邮箱");
        jLabelEmail.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelEmail.setBounds(x, 0, 11 * WIDTH_BASE, HEIGHT_ROW);
        jLabelEmail.setVisible(true);
        jLabelEmail.setVerticalAlignment(SwingConstants.CENTER);
        jLabelEmail.setHorizontalAlignment(SwingConstants.CENTER);
        x += 11 * WIDTH_BASE;
        jPanelTableHeader.add(jLabelEmail);

        JLabel jLabelDownloadCnt = new JLabel();
        jLabelDownloadCnt.setText("下载量");
        jLabelDownloadCnt.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelDownloadCnt.setBounds(x, 0, 3 * WIDTH_BASE, HEIGHT_ROW);
        jLabelDownloadCnt.setVisible(true);
        jLabelDownloadCnt.setVerticalAlignment(SwingConstants.CENTER);
        jLabelDownloadCnt.setHorizontalAlignment(SwingConstants.CENTER);
        x += 3 * WIDTH_BASE;
        jPanelTableHeader.add(jLabelDownloadCnt);

        JLabel jLabelDate = new JLabel();
        jLabelDate.setText("最近登录时间");
        jLabelDate.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelDate.setBounds(x, 0, 10 * WIDTH_BASE, HEIGHT_ROW);
        jLabelDate.setVisible(true);
        jLabelDate.setVerticalAlignment(SwingConstants.CENTER);
        jLabelDate.setHorizontalAlignment(SwingConstants.CENTER);
        x += 10 * WIDTH_BASE;
        jPanelTableHeader.add(jLabelDate);

        JLabel jLabelOption = new JLabel();
        jLabelOption.setText("操作选项");
        jLabelOption.setFont(MyFonts.TEXT_FONT_BOLD_18);
        jLabelOption.setBounds(x, 0, 6 * WIDTH_BASE, HEIGHT_ROW);
        jLabelOption.setVisible(true);
        jLabelOption.setVerticalAlignment(SwingConstants.CENTER);
        jLabelOption.setHorizontalAlignment(SwingConstants.CENTER);
        jPanelTableHeader.add(jLabelOption);

    }

    /**
     * 用来加载整个用户信息的板块
     */

    private void initBodyContent() {

        usersNum = adminData.getUserNum();
        pagesNum = usersNum/10+1;
        if (usersNum == 0) {
            JLabel jLabel = new JLabel("暂无数据");
            jLabel.setFont(MyFonts.TITLE_FONT_36);
            jLabel.setBounds((WIDTH_TABLE-WIDGET_GAP)/2,HEIGHT_BODY/2+HEIGHT_ROW+TABLE_Y,WIDGET_GAP,HEIGHT_ROW*2);
            jLabel.setVisible(true);

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
            jPanelTableBody.add(jPanelsUsers[i]);
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

    /**
     * 用于加载详细的用户信息
     * @param i 该信息在展示页中的行数，取值[0,9]
     */

    private void addDetailMsg(int i) {
        int x = 0;
        JTextField jTextFieldIndex = new JTextField();
        jTextFieldIndex.setEditable(false);
        jTextFieldIndex.setOpaque(false);
        jTextFieldIndex.setText(String.valueOf((i + whichPage * 10)));
        jTextFieldIndex.setFont(MyFonts.TEXT_FONT_18);
        jTextFieldIndex.setBounds(WIDTH_BASE, 0, x + 2 * WIDTH_BASE, HEIGHT_ROW);
        jTextFieldIndex.setVisible(true);
        jTextFieldIndex.setHorizontalAlignment(SwingConstants.CENTER);
        x += 3 * WIDTH_BASE;
        jPanelsUsers[i].add(jTextFieldIndex);

        JTextField jTextFieldID = new JTextField();
        jTextFieldID.setEditable(false);
        jTextFieldID.setOpaque(false);
        jTextFieldID.setText(adminData.getUserId(i + whichPage * 10));
        jTextFieldID.setFont(MyFonts.TEXT_FONT_18);
        jTextFieldID.setBounds(x, 0, 4 * WIDTH_BASE, HEIGHT_ROW);
        jTextFieldID.setVisible(true);
        //jTextFieldID.setVerticalAlignment(SwingConstants.CENTER);
        jTextFieldID.setHorizontalAlignment(SwingConstants.CENTER);
        x += 4 * WIDTH_BASE;
        jPanelsUsers[i].add(jTextFieldID);

        JTextField jTextFieldName = new JTextField();
        jTextFieldName.setEditable(false);
        jTextFieldName.setOpaque(false);
        jTextFieldName.setText(adminData.getUserName(i + whichPage * 10));
        jTextFieldName.setFont(MyFonts.TEXT_FONT_18);
        jTextFieldName.setBounds(x, 0, 10 * WIDTH_BASE, HEIGHT_ROW);
        jTextFieldName.setVisible(true);
        //jTextFieldName.setVerticalAlignment(SwingConstants.CENTER);
        jTextFieldName.setHorizontalAlignment(SwingConstants.CENTER);
        x += 10 * WIDTH_BASE;
        jPanelsUsers[i].add(jTextFieldName);

        JTextField jTextFieldEmail = new JTextField();
        jTextFieldEmail.setEditable(false);
        jTextFieldEmail.setOpaque(false);
        jTextFieldEmail.setText(adminData.getUserEmail(i + whichPage * 10));
        jTextFieldEmail.setFont(MyFonts.TEXT_FONT_18);
        jTextFieldEmail.setBounds(x, 0, 11 * WIDTH_BASE, HEIGHT_ROW);
        jTextFieldEmail.setVisible(true);
        //jTextFieldEmail.setVerticalAlignment(SwingConstants.CENTER);
        jTextFieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
        x += 11 * WIDTH_BASE;
        jPanelsUsers[i].add(jTextFieldEmail);

        JTextField jTextFieldDownloadCnt = new JTextField();
        jTextFieldDownloadCnt.setEditable(false);
        jTextFieldDownloadCnt.setOpaque(false);
        jTextFieldDownloadCnt.setText(String.valueOf(adminData.getUserDownloadCnt(i + whichPage * 10)));
        jTextFieldDownloadCnt.setFont(MyFonts.TEXT_FONT_18);
        jTextFieldDownloadCnt.setBounds(x, 0, 3 * WIDTH_BASE, HEIGHT_ROW);
        //jTextFieldDownloadCnt.setVerticalAlignment(SwingConstants.CENTER);
        jTextFieldDownloadCnt.setHorizontalAlignment(SwingConstants.CENTER);
        x += 3 * WIDTH_BASE;
        jPanelsUsers[i].add(jTextFieldDownloadCnt);
        jTextFieldDownloadCnt.setVisible(true);

        JTextField jTextFieldDate = new JTextField();
        jTextFieldDate.setEditable(false);
        jTextFieldDate.setOpaque(false);
        jTextFieldDate.setText(adminData.getUserDate(i + whichPage * 10));
        jTextFieldDate.setFont(MyFonts.TEXT_FONT_18);
        jTextFieldDate.setBounds(x, 0, 10 * WIDTH_BASE, HEIGHT_ROW);
        jTextFieldDate.setVisible(true);
        //jTextFieldDate.setVerticalAlignment(SwingConstants.CENTER);
        jTextFieldDate.setHorizontalAlignment(SwingConstants.CENTER);
        x += 10 * WIDTH_BASE;
        jPanelsUsers[i].add(jTextFieldDate);

        final String[] oldEmail = {null};
        JButton jButtonChangePassword = new JButton();
        JButton jButtonContinue = new JButton();
        JButton jButtonReset = new JButton();
        JButton jButtonCancel = new JButton();
        JButton jButtonChangeEmail = new JButton();
        JFrame passwordWin = new JFrame();
        JLabel jLabelInput = new JLabel();
        JPasswordField jPasswordField = new JPasswordField();
        JButton jButtonYes = new JButton();
        JButton jButtonNo = new JButton();


        jButtonContinue.setVisible(false);
        jButtonContinue.setText("确认修改");
        jButtonContinue.setBounds(x, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
        jButtonContinue.setVerticalAlignment(SwingConstants.CENTER);
        jButtonContinue.addActionListener(evt -> {
            System.out.println("AdminMainFrm : Click yes button");
            String newEmail = jTextFieldEmail.getText();
            if(MyBootFrame.isLegalEmail(newEmail)){
                if(AdminMainCtrl.Change(adminData.getUserName(i + whichPage * 10),adminData.getUserId(i + whichPage * 10),newEmail,
                        adminData.getUserPassword(i + whichPage * 10),adminData.getUserDownloadCnt(i + whichPage * 10),
                        adminData.getUserDate(i + whichPage * 10))) {
                    jButtonReset.setVisible(true);
                    jButtonChangePassword.setVisible(true);
                    jButtonContinue.setVisible(false);
                    jButtonCancel.setVisible(false);
                    jTextFieldEmail.setText(newEmail);
                    jTextFieldEmail.setEditable(false);
                    adminData.ChangeEmail(i + whichPage * 10,newEmail);
                } else {
                    OptionError();
                }
            } else {
                EmailOrPasswordError();
            }
        });
        jPanelsUsers[i].add(jButtonContinue);


        jButtonReset.setVisible(true);
        jButtonReset.setText("重置次数");
        jButtonReset.setBounds(x, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
        jButtonReset.setVerticalAlignment(SwingConstants.CENTER);
        jButtonReset.addActionListener(evt -> {
            System.out.println("AdminMainFrm : Click reset button");
            if(AdminMainCtrl.Change(adminData.getUserName(i + whichPage * 10),adminData.getUserId(i + whichPage * 10),
                    adminData.getUserEmail(i + whichPage * 10), adminData.getUserPassword(i + whichPage * 10),
                    0,adminData.getUserDate(i + whichPage * 10))){
                jTextFieldDownloadCnt.setText("0");
                adminData.ChangeDownloadCnt(i+10*whichPage);
            } else {
                OptionError();
            }
        });
        x+=4*WIDTH_BASE;
        jPanelsUsers[i].add(jButtonReset);


        jButtonCancel.setVisible(false);
        jButtonCancel.setText("取消修改");
        jButtonCancel.setBounds(x, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
        jButtonCancel.setVerticalAlignment(SwingConstants.CENTER);
        jButtonCancel.addActionListener(e -> {
            System.out.println("AdminMainFrm : Click cancel button");
            jButtonReset.setVisible(true);
            jButtonChangePassword.setVisible(true);
            jButtonContinue.setVisible(false);
            jButtonCancel.setVisible(false);
            jTextFieldEmail.setText(String.valueOf(oldEmail[0]));
            jTextFieldEmail.setEditable(false);
        });
        jPanelsUsers[i].add(jButtonCancel);


        jButtonChangePassword.setVisible(true);
        jButtonChangePassword.setText("修改密码");
        jButtonChangePassword.setBounds(x, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
        jButtonChangePassword.setVerticalAlignment(SwingConstants.CENTER);
        jButtonChangePassword.addActionListener(evt -> {
            System.out.println("AdminMainFrm : Click change password button");
            passwordWin.setVisible(true);
            jPasswordField.setText("");
            jPasswordField.setVisible(true);
            jLabelInput.setVisible(true);
            jButtonYes.setVisible(true);
            jButtonNo.setVisible(true);

        });
        x+=4*WIDTH_BASE;
        jPanelsUsers[i].add(jButtonChangePassword);



        jButtonChangeEmail.setVisible(true);
        jButtonChangeEmail.setText("修改邮箱");
        jButtonChangeEmail.setBounds(x, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
        jButtonChangeEmail.setVerticalAlignment(SwingConstants.CENTER);
        jButtonChangeEmail.setVisible(true);
        jButtonChangeEmail.addActionListener(e -> {
            System.out.println("AdminMainFrm : Click change email button");
            jTextFieldEmail.setEditable(true);
            jButtonReset.setVisible(false);
            jButtonChangePassword.setVisible(false);
            jButtonCancel.setVisible(true);
            jButtonContinue.setVisible(true);
            oldEmail[0] = (jTextFieldEmail.getText());
        });
        jPanelsUsers[i].add(jButtonChangeEmail);

        passwordWin.setLayout(null);
        passwordWin.setResizable(false);
        passwordWin.setSize(300,200);


        jLabelInput.setText("请输入修改后密码：");
        jLabelInput.setBounds(WIDGET_X,10,WIDGET_GAP,30);
        passwordWin.add(jLabelInput);

        jPasswordField.setBounds(WIDGET_X,FIELD_HEIGHT,240,30);

        jButtonYes.setText("确认");
        jButtonYes.setBounds(30,80,100,30);
        jButtonYes.addActionListener(evt -> {
            System.out.println("AdminUserEditFrmWin : Click yes button");
            String newPassword = String.valueOf(jPasswordField.getPassword());
            if(MyBootFrame.isLegalPassword(newPassword)){
                if(AdminMainCtrl.Change(adminData.getUserName(i + whichPage * 10),adminData.getUserId(i + whichPage * 10),
                        adminData.getUserEmail(i + whichPage * 10), newPassword,
                        adminData.getUserDownloadCnt(i + whichPage * 10),adminData.getUserDate(i + whichPage * 10))) {
                    jPasswordField.setText("");
                    passwordWin.setVisible(false);
                    jPasswordField.setVisible(false);
                    jLabelInput.setVisible(false);
                    jButtonYes.setVisible(false);
                    jButtonNo.setVisible(false);
                    passwordWin.dispose();
                    adminData.ChangePassword(i + whichPage * 10,newPassword);
                } else {
                    OptionError();
                }
            } else {
                EmailOrPasswordError();
            }

        });

        jButtonNo.setText("重置");
        jButtonNo.setBounds(150,80,100,30);
        jButtonNo.addActionListener(evt -> {
            System.out.println("AdminUserEditFrmWin : Click no button");
            jPasswordField.setText("");
        });

        passwordWin.add(jPasswordField);
        passwordWin.add(jButtonYes);
        passwordWin.add(jButtonNo);
        passwordWin.setVisible(false);


    }

    /**
     * 用来加载管理员管理用户界面中，最下方一栏的页数提醒和换页按键
     */

    private void intiBodyPage(){
        JButton jButtonLeft = new JButton();
        JButton jButtonRight = new JButton();
        JLabel jLabelPage = new JLabel();

        jLabelPage.setText(whichPage+1+"/"+pagesNum);
        jLabelPage.setBounds(TABLE_X+WIDTH_TABLE/2-2*WIDTH_BASE,TABLE_Y+HEIGHT_ROW+10*HEIGHT_DETAIL,4*WIDTH_BASE,HEIGHT_DETAIL);
        jLabelPage.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelPage.setVerticalAlignment(SwingConstants.CENTER);
        jLabelPage.setVisible(true);
        jPanelTableBody.add(jLabelPage);

        jButtonLeft.setText("上一页");
        jButtonLeft.setBounds(TABLE_X+WIDTH_TABLE/2-6*WIDTH_BASE,TABLE_Y+HEIGHT_ROW+10*HEIGHT_DETAIL,4*WIDTH_BASE,HEIGHT_DETAIL);
        jButtonLeft.setVerticalAlignment(SwingConstants.CENTER);
        jButtonLeft.setHorizontalAlignment(SwingConstants.CENTER);
        jButtonLeft.setVisible(true);
        jPanelTableBody.add(jButtonLeft);
        jButtonLeft.addActionListener(e -> {
            if(whichPage > 0){
                whichPage--;
                initBodyContent();
            } else {
                OptionError();
            }
        });

        jButtonRight.setText("下一页");
        jButtonRight.setBounds(TABLE_X+WIDTH_TABLE/2+2*WIDTH_BASE,TABLE_Y+HEIGHT_ROW+10*HEIGHT_DETAIL,4*WIDTH_BASE,HEIGHT_DETAIL);
        jButtonRight.setVerticalAlignment(SwingConstants.CENTER);
        jButtonRight.setHorizontalAlignment(SwingConstants.CENTER);
        jButtonRight.setVisible(true);
        jPanelTableBody.add(jButtonRight);
        jButtonRight.addActionListener(e -> {
            if(whichPage < pagesNum - 1){
                whichPage++;
                initBodyContent();
            } else {
                OptionError();
            }
        });
    }




    /**
     * 当服务器连接超时时，返回提示信息
     */

    public void timeoutError() {
        msgLabel.setText("服务器连接超时，请稍后重试");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        msgLabel.setVisible(false);
    }

    /**
     * 当邮箱或密码格式不合法时，返回提示信息
     */

    public void EmailOrPasswordError(){
        msgLabel.setText("格式错误，请重新输入");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        msgLabel.setVisible(false);
    }

    /**
     * 用来返回操作失败的提示信息
     */
    public void OptionError(){
        msgLabel.setText("操作失败，请重试");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        msgLabel.setVisible(false);
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
