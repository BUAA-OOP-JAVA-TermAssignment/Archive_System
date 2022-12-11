package controller;

import client.Client;
import data.UserData;
import message.BaseMsg;
import message.ModifyUserInfo;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Closer {
    //TODO:����һ�����뵽UserData��
    private static boolean hasUserInfoChange = true;

    private final static WindowAdapter dataUploadCloser = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Closer : windows guest closer tripped");
            if(Client.getMyClient().isDataChanging()) {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "��ǰ���ڽ������ݴ��䣬Ҫ�����˳���", "����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.OK_OPTION)
                    return;
            }else {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "ȷ��Ҫ�˳��𣿳��򽫻���ȷ�Ϻ��ϴ����ݣ����ڴ�����Ϻ��˳���", "�˳�ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION)
                    return;
            }

            while (!uploadGuestData()) {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "���ݸ���ʧ�ܣ�Ҫ�ٴγ��Է�����Ϣ�����ȷ�����ٴη��ͣ�ȡ����������˳�����", "����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.OK_OPTION)
                    break;
            }

            if(!Client.getMyClient().disConnect()) {
                JOptionPane.showMessageDialog(e.getWindow(), "�ر���������ʧ�ܣ��������ӿ���δ�ɹ������������Ѿ��쳣�Ͽ������򽫻�ֱ���˳���", "����", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        }
    };


    private final static WindowAdapter noDataUploadCloser = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Closer : windows admin closer tripped");
            if(Client.getMyClient().isDataChanging()) {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "��ǰ���ڽ��в�����Ҫ�����˳���", "����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.OK_OPTION)
                    return;
            }else {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "ȷ��Ҫ�˳���", "�˳�ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) != JOptionPane.OK_OPTION)
                    return;
            }

            if(!Client.getMyClient().disConnect()) {
                JOptionPane.showMessageDialog(e.getWindow(), "�ر���������ʧ�ܣ��������ӿ���δ�ɹ������������Ѿ��쳣�Ͽ������򽫻�ֱ���˳���", "����", JOptionPane.ERROR_MESSAGE);
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
