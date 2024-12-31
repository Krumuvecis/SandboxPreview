package human;

import java.util.Map;

import dimensions.mass.Mass;

//old ideas, TODO: review all
public class ShipCrew {
    Map<HumanRequirements, Integer> crewComposition;

    //
    public ShipCrew(Map<HumanRequirements, Integer> crewComposition) {
        this.crewComposition = crewComposition;
    }

    //
    public Mass getDailyFoodConsumption() {
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
    public Mass getDailyWaterConsumption() {
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
    public Mass getTotalLuggage() {
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
    public double getRequiredArea() {
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