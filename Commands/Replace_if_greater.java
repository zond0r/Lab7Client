package Commands;

import Controller.CommandWithObject;
import Ticket.Ticket;

public class Replace_if_greater implements CommandWithObject {

    @Override
    public Object execute(Object object) {
        return null;
    }

    @Override
    public String getName() {
        return "replace_if_greater";
    }


    @Override
    public boolean check(Object arg) {
        return false;
    }

    @Override
    public Ticket getNewTicket() {
        return null;
    }
}
