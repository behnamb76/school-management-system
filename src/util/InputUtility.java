package util;

import java.sql.Date;
import java.util.Scanner;

public class InputUtility {
    private static final Scanner sc = new Scanner(System.in);

    public static int getIntInput(String message) {
        System.out.print(message);
        return Integer.parseInt(sc.nextLine());
    }

    public static Long getLongInput(String message) {
        System.out.print(message);
        return Long.parseLong(sc.nextLine());
    }

    public static double getDoubleInput(String message) {
        System.out.print(message);
        return Double.parseDouble(sc.nextLine());
    }

    public static String getStringInput(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    public static Date getDateInput(String message) {
        System.out.print(message);
        String date = sc.nextLine();
        String[] dateParts = date.split("-");
        return new Date(Integer.parseInt(dateParts[0]) - 1900, Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[2]));
    }
}
