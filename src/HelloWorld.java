import client.Client;
import controller.ClientCtrl;
import controller.DownloadCtrl;
import controller.SearchCtrl;
import data.BookData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) throws IOException {
        testSearch();
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

    static void testSearch() {
        Client.getMyClient().connect();
        List<BookData> list = SearchCtrl.getInstance().search("µçÂ·", 0, 3);
        if (list == null) {
            System.out.println("ËÑË÷´íÎó");
            return;
        }
        for (BookData bd : list) {
            System.out.println(bd.getId());
            System.out.println(bd.getName());
            System.out.println(bd.getAuthor());
            System.out.println(bd.getMatchedText());
            System.out.println(bd.getDownloadCnt());
            System.out.println(bd.getScore());
            System.out.println("-----------------------------------");
        }
    }

}
