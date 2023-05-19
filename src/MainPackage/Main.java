package MainPackage;
import java.io.IOException;
import Package.Photo;
import Package.OperatorLaplace;
public class Main {
	//bloc de initializare
	static {
		System.out.println("Operator Laplacian (Positive/Negative)");
	}
	//metoda avand varargs la parametru
	public static void titlu (int... a) throws IOException {
		if (a[0] == 0)
			System.out.println("Detalii:");
	}
	
	public static void main(String[] args) throws IOException {
		
		try {
			int [] a = {0};
			titlu(a);
			double startCitireLocatii = System.nanoTime();
			String inSource = args[0];
			String outSource = args[1];
			double stopCitireLocatii = System.nanoTime();
		
			System.out.println("Timpul de citire al path-urilor de input si output:" 
					+ (stopCitireLocatii - startCitireLocatii) / Math.pow(10,9));// trecere in secunde
		
			Photo imagine = new Photo(inSource);
		
			System.out.println("Timpul de incarcare a sursei:" + imagine.getTimpIncarcare());
			System.out.println("Timpul de extragere al pixelilor:" + imagine.getTimpExtragere()); 		
		
			OperatorLaplace fl = new OperatorLaplace();
			Photo out = fl.aplica(imagine);//aplic filtrul
		
			System.out.println("Timpul de executie al algoritmului:" + fl.getTimpExec());
		
			double startScriere = System.nanoTime();
			out.writeImageTo(outSource);//afisare imagine
			double stopScriere = System.nanoTime();
		
			System.out.println("Timpul necesar scrierii imaginii prelucrate:" 
					+ (stopScriere-startScriere) / Math.pow(10,9));
		}
		catch(Exception ex)
		{
			System.out.println("Surse de citire/scriere lipsa!");
		}
	}

}
