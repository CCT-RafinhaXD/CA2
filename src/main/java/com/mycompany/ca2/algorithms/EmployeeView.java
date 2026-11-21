/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ca2.algorithms;
import java.util.Scanner;
/**
 *
 * @author rafae
 */
public class EmployeeView {
    
     private Scanner scanner = new Scanner(System.in);

    // prints the menu using the enum
    public void showMenu() {
        System.out.println();
        System.out.println("Do You wish to SORT or SEARCH:");
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.getNumber() + ". " + option.getLabel());
        }
    }

    // reads the number typed by the user, returns -1 if it is not a number
    public int readOption() {
        String text = scanner.nextLine();
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
    
    
}
