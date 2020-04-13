/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapplication;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * @author Ellis
 */
public class BankingApplication {
    //Initialize bank
    public static Bank bankOfAmerica=new Bank();

    /**
     * @param args the command line arguments
     * @throws bankingapplication.InvalidBankingEntryException
     */
    
    
    
    
    public static void main(String[] args) throws InvalidBankingEntryException, IOException, FileNotFoundException, ClassNotFoundException {
        //initializing local variables and drop tables
        //sql.drop();
        int i=0;
        int accno=0;
        Account acc=null;
        SalaryAccount acc1=null;
        SavingAccount acc2=null;
        CurrentAccount acc3=null;
        Customer cust;
        Customer user;
        
        //
        
        while(i!=1){
            //call Menu
            int sel = startMenu();
            
            //Creating an account
            if (sel==1){
                //Set Account Type
                int type = accountType();
                
                //Create each type
                if (type ==1){
                    
                    user=new Customer();
                
                    //Set customer Data
                    //cust = getCustomerInfo(user);
                    //Creates Salary Account with initial deposit
                    acc1=new SalaryAccount();
                    double init = initial();
                    acc1.setInitial(init);
                    
                    //adds the account and the customer to the banks data
                    user.setCustomersAccount(acc1);
                    bankOfAmerica.customers.add(user);
                    bankOfAmerica.setCustNum();
                    bankOfAmerica.addAccount(acc1);
                } else if (type ==2){
                    user=new Customer();
                
                    //Set customer Data
                    //cust = getCustomerInfo(user);
                    //Creates Savings Account with initial
                    acc2 = new SavingAccount();
                    double init = initial();
                    acc2.setInitial(init);
                    
                    //adds the account and the customer to the banks data
                    user.setCustomersAccount(acc2);
                    bankOfAmerica.customers.add(user);
                    bankOfAmerica.setCustNum();
                    bankOfAmerica.addAccount(acc2);
                } else{
                    user=new Customer();
                
                    //Set customer Data
                    //cust = getCustomerInfo(user);
                    //Creates Current Account with initial
                    acc3 = new CurrentAccount();
                    double init = initial();
                    acc3.setInitial(init);
                    
                    //adds the account and the customer to the banks data
                    user.setCustomersAccount(acc3);
                    bankOfAmerica.customers.add(user);
                    bankOfAmerica.setCustNum();
                    bankOfAmerica.addAccount(acc3);
                }
                
            //Accesses Existing account
            }else if (sel == 2){
                //create hashmap to access a user attached to the account
                
                HashMap<Integer,Customer> cMap = new HashMap();
                for (Customer customer : bankOfAmerica.customers) {
                    Integer inte = customer.getCustomersAccount().getCheckingAccount();
                    cMap.put(inte, customer);
                }
                
                System.out.println(cMap);
                
                //while loop to select an account
                int k=0;
                while (k!=1){
                    //select account and get checking account number
                    acc=selAccount();
                    accno=acc.getCheckingAccount();
                    if (acc==null){
                        k=cont();
                    }else{
                        k=1;
                    }
                }
                
                Integer acno= accno;
                //select the appropriate customer
                Customer cust1 = cMap.get(acno);
                
                //make a transaction
                transact(cust1);
                
            //This creates a new Customer and attaches it to an existing account
            } else{
                
                //Create the new customer
                Customer newCust;
                user = new Customer();
                newCust = getCustomerInfo(user);
                
                //selcect account
                int k=0;
                while (k!=1){
                    acc=selAccount();
                    if (acc==null){
                        k=cont();
                    }else {
                        k=1;
                    }
                }
                
                //adds the customer to the bank
                newCust.setCustomersAccount(acc);
                bankOfAmerica.customers.add(newCust);
                bankOfAmerica.setCustNum();
            }
            
            //Allow the userto see the accounts and users
            for (int f=0;f<bankOfAmerica.getAccountNum();f++){
                System.out.println(bankOfAmerica.accounts.get(f));
            }
            for (int f=0;f<bankOfAmerica.customers.size();f++){
                System.out.println(bankOfAmerica.customers.get(f));
            }
            
            //gives an option to exit
            i=cont();
            if (i==1){
                //saves to database when the user is done
                bankOfAmerica.executeWCreateSql();
            }
        }
    }
    
     public static int startMenu(){
        Scanner scan = new Scanner(System.in);
        int j=0;
        while(j!=1){
            System.out.println("What would you like to do?:");
            System.out.println("1. Create an Account");
            System.out.println("2. Access Existing Account through Customer");
            System.out.println("3. Add Customer to Existing Account");
            try{
                int sel;
                sel = scan.nextInt();
                return sel;
            } catch(Exception e){
                System.out.println("Please give a correct input");
            }
        }
        return 0;
    }
     
    public static Customer getCustomerInfo(Customer newCust){
        System.out.println("Enter the following information: ");
        int j=0;
        while(j!=1){
            try{
                newCust.setNname(getName());
                newCust.setAddress(getAddress());
                newCust.setProfessionalStatus(getProfession());
                newCust.setAge(getAge());
                return newCust;
            } catch (Exception e){
                System.out.println("Failed to create Customer");
            }
        }
        return null;
    }

    private static String getName() {
        Scanner scan = new Scanner(System.in);
        int j=0;
        while(j!=1){
            System.out.println("Enter your name: ");
            try{
                String sel;
                sel=scan.nextLine();
                return sel;
            } catch(Exception e){
                System.out.println("Please give a correct input");
            }
        }
        return null;
    }

    private static String getProfession(){
        int j=0;
        while(j!=1){
            System.out.println("Select Your Professional Status:");
            System.out.println("1. Retired");
            System.out.println("2. Entrepeneur");
            System.out.println("3. Salary");
            System.out.println("4. Umemployed");
            try{
                int sel;
                Scanner scan = new Scanner(System.in);
                sel = scan.nextInt();
                switch(sel){
                    case 1:
                        return "Retired";
                    case 2:
                        return "Entrepeneur";
                    case 3:
                        return "Salary";
                    case 4:
                        return "Unemployed";
                    default:
                        System.out.println("Please enter a number between 1 and 4:");
                }
            } catch(Exception e){
                System.out.println("Please give a correct input");
            }
        }
        return null;
    }

    private static String getAddress() {
        int j=0;
        while(j!=1){
            System.out.println("Enter your address:");
            try{
                String sel;
                Scanner scan = new Scanner(System.in);
                sel = scan.nextLine();
                return sel;
            } catch(Exception e){
                System.out.println("Please give a correct input");
            }
        }
        return null;
    }

    private static int getAge() {
       int j=0;
        while(j!=1){
            System.out.println("Enter Your Age:");
            try{
                int sel;
                Scanner scan = new Scanner(System.in);
                sel = scan.nextInt();
                if (sel>=18){
                    return sel;
                }
            } catch(Exception e){
                System.out.println("Please give a correct input");
            }
        }
        return 0;
    }

    private static int accountType() {
        int j=0;
        while(j!=1){
            System.out.println("Select Acount Type:");
            System.out.println("1. Salary Account");
            System.out.println("2. Savings Account");
            System.out.println("3. Current Account");
            try{
                int sel;
                Scanner scan = new Scanner(System.in);
                sel = scan.nextInt();
                if(sel ==1||sel ==2||sel==3){
                    return sel;
                }
            } catch(Exception e){
                System.out.println("Please give a correct input");
            }
        }
        return 0;
    }
    private static double initial(){
        System.out.println("Enter an initial deposit amount: ");
        int j=0;
        while(j!=1){
            try{
                double sel;
                Scanner scan = new Scanner(System.in);
                sel = scan.nextDouble();
                return sel;
            } catch(Exception e){
                System.out.println("Please give a correct input");
            }
        }
        return 0;
    }

    private static void transact(Customer c) {
        int j=0;
        Account acc1 = null;
        while(j!=1){
            System.out.println("Select Your Professional Status:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Provide Statement");
            try{
                int sel;
                String retStri = null;
                Scanner scan = new Scanner(System.in);
                sel = scan.nextInt();
                Account acc=c.getCustomersAccount();
                switch (sel){
                    case 1:
                        c.ident=1;
                        c.amount=getAmount();
                        break;
                    case 2:
                        c.ident=2;
                        c.amount=getAmount();
                        break;
                    case 3:
                        acc1=selAccount();
                        c.amount=getAmount();
                        c.getCustomersAccount().transfer(c.amount, acc1);
                        break;
                    case 4:
                        c.ident=3;
                        break;
                }
                c.start();
                j=1;
                
                sleep(200);
                
                for (int f=0;f<bankOfAmerica.getAccountNum();f++){
                    if(bankOfAmerica.accounts.get(f).getCheckingAccount()==acc.getCheckingAccount()){
                        bankOfAmerica.removeAccount(f);
                        bankOfAmerica.addAccount(acc);
                    } 
                    if(sel==3 && bankOfAmerica.accounts.get(f).getCheckingAccount()==acc1.getCheckingAccount()){
                        bankOfAmerica.removeAccount(f);
                        bankOfAmerica.addAccount(acc1);
                    }
                }
                
                
                
            } catch(Exception e){
                System.out.println("There Was an error");
            }
        }
    }

    private static double getAmount() {
        int j=0;
        while(j!=1){
            System.out.println("Enter The transaction amount: ");
            try{
                double sel;
                Scanner scan = new Scanner(System.in);
                sel = scan.nextDouble();
                return sel;
            } catch(Exception e){
                System.out.println("Please give a correct input");
            }
        }
        return 0;
    }
    
    public static Account selAccount(){
        System.out.println("Please Enter the Account Number you would like to access");
        Scanner scan = new Scanner(System.in);
        int accountNo=scan.nextInt();
        Account a=null;
            for (int r =0;r<bankOfAmerica.getAccountNum();r++){
                if(bankOfAmerica.accounts.get(r).getCheckingAccount()==accountNo){
                    a=bankOfAmerica.accounts.get(r);
                }
            }
        return a;
    }
    
    public static int cont() {
        Scanner scan = new Scanner(System.in);
        int j=0;
        int i=0;
        while(j!=1){
            System.out.println("Would you like to continue?: ('y' or 'n')");
            try{
                int sel;
                String yOrNo = scan.nextLine();
                if (yOrNo.contentEquals("y")){
                    i=0;
                    j=1;
                }else if (yOrNo.contentEquals("n")){
                    i=1;
                    j=1;
                }
            } catch(Exception e){
                System.out.println("Please give a correct input");
            }
        }
        return i;
    }
        
}
