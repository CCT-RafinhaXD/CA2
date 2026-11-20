/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ca2.algorithms;

/**
 *
 * @author rafae
 */
public class CA2Algorithms {

    public static void main(String[] args) {
      Employee e = new Employee("Ana Silva", "Clerk", "Finance");
System.out.println(e);

for (MenuOption o : MenuOption.values()) {
    System.out.println(o.getNumber() + ". " + o.getLabel());
}
System.out.println(MenuOption.fromNumber(2));
System.out.println(MenuOption.fromNumber(9));
    }
}
