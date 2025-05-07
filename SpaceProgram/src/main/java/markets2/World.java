package markets2;

import java.util.Set;
import java.util.HashSet;
import java.util.Random;

import org.jetbrains.annotations.NotNull;

import markets2.person.Person;

//
public final class World {
    private static final @NotNull Random RANDOM = new Random();
    private static final double
            YEAR_LENGTH = 40,
            MINIMUM_WEATHER_FERTILITY = -0.1,
            MAXIMUM_WEATHER_FERTILITY = 1,
            TOTAL_LAND = 10,
            BASE_FARMING_YIELD_PER_AREA = 1;
    private int elapsedTime;
    private double weatherFertility;
    private final @NotNull Market market;
    private final @NotNull Set<@NotNull Person> people;
    private final @NotNull Granary granary;
    private final @NotNull Bank bank;

    //
    public World() {
        elapsedTime = 0;
        market = new Market(this);
        people = new HashSet<>();
        granary = new Granary(market);
        bank = new Bank();
    }

    //
    public int getElapsedTime() {
        return elapsedTime;
    }

    //
    public double getWeatherFertility() {
        return weatherFertility;
    }

    //
    public double getLandPerPerson() {
        return TOTAL_LAND / Math.max(1, people.size());
    }

    //
    public double getMaximumFarmingYield() {
        return getLandPerPerson() * BASE_FARMING_YIELD_PER_AREA * weatherFertility;
    }

    //
    public @NotNull Market getMarket() {
        return market;
    }

    //
    public @NotNull Set<@NotNull Person> getPeople() {
        return people;
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

        //markets
        updatePeopleDecisions();
        granary.update();
        market.update();

        //TODO: update more stuff here

        //people
        updatePeopleUnconscious();
        removeDeadPeople();
        elapsedTime++;
    }

    private void updateWeather() {
        //fertility = MINIMUM_FERTILITY + RANDOM.nextDouble() * (MAXIMUM_FERTILITY - MINIMUM_FERTILITY);
        double yearFraction = 2 * Math.PI * elapsedTime / YEAR_LENGTH;
        double weatherFertilityRange = MAXIMUM_WEATHER_FERTILITY - MINIMUM_WEATHER_FERTILITY;
        weatherFertility = Math.max(0, MINIMUM_WEATHER_FERTILITY + weatherFertilityRange * (1 + Math.cos(yearFraction)) / 2);
    }

    private void updatePeopleDecisions() {
        for (@NotNull Person person : people) {
            person.updateDecision();
        }
    }

    private void updatePeopleUnconscious() {
        for (@NotNull Person person : Set.copyOf(people)) {
            person.updateUnconscious();
        }
    }

    private void removeDeadPeople() {
        people.removeIf(person -> !person.isAlive());
    }

    //
    public void addPerson(@NotNull Person person) {
        people.add(person);
    }
}