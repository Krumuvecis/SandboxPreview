package markets2.processes;

import org.jetbrains.annotations.NotNull;

import markets2.resources.ResourceSet;

//
public interface ProductionProcess extends ProcessInterface {
    //
    @NotNull ResourceSet getProducts();
}