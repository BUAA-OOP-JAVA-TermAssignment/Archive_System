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
    public static synchronized boolean Change(String userName,String id, String email, String password, int downloadCnt,String date){
        userCentreFrm.enWaitMode();
        System.out.println("UserCentreCtrl : UserCentreCtrl change request");
        if(myClient == null) {
            userCentreFrm.disWaitMode();
            return false;
        }
        int ret = myClient.sendMsg(ModifyUserInfo.createModifyUserInfo(id,email,password,downloadCnt));

        if(ret == Client.SUCCESS){
            userCentreFrm.disWaitMode();
        } else {
            userCentreFrm.disWaitMode();
            return false;
        }
        BaseMsg retMsg = myClient.waitMsg();
        if(retMsg.getMsgCode() == - BaseMsg.MODIFY_USER_INFO) {
            userCentreFrm.disWaitMode();
            return true;
        }
        userCentreFrm.disWaitMode();
        return false;
    }
}
