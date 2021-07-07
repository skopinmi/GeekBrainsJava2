package Lesson_6.homework;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ClientStart {
    public static void main(String[] args) {
        Socket socket;
        Scanner in;
        Scanner sc;

        final String IP_ADRESS = "localhost";
        final int PORT = 8189;

        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new Scanner(socket.getInputStream());
            sc = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        if (sc.hasNextLine()){
                            out.println(sc.nextLine());
                        }
                    }
                }
            }).start();
            try {
                while (true) {
                    String str = in.nextLine();
                    if (str.equalsIgnoreCase("/end")) {
                        break;
                    }
                    System.out.println("Server " + str);
                }
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
