import client.Client;
import controller.ClientCtrl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HelloWorld {
    public static void main(String[] args) throws IOException {
        Client.getMyClient().connect();
        ClientCtrl.getClientCtrl().userLogin("zzq", "123456");
        while (true){}
    }
}
