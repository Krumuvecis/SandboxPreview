package ships;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import dimensions.time.Time;
import dimensions.time.TimeUnit;
import rotors.RotatingHabitatSystem;

//
class ShipAccommodations implements MassiveShipPart {
    //final @NotNull Mass food, water, luggage;
    //final @NotNull RotatingHabitatSystem rotatingHabitats;

    //
    ShipAccommodations(Time tripTime, /*ShipCrew crew, */double staffArea, int rotorCount, int floorCount) {
        //food = new Mass(tripTime.get(TimeUnit.DAY) * crew.getDailyFoodConsumption().getInBase());
        //water = new Mass(tripTime.get(TimeUnit.DAY) * crew.getDailyWaterConsumption().getInBase());
        //luggage = crew.getTotalLuggage();
        //rotatingHabitats = new RotatingHabitatSystem(crew, staffArea, rotorCount, floorCount);
    }

    @Override
    public @NotNull Mass getMass() {
        return new Mass(/*food.getInBase() + water.getInBase() + luggage.getInBase() + rotatingHabitats.getMass().getInBase()*/0);
    }
}