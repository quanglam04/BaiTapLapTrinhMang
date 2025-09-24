package BaiTap.TCP.TCP_Me6jetPg;

import BaiTap.Config.Config;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 [Mã câu hỏi (qCode): Me6jetPg].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: <br>
 a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".<br>
 Ví dụ: "B16DCCN999;C89DAB45"<br>
 b. Nhận dữ liệu từ server là một chuỗi các số nguyên được phân tách bởi ký tự ",".<br>
 Ví dụ: "8,4,2,10,5,6,1,3"<br>
 c. Tính tổng của tất cả các số nguyên tố trong chuỗi và gửi kết quả lên server.<br>
 Ví dụ: Với dãy "8,4,2,10,5,6,1,3", các số nguyên tố là 2, 5, 3, tổng là 10. Gửi lên server chuỗi "10".<br>
 d. Đóng kết nối và kết thúc chương trình.<br>
 */

public class Client{
  private static final int SERVER_PORT = 2206;
  private static final String SERVER_HOST = Config.SERVER_HOST;
  public static void main(String[] args) throws IOException {
      Socket socket = null;
    InputStream ip = null;
    OutputStream os = null;

    socket = new Socket(SERVER_HOST,SERVER_PORT);
    ip = socket.getInputStream();
    os = socket.getOutputStream();

    String qCode = "Me6jetPg";
    String message = Config.STUDENT_CODE+";"+qCode;

    os.write(message.getBytes());
    os.flush();
    System.out.println("Đã gửi message lên server");

    byte[] buffer = new byte[1024];
    int bytesRead = ip.read(buffer);
    if(bytesRead!=-1){
      String data = new String(buffer,0,bytesRead);
      System.out.println(">>>> Data từ server: "+data);
      String result = process(data);
      os.write(result.getBytes());
      os.flush();
      System.out.println("Đã xử lý xong và gửi kết quả lên server: "+result);
    }

    socket.close();
  }

  public static boolean isPrime(int x){
    if(x<2) return false;
    for(int i=2;i*i<=x;++i)
      if(x%i==0)
        return false;
    return true;
  }

  public static String process(String data){
    String[] arr = data.split(",");
    return String.valueOf(Arrays.stream(arr)
        .mapToInt(Integer::parseInt)
        .filter((x) -> isPrime(x))
        .sum());

  }
}


