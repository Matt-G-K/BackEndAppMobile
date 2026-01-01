package dk.sdu.mmmi.cbse.restapibackendandroid.service;

import org.springframework.stereotype.Service;

import dk.sdu.mmmi.cbse.restapibackendandroid.Group;
import dk.sdu.mmmi.cbse.restapibackendandroid.Transaction;
import dk.sdu.mmmi.cbse.restapibackendandroid.User;
import dk.sdu.mmmi.cbse.restapibackendandroid.model.types.NotificationType;
import dk.sdu.mmmi.cbse.restapibackendandroid.repositoy.GroupStore;
import dk.sdu.mmmi.cbse.restapibackendandroid.repositoy.TransactionStore;
import dk.sdu.mmmi.cbse.restapibackendandroid.repositoy.UserStore;



@Service
public class NotificationService {
    private final DeviceTokenService deviceTokenService;
    private final FcmService fcmService;
    private final GroupStore group;
    private final UserStore user;
    private final TransactionStore transactions;

    public NotificationService(DeviceTokenService deviceTokenService, FcmService fcmService, GroupStore group, UserStore user, TransactionStore transactions) {
        this.deviceTokenService = deviceTokenService;
        this.fcmService = fcmService;
        this.group = group;
        this.user = user;
        this.transactions = transactions;
    }


    // Method to send a group invitation notification
    public void sendGroupInvitationNotification(String userId, String groupName) {
        var tokens = deviceTokenService.getTokensUser(userId);

        for(String token: tokens){
            System.out.println("Sending group invitation notification to token: " + token +
                                   " for group: " + groupName);
            fcmService.sendPushNotification(
                userId,
                token,
                "Added To Group",
                "You have been added to the group: " + groupName,
                NotificationType.ADDED_TO_GROUP
            );
        }
    }

    // Method to send a group payment reminder notification
    public void sendGroupPing(String userId, String groupName) {
        var tokens = deviceTokenService.getTokensUser(userId);

        for(String token: tokens){
            System.out.println("Sending group ping notification to token: " + token);
            fcmService.sendPushNotification(
                userId,
                token,
                "Group Payment Reminder",
                "Don't forget to settle your payments in the group: " + groupName,
                NotificationType.GROUP_PING
            );
        }
    }

    // Method to send a transaction created notification
    public void sendTransactionCreatedNotification(String userId, String transaction, Group group) {
        for(String memberId: group.getMemberIDs()){
            if(memberId.equals(userId)){
                continue;
            }
            var tokens = deviceTokenService.getTokensUser(memberId);
            for(String token: tokens){
                System.out.println("Sending transaction created notification to token: " + token);
                fcmService.sendPushNotification(
                    memberId,
                    token,
                    "New Transaction Created",
                    user.getUserById(userId).getUsername() + "created a transaction in " + group.getName() +
                    " for amount: " + transaction,
                    NotificationType.TRANSACTION_CREATED
                );
            }
        }
    }

    // Added to a transaction notification
    public void sendAddedToTransactionNotification(String username) {
        var tokens = deviceTokenService.getTokensUser(user.getUserByUsername(username).getUserId());
        for(String token: tokens){
            System.out.println("Sending added to transaction notification to token: " + token);
            fcmService.sendPushNotification(
                username,
                token,
                "Added To Transaction",
                "You have been added to a transaction.",
                NotificationType.ADDED_TO_TRANSACTION
            );
        }
    }

    // Notify when an expense is created to a transaction
    public void sendExpenseCreatedNotification(String expense, Transaction transaction) {
        for(String memberId : transaction.getUsers()){
            var tokens = deviceTokenService.getTokensUser(memberId);
            for(String token: tokens){
                System.out.println("Sending transaction created notification to token: " + token);
                fcmService.sendPushNotification(
                    memberId,
                    token,
                    "New expense added to transaction",
                    "An expense: " + expense + " has been added to transaction: " + transaction.getId(),
                    NotificationType.EXPENSE_CREATED
                );
            }
        }
    }

    // Notify when an expense is marked as paid
    public void sendExpensePaidNotification(int transactionId) {
        Transaction transaction = transactions.getTransactionById(transactionId);

        for(String memberId : transaction.getUsers()){
            var tokens = deviceTokenService.getTokensUser(memberId);
            for(String token: tokens){
                System.out.println("Sending expense paid notification to token: " + token);
                fcmService.sendPushNotification(
                    memberId,
                    token,
                    "Expense Paid",
                    "An expense in transaction: " + transaction.getId() + " has been paid.",
                    NotificationType.EXPENSE_PAID
                );
            }
        }
    }
}
