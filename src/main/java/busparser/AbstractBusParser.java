package busparser;

import busentity.Bus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

abstract class AbstractBusParser implements BusParser {
    private final String allBusesTableClassProperty;
    private final String busNumberClassProperty;
    private final String isLivePrognosesClassProperty;
    private final String arrivedPrognosesClassProperty;
    private final String arrivedByScheduleClassProperty;

    public AbstractBusParser(Properties properties) {
        this.allBusesTableClassProperty = properties.getProperty(ALL_BUSES_TABLE_CLASS);
        this.busNumberClassProperty = properties.getProperty(BUS_NUMBER_CLASS);
        this.isLivePrognosesClassProperty = properties.getProperty(IS_LIVE_PROGNOSES_CLASS);
        this.arrivedPrognosesClassProperty = properties.getProperty(ARRIVED_PROGNOSES_CLASS);
        this.arrivedByScheduleClassProperty = properties.getProperty(ARRIVED_BY_SCHEDULE_CLASS);
    }

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

    protected List<Bus> parseDocument(Document doc) {
        Elements allBusesInfo = doc.getElementsByClass(allBusesTableClassProperty);
        List<Bus> result = new ArrayList<>(allBusesInfo.size());

        for (var elem : allBusesInfo) {
            Bus.BusBuilder busBuilder = Bus.builder();

            busBuilder.busName(elem.getElementsByClass(busNumberClassProperty).text());
            busBuilder.isBusOnLive(!elem.getElementsByClass(isLivePrognosesClassProperty).isEmpty());
            busBuilder.arrivedTime(elem.getElementsByClass(arrivedPrognosesClassProperty).text());
            busBuilder.scheduleTime(elem.getElementsByClass(arrivedByScheduleClassProperty).text());

            result.add(busBuilder.build());
        }

        return result;
    }
}
