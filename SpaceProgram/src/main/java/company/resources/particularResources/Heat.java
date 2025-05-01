package company.resources.particularResources;

import company.resources.Resource;
import org.jetbrains.annotations.NotNull;

//
public class Heat extends Resource<@NotNull Heat> {
    //
    public Heat(double amount) {
        super("Heat (J)", amount);
    }

    //
    @Override
    public @NotNull Heat copy() {
        return new Heat(getAmount());
    }
}