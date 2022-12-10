import client.Client;
import controller.ClientCtrl;
import controller.DownloadCtrl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HelloWorld {
    public static void main(String[] args) throws IOException {
        testDownload();
    }

    void testlogin() {
        Client.getMyClient().connect();
        ClientCtrl.getClientCtrl().userLogin("zzq", "123456");
        while (true) {
        }
    }

    static void testDownload() {
        Client.getMyClient().connect();
        DownloadCtrl.downloadFile("EX_0001", "D:\\Archive_System\\download", "file1");
    }

}
