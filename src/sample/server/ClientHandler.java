//package server;
//
//import sample.server.AuthService;
//import server.Server;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//import java.util.ArrayList;
//
//public class ClientHandler {
//
//    private Socket socket;
//    private DataInputStream dataInputStream;
//    private DataOutputStream dataOutputStream;
//    private Server server;
//    private String nick;
//    private ArrayList <String> blackList;
//
//    public ClientHandler (Server server, Socket socket) {
//        try {
//            this.server = server;
//            this.socket = socket;
//            this.dataInputStream = new DataInputStream(socket.getInputStream());
//            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        while (true) {
//                            String string = dataInputStream.readUTF();
//                            if (string.startsWith("/auth")) {
//                                String[] tokens = string.split( " ");
//                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
//                                if (!server.nickIsConnect(newNick)) {
//                                    if (newNick != null) {
//                                        sendMsg("/authok");
//                                        nick = newNick;
////                                        000
//                                        blackList = AuthService.getBlackList(nick);
//                                        server.subscribe(ClientHandler.this);
//                                        break;
//                                    } else {
//                                        sendMsg("Неверный логин/пароль.");
//                                    }
//                                } else {
//                                    sendMsg("Клиент с таким ником уже подключен.");
//                                }
//                            }
//
//                        }
//                        while (true) {
//                            String string = dataInputStream.readUTF();
//
//                            if (string.equalsIgnoreCase("/end")) {
//                                dataOutputStream.writeUTF("/serverClosed");
//                                break;
//                            }
//
//                            if (string.startsWith("/w")) {
//                                String[] tokens = string.split( " ", 3);
//                                String a = tokens [1];
//                                String b = tokens [2];
//                                if (server.nickIsConnect(a)) {
//                                    server.toNickMsg((nick + " только Вам: " + b), a);
//                                } else {
//                                    sendMsg("Клиента с таким ником нет в сети.");
//                                }
//                            } else if (string.startsWith("/blacklist")) {
//                                String[] tokens = string.split(" ", 2);
//                                AuthService.addToBlackList(nick, tokens[1]);
//                                sendMsg(tokens [1] + " добавлен в черный список.");
//                                blackList = AuthService.getBlackList(nick);
//                            } else if (string.startsWith("/deleteblacklist")) {
//                                String[] tokens = string.split(" ", 2);
//                                AuthService.deleteBlackList(nick, tokens[1]);
//                                sendMsg(tokens [1] + " удален из черного списока.");
//                                blackList = AuthService.getBlackList(nick);
//                            } else {
//                                server.broadcastMsg(string, nick);
//                            }
//                        }
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        try {
//                            dataInputStream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            dataOutputStream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            socket.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        server.unsubscribe(ClientHandler.this);
//                    }
//                }
//            }).start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void sendMsg (String string) {
//        try {
//            dataOutputStream.writeUTF(string);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getNick() {
//        return nick;
//    }
//
//    public ArrayList<String> getBlackList () {
//        return blackList;
//    }
//
//    public boolean isBlack (String nick) {
//        return blackList.contains(nick);
//    }
//}
