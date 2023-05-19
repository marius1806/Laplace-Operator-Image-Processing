package Package;
import java.awt.Color;

public class Pixel {
	
	private int red, green, blue;
	
	Pixel()
	{
		red = -1;
		green = -1;
		blue = -1;
	}
	Pixel(int r, int g, int b)
	{
		this.red = r;
		this.green = g;
		this.blue = b;
	}

	public int getR() {
		return red;
	}
	public void setR(int r) {
		this.red = r;
	}
	public int getG() {
		return green;
	}
	public void setG(int g) {
		this.green = g;
	}
	public int getB() {
		return blue;
	}
	public void setB(int b) {
		this.blue = b;
	}
	
	public void setRGB(int r, int g, int b)
	{
		this.red = r;
		this.green = g;
		this.blue = b;
	}
	
	public int[] getRGB()
	{
		int[] rgb = new int[3];
		rgb[0] = red;
		rgb[1] = green;
		rgb[2] = blue;
		
		return rgb;
	}
	
	public Color getColor()
	{
		return new Color(red, green, blue);
 	}
}


