package controller;

import client.Client;
import message.UserLoginMsg;

/**
 * 所有需要和客户端通信的都由此控制
 * 所有数据以报文（msg）形式传输
 */
public class ClientCtrl {

    private static ClientCtrl clientCtrl;

    private ClientCtrl() {
    }

    /**
     * 单例获取服务器对象
     *
     * @return
     */
    public static ClientCtrl getClientCtrl() {
        if (clientCtrl == null) {
            clientCtrl = new ClientCtrl();
        }
        return clientCtrl;
    }

    Client client = Client.getMyClient();

    /**
     * 用户登录
     *
     * @param username 账号
     * @param password 密码
     * @return true 成功 false 失败
     */
    public boolean userLogin(String username, String password) {
        client.sendMsg(new UserLoginMsg(username, password));
        //TODO:怎么获取返回信息？

        return true;
    }

}
