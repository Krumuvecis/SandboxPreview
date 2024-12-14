package dimensions;

import java.util.ArrayList;

import dimensions.time.TimeUnit;
import dimensions.time.Time;

//
final class TimeTest extends DimensionalValueTest<Time, TimeUnit> {
    //
    public static void main(String[] args) {
        new TimeTest(new Time(100, TimeUnit.MS));
        new TimeTest(new Time(1, TimeUnit.S));
        new TimeTest(new Time(1, TimeUnit.MIN));
        new TimeTest(new Time(1, TimeUnit.H));
        new TimeTest(new Time(1, TimeUnit.DAY));
        new TimeTest(new Time(1, TimeUnit.WEEK));
        new TimeTest(new Time(1, TimeUnit.MONTH));
        new TimeTest(new Time(1, TimeUnit.YEAR));
    }

    private TimeTest(Time time) {
        super(time, new ArrayList<>(){{
            add(TimeUnit.MS);
            add(TimeUnit.S);
            add(TimeUnit.MIN);
            add(TimeUnit.H);
            add(TimeUnit.DAY);
            add(TimeUnit.WEEK);
            add(TimeUnit.MONTH);
            add(TimeUnit.YEAR);
        }});
    }
}