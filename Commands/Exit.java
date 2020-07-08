package Commands;

import Controller.CommandWithoutArg;

public class Exit implements CommandWithoutArg {
    @Override
    public Object execute(Object object) {
        System.exit(0);
        return null;
    }

    @Override
    public String getName() {
        return "exit";
    }


}
