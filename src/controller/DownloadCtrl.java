package controller;

import client.Client;
import message.BaseMsg;
import message.DownloadRequestMsg;

import java.io.InputStream;

/**
 * @author pcpas
 */
public class DownloadCtrl {

    /**
     * 通过指定文件的ID来下载文件
     * @param docId 文件ID
     * @param downloadPath 保存文件位置
     * @param fileName 文件命名
     * @return
     */
    public static boolean downloadFile(String docId, String downloadPath, String fileName) {
        Client client = Client.getMyClient();
        //发送信息，此时应确保处于可以接受文件的状态（即此时不可以同时也在等待另一个报文！）
        client.sendMsg(new DownloadRequestMsg(BaseMsg.DOWNLOAD_FILE_REQUEST, docId));
        //下载文件
        if (!client.downloadFile(downloadPath, fileName)) {
            return false;
        }
        return true;
    }

}
