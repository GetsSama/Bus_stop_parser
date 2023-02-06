package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    private static final String request = "https://yandex.ru/maps/213/moscow/stops/stop__9643979/?ll=37.546432%2C55.646866&tab=overview&z=16.99";

    public static void main(String[] args) {
        Document doc = getHtmlDocByRequest(request);

        try (PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(Path.of("D:\\TMP\\map_bus_html.txt")))) {
            printWriter.print(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(getListOfAvailableBuses(doc));

        String allBusesInfoClass = "masstransit-vehicle-snippet-view _clickable _type_bus";
        Elements allBusesInfo = doc.getElementsByClass(allBusesInfoClass);
//        for (var elem : allBusesInfo) {
//            String bus = elem.attr("span");
//            System.out.println(bus);
//        }
        System.out.println(allBusesInfo.tagName("span"));
    }

    public static Document getHtmlDocByRequest (String request) {
        try {
            return Jsoup.connect(request).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getListOfAvailableBuses(Document htmlDoc) {
        String allBusesClass = "masstransit-transport-list-view__type-transport _type_bus _highlighted";
        return htmlDoc.getElementsByClass(allBusesClass).eachText();
    }
}
