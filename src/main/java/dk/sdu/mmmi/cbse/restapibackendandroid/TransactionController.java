package dk.sdu.mmmi.cbse.restapibackendandroid;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TransactionController {

    private List<Transaction> Transactions = new ArrayList<>();

    @GetMapping("/api/transactions/group/{id}")
    public List<Transaction> getTransactionsGroup(@PathVariable int id) {
        List<Transaction> transactionsgroup = new ArrayList<>();
        for(Transaction transaction: Transactions) {
            if(transaction.getGroup().equals(id)) {
                transactionsgroup.add(transaction);
            }
        }
        return transactionsgroup;
    }

    @GetMapping("/api/transactions/user/{username}")
    public List<Transaction> getTransactionsUser(@PathVariable String username) {
        List<Transaction> transactionsgroup = new ArrayList<>();
        for(Transaction transaction: Transactions) {
            if(transaction.getUsers().contains(username)) {
                transactionsgroup.add(transaction);
            }
        }
        return transactionsgroup;
    }

    @GetMapping("/api/transactions/{id}")
    public List<Transaction> getTransaction(@PathVariable int id) {
        List<Transaction> transactionsgroup = new ArrayList<>();
        for(Transaction transaction: Transactions) {
            if(transaction.getId().equals(id)) {
                transactionsgroup.add(transaction);
            }
        }
        return transactionsgroup;
    }

    @PostMapping("/api/transactions/create/{amount}/{username}/{group}")
    public String createTransaction(@PathVariable int amount, @PathVariable String username, @PathVariable int group) {
        System.out.println("Trying to create group with amount: "+amount+" for group: "+group);
        int ID = Transactions.getLast().getId()+1;
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        ArrayList<String> users = new ArrayList<>();
        ArrayList<String> expenses = new ArrayList<>();
        users.add(username);
        String splitType = "RoundRobin";
        Transaction newTransaction = new Transaction(ID, amount, users, expenses, group, date, splitType, false);
        Transactions.add(newTransaction);
        return "Transaction created with id: "+ID;
    }

    @PutMapping("/api/transactions/adduser/{id}/{username}")
    public String addUserTransaction(@PathVariable int id, @PathVariable String username) {
        for(Transaction transaction: Transactions) {
            if(transaction.getId().equals(id) && !transaction.getUsers().contains(username)) {
                transaction.addUser(username);
                return "User: "+username+" added to transaction: "+transaction;
            } else if (transaction.getId().equals(id) && transaction.getUsers().contains(username)) {
                return "User already associated with transaction";
            }
        }
        return "Error";
    }

    @PutMapping("/api/transactions/removeuser/{id}/{username}")
    public String removeUserTransaction(@PathVariable int id, @PathVariable String username) {
        for(Transaction transaction: Transactions) {
            if(transaction.getId().equals(id) && transaction.getUsers().contains(username)) {
                transaction.removeUser(username);
                return "User: "+username+" added to transaction: "+transaction;
            } else if (transaction.getId().equals(id) && !transaction.getUsers().contains(username)) {
                return "User not associated with transaction";
            }
        }
        return "Error";
    }
    
    @GetMapping("api/transactions/getsplittype/{id}")
    public String getSplitType(@PathVariable int id) {
        String splitType = "";
        for(Transaction transaction: Transactions) {
            if(transaction.getId().equals(id)) {
                splitType = transaction.getSplitType();
            }
        }
        return splitType;
    }

    @PutMapping("api/transactions/setsplittype/{id}/{string}")
    public String setSplitType(@PathVariable int id, @PathVariable String string) {
        for(Transaction transaction: Transactions) {
            if(transaction.getId().equals(id)) {
                transaction.setSplitType(string);
                return "Set splittype to: "+string+" for transaction: "+id;
            }
        }
        return "Error";
    }

    @GetMapping("api/transactions/expenses/{id}")
    public List<String> getExpensesTransaction(@PathVariable int id) {
        for(Transaction transaction: Transactions) {
            if(transaction.getId().equals(id)) {
                return transaction.getExpenses();
            }
        }
        return new ArrayList<String>();
    }

    @PutMapping("api/transactions/addexpenses/{id}/{expense}")
    public String addExpense(@PathVariable int id, @PathVariable String expense) {
        for(Transaction transaction: Transactions) {
            if(transaction.getId().equals(id) && !transaction.getExpenses().contains(expense)) {
                transaction.addExpense(expense);
                return "Expense: "+expense+" added to transaction: "+transaction;
            } else if (transaction.getId().equals(id) && transaction.getExpenses().contains(expense)) {
                return "Expense already associated with transaction";
            }
        }
        return "Error";
    }

    @PutMapping("api/transactions/removeexpenses/{id}/{expense}")
    public String removeExpense(@PathVariable int id, @PathVariable String expense) {
        for(Transaction transaction: Transactions) {
            if(transaction.getId().equals(id) && transaction.getExpenses().contains(expense)) {
                transaction.removeExpense(expense);
                return "Expense: "+expense+" removed from transaction: "+transaction;
            } else if (transaction.getId().equals(id) && !transaction.getExpenses().contains(expense)) {
                return "Expense not associated with transaction";
            }
        }
        return "Error";
    }

    @GetMapping("api/transactions/getpaidstatus/{id}")
    public String getPaidStatus(@PathVariable int id) {
        for(Transaction transaction: Transactions) {
            if(transaction.getId().equals(id)) {
                return String.valueOf(transaction.getPaidStatus());
            }
        }
        return "Error";
    }

    @PutMapping("api/transactions/setpaidstatus/{id}/{value}")
    public String setPaidStatus(@PathVariable int id, @PathVariable boolean value) {
        for(Transaction transaction: Transactions) {
            if(transaction.getId().equals(id)) {
                transaction.setPaidStatus(value);
                return "Paid status set to: "+value+" for transaction: "+transaction;
            }
        }
        return "Error";
    }
}
