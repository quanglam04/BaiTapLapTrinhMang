package BaiTap.TCP.TCP_4YGkQxwP;

import BaiTap.Config.Config;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 [Mã câu hỏi (qCode): 4YGkQxwP].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng byte (BufferedWriter/BufferedReader) theo kịch bản sau: <br>
 a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;EC4F899B"<br>
 b.	Nhận một chuỗi ngẫu nhiên là danh sách các một số tên miền từ server<br>
 Ví dụ: giHgWHwkLf0Rd0.io, I7jpjuRw13D.io, wXf6GP3KP.vn, MdpIzhxDVtTFTF.edu, TUHuMfn25chmw.vn, HHjE9.com, 4hJld2m2yiweto.vn, y2L4SQwH.vn, s2aUrZGdzS.com, 4hXfJe9giAA.edu<br>
 c.	Tìm kiếm các tên miền .edu và gửi lên server<br>
 Ví dụ: MdpIzhxDVtTFTF.edu, 4hXfJe9giAA.edu<br>
 d.	Đóng kết nối và kết thúc chương trình.<br>
 */


public class TCPClient {
  private static final int SERVER_PORT = 2208;
  private static final String SERVER_HOST = Config.SERVER_HOST;
  public static void main(String[] args) {
    Socket socket = null;
    BufferedWriter writer = null;
    BufferedReader reader = null;

    try {
      socket = new Socket(SERVER_HOST,SERVER_PORT);
      System.out.println("Đã kết nối tới server: " + SERVER_HOST + ":" + SERVER_PORT);

      writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      String qCode = "4YGkQxwP";
      String initialMessage = Config.STUDENT_CODE+";"+qCode;

      writer.write(initialMessage);
      writer.newLine();
      writer.flush();
      System.out.println("Gửi thông điệp: " + initialMessage);

      String domains = reader.readLine();
      System.out.println("Data từ server:  " + domains);

      String results = process(domains);

      writer.write(results);
      writer.newLine();
      writer.flush();

    } catch (UnknownHostException e) {
      System.err.println("Không thể tìm thấy host: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Lỗi I/O: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Lỗi: " + e.getMessage());
    } finally {
      try {
        if (writer != null) {
          writer.close();
        }
        if (reader != null) {
          reader.close();
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
   *
   * Xử lý: Tìm các tên miền .edu từ 1 list tên miền cho trước
   * @param domains
   * @return Các tên miền .edu
   */

  public static String process(String domains){
    String[] arr = domains.split(",");
    ArrayList<String> result = new ArrayList<>();
    StringBuilder sb = new StringBuilder("");
    for(int index = 0; index<arr.length;++index) {
      String[] parts = arr[index].split("\\.");
      if(parts[1].equals("edu")){
        result.add(arr[index]);
      }
    }
    for(String x: result){
      sb.append(x);
      if(!x.equals(result.get(result.size()-1)))
        sb.append(",");
    }
    return sb.toString();
  }
}
