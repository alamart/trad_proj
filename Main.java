
import java.io.IOException;
import java.util.Scanner;


public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*System.out.println("Nom fichier :");
		Scanner sc = new Scanner(System.in);*/
		//String fichier1="test.mstat";//sc.nextLine();
		//String[] fichiertab ={"/net/k10/u/enseignant/allauzen/cours/m1_ter/hansard/lc.full.hansard.5000.f"};
		//LireFichier.lire(fichier);
		//LireFichier.lire_scanner("test.f");
		//LireFichier.lire_scanner(fichiertab);
		//LireFichier.lire_mstat(fichier1);
		//System.out.println("Texte a ecrire :");
		//String texte=sc.nextLine();
		//EcrireFichier.ecrire(fichier, texte);
		
		BiCorpus en_fr=new BiCorpus("texte.e", "texte.f");
		BiCorpus fr_en=new BiCorpus("texte.f", "texte.e");
		
		String[] src={"salut","salut","le","monde"};
		String[] cible={"hello","world","world"};
		BiPhrase bp=new BiPhrase(src, cible);
		Cooccurence co=new Cooccurence();
		Cooccurence co2=new Cooccurence();
		//bp.getCoocurrence(co);
		en_fr.getCoocurrence(co);
		fr_en.getCoocurrence(co2);
		System.out.println(co.toString());
		
		Scanner sc=new Scanner(System.in);
		while(1==1){
			System.out.println("anglais -->francais : 1");
			System.out.println("français -->anglais : 2");
			
			int choix=Integer.parseInt(sc.nextLine());
			System.out.println("Votre mot?");
			String mot=sc.nextLine();
			switch (choix) {
			
			case 1:
				System.out.println((co.cooc.get(mot)).trier().toString());
				System.out.println(co.resultMI(en_fr, mot).toString());
				break;
				
			case 2:
				System.out.println((co2.cooc.get(mot)).trier().toString());
				break;

			default:
				System.out.println("Faites votre choix");
				break;
			}
		
		}
		
	}

}