package rotors.rotatingHabitat.modularHabitat.habitatSection;

//
public final class EvenException extends Exception {
    //
    public EvenException() {
        super("Even-width sections not supported, unable to create a section");
    }

    //
    public static void checkEvenness(int number) {
        if (EvenException.isEven(number)) {
            throw new RuntimeException(new EvenException());
        }
    }

    private static boolean isEven(int number) {
        return (number % 2) == 0;
    }
}