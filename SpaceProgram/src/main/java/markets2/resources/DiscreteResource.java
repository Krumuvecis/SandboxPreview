package markets2.resources;

import org.jetbrains.annotations.NotNull;

//
public class DiscreteResource extends AbstractResource {
    private final double size; //single-item size

    //custom size
    DiscreteResource(@NotNull String name, double size) {
        super(name);
        if (size < 0) {
            throw new RuntimeException("Discrete resource (" + name + ") initialization exception: negative size not supported.");
        } else {
            this.size = size;
        }
    }

    //size = 0; TODO: intended for deeds, other papers, etc. (not money though, as money is non-discrete)
    @SuppressWarnings("unused")
    DiscreteResource(@NotNull String name) {
        this(name, 0);
    }

    //single-item size
    public final double getSize() {
        return size;
    }
}