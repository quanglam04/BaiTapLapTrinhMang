package BaiTap.TCP.TCP_v2EyWghv;

import BaiTap.Config.Config;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TCPClient {
  public static final int SERVER_PORT = 2206;
  public static final String SERVER_HOST = Config.SERVER_HOST;
  public static final String Q_CODE = "v2EyWghv";

  public static void main(String[] args) {
    // Sử dụng try-with-resources để đảm bảo Socket và luồng được đóng an toàn
    try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
        InputStream ip = socket.getInputStream();
        OutputStream op = socket.getOutputStream()) {

      // a. Gửi mã sinh viên và mã câu hỏi: "studentCode;qCode"
      String initMessage = Config.STUDENT_CODE + ";" + Q_CODE;
      op.write(initMessage.getBytes(StandardCharsets.US_ASCII));
      op.flush();
      System.out.println("-> Sent initialization: " + initMessage);

      // b. Nhận dữ liệu từ server
      byte[] buffer = new byte[4096]; // Tăng kích thước buffer để đảm bảo nhận đủ mảng lớn
      int byteRead = ip.read(buffer);

      if (byteRead > 0) {
        String dataString = new String(buffer, 0, byteRead, StandardCharsets.US_ASCII).trim();
        System.out.println("<- Received data: " + dataString);

        // Chuyển chuỗi CSV thành mảng số nguyên
        int[] numbers;
        try {
          numbers = Arrays.stream(dataString.split(","))
              .mapToInt(Integer::parseInt)
              .toArray();
        } catch (NumberFormatException e) {
          System.err.println("Error parsing received data: " + e.getMessage());
          return;
        }

        // c. Xử lý tìm chuỗi con tăng dần dài nhất (Longest Increasing SUBARRAY)
        // Lưu ý: Nếu Server yêu cầu LIS (Subsequence), hàm này sẽ sai.
        String resultMessage = findLongestIncreasingSubarray(numbers);

        // Gửi kết quả lên server theo format: "chuỗi tăng dài nhất; độ dài"
        op.write(resultMessage.getBytes(StandardCharsets.US_ASCII));
        op.flush();
        System.out.println("-> Sent result: " + resultMessage);

      } else if (byteRead == 0) {
        System.out.println("Server sent no data.");
      } else {
        System.out.println("Server closed connection.");
      }

      // d. Đóng kết nối và kết thúc chương trình (xử lý bởi try-with-resources)
    } catch (IOException e) {
      System.err.println("Connection or I/O error occurred: " + e.getMessage());
    }
    System.out.println("--- Client program finished ---");
  }

  /**
   * Thuật toán tìm chuỗi con tăng dần dài nhất (Longest Increasing SUBARRAY - Liên tiếp)
   * @param numbers Mảng số nguyên đầu vào
   * @return Chuỗi kết quả theo định dạng "chuỗi tăng dài nhất;độ dài"
   */
  private static String findLongestIncreasingSubarray(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
      return ";" + 0;
    }

    int maxLength = 1;
    int currentLength = 1;
    int maxStartIndex = 0;
    int currentStartIndex = 0;

    for (int i = 1; i < numbers.length; i++) {
      if (numbers[i] > numbers[i - 1]) {
        currentLength++;
      } else {
        // Đứt chuỗi: Reset độ dài về 1 và vị trí bắt đầu là i
        currentLength = 1;
        currentStartIndex = i;
      }

      // Cập nhật độ dài tối đa
      if (currentLength > maxLength) {
        maxLength = currentLength;
        maxStartIndex = currentStartIndex;
      }
    }

    // Trích xuất chuỗi con tăng dần dài nhất
    int[] longestSubarray = Arrays.copyOfRange(numbers, maxStartIndex, maxStartIndex + maxLength);

    // Định dạng thành chuỗi CSV (ví dụ: "5,10,20,25,50")
    String longestSubarrayString = Arrays.stream(longestSubarray)
        .mapToObj(String::valueOf)
        .collect(Collectors.joining(","));

    return longestSubarrayString + ";" + maxLength;
  }
}