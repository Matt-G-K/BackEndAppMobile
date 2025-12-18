package dk.sdu.mmmi.cbse.restapibackendandroid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
public class ExpenseController {

    private ArrayList<Expense> Expenses = new ArrayList<>();



}
