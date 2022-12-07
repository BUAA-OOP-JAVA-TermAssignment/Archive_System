package controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Closer {
    //TODO:����һ�����뵽UserData��
    private static boolean hasUserInfoChange = true;

    private final static WindowAdapter confirmCloser = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Closer : windows confirm closer tripped");
            if(NetworkCtrl.isIsChangingData() == true) {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "��ǰ���ڽ������ݴ��䣬Ҫ�����˳���", "����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.CANCEL_OPTION)return;
            }else {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "ȷ��Ҫ�˳���", "�˳�ȷ��", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.CANCEL_OPTION)return;
            }
            uploadData();
            System.exit(0);
        }
    };

    private static void uploadData() {
        if(!hasUserInfoChange)return;
        System.out.println("Closer : uploading data");
    }

    public static WindowAdapter getConfirmCloser() {
        return confirmCloser;
    }
}
