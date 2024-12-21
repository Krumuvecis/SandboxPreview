package space.ships;

import dimensions.distance.Distance;
import dimensions.mass.Mass;
import dimensions.mass.MassUnit;
import dimensions.time.Time;
import org.jetbrains.annotations.NotNull;
import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;
import space.routes.ManeuverRoute;
import space.routes.NotSameRootException;
import space.routes.ObjectRoute;

public class ShipDesigner {
    Time tripTime;
    double deltaV;
    int
            rotorCount,
            floorCount;

    //
    public ShipDesigner(@NotNull AbstractOrbitalBody start, @NotNull AbstractOrbitalBody target,
                        int rotorCount, int floorCount)
            throws NotSameRootException {
        ObjectRoute objectRoute = new ObjectRoute(start, target);
        ManeuverRoute maneuverRoute = new ManeuverRoute(objectRoute);
        tripTime = maneuverRoute.getTotalTransferTime();
        deltaV = maneuverRoute.getTotalDeltaV();
        this.rotorCount = rotorCount;
        this.floorCount = floorCount;
    }

    //
    public Ship getShip(ShipCrew crew, double staffArea, Mass fuelMass) {
        ShipPropulsion shipPropulsion = new ShipPropulsion(20000, new Mass(20, MassUnit.T));
        Ship.FuelTanks fuelTanks = new Ship.FuelTanks(fuelMass, 1000, 3000, new Distance(0.05));
        return new Ship(shipPropulsion, fuelTanks, tripTime, crew, staffArea, rotorCount, floorCount);
    }

    //
    public Mass getCargo(Ship ship) {
        return ship.getMaxCargo(deltaV);
    }
}