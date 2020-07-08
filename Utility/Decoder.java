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
import java.util.HashMap;

public class Decoder {
    public Decoder() {
    }

    public static HashMap<Long, Ticket> decodeIntoCollection(String data) {
        HashMap collection = new HashMap();

        try {
            String[] lines = data.split("\n");
            String[] var3 = lines;
            int var4 = lines.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String line = var3[var5];
                Ticket ticket = new Ticket();
                String[] params = line.split(",");
                ticket.setId(Long.parseLong(params[0]));
                ticket.setName(params[1]);
                Coordinates coordinates = new Coordinates();
                coordinates.setX(Integer.parseInt(params[2]));
                coordinates.setY(Integer.parseInt(params[3]));
                ticket.setCoordinates(coordinates);
                ticket.setCreationDate(Timestamp.valueOf(LocalDate.parse(params[4]).atStartOfDay()));
                ticket.setPrice(Double.parseDouble(params[5]));
                ticket.setType(TicketType.valueOf(params[6]));
                Event event = new Event();
                event.setName(params[7]);
                event.setEventType(EventType.valueOf(params[8]));
                event.setDate(Timestamp.valueOf(LocalDate.parse(params[9]).atStartOfDay()));
                ticket.setCreationDate(Timestamp.from(Instant.now()));
                ticket.setEvent(event);
                collection.put(Long.parseLong(params[0]), ticket);
            }

            System.out.println("Коллекция успешно заполнена.");
            return collection;
        } catch (Exception var11) {
            if (FileRead.getFilename().equals("")) {
                System.out.println("Изначальный файл не указан.");
            } else {
                System.out.println("В указанном файле некорректные данные.");
            }

            System.out.println("Коллекция пустая.");
            return collection;
        }
    }
}
