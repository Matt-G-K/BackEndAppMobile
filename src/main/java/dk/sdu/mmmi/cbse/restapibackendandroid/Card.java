package dk.sdu.mmmi.cbse.restapibackendandroid;

import java.util.UUID;

public class Card {

    private String id;
    private String cardNumber;
    private Integer expiryDate;

    public Card( String cardNumber, Integer expiryDate) {
        this.id = UUID.randomUUID().toString();
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Integer expiryDate) {
        this.expiryDate = expiryDate;
    }
}
