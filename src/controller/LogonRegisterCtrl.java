package controller;

import client.Client;
import data.UserData;
import message.BaseMsg;
import message.LoginReturnMsg;
import message.UserLoginRequestMsg;
import style.StyleCtrl;
import view.AdminMainFrm;
import view.LogOnFrm;
import view.RegisterFrm;


public class LogonRegisterCtrl {
    public static final int LOGON = 0, REGISTER = 1, RUNNING = 2;
    static {
        // 添加判断，防止和其他类的测试方法冲突
        if(StyleCtrl.getStyle() == StyleCtrl.NOT_SET) StyleCtrl.init();
    }


    private static Client myClient = null;
    // 启动即使用
    private static final LogOnFrm logOnFrm = LogOnFrm.getInstance();
    // 调用时加载
    private static RegisterFrm registerFrm = null;


    private static int status = 0;
    public static void main(String[] args) {
        logOnFrm.setVisible(true);
        //System.out.println("startGet");
        myClient = Client.getMyClient();
        System.out.println("LogonRegisterCtrl : client ready");
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

    public static void tryLogon(int userType, String id, String password) {
        System.out.println("LogonRegisterCtrl : receive logon request : " + id + " " + password);

        // 当发送消息时连接还未就绪
        if(myClient == null) {
            logOnFrm.connectError();
            logOnFrm.disWaitMode();
            return;
        }
        int ret = myClient.sendMsg(UserLoginRequestMsg.createLoginRequestMsg(userType, id, password));

        if(ret == Client.SUCCESS) logOnFrm.sendMsgNotice();
        else {
            logOnFrm.connectError();
            logOnFrm.disWaitMode();
            return;
        }

        BaseMsg retMsg = myClient.waitMsg();
        if(retMsg.getMsgCode() == - BaseMsg.LOGIN) {
            logOnFrm.logonSuccess();
            //TODO:打开主界面

            LoginReturnMsg loginReturnMsg;
            try{
                loginReturnMsg = (LoginReturnMsg) retMsg;
            }catch (Exception e) {
                System.out.println("!!!LogonRegisterCtrl : success return message type error");
                logOnFrm.disWaitMode();
                return;
            }


            if(userType == LogOnFrm.GUEST)
                new Thread(GuestMainCtrl::startMainWindow).start();
            else if(userType == LogOnFrm.ADMIN)
                new Thread(AdminMainCtrl::startMainWindow).start();
            status = RUNNING;

            // 保存用户信息，由于用户个人中心有判断用户个人信息是否保存完毕的逻辑，故这部分放到窗口打开后操作
            UserData.getInstance().readInfo(
                    loginReturnMsg.getUserName(),
                    loginReturnMsg.getId(),
                    loginReturnMsg.getEmail(),
                    loginReturnMsg.getPassword(),
                    loginReturnMsg.getDownloadCnt()
            );
            return;
        }

        logOnFrm.disWaitMode();
        if(retMsg.getMsgCode() == BaseMsg.TIME_OUT) {
            logOnFrm.timeoutError();
            return;
        }

        if(retMsg.getMsgCode() == BaseMsg.UNDEFINED_FAILED) {
            logOnFrm.idOrPasswordError();
            return;
        }

        System.out.println("!!!LogonRegisterCtrl : undefined return message");
    }
}
