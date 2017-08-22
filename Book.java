/**This program is to sort book information by index, 
 * add records, search a record(binary and sequential search).
 * 
 */
import java.io.Serializable;
public class Book implements Serializable
{
  private long ISBN;
  private String title;
  private int issue_year;
  private String author;
  private double price;
  private int pages;
  
  public Book()
  {
	  ISBN=1000000;
	  title=null;
	  issue_year=2000;
	  author=null;
	  price=1;
	  pages=100;
  }
  public Book(long newISBN,String newtitle,int newissue_year,String newauthor,double newprice,int newpages)
  {
	  if(newISBN<0||newtitle==null||newissue_year<-2000||newauthor==null||newprice<0||newpages<=0)
	  {
		  System.out.println("Fatal error!");
		  System.exit(0);
	  }
	  else
	  {
	      ISBN=newISBN;
	      title=newtitle;
	      issue_year=newissue_year;
	      author=newauthor;
	      price=newprice;
	      pages=newpages;
	  }
  }
  public long getISBN()
  {
	  return ISBN;
  }
  public String getTitle()
  {
	  return title;
  }
  public int getIssue_year()
  {
	  return issue_year;
  }
  public String getAuthor()
  {
	  return author;
  }
  public double getPrice()
  {
	  return price;
  }
  public int getPages()
  {
	  return pages;
  }
  public void setISBN(long newISBN)
  {
	  if(newISBN<0)
	  {
		  System.out.println("Fatal error!");
		  System.exit(0);
	  }
	  ISBN=newISBN;
  }
  public void setTitle(String newtitle)
  {
	  if(newtitle==null)
	  {
		  System.out.println("Fatal error!");
		  System.exit(0);
	  }
	  title=newtitle;
  }
  public void setIssue_year(int newissue_year)
  {
	  if(newissue_year<-2000)
	  {
		  System.out.println("Fatal error!");
		  System.exit(0);
	  }
	  issue_year=newissue_year;
  }
  public void setAuthor(String newauthor)
  {
	  if(newauthor==null)
	  {
		  System.out.println("Fatal error!");
		  System.exit(0);
	  }
	  author=newauthor;
  }
  public void setPrice(double newprice)
  {
	  if(newprice<0)
	  {
		  System.out.println("Fatal error!");
		  System.exit(0);
	  }
	  price=newprice;
  }
  public void setPages(int newpages)
  {
	  if(newpages<=0)
	  {
		  System.out.println("Fatal error!");
		  System.exit(0);
	  }
	  pages=newpages;
  }
  public String toString()
  {
	  return ISBN+" "+title+" "+issue_year+" "+author+" "+price+" "+pages;
  }
}
