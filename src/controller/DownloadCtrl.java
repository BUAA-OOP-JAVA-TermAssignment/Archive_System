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
     * ͨ��ָ���ļ���ID�������ļ�
     * @param docId �ļ�ID
     * @param downloadPath �����ļ�λ��
     * @param fileName �ļ�����
     * @return
     */
    public static boolean downloadFile(String docId, String downloadPath, String fileName) {
        Client client = Client.getMyClient();
        //������Ϣ����ʱӦȷ�����ڿ��Խ����ļ���״̬������ʱ������ͬʱҲ�ڵȴ���һ�����ģ���
        client.sendMsg(new DownloadRequestMsg(BaseMsg.DOWNLOAD_FILE_REQUEST, docId));
        //�����ļ�
        if (!client.downloadFile(downloadPath, fileName)) {
            return false;
        }
        return true;
    }

}
