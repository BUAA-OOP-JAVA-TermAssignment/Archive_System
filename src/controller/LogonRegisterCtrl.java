package controller;

import style.StyleCtrl;
import view.LogOnFrm;
import view.MyBootFrame;
import view.RegisterFrm;

import javax.swing.*;

public class LogonRegisterCtrl {
    static {
        // ����жϣ���ֹ��������Ĳ��Է�����ͻ
        if(StyleCtrl.getStyle() == StyleCtrl.NOT_SET) StyleCtrl.init();
    }
    // ������ʹ��
    private static LogOnFrm logOnFrm = LogOnFrm.getInstance();
    // ����ʱ����
    private static RegisterFrm registerFrm = null;
    public static final int LOGON = 0, REGISTER = 1, RUNNING = 2;

    private static int status = 0;
    public static void main(String[] args) {
        logOnFrm.setVisible(true);
    }

    public static void changeLogToReg() {
        if(status != LOGON)return;

        if(registerFrm == null) registerFrm = RegisterFrm.getInstance();

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

    public static void tryLogon() {

    }
}
