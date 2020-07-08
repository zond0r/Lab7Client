//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import App.ClientReceiver;
import App.ClientSender;
import Commands.Clear;
import Commands.Execute_script;
import Commands.Filter_starts_with_name;
import Commands.Help;
import Commands.Info;
import Commands.Insert;
import Commands.Print_ascending;
import Commands.Print_field_descending_type;
import Commands.Remove_key;
import Commands.Remove_lower_key;
import Commands.Replace_if_greater;
import Commands.Replace_if_lower;
import Commands.Show;
import Commands.Update;
import Controller.Commandable;
import Controller.Commands;
import Utility.Console;
import Utility.ScriptException;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.Map;

public class main {
    public main() {
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, ScriptException {
        Commands commands = new Commands();
        commands.regist(new Commandable[]{new Print_field_descending_type(), new Clear(), new Filter_starts_with_name(), new Show(), new Replace_if_lower(), new Update(), new Remove_lower_key(), new Help(), new Info(), new Print_ascending(), new Execute_script(), new Insert(), new Remove_key(), new Replace_if_greater()});
        ClientSender.tryToConnect();
        System.out.println(ClientReceiver.receive());

        while(true) {
            String commandName;
            do {
                System.out.println("Введите команду,для справки введите help.");
                commandName = Console.read();
            } while(commandName.equals(""));

            try {
                Map<Commandable, String> map = commands.executeCommand(commandName);
                if (map != null) {
                    ClientSender.send(map);
                    System.out.println(ClientReceiver.receive());
                }
            } catch (SocketTimeoutException var4) {
                System.out.println("Возможно сервер занят другим пользователем,подождите пожалуйста и попробуйте снова.");
            } catch (SocketException var5) {
                System.out.println("Сервер отключился,попробую подсоединиться заново.");
                ClientSender.tryToConnect();
                ClientReceiver.receive();
            } catch (SQLException var6) {
                var6.printStackTrace();
            }
        }
    }
}
