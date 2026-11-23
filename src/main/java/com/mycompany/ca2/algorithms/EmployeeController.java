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
                        break;
                    case SEARCH:
                        view.showMessage("SEARCH selected");
                        break;
                    case ADD_RECORDS:
                        view.showMessage("ADD RECORDS selected");
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

    public static void main(String[] args) {
        EmployeeModel model = new EmployeeModel();
        EmployeeView view = new EmployeeView();
        EmployeeController controller = new EmployeeController(model, view);
        controller.run();
    }
    
}
