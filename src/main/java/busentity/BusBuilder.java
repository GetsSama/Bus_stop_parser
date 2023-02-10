package busentity;

import java.util.Objects;

/**
 * Support class for build correct Bus entities. !Simple and naive realization for builder, fields determinate in hardcode.
 * @author Zhuravlev N.O.
 * @version 0.0.1
 * @see Bus
 */
public class BusBuilder {
    private Bus builtBus;
    private String busName;
    private String arrivedTime;
    private String scheduleTime;
    private boolean isBusOnLive;

    public BusBuilder() {}

    /**
     * Check that fields non-null.
     * @return true if - OK
     */
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

    /**
     * Build complete Bus object, if all fields correct.
     * @return Bus instance
     * @throws IllegalStateException if {@link BusBuilder#fieldsCheck()} return false;
     */
    public Bus build() {
        if(!fieldsCheck())
            throw new IllegalStateException("Bus entity is uncompleted state!");
        return new Bus(busName, isBusOnLive, arrivedTime, scheduleTime);
    }

    /**
     * Reset state this instance of BusBuilder for reuse.
     */
    public void refresh() {
        busName = null;
        arrivedTime = null;
        scheduleTime = null;
        isBusOnLive = false;
    }
}
