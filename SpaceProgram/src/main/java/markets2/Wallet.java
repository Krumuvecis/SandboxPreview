package markets2;

//
public class Wallet {
    private double money;

    //
    public Wallet() {
        money = 0;
    }

    //
    public final double getMoney() {
        return money;
    }

    //returns remainder, if subtracting more than available
    @SuppressWarnings("UnusedReturnValue")
    public final double addMoney(double delta) {
        double result = money + delta;
        if (result < 0) {
            money = 0;
            return -result;
        } else {
            money = result;
            return 0;
        }
    }
}