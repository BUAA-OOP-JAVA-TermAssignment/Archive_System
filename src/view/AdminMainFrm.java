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
    public void sendMsgNotice() {
        msgLabel.setText("正在与数据库连接...");
        msgLabel.setForeground(Color.YELLOW);
        msgLabel.setVisible(true);
    }

    public void timeoutError() {
        msgLabel.setText("服务器连接超时，请稍后重试");
        msgLabel.setForeground(Color.RED);
        msgLabel.setVisible(true);
    }
}