package Package;
import java.io.IOException;

public abstract class Filtru implements DetaliiExecutie {
	
	protected double timpExec;
	
	public abstract Photo aplica(Photo image) throws IOException; //functia de aplicare a unui filtru
	
	public double getTimpExec() //concretizarea functiei din interfata
	{
		return timpExec;
	}

}
