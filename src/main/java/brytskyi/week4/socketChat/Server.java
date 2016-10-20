package brytskyi.week4.socketChat;


import sun.plugin2.util.PojoUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// TODO: 17.10.16 WRITE Comments!!!
public class Server {

    private ServerSocket serverSocket;
    private List<SocketWrapper> clients;
    private int port;

    public Server(int port) {
        this.port = port;
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clients = new ArrayList<>();
        Thread registerTh = new Thread(new ClientsRegister());
        registerTh.start();
        while (true) {
        }
    }

    private void newClientCame(SocketWrapper client) throws IOException {
        String info = makeInfo(client.getSocket());
        showInfo(info);
        showGreeting(client);
        startClientLogic(client);
        sendToOther(info, client);
        clients.add(client);
    }

    private void startClientLogic(SocketWrapper client) throws IOException {
        Thread clientThread = new Thread(new ClientLogic(client));
        clientThread.start();
    }

    private void showGreeting(SocketWrapper client) throws IOException {
        String mess = "Hi, you ve connected to " + serverSocket.getInetAddress().getHostAddress() + ":" + port;
        sendMessage(mess, client);
    }

    private void showInfo(String s) {
        System.out.println("New Client was connected\n" + s);
    }

    private String makeInfo(Socket client) {
        String info = String.format("New client connected\nClient host: %s, port: %s\n", client.getInetAddress().getHostAddress(), client.getPort());
        return info;
    }

    private synchronized void sendToOther(Object message, SocketWrapper owner) throws IOException {
        for (SocketWrapper client : clients) {
            if (!client.equals(owner)) {
                sendMessage(message, client);
            }
        }
    }

    private void sendMessage(Object message, SocketWrapper target) throws IOException {
        Thread messageTh = new Thread(new MessageSender(message, target.getOs()));
        messageTh.start();
    }

    private class ClientLogic implements Runnable {

        private SocketWrapper owner;
        private InputStream is;

        public ClientLogic(SocketWrapper owner) throws IOException {
            this.owner = owner;
            is = owner.getIs();
        }

        @Override
        public void run() {
            Object message = null;
            try (ObjectInputStream ois = new ObjectInputStream(this.is)) {
                while (owner.getSocket().isConnected()) {
                    message = ois.readObject();
                    System.out.println("mesage " + message);
                    if (owner.getNameOfUser() == null) {
                        owner.setNameOfUser((String) message);
                    } else
                        sendToOther(owner.getNameOfUser()+": " + message, owner);
                }
                clients.remove(owner);
                sendToOther(owner.getNameOfUser() + " have left :((", owner);
            } catch (IOException e) {
                clients.remove(owner);
                try {
                    sendToOther(owner.getNameOfUser() + " have left :((", owner);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


    private class ClientsRegister implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Socket client = serverSocket.accept();
                    System.out.println("client came");
                    newClientCame(new SocketWrapper(client, null));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class MessageSender implements Runnable {

        private Object message;
        private ObjectOutputStream os;

        public MessageSender(Object message, ObjectOutputStream os) throws IOException {
            this.message = message;
            this.os = os;
        }

        @Override
        public void run() {
            try {
                os.writeObject(message);
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
