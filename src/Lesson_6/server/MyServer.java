package Lesson_6.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MyServer {

    private Vector<ClientHandler> clients;

    public MyServer () {
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Мой сервер запущен.");
            clients = new Vector<>();

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился.");
                new ClientHandler(this, socket);
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
           AuthService.disconnect();
        }
    }

    public void subscribe (ClientHandler client) { clients.add(client);}

    public void unsubscribe (ClientHandler client) { clients.remove(client);}

    public void broadcastMsg (String msg) {
        for (ClientHandler a : clients) {
            a.sendMsg(msg);
        }
    }
//  отправка сообщения конкретному клиенту

    public void toNickMsg (String msg, String nick) {
        for (ClientHandler a : clients) {
            if (a.getNick().equals(nick)) {
                a.sendMsg(msg);
            }
        }
    }

//  проверка подключен ли client с таким ником

    public boolean nickIsConnect (String nick) {

        for (ClientHandler a : clients) {
            if (a.getNick().equals(nick)){
                return true;
            }
        }
        return false;
    }
}
