package dimensions.mass;

import java.util.ArrayList;

import dimensions.DimensionalValueTest;

//
final class MassTest extends DimensionalValueTest<Mass, MassUnit> {
    //
    public static void main(String[] args) {
        new MassTest(new Mass(100, MassUnit.G));
        new MassTest(new Mass(1, MassUnit.KG));
        new MassTest(new Mass(1, MassUnit.T));
        new MassTest(new Mass(1, MassUnit.EARTH_MASS));
        new MassTest(new Mass(1, MassUnit.JUPITER_MASS));
        new MassTest(new Mass(1, MassUnit.SOLAR_MASS));
    }

    private MassTest(Mass mass) {
        super(mass, new ArrayList<>(){{
            add(MassUnit.G);
            add(MassUnit.KG);
            add(MassUnit.T);
            add(MassUnit.EARTH_MASS);
            add(MassUnit.JUPITER_MASS);
            add(MassUnit.SOLAR_MASS);
        }});
    }
}