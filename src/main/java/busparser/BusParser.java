package busparser;

import busentity.Bus;

import java.io.File;
import java.util.List;

public interface BusParser {
    List<Bus> parse(String sourceURL);
    List<Bus> parse(File sourceFile);
}
