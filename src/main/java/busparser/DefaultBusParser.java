package busparser;

import busentity.Bus;
import busentity.BusBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultBusParser implements BusParser{
    public static final String ALL_BUSES_TABLE_CLASS = "masstransit-vehicle-snippet-view _clickable _type_bus";
    public static final String BUS_NUMBER_CLASS = "masstransit-vehicle-snippet-view__main-text";
    public static final String IS_LIVE_PROGNOSES_CLASS = "masstransit-prognoses-view__title _live";
    public static final String ARRIVED_PROGNOSES_CLASS = "masstransit-prognoses-view__title-text";
    public static final String ARRIVED_BY_SCHEDULE_CLASS = "masstransit-prognoses-view__time";

    @Override
    public List<Bus> parse(String sourceURL) {
        Document doc;

        try {
            doc = Jsoup.connect(sourceURL).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parseDocument(doc);
    }

    @Override
    public List<Bus> parse(File sourceFile) {
        Document doc;

        try {
            doc = Jsoup.parse(sourceFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parseDocument(doc);
    }

    private List<Bus> parseDocument(Document doc) {
        Elements allBusesInfo = doc.getElementsByClass(ALL_BUSES_TABLE_CLASS);
        List<Bus> result = new ArrayList<>(allBusesInfo.size());
        BusBuilder busBuilder = new BusBuilder();

        for (var elem : allBusesInfo) {
            busBuilder.setBusName(elem.getElementsByClass(BUS_NUMBER_CLASS).text());
            busBuilder.setBusOnLive(!elem.getElementsByClass(IS_LIVE_PROGNOSES_CLASS).isEmpty());
            busBuilder.setArrivedTime(elem.getElementsByClass(ARRIVED_PROGNOSES_CLASS).text());
            busBuilder.setScheduleTime(elem.getElementsByClass(ARRIVED_BY_SCHEDULE_CLASS).text());
            result.add(busBuilder.build());
            busBuilder.refresh();
        }

        return result;
    }
}
