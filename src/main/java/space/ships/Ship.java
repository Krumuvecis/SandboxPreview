package space.ships;

import org.jetbrains.annotations.NotNull;

import dimensions.distance.Distance;
import dimensions.mass.Mass;
import dimensions.time.Time;

//
public class Ship {
    ShipPropulsion propulsion;
    FuelTanks fuelTanks;
    ShipAccommodations accommodations;

    //
    public Ship(ShipPropulsion shipPropulsion, FuelTanks fuelTanks,
                Time tripTime, ShipCrew crew, double staffArea, int rotorCount, int floorCount) {
        this.propulsion = shipPropulsion;
        this.fuelTanks = fuelTanks;
        accommodations = new ShipAccommodations(tripTime, crew, staffArea, rotorCount, floorCount);
    }

    //
    public Mass getDryMass() {
        return new Mass(propulsion.getMass().getSI() + fuelTanks.getMass().getSI() + accommodations.getMass().getSI());
    }

    //
    public Mass getMaxCargo(double deltaV) {
        return propulsion.getAvailableCargo(deltaV, getDryMass(), fuelTanks.getMaxFuel());
    }

    //
    public static class FuelTanks implements MassiveShipPart {
        private final Mass
                maxFuel,
                dryMass;

        //assumes a single spherical tank
        public FuelTanks(Mass fuelMass, double fuelDensity, double tankMaterialDensity, Distance tankThickness) {
            maxFuel = fuelMass;
            double
                    fuelVolume = fuelMass.getSI() / fuelDensity,
                    radius = Math.pow(3 * fuelVolume / 4 / Math.PI, 1.0/3),
                    area = 4 * Math.PI * Math.pow(radius, 2);
            dryMass = new Mass(area * tankThickness.getSI() * tankMaterialDensity);
        }

        //dry mass
        @Override
        public @NotNull Mass getMass() {
            return dryMass;
        }

        public Mass getMaxFuel() {
            return maxFuel;
        }
    }
}