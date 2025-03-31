package operands;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import utils.Copyable;

//addition/subtraction
interface SummableOperand<T extends @NotNull SummableOperand<T>> extends Copyable<T> {
    //full
    void sum(T addend);

    //full
    default T getSum(T addend) {
        T copy = copy();
        copy.sum(addend);
        return copy;
    }

    //full, with a list (adds list to copy of self)
    default T getSum(@NotNull List<T> addendList) {
        T copy = copy();
        for (T addend : addendList) {
            copy.sum(addend);
        }
        return copy;
    }
}