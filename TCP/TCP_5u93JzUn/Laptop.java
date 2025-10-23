package BaiTap.TCP.TCP_5u93JzUn;

import java.io.Serializable;

public class Laptop implements Serializable {
  private int id;
  private String code;
  private String name;
  private int quantity;
  private static final long serialVersionUID = 20150711L;

  public Laptop(int id, String code, String name, int quantity) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "Laptop{" +
        "id=" + id +
        ", code='" + code + '\'' +
        ", name='" + name + '\'' +
        ", quantity=" + quantity +
        '}';
  }

  public void process(){
    // chuyen doi ten
    String[] names = this.name.split("\\s+");
    StringBuilder newName = new StringBuilder("");
    newName.append(names[names.length-1]+" ");
    for(int i=1;i<names.length-1;++i)
      newName.append(names[i]+" ");
    newName.append(names[0]);
    this.name = newName.toString().trim();
    // chuyen doi san pham

    this.quantity = Integer.parseInt(new StringBuilder(this.quantity+"").reverse().toString());
  }
}
