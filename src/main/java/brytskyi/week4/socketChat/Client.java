package brytskyi.week4.socketChat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by alexandr on 17.10.16.
 */
public class Client {

    private String serverhost;
    private int pserverort;
    private SocketWrapper socketWrapper;


    public Client(String host, int port, String name) throws IOException {
        this.serverhost = host;
        this.pserverort = port;
        Socket socket = new Socket(host, port);
        this.socketWrapper = new SocketWrapper(socket, name);
        initInputStreamListener(socketWrapper.getIs());
        initWritingMessagesThread();
        sendMessage(name);
        System.out.println("inited writing");
    }

    private void initWritingMessagesThread() {
        Thread mesTh = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter message nahui!!!");
                while (true) {
                    String mess = scanner.next();
                    mess += " :" + socketWrapper.getSocket().getInetAddress();
                    sendMessage(mess);
                }
            }
        });
        mesTh.start();
    }

    private void sendMessage(String mess) {
        Thread th = new Thread(new MessageSender(mess));
        th.start();
    }


    private void initInputStreamListener(InputStream is) {
        Thread inputThread = new Thread(new InputStreamListener(is));
        inputThread.start();
    }


    private class MessageSender implements Runnable {
        private Object mess;
        private ObjectOutputStream oos;

        public MessageSender(Object mess) {
            this.mess = mess;
            this.oos = socketWrapper.getOs();
        }

        @Override
        public void run() {
            try {
                oos.writeObject(mess);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private class InputStreamListener implements Runnable {

        private InputStream ois;

        public InputStreamListener(InputStream ois) {
            this.ois = ois;
        }

        @Override
        public void run() {
            Object message = null;
            try (ObjectInputStream ois = new ObjectInputStream(this.ois)) {
                while (socketWrapper.getSocket().isConnected()) {
                    message = ois.readObject();
                    messageRecieved(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void messageRecieved(Object message) {
        System.out.println(message);
    }


}
