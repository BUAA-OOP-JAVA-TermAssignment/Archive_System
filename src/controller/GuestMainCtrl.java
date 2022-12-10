package controller;

import view.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;

public class GuestMainCtrl {
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
        try {
            interFrame.setMaximum(true);
        } catch (PropertyVetoException e) {
            System.out.println("GuestMainCtrl : default inter frame maximum error");
        }
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
}
