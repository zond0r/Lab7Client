//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package App;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

public class ClientSender {
    public static Boolean serverisconnected = false;

    public ClientSender() {
    }

    public static void send(Object o) throws SocketException, ClassNotFoundException, InterruptedException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ClientReceiver.socket.getOutputStream());
            objectOutputStream.writeObject(o);
        } catch (IOException var2) {
            serverisconnected = false;
            tryToConnect();
        }

    }

    public static void tryToConnect() throws InterruptedException, ClassNotFoundException {
        while(!serverisconnected) {
            try {
                Socket socket = new Socket("localhost", 5018);
                socket.setSoTimeout(3000);
                serverisconnected = true;
                System.out.println("Подключился к серверу");
                ClientReceiver.socket = socket;
                socket.setSoTimeout(0);
            } catch (ConnectException var1) {
                System.out.println("Сервер отключен или недоступен,попробуйте позже.");
                Thread.sleep(2000L);
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

    }
}
