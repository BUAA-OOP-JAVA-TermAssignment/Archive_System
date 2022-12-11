package controller;

import client.Client;
import message.BaseMsg;
import message.SearchRequestMsg;
import message.SearchReturnMsg;
import message.UserLoginRequestMsg;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;

public class GuestMainCtrl {

    private static Client myClient = Client.getMyClient();
    // ������ֱ�Ӽ���
    private static MyFrame mainWindow = GuestMainFrm.getInstance();
    // Ĭ��������ʾ��С���ڣ�ֱ�Ӽ���
    private static GuestSearchFrm searchFrm = GuestSearchFrm.getInstance();
    private static UserCentreFrm centreFrm = null;


    private static void setMenu() {
        JMenuItem menuItemSearch = new JMenuItem("����");
        menuItemSearch.addActionListener(actionEvent -> {
            searchFrm.setVisible(true);
            searchFrm.toFront();
        });
        mainWindow.getJMenuBar().getMenu(0).add(menuItemSearch);

        JMenuItem menuItemPersonCenter = new JMenuItem("��������");
        menuItemPersonCenter.addActionListener(actionEvent -> {
            if(centreFrm == null) {
                initPersonalCentre();
            }
            centreFrm.setVisible(true);
            centreFrm.toFront();
        });
        mainWindow.getJMenuBar().getMenu(1).setText("�ÿ��˻�");
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
    }

    public static void trySearch(String searchText, int offset, int cnt) {
        makeAllWait();
        System.out.println("LogonRegisterCtrl : receive search request : " + searchText + " " + offset + " " + cnt);

        // ��������Ϣʱ���ӻ�δ����
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
                searchFrm.searchSuccess((SearchReturnMsg) retMsg);
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
}
