package Controller.Messages;

public class RequestMessage implements Message{

    private String fulfiller;
    private String amount;

    public RequestMessage (String fulfiller, String amount){
        this.fulfiller = fulfiller;
        this.amount = amount;
    }

    public String getFulfiller() {
        return fulfiller;
    }

    public String getAmount() {
        return amount;
    }
}
