package dk.sdu.mmmi.cbse.restapibackendandroid;

public class Car {
    private String id;
    private String name;
    private String model;
    private String location;
    private Double price;
    private String listingDate;
    private String image;
    private String status;
    private Integer totalprice;
    private Integer days;

    public Car(String id, String name, String model, String location, Double price, String listingDate, String image, String status, Integer totalprice, Integer days){
        this.id = id;
        this.name = name;
        this.model = model;
        this.location = location;
        this.price = price;
        this.listingDate = listingDate;
        this.image = image;
        this.status = status;
        this.totalprice = totalprice;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Integer getTotalPrice(){
        return totalprice;
    }

    public Integer getDays(){
        return days;
    }

    public String getListingDate() {
        return listingDate;
    }

    public void setListingDate(String listingDate) {
        this.listingDate = listingDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
