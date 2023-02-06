package busentity;

import java.util.Objects;

public class BusBuilder {
    private Bus builtBus;
    private String busName;
    private String arrivedTime;
    private String scheduleTime;
    private boolean isBusOnLive;

    public BusBuilder() {}

    private boolean fieldsCheck() {
        Objects.requireNonNull(busName);
        Objects.requireNonNull(arrivedTime);
        Objects.requireNonNull(scheduleTime);
        return true;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public void setArrivedTime(String arrivedTime) {
        this.arrivedTime = arrivedTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public void setBusOnLive(boolean busOnLive) {
        isBusOnLive = busOnLive;
    }

    public Bus build() {
        if(!fieldsCheck())
            throw new IllegalStateException("Bus entity is uncompleted state!");
        return new Bus(busName, isBusOnLive, arrivedTime, scheduleTime);
    }
}
