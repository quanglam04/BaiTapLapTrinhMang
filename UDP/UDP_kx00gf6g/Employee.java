package BaiTap.UDP.UDP_kx00gf6g;

import java.io.Serializable;

public class Employee implements Serializable {
  private String id;
  private String name;
  private double salary;
  private String hireDate;
  private static final long serialVersionUID = 20261107L;

  public Employee(String id, String name, double salary, String hireDate) {
    this.id = id;
    this.name = name;
    this.salary = salary;
    this.hireDate = hireDate;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", salary=" + salary +
        ", hireDate='" + hireDate + '\'' +
        '}';
  }


}
