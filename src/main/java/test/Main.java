package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    private static final String request = "https://yandex.ru/maps/213/moscow/stops/stop__9643979/?ll=37.546372%2C55.647286&tab=overview&z=16.73";

    public static void main(String[] args) throws IOException {
        //Document doc = getHtmlDocByRequest(request);
        Document doc = Jsoup.parse(new File("\\TMP\\map_bus_html.txt"));

//        try (PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(Path.of("\\TMP\\map_bus_html.txt")))) {
//            printWriter.print(doc);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //System.out.println(getListOfAvailableBuses(doc));

        String allBusesInfoClass = "masstransit-vehicle-snippet-view _clickable _type_bus";
        Elements allBusesInfo = doc.getElementsByClass(allBusesInfoClass);
//        for (var elem : allBusesInfo) {
//            System.out.println(elem);
//            System.out.println("\n");
//        }

        Element firstElem = allBusesInfo.first();
        System.out.println(firstElem);

        System.out.println(firstElem.getElementsByClass("masstransit-vehicle-snippet-view__main-text").text());  //Get bus number
        boolean isBusPrognosesInLive =  !firstElem.getElementsByClass("masstransit-prognoses-view__title _live").isEmpty(); //If prognosis in live = true
        String prognosis = firstElem.getElementsByClass("masstransit-prognoses-view__title-text").text(); //Bus arrival prognosis
        String regularTime = firstElem.getElementsByClass("masstransit-prognoses-view__time").text(); //Next buses by schedule

        System.out.println(isBusPrognosesInLive);
        System.out.println(prognosis);
        System.out.println(regularTime);

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
