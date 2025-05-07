package markets;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ThreadAbstraction.AbstractUpdater;

import chemistry.molecules.Molecule;

//
public class Markets {
    //money-certificate, piece of paper, no other use
    public static class Money {}

    //buys/sells single thing
    public static class MoleculeMarket {
        private final @NotNull Molecule molecule;

        //
        public MoleculeMarket(@NotNull Molecule molecule) {
            this.molecule = molecule;
        }

        //
        public final @NotNull Molecule getMolecule() {
            return molecule;
        }
    }

    //contains many single-thing-markets
    public static class MarketPlace {
        private final @NotNull Map<@NotNull Molecule, @NotNull MoleculeMarket> marketMap;

        //
        public MarketPlace() {
            marketMap = new HashMap<>();
        }

        //
        public final @NotNull Set<@NotNull Molecule> getTradableMolecules() {
            return marketMap.keySet();
        }

        //
        public final @NotNull Set<@NotNull MoleculeMarket> getAllMarkets() {
            return Set.copyOf(marketMap.values());
        }

        //
        public final @Nullable MoleculeMarket getMarket(@NotNull Molecule molecule) {
            return marketMap.get(molecule);
        }

        //
        public final void update() {
            //
        }

        //
        public final void addMarket(@NotNull MoleculeMarket market) {
            @NotNull Molecule molecule = market.getMolecule();
            if (getTradableMolecules().contains(molecule)) {
                //market already defined!
            } else {
                marketMap.put(molecule, market);
            }
        }
    }

    //
    public static class MarketPlaceIterator extends AbstractUpdater {
        private final @NotNull MarketPlace marketPlace;

        //delay in ms
        public MarketPlaceIterator(@NotNull MarketPlace marketPlace, long delay) {
            super(delay);
            this.marketPlace = marketPlace;
        }

        //
        @Override
        public final void update() {
            marketPlace.update();
        }
    }
}