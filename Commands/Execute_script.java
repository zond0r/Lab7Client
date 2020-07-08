package Commands;

import App.ClientReceiver;
import App.ClientSender;
import Controller.CommandWithObject;
import Controller.Commandable;
import Controller.Commands;
import Utility.CreateTicket;
import Utility.FileRead;
import Utility.ScriptException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Execute_script implements Commandable {
    Commands invoker = new Commands();
    HashMap scripts = new HashMap();

    @Override
    public Object execute(Object arg) throws IOException, InterruptedException, ScriptException, ClassNotFoundException {
        scripts.put((String) arg, 1);
        try {
            String data = FileRead.readFromFile((String) arg);
            Commands command = new Commands();
            if (data != null) {
                String[] commands = data.split("\n|\r\n");
                for (int i = 0; i < commands.length; i++) {
                    boolean isValidScript = true;
                    if (!(commands[i].equals(""))) {
                        String[] commandAndArg = commands[i].split(" ");
                        if (commandAndArg[0].equals("execute_script")) {
                            if (scripts.get(commandAndArg[1]) == null)
                                scripts.put(commandAndArg[1], 1);
                            else isValidScript = false;
                        }

                        try {
                            CommandWithObject commandWithObject = (CommandWithObject) Commands.getCommand(commandAndArg[0]);
                            if (commandWithObject != null) {
                                String[] params = new String[8];
                                try {
                                    for (int j = 0; j < 8; j++) {
                                        i++;
                                        params[j] = (commands[i]);
                                    }
                                    CreateTicket.isInScript = true;
                                    CreateTicket.setParams(params);
                                    System.out.println("\nКоманда \"" + commands[i - 8] + "\":");
                                    Map<Commandable, String> map = invoker.executeCommand(commands[i-8]);
                                    if (map != null) {
                                        ClientSender.send(map);
                                        if (Commands.timeTostop) {
                                            System.out.println("Коллекция сохранена.");
                                            System.exit(0);
                                        }
                                        System.out.println(ClientReceiver.receive());
                                        System.out.println();
                                        CreateTicket.isInScript = false;
                                    }
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("Команда \"" + commandAndArg[0] + "\":");
                                    System.out.println("Недостаточно параметров");
                                }
                            }
                        } catch (Exception e) {
                            if (isValidScript) {
                                System.out.println("Команда \"" + commands[i] + "\":");
                                Map<Commandable, String> map = invoker.executeCommand(commands[i]);
                                if (map != null) { ;
                                    ClientSender.send(map);
                                    if (Commands.timeTostop) {
                                        System.out.println("Коллекция сохранена.");
                                        System.exit(0);
                                    }
                                    System.out.println(ClientReceiver.receive());
                                    System.out.println();
                                } else {
                                    System.out.println("Команда \"" + commands[i] + "\": невыполнима.\n");
                                }
                            }
                        }
                    }
                }
            } else System.out.println("Указанный файл не найден.");
        } catch (NullPointerException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scripts.clear();
            return null;
        }
    }

    @Override
    public String getName() {
        return "execute_script";
    }
}
