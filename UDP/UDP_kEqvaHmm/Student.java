package BaiTap.UDP.UDP_kEqvaHmm;

import java.io.Serializable;

public class Student implements Serializable {
  private String id;
  private String code;
  private String name;
  private String email;
  private static final long serialVersionUID = 20171107;
  public Student(String code){
    this.code = code;
  }
  public Student(String id, String code, String name, String email){
    this.id = id;
    this.code= code;
    this.name = name;
    this.email = email;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id='" + id + '\'' +
        ", code='" + code + '\'' +
        ", name='" + name + '\'' +
        ", emai='" + email + '\'' +
        '}';
  }

  public void changeToStandardName(){
    StringBuilder newName = new StringBuilder();
    String[] arr = name.split("\\s+");
    for(String name: arr){
      newName.append(Character.toUpperCase(name.charAt(0))).append(name.substring(1).toLowerCase()).append(" ");

    }
    this.name = newName.toString().trim();
  }

  public void makeEmail(){
 // nguyen van tuan nam -> namnvt@ptit.edu.vn
    StringBuilder newEmail = new StringBuilder("");
    String[] arr = this.name.toLowerCase().split("\\s+");
    newEmail.append(arr[arr.length-1]);
    for(int i=0;i<arr.length-1;++i)
      newEmail.append(arr[i].charAt(0));
    newEmail.append("@ptit.edu.vn");
    this.email = newEmail.toString().trim();
  }
}
