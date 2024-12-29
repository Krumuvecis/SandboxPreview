package rotors;

import dimensions.distance.Distance;
import human.rotationalLimits.RotationalLimits;

//
public abstract class RotorGeometry {
    private final RotationalLimits limits;
    private final RotorConfiguration configuration;

    //
    RotorGeometry(RotationalLimits limits, RotorConfiguration configuration) {
        this.limits = limits;
        this.configuration = configuration;
    }

    //
    public RotationalLimits getRotationalLimits() {
        return limits;
    }

    //
    public Distance getRadius() {
        return limits.getRadius();
    }

    //
    public RotorConfiguration getConfiguration() {
        return configuration;
    }

    //
    public static class SectorRotor extends RotorGeometry {
        //
        public SectorRotor(RotationalLimits limits, RotorConfiguration configuration) {
            super(limits, configuration);
        }
    }

    //
    public static class RingRotor extends RotorGeometry {
        //
        public RingRotor(RotationalLimits limits, RotorConfiguration configuration) {
            super(limits, configuration);
        }
    }

    public static class RotorGeometryGenerator {
        private final RotationalLimits limits;

        //
        RotorGeometryGenerator(RotationalLimits limits) {
            this.limits = limits;
        }

        public RotorGeometry getGeometry() {
            //return new RotorGeometry(limits);
            return null;
        }
    }
}