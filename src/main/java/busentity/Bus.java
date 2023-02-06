package busentity;

import java.util.Objects;

public class Bus {
    private final String busName;
    private final boolean isBusOnLive;
    private final String arrivedTime;
    private final String scheduleTime;

    public Bus(String busName, boolean isBusOnLive, String arrivedTime, String scheduleTime) {
        this.busName = busName;
        this.isBusOnLive = isBusOnLive;
        this.arrivedTime = arrivedTime;
        this.scheduleTime = scheduleTime;
    }

    public String getBusName() {
        return busName;
    }

    public boolean isBusOnLive() {
        return isBusOnLive;
    }

    public String getArrivedTime() {
        return arrivedTime;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return isBusOnLive == bus.isBusOnLive && busName.equals(bus.busName) && arrivedTime.equals(bus.arrivedTime) && scheduleTime.equals(bus.scheduleTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(busName, isBusOnLive, arrivedTime, scheduleTime);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busName='" + busName + '\'' +
                ", isBusOnLive=" + isBusOnLive +
                ", arrivedTime='" + arrivedTime + '\'' +
                ", scheduleTime='" + scheduleTime + '\'' +
                '}';
    }
}
