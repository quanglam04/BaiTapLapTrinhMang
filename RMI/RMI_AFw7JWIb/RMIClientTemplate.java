package BaiTap.RMI.RMI_AFw7JWIb;

import java.rmi.registry.*;

public class RMIClientTemplate {
  public static void main(String[] args) {
    try {
      Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
      /**
       *        ServiceInterface  service = (ServiceInterface) registry.lookup("ServiceName");
       *
       *        Request
       *        Phương thức 1: Lấy dữ liệu từ server
       *       DataType data = service.requestData("StudentCode", "QCode");
       *       System.out.println("Received: " + data);
       *
       *        Process
       *        Phương thức 2: Gửi dữ liệu đã xử lý về server
       *       DataType result = process(data);
       *       System.out.println("Processed: " + result);
       *
       *        Submit
       *       service.submitData("StudentCode", "QCode", result);
       *       System.out.println("Done!");
       */

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   *   private static DataType process(DataType data) {
   *     // TODO: Implement processing logic
   *     return data;
   *   }
   */


}