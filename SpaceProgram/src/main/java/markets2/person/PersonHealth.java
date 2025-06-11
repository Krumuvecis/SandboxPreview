package markets2.person;

import markets2.UpdatableInterface;

//
public final class PersonHealth implements UpdatableInterface {
    private static final double DEFAULT_MAXIMUM_HEALTH = 20;
    private boolean alive = true;
    private int age = 0;
    private final double maxHealth;
    private double health;

    //custom maximum health
    PersonHealth(double maxHealth) {
        this.maxHealth = maxHealth;
        health = maxHealth;
    }

    //default maximum health
    PersonHealth() {
        this(DEFAULT_MAXIMUM_HEALTH);
    }

    //
    public boolean isAlive() {
        return alive;
    }

    //
    public int getAge() {
        return age;
    }

    //
    public double getMaxHealth() {
        return maxHealth;
    }

    //
    public double getHealth() {
        return health;
    }

    //
    public double getHealthFraction() {
        return health / maxHealth;
    }

    //
    public double getMissingHealth() {
        return Math.max(0, maxHealth - health);
    }

    //alive status must already be checked
    @Override
    public void update() {
        //TODO: suffer from injuries here

        age ++;
        deathCheck();
    }

    private void deathCheck() {
        if (health <= 0) {
            alive = false;
        }

        //TODO: perhaps check max age?
    }

    //adds/subtracts health; returns remainder after max-health/health is checked
    @SuppressWarnings("UnusedReturnValue")
    public double addHealth(double delta) {
        double cappedDelta = 0;
        if (delta > 0) { //adds health
            cappedDelta = Math.min(delta, maxHealth);
        } else if (delta < 0) { //reduces health
            cappedDelta = Math.max(delta, -health);
        }
        this.health += cappedDelta;
        return delta - cappedDelta;
    }
}