import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class EcrireFichier {

	public static void ecrire(String fichier, String texte) throws IOException{
		String adressedufichier = System.getProperty("user.dir") + "/"+ fichier;
		FileWriter fw = new FileWriter(adressedufichier, false);
		BufferedWriter output = new BufferedWriter(fw);
		output.write(texte);
		output.flush();
		
		output.close();
	}
	
	
}