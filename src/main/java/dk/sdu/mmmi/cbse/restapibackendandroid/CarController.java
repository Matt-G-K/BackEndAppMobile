package dk.sdu.mmmi.cbse.restapibackendandroid;

import dk.sdu.mmmi.cbse.restapibackendandroid.Car;
import dk.sdu.mmmi.cbse.restapibackendandroid.Rental.RentalModel;

import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@CrossOrigin(origins = "*")
public class CarController {

    private final String ip = "192.168.1.96";

    private final UserController userController;

    public CarController(UserController userController) {
        this.userController = userController;
    }

    private List<Car> Cars = Arrays.asList(
            new Car("1",
                    "1-series",
                    "BMW",
                    "Copenhagen",
                    30.00,
                    "01-01-2025",
                    "http://"+ip+":8080/images/BMW 1-series.png",
                    "Available",
                    800,
                    3),
            new Car("2",
                    "Corsa",
                    "Opel",
                    "Odense",
                    35.00,
                    "01-02-2025",
                    "http://"+ip+":8080/images/opel corsa.png",
                    "Available",
                    400,
                    2),
            new Car("3",
                    "Picanto",
                    "Kia",
                    "Nyborg",
                    25.00,
                    "01-01-2025",
                    "http://"+ip+":8080/images/kia picanto.png",
                    "Available",
                    1500,
                    10));


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

    // Rental helper methods

    // List of rentals period by car id
    private final Map<String, List<Rental.RentalModel>> rentalsByCar = new ConcurrentHashMap<>();

    private Optional<Car> findCarById(String carId) {
        return Cars.stream().filter(car -> car.getId().equals(carId)).findFirst();
    }

    private static OffsetDateTime nowUTC() {
        return OffsetDateTime.now();
    }

    private RentalModel getActive(String carId) {
        List<Rental.RentalModel> rentals = rentalsByCar.getOrDefault(carId, List.of());
        for (int i = rentals.size() - 1; i >= 0; i--) {

            if (rentals.get(i).getReturnDate() == null) {
                return rentals.get(i);
            }

        }
        return null;
    }

    // Rental endpoints

    // Rent a car by id
    @PutMapping("/api/rent/{id}")
    public String rentCar(@PathVariable int id) {
        System.out.println("got request with id: " + id);

        Car car = findCarById((String.valueOf(id))).orElse(null);
        if (car == null)
            return "Car with id: " + id + " not found";
        if (!"Available".equals(car.getStatus()) || getActive(String.valueOf(id)) != null)
            return "Car with id: " + id + " is not available";
        RentalModel rental = new RentalModel(String.valueOf(id), null, nowUTC(), null, car.getImage(), car.getName(), car.getModel(), car.getTotalPrice(), car.getDays());
        rentalsByCar.computeIfAbsent(String.valueOf(id), k -> new CopyOnWriteArrayList<>())
            .add(rental);
        car.setStatus("Rented");
        System.out.println("Car with id: " + id + " rented");
        return "Car with id: " + id + " rented";
    }

    // Return a car by id
    @PutMapping("/api/return/{id}")
    public Map<String, Object> endRental(@PathVariable String id) {
        Map<String, Object> resp = new LinkedHashMap<>();
        Car car = findCarById(id).orElse(null);
        if (car == null) {
            resp.put("message", "Car not found");
            return resp;
        }
        RentalModel active = getActive(String.valueOf(id));
        if (active == null) {
            resp.put("message", "No active rental");
            return resp;
        }
        active.setReturnDate(nowUTC());
        car.setStatus("Available");
        resp.put("message", "Car returned");
        resp.put("startAt", active.getRentalDate());
        resp.put("endAt", active.getReturnDate());
        return resp;
    }

    // List all rentals period for a car by id
    @GetMapping("/api/cars/{id}/rentals")
    public List<RentalModel> listRentals(@PathVariable String id) {
        List<RentalModel> list = new ArrayList<>(rentalsByCar.getOrDefault(id, List.of()));
        list.sort(Comparator.comparing(RentalModel::getRentalDate).reversed());
        return list;
    }

    // User rent car by id
    @PutMapping("/api/rent/{id}/{username}")
    public String rentCarByUser(@PathVariable int id, @PathVariable String username) {
        Car car = findCarById((String.valueOf(id))).orElse(null);
        if (car == null)
            return "Car with id: " + id + " not found";
        if (!"Available".equals(car.getStatus()) || getActive(String.valueOf(id)) != null)
            return "Car with id: " + id + " is not available";
        RentalModel rental = new RentalModel(String.valueOf(id), username, nowUTC(), null, car.getImage(), car.getName(), car.getModel(),car.getTotalPrice(),car.getDays());
        
            rentalsByCar.computeIfAbsent(String.valueOf(id), k -> new CopyOnWriteArrayList<>())
                .add(rental);
        car.setStatus("Rented");
        System.out.println("Car with id: " + id + " rented by user: " + username);
        return "Car with id: " + id + " rented by user: " + username;
    }

    @GetMapping("/api/users/{username}/rentals")
    public List<Rental.RentalModel> getUserRentals(@PathVariable String username) {
        List<Rental.RentalModel> out = new ArrayList<>();
        rentalsByCar.values().forEach(list -> {
            for (Rental.RentalModel r : list)
                if (username.equals(r.getUser()))
                    out.add(r);
        });
        out.sort(Comparator.comparing(Rental.RentalModel::getRentalDate).reversed());
        return out;
    }
}