package markets2;

import org.jetbrains.annotations.NotNull;

import ThreadAbstraction.AbstractUpdater;

import markets2.person.Person;
import markets2.graphics.Window;

//
public class Main {
    private static final long WORLD_UPDATE_DELAY = 1000;
    private static final int STARTING_POPULATION = 2;

    //
    public static void main(String[] args) {
        @NotNull World world = getNewWorld();
        new WorldUpdater(world).start();
        new Window(world);
    }

    private static @NotNull World getNewWorld() {
        @NotNull World world = new World();
        addStartingPopulation(world);
        return world;
    }

    private static void addStartingPopulation(@NotNull World world) {
        for (int i = 0; i < STARTING_POPULATION; i ++) {
            world.addPerson(new Person(world, 0));
        }
    }

    private static final class WorldUpdater extends AbstractUpdater {
        private final @NotNull World world;

        //
        WorldUpdater(@NotNull World world) {
            super(WORLD_UPDATE_DELAY);
            this.world = world;
        }

        //
        @Override
        public void update() {
            world.update();
        }
    }
}