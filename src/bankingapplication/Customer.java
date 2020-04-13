/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapplication;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ellis
 */
public class Customer extends Thread implements Serializable,Comparable<Customer>{
    private int age;
    private String professionalStatus;
    private String address;
    private String name;
    double amount;
    int ident;
    private Account customersAccount;
    private int accountNo;

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    } 

    public String getProfessionalStatus() {
        return professionalStatus;
    }

    public void setNname(String name){
        this.name=name;
    }
    
    public void setProfessionalStatus(String professionalStatus) {
        this.professionalStatus = professionalStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public Account getCustomersAccount() {
        return customersAccount;
    }

    public void setCustomersAccount(Account customersAccount) {
        this.customersAccount = customersAccount;
    }
        
        
        
        
    public Customer(){
        
    }   
        
    public Customer(Account a, double amount, int i){
        this.amount=amount;
        this.customersAccount=a;
        this.ident=i;
    }

    public Customer(int age, String professionalStatus, String address, String name, int ident, double am) {
        this.age = age;
        this.professionalStatus = professionalStatus;
        this.address = address;
        this.name = name;
        this.ident = ident;
        this.amount=am;
        this.accountNo=customersAccount.getCheckingAccount();
    }
        
        

    @Override
    public String toString() {
        return "Customer{" + "age=" + age + ", professionalStatus=" + professionalStatus + ", address=" + address + ", name=" + name + ", customersAccount=" + customersAccount + '}';
    }
        
        
        
    public void setAge(int x)throws InvalidBankingEntryException{
        if (x<18){
            throw new InvalidBankingEntryException("Invalid Age Entry");
        }
        else{
            this.age=x;
        }
    }
        
    @Override
    public void run(){
        if (this.ident==1){
            this.customersAccount.withdraw(this.amount);
        }
        else if(this.ident==2)  {
            this.customersAccount.deposit(this.amount);
        }
        else if(this.ident==3)  {
            this.customersAccount.provideStatement();
        }
           
        try {
            join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int compareTo(Customer o) {
        if (o.getCustomersAccount().getCheckingAccount()<this.getCustomersAccount().getCheckingAccount()){
            return -1;
        }
        else if (o.getCustomersAccount().getCheckingAccount()==this.getCustomersAccount().getCheckingAccount()){
            return 0;
        }
        else{
            return 1;
        }
    }
        
}
