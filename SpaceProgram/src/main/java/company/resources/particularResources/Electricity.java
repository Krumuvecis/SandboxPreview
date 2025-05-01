package company.resources.particularResources;

import company.resources.Resource;
import org.jetbrains.annotations.NotNull;

//
public class Electricity extends Resource<@NotNull Electricity> {
    //
    public Electricity(double amount) {
        super("Electricity (J)", amount);
    }

    //
    @Override
    public @NotNull Electricity copy() {
        return new Electricity(getAmount());
    }
}