package view;

import controller.Closer;

import javax.swing.*;
import java.awt.*;

public class AdminMainFrm extends MyFrame {
    public final JLabel msgLabel = new JLabel();
    private static final AdminMainFrm adminMainFrm = new AdminMainFrm();
    private AdminUserEditFrm adminUserEditFrm;

    private AdminMainFrm() {
        this.addWindowListener(Closer.getNoDataUploadCloser());
        adminUserEditFrm = AdminUserEditFrm.createAdminEditFrm();
        adminUserEditFrm.setVisible(true);
        this.add(adminUserEditFrm);
    }

    public static AdminMainFrm getInstance() {
        return adminMainFrm;
    }

}