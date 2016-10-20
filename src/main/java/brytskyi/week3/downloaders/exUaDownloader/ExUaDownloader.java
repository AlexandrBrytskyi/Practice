package brytskyi.week3.downloaders.exUaDownloader;

import brytskyi.week3.downloaders.urlDownloader.UrlDownloader;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by alexandr on 15.10.16.
 */

// TODO: 16.10.16 console clearing
public class ExUaDownloader {

    private LinksGetter linksGetter;
    private UrlDownloader downloader;
    private Scanner scanner;
    private boolean isDownloading;

    public ExUaDownloader() {
        linksGetter = new LinksGetter();
        downloader = new UrlDownloader();
        scanner = new Scanner(System.in);
        while (true) {
            giveInterface();
        }
    }

    private void giveInterface() {
        System.out.println("Welcome, enter the page please");
        String page = scanner.next();
        System.out.println("Ok, now trying to look through links");
        Set<UrlTittleWrapper> urls = null;
        urls = getUrlSet(page, urls);
        Set<UrlTittleWrapper> finalUrls = urls;
        Map<Integer, UrlTittleWrapper> urlsMap = new HashMap<Integer, UrlTittleWrapper>() {{
            int i = 1;
            for (UrlTittleWrapper url : finalUrls) {
                put(i, url);
                i++;
            }
        }};
        UrlTittleWrapper url = defineURLuserChose(urlsMap);
        System.out.println("You`ve chosen url :" + url.toString());
        showDownloadingMenu(url);
    }

    private void showDownloadingMenu(UrlTittleWrapper url) {
        try {
            download(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Check file, please");
            scanner = new Scanner(System.in);
            showDownloadingMenu(url);
        }
    }

    private UrlTittleWrapper defineURLuserChose(Map<Integer, UrlTittleWrapper> urlsMap) {
        showURLs(urlsMap);
        UrlTittleWrapper url = null;
        try {
            int res = scanner.nextInt();
            url = urlsMap.get(res);
            if (url == null) throw new NullPointerException();
        } catch (Exception e) {
            System.out.println("Wrong choose!!");
            scanner = new Scanner(System.in);
            defineURLuserChose(urlsMap);
        }
        return url;
    }

    private void showURLs(Map<Integer, UrlTittleWrapper> urlsMap) {
        if (urlsMap.isEmpty()) {
            System.out.println("Sorry, no urls");
            giveInterface();
        }
        System.out.println("Please, enter number of url to download");
        for (Map.Entry<Integer, UrlTittleWrapper> integerURLEntry : urlsMap.entrySet()) {
            System.out.println(integerURLEntry);
        }
    }

    private Set<UrlTittleWrapper> getUrlSet(String page, Set<UrlTittleWrapper> urls) {
        try {
            urls = linksGetter.getURLs(URI.create(page).toURL());
        } catch (IOException e) {
            System.out.println("Wrong URL!!!");
            scanner = new Scanner(System.in);
            giveInterface();
        }
        return urls;
    }


    public void download(UrlTittleWrapper url) throws IOException {
        System.out.println("Enter file path, please");
        String filePath = scanner.next();
        isDownloading = true;
        Thread percents = new Thread(new Runnable() {
            @Override
            public void run() {
                final String os = System.getProperty("os.name");
                while (isDownloading) {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Downloading file " + url.getTittle()
                            + "\n" + "into folder: " + filePath
                            + "\n" + downloader.getPersents() + "% of 100");

                    System.out.print("\033[H\033[2J");
                }
            }

            private void clearConsole(String os) {
                if (os.contains("Windows")) {
                    try {
                        Runtime.getRuntime().exec("cls");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Runtime.getRuntime().exec("clear");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        percents.start();
        try {
            downloader.download(url.getUrl().toString(), filePath + url.getTittle());
        } catch (IOException e) {
            isDownloading = false;
            throw e;
        }
        isDownloading = false;
        System.out.println("Congratulations!!!");
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
