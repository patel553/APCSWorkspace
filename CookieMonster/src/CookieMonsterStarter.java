// 

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;


/**
 *  In this program Cookie Monster finds the optimal path from 
 *  the upper left corner (0,0) to the lower right corner
 *  (SIZE-1,SIZE-1) in a cookie array.  The elements of
 *  the array contain cookies (a non-negative number) or barrels
 *  (-1).  On each step Cookie Monster can only go down or
 *  to the right.  He is not allowed to step on barrels. 
 *  The optimal path contains the largest number of cookies.
 *   
 *  The program prompts the user for a file name,
 *  reads the cookie array from the file, and reports the
 *  number of cookies on the optimal path. Assumed size of the
 *  grid of values i 12 x 12, stored in row-major order.
 *  
 *  Bonus:  Adapt the program to read 2 ints from the file first
 *  representing numRows and numCols, and then read all values into
 *  a 2-d array.  (Consult FloodFill project for an example.)
 *  
 *  The program also reports the actual optimal path, location
 *  by location.
 *  
 *  Finally, the program will output every successful path found,
 *  along with the total cookies along that path.
 */


public class CookieMonsterStarter
{
  private final int SIZE = 12; //Can be altered for different files. 
  private int[][] cookies = new int[SIZE][SIZE];
  
  
  public class Location
  {
	  private int row;
	  private int col;
	  
	  public Location(int row,int col)
	  {
		  this.row=row;
		  this.col=col;
	  }
	  
	  public int getRow()
	  {
		  return row;
	  }
	  
	  public int getCol()
	  {
		  return col;
	  }
	  
	  public int cookiesAtLocation()
	  {
		  return cookies[row][col];
	  }
	  
	  public String toString()
	  {
		  return "("+row+ ", "+col+")";
	  }
  }
  
  public class Path
  {
	  private Queue<Location> locations=new LinkedList<Location>();
	  private int pathTotal=0;
	  private Location current;
	  
	  public Path()
	  {
		  
	  }
	  
	  public Path(Path p)
	  {
		  this.locations=p.locations;
		  this.pathTotal=p.pathTotal;
		  this.current=p.current;
	  }
	  
	  public int getPathTotal()
	  {
		  return pathTotal;
	  }
	  
	  public Location getLocation()
	  {
		  return current;
	  }
	  
	  public void add(Location l)
	  {
		  locations.add(l);
		  pathTotal+=cookies[l.getRow()][ l.getCol()];
		  current=l;
	  }
	  
	  public Queue<Location> getPath()
	  {
		  return locations;
	  }
	  
	  public String toString()
	  {
		  String s="";
		  
		  for(int i=0;i<locations.size();i++)
		  {
			  Location l=locations.remove();
			  s+=l.toString();
			  s+=", ";
			  locations.add(l);
		  }
		  s+="cookies="+pathTotal;
		  return s;
	  }
  }
    
  /**
   *  Reads cookies from file
   */
  private void loadCookies(Scanner input)
  {  
    for (int row = 0;   row < SIZE;   row++)  
      for (int col = 0;   col < SIZE;   col++)  
        cookies[row][col] = input.nextInt();  
  }  

  /**
   *  Returns true if (row, col) is within the array and that position is
   *  not a barrel (-1); false otherwise
   */
  private boolean goodPoint(int row, int col)  
  {  
	  return row<SIZE&&col<SIZE&&cookies[row][col]>=0;  
  }
  private boolean goodPoint(Location l)
  {
	  return l.getRow()<SIZE&&l.getCol()<SIZE&&cookies[l.getRow()][l.getCol()]>=0;  
  }

  /**
   *  Returns the largest number of cookies collected by Monster
   *  on a path from (0,0) to (SIZE-1, SIZE-1)
   */
  
  private int optimalPath()  
  {  
	  // LOTS OF STUFF FOR HERE! Plan first!
	  //go through, check down then proceed if possible and add right location and path to stack, if not move right and repeat
	  //print out paths as you go
	  //keep track of cookies on each path also
	  Stack<Path> pathStack=new Stack<Path>();
	  Location currentLoc=new Location(0,0);
	  Path currentPath=new Path();
	  ArrayList<Path> allPaths=new ArrayList<Path>();
	  
	  if(!goodPoint(0,0))
		  return 0;
	  
	  allPaths.add(new Path());
	  allPaths.get(0).add(new Location(0,0));
	//  allPaths.get(0).addPoint(new Location(1,0));
//	  
//	  Path next=new Path();
//	  next.addPoint(new Location(0,0));
//	  next.addPoint(new Location(1,0));
//	  allPaths.add(next);
	  
	  pathStack.push(allPaths.get(0));
//	  pathStack.push(allPaths.get(1));
	  
	  while(!pathStack.isEmpty())
	  {
		  currentPath=pathStack.pop();
		  currentLoc=currentPath.getLocation();
		  
		  if(goodPoint(currentLoc))
		  {
			  if(currentLoc.getRow()==SIZE-1&&currentLoc.getCol()==SIZE-1)
			  {
				  System.out.println(currentPath);
			  }
			  else
			  {
				  boolean down=goodPoint(currentLoc.getRow()+1,currentLoc.getCol());
				  boolean right=goodPoint(currentLoc.getRow(),currentLoc.getCol()+1);
				  
				  if(down&&right)
				  {
					  Path p=new Path(currentPath);
					  p.add(new Location(currentLoc.getRow(),currentLoc.getCol()+1));
					  allPaths.add(p);
					  
					  currentPath.add(new Location(currentLoc.getRow()+1,currentLoc.getCol()));
					  
					  pathStack.push(p);
					  pathStack.push(currentPath);
				  }
				  
				  else if(down)
				  {
					  currentPath.add(new Location(currentLoc.getRow()+1,currentLoc.getCol()));
					  pathStack.push(currentPath);
				  }
				  else if(right)
				  {
					  currentPath.add(new Location(currentLoc.getRow(),currentLoc.getCol()+1));
					  pathStack.push(currentPath);
				  }
			  }
			  
		  }
	  }
	  
	  int maxCookies=0;
	  for(int i=0;i<allPaths.size();i++)
	  {
		  if(allPaths.get(i).getPathTotal()>maxCookies)
			  maxCookies=allPaths.get(i).getPathTotal();
	  }
	  
	  
	  
	  return maxCookies;
  }
  
  
  
  /**  The following is something we coded together in Ch20 work:
  *		E  is an Element Type
  * 	It is a Static method:  to activate it...
  *      in another class:  someotherq= CoookieMonster.copy(someq);
  *      in this class:   		 newq = copy(q);
  *      */
  public static <E>  Queue<E>   copy(Queue<E> q){
	  
	  Queue<E> q2 = new LinkedList<E>();
	  
	  if (!q.isEmpty()){
		  
		   E obj = q.remove();
		   E first = obj;
		   q2.add(obj);
		   q.add(obj);
		   
		   while (q.peek() != first) {
			   obj = q.remove();
			   q.add(obj);
			   q2.add(obj);
		   }  
	  }
	  
	  return q2;
  }
  
  

  public static void main(String args[])
  {  // Adapt this as you see fit.
    String fileName;

    if (args.length >= 1)
    {
      fileName = args[0];
    }
    else
    {
      Scanner kboard = new Scanner(System.in);
      System.out.print("Enter the cookies file name: ");
      fileName = kboard.nextLine();
     }

    File file = new File(fileName);
    Scanner input = null;
    try
    {
      input = new Scanner(file);
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("Cannot open " + fileName);
      System.exit(1);
    }

    CookieMonsterStarter monster = new CookieMonsterStarter();
    monster.loadCookies(input);
    System.out.println("Optimal path has " +
                                  monster.optimalPath() + " cookies.\n");
  }
}