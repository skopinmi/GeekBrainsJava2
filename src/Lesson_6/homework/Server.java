package Lesson_6.homework;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {

        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            socket = server.accept();
            System.out.println("Клиент подключился");
            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        out.println(sc.nextLine());
                    }
                }
            }).start();
            while (true) {
                String str = sc1.nextLine();
                if (str.equalsIgnoreCase("/end")) {
                    break;
                }
                System.out.println("Client " + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }   finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

