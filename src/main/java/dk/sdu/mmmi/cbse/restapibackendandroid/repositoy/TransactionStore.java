package dk.sdu.mmmi.cbse.restapibackendandroid.repositoy;

import java.util.ArrayList;
import java.util.List;

import dk.sdu.mmmi.cbse.restapibackendandroid.Transaction;

public class TransactionStore {
    private List<Transaction> Transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return Transactions;
    }

    public void addTransaction(Transaction transaction) {
        Transactions.add(transaction);
    }

    public Transaction getTransactionById(Integer id) {
        for (Transaction transaction : Transactions) {
            if (transaction.getId().equals(id)) {
                return transaction;
            }
        }
        return null;
    }

    public Transaction getLast() {
        return Transactions.get(Transactions.size() - 1);
    }
}
