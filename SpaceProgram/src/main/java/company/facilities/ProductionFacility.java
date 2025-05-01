package company.facilities;

import java.util.List;
import java.util.ArrayList;

import company.resources.particularResources.Electricity;
import company.resources.particularResources.Heat;
import company.resources.particularResources.Money;
import org.jetbrains.annotations.NotNull;

import dimensions.time.TimeUnit;
import dimensions.time.Time;
import company.resources.*;

//
public interface ProductionFacility {
    //
    @NotNull Time getOperationalTime();

    //
    @NotNull List<@NotNull Resource<?>> getOperationalCosts();

    //
    @NotNull List<@NotNull Resource<?>> getOperationalProducts();


    //TODO: some ideas below:

    //setup,

    //turn-on costs
    //  checking all the systems and proper turning on

    //run costs
    //  even at 0% it costs some power and produces some waste just to keep it on

    //TODO: aha batch or continuous

    //batch costs and output
    //  continuous feed in: resources + power + etc.
    //  continuous out: product + waste (waste materials + waste heat + etc.)

    //turn-off costs
    //  proper shutting off, waste disposal, etc

    //turnaround

    //
    final class Factory extends SimpleFacility implements ProductionFacility {
        private static final double
                SETUP_PRICE = 100000,
                RESELL_VALUE_RATIO = 0.1;
        private static final @NotNull Time OPERATIONAL_TIME = new Time(1, TimeUnit.MONTH);
        private static final double WORKER_SALARY = 1500;
        private static final int WORKER_COUNT = 10;
        private static final double
                THROUGHPUT_MASS = 100,
                ELECTRICITY_PER_MASS = 10,
                MASS_EFFICIENCY = 0.9,
                ELECTRICAL_EFFICIENCY = 0.7;
        private static final @NotNull List<@NotNull Resource<?>>
                OPERATIONAL_COSTS = new ArrayList<>() {{
                    add(new Money(WORKER_SALARY * WORKER_COUNT)); //wages
                    add(new Materials.Iron(THROUGHPUT_MASS)); //feed
                    add(new Electricity(THROUGHPUT_MASS * ELECTRICITY_PER_MASS)); //power
                }},
                OPERATIONAL_PRODUCTS = new ArrayList<>() {{
                    add(new Materials.Steel(THROUGHPUT_MASS * MASS_EFFICIENCY)); //product
                    add(new Materials.Slag(THROUGHPUT_MASS * (1 - MASS_EFFICIENCY))); //waste material
                    add(new Heat(THROUGHPUT_MASS * ELECTRICITY_PER_MASS * (1 - ELECTRICAL_EFFICIENCY))); //waste heat
                }};

        //
        public Factory() {
            super("Factory", SETUP_PRICE, RESELL_VALUE_RATIO);
        }

        //
        @Override
        public @NotNull Time getOperationalTime() {
            return OPERATIONAL_TIME;
        }

        //
        @Override
        public @NotNull List<@NotNull Resource<?>> getOperationalCosts() {
            return OPERATIONAL_COSTS;
        }

        //
        @Override
        public @NotNull List<@NotNull Resource<?>> getOperationalProducts() {
            return OPERATIONAL_PRODUCTS;
        }
    }
}