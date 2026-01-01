package dk.sdu.mmmi.cbse.restapibackendandroid;

import org.springframework.web.bind.annotation.*;

import dk.sdu.mmmi.cbse.restapibackendandroid.service.NotificationService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
public class ExpenseController {

    private ArrayList<Expense> Expenses = new ArrayList<>();
    private final NotificationService notificationService;
    public ExpenseController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("api/createexpense/{id}/{username}/{amount}/{transactionID}")
    public String createExpense(@PathVariable String id, @PathVariable String username,
                                @PathVariable Integer amount, @PathVariable Integer transactionID) {
        System.out.println("Trying to create expense with id: "+id);
        Expense newExpense = new Expense(id, username, amount, transactionID, false);
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

    @GetMapping("api/expense/getpaidstatus/{id}")
    public String getPaidStatus(@PathVariable String id) {
        for(Expense expense: Expenses) {
            if(expense.getExpenseID().equals(id)) {
                return String.valueOf(expense.getPaidStatus());
            }
        }
        return "Error";
    }

    @PutMapping("api/expense/setpaidstatus/{id}/{value}")
    public String setPaidStatus(@PathVariable String id, @PathVariable boolean value) {
        for(Expense expense: Expenses) {
            if(expense.getExpenseID().equals(id)) {
                expense.setPaidStatus(value);
                if(value) {
                    notificationService.sendExpensePaidNotification(expense.getTransactionID());
                }
                return "Paid status set to: "+value+" for expense: "+expense;
            }
        }
        return "Error";
    }


}
