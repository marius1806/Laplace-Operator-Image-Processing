package Package;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Photo extends TablouDePixeli {

	private BufferedImage image; //handler imagine
	private String source; 
	private double timpIncarcare;
	private double timpExtragerePixeli;
	
	Photo()
	{
		super(); //apelare constructor baza
		image = null;
	}
	
	public void readFrom(String inSource) throws IOException
	{
		source = inSource;
		
		double pornireIncarcare = System.nanoTime();
		try 
		{
			image = ImageIO.read(new File(inSource));
		}
		catch(IOException ex)
		{
			System.out.println("Exceptie:" + ex.getMessage());
		}
		
		double oprireIncarcare = System.nanoTime();
		timpIncarcare=(oprireIncarcare - pornireIncarcare)/Math.pow(10,9);
		
		width = image.getWidth();
		height = image.getHeight();
		matrix = new Pixel[width][height];
		
		double pornireExtragere = System.nanoTime();
		
		//extragere pixeli catre matricea de pixeli
		try {
			
			for(int idx = 0; idx <= width - 1; idx++)
				for(int jdx = 0; jdx <= height - 1; jdx++)
				{
					matrix[idx][jdx] = new Pixel();
					int clr = image.getRGB(idx,jdx);
					
					//extragere R, G, B prin mascarea pozitionala a bitilor in mod corespunzator
					matrix[idx][jdx].setR((clr & 0x00ff0000) >> 16); //extragere R
					matrix[idx][jdx].setG((clr & 0x0000ff00) >> 8); //extragere G
					matrix[idx][jdx].setB(clr & 0x000000ff); //extragere B
				}
		}
		catch(Exception ex)
		{
			System.out.println("Exceptie:" + ex.getMessage());
		}
		
		double oprireExtragere = System.nanoTime();
		timpExtragerePixeli = (oprireExtragere - pornireExtragere) / Math.pow(10,9);
	}
	
	private BufferedImage duplicate(BufferedImage image) { //copiere profunda intre handleri
		if (image == null)
		throw new NullPointerException();

		BufferedImage j = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		j.setData(image.getData());
		return j;
		}
	
	public void resize(int width,int height) //redimensionare cu completare
	{
		int oldWidth = this.width;
		int oldHeight = this.height;
		this.width = width;
		this.height = height;
		
		BufferedImage aux = new BufferedImage(width, height,
		        BufferedImage.TYPE_INT_RGB); //handler pentru imagini goale 
		
		try
		{
			for(int idx = 0; idx <= width - 1; idx++)
				for(int jdx = 0; jdx <= height - 1; jdx++)
				{
					if(idx <= oldWidth - 1 && jdx <= oldHeight - 1)
					{
						int clr = image.getRGB(idx, jdx);
						aux.setRGB(idx, jdx, clr);//copiere cu fiecare pixel
					}
					else
						aux.setRGB(idx, jdx, 0xffffffff);
				}
		}
		catch(Exception ex)
		{
			System.out.println("Exceptie:" + ex.getMessage());
		}
		
		image = null;
		image = duplicate(aux);

		Pixel[][] auxMatrix = new Pixel[width][height];
		for(int idx = 0; idx < auxMatrix.length; idx++)
			for(int jdx = 0; jdx < auxMatrix[idx].length; jdx++)
			{
				auxMatrix[idx][jdx] = new Pixel();
				if(idx <= oldWidth - 1 && jdx <= oldHeight - 1)
					auxMatrix[idx][jdx] = matrix[idx][jdx];
				else
					auxMatrix[idx][jdx] = new Pixel(255, 255, 255);	
			}
		matrix = null;
		matrix = auxMatrix.clone();
	}
	
	public Photo(String inSource) throws IOException { //constructor avand ca parametru path-ul imaginii
		
		source = inSource;
		
		double pornireIncarcare=System.nanoTime();
		image = ImageIO.read(new File(inSource));
		double oprireIncarcare=System.nanoTime();
		timpIncarcare = (oprireIncarcare-pornireIncarcare)/Math.pow(10,9);
		
		width = image.getWidth();
		height = image.getHeight();
		matrix = new Pixel[width][height];
		
		double pornireExtragere=System.nanoTime();
		
		try {
			for(int idx = 0; idx <= width - 1; idx++)
				for(int jdx = 0; jdx <= height - 1; jdx++)
				{
					matrix[idx][jdx]=new Pixel();
					int clr = image.getRGB(idx, jdx);
					
					//mascarea pozitionala a bitilor pentru extragerea RGB
					matrix[idx][jdx].setR((clr & 0x00ff0000) >> 16);
					matrix[idx][jdx].setG((clr & 0x0000ff00) >> 8);
					matrix[idx][jdx].setB(clr & 0x000000ff);
				}
		}
		catch(Exception e)
		{
			System.out.println("Exceptie:" + e.getMessage());
		}
		
		
		double oprireExtragere = System.nanoTime();
		timpExtragerePixeli = (oprireExtragere-pornireExtragere)/Math.pow(10,9);
	}
	
	public void writeImageTo(String outSource) //scrie noua imagine in fisierul cu path-ul de output
	{
		try
		{
			ImageIO.write(image, "bmp", new File(outSource));
		}
		catch(IOException e)
		{
			System.out.println("O exceptie a avut loc:" + e.getMessage());
		}
		System.out.println("Transformarea a avut succes!");
	}
	
	private int RGBtoHex(int r,int g, int b) //obtine valoarea hexazecimala din canale
	{
		int clrR = 0;
        int clrG = 0;
        int clrB = 0;
        
        clrR = (clrR | r)<<16;
        clrG = (clrG | g)<<8;
        clrB = (clrB | b);
        
        int clr=clrR | clrG | clrB;
        
        return clr;
	}
	
	
	public String getPath()//path-ul actual al imaginii
	{
		return source;
	}

	
	public void setPixel(int idx, int jdx, Pixel p) //seteaza pixelul idx, jdx
	{
		super.setPixel(idx, jdx, p);
		image.setRGB(idx, jdx, RGBtoHex(p.getR(),p.getG(),p.getB()));
	}
	
	public double getTimpIncarcare()
	{
		return timpIncarcare;
	}
	
	public double getTimpExtragere()
	{
		return timpExtragerePixeli;
	}
	
	public TablouDePixeli[] getPereche(String source2)
	{
		TablouDePixeli[] arr = new TablouDePixeli[2];
		arr[0] = this;
		
		try {
		arr[1] = new Photo(source2);
		}
		catch(Exception e)
		{
			System.out.println("Exceptie:" + e.getMessage());
		}
		
		return arr;
	}
	
}
