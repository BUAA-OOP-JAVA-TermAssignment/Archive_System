package client;

import message.BaseMsg;
import message.LoginReturnMsg;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
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

    private boolean isDataChanging = false;

    /**
     * 连接到服务器
     *
     * @return true 成功 false 失败
     */
    public boolean connect() {
        try {
            socket = new Socket("127.0.0.1", 8888);
            //将超时时间设定为10s，若没有返回任何信息则抛出异常
            socket.setSoTimeout(10000);
            System.out.println("Connect Server Success");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Connect Server failed!");
            return false;
        }
        this.isConnected = true;
        return true;
    }

    /**
     * 关闭客户端连接
     *
     * @return
     */
    public boolean disConnect() {
        if (socket == null) {
            return false;
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Close connection failed!");
            return false;
        }
        isConnected = false;
        return true;
    }


    /**
     * 供Controller调用，向服务器发送msg类的方法
     *
     * @param msg
     */
    public int sendMsg(BaseMsg msg) {
        isDataChanging = true;
        // TODO:test
        System.out.println("Client : send message");
        //return SUCCESS;


        if (!isConnected) {
            isDataChanging = false;
            return DISCONNECT;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(msg);
            System.out.println("Send Message Success");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Send Message Failed!");
            isDataChanging = false;
            return EXCEPTION;
        }
        isDataChanging = false;
        return SUCCESS;

    }

    public BaseMsg waitMsg() {
        isDataChanging = true;
        System.out.println("Client : wait message");
        // TODO:阻塞等待主机返回的消息，不判断返回消息类型，交由调用的方法处理，超时自动返回一个超时的消息
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            BaseMsg msg = (BaseMsg) ois.readObject();
            isDataChanging = false;
            return msg;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            isDataChanging = false;
            return new BaseMsg(BaseMsg.UNDEFINED_FAILED);
        } catch (SocketTimeoutException e) {
            isDataChanging = false;
            return new BaseMsg(BaseMsg.TIME_OUT);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lost connection!");
        }
        //return LoginReturnMsg.createLoginReturnMsg("菜菜", "20374249", "20374249@buaa.edu.cn", "123456789", 5, "2022-12-11 01:20:05");
        isDataChanging = false;
        return new BaseMsg(BaseMsg.TIME_OUT);
    }

    public boolean downloadFile(String savePath, String filename) {
        try {
            InputStream inputStream = socket.getInputStream();
            File file = new File(savePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            BufferedOutputStream bos = new BufferedOutputStream((new FileOutputStream(file + "\\" + filename)));
            byte[] data = new byte[1024];
            int len;
            while ((len = inputStream.read(data)) != -1) {
                if (len == 3) {
                    if (data[0] == 'E' && data[1] == 'O' && data[2] == 'F') {
                        break;
                    }
                }
                bos.write(data, 0, len);
            }
            System.out.println("File Download Success");
            bos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isDataChanging() {
        return isDataChanging;
    }
}
