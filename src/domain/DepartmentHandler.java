/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;
import io.helidon.common.http.Http;
import io.helidon.webserver.Handler;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import java.util.Map;
/**
 *
 * @author hugon
 */
public class DepartmentHandler implements Handler{
    private Map<String, Department> departmentMap;
    
    public DepartmentHandler(Map<String, Department> departmentMap){
        this.departmentMap = departmentMap;    
    }
    
    @Override
    public void accept(ServerRequest req, ServerResponse res){
        String departmentName = req.path().param("name");
        Department department = this.departmentMap.get(departmentName);
        StringBuilder resBuilder = new StringBuilder();
        resBuilder.append("Department: " + department.getDepartmentName() + "\n");
        resBuilder.append(department.getEmployeesString());
        resBuilder.append("Total Salary: " + department.getTotalSalary() + "\n");
        resBuilder.append("Average Salary: " + department.getAverageSalary());
        res.status(Http.Status.OK_200);
        res.headers().put("Content-Type", "text/plain; charset=UTF-8");
        res.send(resBuilder);
    }
}
