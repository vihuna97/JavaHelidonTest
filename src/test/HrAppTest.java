/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import domain.Department;
import domain.DepartmentHandler;
import domain.Employee;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.WebServer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author hugon
 */
public class HrAppTest {
    public static void main(String[] args) {
        Map<String, Department> departmentMap = new HashMap();
        System.out.println("Hr Application Test!");
        Employee emp1 = new Employee();
        emp1.setEmployee_id(1999);
        emp1.setName("Hugo Navas");
        emp1.setSalary(1200.00);
        Employee emp2 = new Employee();
        emp2.setEmployee_id(2000);
        emp2.setName("Victor Cruz");
        emp2.setSalary(1400.00);
        Employee emp3 = new Employee();
        emp3.setName("Catalina Cruz");
        emp3.setEmployee_id(2001);
        emp3.setSalary(1500.00);
        Department dp1 = new Department("HR", 10);
        
        Department dp2 = new Department("htR", 10);
        
        dp2.addEmployee(emp1);
        dp2.addEmployee(emp2);
        dp2.addEmployee(emp3);
        
        departmentMap.put(dp2.getDepartmentName(), dp2);
        
        System.out.println(dp2.getEmployeesString()); 
        
        DepartmentHandler departmentH = new DepartmentHandler(departmentMap);
        
        try{
            
            Routing routing = Routing.builder().get("/department/{name}", departmentH).build();
            ServerConfiguration config = ServerConfiguration.builder().bindAddress(InetAddress.getLocalHost()).port(8888).build();
            WebServer ws = WebServer.create(config, routing);
            ws.start();
        }
        catch (UnknownHostException e){
            e.printStackTrace();
        }
        
        
    }
}
