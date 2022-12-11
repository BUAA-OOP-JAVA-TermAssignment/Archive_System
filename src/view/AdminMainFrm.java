package view;

import controller.Closer;

import javax.swing.*;
import java.awt.*;

public class AdminMainFrm extends MyFrame {
    public final JLabel msgLabel = new JLabel();
    private static final AdminMainFrm adminMainFrm = new AdminMainFrm();

    private AdminMainFrm() {
        this.addWindowListener(Closer.getNoDataUploadCloser());
    }

    public static AdminMainFrm getInstance() {
        return adminMainFrm;
    }

}