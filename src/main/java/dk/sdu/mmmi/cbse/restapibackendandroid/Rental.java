package dk.sdu.mmmi.cbse.restapibackendandroid;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rental {
    private final Map<String,List<Rental>> rentalsByCar = new HashMap<>();

    //Model
    public static class RentalModel {
        private String carId;
        private String user;
        private OffsetDateTime rentalStartDate;
        private OffsetDateTime returnDate;
        // Snapshot of time when rental was created
        private String imageUrl;
        private String name;
        private String model;


        public RentalModel( String carId, String user, OffsetDateTime rentalStartDate, OffsetDateTime returnDate, String imageUrl, String name, String model) {
            this.carId = carId;
            this.user = user;
            this.rentalStartDate = rentalStartDate;
            this.returnDate = returnDate;
            this.imageUrl = imageUrl;
            this.name = name;
            this.model = model;
        }

        
        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }


        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public OffsetDateTime getRentalDate() {
            return rentalStartDate;
        }

        public void setRentalDate(OffsetDateTime rentalStartDate) {
            this.rentalStartDate = rentalStartDate;
        }

        public OffsetDateTime getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(OffsetDateTime returnDate) {
            this.returnDate = returnDate;
        }

        public String getImageUrl() {
            return imageUrl;
        }
        
        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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
    }
}
