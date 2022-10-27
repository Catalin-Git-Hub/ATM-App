public class Card {
    private String cardNumber;
    private String cvv;
    private String bankName;
    private String pin;
    private Account currentAccount;

    public Card(String cardNumber, String cvv, String bankName, String pin, Account currentAccount) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.bankName = bankName;
        this.pin = pin;
        this.currentAccount = currentAccount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cvv='" + cvv + '\'' +
                ", bankName='" + bankName + '\'' +
                ", pin='" + pin + '\'' +
                ", currentAccount=" + currentAccount +
                '}';
    }
}
