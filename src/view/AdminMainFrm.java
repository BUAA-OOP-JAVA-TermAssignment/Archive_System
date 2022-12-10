package view;

import panel.BriefPaperPanel;
import style.MyFonts;
import message.AdminUserEditMsg;

import javax.swing.*;
import java.awt.*;

public class AdminMainFrm extends MyFrame{

    public final static int WIDTH_TABLE = 1300;
    public final static int HEIGHT_ROW = 60;
    public final static int HEIGHT_DETAIL = 50;
    public final static int HEIGHT_BODY = 500;
    public final static int WIDTH_BASE = 25;
    public final static int TABLE_X = 100;
    public final static int TABLE_Y = 100;


    private JPanel jPanelTableHeader = new JPanel();
    private JPanel jPanelTableBody = new JPanel();
    private JPanel[] jPanelsUsers = new JPanel[10];
    AdminUserEditMsg adminUserEditMsg = null;
    private int usersNum = 0;
    private int pagesNum = 0;
    private int whichPage = 0;

    private static volatile AdminMainFrm adminMainFrm = null;
    private JDesktopPane table;
    CardLayout cardLayout = new CardLayout();
    private AdminMainFrm() {
        super();
        table = getTable();
        initComponents();
    }

    public static AdminMainFrm createAdminMainFrm(){
        if(adminMainFrm == null) {
            synchronized (GuestSearchFrm.class) {
                if(adminMainFrm == null) {
                    adminMainFrm = new AdminMainFrm();
                }
            }
        }

        return adminMainFrm;
    }

    private void initComponents(){
        initTitle();
        initFrame();
        initTableHeader();
        initTableBody();

    }

    private void initTitle(){
        //TODO:有一个主页面最上方的title捏
    }

    private void initFrame(){
        this.setTitle("管理员个人中心");
    }

    private void initTableHeader(){
        jPanelTableHeader.setVisible(true);
        jPanelTableHeader.setLayout(null);
        jPanelTableHeader.setBackground(Color.LIGHT_GRAY);
        jPanelTableHeader.setSize(WIDTH_TABLE, HEIGHT_ROW);
        jPanelTableHeader.setLocation(TABLE_X,TABLE_Y);
        table.add(jPanelTableHeader);
        initHeaderLabel();
    }

    private void initTableBody(){
        jPanelTableBody.setVisible(true);
        jPanelTableBody.setLayout(null);
        jPanelTableBody.setBackground(Color.PINK);
        jPanelTableBody.setSize(WIDTH_TABLE, HEIGHT_BODY);
        jPanelTableBody.setLocation(TABLE_X,TABLE_Y+HEIGHT_ROW);
        table.add(jPanelTableBody);
        initBodyContent();
    }

    private void initHeaderLabel(){
        int y = 0;
        JLabel jLabelIndex = new JLabel();
        jLabelIndex.setText("序号");
        jLabelIndex.setFont(MyFonts.TEXT_FONT_18);
        jLabelIndex.setBounds(WIDTH_BASE,0,y+2*WIDTH_BASE,HEIGHT_ROW);
        jLabelIndex.setVisible(true);
        jLabelIndex.setVerticalAlignment(SwingConstants.CENTER);
        jLabelIndex.setHorizontalAlignment(SwingConstants.CENTER);
        y += 3*WIDTH_BASE;
        jPanelTableHeader.add(jLabelIndex);

        JLabel jLabelID = new JLabel();
        jLabelID.setText("学号");
        jLabelID.setFont(MyFonts.TEXT_FONT_18);
        jLabelID.setBounds(y,0,4*WIDTH_BASE,HEIGHT_ROW);
        jLabelID.setVisible(true);
        jLabelID.setVerticalAlignment(SwingConstants.CENTER);
        jLabelID.setHorizontalAlignment(SwingConstants.CENTER);
        y += 4*WIDTH_BASE;
        jPanelTableHeader.add(jLabelID);

        JLabel jLabelName = new JLabel();
        jLabelName.setText("姓名");
        jLabelName.setFont(MyFonts.TEXT_FONT_18);
        jLabelName.setBounds(y,0,10*WIDTH_BASE,HEIGHT_ROW);
        jLabelName.setVisible(true);
        jLabelName.setVerticalAlignment(SwingConstants.CENTER);
        jLabelName.setHorizontalAlignment(SwingConstants.CENTER);
        y += 10*WIDTH_BASE;
        jPanelTableHeader.add(jLabelName);

        JLabel jLabelEmail = new JLabel();
        jLabelEmail.setText("邮箱");
        jLabelEmail.setFont(MyFonts.TEXT_FONT_18);
        jLabelEmail.setBounds(y,0,11*WIDTH_BASE,HEIGHT_ROW);
        jLabelEmail.setVisible(true);
        jLabelEmail.setVerticalAlignment(SwingConstants.CENTER);
        jLabelEmail.setHorizontalAlignment(SwingConstants.CENTER);
        y += 11*WIDTH_BASE;
        jPanelTableHeader.add(jLabelEmail);

        JLabel jLabelDownloadCnt = new JLabel();
        jLabelDownloadCnt.setText("下载量");
        jLabelDownloadCnt.setFont(MyFonts.TEXT_FONT_18);
        jLabelDownloadCnt.setBounds(y,0,3*WIDTH_BASE,HEIGHT_ROW);
        jLabelDownloadCnt.setVisible(true);
        jLabelDownloadCnt.setVerticalAlignment(SwingConstants.CENTER);
        jLabelDownloadCnt.setHorizontalAlignment(SwingConstants.CENTER);
        y += 3*WIDTH_BASE;
        jPanelTableHeader.add(jLabelDownloadCnt);

        JLabel jLabelDate = new JLabel();
        jLabelDate.setText("最近登录时间");
        jLabelDate.setFont(MyFonts.TEXT_FONT_18);
        jLabelDate.setBounds(y,0,10*WIDTH_BASE,HEIGHT_ROW);
        jLabelDate.setVisible(true);
        jLabelDate.setVerticalAlignment(SwingConstants.CENTER);
        jLabelDate.setHorizontalAlignment(SwingConstants.CENTER);
        y += 10*WIDTH_BASE;
        jPanelTableHeader.add(jLabelDate);

        JLabel jLabelOption = new JLabel();
        jLabelOption.setText("操作选项");
        jLabelOption.setFont(MyFonts.TEXT_FONT_18);
        jLabelOption.setBounds(y,0,6*WIDTH_BASE,HEIGHT_ROW);
        jLabelOption.setVisible(true);
        jLabelOption.setVerticalAlignment(SwingConstants.CENTER);
        jLabelOption.setHorizontalAlignment(SwingConstants.CENTER);
        jPanelTableHeader.add(jLabelOption);

    }

    private void initBodyContent(){
        adminUserEditMsg = AdminUserEditMsg.createAdminUserEditMsg();
        usersNum = adminUserEditMsg.getUserNum();
        if(usersNum == 0){
            //TODO: 一个暂无数据的标
        } else{
            initDetailPanels();
            for(int i = 0; i < 10 || i < usersNum; i++){
                jPanelsUsers[i].setVisible(true);
                table.add(jPanelsUsers[i]);
                addDetailMsg(i);
            }

        }
    }

    /**
     * 用来初始化用户信息的每一个横行
     */
    private void initDetailPanels(){
        for(int i = 0; i < 10; i++){
            jPanelsUsers[i].setLayout(null);
            if(i % 2 == 0){
                jPanelsUsers[i].setBackground(Color.LIGHT_GRAY);
            } else {
                jPanelsUsers[i].setBackground(Color.WHITE);
            }
            jPanelsUsers[i].setSize(WIDTH_TABLE, HEIGHT_DETAIL);
            jPanelsUsers[i].setLocation(TABLE_X,TABLE_Y+HEIGHT_ROW+i*HEIGHT_DETAIL);
        }
    }

    private void addDetailMsg(int i){
        int y = 0;
        JTextField jTextFieldIndex = new JTextField();
        jTextFieldIndex.setText(String.valueOf((i+whichPage*10)));
        jTextFieldIndex.setFont(MyFonts.TEXT_FONT_18);
        jTextFieldIndex.setBounds(WIDTH_BASE,0,y+2*WIDTH_BASE,HEIGHT_ROW);
        jTextFieldIndex.setVisible(true);
        jTextFieldIndex.setHorizontalAlignment(SwingConstants.CENTER);
        y += 3*WIDTH_BASE;
        jPanelTableHeader.add(jTextFieldIndex);

        JLabel jLabelID = new JLabel();
        jLabelID.setText("学号");
        jLabelID.setFont(MyFonts.TEXT_FONT_18);
        jLabelID.setBounds(y,0,4*WIDTH_BASE,HEIGHT_ROW);
        jLabelID.setVisible(true);
        jLabelID.setVerticalAlignment(SwingConstants.CENTER);
        jLabelID.setHorizontalAlignment(SwingConstants.CENTER);
        y += 4*WIDTH_BASE;
        jPanelTableHeader.add(jLabelID);

        JLabel jLabelName = new JLabel();
        jLabelName.setText("姓名");
        jLabelName.setFont(MyFonts.TEXT_FONT_18);
        jLabelName.setBounds(y,0,10*WIDTH_BASE,HEIGHT_ROW);
        jLabelName.setVisible(true);
        jLabelName.setVerticalAlignment(SwingConstants.CENTER);
        jLabelName.setHorizontalAlignment(SwingConstants.CENTER);
        y += 10*WIDTH_BASE;
        jPanelTableHeader.add(jLabelName);

        JLabel jLabelEmail = new JLabel();
        jLabelEmail.setText("邮箱");
        jLabelEmail.setFont(MyFonts.TEXT_FONT_18);
        jLabelEmail.setBounds(y,0,11*WIDTH_BASE,HEIGHT_ROW);
        jLabelEmail.setVisible(true);
        jLabelEmail.setVerticalAlignment(SwingConstants.CENTER);
        jLabelEmail.setHorizontalAlignment(SwingConstants.CENTER);
        y += 11*WIDTH_BASE;
        jPanelTableHeader.add(jLabelEmail);

        JLabel jLabelDownloadCnt = new JLabel();
        jLabelDownloadCnt.setText("下载量");
        jLabelDownloadCnt.setFont(MyFonts.TEXT_FONT_18);
        jLabelDownloadCnt.setBounds(y,0,3*WIDTH_BASE,HEIGHT_ROW);
        jLabelDownloadCnt.setVisible(true);
        jLabelDownloadCnt.setVerticalAlignment(SwingConstants.CENTER);
        jLabelDownloadCnt.setHorizontalAlignment(SwingConstants.CENTER);
        y += 3*WIDTH_BASE;
        jPanelTableHeader.add(jLabelDownloadCnt);

        JLabel jLabelDate = new JLabel();
        jLabelDate.setText("最近登录时间");
        jLabelDate.setFont(MyFonts.TEXT_FONT_18);
        jLabelDate.setBounds(y,0,10*WIDTH_BASE,HEIGHT_ROW);
        jLabelDate.setVisible(true);
        jLabelDate.setVerticalAlignment(SwingConstants.CENTER);
        jLabelDate.setHorizontalAlignment(SwingConstants.CENTER);
        y += 10*WIDTH_BASE;
        jPanelTableHeader.add(jLabelDate);

        JLabel jLabelOption = new JLabel();
        jLabelOption.setText("操作选项");
        jLabelOption.setFont(MyFonts.TEXT_FONT_18);
        jLabelOption.setBounds(y,0,6*WIDTH_BASE,HEIGHT_ROW);
        jLabelOption.setVisible(true);
        jLabelOption.setVerticalAlignment(SwingConstants.CENTER);
        jLabelOption.setHorizontalAlignment(SwingConstants.CENTER);
        jPanelTableHeader.add(jLabelOption);
    }



    public static void main(String[] args){
        AdminMainFrm adminMainFrm1 = AdminMainFrm.createAdminMainFrm();
        adminMainFrm1.setVisible(true);
    }


}
