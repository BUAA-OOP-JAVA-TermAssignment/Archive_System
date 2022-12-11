package controller;

import client.Client;
import data.AdminData;
import data.UserData;
import message.*;
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
        // 添加判断，防止和其他类的测试方法冲突
        if(StyleCtrl.getStyle() == StyleCtrl.NOT_SET) StyleCtrl.init();
    }
    private static Client myClient = Client.getMyClient();
    // 启动即使用
    private static final AdminMainFrm adminMainFrm = AdminMainFrm.getInstance();
    //private static final AdminUserEditFrm adminUserEditFrm = AdminUserEditFrm.createAdminEditFrm();

    public static void main(String[] args) {
        adminMainFrm.setVisible(true);
        //System.out.println("startGet");
        myClient = Client.getMyClient();
        System.out.println("AdminMainCtrl : client ready");
    }

    private static final AdminMainFrm mainWindow = AdminMainFrm.getInstance();
    private static final AdminUserEditFrm adminUserEditFrm = AdminUserEditFrm.getInstance();


    public static void startMainWindow() {
        LogOnFrm.getInstance().setVisible(false);
        LogOnFrm.getInstance().setEnabled(false);
        RegisterFrm.getInstance().setVisible(false);
        RegisterFrm.getInstance().setEnabled(false);
        mainWindow.setVisible(true);
        adminUserEditFrm.Load();
    }

    public static synchronized boolean tryLoad() {
        adminMainFrm.enWaitMode();
        System.out.println("AdminMainCtrl : receive adminMain request");
        myClient = Client.getMyClient();
        // 当发送消息时连接还未就绪
        if(myClient == null) {
            adminMainFrm.disWaitMode();
            return false;
        }
        int ret = myClient.sendMsg(new BaseMsg(BaseMsg.ADMIN_USER_SEND));

        if(ret != Client.SUCCESS){
            adminMainFrm.disWaitMode();
            return false;
        }

        BaseMsg retMsg = myClient.waitMsg();
        if(retMsg.getMsgCode() == - BaseMsg.ADMIN_USER_REQUEST) {
            // 加载信息
            AdminUserRequestMsg adminUserRequestMsg;
            try{
                adminUserRequestMsg = (AdminUserRequestMsg) retMsg;
                System.out.println("Success!!!");
            }catch (Exception e) {
                System.out.println("AdminUserCtrl : return message error");
                adminMainFrm.disWaitMode();
                return false;
            }

            for(int i = 0; i < adminUserRequestMsg.getUserNum(); i++){
                AdminData.getInstance().add(adminUserRequestMsg.getUserId(i), adminUserRequestMsg.getUserName(i), adminUserRequestMsg.getUserPassword(i),
                        adminUserRequestMsg.getUserDownloadCnt(i),adminUserRequestMsg.getUserEmail(i), adminUserRequestMsg.getUserDate(i));
            }
            adminMainFrm.disWaitMode();
            return true;
        }

        return false;
    }

    public static synchronized boolean Change(String userName,String id, String email, String password, int downloadCnt, String date){
        adminMainFrm.enWaitMode();
        System.out.println("AdminMainCtrl : adminUserEdit reset downloadCnt request");
        if(myClient == null) {
            adminMainFrm.disWaitMode();
            return false;
        }
        int ret = myClient.sendMsg(AdminUserEditMsg.createAdminUserEditMsg(AdminUserEditMsg.CHANGE,userName,id,email,password,downloadCnt,date));

        if(ret == Client.SUCCESS){
            adminMainFrm.disWaitMode();
            return true;
        } else {
            adminMainFrm.disWaitMode();
            return false;
        }
    }

    public static synchronized boolean Delete(String userName,String id, String email, String password, int downloadCnt, String date){
        adminMainFrm.enWaitMode();
        System.out.println("AdminMainCtrl : adminUserEdit delete request");
        if(myClient == null) {
            adminMainFrm.disWaitMode();
            return false;
        }
        int ret = myClient.sendMsg(AdminUserEditMsg.createAdminUserEditMsg(AdminUserEditMsg.DELETE,userName,id,email,password,downloadCnt,date));

        if(ret == Client.SUCCESS){
            adminMainFrm.disWaitMode();
            return true;
        } else {
            adminMainFrm.disWaitMode();
            return false;
        }
    }


}
