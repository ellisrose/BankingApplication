package bankingapplication;

import java.io.Serializable;

public abstract class Account implements Banking, Serializable{

    protected void setCheckingAccount(int checkingAccount) {
        this.checkingAccount = checkingAccount;
    }
    protected int checkingAccount;
    private double minInitial;
    public static int nextAccNum=101; 
    double balance;
    
    //Constructor
    public Account(){
        this.balance=0;
        this.checkingAccount=nextAccNum;
        nextAccNum++;
    }
     
    //getters and setters
    private int setCheckingAccount(){
        return nextAccNum;
    }

    public int getCheckingAccount() {
        return checkingAccount;
    }

    public double getBalance() {
        return balance;
    }
        
    public void setBalance(double balance) {
        this.balance = balance;
    }

    //To String
    @Override
    public String toString() {
        return "Account{" + "checkingAccount=" + checkingAccount + ", balance=" + balance + '}';
    }
       
    //All methods from Banking interface
    public abstract void transfer(double x,Account a);   
        
    @Override
    public synchronized void deposit(double deposit){
        this.balance+=deposit;
        System.out.println("Deposite is completed by "+Thread.currentThread().getName());
        System.out.println("Balance after deposite is="+balance);
        this.notify();//this is again invoking the wait
    }
        
    @Override
    public synchronized void provideStatement(){
        System.out.println("The current balance on this account is: "+this.balance);
    }
	
    
    @Override
    public abstract void withdraw(double amt);
        
}