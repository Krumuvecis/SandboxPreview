package markets2.resources.containers;

//
interface LimitedMassInterface {
    //
    double getTakenMass();

    //
    double getLimit_mass();

    //
    default double getRemaining_mass() {
        return getLimit_mass() - getTakenMass();
    }

    //
    default double getFullness_mass() {
        return getTakenMass() / getLimit_mass();
    }
}