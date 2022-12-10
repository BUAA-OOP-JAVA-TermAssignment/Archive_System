package controller;

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
            if(NetworkCtrl.isIsChangingData()) {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "当前正在进行数据传输，要继续退出吗？", "警告", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.CANCEL_OPTION)
                    return;
            }else {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "确定要退出吗？程序将会在确认后上传数据，并在传输完毕后退出。", "退出确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.CANCEL_OPTION)
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
                if(JOptionPane.showConfirmDialog(e.getWindow(), "当前正在进行操作，要继续退出吗？", "警告", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.CANCEL_OPTION)
                    return;
            }else {
                if(JOptionPane.showConfirmDialog(e.getWindow(), "确定要退出吗？", "退出确认", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.CANCEL_OPTION)
                    return;
            }
            System.exit(0);
        }
    };

    private static void uploadGuestData() {
        if(!hasUserInfoChange)return;
        // TODO:更新访客信息
        System.out.println("Closer : uploading data");
    }

    public static WindowAdapter getDataUploadCloser() {
        return dataUploadCloser;
    }
    public static WindowAdapter getNoDataUploadCloser() {
        return noDataUploadCloser;
    }
}
