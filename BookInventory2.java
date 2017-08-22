import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.InputMismatchException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.EOFException;
import java.util.StringTokenizer;
public class BookInventory2 
{
  private static Book[] bkArr;//declare an array of Book type.
  private static int size=0;//track the length of array "bkArr".
  /**track the number of comparisons the binary search used".It is not 0 because 
   * if program find the target ISBN in the first time.Those methods will not execute,
   * the count_binary will not increase.But actually program tried once.
   */
  private static int count_binary=1;
  private static int count_sequential=1;//track the number of comparisons the sequential search used"
  private static long maxISBN;
  /**To obtain the biggest ISBN in the file, which has a input file stream parameter.
   * The stream shall be closed finally.
   */ 
  private static void getMaxISBN(BufferedReader inputFile)
  {
	  try
	    {
		   String line=null;
		   for(int i=0;i<size;i++)//the for-loop find the last line of the file.
		   {
		     line=inputFile.readLine();
		   }
		   /**using class StringTokenizer to get the last ISBN, and using parseLong method
		    * to convert a string to long type.
		    */
		   StringTokenizer st=new StringTokenizer(line);
		   maxISBN=Long.parseLong(st.nextToken());
		   inputFile.close();
	    }
	    catch(IOException e)
	 	{
		   System.out.println("Error reading from the text.");
		}
  }
  /**To obtain the length of array, which has a input file stream parameter.
   * The stream shall be closed finally.
   */
  private static void getArraySize(BufferedReader inputFile)
  {	  
    try
    {
	   String line=inputFile.readLine();
	   while(line!=null)//the while-loop check the end of a file.
	   {
	     size++;
	     line=inputFile.readLine();
	   }	 
	     inputFile.close();
    }
    catch(IOException e)
 	{
	   System.out.println("Error reading from the text.");
	}	  
  }  
  private static void addRecords(PrintWriter outputFileName)
  {		
      Scanner keyboard=new Scanner(System.in);
	  boolean done=false;//controls inner while-loop.If the user get InputMismatchException,the loop will not stop.
  	  boolean quit=false;//controls outer while-loop.If the user want to continue to add records, the loop will not stop.
	  boolean exit_loop=false;//controls inner while-loop.If the user did not enter "y","Y","N" or "n",the loop will not stop.
	  boolean add=true;//controls inner while-loop.If the user did not enter "y","Y","N" or "n",the loop will not stop.
	  System.out.println("========================================");
	  System.out.println("Please add a new record.");
	  System.out.println("========================================");
	  //initialize following attributes.
	  long isbn=0;
	  String titles=null;
	  int issueYear=2000;
	  String authorName=null;
	  double prices=0;
	  int page=1;
	  String yesOrNo=null;
	  String addOrNo=null;
	  while(!quit)
	  {				
        System.out.print("Please enter title name(String type): ");
	    titles=keyboard.nextLine();
	    System.out.print("Please enter author name(String type): ");
	    authorName=keyboard.nextLine();
	    /**put the statement here is necessary because if the outer while-loop execute the second time,
	     * done=true,so the while(!done) loop will never execute again.
	     */
	    done=false;
		while(!done)
		{
		  try
		  {
			System.out.print("Please enter ISBN number(Long type): ");
		    isbn=keyboard.nextLong();//if the input is not long,it will throw InputMismatchException, the while loop will execute again.
		    if(isbn<=maxISBN)
		    	throw new Exception("The ISBN you entered should be bigger than "+maxISBN+".");
		    keyboard.nextLine();//clear the line.
		    done=true;
		  }
		  catch(InputMismatchException e2)
		  {
			keyboard.nextLine();//clear the line.
			System.out.print("This is not a correct type, ");
		  }
		  catch(Exception e5)
		  {
			  System.out.print(e5.getMessage());
		  }
		}
		done=false;
		while(!done)
		{
		  try
		  {		   
		    System.out.print("Please enter issue year(int type): ");
		    issueYear=keyboard.nextInt();
		    keyboard.nextLine();
		    done=true;
		  }
	  	  catch(InputMismatchException e2)
		  {
	  		keyboard.nextLine();
	  		System.out.print("This is not a correct type, ");
		  }
		}		
		done=false;
		while(!done)
		{
		  try
		  {		   		    
		    System.out.print("Please enter price(double type): ");
		    prices=keyboard.nextDouble();
		    keyboard.nextLine();	
		    done=true;
		  }
	  	  catch(InputMismatchException e2)
		  {
	  		keyboard.nextLine();
	  		System.out.print("This is not a correct type, ");
		  }
		}
		done=false;
		while(!done)
		{
		  try
		  {		
			System.out.print("Please enter number of pages(int type): ");  
		    page=keyboard.nextInt();
		    keyboard.nextLine();
		    done=true;
		  }
	  	  catch(InputMismatchException e2)
		  {
	  		keyboard.nextLine();
	  		System.out.print("This is not a correct type, ");
		  }
		}
		/**put the statement here is necessary because if the outer while-loop execute the second time,
	     * add=false,so the while(add) loop will never execute again.
	     */
		add=true;
		while(add)
		{			
			System.out.print("Are you sure to add the record you entered? Enter \"y\" for Yes or \"n\" for No:");
			addOrNo=keyboard.nextLine();
			try
			{
				if(!(addOrNo.equalsIgnoreCase("y")||addOrNo.equalsIgnoreCase("n")))
			    	throw new Exception("Please Enter \"y\" for Yes or \"n\" for No:");
				add=false;
				if(addOrNo.equalsIgnoreCase("n"))
					System.out.println("What you entered were ignored.");
				else
			        outputFileName.println(isbn+" "+titles+" "+issueYear+" "+authorName+" "+prices+" "+page);//write record in output file.
			}
			catch(Exception e2)
			{
				System.out.println(e2.getMessage());
			}
		}						
		System.out.print("Do you want to add more records? Enter \"y\" for continue or \"n\" for stop:");
		/**put the statement here is necessary because if the outer while-loop execute the second time,
	     * exit_loop=true,so the while(!exit_loop) loop will never execute again.
	     */
		exit_loop=false;
		while(!exit_loop)
		{
		  try
		  {		 
		    yesOrNo=keyboard.nextLine();		    
		    if(!(yesOrNo.equalsIgnoreCase("y")||yesOrNo.equalsIgnoreCase("n")))
		    	throw new Exception("Please Enter \"y\" for continue or \"n\" for stop:");
		    exit_loop=true;
		    if(yesOrNo.equalsIgnoreCase("n"))
				quit=true;		
			else
			{
				System.out.println("========================================");
				System.out.println("You choose to add another record.");
				System.out.println("========================================");
			}
		  }	  	 
		  catch(Exception e3)
		  {
			  System.out.println(e3.getMessage());
		  }
		}							
	  }
	  outputFileName.close();
   }
  /**accepts an input file stream name and display the contents of this file to the screen.
   */
   private static void displayFileContents(BufferedReader fileName)
   {
	  try
	  {
	    String line=fileName.readLine();
	    while(line!=null)
	    {
		  System.out.println(line);
		  line=fileName.readLine();
	    }
	    fileName.close();//close the input stream.
	  }
	  catch(IOException e)
	  {
		  System.out.println("Error reading from the text.");
	  }	  
   }
   /**accepts an input file stream name and display the contents of this file to the screen.
    * It is better to use Scanner rather than bufferedReader because Scanner class has nextLong,nextDouble...methods which are
    * very convenient to use for array.
    */
   private static void fillArray(Scanner inputFile)
   {
	  bkArr=new Book[size];//create the number of "size" of Book object and assign them to bkArr.	
	  /**the for loop is try to copy all the records of input file to bkArr.
	   */
	  for(int i=0;i<size;i++)
	  {
		  bkArr[i]=new Book(inputFile.nextLong(),inputFile.next(),inputFile.nextInt(),inputFile.next(),inputFile.nextDouble(),inputFile.nextInt());
	  }	
	  inputFile.close();//close the input stream.
   }
   /**Use binary search method to find the target ISBN.Assume all the ISBN in the file are in order.
    */
   private static void binaryBookSearch(Book[] arr,int start,int end,long isbn)
   {
	  int mid=(start+end)/2;
	  while(arr[mid].getISBN()!=isbn&&start<=end)
	  {
		count_binary++;//track the number of comparisons.
		if(arr[mid].getISBN()>isbn)
			end=mid-1;
		else
			start=mid+1;
		mid=(start+end)/2;
	  }
	  if(start<=end)
		System.out.println("The ISBN was found in the text, the binary search found the ISBN after "+count_binary+" comparisons.");
	  else
		System.out.println("The ISBN was not found in the text, the binary search made "+count_binary+" comparisons.");
   }
   /**Use sequential search method to find the target ISBN.Assume all the ISBN in the file are in order.
    */
   private static void sequentialBookSearch(Book[] arr,int start,int end,long isbn)
   {
	 int i=0;
	 for(i=start;i<end+1;i++)//the index of array is 1 less than the order.
	 {
		if(arr[i].getISBN()!=isbn)
			count_sequential++;//track the number of comparisons.
		else
			break;
	 }
	 if(count_sequential<=i+1)
		System.out.println("The ISBN was found in the text, the sequential search found the ISBN after "+count_sequential+" comparisons.");
	 else
		System.out.println("The ISBN was not found in the text, the sequential search made "+count_sequential+" comparisons.");
   }
   /**accepts an output file stream name to write an array to a binary file.
    */
   private static void toBinaryFile(ObjectOutputStream fileName)
   {
	   try
	   {
	       fileName.writeObject(bkArr);
	   }
	   catch(IOException e)
	   {
		 System.out.println("Error writing to file.");
	   }
   }
   /**accepts an input file stream name to read an a binary file and output on the screen.
    */
   private static void readBinaryFile(ObjectInputStream fileName)
   {
	   Book[] bk=null;
	   try
	   {		   
		  bk=(Book[])fileName.readObject();
	   }
	   catch(ClassNotFoundException e2)//firstly check if the casting is legal or not.
	   {
		  System.out.println("Problem opening files.Program will terminate.");
		  System.exit(0);
	   }
	   catch(IOException e)//second, check if there is problem for file input.
	   {
		 System.out.println("Problem with file input.");
	   }
	   System.out.println("The following array elements were rad from the file:");
	   for(int i=0;i<bk.length;i++)//output all the content in the array.
		   System.out.println(bk[i]);
   }
   public static void main(String[] args)
   {	 	 
	  BufferedReader br=null;
	  PrintWriter pw=null;
	  /**the first try-catch is to open the input stream for getting the size 
	   * of array.
	   */
	  try
	  {
		  br=new BufferedReader(new FileReader("Sorted_Book_Info.txt"));
		  getArraySize(br);
	  }
	  catch(FileNotFoundException e)
	  {
		  System.out.println("Problem opening files.Program will terminate.");
		  System.exit(0);
	  }
	  /**the second try-catch is to re-open the input stream for getting the maxISBN 
	   * of the file.
	   */
	  try
	  {
		  br=new BufferedReader(new FileReader("Sorted_Book_Info.txt"));
		  getMaxISBN(br);
	  }
	  catch(FileNotFoundException e)
	  {
		  System.out.println("Problem opening files.Program will terminate.");
		  System.exit(0);
	  }
	  /**the third try-catch is to add records to the output file.*/
	  try
	  {
		  pw=new PrintWriter(new FileOutputStream("Sorted_Book_Info.txt",true));		  
		  addRecords(pw);
	  }
	  catch(FileNotFoundException e)
	  {
		  System.out.println("Problem opening files.Program will terminate.");
		  System.exit(0);
	  }
	  /**the fourth try-catch is to display all the records of input file.*/
	  
	  try
	  {
		  br=new BufferedReader(new FileReader("Sorted_Book_Info.txt"));
		  System.out.println();
		  displayFileContents(br);
		  System.out.println();
	  }
	  catch(FileNotFoundException e)
	  {
		  System.out.println("Problem opening files.Program will terminate.");
		  System.exit(0);
	  }
	  /**the fifth try-catch is to re-open the input stream for reading the 
	   * contents of the file to the array.
	   */
	  Scanner inputF=null;
	  try
	  {
		  inputF=new Scanner(new FileInputStream("Sorted_Book_Info.txt"));
		  fillArray(inputF);		  
	  }
	  catch(FileNotFoundException e)
	  {
		  System.out.println("Problem opening files.Program will terminate.");
		  System.exit(0);
	  }
	  /**invoke the method binaryBookSearch(),sequentialBookSearch() to find target ISBN and track the */
	  System.out.println("Search the ISBN 957877747 and compare two search methods.");
	  binaryBookSearch(bkArr,0,14,957877747);
	  sequentialBookSearch(bkArr,0,14,957877747);
	  
	  if(count_binary<count_sequential)
		  System.out.println("Binary search is better than sequential search.");  
	  else if(count_binary>=count_sequential)
		  System.out.println("Binary search is not better than sequential search.");
	  System.out.println();
	  
	  //let count_binary and count_sequential to be 1 again so that count them again.
	  count_binary=1;
	  count_sequential=1;
	  System.out.println("Search the ISBN 200900210 and compare two search methods.");
	  binaryBookSearch(bkArr,0,14,200900210);
	  sequentialBookSearch(bkArr,0,14,200900210);
	  if(count_binary<count_sequential)
		  System.out.println("Binary search is better than sequential search.");  
	  else if(count_binary>=count_sequential)
		  System.out.println("Binary search is not better than sequential search.");
	  System.out.println();
	  //let count_binary and count_sequential to be 1 again so that count them again.
	  
	  count_binary=1;
	  count_sequential=1;
	  System.out.println("Search the ISBN 798887166 and compare two search methods.");
	  binaryBookSearch(bkArr,0,14,798887166);
	  sequentialBookSearch(bkArr,0,14,798887166);
	  if(count_binary<count_sequential)
		  System.out.println("Binary search is better than sequential search.");  
	  else if(count_binary>=count_sequential)
		  System.out.println("Binary search is not better than sequential search.");
	  System.out.println();
	  //let count_binary and count_sequential to be 1 again so that count them again.
	  
	  count_binary=1;
	  count_sequential=1;
	  System.out.println("Search a not existed ISBN 555555555 and compare two search methods.");
	  binaryBookSearch(bkArr,0,14,555555555);
	  sequentialBookSearch(bkArr,0,14,555555555);
	  if(count_binary<count_sequential)
		  System.out.println("Binary search is better than sequential search.");  
	  else if(count_binary>=count_sequential)
		  System.out.println("Binary search is not better than sequential search.");
	  System.out.println();
	  System.out.println("==============================================================Conclusion==================================================================");
	  System.out.println("Sequential search: the worst case is that it can not find the target one.It needs n+1 times(n is the length of array).");
	  System.out.println("                   the second worst case is that it needs n times(length of array) to find target one.");
	  System.out.println("                   the best case is that it needs 1 time(at the beginning of an array) to find target one.");
	  System.out.println("Binary search:     the worst case is that it can not find the target one.It needs (integer of log2(n))+1 times(n is the length of array).");
	  System.out.println("                   the second worst case is that it needs integer of log2(n) times(length of an array) to find target one.");
	  System.out.println("                   the best case is that it needs 1 time(middle of an array) to find target one.");
	  System.out.println("==========================================================================================================================================");
	  /**the sixth try-catch is to re-open the output stream for writing the 
	   * contents of the array to the binary file.
	   */	  
	  try
	  {
		  ObjectOutputStream outputF=new ObjectOutputStream(new FileOutputStream("Book.dat"));	
		  toBinaryFile(outputF);
		  outputF.close();
	  }
	  catch(FileNotFoundException e)
	  {
		  System.out.println("Problem opening files.Program will terminate.");
		  System.exit(0);
	  }	  
	  catch(IOException e)
	  {
		  System.out.println("Problem opening files.Program will terminate.");
		  System.exit(0);
	  }
	  /**the seventh try-catch is to re-open the input stream for reading the 
	   * contents of the file to the array.
	   */
	  try
	  {
		  ObjectInputStream readF=new ObjectInputStream(new FileInputStream("Book.dat"));	
		  readBinaryFile(readF);
		  readF.close();
	  }
	  catch(FileNotFoundException e)
	  {
		  System.out.println("Problem opening files.Program will terminate.");
		  System.exit(0);
	  }
	  catch(IOException e)
	  {
		  System.out.println("Problem reading files.Program will terminate.");
		  System.exit(0);
	  }
	  System.out.println("====================================");
	  System.out.println("End of program.");
	  System.out.println("====================================");
   }
}
