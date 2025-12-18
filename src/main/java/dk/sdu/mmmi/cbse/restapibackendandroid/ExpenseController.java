package dk.sdu.mmmi.cbse.restapibackendandroid;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
public class ExpenseController {

    private ArrayList<Expense> Expenses = new ArrayList<>();

    @PostMapping("api/createexpense/{id}/{username}/{amount}/{transactionID}")
    public String createExpense(@PathVariable String id, @PathVariable String username,
                                @PathVariable Integer amount, @PathVariable Integer transactionID) {
        System.out.println("Trying to create expense with id: "+id);
        Expense newExpense = new Expense(id, username, amount, transactionID);
        Expenses.add(newExpense);
        return "Expense created with id: "+id;
    }

    @GetMapping("api/expense/{id}")
    public String getExpense(@PathVariable String id) {
        for(Expense expense: Expenses){
            if(expense.getExpenseID().equals(id)) {
                return expense.toString();
            } else {
                System.out.println("Not this expense "+expense.getExpenseID());
            }
        }
        return "Error";
    }

    @PutMapping("api/expense/{id}/changeamount/{amount}")
    public String changeAmount(@PathVariable String id, @PathVariable Integer amount){
        for(Expense expense: Expenses){
            if(expense.getExpenseID().equals(id)) {
                expense.setAmount(amount);
                return "Amount changed to: "+amount+" for expense: "+id;
            } else {
                System.out.println("Not this expense "+expense.getExpenseID());
            }
        }
        return "Error";
    }

    @PutMapping("api/expense/{id}/changeuser/{username}")
    public String changeUser(@PathVariable String id, @PathVariable String username){
        for(Expense expense: Expenses){
            if(expense.getExpenseID().equals(id)) {
                expense.setUsername(username);
                return "Username changed to: "+username+" for expense: "+id;
            } else {
                System.out.println("Not this expense "+expense.getExpenseID());
            }
        }
        return "Error";
    }



}
