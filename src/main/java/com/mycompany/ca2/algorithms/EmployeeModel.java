/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ca2.algorithms;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author rafae
 */

public class EmployeeModel {

    private Employee[] employees = new Employee[0];
    private String[] managerTypes = new String[0]; // valid job titles
    private String[] departments = new String[0];  // valid departments
    private boolean sorted = false; // true after the list is sorted

    // reads the file and fills the arrays, returns false if the file is not found
    public boolean loadFile(String fileName) {
        try {
            File file = new File(fileName);

            // first pass: count the employee lines (skip header and blank lines)
            int count = 0;
            boolean firstLine = true;
            Scanner counter = new Scanner(file);
            while (counter.hasNextLine()) {
                String line = counter.nextLine();
                if (firstLine) {
                    firstLine = false;
                } else if (!line.trim().isEmpty()) {
                    count++;
                }
            }
            counter.close();

            // second pass: create the employees
            employees = new Employee[count];
            managerTypes = new String[0];
            departments = new String[0];
            sorted = false;

            int i = 0;
            firstLine = true;
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (firstLine) {
                    firstLine = false;
                } else if (!line.trim().isEmpty()) {
                    String[] fields = line.split(",");

                    String name = fields[0].trim() + " " + fields[1].trim();
                    String department = fields[5].trim();
                    String managerType = fields[7].trim();

                    employees[i] = new Employee(name, managerType, department);
                    i++;

                    managerTypes = addIfNew(managerTypes, managerType);
                    departments = addIfNew(departments, department);
                }
            }
            reader.close();

            return true;

        } catch (FileNotFoundException e) {
            return false;
        }
    }

    // adds the value to the array only if it is not there yet
    private String[] addIfNew(String[] array, String value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return array;
            }
        }

        String[] bigger = new String[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            bigger[i] = array[i];
        }
        bigger[array.length] = value;
        return bigger;
    }

        // sorts the employees by full name using quick sort
    public void sortEmployees() {
        employees = quickSort(employees);
        sorted = true;
    }

    public boolean isSorted() {
        return sorted;
    }

    // quicksort by full name
    private Employee[] quickSort(Employee[] arr) {

        // base case
        if (arr.length <= 1) {
            return arr;
        }

        Employee pivot = arr[arr.length - 1];

        int leftCount = 0;
        int rightCount = 0;

        // count sizes
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].getName().compareToIgnoreCase(pivot.getName()) < 0) {
                leftCount++;
            } else {
                rightCount++;
            }
        }

        // create arrays
        Employee[] left = new Employee[leftCount];
        Employee[] right = new Employee[rightCount];

        int l = 0, r = 0;

        // fill arrays
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].getName().compareToIgnoreCase(pivot.getName()) < 0) {
                left[l++] = arr[i];
            } else {
                right[r++] = arr[i];
            }
        }

        // recursive calls
        left = quickSort(left);
        right = quickSort(right);

        // combine arrays
        Employee[] result = new Employee[arr.length];
        int index = 0;

        for (int i = 0; i < left.length; i++) {
            result[index++] = left[i];
        }

        result[index++] = pivot;

        for (int i = 0; i < right.length; i++) {
            result[index++] = right[i];
        }

        return result;
    }
    
    public Employee[] getEmployees() {
        return employees;
    }

    public String[] getManagerTypes() {
        return managerTypes;
    }

    public String[] getDepartments() {
        return departments;
    }
}
