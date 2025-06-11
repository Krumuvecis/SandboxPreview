package markets2;

import java.util.Set;
import java.util.HashSet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import markets2.resources.ParticularResources;
import markets2.market.MultiMarket;
import markets2.granary.Granary;
import markets2.person.Person;

//
public final class World {
    private static final int YEAR_LENGTH = 40;
    private static final double
            MINIMUM_WEATHER_FERTILITY = -0.1,
            MAXIMUM_WEATHER_FERTILITY = 1,
            TOTAL_LAND = 5,
            BASE_YIELD_PER_AREA_FOOD = 1,
            BASE_YIELD_PER_AREA_STICKS = 0.5;
    private int elapsedTime;
    private double weatherFertility;
    private final @NotNull MultiMarket market;
    private final @NotNull Set<@NotNull Person> people;
    private final @NotNull Granary granary;
    private final @NotNull Bank bank;

    //
    public World() {
        elapsedTime = 0;
        market = new MultiMarket(this);
        people = new HashSet<>();
        granary = new Granary(market.getMarket(ParticularResources.FOOD));
        bank = new Bank();
    }

    //total elapsed time from the very beginning
    public int getElapsedTime() {
        return elapsedTime;
    }

    //
    public int getFullYears() {
        return Math.floorDiv(elapsedTime, YEAR_LENGTH);
    }

    //capped at year length, repeats every year
    public int getDayOfYear() {
        return elapsedTime - getFullYears() * YEAR_LENGTH;
    }

    //fraction of the elapsed year vs total year length
    public double getYearFraction() {
        return (double) getDayOfYear() / YEAR_LENGTH;
    }

    //
    public double getWeatherFertility() {
        return weatherFertility;
    }

    //
    public double getLandPerPerson() {
        return TOTAL_LAND / Math.max(1, people.size());
    }

    //land * fertility
    public double getMaximumYield_gatherFood() {
        return getLandPerPerson() * BASE_YIELD_PER_AREA_FOOD * weatherFertility;
    }

    //land * fertility
    public double getMaximumYield_gatherSticks() {
        return getLandPerPerson() * BASE_YIELD_PER_AREA_STICKS;
    }

    //
    public @NotNull MultiMarket getMarket() {
        return market;
    }

    //
    public @NotNull @Unmodifiable Set<@NotNull Person> getPeople() {
        return Set.copyOf(people);
    }

    //
    public @NotNull Granary getGranary() {
        return granary;
    }

    //
    public @NotNull Bank getBank() {
        return bank;
    }

    //
    public void update() {
        //world
        updateWeather();

        //people actions
        updatePeople_actionDecisions();
        updatePeople_performActions();

        //markets
        granary.update(); //does the granary orders
        updatePeople_marketDecisions(); //does the people orders
        market.update(); //fulfills the orders

        //people uncontrolled
        updatePeople_nutritionAndHealth();
        removeDeadPeople();
        elapsedTime++;
    }

    private void updateWeather() {
        double
                weatherFertilityRange = MAXIMUM_WEATHER_FERTILITY - MINIMUM_WEATHER_FERTILITY,
                uncappedWeatherFertility = MINIMUM_WEATHER_FERTILITY + weatherFertilityRange * (1 + Math.cos(getYearFraction())) / 2;
        weatherFertility = Math.max(0, uncappedWeatherFertility);
    }

    private void updatePeople_actionDecisions() {
        for (@NotNull Person person : getPeople()) {
            person.updateActionDecisions();
        }
    }

    private void updatePeople_performActions() {
        for (@NotNull Person person : getPeople()) {
            person.performAction();
        }
    }

    private void updatePeople_marketDecisions() {
        for (@NotNull Person person : getPeople()) {
            person.updateMarketDecisions();
        }
    }

    private void updatePeople_nutritionAndHealth() {
        for (@NotNull Person person : getPeople()) {
            person.updateNutritionAndHealth();
        }
    }

    private void removeDeadPeople() {
        people.removeIf(person -> !person.getHealth().isAlive());
    }

    //adds a new person to the world
    public void addPerson(@NotNull Person person) {
        people.add(person);
    }
}