/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hugon
 */
public class Employee {
    private static int counter_id;
    private int employee_id; 
    private String name;
    private double salary;
    private static Map<Integer, Boolean> ids = new HashMap<>();
    
    public Employee(){
        this.employee_id = ++Employee.counter_id;
        ids.put(this.employee_id, true);
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        if(ids.containsKey(employee_id)){
            throw new IllegalArgumentException("the given ID " + employee_id + " for the employee "+ this.getName() + " already exists");
        }
        ids.put(employee_id, true);
        this.employee_id = employee_id;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    @Override
    public String toString(){
        return "Employee: " + this.employee_id  + " " + this.name + " " + "Salary: " + this.salary + "\n";
    }
}
