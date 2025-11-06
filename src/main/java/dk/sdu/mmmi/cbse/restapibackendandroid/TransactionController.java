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
        users.add(username);
        new Transaction(ID, amount, users, group, date);
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
}
