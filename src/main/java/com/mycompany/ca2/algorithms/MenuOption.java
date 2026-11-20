/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ca2.algorithms;

/**
 *
 * @author rafae
 */
public enum MenuOption {
    
    SORT (1, "SORT"),
    SEARCH (2, "SEARCH"),
    ADD_RECORDS (3, "ADD RECORDS"),
    CREATE_TREE (4, "Create a binary tree"),
    EXIT (5, "EXIT");
    private String label;
    private int number;

    MenuOption(int number, String label) {
        this.number = number;
        this.label = label;
    }

    public int getNumber() {
        return number;
    }

    public String getLabel() {
        return label;
    }

    // returns the option with this number, or null if it does not exist
    public static MenuOption fromNumber(int number) {
        for (MenuOption option : MenuOption.values()) {
            if (option.getNumber() == number) {
                return option;
            }
        }
        return null;
    }
    
}
