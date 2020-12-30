package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void insert(long number, long pin) {
        String sql = "INSERT INTO card(number, pin) VALUES(?,?)";

        String url = "jdbc:sqlite:.//card.s3db";

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, number);
            pstmt.setLong(2, pin);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) throws SQLException {

        long[] array = new long[2];
        long almostFullCard;
        String almostFullCardString;
        long pin = 0;
        String numberDone = null;
        long numberDone2 = 0;

        String url = "jdbc:sqlite:.//card.s3db";

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS card (\n"
                    + "     id INTEGER PRIMARY KEY,\n" // autimaticly create new id
                    + "     number TEXT,\n"                       // don't write VARCHAR its mistake!
                    + "     pin TEXT,\n"
                    + "     balance INTEGER DEFAULT 0"
                    + ");";
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        while(true) {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");

            int customerFirstInput = scanner.nextInt();

            if(customerFirstInput == 0){
                break;
            }

            if (customerFirstInput == 3) {
                System.out.println("You have successfully logged out!");
            }

            if (customerFirstInput == 1) {
                long accountId = (long) (Math.random() * 1000000000L);//account id is digit before checksum num
                almostFullCard = 400000000000000L + accountId; //adding the 400000 at the beginning

                almostFullCardString = Long.toString(almostFullCard);
                almostFullCardString = almostFullCardString.substring(0,15);
                char[] numArray = almostFullCardString.toCharArray();
                int total = 0;
                int lastDig;
                for (int i = 0; i < numArray.length; i++) {
                    int total1 = Integer.parseInt(Character.toString(numArray[i]));
                    if (i % 2 == 1) {
                        total += total1;
                    } else {
                        int doubled = total1 * 2;
                        if (doubled >= 10) {
                            total += doubled - 9;
                        } else {
                            total += doubled;
                        }
                    }
                }
                lastDig = 10 - (total % 10);
                numberDone = almostFullCardString + lastDig;
                numberDone2 = Long.valueOf(numberDone);

                pin = (long) (Math.random() * 10000L);
                array[0] = pin;
                //array[1] = pin;
                System.out.println("Your card has been created");
                System.out.println("Your card number:");
                System.out.println(numberDone2);
                System.out.println("Your card PIN:");
                System.out.println(array[0]);
            }

            //InsertApp app = new InsertApp();
            // insert three new rows
            Main.insert(numberDone2, pin);

            if (customerFirstInput == 2) {
                System.out.println("Enter your card number:");
                Long inputCard = scanner.nextLong();
                System.out.println("Your card PIN:");
                long inputPin = scanner.nextLong();
                //System.out.println(numberDone);

                if(inputCard == numberDone2 && inputPin == array[0]){
                    System.out.println("You have successfully logged in!");
                    System.out.println("1. Balance");
                    System.out.println("2. Log out");
                    System.out.println("0. Exit");
                    System.out.println(numberDone);
                    int secondInput = scanner.nextInt();

                    if(secondInput == 2){
                        System.out.println("You have successfully logged out!");
                    }

                    if(secondInput == 1){
                        System.out.println(0);
                    }

                    if(secondInput == 0){
                        break;
                    }

                }else{
                    System.out.println("Wrong card number or PIN!");
                }

            }
        }

    }
}