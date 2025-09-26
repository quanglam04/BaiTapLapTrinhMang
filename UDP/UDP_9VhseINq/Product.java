package BaiTap.UDP.UDP_9VhseINq;

import java.io.Serializable;

public class Product implements Serializable {
  private String id;
  private String code;
  private String name;
  private int quantity;
  private static final long serialVersionUID = 20161107;

  public Product(){}

  public Product(String id, String name,String code, int quantity){
    this.id = id;
    this.code = code;
    this.name = name;
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id='" + id + '\'' +
        ", code='" + code + '\'' +
        ", name='" + name + '\'' +
        ", quantity=" + quantity +
        '}';
  }

  public void fixName(){
    // a = trinh quang lam
    // -> lam quang trinh
    String[] arr = name.split("\\s+");
    StringBuilder newName = new StringBuilder();
    newName.append(arr[arr.length-1]+" ");
    for(int index = 1 ;index < arr.length-1;++index)
      newName.append(arr[index]+" ");
    newName.append(arr[0]);
    this.name = newName.toString().trim();
  }
  public void fixQuantity(){
    StringBuilder sb = new StringBuilder(String.valueOf(this.quantity));
    this.quantity = Integer.parseInt(sb.reverse().toString());
  }
}
