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
     * ���������˷����޸��û���Ϣ����Ϣ
     * @param userName �û�������
     * @param id �û���ѧ����
     * @param email �û�������
     * @param password �û�������
     * @param downloadCnt �û�����������
     * @param date �û������¼��ʱ��
     * @return boolean���͵ķ���ֵ���������˳ɹ��޸ģ�����true�����򷵻�false
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
