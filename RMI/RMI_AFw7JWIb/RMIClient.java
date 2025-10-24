package BaiTap.RMI.RMI_AFw7JWIb;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
  public static void main(String[] args) {
    String studentCode = "B22DCCN482";
    String qCode = "AFw7JWIb";
    String serverHost = "203.162.10.109"; // Địa chỉ RMI Server
    int serverPort = 1099; // Cổng RMI Registry mặc định

    try {
      // Bước 1: Kết nối tới RMI Registry
      Registry registry = LocateRegistry.getRegistry(serverHost, serverPort);
      System.out.println("Đã kết nối tới RMI Registry tại " + serverHost + ":" + serverPort);

      // Bước 2: Tìm kiếm đối tượng từ xa
      ByteService byteService = (ByteService) registry.lookup("RMIByteService");
      System.out.println("Đã tìm thấy RMIByteService");

      // Bước 3: Gọi phương thức requestData để nhận dữ liệu
      byte[] receivedData = byteService.requestData(studentCode, qCode);
      System.out.println("Nhận được dữ liệu: " + byteArrayToString(receivedData));
      System.out.println("Dữ liệu byte: " + byteArrayToReadable(receivedData));

      // Bước 4: Mã hóa XOR với khóa "PTIT"
      String key = "PTIT";
      byte[] encryptedData = xorEncrypt(receivedData, key);
      System.out.println("Dữ liệu đã mã hóa: " + byteArrayToReadable(encryptedData));

      // Bước 5: Gửi dữ liệu đã mã hóa về server
      byteService.submitData(studentCode, qCode, encryptedData);
      System.out.println("Đã gửi dữ liệu đã mã hóa về server");

      System.out.println("Hoàn thành!");

    } catch (Exception e) {
      System.err.println("Lỗi: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Thực hiện mã hóa XOR cho mảng byte với một khóa
   * @param data Dữ liệu cần mã hóa
   * @param key Khóa mã hóa
   * @return Dữ liệu đã được mã hóa
   */
  private static byte[] xorEncrypt(byte[] data, String key) {
    byte[] keyBytes = key.getBytes();
    byte[] result = new byte[data.length];

    for (int i = 0; i < data.length; i++) {
      // Lặp lại khóa bằng cách sử dụng phép chia lấy dư
      result[i] = (byte) (data[i] ^ keyBytes[i % keyBytes.length]);
    }

    return result;
  }

  /**
   * Chuyển mảng byte thành chuỗi ASCII (nếu có thể)
   */
  private static String byteArrayToString(byte[] data) {
    try {
      return new String(data, "ASCII");
    } catch (Exception e) {
      return "[Không thể chuyển thành chuỗi]";
    }
  }

  /**
   * Hiển thị mảng byte dưới dạng dễ đọc
   */
  private static String byteArrayToReadable(byte[] data) {
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < data.length; i++) {
      sb.append(data[i] & 0xFF); // Hiển thị dưới dạng số dương
      if (i < data.length - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }
}