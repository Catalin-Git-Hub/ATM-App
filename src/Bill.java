public class Bill {
    private String billNumber;
    private String receiverName;
    private long amount;

    public Bill(String billNumber, String receiverName, long amount) {
        this.billNumber = billNumber;
        this.receiverName = receiverName;
        this.amount = amount;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billNumber='" + billNumber + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", amount='" + MoneyConverterUtils.convertToRon(amount)  + "Ron." +
                '}';
    }
}
