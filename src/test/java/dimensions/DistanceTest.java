package dimensions;

import java.util.ArrayList;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;

//
final class DistanceTest extends DimensionalValueTest<Distance, DistanceUnit> {
    //
    public static void main(String[] args) {
        new DistanceTest(new Distance(100, DistanceUnit.M));
        new DistanceTest(new Distance(1, DistanceUnit.KM));
        new DistanceTest(new Distance(1, DistanceUnit.AU));
        new DistanceTest(new Distance(1, DistanceUnit.LY));
        new DistanceTest(new Distance(1, DistanceUnit.PC));
    }

    private DistanceTest(Distance distance) {
        super(distance, new ArrayList<>(){{
            add(DistanceUnit.M);
            add(DistanceUnit.KM);
            add(DistanceUnit.AU);
            add(DistanceUnit.LY);
            add(DistanceUnit.PC);
        }});
    }
}