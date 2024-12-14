import java.io.Serializable;

public class ExchangeCalculatorBean implements Serializable {
    private double rate1;
    private double rate2;
    private double rate3;

    public double getRate1() { return rate1; }
    public void setRate1(double rate1) { this.rate1 = rate1; }

    public double getRate2() { return rate2; }
    public void setRate2(double rate2) { this.rate2 = rate2; }

    public double getRate3() { return rate3; }
    public void setRate3(double rate3) { this.rate3 = rate3; }

    public double calculate(double amount, int currency) {
        switch (currency) {
            case 1: return amount * rate1;
            case 2: return amount * rate2;
            case 3: return amount * rate3;
            default: return 0;
        }
    }
}
