package controller;

import client.Client;
import data.AdminData;
import message.AdminUserEditMsg;
import message.AdminUserRequestMsg;
import message.BaseMsg;
import message.ModifyUserInfo;
import view.GuestMainFrm;
import view.UserCentreFrm;

public class UserCentreCtrl {
    private static Client myClient = Client.getMyClient();
    private static final GuestMainFrm guestMainFrm = GuestMainFrm.getInstance();
    private static final UserCentreFrm userCentreFrm = UserCentreFrm.getInstance();
    public static synchronized boolean Change(String userName,String id, String email, String password, int downloadCnt,String date){
        userCentreFrm.enWaitMode();
        System.out.println("UserCentreCtrl : UserCentreCtrl change request");
        if(myClient == null) {
            userCentreFrm.disWaitMode();
            return false;
        }
        int ret = myClient.sendMsg(AdminUserEditMsg.createAdminUserEditMsg(AdminUserEditMsg.MODIFY_USER_INFO,userName,id,email,password,downloadCnt,date));

        if(ret == Client.SUCCESS){
            userCentreFrm.disWaitMode();
            return true;
        } else {
            userCentreFrm.disWaitMode();
            return false;
        }
    }
}
