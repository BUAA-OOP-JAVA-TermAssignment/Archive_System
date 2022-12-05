package controller;

import style.StyleCtrl;
import view.LogOnFrm;
import view.MyBootFrame;
import view.RegisterFrm;

import javax.swing.*;

public class LogonRegisterCtrl {
    private final static LogOnFrm logOnFrm = new LogOnFrm();
    private final static RegisterFrm registerFrm = new RegisterFrm();
    public static final int LOGON = 0, REGISTER = 1, RUNNING = 2;

    private static int status = 0;
    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARK);
        logOnFrm.setVisible(true);
    }

    public static void changeLogToReg() {
        if(status != LOGON)return;
        registerFrm.setVisible(true);
        registerFrm.setLocationRelativeTo(logOnFrm);
        logOnFrm.setVisible(false);
        status = REGISTER;
    }

    public static void changeRegToLog() {
        if(status != REGISTER)return;
        logOnFrm.setVisible(true);
        logOnFrm.setLocationRelativeTo(registerFrm);
        registerFrm.setVisible(false);
        status = LOGON;
    }

    public static void timeoutWakeupTest(MyBootFrame sourceFrame) {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            }catch (Exception e) {
                System.out.println("!!! LogonRegisterCtrl : Thread sleep error");
            }
            sourceFrame.disWaitMode();
        }).start();
    }
}
