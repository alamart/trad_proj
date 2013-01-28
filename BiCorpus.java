import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class BiCorpus {

	//Stocke le corpus parallele
	ArrayList<BiPhrase> corpus;
	int index;
	HashMap<String, Integer> compteMotSrc;
	HashMap<String, Integer> compteMotCible;
	//autres attributs comme  les noms de fichier
	
	public BiCorpus(String cheminSrc, String cheminCible) throws FileNotFoundException{
		
		corpus=new ArrayList<BiPhrase>();
		compteMotCible=new HashMap<String, Integer>();
		compteMotSrc=new HashMap<String, Integer>();
		
		Scanner scan1=new Scanner(new File(cheminSrc));
		Scanner scan2=new Scanner(new File(cheminCible));
		
		String[] phr1;
		String[] phr2;
		int cpt=0;
			
		while(scan1.hasNextLine() && scan2.hasNextLine() ){
			
			String line1=scan1.nextLine();
			String line2=scan2.nextLine();
			phr1=line1.split(" ");
			phr2=line2.split(" ");
			BiPhrase bp=new BiPhrase(phr1, phr2);
			corpus.add(bp);
			cpt++;
			HashSet<String> setSource=new HashSet<String>();
			HashSet<String> setCible=new HashSet<String>();
			for(String mot : phr1){
				setSource.add(mot);
			}
			for(String mot_cible : phr2){
				setCible.add(mot_cible);
			}
			for(String mot : setSource){
				if (compteMotSrc.containsKey(mot)){
					compteMotSrc.put(mot, (compteMotSrc.get(mot)+1));
				}
				else{
					compteMotSrc.put(mot, 1);
				}
			}
			for(String mot : setCible){
				if (compteMotCible.containsKey(mot)){
					compteMotCible.put(mot, (compteMotCible.get(mot)+1));
				}
				else{
					compteMotCible.put(mot, 1);
				}
			}
			System.out.println(cpt);
			bp.toString();
			
		}
		
		if (scan1.hasNextLine()!=scan2.hasNextLine()){
			System.out.println("Textes de taille différente : "+cpt+" lignes pour le plus petit texte.");
		}
		scan1.close();
		scan2.close();
	}
	
	public BiPhrase getNextBiphrase(){
		BiPhrase bp=this.corpus.get(index);
		this.index++;
		return bp;
		
	}
	
	public boolean hasNextBiPhrase(){
		
		return (this.index++ < this.corpus.size());
	}
	
	public void rembobiner(){
		this.index=0;
	}
	
	public Cooccurence getCoocurrence(Cooccurence c){
		for(BiPhrase bp:this.corpus){
			bp.getCoocurrence(c);
		}
		return c;
	}
	
	
	/*
	 * Dans un main
	 * 
	 * bicorpus.rembobiner();
	 * while bicorpus.hasNextBiphrase(){
	 * Biphrase bp= bicorpus.getNextBiphrase();
	 * 
	 */
	
	
	
	
	
}
