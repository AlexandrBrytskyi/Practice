package brytskyi.week3.vkGrabber;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.Actor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.friends.FriendsList;
import com.vk.api.sdk.objects.friends.UserXtrLists;
import com.vk.api.sdk.objects.friends.responses.GetResponse;
import com.vk.api.sdk.objects.users.User;
import com.vk.api.sdk.queries.friends.FriendsGetQuery;
import com.vk.api.sdk.queries.search.SearchGetHintsFilter;
import com.vk.api.sdk.queries.users.UserField;
import com.vk.api.sdk.queries.users.UsersNameCase;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.util.*;
import java.util.List;

/**
 * Created by alexandr on 16.10.16.
 */
public class VkUtils {

    private VkApiClient vkApiClient;
    private String token;
    private UserActor actor;

    public VkUtils() {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vkApiClient = new VkApiClient(transportClient);
        checkToken();
        login();
    }

    private void login() {
        if (token == null) {
            System.out.println("Enter code from browser address line");
            try {
                Desktop.getDesktop().browse(URI.create("https://oauth.vk.com/authorize?client_id=" + Properties.APP_ID +
                        "&display=mobile&redirect_uri=" + Properties.REDIRECT_URI +
                        "&scope=offline,wall,friends&response_type=code&v=5.58"));
                String code = new Scanner(System.in).next();
                UserAuthResponse authResponse = vkApiClient.oauth()
                        .userAuthorizationCodeFlow(Properties.APP_ID, Properties.CLIENT_SECRET, Properties.REDIRECT_URI, code)
                        .execute();
                token = authResponse.getAccessToken();
                writeTokenInFile(token);
                actor = new UserActor(authResponse.getUserId(), token);
            } catch (IOException e) {
                e.printStackTrace();
                login();
            } catch (ApiException e) {
                login();
            } catch (ClientException e) {
                login();
            }
        } else {
            actor = new UserActor(Properties.APP_ID, token);
        }
    }

    private void writeTokenInFile(String token) {
        try (Writer w = new FileWriter("temp/token");) {
            w.write(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkToken() {
        try {
            Scanner scanner = new Scanner(new FileInputStream("temp/token"));
            token = scanner.next();
        } catch (FileNotFoundException e) {
            return;
        }
    }


    public VkApiClient getVkApiClient() {
        return vkApiClient;
    }

    public UserActor getActor() {
        return actor;
    }

    public static void main(String[] args) {
        VkUtils utils = new VkUtils();
        VkApiClient vkApiClient = utils.getVkApiClient();
        UserActor actor = utils.getActor();
        try {
            User u = vkApiClient.friends().search(actor, actor.getId()).q("Небесюк").execute().getItems().get(0);
            System.out.println(u.getId());
            vkApiClient.wall().post(actor).ownerId(u.getId()).message("Privet is VK API").attachments("http://strana-sovetov.com/images/stories/tip/hobby/plants/mens-happiness-anthurium-0.jpg").execute();
//            vkApiClient.wall().post(actor).message("Sania pisiun").ownerId(actor.getId()).placeId(nebaId).execute();
//            vkApiClient.wall().post(actor).message("Hello from vk API").execute();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }
}
