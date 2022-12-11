package controller;

import client.Client;
import data.UserData;
import message.BaseMsg;
import message.ModifyUserInfo;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Closer {
    //TODO:把这一项移入到UserData中
    private static boolean hasUserInfoChange = true;

    private final static WindowAdapter dataUploadCloser = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Closer : windows guest closer tripped");
            if(Client.getMyClient().isDataChanging()) {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "当前正在进行数据传输，要继续退出吗？", "警告", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.OK_OPTION)
                    return;
            }else {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "确定要退出吗？程序将会在确认后上传数据，并在传输完毕后退出。", "退出确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION)
                    return;
            }

            while (!uploadGuestData()) {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "数据更新失败，要再次尝试发送消息吗？如果确定则再次发送，取消将会继续退出程序。", "警告", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.OK_OPTION)
                    break;
            }

            if(!Client.getMyClient().disConnect()) {
                JOptionPane.showMessageDialog(e.getWindow(), "关闭网络连接失败，网络连接可能未成功建立，或者已经异常断开，程序将会直接退出。", "错误", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        }
    };


    private final static WindowAdapter noDataUploadCloser = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Closer : windows admin closer tripped");
            if(Client.getMyClient().isDataChanging()) {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "当前正在进行操作，要继续退出吗？", "警告", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.OK_OPTION)
                    return;
            }else {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "确定要退出吗？", "退出确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION)
                    return;
            }

            if(!Client.getMyClient().disConnect()) {
                JOptionPane.showMessageDialog(e.getWindow(), "关闭网络连接失败，网络连接可能未成功建立，或者已经异常断开，程序将会直接退出。", "错误", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        }
    };

    private static boolean uploadGuestData() {
        if(!hasUserInfoChange)return true;

        System.out.println("Closer : uploading data");

        UserData userData = UserData.getInstance();
        int ret = Client.getMyClient().sendMsg(ModifyUserInfo.createModifyUserInfo(
                userData.getId(),
                userData.getPassword(),
                userData.getEmail(),
                userData.getDownloadNum()
        ));

        if(ret != Client.SUCCESS)
            return false;


        BaseMsg retMsg = Client.getMyClient().waitMsg();
        return retMsg.getMsgCode() == BaseMsg.SUCCESS;
    }

    public static WindowAdapter getDataUploadCloser() {
        return dataUploadCloser;
    }
    public static WindowAdapter getNoDataUploadCloser() {
        return noDataUploadCloser;
    }
}
