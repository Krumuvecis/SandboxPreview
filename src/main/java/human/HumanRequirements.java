package human;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;

//old ideas, TODO: review all
public class HumanRequirements {
    public Mass
            dailyFoodConsumption,
            dailyWaterConsumption,
            luggagePerPerson;
    public double
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
    public static class MilitaryRequirements extends HumanRequirements {
        //
        MilitaryRequirements() {
            super(new Mass(5), new Mass(10), 10, 10, new Mass(1, MassUnit.T));
        }
    }

    //
    public static class CivilianRequirements extends HumanRequirements {
        //
        CivilianRequirements() {
            super(new Mass(2), new Mass(10), 20, 20, new Mass(2, MassUnit.T));
        }
    }

    //
    public static class LuxuryRequirements extends HumanRequirements {
        //
        LuxuryRequirements() {
            super(new Mass(5), new Mass(20), 50, 50, new Mass(10, MassUnit.T));
        }
    }
}