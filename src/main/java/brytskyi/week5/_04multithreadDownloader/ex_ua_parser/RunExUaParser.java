package brytskyi.week5._04multithreadDownloader.ex_ua_parser;

import brytskyi.week5._04multithreadDownloader.ex_ua_parser.controller.Controller;

import java.net.MalformedURLException;

/**
 * Created by alexandr on 03.11.16.
 */
public class RunExUaParser {

    public static void main(String[] args) {
        try {
            new Controller("http://www.ex.ua/105813471?r=2,23775");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}
