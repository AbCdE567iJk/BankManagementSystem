package org.example;

import java.util.*;

class Account {
    private static int nextAccountNumber = 1000;
    private int accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String name, double initialDeposit) {
        this.accountHolderName = name;
        this.balance = initialDeposit;
        this.accountNumber = nextAccountNumber++;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn ₹" + amount);
        } else {
            System.out.println("Invalid or Insufficient balance.");
        }
    }

    public void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name   : " + accountHolderName);
        System.out.println("Balance       : ₹" + balance);
    }
}

class Bank {
    private Map<Integer, Account> accounts = new HashMap<>();

    public void createAccount(String name, double initialDeposit) {
        Account account = new Account(name, initialDeposit);
        accounts.put(account.getAccountNumber(), account);
        System.out.println("Account created successfully! Your Account Number is: " + account.getAccountNumber());
    }

    public void deposit(int accNo, double amount) {
        Account account = accounts.get(accNo);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(int accNo, double amount) {
        Account account = accounts.get(accNo);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void viewAccount(int accNo) {
        Account account = accounts.get(accNo);
        if (account != null) {
            account.displayDetails();
        } else {
            System.out.println("Account not found.");
        }
    }
}

class BankManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        int choice;

        do {
            System.out.println("\n===== Bank Management System =====");
            System.out.println("1. Create Account");
            System.out.println("2. View Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Initial Deposit: ₹");
                    double deposit = scanner.nextDouble();
                    bank.createAccount(name, deposit);
                }
                case 2 -> {
                    System.out.print("Enter Account Number: ");
                    int accNo = scanner.nextInt();
                    bank.viewAccount(accNo);
                }
                case 3 -> {
                    System.out.print("Enter Account Number: ");
                    int accNo = scanner.nextInt();
                    System.out.print("Enter Amount to Deposit: ₹");
                    double amount = scanner.nextDouble();
                    bank.deposit(accNo, amount);
                }
                case 4 -> {
                    System.out.print("Enter Account Number: ");
                    int accNo = scanner.nextInt();
                    System.out.print("Enter Amount to Withdraw: ₹");
                    double amount = scanner.nextDouble();
                    bank.withdraw(accNo, amount);
                }
                case 5 -> System.out.println("Thank you for using the Bank Management System.");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
