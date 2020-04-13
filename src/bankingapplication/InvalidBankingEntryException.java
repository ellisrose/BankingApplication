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
public class InvalidBankingEntryException extends Exception{
        String str;
        
        InvalidBankingEntryException(String str){
            this.str=str;
            System.out.println(str);
        }
        }
