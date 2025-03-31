package operands;

import org.jetbrains.annotations.NotNull;

//
public interface Operable<T extends @NotNull Operable<T>> extends SummableOperand<T>, MultipliableOperand<T> {}