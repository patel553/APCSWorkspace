/**
 * 
 */

/**
 * @author jack
 *
 */
public class Problem12 {

	/**
	 * @param args
	 */
	
	public void three(int[][] grid)
	{
		for(int i=0;i<grid.length;i++)
		{
			for(int j=0;j<grid[i].length;j++)
			{
				int place=grid[i][j];
				int rowCount=0;
				int colCount=0;
				for(int k=0;k<grid.length;k++)
				{
					if(grid[i][k]==place)
						rowCount++;
				}
				for(int k=0;k<grid[j].length;k++)
				{
					if(grid[k][j]==place)
						colCount++;
				}
				if(rowCount>2&&colCount>2)
				{
					System.out.println("Row: "+i);
					System.out.println("Col:"+j);
					System.out.println("Value: "+grid[i][j]);
				}
			}
		}
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid= new int[4][4];
		
		
		grid[0][0]=4;
		grid[0][1]=4;
		grid[0][2]=4;
		grid[0][3]=5;
		grid[1][0]=5;
		grid[1][1]=4;
		grid[1][2]=6;
		grid[1][3]=7;
		grid[2][0]=8;
		grid[2][1]=4;
		grid[2][2]=9;
		grid[2][3]=10;
		grid[3][0]=11;
		grid[3][1]=12;
		grid[3][2]=13;
		grid[3][3]=14;
		
		Problem12 cool=new Problem12();
		cool.three(grid);
	}

}
