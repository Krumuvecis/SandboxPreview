package rotors;

import dimensions.distance.Distance;
import dimensions.mass.Mass;

//
public class RotorConfiguration {
    private final double ceilingHeight;
    private final int floorCount;

    //
    public RotorConfiguration(double ceilingHeight, int floorCount) {
        this.ceilingHeight = ceilingHeight;
        this.floorCount = floorCount;
    }

    //
    public double getCeilingHeight() {
        return ceilingHeight;
    }

    //
    public int getFloorCount() {
        return floorCount;
    }

    //
    static class TestRotorConfig {
        //
        TestRotorConfig() {

            //elevator & airlock
            //end hall
            //corridor
            //lounge hall
            //corridor
            //end hall
            //elevator & airlock

        }
    }
}

//
interface MassiveRotorPart {
    Mass getMass();
}

//
enum RotorSizeStandard {
    MM_1200("1.2 m", new Distance(1.2)), //for narrow corridors
    MM_2000("2 m", new Distance(2)), //for wider corridors and small rooms
    MM_4000("4 m", new Distance(3.9)), //for medium rooms
    MM_5200("5.2 m", new Distance(5.2)), //1.2m corridor + 4m of rooms
    MM_6000("6 m", new Distance(5.2)), //2m corridor + 4m of rooms
    MM_7000("8 m", new Distance(7)), //for big rooms
    MM_9200("9.2 m", new Distance(9.2)), //1.2m corridor + 8m of rooms
    MM_10000("10 m", new Distance(10)), //2m corridor + 8m of rooms
    MM_10400("10.4 m", new Distance(10.4)), //2x 5.2
    MM_18000("18 m", new Distance(16)), //2m corridor + 16m of rooms

    MM_1000("1 m", new Distance(1)); //template

    private final String name;
    private final Distance standard;

    //
    RotorSizeStandard(String name, Distance standard) {
        this.name = name;
        this.standard = standard;
    }

    //
    public final String getName() {
        return name;
    }

    //
    public final Distance getStandard() {
        return standard;
    }
}

class StandardizedRotorPart {
    //
}

//below rooms, like a floor
class RotorStructuralPart implements MassiveRotorPart {
    @Override
    public Mass getMass() {
        return null;
    }
}

class RotorAuxiliaryPart implements MassiveRotorPart {
    @Override
    public Mass getMass() {
        return null;
    }
}

//goes above the floor
class RotorModule implements MassiveRotorPart {
    @Override
    public Mass getMass() {
        return null;
    }

    //where the elevator connects to the rotor
    static class RotorElevatorRoom extends RotorModule {
        //
    }

    //
    static class Hall extends RotorModule {
        //
    }

    //
    static class Corridor extends RotorModule {
        boolean doublySided;
    }

    //for air compressors, thermals, electrical mains, etc.
    static class TechnicalRoom extends RotorModule {
        //
    }

    //
    static class Cabin extends RotorModule {
        RotorSizeStandard
                width,
                length;

        Cabin(RotorSizeStandard width, RotorSizeStandard length) {
            this.width = width;
            this.length = length;
        }
    }
}