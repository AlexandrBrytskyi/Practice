package brytskyi.week5._03asynchronusChat.socketChat;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

// TODO: 17.10.16 WRITE Comments!!!
public class Server {

    private ServerSocket serverSocket;
    private List<SocketWrapper> clients;
    private int port;
    private PrintWriter historyWriter;
    private Set<String> bannedHosts;

    public Server() {
        try {
            File file = new File("temp/chat/" + new Date().toString());
            if (!file.exists()) file.createNewFile();
            historyWriter = new PrintWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src/main/resources/chat/server_settings.properties")));
            port = Integer.valueOf(properties.getProperty("port"));
            String bannedStr = properties.getProperty("banned");
            fillSet(bannedStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void fillSet(String bannedStr) {
        if (bannedHosts == null) this.bannedHosts = new HashSet<>();
        if (bannedStr == null) return;
        String[] valuses = bannedStr.split(",");
        if (null != valuses)
            for (String s : valuses) {
                bannedHosts.add(s);
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
        historyWriter.append(owner + ": " + message + "\n");
        historyWriter.flush();
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
                        sendToOther(owner.getNameOfUser() + ": " + message, owner);
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
                    if (bannedHosts.contains(client.getInetAddress().getHostAddress())) {
                        client.close();
                        System.out.println("banned client tryed to come");
                    } else {
                        System.out.println("client came");
                        newClientCame(new SocketWrapper(client, null));
                    }
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
