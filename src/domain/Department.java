/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;
import java.util.HashMap;
import java.util.Map;
import io.helidon.common.http.Http;
import io.helidon.webserver.Handler;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
/**
 *
 * @author hugon
 */
public class Department implements Handler{
    private String departmentName;
    private static Map<String, Boolean> hm = new HashMap<>();
    private Employee[] employees;
    private int counter_employees;
    private int max_employees;
    
    public Department(String name, int max_employees){
        if(hm.containsKey(name.toLowerCase())){
            throw new IllegalArgumentException("A department with this name already exists");
        }
        this.max_employees = max_employees; 
        this.departmentName = name;
        this.employees = new Employee[this.max_employees];
        hm.put(name.toLowerCase(), true);
    
    }
    
    public String getDepartmentName(){
        return this.departmentName;    
    }
    
    public void addEmployee(Employee emp){
        if(this.employees.length > this.max_employees){
            throw new IllegalArgumentException("The department has the maximum of employees allowed");
        }
        this.employees[this.counter_employees++] = emp;
    }
    
    public Employee[] getEmployees() {
        if(this.employees == null){
            throw new IllegalArgumentException("The department has no employees");
        }
        return this.employees;
    }
    
    public int numberEmployees(){
        return this.counter_employees;
    }
    
    public String identifyEmployeeByCode(int code){
        String res = "";
        for(Employee i: this.employees){
            if(i.getEmployee_id() == code){
                res += i.toString();
            }
        }
        return res;        
    }
    
    public String getEmployeesString(){
        String res = "";
        System.out.println(this.employees[0]);
        if(this.employees == null){
            return "No hay empleados que mostrar";
        }
        for(Employee i: this.getEmployees()){
            if(i == null){
                continue;
            }
            res +=  i;  
        }
        return res;        
    }
    
    public double getTotalSalary(){
        double totalSalary = 0.0;
        for(Employee i: this.employees){
            if(i == null){
                continue;
            }
            totalSalary += i.getSalary();  
        }
        return totalSalary;
    }
    
    public double getAverageSalary(){
        return this.getTotalSalary() / this.numberEmployees();
    }
    
    @Override
    public void accept(ServerRequest req, ServerResponse res){
        StringBuilder resBuilder = new StringBuilder();
        resBuilder.append("Department: " + this.departmentName + "\n");
        resBuilder.append(this.departmentName + "\n");
        resBuilder.append(this.getEmployeesString());
        resBuilder.append("Total Salary: " + this.getTotalSalary());
        resBuilder.append("Average Salary: " + this.getAverageSalary());
        res.status(Http.Status.OK_200);
        res.headers().put("Content-Type", "text/plain; charset=UTF-8");
        res.send(resBuilder);
    }
}
