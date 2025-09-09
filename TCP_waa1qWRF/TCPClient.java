package BaiTap.TCP_waa1qWRF;

import BaiTap.Config.Config;
import java.net.*;
import java.io.*;

/**
 * @author Trịnh Quang Lâm
 * @since 09/09/2025<br>
 * [qCode]: waa1qWRF<br>
 */

public class TCPClient {
  private static final int SERVER_PORT = 2207;
  private static final String SERVER_HOST = Config.SERVER_HOST;
  public static void main(String[] args) {
    Socket socket = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;

    try {
      socket = new Socket(SERVER_HOST, SERVER_PORT);
      System.out.println("Đã kết nối tới server: " + SERVER_HOST + ":" + SERVER_PORT);

      dis = new DataInputStream(socket.getInputStream());
      dos = new DataOutputStream(socket.getOutputStream());

      String qCode = "waa1qWRF";
      String initialMessage = Config.STUDENT_CODE+";"+qCode;

      dos.writeUTF(initialMessage);
      dos.flush();
      System.out.println("Gửi thông điệp: " + initialMessage);

      int k = dis.readInt();
      System.out.println("Nhận k = " + k);

      String arrayString = dis.readUTF();
      System.out.println("Nhận mảng: " + arrayString);

      String result = processArray(arrayString, k);
      System.out.println("Xử lý xong!");

      dos.writeUTF(result);
      dos.flush();


    } catch (UnknownHostException e) {
      System.err.println("Không thể tìm thấy host: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Lỗi I/O: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Lỗi: " + e.getMessage());
    } finally {
      try {
        if (dis != null) {
          dis.close();
        }
        if (dos != null) {
          dos.close();
        }
        if (socket != null && !socket.isClosed()) {
          socket.close();
          System.out.println("Kết nối đã được đóng.");
        }
      } catch (IOException e) {
        System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
      }
    }
  }

  /**
   * Xử lý mảng: chia thành các đoạn có độ dài k và đảo ngược mỗi đoạn
   * @param arrayString Chuỗi chứa mảng số nguyên, phân tách bởi dấu phẩy
   * @param k Độ dài của mỗi đoạn
   * @return Chuỗi kết quả sau khi xử lý
   */
  private static String processArray(String arrayString, long k) {
    String[] numbers = arrayString.split(",");

    String[] result = new String[numbers.length];

    for (int i = 0; i < numbers.length; i += k) {
      int endIndex = (int)Math.min(i + k, numbers.length);
      for (int j = 0; j < endIndex - i; j++) {
        result[i + j] = numbers[endIndex - 1 - j].trim();
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < result.length; i++) {
      sb.append(result[i]);
      if (i < result.length - 1) {
        sb.append(",");
      }
    }

    return sb.toString();
  }
}