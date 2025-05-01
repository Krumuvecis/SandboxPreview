package company.facilities;

import org.jetbrains.annotations.NotNull;

import company.resources.Resource;

//
public interface ImportFacility {
    double getPrice(@NotNull Resource<?> resource);

    //
    final class ImportCenter extends SimpleFacility implements ImportFacility {
        private static final double
                SETUP_PRICE = 20000,
                RESELL_VALUE_RATIO = 0;

        //
        public ImportCenter() {
            super("Import Center", SETUP_PRICE, RESELL_VALUE_RATIO);
        }

        //
        @Override
        public double getPrice(@NotNull Resource<?> resource) {
            //TODO: finish this
            return 1;
        }
    }
}