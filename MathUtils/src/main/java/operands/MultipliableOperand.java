package operands;

import org.jetbrains.annotations.NotNull;

import utils.Copyable;

//multiplication/division
interface MultipliableOperand<T extends @NotNull MultipliableOperand<T>> extends Copyable<T> {
    //multiplication with a number
    void multiply(double multiplier);

    //multiplication with a number
    default T getMultiplied(double multiplier) {
        T copy = copy();
        copy.multiply(multiplier);
        return copy;
    }
}