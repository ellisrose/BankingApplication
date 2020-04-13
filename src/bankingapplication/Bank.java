/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapplication;

import static BankingApplication.sql.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author Ellis
 */
class Bank{
    ArrayList<Account> accounts= new ArrayList<Account>();
    ArrayList<Customer> customers= new ArrayList<Customer>();
    private int accountNum=0;
    private int custNum=0;
    
    public void setCustNum() {
        this.custNum++;
    }

    public int getAccountNum() {
        return accountNum;
    }
    
    public void removeAccount(int i){
        this.customers.remove(i);
        this.accountNum--;
    }

    public int getCustNum() {
        return custNum;
    }
        
    void addAccount(Account a){
        this.accounts.add(a);
        //aMap.put(accounts.get(accountNum).getCheckingAccount(),accounts.get(accountNum));
        accountNum++;
    }
        
    void addCustomer(Customer c){
        this.customers.add(c);
        //int y = customers.get(custNum).getCustomersAccount().getCheckingAccount();
        //cMap.put(y, customers.get(custNum));
        custNum++;
    }
        
    /*public LinkedHashMap<Integer, Customer> getcMap() {
        return cMap;
    }

    public LinkedHashMap<Integer, Account> getaMap() {
        return aMap;
    }
        LinkedHashMap<Integer, Customer> cMap;
        LinkedHashMap<Integer, Account> aMap;*/
        
       
 /* void sortHash(){
        Comparator<Entry<Integer,Account>> compareAcc = new Comparator<Entry<Integer,Account>>(){

            @Override
            public int compare(Entry<Integer, Account> o1, Entry<Integer, Account> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
            
        };
        Comparator<Entry<Integer,Customer>> compareCust = new Comparator<Entry<Integer,Customer>>(){

            @Override
            public int compare(Entry<Integer, Customer> o1, Entry<Integer, Customer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
            
        };
        List<Entry<Integer, Customer>> cust1 = new List<Entry<Integer, Customer>>(cMap.entrySet());
        List<Entry<Integer, Account>> acc1 = new List<Entry<Integer, Account>>(aMap.entrySet());
            
        acc1.sort(compareAcc);
        cust1.sort(compareCust);
            
        aMap.clear();
        cMap.clear();
           
        for (Entry<Integer,Account> mapping: acc1){
            aMap.put(mapping.getKey(),mapping.getValue());
        }
        for (Entry<Integer,Customer> mapping: cust1){
            cMap.put(mapping.getKey(),mapping.getValue());
        }
    }*/
       
    public void executeWCreateSql(){
        sql.create();
        for (int i = 0; i<this.accountNum;i++){
            sql.insertAccountByPrepare(accounts.get(i));
        }
        for (int i = 0; i<custNum;i++){
            sql.insertCustomerByPrepare(customers.get(i));
        }
    }
    
    public void executeSql(){
        for (int i = 0; i<this.accountNum;i++){
            sql.insertAccountByPrepare(accounts.get(i));
        }
        for (int i = 0; i<custNum;i++){
            sql.insertCustomerByPrepare(customers.get(i));
        }
    }
        
    void print_to_File() throws FileNotFoundException, IOException{
        ObjectOutputStream objsA = new ObjectOutputStream(new FileOutputStream("BankAccountDetails.txt"));
        ObjectOutputStream objsC = new ObjectOutputStream(new FileOutputStream("BankCustDetails.txt"));
        for(int i=0;i<accountNum;i++){
            objsA.writeObject(accounts.indexOf(i));
        }
        for(int i=0;i<custNum;i++){
            objsC.writeObject(customers.indexOf(i));
        }
        objsA.flush();
        objsC.flush();
        objsA.close();
        objsA.close();
    }
        
    void get_from_file () throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream inA = new ObjectInputStream(new FileInputStream("BankAccountDetails.txt"));
        ObjectInputStream inC = new ObjectInputStream(new FileInputStream("BankCustDetails.txt"));
        for (int i=0;i<accountNum;i++){
            Account x= (Account)inA.readObject();
            System.out.println(x);
        }
        for (int i=0;i<custNum;i++){
            Customer x = (Customer)inC.readObject();
            System.out.println(x);
        }
    }
}
