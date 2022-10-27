public class MoneyConverterUtils {
    public static long convertToBani(double amountRon){
        return (long) (amountRon * 100);
    }

    public static double convertToRon(long amountInBani){
        return (amountInBani / 100.0);
    }
}
