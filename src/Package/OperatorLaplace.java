package Package;
import java.io.IOException;

public class OperatorLaplace extends Filtru {

	@Override
	public Photo aplica(Photo image) throws IOException
	{
		Photo out = new Photo(image.getPath());
		
		double starttime = System.nanoTime();
		
		try
		{
			
		for(int idx = 1; idx < image.getDimensions()[0] - 1; idx++)
			for(int jdx = 1; jdx < image.getDimensions()[1] - 1; jdx++)//nu luam in considerare colturile
			{
				//pixeli din imprejur
				Pixel pix00 = image.getPixel(idx - 1, jdx - 1);
				Pixel pix01 = image.getPixel(idx - 1, jdx);
				Pixel pix02 = image.getPixel(idx - 1, jdx + 1);
				
				Pixel pix10 = image.getPixel(idx, jdx - 1);
				Pixel pix11 = image.getPixel(idx, jdx);
				Pixel pix12 = image.getPixel(idx, jdx + 1);
				
				Pixel pix20 = image.getPixel(idx + 1, jdx - 1);
				Pixel pix21 = image.getPixel(idx + 1, jdx);
				Pixel pix22 = image.getPixel(idx + 1, jdx + 1);
				
				//valorile noului pixel
				
				int r = -pix00.getR() -   pix01.getR() - pix02.getR() +
                        -pix10.getR() + 8 * pix11.getR() - pix12.getR() +
                        -pix20.getR() -   pix21.getR() - pix22.getR();
				
				int g = -pix00.getG() -   pix01.getG() - pix02.getG() +
                        -pix10.getG() + 8 * pix11.getG() - pix12.getG() +
                        -pix20.getG() -   pix21.getG() - pix22.getG();
				
				int b = -pix00.getB() -   pix01.getB() - pix02.getB() +
                        -pix10.getB() + 8 * pix11.getB() - pix12.getB() +
                        -pix20.getB() -   pix21.getB() - pix22.getB();
				
				r = Math.min(255, Math.max(0, r));
                g = Math.min(255, Math.max(0, g));
                b = Math.min(255, Math.max(0, b));
                
                out.setPixel(idx, jdx, new Pixel(r, g, b)); //pixelul nou
			}
		}
		catch(Exception ex)
		{
			System.out.println("Exceptie:" + ex.getMessage());
		}
		
		double stoptime=System.nanoTime();
		timpExec=(stoptime - starttime)/Math.pow(10,9);
		
		return out;
	}

}
