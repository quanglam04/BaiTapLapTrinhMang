package BaiTap.RMI.RMI_yPbHZcCk;

import java.io.Serializable;

public class ProductX implements Serializable {
  private String id;
  private String code;
  private String discountCode;
  private int discount;
  private static final long serialVersionUID = 20171107;

  public ProductX(String id, String code, String discountCode, int discount) {
    this.id = id;
    this.code = code;
    this.discountCode = discountCode;
    this.discount = discount;
  }

  @Override
  public String toString() {
    return "ProductX{" +
        "id='" + id + '\'' +
        ", code='" + code + '\'' +
        ", discountCode='" + discountCode + '\'' +
        ", discount=" + discount +
        '}';
  }

  public void process(){
    this.discount = 0;
    for(char c : discountCode.toCharArray())
      if(Character.isDigit(c))
        this.discount += Integer.parseInt(String.valueOf(c));
  }
}
