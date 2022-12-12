package view;

import controller.AdminMainCtrl;
import data.AdminData;
import style.MyFonts;
import style.StyleCtrl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminUserEditFrm extends MyInterFrame {

    private static final int WIDGET_X = 20;
    private static final int WIDGET_GAP = 400;
    private static final int FIELD_HEIGHT = 40;


    public final static int WIDTH_TABLE = 1400;
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

    JTextField[] jTextFieldIndex = new JTextField[]{
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField(),
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField()
    };
    JTextField[] jTextFieldID = new JTextField[]{
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField(),
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField()
    };
    JTextField[] jTextFieldName = new JTextField[]{
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField(),
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField()
    };
    JTextField[] jTextFieldEmail = new JTextField[]{
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField(),
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField()
    };
    JTextField[] jTextFieldDownloadCnt = new JTextField[]{
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField(),
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField()
    };
    JTextField[] jTextFieldDate = new JTextField[]{
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField(),
            new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField()
    };

    final String[] oldEmail = {null};
    JButton[] jButtonChangePassword = new JButton[]{
            new JButton("修改密码"),new JButton("修改密码"),new JButton("修改密码"),new JButton("修改密码"),new JButton("修改密码"),
            new JButton("修改密码"),new JButton("修改密码"),new JButton("修改密码"),new JButton("修改密码"),new JButton("修改密码")
    };
    JButton[] jButtonContinue = new JButton[]{
            new JButton("确认修改"),new JButton("确认修改"),new JButton("确认修改"),new JButton("确认修改"),new JButton("确认修改"),
            new JButton("确认修改"),new JButton("确认修改"),new JButton("确认修改"),new JButton("确认修改"),new JButton("确认修改")
    };
    JButton[] jButtonReset = new JButton[]{
            new JButton("重置次数"),new JButton("重置次数"),new JButton("重置次数"),new JButton("重置次数"),new JButton("重置次数"),
            new JButton("重置次数"),new JButton("重置次数"),new JButton("重置次数"),new JButton("重置次数"),new JButton("重置次数")
    };
    JButton[] jButtonCancel = new JButton[]{
            new JButton("取消修改"),new JButton("取消修改"),new JButton("取消修改"),new JButton("取消修改"),new JButton("取消修改"),
            new JButton("取消修改"),new JButton("取消修改"),new JButton("取消修改"),new JButton("取消修改"),new JButton("取消修改")
    };
    JButton[] jButtonChangeEmail = new JButton[]{
            new JButton("修改邮箱"),new JButton("修改邮箱"),new JButton("修改邮箱"),new JButton("修改邮箱"),new JButton("修改邮箱"),
            new JButton("修改邮箱"),new JButton("修改邮箱"),new JButton("修改邮箱"),new JButton("修改邮箱"),new JButton("修改邮箱")
    };
    JFrame passwordWin = new JFrame();
    JLabel jLabelInput = new JLabel();
    JPasswordField jPasswordField = new JPasswordField();
    JButton jButtonYes = new JButton();
    JButton jButtonNo = new JButton();
    private final JButton[] jButtonsDelete = new JButton[]{
            new JButton("删除"),new JButton("删除"),new JButton("删除"),new JButton("删除"),new JButton("删除"),
            new JButton("删除"),new JButton("删除"),new JButton("删除"),new JButton("删除"),new JButton("删除")
    };
    private final JButton[] jButtonsYes = new JButton[]{
            new JButton("确定"),new JButton("确定"),new JButton("确定"),new JButton("确定"),new JButton("确定"),
            new JButton("确定"),new JButton("确定"),new JButton("确定"),new JButton("确定"),new JButton("确定")
    };

    private final JButton[] jButtonsNo = new JButton[]{
            new JButton("取消"),new JButton("取消"),new JButton("取消"),new JButton("取消"),new JButton("取消"),
            new JButton("取消"),new JButton("取消"),new JButton("取消"),new JButton("取消"),new JButton("取消")
    };

    private int usersNum = 0;
    private int pagesNum = 0;
    private int whichPage = 0;


    private static volatile AdminUserEditFrm adminEditFrm = null;
    private static Container container;
    private AdminData adminData;

    private AdminUserEditFrm() {
        super();

        initComponents();
    }

    public static AdminUserEditFrm getInstance() {
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
       adminData = AdminData.getInstance();
//        adminData.add("zzq1","20374265","2020202020",8,"asdhu@buaa.edu.cn","2020-2020-2020");
//        adminData.add("zzq2","20374265","2020202020",8,"asdhu@buaa.edu.cn","2020-2020-2020");
//        adminData.add("zzq3","20374265","2020202020",8,"asdhu@buaa.edu.cn","2020-2020-2020");
//        adminData.add("zzq4","20374265","2020202020",8,"asdhu@buaa.edu.cn","2020-2020-2020");
//        adminData.add("zzq5","20374265","2020202020",8,"asdhu@buaa.edu.cn","2020-2020-2020");
//        adminData.add("zzq6","20374265","2020202020",8,"asdhu@buaa.edu.cn","2020-2020-2020");
//        adminData.add("zzq7","20374265","2020202020",8,"asdhu@buaa.edu.cn","2020-2020-2020");
//        adminData.add("zzq8","20374265","2020202020",8,"asdhu@buaa.edu.cn","2020-2020-2020");
//        adminData.add("zzq9","20374265","2020202020",8,"asdhu@buaa.edu.cn","2020-2020-2020");
//        adminData.add("zzq10","20374265","2020202020",8,"asdhu@buaa.edu.cn","2020-2020-2020");
//        adminData.add("zzq11","20374265","2020202020",8,"asdhu@buaa.edu.cn","2020-2020-2020");
//        adminData.add("zzq12","20374265","2020202020",8,"asdhu@buaa.edu.cn","2020-2020-2020");
        System.out.println(adminData.getUserNum());
        initTitle();
        initFrame();
        initTableHeader();
        initTableBody();

    }

    public void Load(){
        boolean isLoad = AdminMainCtrl.tryLoad();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(isLoad){
            initTableBody();
        } else {
            System.out.println("没有数据");
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
        //jPanelTableHeader.setBackground(Color.LIGHT_GRAY);
        jPanelTableHeader.setSize(WIDTH_TABLE, HEIGHT_ROW);
        jPanelTableHeader.setLocation(TABLE_X, TABLE_Y);
        container.add(jPanelTableHeader);
        initHeaderLabel();
    }

    /**
     * 用来加载整体的用户信息主体内容
     */
    private void initTableBody() {
        adminData = AdminData.getInstance();
        jPanelTableBody.setVisible(true);
        jPanelTableBody.setLayout(null);
        //jPanelTableBody.setBackground(Color.PINK);
        jPanelTableBody.setSize(WIDTH_TABLE, HEIGHT_BODY);
        jPanelTableBody.setLocation(TABLE_X-70, TABLE_Y + HEIGHT_ROW);
        initBodyContent();
        initBodyPage();
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
        jLabelOption.setBounds(x+3*WIDTH_BASE, 0, 6 * WIDTH_BASE, HEIGHT_ROW);
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
                initDeleteButtons(i);
            }
            initDetailMsg();
            changeDetailMsg();
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
            jPanelsUsers[i].setLocation(70,  i * HEIGHT_DETAIL);

            jButtonsDelete[i].setBounds(10,i * HEIGHT_DETAIL,70,HEIGHT_DETAIL);
            jPanelTableBody.add(jButtonsDelete[i]);
            int finalI1 = i;
            jButtonsDelete[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(AdminMainCtrl.Delete(adminData.getUserName(finalI1 + whichPage * 10),adminData.getUserId(finalI1 + whichPage * 10),
                            adminData.getUserEmail(finalI1 + whichPage * 10), adminData.getUserPassword(finalI1 + whichPage * 10),
                            adminData.getUserDownloadCnt(finalI1 + whichPage * 10), adminData.getUserDate(finalI1 + whichPage * 10))){
                        adminData.DeleteUser(finalI1 + whichPage * 10);
                        usersNum--;
                        changeDetailMsg();
                    }

                }
            });

        }
    }

    /**
     * 用于加载详细的用户信息
     */

    private void initDetailMsg() {
        System.out.println("Success!"+whichPage);
        final int[] select = {-1};
        for(int j = 0; j < 10; j++){
            int x = 0;
            jTextFieldIndex[j].setEditable(false);
            jTextFieldIndex[j].setOpaque(false);
            jTextFieldIndex[j].setFont(MyFonts.TEXT_FONT_18);
            jTextFieldIndex[j].setBounds(WIDTH_BASE, 0, x + 2 * WIDTH_BASE, HEIGHT_ROW);
            jTextFieldIndex[j].setHorizontalAlignment(SwingConstants.CENTER);
            x += 3 * WIDTH_BASE;
            jPanelsUsers[j].add(jTextFieldIndex[j]);

            jTextFieldID[j].setEditable(false);
            jTextFieldID[j].setOpaque(false);
            jTextFieldID[j].setFont(MyFonts.TEXT_FONT_18);
            jTextFieldID[j].setBounds(x, 0, 4 * WIDTH_BASE, HEIGHT_ROW);
            jTextFieldID[j].setHorizontalAlignment(SwingConstants.CENTER);
            x += 4 * WIDTH_BASE;
            jPanelsUsers[j].add(jTextFieldID[j]);

            jTextFieldName[j].setEditable(false);
            jTextFieldName[j].setOpaque(false);
            jTextFieldName[j].setFont(MyFonts.TEXT_FONT_18);
            jTextFieldName[j].setBounds(x, 0, 10 * WIDTH_BASE, HEIGHT_ROW);
            jTextFieldName[j].setHorizontalAlignment(SwingConstants.CENTER);
            x += 10 * WIDTH_BASE;
            jPanelsUsers[j].add(jTextFieldName[j]);

            jTextFieldEmail[j].setEditable(false);
            jTextFieldEmail[j].setOpaque(false);
            jTextFieldEmail[j].setFont(MyFonts.TEXT_FONT_18);
            jTextFieldEmail[j].setBounds(x, 0, 11 * WIDTH_BASE, HEIGHT_ROW);
            jTextFieldEmail[j].setHorizontalAlignment(SwingConstants.CENTER);
            x += 11 * WIDTH_BASE;
            jPanelsUsers[j].add(jTextFieldEmail[j]);

            jTextFieldDownloadCnt[j].setEditable(false);
            jTextFieldDownloadCnt[j].setOpaque(false);
            jTextFieldDownloadCnt[j].setFont(MyFonts.TEXT_FONT_18);
            jTextFieldDownloadCnt[j].setBounds(x, 0, 3 * WIDTH_BASE, HEIGHT_ROW);
            jTextFieldDownloadCnt[j].setHorizontalAlignment(SwingConstants.CENTER);
            x += 3 * WIDTH_BASE;
            jPanelsUsers[j].add(jTextFieldDownloadCnt[j]);

            jTextFieldDate[j].setEditable(false);
            jTextFieldDate[j].setOpaque(false);
            jTextFieldDate[j].setFont(MyFonts.TEXT_FONT_18);
            jTextFieldDate[j].setBounds(x, 0, 10 * WIDTH_BASE, HEIGHT_ROW);
            jTextFieldDate[j].setHorizontalAlignment(SwingConstants.CENTER);
            x += 10 * WIDTH_BASE;
            jPanelsUsers[j].add(jTextFieldDate[j]);


            jButtonContinue[j].setBounds(x, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
            jButtonContinue[j].setVerticalAlignment(SwingConstants.CENTER);
            int finalJ = j;
            int finalJ4 = j;
            jButtonContinue[j].addActionListener(evt -> {
                System.out.println("AdminMainFrm : Click yes button");
                String newEmail = jTextFieldEmail[finalJ].getText();
                if(MyBootFrame.isLegalEmail(newEmail)){
                    if(AdminMainCtrl.Change(adminData.getUserName(finalJ4 + whichPage * 10),adminData.getUserId(finalJ4 + whichPage * 10),newEmail,
                            adminData.getUserPassword(finalJ4 + whichPage * 10),adminData.getUserDownloadCnt(finalJ4 + whichPage * 10),
                            adminData.getUserDate(finalJ4 + whichPage * 10))) {
                        jButtonReset[finalJ].setVisible(true);
                        jButtonChangePassword[finalJ].setVisible(true);
                        jButtonContinue[finalJ].setVisible(false);
                        jButtonCancel[finalJ].setVisible(false);
                        jTextFieldEmail[finalJ].setText(newEmail);
                        jTextFieldEmail[finalJ].setEditable(false);
                        adminData.ChangeEmail(finalJ4 + whichPage * 10,newEmail);
                    } else {
                        OptionError();
                    }
                } else {
                    EmailOrPasswordError();
                }
            });
            jPanelsUsers[j].add(jButtonContinue[j]);


            jButtonReset[j].setBounds(x, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
            jButtonReset[j].setVerticalAlignment(SwingConstants.CENTER);
            int finalJ3 = j;
            int finalJ6 = j;
            jButtonReset[j].addActionListener(evt -> {
                System.out.println("AdminMainFrm : Click reset button");
                if(AdminMainCtrl.Change(adminData.getUserName(finalJ6 + whichPage * 10),adminData.getUserId(finalJ6 + whichPage * 10),
                        adminData.getUserEmail(finalJ6 + whichPage * 10), adminData.getUserPassword(finalJ6 + whichPage * 10),
                        0,adminData.getUserDate(finalJ6 + whichPage * 10))){
                    jTextFieldDownloadCnt[finalJ3].setText("0");
                    adminData.ChangeDownloadCnt(finalJ6 +10*whichPage);
                } else {
                    OptionError();
                }
            });
            x+=4*WIDTH_BASE;
            jPanelsUsers[j].add(jButtonReset[j]);


            jButtonCancel[j].setBounds(x, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
            jButtonCancel[j].setVerticalAlignment(SwingConstants.CENTER);
            int finalJ1 = j;
            jButtonCancel[j].addActionListener(e -> {
                System.out.println("AdminMainFrm : Click cancel button");
                jButtonReset[finalJ1].setVisible(true);
                jButtonChangePassword[finalJ1].setVisible(true);
                jButtonContinue[finalJ1].setVisible(false);
                jButtonCancel[finalJ1].setVisible(false);
                jTextFieldEmail[finalJ1].setText(String.valueOf(oldEmail[0]));
                jTextFieldEmail[finalJ1].setEditable(false);
            });
            jPanelsUsers[j].add(jButtonCancel[j]);


            jButtonChangePassword[j].setBounds(x, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
            jButtonChangePassword[j].setVerticalAlignment(SwingConstants.CENTER);
            int finalJ7 = j;
            jButtonChangePassword[j].addActionListener(evt -> {
                System.out.println("AdminMainFrm : Click change password button");
                passwordWin.setVisible(true);
                jPasswordField.setText("");
                jPasswordField.setVisible(true);
                jLabelInput.setVisible(true);
                jButtonYes.setVisible(true);
                jButtonNo.setVisible(true);
                select[0] = finalJ7;
            });
            x+=4*WIDTH_BASE;
            jPanelsUsers[j].add(jButtonChangePassword[j]);


            jButtonChangeEmail[j].setBounds(x, 5,WIDTH_BASE*4-10, HEIGHT_DETAIL-10);
            jButtonChangeEmail[j].setVerticalAlignment(SwingConstants.CENTER);
            jButtonChangeEmail[j].setVisible(true);
            int finalJ2 = j;
            jButtonChangeEmail[j].addActionListener(e -> {
                System.out.println("AdminMainFrm : Click change email button");
                jTextFieldEmail[finalJ2].setEditable(true);
                jButtonReset[finalJ2].setVisible(false);
                jButtonChangePassword[finalJ2].setVisible(false);
                jButtonCancel[finalJ2].setVisible(true);
                jButtonContinue[finalJ2].setVisible(true);
                oldEmail[0] = (jTextFieldEmail[finalJ2].getText());
            });
            jPanelsUsers[j].add(jButtonChangeEmail[j]);



        }
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
                if(AdminMainCtrl.Change(adminData.getUserName(select[0] + whichPage * 10),adminData.getUserId(select[0] + whichPage * 10),
                        adminData.getUserEmail(select[0] + whichPage * 10), newPassword,
                        adminData.getUserDownloadCnt(select[0] + whichPage * 10),adminData.getUserDate(select[0] + whichPage * 10))) {
                    jPasswordField.setText("");
                    passwordWin.setVisible(false);
                    jPasswordField.setVisible(false);
                    jLabelInput.setVisible(false);
                    jButtonYes.setVisible(false);
                    jButtonNo.setVisible(false);
                    passwordWin.dispose();
                    adminData.ChangePassword(select[0] + whichPage * 10,newPassword);
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

    private void changeDetailMsg(){
        for(int j = usersNum-10*whichPage;j < 10; j++){
            jTextFieldIndex[j].setVisible(false);
            jTextFieldID[j].setVisible(false);
            jTextFieldName[j].setVisible(false);
            jTextFieldEmail[j].setVisible(false);
            jTextFieldDownloadCnt[j].setVisible(false);
            jTextFieldDate[j].setVisible(false);
            jButtonContinue[j].setVisible(false);
            jButtonReset[j].setVisible(false);
            jButtonCancel[j].setVisible(false);
            jButtonChangePassword[j].setVisible(false);
            jButtonChangeEmail[j].setVisible(false);
            jButtonsDelete[j].setVisible(false);
        }
        for(int j = 0; j < 10 && j < usersNum-10*whichPage; j++){
            jTextFieldIndex[j].setText(String.valueOf((j + whichPage * 10 + 1)));
            jTextFieldIndex[j].setVisible(true);
            jTextFieldID[j].setText(adminData.getUserId(j + whichPage * 10));
            jTextFieldID[j].setVisible(true);
            jTextFieldName[j].setText(adminData.getUserName(j + whichPage * 10));
            jTextFieldName[j].setVisible(true);
            jTextFieldEmail[j].setText(adminData.getUserEmail(j + whichPage * 10));
            jTextFieldEmail[j].setVisible(true);
            jTextFieldDownloadCnt[j].setText(String.valueOf(adminData.getUserDownloadCnt(j + whichPage * 10)));
            jTextFieldDownloadCnt[j].setVisible(true);
            jTextFieldDate[j].setText(adminData.getUserDate(j + whichPage * 10));
            jTextFieldDate[j].setVisible(true);
            jButtonContinue[j].setVisible(false);
            jButtonReset[j].setVisible(true);
            jButtonCancel[j].setVisible(false);
            jButtonChangePassword[j].setVisible(true);
            jButtonChangeEmail[j].setVisible(true);
            jButtonsDelete[j].setVisible(true);
        }

    }

    /**
     * 用来加载管理员管理用户界面中，最下方一栏的页数提醒和换页按键
     */

    private void initBodyPage(){
        JButton jButtonLeft = new JButton();
        JButton jButtonRight = new JButton();
        JLabel jLabelPage = new JLabel();

        jLabelPage.setText(whichPage+1+"/"+pagesNum);
        jLabelPage.setBounds(WIDTH_TABLE/2-2*WIDTH_BASE,HEIGHT_BODY-50,4*WIDTH_BASE,HEIGHT_DETAIL);
        jLabelPage.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelPage.setVerticalAlignment(SwingConstants.CENTER);
        jLabelPage.setVisible(true);
        jPanelTableBody.add(jLabelPage);

        jButtonLeft.setText("上一页");
        jButtonLeft.setBounds(WIDTH_TABLE/2-6*WIDTH_BASE,HEIGHT_BODY-50,4*WIDTH_BASE,HEIGHT_DETAIL);
        jButtonLeft.setVerticalAlignment(SwingConstants.CENTER);
        jButtonLeft.setHorizontalAlignment(SwingConstants.CENTER);
        jButtonLeft.setVisible(true);
        jPanelTableBody.add(jButtonLeft);
        jButtonLeft.addActionListener(e -> {
            if(whichPage > 0){
                whichPage--;
                jLabelPage.setText(whichPage+1+"/"+pagesNum);

                    changeDetailMsg();

            } else {
                OptionError();
            }
        });

        jButtonRight.setText("下一页");
        jButtonRight.setBounds(WIDTH_TABLE/2+2*WIDTH_BASE,HEIGHT_BODY-50,4*WIDTH_BASE,HEIGHT_DETAIL);
        jButtonRight.setVerticalAlignment(SwingConstants.CENTER);
        jButtonRight.setHorizontalAlignment(SwingConstants.CENTER);
        jButtonRight.setVisible(true);
        jPanelTableBody.add(jButtonRight);
        jButtonRight.addActionListener(e -> {
            if(whichPage < pagesNum - 1){
                whichPage++;
                jLabelPage.setText(whichPage+1+"/"+pagesNum);
                    changeDetailMsg();

            } else {
                OptionError();
            }
        });
        jPanelTableBody.add(jButtonLeft);
        jPanelTableBody.add(jButtonRight);
        jPanelTableBody.add(jLabelPage);
    }

    private void initDeleteButtons(int idx){

    }


    /**
     * 当邮箱或密码格式不合法时，返回提示信息
     */

    public void EmailOrPasswordError(){
        msgLabel.setText("格式错误，请重新输入");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
        try {
            Thread.sleep(200);
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
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        msgLabel.setVisible(false);
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARCULA);
        MyFrame myFrameTest = new MyFrame() {
        };
        AdminUserEditFrm adminMainFrm1 = AdminUserEditFrm.getInstance();
        myFrameTest.getTable().add(adminMainFrm1);
        adminMainFrm1.setVisible(true);
        myFrameTest.setVisible(true);
    }
}
