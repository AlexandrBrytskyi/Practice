package brytskyi.week4.socketChat;

import java.io.IOException;

/**
 * Created by alexandr on 17.10.16.
 */
public class ClientStarter {

    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 9999, "Vasia");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
