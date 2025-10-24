package BaiTap.TCP.TCP_v2EyWghv;

import java.io.*;
import java.net.*;
import java.util.*;

public class TCPClient {
  public static void main(String[] args) {
    String serverAddress = "203.162.10.109";
    int port = 2206;
    String studentCode = "B22DCCN482";
    String qCode = "v2EyWghv";

    Socket socket = null;

    try {
      // Bước 1: Kết nối tới server
      socket = new Socket(serverAddress, port);
      System.out.println("Đã kết nối tới server " + serverAddress + ":" + port);

      // Khởi tạo luồng input/output
      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      PrintWriter pw = new PrintWriter(os, true);

      // Bước 2: Gửi mã sinh viên và mã câu hỏi
      String message = studentCode + ";" + qCode;
      pw.println(message);
      System.out.println("Đã gửi: " + message);

      // Bước 3: Nhận dữ liệu từ server
      String receivedData = br.readLine();
      System.out.println("Nhận được: " + receivedData);

      // Bước 4: Xử lý dữ liệu - tìm chuỗi con tăng dần dài nhất
      String[] numberStrings = receivedData.split(",");
      int[] numbers = new int[numberStrings.length];
      for (int i = 0; i < numberStrings.length; i++) {
        numbers[i] = Integer.parseInt(numberStrings[i].trim());
      }

      // Tìm chuỗi con tăng dần dài nhất
      List<Integer> longestSequence = findLongestIncreasingSubsequence(numbers);

      // Tạo chuỗi kết quả
      StringBuilder result = new StringBuilder();
      for (int i = 0; i < longestSequence.size(); i++) {
        result.append(longestSequence.get(i));
        if (i < longestSequence.size() - 1) {
          result.append(",");
        }
      }
      result.append(";").append(longestSequence.size());

      // Bước 5: Gửi kết quả lên server
      pw.println(result.toString());
      System.out.println("Đã gửi kết quả: " + result.toString());

      // Bước 6: Đóng kết nối
      System.out.println("Hoàn thành!");

    } catch (IOException e) {
      System.err.println("Lỗi: " + e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        if (socket != null && !socket.isClosed()) {
          socket.close();
          System.out.println("Đã đóng kết nối");
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Tìm chuỗi con tăng dần liên tiếp dài nhất trong mảng
   */
  private static List<Integer> findLongestIncreasingSubsequence(int[] arr) {
    if (arr == null || arr.length == 0) {
      return new ArrayList<>();
    }

    List<Integer> longestSequence = new ArrayList<>();
    List<Integer> currentSequence = new ArrayList<>();

    currentSequence.add(arr[0]);

    for (int i = 1; i < arr.length; i++) {
      if (arr[i] > arr[i - 1]) {
        // Nếu số hiện tại lớn hơn số trước đó, thêm vào chuỗi hiện tại
        currentSequence.add(arr[i]);
      } else {
        // Nếu không, kiểm tra xem chuỗi hiện tại có dài hơn chuỗi dài nhất không
        if (currentSequence.size() > longestSequence.size()) {
          longestSequence = new ArrayList<>(currentSequence);
        }
        // Bắt đầu chuỗi mới
        currentSequence.clear();
        currentSequence.add(arr[i]);
      }
    }

    // Kiểm tra lần cuối
    if (currentSequence.size() > longestSequence.size()) {
      longestSequence = new ArrayList<>(currentSequence);
    }

    return longestSequence;
  }
}