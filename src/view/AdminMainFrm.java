package view;

import controller.Closer;

public class AdminMainFrm extends MyFrame {
    private static final AdminMainFrm adminMainFrm = new AdminMainFrm();

    private AdminMainFrm() {
        this.addWindowListener(Closer.getNoDataUploadCloser());
    }

    public static AdminMainFrm getInstance() {
        return adminMainFrm;
    }

}