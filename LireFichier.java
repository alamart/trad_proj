import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;


public class LireFichier {

	public static void lire (String fichier) throws IOException{
		InputStream ips=new FileInputStream(fichier); 
        InputStreamReader ipsr=new InputStreamReader(ips);
        BufferedReader br=new BufferedReader(ipsr);
        String ligne;
        int cpt = 0;
        while ((ligne=br.readLine())!=null && cpt<5){
        	cpt++;
        	System.out.println("Ligne n "+cpt+" : "+ligne);
        	
        }
	}
	
	public static void lire_scanner(String fichier) throws IOException{
		int cpt=0;
		Scanner scan=new Scanner(new File(fichier));
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		while(scan.hasNextLine() && cpt<5){
			cpt++;
			String line=scan.nextLine();
			String[] phrases=line.split(" . ");
			for (String ph : phrases){
				
			
			String[] mots=ph.split(" ");
			for(String mot:mots){
				String m=mot.toLowerCase();
				if (map.containsKey(m)) {
					map.put(m, map.get(m)+1);
				}
				else {
					map.put(m, 1);
				}
			}
			}
			System.out.println("Ligne n "+cpt+" : "+line);
			
		}
		//System.out.println(map.toString());
		EcrireFichier.ecrire("test.mstat", map.toString());
		scan.close();
	}
	
	
	public static void lire_scanner(String[] fichiers) throws IOException{
		
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		
		for (String fichier:fichiers){
			Scanner scan=new Scanner(new File(fichier));
			int cpt=0;
			while(scan.hasNextLine() ){
				cpt++;
				String line=scan.nextLine();
				String[] phrases=line.split(" . ");
				
				for (String ph : phrases){
										
					String[] mots=ph.split(" ");
					for(String mot:mots){
						String m=mot.toLowerCase();
						if (map.containsKey(m)) {
							map.put(m, map.get(m)+1);
						}
						else {
							map.put(m, 1);
						}
					}
				}
				System.out.println("Ligne n "+cpt+" : "+line);
					
			}
			//System.out.println(map.toString());
			EcrireFichier.ecrire("test.mstat", map.toString());
			System.out.println("Nombre de lignes : "+cpt);
			System.out.println("Taille de la Map : "+map.size());
			
			scan.close();
		}
		
	}
	
	public static void lire_mstat(String fichier)throws IOException{
		Scanner scan=new Scanner(new File(fichier));
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		while (scan.hasNextLine()) {
			String line=scan.nextLine();
			String[] mots=line.split(", ");
			for(String mot : mots){
				//System.out.println(mot);
				String[] duo=mot.split("=");
				String s0=duo[0];
				String s1=duo[1];
				if (s0.startsWith("{")){
					map.put(s0.substring(1),Integer.parseInt(s1));
				}
				else if (s1.endsWith("}")){
					map.put(s0, Integer.parseInt(s1.substring(0, s1.length()-1)));
				}
					
				
				else {
				//System.out.println(Integer.parseInt(s1));
				map.put(duo[0], Integer.parseInt(s1));
				}
			}
			//System.out.println(line);
		}
		
		scan.close();
		
		System.out.println(map.toString());
		System.out.println("Taille de la Map : "+map.size());
	}
	
	
}