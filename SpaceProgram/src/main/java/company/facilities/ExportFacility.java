package company.facilities;

import org.jetbrains.annotations.NotNull;

import company.resources.Resource;

//
public interface ExportFacility {
    //
    double getPrice(@NotNull Resource<?> resource);

    //
    final class ExportCenter extends SimpleFacility implements ExportFacility {
        private static final double
                SETUP_PRICE = 20000,
                RESELL_VALUE_RATIO = 0;

        //
        public ExportCenter() {
            super("Export Center", SETUP_PRICE, RESELL_VALUE_RATIO);
        }

        //
        @Override
        public double getPrice(@NotNull Resource<?> resource) {
            //TODO: finish this
            return 2;
        }
    }
}