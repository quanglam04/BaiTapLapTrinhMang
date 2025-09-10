package BaiTap.TCP_Zc61xXOq;

import BaiTap.Config.Config;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * [Mã câu hỏi (qCode): Zc61xXOq].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s).
 * Yêu cầu xây dựng chương trình client thực hiện kết nối tới server trên sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
 * a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;C64967DD" <br>
 * b.	Nhận dữ liệu từ server là một chuỗi gồm các giá trị nguyên được phân tách với nhau bằng  "|" <br>
 * Ex: 2|5|9|11 <br>
 * c.	Thực hiện tìm giá trị tổng của các số nguyên trong chuỗi và gửi lên server <br>
 * Ex: 27 <br>
 * d.	Đóng kết nối và kết thúc <br>
 */
public class Client {
  private static int SERVER_POST = 2206;
  private static String SERVER_HOST = Config.SERVER_HOST;

  public static void main(String[] args) throws IOException {
    Socket socket = new Socket(SERVER_HOST,SERVER_POST);
    InputStream ip = socket.getInputStream();
    OutputStream os = socket.getOutputStream();

    String qCode = "Zc61xXOq";
    String message = Config.STUDENT_CODE +";" +qCode;

    os.write(message.getBytes());
    os.flush();

    byte[] buffer = new byte[1024];
    int bytesRead = ip.read(buffer);
    if(bytesRead!=-1) {
      String data = new String(buffer,0,bytesRead);
     System.out.println("Đã nhận dữ liệu từ server");
      String result = String.valueOf(process(data));
      os.write(result.getBytes());
      os.flush();
      System.out.println("Xử lý xong: "+result);
    }

    socket.close();


  }

  public static int process(String data){
    String[] arr = data.split("\\|");
    return Arrays.stream(arr).mapToInt(Integer::parseInt).sum();
  }

}
