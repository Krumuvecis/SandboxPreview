package main;

import java.util.ArrayList;
import java.util.List;

//
public class SpaceProgram {
    SpaceProgramState
            initialState,
            endState;

    //
    public SpaceProgram(SpaceProgramState initialState, SpaceProgramState endState) {
        this.initialState = initialState;
        this.endState = endState;
    }

    public SpaceProgramState getInitialState() {
        return initialState;
    }

    public SpaceProgramState getEndState() {
        return endState;
    }

    public SpaceProgramAssets getMissingAssets() {
        return initialState.getMissingAssets(endState.getAssets());
    }

    //
    public static class SpaceProgramState {
        private final SpaceProgramAssets assets;

        //
        public SpaceProgramState(SpaceProgramAssets assets) {
            this.assets = assets;
        }

        public SpaceProgramAssets getAssets() {
            return assets;
        }

        public SpaceProgramAssets getMissingAssets(SpaceProgramAssets targetAssets) {
            return assets.getMissingAssets(targetAssets);
        }
    }

    //
    public static class SpaceProgramAssets {
        private final List<SurfaceConstruction> surfaceConstructions;
        private final List<OrbitalStation> orbitalStations;
        private final List<Spaceship> spaceships;

        //
        public SpaceProgramAssets(List<SurfaceConstruction> surfaceConstructions,
                                  List<OrbitalStation> orbitalStations,
                                  List<Spaceship> spaceships) {
            this.surfaceConstructions = surfaceConstructions;
            this.orbitalStations = orbitalStations;
            this.spaceships = spaceships;
        }

        public SpaceProgramAssets getMissingAssets(SpaceProgramAssets targetAssets) {
            //check all surface constructions
            List<SurfaceConstruction> missingSurfaceConstructions = new ArrayList<>();
            for (SurfaceConstruction surfaceConstruction : targetAssets.surfaceConstructions) {
                if (!surfaceConstructions.contains(surfaceConstruction)) {
                    missingSurfaceConstructions.add(surfaceConstruction);
                }
            }

            //check all orbital stations
            List<OrbitalStation> missingOrbitalStations = new ArrayList<>();
            for (OrbitalStation orbitalStation : targetAssets.orbitalStations) {
                if (!orbitalStations.contains(orbitalStation)) {
                    missingOrbitalStations.add(orbitalStation);
                }
            }

            //check all spaceships
            List<Spaceship> missingSpaceships = new ArrayList<>();
            for (Spaceship spaceship : targetAssets.spaceships) {
                if (!spaceships.contains(spaceship)) {
                    missingSpaceships.add(spaceship);
                }
            }

            return new SpaceProgramAssets(missingSurfaceConstructions, missingOrbitalStations, missingSpaceships);
        }
    }
}