import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scannerText = new Scanner(System.in);
        Scanner scannerNumbers = new Scanner(System.in);
        System.out.print("Enter bank name: ");
        String bankName = scannerText.nextLine();
        System.out.print("Enter fee: ");
        double bankFee = scannerNumbers.nextDouble();
        System.out.print("Enter ATM available amount of money: ");
        double amountOfMoneyRon = scannerNumbers.nextDouble();
        ATM ingAtm = new ATM(bankName, bankFee, MoneyConverterUtils.convertToBani(amountOfMoneyRon));

        System.out.print("Enter card number: ");
        String cardNumber = scannerText.nextLine();
        System.out.print("Enter cvv: ");
        String cvv = scannerText.nextLine();
        System.out.print("Enter bank name of a card: ");
        String cardBankName = scannerText.nextLine();
        System.out.print("Set pin: ");
        String pin = scannerText.nextLine();
        Account currentAccount = new Account("RON");
        System.out.println("Enter initial amount of money: ");
        int initialAmount = scannerNumbers.nextInt();
        currentAccount.setAvailableAmount(MoneyConverterUtils.convertToBani(initialAmount));
        Card currentCard = new Card(cardNumber, cvv, cardBankName, pin, currentAccount);

        ingAtm.insertCard(currentCard);
        if (ingAtm.getCurrentCard() != null){
            //toata logica urmatoare a ATM-ului
            ingAtm.startProcessing();
        }
    }
}
