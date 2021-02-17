package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    private Vector<sample.server.ClientHandler> clients;

    public Server() throws SQLException {
        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();

        try {
            sample.server.AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new sample.server.ClientHandler(this,socket);
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
            sample.server.AuthService.disconnect();
        }
    }

    public void subscribe(sample.server.ClientHandler client) {
        clients.add(client);
        broadcastClientList();
    }

    public void unsubscribe(sample.server.ClientHandler client) {
        clients.remove(client);
        broadcastClientList();
    }

    public void broadcastMsg (String msg) {
        for (sample.server.ClientHandler a : clients) {
            a.sendMsg(msg);
        }
    }
// доработка - блокировка сообщений клиентов из черного списка

    public void broadcastMsg (String msg, String nick) {
        for (sample.server.ClientHandler a : clients) {
            if (!a.isBlack(nick)) {
                a.sendMsg(nick + ": " + msg);
            }
        }
    }
//  отправка сообщения конкретному клиенту

    public void toNickMsg (String msg, String nick) {
        for (sample.server.ClientHandler a : clients) {
            if (a.getNick().equals(nick)) {
                a.sendMsg(msg);
            }
        }
    }

//  проверка подключен ли client с таким ником

    public boolean nickIsConnect (String nick) {

        for (sample.server.ClientHandler a : clients) {
            if (a.getNick().equals(nick)){
                return true;
            }
        }
        return false;
    }

    public void broadcastClientList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientlist ");
        for (sample.server.ClientHandler o: clients) {
            sb.append(o.getNick() + " ");
        }
        String out = sb.toString();

        for (sample.server.ClientHandler o: clients) {
            o.sendMsg(out);
        }
    }
}
