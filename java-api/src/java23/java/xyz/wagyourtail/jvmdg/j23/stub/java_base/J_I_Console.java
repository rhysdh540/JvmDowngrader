package xyz.wagyourtail.jvmdg.j23.stub.java_base;

import xyz.wagyourtail.jvmdg.version.Stub;

import java.io.Console;
import java.io.PrintWriter;
import java.util.Locale;

public class J_I_Console {

    @Stub
    public static Console format(Console console, Locale locale, String format, Object... args) {
        PrintWriter pw = console.writer();
        pw.format(locale, format, args);
        pw.flush();
        return console;
    }

    @Stub
    public static Console printf(Console console, Locale locale, String format, Object... args) {
        PrintWriter pw = console.writer();
        pw.printf(locale, format, args);
        pw.flush();
        return console;
    }

    @Stub
    public static String readLine(Console console, Locale locale, String format, Object... args) {
        PrintWriter pw = console.writer();
        pw.printf(locale, format, args);
        pw.flush();
        return console.readLine();
    }

    @Stub
    public static char[] readPassword(Console console, Locale locale, String format, Object... args) {
        PrintWriter pw = console.writer();
        pw.printf(locale, format, args);
        pw.flush();
        return console.readPassword();
    }

}
