package n2Callback;

import n2Callback.paymentGateway.PaymentGateway;
import n2Callback.paymentMethods.BankAccount;
import n2Callback.paymentMethods.CreditCard;
import n2Callback.paymentMethods.PaymentMethod;
import n2Callback.paymentMethods.Paypal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Store {
    private Scanner scanner;
    private PaymentMethod paymentMethod;
    private int option;
    private double amount;


    public void payment() {
        scanner = new Scanner(System.in);
        // Ask the total amount
        setAmount();

        // payment method options
        option = setOption();

        switch (option) {
            case 0:
                System.out.println("The process has been cancelled.");
                break;
            case 1:
                paymentMethod = new BankAccount();
                break;
            case 2:
                paymentMethod = new Paypal();
                break;
            case 3:
                paymentMethod = new CreditCard();
                break;
            default:
                System.out.println("Option not valid.");
        }
        //paymentMethod.payment(amount);
        if (option != 0) PaymentGateway.processPayment(amount, paymentMethod);
        scanner.close();
    }

    public void setAmount() {
        System.out.print("Insert total amount: ");
        try {
            this.amount = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException ime) {
            System.out.println(ime);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int setOption() {
        int option = 0;
        do {
            // menu
            System.out.println("Choose payment method:");
            System.out.println("1- Bank account");
            System.out.println("2- Paypal account");
            System.out.println("3- Credit card");
            System.out.println("0- Cancel payment");
            System.out.print(">> ");

            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ime) {
                System.out.println(ime);
                ime.printStackTrace();;
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        } while (option < 0 || option > 3);

        return option;
    }

}
