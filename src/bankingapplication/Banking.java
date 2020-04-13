/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankingapplication;

/**
 *
 * @author Ellis
 */
public interface Banking{

    /**
     *
     * @param x
     * @param transfer
     * @param a
     */
    
    //methods that accounts must implement
    public void transfer(double transfer, Account a);
    public void withdraw(double withdrawl);
    public void deposit(double deposit);
    public void provideStatement();
}
