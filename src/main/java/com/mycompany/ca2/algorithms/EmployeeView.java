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
    
        public String askFileName() {
        System.out.print("Please enter the filename to read: ");
        return scanner.nextLine().trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
        

        // shows the first names of the list (up to the limit)
    public void showNames(Employee[] list, int limit) {
        int max = Math.min(limit, list.length);
        System.out.println("First " + max + " names (A-Z):");
        for (int i = 0; i < max; i++) {
            System.out.println((i + 1) + ". " + list[i].getName());
        }
    }
    
          public String askName() {
        System.out.print("Enter the full name to search: ");
        return scanner.nextLine().trim();
    }

    // shows the details of one employee, or a message if it is null
    public void showEmployee(Employee employee) {
        if (employee == null) {
            System.out.println("Employee not found.");
        } else {
            System.out.println("Name: " + employee.getName());
            System.out.println("Manager Type: " + employee.getManagerType());
            System.out.println("Department: " + employee.getDepartment());
        }
    }
}
