package view;

import controller.Closer;

import javax.swing.*;

public class AdminMainFrm extends MyFrame{
    private static final AdminMainFrm adminMainFrm = new AdminMainFrm();

    private AdminMainFrm() {
        this.addWindowListener(Closer.getNoDataUploadCloser());

        JMenuItem menuItemSearch = new JMenuItem("用户管理");
        this.getJMenuBar().getMenu(0).add(menuItemSearch);
        this.getJMenuBar().getMenu(1).setText("管理员账户");
    }

    public static AdminMainFrm getInstance() {
        return adminMainFrm;
    }
}
