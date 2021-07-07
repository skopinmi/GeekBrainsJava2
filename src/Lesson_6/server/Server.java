package Lesson_6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    private Vector<ClientHandler> clients;

    public Server() {
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");
            clients = new Vector<>();

// добавил поток проверяющий и удаляющий отключенные клиенты из коллекции

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        deleteClients();
                    }
                }
            }).start();
            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                clients.add(new ClientHandler(this, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }
// пробегает по коллекции с клиентами удаляет с закрытым socket
    public void deleteClients () {
        Iterator <ClientHandler> iter = clients.iterator();
        while (iter.hasNext()) {
             if(iter.next().isClosed()) {
                 iter.remove();
                 System.out.println("Client was delete");
            }
        }
    }
}
