package BaiTap.RMI.RMI_yPbHZcCk;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {

  public static void main(String[] args) throws Exception {
    Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
    ObjectService service = (ObjectService) registry.lookup("RMIObjectService");

    ProductX productX = (ProductX) service.requestObject("B22DCCN482","yPbHZcCk");
    System.out.println(productX.toString());
    productX.process();
    System.out.println(productX.toString());
    service.submitObject("B22DCCN482","yPbHZcCk",productX);

  }
}
