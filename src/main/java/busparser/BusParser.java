package busparser;

import busentity.Bus;

import java.io.File;
import java.util.List;

/**
 * API for parse data about bus schedule from Yandex Map.
 * @author Zhuravlev N.O.
 * @version 0.0.1
 * @see Bus
 */
public interface BusParser {
    String ALL_BUSES_TABLE_CLASS = "allBusesTableClassName";
    String BUS_NUMBER_CLASS = "busNumberClassName";
    String IS_LIVE_PROGNOSES_CLASS = "isLivePrognosesClassName";
    String ARRIVED_PROGNOSES_CLASS = "arrivedPrognosesClassName";
    String ARRIVED_BY_SCHEDULE_CLASS = "arrivedByScheduleClassName";

    /**
     * @param sourceURL - URL of concrete bus station in Yandex Map
     * @return List of all Bus entities which can arrived at given bus station
     */
    List<Bus> parse(String sourceURL);

    /**
     *
     * @param sourceFile - file with HTML from Yandex Map response
     * @return List of all Bus entities which can arrived at given bus station
     */
    List<Bus> parse(File sourceFile);
}
