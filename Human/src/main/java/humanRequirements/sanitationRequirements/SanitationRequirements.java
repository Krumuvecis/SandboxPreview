package humanRequirements.sanitationRequirements;

//
@SuppressWarnings("ClassCanBeRecord")
public class SanitationRequirements implements SanitationRequirementInterface {
    private final double showerRate; //showers per second

    //
    public SanitationRequirements(double showerRate) {
        this.showerRate = showerRate;
    }

    //showers per second
    @Override
    public double getShowerRate() {
        return showerRate;
    }
}