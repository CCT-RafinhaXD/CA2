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
    private Employee[] newEmployees = new Employee[0]; // records added by the user
    private boolean sorted = false; // true after the list is sorted

    // binary tree (hierarchy)
    private Node root = null;
    private int nodeCount = 0;

    // one node of the binary tree
    private static class Node {
        Employee employee;
        Node left;
        Node right;

        Node(Employee employee) {
            this.employee = employee;
        }
    }

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
            newEmployees = new Employee[0];

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
        // recursive binary search by full name, returns the position or -1
    private int binarySearch(Employee[] arr, String name, int low, int high) {

        // base case: not found
        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;
        int result = name.compareToIgnoreCase(arr[mid].getName());

        if (result == 0) {
            return mid;                                    // found
        } else if (result < 0) {
            return binarySearch(arr, name, low, mid - 1);  // search the left half
        } else {
            return binarySearch(arr, name, mid + 1, high); // search the right half
        }
    }

    // searches an employee by full name, returns null if not found
    public Employee searchEmployee(String name) {
        // binary search only works on a sorted list
        if (!sorted) {
            sortEmployees();
        }

        int position = binarySearch(employees, name.trim(), 0, employees.length - 1);

        if (position == -1) {
            return null;
        }
        return employees[position];
    }
    
        // returns true if an employee with this name already exists
    public boolean nameExists(String name) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // returns the manager type with this number (1 to n), or null if it does not exist
    public String findManagerType(int number) {
        if (number < 1 || number > managerTypes.length) {
            return null;
        }
        return managerTypes[number - 1];
    }

    // returns the department with this number (1 to n), or null if it does not exist
    public String findDepartment(int number) {
        if (number < 1 || number > departments.length) {
            return null;
        }
        return departments[number - 1];
    }

    // adds a new employee to the list and to the list of new records
    public void addEmployee(String name, String managerType, String department) {
        Employee newEmployee = new Employee(name, managerType, department);

        // bigger employees array
        Employee[] bigger = new Employee[employees.length + 1];
        for (int i = 0; i < employees.length; i++) {
            bigger[i] = employees[i];
        }
        bigger[employees.length] = newEmployee;
        employees = bigger;

        // bigger array of new records
        Employee[] biggerNew = new Employee[newEmployees.length + 1];
        for (int i = 0; i < newEmployees.length; i++) {
            biggerNew[i] = newEmployees[i];
        }
        biggerNew[newEmployees.length] = newEmployee;
        newEmployees = biggerNew;

        // the list is not sorted anymore
        sorted = false;
    }

    public Employee[] getNewEmployees() {
        return newEmployees;
    }
    
    // lower number = higher position in the hierarchy
    private int getRank(String managerType) {
        if (managerType.equals("Head Manager")) {
            return 1;
        } else if (managerType.equals("Senior Manager")) {
            return 2;
        } else if (managerType.equals("Manager")) {
            return 3;
        } else if (managerType.equals("Assistant Manager")) {
            return 4;
        } else if (managerType.equals("Team Lead")) {
            return 5;
        }
        return 6; // all the other employees
    }

    // picks the first records by rank (highest positions first) and builds the tree
    public void createTree(int total) {
        // sorted list gives the same order every time (A-Z inside each rank)
        if (!sorted) {
            sortEmployees();
        }

        int size = Math.min(total, employees.length);
        Employee[] chosen = new Employee[size];
        int count = 0;

        for (int rank = 1; rank <= 6 && count < size; rank++) {
            for (int i = 0; i < employees.length && count < size; i++) {
                if (getRank(employees[i].getManagerType()) == rank) {
                    chosen[count] = employees[i];
                    count++;
                }
            }
        }

        insertLevelOrder(chosen);
    }

    // level-order (breadth-first) insertion: fill left first, then right,
    // when both children are taken, move to the next node in level order
    private void insertLevelOrder(Employee[] list) {
        root = null;
        nodeCount = 0;

        if (list.length == 0) {
            return;
        }

        Node[] queue = new Node[list.length];
        int head = 0;
        int tail = 0;

        root = new Node(list[0]);
        queue[tail] = root;
        tail++;
        nodeCount = 1;

        for (int i = 1; i < list.length; i++) {
            Node parent = queue[head];
            Node child = new Node(list[i]);

            if (parent.left == null) {
                parent.left = child;
            } else {
                parent.right = child;
                head++; // parent is full, the next node in the queue is the next parent
            }

            queue[tail] = child;
            tail++;
            nodeCount++;
        }
    }

    // level-order traversal: returns the employees level by level
    public Employee[] levelOrderTraversal() {
        Employee[] result = new Employee[nodeCount];

        if (root == null) {
            return result;
        }

        Node[] queue = new Node[nodeCount];
        int head = 0;
        int tail = 0;
        int index = 0;

        queue[tail] = root;
        tail++;

        while (head < tail) {
            Node current = queue[head];
            head++;

            result[index] = current.employee;
            index++;

            if (current.left != null) {
                queue[tail] = current.left;
                tail++;
            }
            if (current.right != null) {
                queue[tail] = current.right;
                tail++;
            }
        }

        return result;
    }

    // height of the tree in levels (empty tree = 0)
    public int getTreeHeight() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int getTreeNodeCount() {
        return nodeCount;
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
