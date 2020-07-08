//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utility;

import java.util.Scanner;

public class Console {
    static Scanner in;

    public Console() {
    }

    public static String read() {
        System.out.print("~ ");
        return in.nextLine();
    }

    static {
        in = new Scanner(System.in);
    }
}
