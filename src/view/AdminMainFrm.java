package view;

import controller.Closer;

import javax.swing.*;

public class AdminMainFrm extends MyFrame{
    private static final AdminMainFrm adminMainFrm = new AdminMainFrm();

    private AdminMainFrm() {
        this.addWindowListener(Closer.getNoDataUploadCloser());

        JMenuItem menuItemSearch = new JMenuItem("�û�����");
        this.getJMenuBar().getMenu(0).add(menuItemSearch);
        this.getJMenuBar().getMenu(1).setText("����Ա�˻�");
    }

    public static AdminMainFrm getInstance() {
        return adminMainFrm;
    }
}
