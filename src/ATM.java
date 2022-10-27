import java.util.Scanner;

public class ATM {
    private String bankName;
    private double fee;
    private long availableAmountOfMoney;
    private Card currentCard;
    int nrOfTries = 3;

    Scanner scannerText = new Scanner(System.in);
    Scanner scannerNumbers = new Scanner(System.in);

    public ATM(String bankName, double fee, long availableAmountOfMoney) {
        this.bankName = bankName;
        this.fee = fee;
        this.availableAmountOfMoney = availableAmountOfMoney;
    }

    public void insertCard(Card insertedCard) {
        System.out.println("Welcome to " + bankName + "!");
        String pin;
        do {
            System.out.print("Enter your pin: ");
            pin = scannerText.nextLine();
            nrOfTries--;
            if (!pin.equals(insertedCard.getPin()) && nrOfTries == 0) {
                System.out.println("You have " + nrOfTries + " left tries!");
                System.out.println("Your card has been blocked!");
                return;
            } else if (!pin.equals(insertedCard.getPin())) {
                System.out.println("Your pin is wrong! Try again!");
                System.out.println("You have " + nrOfTries + " left tries!");
            }
        } while (!pin.equals(insertedCard.getPin()) && nrOfTries > 0);
        currentCard = insertedCard;
    }

    public void startProcessing() {
        byte selectedOption;
        String userAnswer;
        do {
            showMenu();
            System.out.println("Choose an option:");
            selectedOption = scannerNumbers.nextByte();
            switch (selectedOption) {
                case 1:
                    changePin();
                    break;
                case 2:
                    cashWithdraw();
                    break;
                case 3:
                    cashDeposit();
                    break;
                case 4:
                    showCurrentBalance();
                    break;
                case 5:
                    payBill();
                    break;
                case 6:
                    exit();
                    break;
                default:
                    System.out.println("Invalid option was chosen! Please choose a valide option!");

            }
            do {
                System.out.print("Do you want to choose another option?(Y/N): ");
                userAnswer = scannerText.nextLine();
                if (!userAnswer.equalsIgnoreCase("n") && !userAnswer.equalsIgnoreCase("y")){
                    System.out.println("Enter either y or n!");
                }
            }while (!userAnswer.equalsIgnoreCase("n") && !userAnswer.equalsIgnoreCase("y"));

            if (userAnswer.equalsIgnoreCase("n")){
                exit();
                return;
            }

        } while (selectedOption != 6 || userAnswer.equalsIgnoreCase("Y"));
    }

    // Change pin

    public void changePin() {
        String oldPin;
        do {
            System.out.print("Insert your old PIN number: ");
            oldPin = scannerText.nextLine();
            if (!oldPin.equals(currentCard.getPin())) {
                System.out.println("Your PIN number is wrong, please try again.");
            }
        } while (!oldPin.equals(currentCard.getPin()));

        String newPin;
        do {
            System.out.println("Insert your new PIN number: ");
            newPin = scannerNumbers.nextLine();

            if (newPin.length() < 4 || newPin.length() > 6) {
                System.out.println("Your PIN number is too short or too long.");
            }
        } while (newPin.length() < 4 || newPin.length() > 6);
        currentCard.setPin(newPin);
        System.out.println("Your PIN has been updated.");
    }

    public void showMoneyOptions() {
        System.out.println("________________");
        System.out.println("1. 10 RON");
        System.out.println("2. 50 RON");
        System.out.println("3. 100 RON");
        System.out.println("4. 200 RON");
        System.out.println("5. 500 RON");
        System.out.println("6. Other amount");
        System.out.println("7. Exit");
        System.out.println("________________");
    }

    public int moneyOperations(byte option) {
        int selectedAmount = 0;
        switch (option) {
            case 1:
                selectedAmount = 10;
                break;
            case 2:
                selectedAmount = 50;
                break;
            case 3:
                selectedAmount = 100;
                break;
            case 4:
                selectedAmount = 200;
                break;
            case 5:
                selectedAmount = 500;
                break;
            case 6:
                System.out.print("Please enter amount: ");
                selectedAmount = scannerNumbers.nextInt();
                break;
            case 7:
                break;
            default:
                System.out.println("Chose a valid option!");
        }
        return selectedAmount;
    }

    public void cashWithdraw() {
        showMoneyOptions();
        System.out.print("Chose an option: ");
        byte selectedOption = scannerText.nextByte();
        double selectedAmount = moneyOperations(selectedOption);
        if (selectedAmount == 0) {
            return;
        }
        double selectedAmountWithFee = selectedAmount;
        if (!currentCard.getBankName().equals(bankName)) {
            selectedAmountWithFee = selectedAmountWithFee + selectedAmountWithFee * (fee / 100);
        }

        if (availableAmountOfMoney < MoneyConverterUtils.convertToBani(selectedAmount)) {
            System.out.println("Not enough funds in the ATM");
            return;

        } else if (currentCard.getCurrentAccount().getAvailableAmount() >= MoneyConverterUtils.convertToBani(selectedAmountWithFee)) {
            long calculatedAmount = currentCard.getCurrentAccount().getAvailableAmount() - MoneyConverterUtils.convertToBani(selectedAmountWithFee);
            currentCard.getCurrentAccount().setAvailableAmount(calculatedAmount);
            System.out.println("You have " + MoneyConverterUtils.convertToRon(calculatedAmount) + " Ron remaining.");
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void cashDeposit() {
        showMoneyOptions();
        showMoneyOptions();
        System.out.print("Choose an option: ");
        byte selectedOption = scannerText.nextByte();
        int selectedAmount = moneyOperations(selectedOption);
        if (selectedOption == 0) {
            return;
        }
        long newAvailableAmount = currentCard.getCurrentAccount().getAvailableAmount() + MoneyConverterUtils.convertToBani(selectedAmount);
        currentCard.getCurrentAccount().setAvailableAmount(newAvailableAmount);
        System.out.println("Accepted. Your new balance is: " + MoneyConverterUtils.convertToRon(newAvailableAmount));
    }

    public void showCurrentBalance() {
        double availableAmount = MoneyConverterUtils.convertToRon(currentCard.getCurrentAccount().getAvailableAmount());
        System.out.println("Your current balance is: " + availableAmount + "RON.");
    }

    public void exit() {
        System.out.println("Thank you for choosing our ATM!");
        currentCard = null;
    }

    public void payBill() {
        System.out.print("Enter bill number: ");
        String billNumber = scannerText.nextLine();
        System.out.print("Enter receiver name: ");
        String receiverName = scannerText.nextLine();
        System.out.print("Enter amount: ");
        double amount = scannerNumbers.nextDouble();
        Bill bill = new Bill(billNumber, receiverName, MoneyConverterUtils.convertToBani(amount));

        if (currentCard.getCurrentAccount().getAvailableAmount() >= bill.getAmount()) {
        long newBalance = currentCard.getCurrentAccount().getAvailableAmount() - bill.getAmount();
        currentCard.getCurrentAccount().setAvailableAmount(newBalance);
        System.out.println("Bill paid.");
        System.out.println("Your current balance is: " + MoneyConverterUtils.convertToRon(newBalance) + " Ron.");

    }else

    {
        System.out.println("Not enough money");
    }

}

    public void showMenu() {
        System.out.println();
        System.out.println(" ===== MENU =====");
        System.out.println("1.Schimbare pin.");
        System.out.println("2.Retragere numerar.");
        System.out.println("3.Alimentare cont.");
        System.out.println("4.Interogare sold.");
        System.out.println("5.Plata factura.");
        System.out.println("6.Exit.");
        System.out.println();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public long getAvailableAmountOfMoney() {
        return availableAmountOfMoney;
    }

    public void setAvailableAmountOfMoney(long availableAmountOfMoney) {
        this.availableAmountOfMoney = availableAmountOfMoney;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    @Override
    public String toString() {
        return "ATM{" +
                "bankName='" + bankName + '\'' +
                ", fee=" + fee +
                ", availableAmountOfMoney=" + availableAmountOfMoney +
                ", currentCard=" + currentCard +
                '}';
    }
}