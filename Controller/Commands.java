package Controller;


import Utility.ScriptException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Commands {
    private static Map<String, Commandable> commands = new HashMap<>();
    private static ArrayList<String> history = new ArrayList<>();
    public static Boolean timeTostop = false;

    public static Commandable getCommand(String commandname) {
        return commands.get(commandname);
    }

    public void regist(Commandable... commands) {
        for (Commandable command : commands)
            Commands.commands.put(command.getName(), command);
    }

    public Map<Commandable, String> executeCommand(String commandName) throws IOException, InterruptedException, ScriptException, ClassNotFoundException {
        String[] nameAndArgument = commandName.split(" ");
        Map<Commandable, String> comandAndParam = new HashMap();
        if (!commandName.equals("")) {
            if (nameAndArgument[0].equals("exit")) {
                comandAndParam.put(commands.get(nameAndArgument[0]), null);
                commands.get(nameAndArgument[0]).execute(null);
                return comandAndParam;
            }
            else if (nameAndArgument[0].equals("execute_script")){
                try {
                    commands.get(nameAndArgument[0]).execute(nameAndArgument[1]);
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                }
                return null;
            }
            else if (nameAndArgument[0].equals("help")) {
                System.out.println(commands.get(nameAndArgument[0]).execute(null));
                return null;
            } else {
                if (commands.get(nameAndArgument[0]) == null) {
                    System.out.println("Такой команды не существует, введите \"help\", чтобы ознакомиться со всем перечнем команд.");
                } else {
                    try {
                        CommandWithoutArg commandWithoutArg = (CommandWithoutArg) commands.get(nameAndArgument[0]);
                        try {
                            String s = nameAndArgument[1];
                            System.out.println("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                        } catch (Exception e) {
                            comandAndParam.put(commands.get(nameAndArgument[0]), null);
                            return comandAndParam;
                        }
                    } catch (Exception e) {
                        try {
                            String s = nameAndArgument[2];
                            System.out.println("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                        } catch (IndexOutOfBoundsException e1) {
                            try {
                                comandAndParam.put(commands.get(nameAndArgument[0]), nameAndArgument[1]);

                                return comandAndParam;
                            } catch (IndexOutOfBoundsException e2) {
                                System.out.println("Неверный формат команды, введите \"help\", чтобы ознакомиться с форматами команд.");
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}