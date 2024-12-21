package space.ships;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;

//
class HumanRequirements {
    static final double STANDARD_GRAVITY = 9.8; // m/s^2
    Mass
            dailyFoodConsumption,
            dailyWaterConsumption,
            luggagePerPerson;
    double
            privateAreaPerPerson,
            commonAreaPerPerson;

    HumanRequirements(Mass dailyFoodConsumption, Mass dailyWaterConsumption,
                      double privateAreaPerPerson, double commonAreaPerPerson,
                      Mass luggagePerPerson) {
        this.dailyFoodConsumption = dailyFoodConsumption;
        this.dailyWaterConsumption = dailyWaterConsumption;
        this.privateAreaPerPerson = privateAreaPerPerson;
        this.commonAreaPerPerson = commonAreaPerPerson;
        this.luggagePerPerson = luggagePerPerson;
    }

    //
    static class MilitaryRequirements extends HumanRequirements {
        //
        MilitaryRequirements() {
            super(new Mass(5), new Mass(10), 10, 10, new Mass(1, MassUnit.T));
        }
    }

    //
    static class CivilianRequirements extends HumanRequirements {
        //
        CivilianRequirements() {
            super(new Mass(2), new Mass(10), 20, 20, new Mass(2, MassUnit.T));
        }
    }

    //
    static class LuxuryRequirements extends HumanRequirements {
        //
        LuxuryRequirements() {
            super(new Mass(5), new Mass(20), 50, 50, new Mass(10, MassUnit.T));
        }
    }
}