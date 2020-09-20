package swingy.utils;

public class Log {
    public static void log(String message) {
        if (Globals.CONSOLE_MODE == true) {
            System.out.println(message);
        }
    }
    public static void inputSign() {
        System.out.format("$ ");
    }
}
