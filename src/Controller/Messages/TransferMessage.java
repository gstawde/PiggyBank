package Controller.Messages;


public class TransferMessage implements Message{
    private String recipient;
    private String amount;

    public TransferMessage(String recipient, String amount){
        this.recipient = recipient;
        this.amount = amount;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getAmount() {
        return amount;
    }
}
