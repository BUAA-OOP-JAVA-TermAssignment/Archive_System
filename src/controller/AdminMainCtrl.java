package controller;

import view.AdminMainFrm;
import view.LogOnFrm;
import view.RegisterFrm;

/**
 * @author : AkashiSensei
 * &#064;date : 2022/12/10 16:18
 */
public class AdminMainCtrl {
    private static final AdminMainFrm mainWindow = AdminMainFrm.getInstance();

    public static void startMainWindow() {
        LogOnFrm.getInstance().setVisible(false);
        LogOnFrm.getInstance().setEnabled(false);
        RegisterFrm.getInstance().setVisible(false);
        RegisterFrm.getInstance().setEnabled(false);
        mainWindow.setVisible(true);
    }
}
