package BaiTap.UDP.UDP_6TzdcfQs;

import java.io.Serializable;
import java.util.Arrays;

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

  public void chuanHoaName(){
    String[] arr = this.name.split("\\s+");
    StringBuilder newName = new StringBuilder("");
    for(String name: arr){
      newName.append(Character.toUpperCase(name.charAt(0))).append(name.substring(1).toLowerCase()).append(" ");
    }
    this.name = newName.toString().trim();
  }

  public void chuanHoaSalary(){
    String[] arr = this.hireDate.split("-");
    String year = arr[0];
    int x = Arrays.stream(year.split("|")).mapToInt(Integer::parseInt).sum();
    this.salary = this.salary + this.salary*x/100;
  }

  public void chuanHoaHireDate(){
    String[] arr = this.hireDate.split("-");
    StringBuilder newHireDate = new StringBuilder();
    newHireDate.append(arr[2]+"/").append(arr[1]+"/").append(arr[0]);
    this.hireDate = newHireDate.toString();
  }
}
