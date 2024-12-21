package space.ships;

import dimensions.mass.Mass;
import org.jetbrains.annotations.NotNull;

//
@SuppressWarnings("ClassCanBeRecord")
public class ShipPropulsion implements MassiveShipPart {
    private final double exhaustVelocity;
    private final @NotNull Mass mass;

    //
    ShipPropulsion(double exhaustVelocity, @NotNull Mass mass) {
        this.exhaustVelocity = exhaustVelocity;
        this.mass = mass;
    }

    //
    @Override
    public @NotNull Mass getMass() {
        return mass;
    }

    double getExhaustVelocity() {
        return exhaustVelocity;
    }

    double getECoefficient(double deltaV) {
        return Math.exp(deltaV / exhaustVelocity) - 1;
    }

    public Mass getRequiredFuelMass(double deltaV, Mass shipMass) {
        // m_fuel = ship_mass * eCoefficient
        return new Mass(shipMass.getSI() * getECoefficient(deltaV));
    }

    public double getAvailableDeltaV(Mass shipMass, Mass fuelMass) {
        // deltaV = ve * ln(m_fuel / m_ship + 1)
        return exhaustVelocity * Math.log(fuelMass.getSI() / shipMass.getSI() + 1);
    }

    public Mass getAvailableCargo(double deltaV, Mass shipMass, Mass fuelMass) {
        // m_fuel = (ship_mass + cargo) * eCoefficient
        // cargo = m_fuel / eCoefficient - ship_mass
        return new Mass(fuelMass.getSI() / getECoefficient(deltaV) - shipMass.getSI());
    }
}
