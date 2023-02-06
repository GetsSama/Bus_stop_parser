package test;

import busparser.DefaultBusParser;
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
        //Document doc = Jsoup.parse(new File("\\TMP\\map_bus_html.txt"));

//        try (PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(Path.of("\\TMP\\map_bus_html.txt")))) {
//            printWriter.print(doc);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        var parser = new DefaultBusParser();
        var busesList = parser.parse(request);

        for(var bus : busesList)
            System.out.println(bus);
    }
}
