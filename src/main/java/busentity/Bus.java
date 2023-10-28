package busentity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Simple entity class for persist and give data after parsing.
 * @author Zhuravlev N.O.
 * @version 0.0.1
 * @see busparser.BusParser
 */
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Bus {
    private final String busName;
    private final boolean isBusOnLive;
    private final String arrivedTime;
    private final String scheduleTime;
}
