package controller;

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
            if(NetworkCtrl.isIsChangingData()) {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "��ǰ���ڽ������ݴ��䣬Ҫ�����˳���", "����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.CANCEL_OPTION)
                    return;
            }else {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "ȷ��Ҫ�˳��𣿳��򽫻���ȷ�Ϻ��ϴ����ݣ����ڴ�����Ϻ��˳���", "�˳�ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.CANCEL_OPTION)
                    return;
            }
            uploadGuestData();
            System.exit(0);
        }
    };


    private final static WindowAdapter noDataUploadCloser = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Closer : windows admin closer tripped");
            if(NetworkCtrl.isIsChangingData()) {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "��ǰ���ڽ��в�����Ҫ�����˳���", "����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.CANCEL_OPTION)
                    return;
            }else {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "ȷ��Ҫ�˳���", "�˳�ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.CANCEL_OPTION)
                    return;
            }
            System.exit(0);
        }
    };

    private static void uploadGuestData() {
        if(!hasUserInfoChange)return;
        // TODO:���·ÿ���Ϣ
        System.out.println("Closer : uploading data");
    }

    public static WindowAdapter getDataUploadCloser() {
        return dataUploadCloser;
    }
    public static WindowAdapter getNoDataUploadCloser() {
        return noDataUploadCloser;
    }
}
