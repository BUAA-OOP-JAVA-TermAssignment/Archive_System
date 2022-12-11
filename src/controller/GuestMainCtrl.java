package controller;

import client.Client;
import message.BaseMsg;
import message.SearchRequestMsg;
import message.SearchReturnMsg;
import message.UserLoginRequestMsg;
import style.StyleCtrl;
import view.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.PrintStream;
import java.util.Objects;

public class GuestMainCtrl {
    //TODO:test
    static {
        StyleCtrl.setStyle(StyleCtrl.DARCULA);
    }

    private static Client myClient = Client.getMyClient();
    // 主窗口直接加载
    private static MyFrame mainWindow = GuestMainFrm.getInstance();
    // 默认首先显示的小窗口，直接加载
    private static GuestSearchFrm searchFrm = GuestSearchFrm.getInstance();
    private static UserCentreFrm centreFrm = null;
    private static JFileChooser fileChooser = null;


    private static void setMenu() {
        JMenuItem menuItemSearch = new JMenuItem("搜索");
        menuItemSearch.addActionListener(actionEvent -> {
            searchFrm.setVisible(true);
            searchFrm.toFront();
        });
        mainWindow.getJMenuBar().getMenu(0).add(menuItemSearch);

        JMenuItem menuItemPersonCenter = new JMenuItem("个人中心");
        menuItemPersonCenter.addActionListener(actionEvent -> {
            if(centreFrm == null) {
                initPersonalCentre();
            }
            centreFrm.setVisible(true);
            centreFrm.toFront();
        });
        mainWindow.getJMenuBar().getMenu(1).setText("访客账户");
        mainWindow.getJMenuBar().getMenu(1).add(menuItemPersonCenter);
    }

    private static void showDefaultInterFrm(MyInterFrame interFrame) {
        interFrame.setVisible(true);
        mainWindow.getTable().add(interFrame);
        interFrame.setBounds(0, 0,1000, 600);
    }

    private static void initPersonalCentre() {
        centreFrm = UserCentreFrm.getInstance();
        mainWindow.getTable().add(centreFrm);
    }
    public static void startMainWindow() {
        setMenu();
        showDefaultInterFrm(searchFrm);



        LogOnFrm.getInstance().setVisible(false);
        LogOnFrm.getInstance().setEnabled(false);
        RegisterFrm.getInstance().setVisible(false);
        RegisterFrm.getInstance().setEnabled(false);
        mainWindow.setVisible(true);


        trySearch("", 0, GuestSearchFrm.MAX_ELEMENT);
    }

    public static synchronized void trySearch(String searchText, int offset, int cnt) {
        makeAllWait();
        System.out.println("LogonRegisterCtrl : receive search request : " + searchText + " " + offset + " " + cnt);

        // 当发送消息时连接还未就绪
        if(myClient == null) {
            searchFrm.connectError();
            makeAllDeWait();
            return;
        }
        int ret = myClient.sendMsg(SearchRequestMsg.createSearchRequestMsg(searchText, offset, cnt));

        if(ret == Client.SUCCESS) searchFrm.sendMsgNotice();
        else {
            searchFrm.connectError();
            makeAllDeWait();
            return;
        }

        BaseMsg retMsg = myClient.waitMsg();
        if(retMsg.getMsgCode() ==  - BaseMsg.SEARCH_ARCHIVE) {
            try{
                searchFrm.searchSuccess((SearchReturnMsg) retMsg, searchText, offset);
            }catch (Exception e ){
                searchFrm.undefinedFailed();
            }
            makeAllDeWait();
            return;
        }


        if(retMsg.getMsgCode() == BaseMsg.TIME_OUT) {
            searchFrm.timeoutError();
            makeAllDeWait();
            return;
        }

        if(retMsg.getMsgCode() == BaseMsg.UNDEFINED_FAILED) {
            searchFrm.undefinedFailed();
            makeAllDeWait();
            return;
        }

        System.out.println("!!!LogonRegisterCtrl : logon undefined return message");
        makeAllDeWait();
    }

    public static synchronized void tryDownload(ActionEvent actionEvent, String title, String id) {
        makeAllWait();
        System.out.println("LogonRegisterCtrl : download clicked " + title + " " + id);
        JButton source;
        try {
            source = (JButton) actionEvent.getSource();
        }catch (Exception e) {
            System.out.println("!!!LogonRegisterCtrl : class type change error");
            makeAllDeWait();
            return;
        }

        source.setText("请选择路径");
        if(fileChooser == null) {
            initFileChooser();
        }
        fileChooser.setName(title);
        int result = fileChooser.showSaveDialog(searchFrm);

        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if(selectedFile.exists()) {
                if(JOptionPane.showConfirmDialog(searchFrm, "选中的文件已经存在，要覆盖它吗？", "警告", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.OK_OPTION) {
                    source.setText("下载...");
                    makeAllDeWait();
                    return;
                }
            }
            source.setText("正在下载...");
            boolean ret = DownloadCtrl.downloadFile(id, selectedFile.getPath(), selectedFile.getName());

            if(ret)
                source.setText("下载成功");
            else
                source.setText("下载失败");

            makeAllDeWait();
            return;
        }

        source.setText("下载...");
        if(result == JFileChooser.ERROR_OPTION) {
            JOptionPane.showConfirmDialog(searchFrm, "文件选择发生错误！", "警告", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        }
        makeAllDeWait();
    }

    private static void initFileChooser() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF files", "pdf"));
    }

    private static void makeAllWait() {
        if(searchFrm != null)
            searchFrm.enWaitMode();
        if(centreFrm != null)
            centreFrm.enWaitMode();
    }

    private static void makeAllDeWait() {
        if(searchFrm != null)
            searchFrm.disWaitMode();
        if(centreFrm != null)
            centreFrm.disWaitMode();
    }

    public static void main(String[] args) {
        GuestMainCtrl.startMainWindow();
    }
}
