package controller;

import client.Client;
import data.UserData;
import message.BaseMsg;
import message.LoginReturnMsg;
import message.UserLoginRequestMsg;
import message.UserRegisterRequestMsg;
import style.StyleCtrl;
import view.LogOnFrm;
import view.RegisterFrm;


public class LogonRegisterCtrl {
    public static final int LOGON = 0, REGISTER = 1, RUNNING = 2;
    static {
        // ����жϣ���ֹ��������Ĳ��Է�����ͻ
        if(StyleCtrl.getStyle() == StyleCtrl.NOT_SET) StyleCtrl.init();
    }


    private static Client myClient = null;
    // ������ʹ��
    private static final LogOnFrm logOnFrm = LogOnFrm.getInstance();
    // ����ʱ����
    private static RegisterFrm registerFrm = null;


    private static int status = 0;
    public static void main(String[] args) {
        logOnFrm.setVisible(true);
        //System.out.println("startGet");
        myClient = Client.getMyClient();
        System.out.println("LogonRegisterCtrl : client ready");
    }

    /**
     * �ɵ�¼�����л���ע�ᴰ��
     */
    public static void changeLogToReg() {
        if(status != LOGON)return;

        if(registerFrm == null) registerFrm = RegisterFrm.getInstance();

        registerFrm.setVisible(true);
        registerFrm.setLocationRelativeTo(logOnFrm);
        logOnFrm.setVisible(false);
        status = REGISTER;
    }

    /**
     * ��ע�ᴰ���л�����¼����
     */
    public static void changeRegToLog() {
        if(status != REGISTER)return;


        logOnFrm.setVisible(true);
        logOnFrm.setLocationRelativeTo(registerFrm);
        registerFrm.setVisible(false);
        status = LOGON;
    }

    /**
     * �ڸ��������ĺϷ��Լ���ͨ�����������������Ϣ���������ȴ������Ե�½��
     * ����¼ʧ��ʱ������õ�¼��������ط�������ʾ������Ϣ��
     * ��¼�ɹ�ʱ��������ע��͵�¼���ڣ����½��߳����������ڣ�ͬʱ�������������ص��û���Ϣ�����档
     * @param userType �û����ͣ�1����ÿͣ�2�������Ա
     * @param id �û�ID
     * @param password �û�����
     */
    public static synchronized void tryLogon(int userType, String id, String password) {
        System.out.println("LogonRegisterCtrl : receive logon request : " + id + " " + password);

        // ��������Ϣʱ���ӻ�δ����
        if(myClient == null) {
            logOnFrm.connectError();
            return;
        }
        int ret = myClient.sendMsg(UserLoginRequestMsg.createLoginRequestMsg(userType, id, password));

        if(ret == Client.SUCCESS) logOnFrm.sendMsgNotice();
        else {
            logOnFrm.connectError();
            return;
        }

        BaseMsg retMsg = myClient.waitMsg();
        if(retMsg.getMsgCode() == - BaseMsg.LOGIN) {
            logOnFrm.logonSuccess();
            //TODO:��������

            LoginReturnMsg loginReturnMsg;
            try{
                loginReturnMsg = (LoginReturnMsg) retMsg;
            }catch (Exception e) {
                System.out.println("!!!LogonRegisterCtrl : success return message type error");
                return;
            }


            if(userType == LogOnFrm.GUEST)
                new Thread(GuestMainCtrl::startMainWindow).start();
            else if(userType == LogOnFrm.ADMIN)
                new Thread(AdminMainCtrl::startMainWindow).start();
            status = RUNNING;

            // �����û���Ϣ�������û������������ж��û�������Ϣ�Ƿ񱣴���ϵ��߼������ⲿ�ַŵ����ڴ򿪺����
            UserData.getInstance().readInfo(
                    loginReturnMsg.getUserName(),
                    loginReturnMsg.getId(),
                    loginReturnMsg.getEmail(),
                    loginReturnMsg.getPassword(),
                    loginReturnMsg.getDownloadCnt()
            );
            return;
        }

        if(retMsg.getMsgCode() == BaseMsg.TIME_OUT) {
            logOnFrm.timeoutError();
            return;
        }

        if(retMsg.getMsgCode() == BaseMsg.UNDEFINED_FAILED) {
            logOnFrm.idOrPasswordError();
            return;
        }

        System.out.println("!!!LogonRegisterCtrl : logon undefined return message");
    }


    /**
     * �ڸ��������ĺϷ��Լ���ͨ�����������������Ϣ���������ȴ�������ע�ᡣ
     * ��ע��ʧ��ʱ�������ע�ᴰ������Ӧ���������û���ʾʧ����Ϣ��
     * ��ע��ɹ��󣬻Ὣע����û�ID�������������¼������Ӧλ�ã�����ת�ص�¼���ڡ�
     * @param name
     * @param id
     * @param password
     * @param email
     */
    public static synchronized void tryRegister(String name, String id, String password, String email) {
        System.out.println("LogonRegisterCtrl : receive register request : " + name + " " + id + " " + password + " " +email);

        // ��������Ϣʱ���ӻ�δ����
        if(myClient == null) {
            registerFrm.connectError();
            return;
        }

        int ret = myClient.sendMsg(UserRegisterRequestMsg.createRegisterRequestMsg(name, id, password, email));

        if(ret == Client.SUCCESS) registerFrm.sendMsgNotice();
        else {
            registerFrm.connectError();
            return;
        }

        // ����
        BaseMsg retMsg = myClient.waitMsg();
        if(retMsg.getMsgCode() == BaseMsg.SUCCESS) {
            registerFrm.registerSuccess();
            logOnFrm.getjTextField().setText(id);
            logOnFrm.getjPasswordField().setText(password);
            logOnFrm.getjComboBoxSelectUserType().setSelectedIndex(1);
            logOnFrm.registerSuccess();
            changeRegToLog();
            return;
        }
        if(retMsg.getMsgCode() == BaseMsg.TIME_OUT) {
            registerFrm.timeoutError();
            return;
        }
        if(retMsg.getMsgCode() == BaseMsg.UNDEFINED_FAILED) {
            registerFrm.registerFailed();
            return;
        }

        System.out.println("!!!LogonRegisterCtrl : register undefined return message");
    }
}
