package dk.sdu.mmmi.cbse.restapibackendandroid;

import dk.sdu.mmmi.cbse.restapibackendandroid.Car;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
public class CarController {

    private List<Car> Cars =  Arrays.asList(
            new Car("1",
                    "Car1",
                    "BMW",
                    "Copenhagen",
                    "1000",
                    "01-01-2025",
                    "http://10.0.2.2:8080/images/car1.png",
                    "Available"),
            new Car("2",
                    "Car2",
                    "Ferrari",
                    "Odense",
                    "1500",
                    "01-02-2025",
                    "http://10.0.2.2:8080/images/car2.png",
                    "Rented"),
            new Car("3",
                    "Car3",
                    "Volkswagen",
                    "Aarhus",
                    "800",
                    "03-01-2025",
                    "http://10.0.2.2:8080/images/car3.png",
                    "Available"),
            new Car("4",
                    "Car4",
                    "Citroen",
                    "Nyborg",
                    "1000",
                    "01-01-2025",
                    "http://10.0.2.2:8080/images/car1.png",
                    "Available"),
            new Car("5",
                    "Car5",
                    "BMW",
                    "Odense",
                    "1100",
                    "07-04-2025",
                    "http://10.0.2.2:8080/images/car2.png",
                    "Available"),
            new Car("6",
                    "Car6",
                    "Renault",
                    "Copenhagen",
                    "1200",
                    "01-06-2025",
                    "http://10.0.2.2:8080/images/car3.png",
                    "Available"),
            new Car("7",
                    "Car7",
                    "BMW",
                    "Copenhagen",
                    "1000",
                    "01-01-2025",
                    "http://10.0.2.2:8080/images/car1.png",
                    "Available"),
            new Car("8",
                    "Car8",
                    "BMW",
                    "Copenhagen",
                    "1000",
                    "01-01-2025",
                    "http://10.0.2.2:8080/images/car2.png",
                    "Available"));

    @GetMapping("/api/cars")
    public List<Car> getCars() {
        return Cars;
    }

    @GetMapping("/api/cars/available")
    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<Car>();
        for (Car car : Cars) {
            String status = car.getStatus();
            if (status.equals("Available")) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    @PutMapping("/api/rent/{id}")
    public String rentCar(@PathVariable int id) {
        System.out.println("got request with id: "+id);
        for (Car car: Cars) {
            System.out.println("Scanning cars");
            if (Objects.equals(car.getId(), String.valueOf(id))) {
                if (Objects.equals(car.getStatus(), "Available")) {
                    System.out.println("Trying to rent car");
                    car.setStatus("Rented");
                    return "Car with id: "+id+" is rented";
                } else {
                    System.out.println("Car not available");
                    return "Car with id: "+id+" is not available";
                }
            } else {
                System.out.println("Not car: "+car.getId());
            }
        }
        return "Error";
    }
}