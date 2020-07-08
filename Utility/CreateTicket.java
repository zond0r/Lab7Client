//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utility;

import Ticket.Coordinates;
import Ticket.Event;
import Ticket.EventType;
import Ticket.Ticket;
import Ticket.TicketType;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateTicket {
    private final Scanner scanner;
    public static boolean isInScript = false;
    public static String[] params;

    public CreateTicket() {
        this.scanner = new Scanner(System.in);
    }

    public static void setParams(String[] params) {
        CreateTicket.params = params;
    }

    public Ticket create() throws ScriptException {
        if (!isInScript) {
            Ticket ticket = new Ticket();
            ticket.setId(0L);
            this.setNameForTicket(ticket);
            Coordinates coordinates = new Coordinates();
            this.setCoordinateXForCoordinates(coordinates);
            this.setCoordinateYForCoordinates(coordinates);
            ticket.setCoordinates(coordinates);
            this.setPriceForTicket(ticket);
            this.setTicketTypeForTicket(ticket);
            Event event = new Event();
            this.setNameForEvent(event);
            this.setDateForEvent(event);
            this.setEventTypeForEvent(event);
            ticket.setEvent(event);
            ticket.setCreationDate(Timestamp.from(Instant.now()));
            return ticket;
        } else {
            return this.createFromScript();
        }
    }

    public Ticket createFromScript() throws ScriptException {
        try {
            Ticket ticket = new Ticket();
            ticket.setId(0L);
            this.setNameForTicket(ticket, params[0]);
            Coordinates coordinates = new Coordinates();
            this.setCoordinateXForCoordinates(coordinates, params[1]);
            this.setCoordinateYForCoordinates(coordinates, params[2]);
            ticket.setCoordinates(coordinates);
            this.setPriceForTicket(ticket, params[3]);
            this.setTicketTypeForTicket(ticket, params[4]);
            Event event = new Event();
            this.setNameForEvent(event, params[5]);
            this.setDateForEvent(event, params[6]);
            this.setEventTypeForEvent(event, params[7]);
            ticket.setEvent(event);
            ticket.setCreationDate(Timestamp.from(Instant.now()));
            return ticket;
        } catch (ScriptException var4) {
            System.out.println("Неверно указаны данные для: " + var4.getWrong());
            return null;
        }
    }

    public void setNameForTicket(Ticket ticket) {
        System.out.println("Введите имя билета.");
        System.out.print("$ ");
        String name = this.scanner.nextLine();
        if (!name.equals("") && !name.equals("null")) {
            ticket.setName(name);
        } else {
            this.setNameForTicket(ticket);
        }

    }

    public void setCoordinateXForCoordinates(Coordinates coords) {
        try {
            System.out.println("Введите координату x");
            System.out.print("$ ");
            String x = this.scanner.nextLine();
            int xn = Integer.parseInt(x);
            if (!x.equals("") && !x.equals((Object)null)) {
                coords.setX(xn);
            } else {
                coords.setX(0);
            }
        } catch (NumberFormatException | InputMismatchException var4) {
            System.out.println("Значение должно быть типа:\"int\". Введите значение заново");
            this.setCoordinateXForCoordinates(coords);
        }

    }

    public void setCoordinateYForCoordinates(Coordinates coords) {
        try {
            System.out.println("Введите координату y");
            System.out.print("$ ");
            String y = this.scanner.nextLine();
            Integer yn = Integer.parseInt(y);
            if (!y.equals("") && !y.equals((Object)null) && yn <= 483) {
                coords.setY(yn);
            } else {
                System.out.println("Координата должна быть меньше 483");
                this.setCoordinateYForCoordinates(coords);
            }
        } catch (NumberFormatException | InputMismatchException var4) {
            System.out.println("Значение должно быть типа:\"int\". Введите значение заново.");
            this.setCoordinateYForCoordinates(coords);
        }

    }

    public void setPriceForTicket(Ticket ticket) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите цену билета");
            System.out.print("$ ");
            String price = scanner.nextLine();
            double pricen = Double.parseDouble(price);
            if (!price.equals("") && !price.equals((Object)null) && pricen >= 0.0D) {
                ticket.setPrice(pricen);
            } else {
                System.out.println("Значение не может быть меньше нуля, попробуйте ещё раз.");
                this.setPriceForTicket(ticket);
            }
        } catch (NumberFormatException | InputMismatchException var6) {
            System.out.println("Значение должно быть типа:\"double\". Введите значение заново.");
            this.setPriceForTicket(ticket);
        }

    }

    public void setTicketTypeForTicket(Ticket ticket) {
        try {
            System.out.println("Введите тип билета(VIP,USUAL,BUDGETARY,CHEAP)");
            System.out.print("$ ");
            String ticketType = this.scanner.nextLine().toUpperCase();
            if (!ticketType.equals("") && !ticketType.equals((Object)null)) {
                if (!ticketType.equals("VIP") && !ticketType.equals("USUAL") && !ticketType.equals("BUDGETARY") && !ticketType.equals("CHEAP")) {
                    this.setTicketTypeForTicket(ticket);
                } else {
                    ticket.setType(TicketType.valueOf(ticketType));
                }
            } else {
                this.setTicketTypeForTicket(ticket);
            }
        } catch (InputMismatchException var3) {
            this.setTicketTypeForTicket(ticket);
        }

    }

    public void setNameForEvent(Event event, String name) throws ScriptException {
        if (!name.equals("") && !name.equals("null")) {
            event.setName(name);
        } else {
            throw new ScriptException("nameEvent");
        }
    }

    public void setDateForEvent(Event event, String date) throws ScriptException {
        try {
            LocalDate localDate = LocalDate.parse(date);
            event.setDate(Timestamp.valueOf(localDate.atStartOfDay()));
        } catch (Exception var4) {
            System.out.println("Такого времени не может быть");
            throw new ScriptException("date");
        }
    }

    public void setEventTypeForEvent(Event event, String eventType) throws ScriptException {
        try {
            eventType = eventType.toUpperCase();
            if (!eventType.equals("") && !eventType.equals((Object)null)) {
                if (!eventType.equals("E_SPORTS") && !eventType.equals("FOOTBALL") && !eventType.equals("BASKETBALL") && !eventType.equals("EXPOSITION")) {
                    throw new ScriptException("eventType");
                } else {
                    event.setEventType(EventType.valueOf(eventType));
                }
            } else {
                throw new ScriptException("eventType");
            }
        } catch (InputMismatchException var4) {
            throw new ScriptException("eventType");
        }
    }

    public void setNameForTicket(Ticket ticket, String name) throws ScriptException {
        if (!name.equals("") && !name.equals("null")) {
            ticket.setName(name);
        } else {
            throw new ScriptException("nameTicket");
        }
    }

    public void setCoordinateXForCoordinates(Coordinates coords, String x) throws ScriptException {
        try {
            int xn = Integer.parseInt(x);
            if (!x.equals("") && !x.equals((Object)null)) {
                coords.setX(xn);
            } else {
                coords.setX((Integer)null);
            }

        } catch (NumberFormatException | InputMismatchException var4) {
            throw new ScriptException("coordinate x");
        }
    }

    public void setCoordinateYForCoordinates(Coordinates coords, String y) throws ScriptException {
        try {
            Integer yn = Integer.parseInt(y);
            if (!y.equals("") && !y.equals((Object)null) && yn <= 483) {
                coords.setY(yn);
            } else {
                throw new ScriptException("coordinate y (значение должно быть больше 483");
            }
        } catch (NumberFormatException | InputMismatchException var4) {
            throw new ScriptException("coordinate y");
        }
    }

    public void setPriceForTicket(Ticket ticket, String price) throws ScriptException {
        try {
            double pricen = Double.parseDouble(price);
            if (!price.equals("") && !price.equals((Object)null) && pricen >= 0.0D) {
                ticket.setPrice(pricen);
            } else {
                throw new ScriptException("price");
            }
        } catch (NumberFormatException | InputMismatchException var5) {
            throw new ScriptException("price");
        }
    }

    public void setTicketTypeForTicket(Ticket ticket, String ticketType) throws ScriptException {
        try {
            ticketType = ticketType.toUpperCase();
            if (!ticketType.equals("") && !ticketType.equals((Object)null)) {
                if (!ticketType.equals("VIP") && !ticketType.equals("USUAL") && !ticketType.equals("BUDGETARY") && !ticketType.equals("CHEAP")) {
                    throw new ScriptException("ticketType");
                }

                ticket.setType(TicketType.valueOf(ticketType));
            } else {
                this.setTicketTypeForTicket(ticket);
            }

        } catch (InputMismatchException var4) {
            throw new ScriptException("ticketType");
        }
    }

    public void setNameForEvent(Event event) {
        System.out.println("Введите имя мероприятие.");
        System.out.print("$ ");
        String name = this.scanner.nextLine();
        if (!name.equals("") && !name.equals("null")) {
            event.setName(name);
        } else {
            this.setNameForEvent(event);
        }

    }

    public void setDateForEvent(Event event) {
        System.out.println("Введите время мероприятие в представленном формате: yyyy-mm-dd");
        System.out.print("$ ");
        String date = this.scanner.nextLine();

        try {
            LocalDate localDate = LocalDate.parse(date);
            event.setDate(Timestamp.valueOf(localDate.atStartOfDay()));
        } catch (Exception var4) {
            System.out.println("Такого времени не может быть");
            this.setDateForEvent(event);
        }

    }

    public void setEventTypeForEvent(Event event) {
        try {
            System.out.println("Введите тип мероприятия(E_SPORTS, FOOTBALL, BASKETBALL, EXPOSITION)");
            System.out.print("$ ");
            String eventType = this.scanner.nextLine().toUpperCase();
            if (!eventType.equals("") && !eventType.equals((Object)null)) {
                if (!eventType.equals("E_SPORTS") && !eventType.equals("FOOTBALL") && !eventType.equals("BASKETBALL") && !eventType.equals("EXPOSITION")) {
                    this.setEventTypeForEvent(event);
                } else {
                    event.setEventType(EventType.valueOf(eventType));
                }
            } else {
                this.setEventTypeForEvent(event);
            }
        } catch (InputMismatchException var3) {
            this.setEventTypeForEvent(event);
        }

    }
}
