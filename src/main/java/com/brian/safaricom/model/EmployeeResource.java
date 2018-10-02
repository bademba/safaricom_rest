package com.brian.safaricom.model;

import com.brian.safaricom.db.DBConnector;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import todaysstocks.api.response.BasicResponse;

/**
 *
 * @author Brian Ademba <brian.ademba@gmail.com>
 */
@Path("/todo")
public class EmployeeResource {

    //get a list of all employees
    @GET
    @Path("/getAllEmployees")
    @Produces({MediaType.APPLICATION_JSON})
    @JsonInclude(Include.NON_NULL)
    public List<Employee> getAllEmployees() throws JsonProcessingException {
        List<Employee> list = new ArrayList<Employee>();
        Employee employee = null;
        Connection con = null;
        try {
            con = DBConnector.getDBConnection();
            String sql = "select e.id, e.employeename,e.email, e.age , e.department,e.reportingdate,e.phonenumber,e.idtype,e.idnumber,c.code, "
                    + "e.fk_academics,e.fk_academics_masters,e.fk_academics_phd,e.street,e.housenumber,e.zipcode,e.boxnumber from employee e join academics a on e.fk_academics= a.id join country c on e.nationality=c.id ;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                Education education = new Education();
                education.undergraduate = rs.getString("fk_academics");
                education.masters = rs.getString("fk_academics_masters");
                education.phd = rs.getString("fk_academics_phd");

                EmployeeAddress address = new EmployeeAddress();
                address.street = rs.getString("street");
                address.houseNumber = rs.getString("housenumber");
                address.zipCode = rs.getString("zipcode");
                address.boxNumber = rs.getString("boxnumber");

                employee = new Employee();
                employee.id = rs.getInt("id");
                employee.employeeName = rs.getString("employeename");
                employee.emailAddress = rs.getString("email");
                employee.age = rs.getInt("age");
                employee.department = rs.getString("department");
                employee.reportingDate = rs.getDate("reportingdate");
                employee.phoneNumber = rs.getString("phonenumber");
                employee.identificationType = rs.getString("idtype");
                employee.identificationNumber = rs.getString("idnumber");
                employee.nationality = rs.getString("code");
                employee.employeeAddress = address;
                employee.education = education;

                list.add(employee);
//                Gson gson = new Gson();
//                String getall = null;
//                getall = gson.toJson(employee);
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
                System.out.print(jsonString);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    //add new Employee
    @POST
    @Path("/addEmployee")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDetail(Employee employee) {

        PreparedStatement ps = null;
        Connection conn = null;
        conn = DBConnector.getDBConnection();
        EmployeeAddress emp_Address = new EmployeeAddress();
        employee.employeeAddress = emp_Address;
        Education ed = new Education();
        employee.education = ed;
        try {

            String insertTableSQL = "INSERT INTO employee"
                    + "(employeename, email, age, department, reportingdate,phonenumber,fk_academics,fk_academics_masters,fk_academics_phd,street,housenumber,zipcode,boxnumber,idtype,idnumber,nationality) VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,(select id from country where code = ?) )";
            ps = conn.prepareStatement(insertTableSQL);

            ps.setString(1, employee.getEmployeeName());
            ps.setString(2, employee.getEmailAddress());
            ps.setInt(3, employee.getAge());
            ps.setString(4, employee.getDepartment());
            ps.setDate(5, employee.getReportingDate());
            ps.setString(6, employee.getPhoneNumber());
            ps.setString(7, ed.getPhd());
            ps.setString(8, ed.getMasters());
            ps.setString(9, ed.getUndergraduate());
            ps.setString(10, emp_Address.getStreet());
            ps.setString(11, emp_Address.getHouseNumber());
            ps.setString(12, emp_Address.getZipCode());
            ps.setString(13, emp_Address.getBoxNumber());
            ps.setString(14, employee.getIdentificationType());
            ps.setString(15, employee.getIdentificationNumber());
            ps.setString(16, employee.getNationality());

            // execute insert SQL stetement
            int add_detail = ps.executeUpdate();

            if (add_detail == 1) {
                System.out.println("New employee " + employee.employeeName + " created");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (conn != null) {/*conn.close();*/

            }
        }

        String result = "{\n"
                + "\"status\":\"OK\",\n"
                + "\"httpStatus\":\"" + Response.status(200) + "\",\n"
                + "\"name\":\"" + employee.getEmployeeName() + "\",\n"
                + "\"email\":\"" + employee.getEmailAddress() + "\",\n"
                + "\"age\":\"" + employee.getAge() + "\",\n"
                + "\"department\":\"" + employee.getDepartment() + " \",\n"
                + "\"reportingdate\":\"" + employee.getReportingDate() + " \"\n"
                + "}";
        return Response.status(201).entity(result).build();

    }

    //updating employee date
    @PUT
    @Path("/updateReportingDate")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReportingDate(Employee employee) {

        PreparedStatement ps = null;
        Connection conn = null;
        conn = DBConnector.getDBConnection();
        try {

            //String insertTableSQL = "update employee set reportingdate = reportingdate + ? where email = ? ";
            String updateEmployee = "update employee set reportingdate = date_add(reportingdate, interval ? day) where email = ? ";

            ps = conn.prepareStatement(updateEmployee);

            ps.setInt(1, employee.getDays());
            System.out.println(employee.getDays());

            ps.setString(2, employee.getEmailAddress());

            // execute update SQL stetement
            int add_detail = ps.executeUpdate();

            if (add_detail == 1) {
                System.out.println("Employee " + employee.employeeName + " updated");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (conn != null) {/*conn.close();*/

            }
        }

        String result = "{\n"
                + "\"status\":\"OK\",\n"
                + "\"message\":\"Reporting date updated\",\n"
                + "}";
        return Response.status(201).entity(result).build();

    }

    //retrieve employee by email address
    @GET
    @Path("/getByEmail/{e}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getByEmail(@PathParam("e") String e) throws JsonProcessingException {
        String email = e;
        try {
            Employee employee = new Employee();

            Connection con = null;
            con = DBConnector.getDBConnection();
            PreparedStatement pstmt = null;
            String sql = " select em.id,em.employeename,em.email,em.age,em.department,em.reportingdate,em.phonenumber,em.idtype,em.idnumber,c.code,em.fk_academics,em.fk_academics_phd "
                    + " ,em.fk_academics_masters,em.street,em.housenumber,em.zipcode,em.boxnumber from employee em join country c on em.nationality=c.id where em.email=? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, e);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                Education ed = new Education();
                ed.phd = rs.getString("fk_academics_phd");
                ed.masters = rs.getString("fk_academics_masters");
                ed.undergraduate = rs.getString("fk_academics");

                EmployeeAddress address = new EmployeeAddress();
                address.street = rs.getString("street");
                address.houseNumber = rs.getString("housenumber");
                address.zipCode = rs.getString("zipcode");
                address.boxNumber = rs.getString("boxnumber");

                employee.id = rs.getInt("id");
                employee.employeeName = rs.getString("employeename");
                employee.emailAddress = rs.getString("email");
                employee.age = rs.getInt("age");
                employee.department = rs.getString("department");
                employee.reportingDate = rs.getDate("reportingdate");
                employee.phoneNumber = rs.getString("phonenumber");
                employee.identificationType = rs.getString("idtype");
                employee.identificationNumber = rs.getString("idnumber");
                employee.nationality = rs.getString("code");
                employee.education = ed;
                employee.employeeAddress = address;

            }

            BasicResponse res = new BasicResponse();
            String entity = employee.getEmailAddress();
            if (employee.getEmailAddress().equals(e) == false) {
                //System.out.println("Employee with email " + e + " does not exist");
                 
               return Response.serverError().entity("email does not exist").build();
                

            } else if (employee.getEmailAddress() == null) {
                // return Response.serverError().entity("email cannot be null").build();
                return Response.status(Response.Status.NOT_FOUND).entity("Email not found: " + e).build();
            } else if (e.length() > employee.getEmailAddress().length()) {
                return Response.serverError().entity("email too long").build();
            } else if (e.length() < employee.getEmailAddress().length()) {
                return Response.serverError().entity("email too short").build();
            } else {
                System.out.println("user with email " + e + "  exist");
                ObjectMapper mapper = new ObjectMapper();
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee));

            }
            return Response.ok(employee, MediaType.APPLICATION_JSON).build();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //delete employee
    @POST
    @Path("/deleteEmployee")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(Employee employee) {

        PreparedStatement ps = null;
        Connection conn = null;
        conn = DBConnector.getDBConnection();

        try {

            String deleteQuery = "delete from employee where email = ? ";

            ps = conn.prepareStatement(deleteQuery);

            ps.setString(1, employee.getEmailAddress());

            // execute update SQL stetement
            int add_detail = ps.executeUpdate();

            if (add_detail == 1) {
                System.out.println("Employee " + employee.emailAddress + " Deleted");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (conn != null) {/*conn.close();*/

            }
        }

        String result = "{\n"
                + "\"status\":\"OK\",\n"
                + "\"message\":\"Employee Deleted\",\n"
                + "}";
        return Response.status(201).entity(result).build();

    }

}
