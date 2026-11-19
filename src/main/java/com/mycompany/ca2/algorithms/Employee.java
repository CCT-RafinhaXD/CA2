/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ca2.algorithms;

/**
 *
 * @author rafae
 */
public class Employee {
    
    private String name;
    private String managerType; // Manager Type = Job title from the file
    private String department;

    public Employee(String name, String managerType, String department) {
        this.name = name;
        this.managerType = managerType;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getManagerType() {
        return managerType;
    }

    public String getDepartment() {
        return department;
    }

    public String toString() {
        return name + " | " + managerType + " | " + department;
    }
}
    

