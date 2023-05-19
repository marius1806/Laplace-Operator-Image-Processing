package Package;

public abstract class TablouDePixeli implements Cadru {
	
	protected int width;
	protected int height;
	protected Pixel[][] matrix;
	
	TablouDePixeli()
	{
		width=0;
		height=0;
		matrix=null;
	}
	
	TablouDePixeli(int width,int height)
	{
		this.width = width;
		this.height = height;
		matrix = new Pixel[width][height];//alocarea tabloului de pixeli
		
		try
		{
			
		for(int idx = 0; idx < width; idx++)
			for(int jdx = 0; jdx < height; jdx++)
				matrix[idx][jdx] = new Pixel();//alocarea unui pixel unul cate unul
		}
		catch(Exception e)
		{
			System.out.println("Exceptie:" + e.getMessage());
		}
	}
	
	public void printMat()//afisare pe grupuri de pixeli
	{
		for(int idx = 0; idx < width; idx++)
		{
			System.out.println();
			
			try {
				
			for(int jdx = 0; jdx < height; jdx++)
			{
				System.out.print( "("+ matrix[idx][jdx].getR() + "," + matrix[idx][jdx].getG() + "," + matrix[idx][jdx].getB() + ") " );
			}
			
			}
			catch(Exception e)
			{
				System.out.println("Exceptie:" + e.getMessage());
			}
		}
	}
	
	public Pixel[][] getMat() {//handler-ul matricei
		return matrix;
	}
	
	public void setPixel(int idx, int jdx, Pixel p)//seteaza pixelul idx, jdx
	{
		matrix[idx][jdx] = p;
	}
	
	public Pixel getPixel(int idx, int jdx)//preia pixelul idx, jdx
	{
		return matrix[idx][jdx];
	}

	public abstract void resize(int width, int height);

	public int[] getDimensions()//preia dimensiunile
	{
		int[] arr=new int[2];
		arr[0] = width;
		arr[1] = height;
		
		return arr;
	}
}
