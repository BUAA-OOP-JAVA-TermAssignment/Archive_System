package controller;

import view.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;

public class GuestMainCtrl {
    // 主窗口直接加载
    private static MyFrame mainWindow = GuestMainFrm.getInstance();
    // 默认首先显示的小窗口，直接加载
    private static GuestSearchFrm searchFrm = GuestSearchFrm.getInstance();
    private static UserCentreFrm centreFrm = null;


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
