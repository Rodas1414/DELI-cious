package Delicious.util;

import Delicious.mode1.Order;
import java.io.*;
import java.time.format.DateTimeFormatter;

public class ReceiptGenerator {
    public static void generate(Order order) {
        try {
            File folder = new File("receipts");
            if (!folder.exists()) folder.mkdirs();

            String filename = "receipts/" + order.getTimestamp().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".txt";
            FileWriter writer = new FileWriter(filename);
            writer.write(order.toString());
            writer.close();
            System.out.println("Receipt saved to: " + filename);
        } catch (IOException e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
}
