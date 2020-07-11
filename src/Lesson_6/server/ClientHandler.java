package Lesson_6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private MyServer server;
    private String nick;

    public ClientHandler (MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String string = dataInputStream.readUTF();
                            if (string.startsWith("/auth")) {
                                String[] tokens = string.split( " ");
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                if (!server.nickIsConnect(newNick)) {
                                    if (newNick != null) {
                                        sendMsg("/authok");
                                        nick = newNick;
                                        server.subscribe(ClientHandler.this);
                                        break;
                                    } else {
                                        sendMsg("Неверный логин/пароль.");
                                    }
                                } else {
                                    sendMsg("Клиент с таким ником уже подключен.");
                                }
                            }

                        }
                        while (true) {
                            String string = dataInputStream.readUTF();
                            if (string.equalsIgnoreCase("/end")) {
                                dataOutputStream.writeUTF("/serverClosed");
                                break;
                            }

                            if (string.startsWith("/w")) {
                                String[] tokens = string.split( " ");
                                String a = tokens [1];
//            выглядит - не очень ...     =>
                                String b ="";
                                for (int i = 2; i < tokens.length; i++) {
                                    b += tokens[2];
                                }
//            проверка на наличие клиента в сети
                                if (server.nickIsConnect(a)) {
                                    server.toNickMsg((nick + "только Вам: " + b), a);
                                } else {
                                    sendMsg("Клиента с таким ником нет в сети.");
                                }
                            }
                            else {
                                server.broadcastMsg(nick + ": " + string);
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            dataInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            dataOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg (String string) {
        try {
            dataOutputStream.writeUTF(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }
}
