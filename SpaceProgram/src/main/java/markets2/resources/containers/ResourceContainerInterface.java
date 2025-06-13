package markets2.resources.containers;

import markets2.resources.ResourceCollection;

//
public interface ResourceContainerInterface extends ResourceCollection, LimitedMassInterface, LimitedVolumeInterface {
    //
    @Override
    default double getTakenMass() {
        return getTotalMass();
    }

    //
    @Override
    default double getTakenVolume() {
        return getTotalVolume();
    }
}