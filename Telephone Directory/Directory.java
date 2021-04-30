import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

@SuppressWarnings("resource")
public class Directory {
	
	static TreeMap<String, String> map = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
	
	static void readFromFile()		//To read and store existing data into the TreeMap
	{
		try 
		{
			Scanner sc = new Scanner(new FileReader("directory.txt"));
			String currentLine;
			
			while(sc.hasNext())
			{
				currentLine = sc.nextLine();
				String[] st = currentLine.split("  \\s+");
				map.put(st[0], st[1]);
			}
			sc.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	static void writeToFile()			//To write and update data into the file
	{
		try 
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter("directory.txt"));
			for(Entry<String, String> entry : map.entrySet())
			{
				bw.write(String.format("%-20s %-15s \n", entry.getKey(), entry.getValue() ));
			}
			bw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	static void insert() 
	{
		String name, phoneNo;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the name : ");			//Do not use nextLine() after next(), nextFoo()...
		name = sc.nextLine();							//( except nextLine() itself ) as next() leaves \n 
		System.out.print("Enter the number : ");		//in the buffer which will be accepted by the subsequent 
		phoneNo = sc.nextLine();						//nextLine().
		
		map.put(name, phoneNo);	
		writeToFile();
	}
	
	static void search()
	{
		String ch;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n***SEARCH OPERATIONS***");
		System.out.println("\n1.Search By Name\n2.Search By Phone Number\n3.Back To Menu");
		do
		{				
			System.out.print("\nEnter your prefered search option : ");
			ch = sc.nextLine();
			switch(ch)
			{
				case "1" :  System.out.print("Enter the name : ");
							String name = sc.nextLine();
							
							if(map.containsKey(name))
							{
								System.out.println("\nDetails of the person");
								System.out.println("Name : " + name);
								System.out.println("Phone Number : " + map.get(name));
							}
							else
								System.out.println("No Such Person Found!");
							break;
							
				case "2" :  System.out.print("Enter the Phone Number : ");
							String phoneNo = sc.nextLine();
							
							Set<String> allKeys = map.keySet();
							Boolean flag = false;
							
							for(String key : allKeys)
							{
								if(map.get(key).equals(phoneNo))
								{
									System.out.println("\nDetails of the person");
									System.out.println("Name : " + key);
									System.out.println("Phone Number : " + phoneNo);
									flag = true;
									break;
								}
							}
							if(!flag)
								System.out.println("No Such Person Found!");
							break;
							
				case "3" :  break;
				default  :  System.out.println("Invalid input");
			}
			
		}while(!ch.equals("3"));
	}
	
	static void display()
	{
		String ch;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n***DISPLAY OPERATIONS***");
		System.out.println("\n1.Display All\n2.Display those starting with a particular alphabet\n3.Back To Menu");
		do
		{				
			System.out.print("\nEnter your prefered display option : ");
			ch = sc.next();
			switch(ch)
			{
				case "1" :  System.out.println("\nDetails of all available people\n");
							
							if(!map.isEmpty())
							{
								for(Entry<String, String> entry : map.entrySet())
								{
									System.out.format("%-20s %-15s \n", entry.getKey(), entry.getValue());
								}
							}
							else
								System.out.println("Directory is empty!");
							break;
							
				case "2" :  System.out.print("Enter the alphabet : ");
							char alpha = sc.next().charAt(0);
							
							Set<String> allKeys = map.keySet();
							Boolean flag = false;
							
							System.out.println("\nDetails of all people with name starting with " + alpha + "\n");
							for(String key : allKeys)
							{
								if(Character.toLowerCase(key.charAt(0)) == Character.toLowerCase(alpha))
								{
									System.out.println(key + "\t\t" + map.get(key));
									flag = true;
								}
							}
							if(!flag)
								System.out.println("No person with name starting with " + alpha);
							break;
							
				case "3" :  break;
				default  :  System.out.println("Invalid input");
			}
			
		}while(!ch.equals("3"));
	}
	
	static void update()
	{
		String ch, name;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n***SEARCH OPERATIONS***");
		System.out.println("\n1.Update Details\n2.Delete\n3.Back To Menu");
		do
		{				
			System.out.print("\nEnter your prefered updation option : ");
			ch = sc.nextLine();
			switch(ch)
			{
				case "1" :  System.out.print("Enter the name : ");
							name = sc.nextLine();
							
							if(map.containsKey(name))
							{
								System.out.println("\nEnter the new Phone Number : ");
								map.put(name, sc.nextLine());
								System.out.println("\nThe new details of the person");
								System.out.println("Name : " + name);
								System.out.println("Phone Number : " + map.get(name));
								writeToFile();
							}
							else
								System.out.println("No Such Person Found!");
							break;
							
				case "2" :  System.out.print("Enter the name : ");
							name = sc.nextLine();
							
							if(map.containsKey(name))
							{
								map.remove(name);
								System.out.println("\nDetails of " + name + " deleted susccesfully");
								writeToFile();
							}
							else
								System.out.println("No Such Person Found!");
							break;
							
				case "3" :  break;
				default  :  System.out.println("Invalid input");
			}
			
		}while(!ch.equals("3"));
	}
	
	public static void main(String[] args) {
		
		String ch;
		Scanner sc = new Scanner(System.in);
		readFromFile();							//Previous data first loaded and stored into TreeMap
		
		System.out.println("***MENU***");
		System.out.println("\n1.INSERT\n2.SEARCH\n3.DISPLAY\n4.UPDATE\n5.EXIT");
		do
		{
			System.out.print("\nEnter your choice : ");
			ch = sc.next();
			switch(ch)
			{
				case "1" : insert(); break;
				case "2" : search(); break;
				case "3" : display(); break;
				case "4" : update(); break;
				case "5" : break;
				default  : System.out.println("Invaild Input");
			}
		}while(!ch.equals("5"));
	}
}