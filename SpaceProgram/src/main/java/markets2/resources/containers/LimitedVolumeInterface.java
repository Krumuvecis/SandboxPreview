package markets2.resources.containers;

//
public interface LimitedVolumeInterface {
    //
    double getTakenVolume();

    //
    double getLimit_volume();

    //
    default double getRemaining_volume() {
        return getLimit_volume() - getTakenVolume();
    }

    //
    default double getFullness_volume() {
        return getTakenVolume() / getLimit_volume();
    }
}