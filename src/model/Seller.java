package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.DBException;

public class Seller {
    private Integer id;
    private String name;
    private String email;
    private java.util.Date birthDate;
    private Double baseSalary;
    private Department department;

    public Seller(Integer id, String name, String email, java.util.Date birthDate, Double baseSalary, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public void intertToDatabase(Connection conn) throws SQLException, DBException {
        PreparedStatement st = conn.prepareStatement(
            "INSERT INTO seller "
            + "(Name, Email, Birthdate, BaseSalary, DepartmentId) "
            + "VALUES "
            + "(?, ?, ?, ?, ?)"
        );

        st.setString(1, this.getName());
        st.setString(2, this.getEmail());
        st.setDate(3, new java.sql.Date(this.getBirthDate().getTime()));
        st.setDouble(4, this.getBaseSalary());
        st.setInt(5, this.getDepartment().getId());

        int rowsAffected = st.executeUpdate();

        if (rowsAffected != 1) {
            throw new DBException("Unexpected error! No rows affected!");
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.util.Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(java.util.Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
