/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapplication;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ellis
 */
class SalaryAccount extends Account{

    public SalaryAccount() {
        this.setCheckingAccount(nextAccNum);
        //nextAccNum++;
    }
    
    
    

    public double getInitial() {
        return initial;
    }

    public void setInitial(double initial) {
        if (initial>=0){
            this.initial = initial;
            this.setBalance(initial);
        }
    }
    private double initial;
    
    @Override
    public synchronized void transfer(double transfer, Account a){
            double temp;
            int flag=1;
            while(flag!=0)
            {
		temp=this.getBalance();
		if(temp-transfer>=0)
                {
                    temp=temp-transfer;
                    a.setBalance(a.balance+transfer);
                    this.setBalance(temp);
                    flag=0;			
		}
		else
		{
                    try
                    {
			System.out.println("Insufficient balance "+
			Thread.currentThread().getName()+" is waiting");
			wait();//this is called and the chance is given to deposite
                    }
                    catch(InterruptedException ie)
                    {
			ie.printStackTrace();
                    }
		}
            }
            System.out.println("Transfer is completed by "+
            Thread.currentThread().getName());
            System.out.println("Balance after withdraw is="+this.getBalance());
    }
        @Override
        public synchronized void withdraw(double withdrawl) {
            double temp;
            int flag=1;
            while(flag!=0)
            {
		temp=this.getBalance();
		if(temp-withdrawl>=0)
                {
                    temp=temp-withdrawl;
                    this.setBalance(temp);
                    flag=0;				
		}
		else
		{
                    try
                    {
			System.out.println("Insufficient balance "+
			Thread.currentThread().getName()+" is waiting");
			wait();//this is called and the chance is given to deposite
                    }
                    catch(InterruptedException ie)
                    {
			ie.printStackTrace();
                    }
		}
            }
            System.out.println("Withdraw is completed by "+
            Thread.currentThread().getName());
            System.out.println("Balance after withdraw is="+this.getBalance());
        }

    }
        

