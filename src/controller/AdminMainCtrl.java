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

    /**
     * 在登录成功后，加载管理端主界面
     */
    public static void startMainWindow() {
        LogOnFrm.getInstance().setVisible(false);
        LogOnFrm.getInstance().setEnabled(false);
        RegisterFrm.getInstance().setVisible(false);
        RegisterFrm.getInstance().setEnabled(false);
        mainWindow.setVisible(true);
        adminUserEditFrm.Load();
    }

    /**
     * 尝试向服务端请求所有的用户信息，并返回到管理员客户端
     * @return boolean类型的返回值，当成功获取到用户信息返回true，否则返回false
     */

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
        adminMainFrm.disWaitMode();
        return false;
    }

    /**
     * 用来向服务端发送修改用户信息的消息
     * @param userName 用户的姓名
     * @param id 用户的学工号
     * @param email 用户的邮箱
     * @param password 用户的密码
     * @param downloadCnt 用户的下载数量
     * @param date 用户最近登录的时间
     * @return boolean类型的返回值，如果服务端成功修改，返回true，否则返回false
     */
    public static synchronized boolean Change(String userName,String id, String email, String password, int downloadCnt, String date){
        adminMainFrm.enWaitMode();
        System.out.println("AdminMainCtrl : adminUserEdit reset downloadCnt request");
        myClient = Client.getMyClient();
        if(myClient == null) {
            adminMainFrm.disWaitMode();
            return false;
        }
        int ret = myClient.sendMsg(AdminUserEditMsg.createAdminUserEditMsg(AdminUserEditMsg.CHANGE,userName,id,email,password,downloadCnt,date));

        if(ret == Client.SUCCESS){
            adminMainFrm.disWaitMode();
        } else {
            adminMainFrm.disWaitMode();
            return false;
        }

        BaseMsg retMsg = myClient.waitMsg();
        if(retMsg.getMsgCode() == - BaseMsg.ADMIN_USER_EDIT) {
            adminMainFrm.disWaitMode();
            return true;
        }
        adminMainFrm.disWaitMode();
        return false;
    }

    /**
     * 用来向服务端发送删除用户信息的消息
     * @param userName 用户的姓名
     * @param id 用户的学工号
     * @param email 用户的邮箱
     * @param password 用户的密码
     * @param downloadCnt 用户的下载数量
     * @param date 用户最近登录的时间
     * @return boolean类型的返回值，如果服务端成功删除，返回true，否则返回false
     */
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
        } else {
            adminMainFrm.disWaitMode();
            return false;
        }
        BaseMsg retMsg = myClient.waitMsg();
        if(retMsg.getMsgCode() == - BaseMsg.ADMIN_USER_EDIT) {
            adminMainFrm.disWaitMode();
            return true;
        }
        adminMainFrm.disWaitMode();
        return false;
    }


}
