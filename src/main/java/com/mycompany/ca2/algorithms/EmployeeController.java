/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ca2.algorithms;

/**
 *
 * @author rafae
 */
public class EmployeeController {
    
    private EmployeeModel model;
    private EmployeeView view;

    public EmployeeController(EmployeeModel model, EmployeeView view) {
        this.model = model;
        this.view = view;
    }

    // menu loop
    public void run() {
                String fileName = view.askFileName();
        while (!model.loadFile(fileName)) {
            view.showMessage("File not found, try again.");
            fileName = view.askFileName();
        }
        view.showMessage("File read successfully");
        
        
        
        MenuOption choice = null;

        while (choice != MenuOption.EXIT) {
            view.showMenu();
            int number = view.readOption();
            choice = MenuOption.fromNumber(number);

            if (choice == null) {
                view.showMessage("Invalid option, try again.");
            } else {
                switch (choice) {
                         case SORT:
                        view.showMessage("SORT selected");
                        model.sortEmployees();
                        view.showNames(model.getEmployees(), 20);
                        break;
                        
                        case SEARCH:
                        view.showMessage("SEARCH selected");
                        String name = view.askName();
                        Employee found = model.searchEmployee(name);
                        view.showEmployee(found);
                        break;
                        
                        case ADD_RECORDS:
                        view.showMessage("ADD RECORDS selected");
                        addEmployee();
                        break;
                        
                    case CREATE_TREE:
                        view.showMessage("Create a binary tree selected");
                        break;
                        
                    case EXIT:
                        view.showMessage("Goodbye!");
                        break;
                }
            }
        }
    }
    
        // asks for the data of a new employee, validates it and adds it to the list
    private void addEmployee() {

        // name: cannot be empty or already exist
        String name = view.askNewName();
        while (name.isEmpty() || model.nameExists(name)) {
            if (name.isEmpty()) {
                view.showMessage("The name cannot be empty.");
            } else {
                view.showMessage("This name already exists.");
            }
            name = view.askNewName();
        }

        // manager type: must exist
        String managerType = model.findManagerType(
                view.askChoice("Manager Types:", model.getManagerTypes()));
        while (managerType == null) {
            view.showMessage("Invalid Manager Type, try again.");
            managerType = model.findManagerType(
                    view.askChoice("Manager Types:", model.getManagerTypes()));
        }

        // department: must exist
        String department = model.findDepartment(
                view.askChoice("Departments:", model.getDepartments()));
        while (department == null) {
            view.showMessage("Invalid Department, try again.");
            department = model.findDepartment(
                    view.askChoice("Departments:", model.getDepartments()));
        }

        model.addEmployee(name, managerType, department);
        view.showMessage("Employee added successfully.");
        view.showNewEmployees(model.getNewEmployees());
    }

    public static void main(String[] args) {
        EmployeeModel model = new EmployeeModel();
        EmployeeView view = new EmployeeView();
        EmployeeController controller = new EmployeeController(model, view);
        controller.run();
    }
    
}
