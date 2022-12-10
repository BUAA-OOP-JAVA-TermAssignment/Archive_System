package controller;

import view.*;

public class GuestMainCtrl {
    private static MyFrame mainWindow = GuestMainFrm.getInstance();
    private static GuestSearchFrm searchFrm = null;
    private static UserCentreFrm centreFrm = null;

    public static void startMainWindow() {
        LogOnFrm.getInstance().setVisible(false);
        LogOnFrm.getInstance().setEnabled(false);
        RegisterFrm.getInstance().setVisible(false);
        RegisterFrm.getInstance().setEnabled(false);
        mainWindow.setVisible(true);


    }
}
