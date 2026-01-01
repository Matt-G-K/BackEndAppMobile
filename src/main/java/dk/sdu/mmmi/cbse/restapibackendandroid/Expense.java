package dk.sdu.mmmi.cbse.restapibackendandroid;

public class Expense {
    private String expenseID;
    private String username;
    private Integer amount;
    private Integer transactionID;
    private boolean paidStatus;

    public Expense(String expenseID, String username, Integer amount, Integer transactionID, boolean paidStatus){
        this.expenseID = expenseID;
        this.username = username;
        this.amount = amount;
        this.transactionID = transactionID;
        this.paidStatus = paidStatus;
    }


    public String getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(String expenseID) {
        this.expenseID = expenseID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Integer transactionID) {
        this.transactionID = transactionID;
    }

    public boolean getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(boolean paidStatus) {
        this.paidStatus = paidStatus;
    }
}
