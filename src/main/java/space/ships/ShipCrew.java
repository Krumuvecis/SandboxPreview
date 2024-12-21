package space.ships;

import dimensions.mass.Mass;

import java.util.Map;

public class ShipCrew {
    Map<HumanRequirements, Integer> crewComposition;

    //
    ShipCrew(Map<HumanRequirements, Integer> crewComposition) {
        this.crewComposition = crewComposition;
    }

    //
    Mass getDailyFoodConsumption() {
        double sum = 0;
        for (HumanRequirements humanRequirements : crewComposition.keySet()) {
            sum += getDailyFoodConsumption(humanRequirements);
        }
        return new Mass(sum);
    }

    private double getDailyFoodConsumption(HumanRequirements humanRequirements) {
        int people = crewComposition.get(humanRequirements);
        return people * humanRequirements.dailyFoodConsumption.getSI();
    }

    //
    Mass getDailyWaterConsumption() {
        double sum = 0;
        for (HumanRequirements humanRequirements : crewComposition.keySet()) {
            sum += getDailyWaterConsumption(humanRequirements);
        }
        return new Mass(sum);
    }

    private double getDailyWaterConsumption(HumanRequirements humanRequirements) {
        int people = crewComposition.get(humanRequirements);
        return people * humanRequirements.dailyWaterConsumption.getSI();
    }

    //
    Mass getTotalLuggage() {
        double sum = 0;
        for (HumanRequirements humanRequirements : crewComposition.keySet()) {
            sum += getTotalLuggage(humanRequirements);
        }
        return new Mass(sum);
    }

    private double getTotalLuggage(HumanRequirements humanRequirements) {
        int people = crewComposition.get(humanRequirements);
        return people * humanRequirements.luggagePerPerson.getSI();
    }

    //
    double getRequiredArea() {
        double sum = 0;
        for (HumanRequirements humanRequirements : crewComposition.keySet()) {
            sum += getRequiredArea(humanRequirements);
        }
        return sum;
    }

    private double getRequiredArea(HumanRequirements humanRequirements) {
        int people = crewComposition.get(humanRequirements);
        return people * (humanRequirements.privateAreaPerPerson + humanRequirements.commonAreaPerPerson);
    }
}