package dk.sdu.mmmi.cbse.restapibackendandroid;

public class Account {

    private Integer id;
    private String accountName;
    private Integer regNum;
    private Integer accountNumber;

    public Account(Integer id, String accountName, Integer regNum, Integer accountNumber) {
        this.id = id;
        this.accountName = accountName;
        this.regNum = regNum;
        this.accountNumber = accountNumber;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getRegNum() {
        return regNum;
    }

    public void setRegNum(Integer regNum) {
        this.regNum = regNum;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }
}
