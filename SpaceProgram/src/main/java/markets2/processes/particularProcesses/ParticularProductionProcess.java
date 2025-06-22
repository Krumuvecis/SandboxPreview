package markets2.processes.particularProcesses;

import org.jetbrains.annotations.NotNull;

import markets2.resources.ResourceSet;
import markets2.processes.AbstractProcess;
import markets2.processes.ProductionProcess;

//
final class ParticularProductionProcess extends AbstractProcess implements ProductionProcess {
    private final @NotNull ResourceSet products;

    //
    ParticularProductionProcess(@NotNull String name, double duration, @NotNull ResourceSet products) {
        super(name, duration);
        this.products = products;
    }

    //
    @Override
    public @NotNull ResourceSet getProducts() {
        return products;
    }
}