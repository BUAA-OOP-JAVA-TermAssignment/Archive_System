package client;

import message.BaseMsg;
import message.LoginReturnMsg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * @author pcpas
 */
public class Client {
    public static final int SUCCESS = 0;
    public static final int DISCONNECT = -1;
    public static final int EXCEPTION = -2;

    private static Client client;

    private Client() {
    }

    /**
     * 单例获取MyClient对象
     */
    public static Client getMyClient() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    /**
     * 是否处于连接状态
     */
    private boolean isConnected = false;
    /**
     * 客户端的套接字
     */
    private Socket socket = null;

    /**
     * 连接到服务器
     *
     * @return true 成功 false 失败
     */
    public boolean connect() {
        try {
            socket = new Socket("127.0.0.1", 8888);
            System.out.println("!!!服务器连接成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("！！！连接服务器失败");
            return false;
        }
        this.isConnected = true;
        new ReceiveServerThread(socket).start();
        return true;
    }

    /**
     * 关闭客户端连接
     *
     * @return
     */
    public boolean disConnect() {
        if (socket == null) {
            return true;
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("!!!关闭连接失败");
            return false;
        }
        isConnected = false;
        return true;
    }

    /**
     * 不断接受报文类
     */
    class ReceiveServerThread extends Thread {
        private Socket client;

        public ReceiveServerThread(Socket client) {
            super();
            this.client = client;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    BaseMsg msg = (BaseMsg) ois.readObject();
                    //TODO:接收到msg之后
//                    if (msg instanceof SuccessMsg) {
//                        System.out.println("TODO:登陆成功！");
//                    } else {
//                        System.out.println("TODO:登录失败！");
//                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }


    /**
     * 供Controller调用，向服务器发送msg类的方法
     *
     * @param msg
     */
    public int sendMsg(BaseMsg msg) {
        // TODO:test
        System.out.println("Client : send message");
        return SUCCESS;

        /*
        if (!isConnected) {
            return DISCONNECT;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(msg);
            System.out.println("！！！发送报文成功");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("!!!发送报文错误");
            return EXCEPTION;
        }

        return SUCCESS;

         */
    }

    public BaseMsg waitMsg() {
        System.out.println("Client : wait message");
        // TODO:阻塞等待主机返回的消息，不判断返回消息类型，交由调用的方法处理，超时自动返回一个超时的消息


        // TODO:test
        //return new BaseMsg(BaseMsg.TIME_OUT);
        return LoginReturnMsg.createLoginReturnMsg("菜菜", "20374249", "20374249@buaa.edu.cn", "123456789", 5, new Date());
    }
}
