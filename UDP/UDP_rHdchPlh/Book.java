package BaiTap.UDP.UDP_rHdchPlh;

import java.io.Serializable;

public class Book implements Serializable {
  private String id;
  private String title;
  private String author;
  private String isbn;
  private String publishDate;
  private static final long serialVersionUID = 20251107L;

  public Book(String id, String title, String author, String isbn, String publishDate) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.publishDate = publishDate;
  }

  @Override
  public String toString() {
    return "Book{" +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", isbn='" + isbn + '\'' +
        ", publishDate='" + publishDate + '\'' +
        '}';
  }

  public void chuanHoaTitle(){
    StringBuilder newTitle = new StringBuilder();
    String[] arr = this.title.split("\\s+");
    for(String word:arr)
      newTitle.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(" ");
    this.title = newTitle.toString().trim();
  }

  public void chuanHoaAuthor(){
    StringBuilder newName = new StringBuilder();
    String[] arr = this.author.split("\\s+");
    for(int i=0;i<arr.length;++i)
    {

      if(i==0) {
        newName.append(arr[i].toUpperCase());
        newName.append(",");
      }
      else{
      newName.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1).toLowerCase());

    }
      newName.append(" ");

    }
    this.author = newName.toString().trim();
  }

  public void chuanHoaISBN(){
    StringBuilder newISBN = new StringBuilder(this.isbn);
    newISBN.insert(3,'-');
    newISBN.insert(5,'-');
    newISBN.insert(8,'-');
    newISBN.insert(15,'-');
    this.isbn = newISBN.toString();
  }

  public void chuanHoaPublishDate(){
    String[] arr = this.publishDate.split("\\-");
    StringBuilder newPublishDate = new StringBuilder("");
    newPublishDate.append(arr[1]).append("/").append(arr[0]);
    this.publishDate = newPublishDate.toString();
  }
}
