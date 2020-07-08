//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package App;

import Utility.Console;
import Utility.CreateTicket;
import Utility.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Map.Entry;

public class ClientReceiver {
    public static Socket socket;
    private static BufferedReader in;
    private static ByteBuffer buffer = ByteBuffer.allocate(10000);

    public ClientReceiver() {
    }

    public static Object receive() throws IOException, ClassNotFoundException, SocketTimeoutException, InterruptedException, SocketException, ScriptException, ScriptException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object obj = objectInputStream.readObject();
        CreateTicket createTicket = new CreateTicket();
        Map<Object, Integer> answerMap = (Map)obj;
        obj = ((Entry)answerMap.entrySet().iterator().next()).getKey();
        int a = (Integer)((Entry)answerMap.entrySet().iterator().next()).getValue();
        if (a == 0) {
            return obj;
        } else {
            if (a == 1) {
                System.out.println("Необходимо заполнить доп.данные для выполнения команды");
                ClientSender.send(createTicket.create());
                obj = receive();
            } else if (a == 2) {
                System.out.println("Необходим ответ от вас: " + obj);
                String answer = Console.read();
                ClientSender.send(answer);
                obj = receive();
            } else if (a == 3) {
                System.out.println("На сервере нет подключения к базе данных,работа невозможна.");
                socket.close();
                System.exit(0);
            }

            return obj;
        }
    }
}
