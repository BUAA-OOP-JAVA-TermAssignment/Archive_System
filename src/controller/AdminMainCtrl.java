package controller;

import client.Client;
import data.AdminData;
import data.UserData;
import message.AdminUserRequestMsg;
import message.BaseMsg;
import message.LoginReturnMsg;
import message.UserLoginRequestMsg;
import style.StyleCtrl;
import view.AdminMainFrm;
import view.AdminUserEditFrm;
import view.LogOnFrm;
import view.RegisterFrm;

/**
 * @author : AkashiSensei
 * &#064;date : 2022/12/10 16:18
 */
public class AdminMainCtrl {


    static {
        // ����жϣ���ֹ��������Ĳ��Է�����ͻ
        if(StyleCtrl.getStyle() == StyleCtrl.NOT_SET) StyleCtrl.init();
    }
    private static Client myClient = null;
    // ������ʹ��
    private static final AdminMainFrm adminMainFrm = AdminMainFrm.getInstance();
    private static final AdminUserEditFrm adminUserEditFrm = AdminUserEditFrm.createAdminEditFrm();

    private static int status = 0;
    public static void main(String[] args) {
        adminMainFrm.setVisible(true);
        adminMainFrm.setVisible(true);
        //System.out.println("startGet");
        myClient = Client.getMyClient();
        System.out.println("AdminMainCtrl : client ready");
    }

    private static final AdminMainFrm mainWindow = AdminMainFrm.getInstance();


    public static void startMainWindow() {
        LogOnFrm.getInstance().setVisible(false);
        LogOnFrm.getInstance().setEnabled(false);
        RegisterFrm.getInstance().setVisible(false);
        RegisterFrm.getInstance().setEnabled(false);
        mainWindow.setVisible(true);
    }

    public static boolean tryLoad() {
        System.out.println("AdminMainCtrl : receive adminMain request");

        // ��������Ϣʱ���ӻ�δ����
        if(myClient == null) {
            adminMainFrm.disWaitMode();
            return false;
        }
        int ret = myClient.sendMsg(new BaseMsg(BaseMsg.ADMIN_USER_SEND));

        if(ret == Client.SUCCESS) adminMainFrm.sendMsgNotice();
        else {

            adminMainFrm.disWaitMode();
            return false;
        }

        BaseMsg retMsg = myClient.waitMsg();
        if(retMsg.getMsgCode() == - BaseMsg.ADMIN_USER_REQUEST) {
            // ������Ϣ
            AdminUserEditFrm adminUserEditFrm = AdminUserEditFrm.createAdminEditFrm();
            AdminUserRequestMsg adminUserRequestMsg;
            try{
                adminUserRequestMsg = (AdminUserRequestMsg) retMsg;
            }catch (Exception e) {
                System.out.println("!!!LogonRegisterCtrl : success return message type error");
                adminMainFrm.disWaitMode();
                return false;
            }

            for(int i = 0; i < adminUserRequestMsg.getUserNum(); i++){
                AdminData.getInstance().add(adminUserRequestMsg.getUserId(i), adminUserRequestMsg.getUserName(i), adminUserRequestMsg.getUserPassword(i),
                        adminUserRequestMsg.getUserEmail(i), adminUserRequestMsg.getUserDownloadCnt(i),adminUserRequestMsg.getUserDate(i));
            }

            return true;
        }

        adminMainFrm.disWaitMode();
        if(retMsg.getMsgCode() == BaseMsg.TIME_OUT) {
            adminMainFrm.timeoutError();
            return false;
        }


        System.out.println("!!!LogonRegisterCtrl : logon undefined return message");
        return false;
    }
}
