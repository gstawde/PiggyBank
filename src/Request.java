
public class Request implements Comparable<Request>{
    Double  amount;
    User requester;
    User fulfiller;

    public Request(double amount, User requester, User fulfiller){
        this.amount    =    amount;
        this.requester = requester;
        this.fulfiller = fulfiller;
    }

    public double getAmount() {
        return amount;
    }

    public User getRequester() {
        return requester;
    }

    public User getFulfiller() {
        return fulfiller;
    }

    public int compareTo(Request r) {
        if(!this.requester.equals(r.requester)) {
            return this.requester.compareTo(r.requester);
        }else if(!this.fulfiller.equals(r.fulfiller)) {
            return this.fulfiller.compareTo(r.fulfiller);
        }else{
            return (int)Math.signum(this.amount.compareTo(r.amount));
        }
    }

    @Override
    public boolean equals(Object o){
        return compareTo((Request)o) == 0;
    }
}
