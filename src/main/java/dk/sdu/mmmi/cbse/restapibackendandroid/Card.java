package dk.sdu.mmmi.cbse.restapibackendandroid;

public class Card {

    private Integer id;
    private Integer cardNumber;
    private Integer expiryDate;

    public Card(Integer id, Integer cardNumber, Integer expiryDate) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Integer expiryDate) {
        this.expiryDate = expiryDate;
    }
}
