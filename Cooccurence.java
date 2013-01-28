import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class Cooccurence {
	
	HashMap<String, Compte> cooc;
	
	public Cooccurence(){
		this.cooc=new HashMap<String, Compte>();
	}
	
	public void ajout(String cible){
		this.cooc.put(cible, new Compte());
	}
	
	public void ajout(String cible, Compte compte){
		this.cooc.put(cible, compte);
	}
	
	public int getCompte(String src, String cible){
		Compte src_map=this.cooc.get(src);
		return src_map.comptes.get(cible);
		
	}
	
	public boolean hasCompte(String src, String cible){
		Compte src_map=this.cooc.get(src);
		return src_map.comptes.containsKey(cible);
	}
	
	private double calculMI(BiCorpus bc, String motSrc, String motCible){
		int n=bc.corpus.size();
		int n1A=bc.compteMotSrc.get(motSrc);
		int n1B=bc.compteMotCible.get(motCible);
		int n11=this.getCompte(motSrc, motCible);
		double[] nA={n-n1A,n1A};
		double[] nB={n-n1B,n1B};
		double[] nAB={n-n1A-n1B,n1A-n11,n1B-n11,n11};
		double sum=nAB[0]*Math.log(nAB[0]/(nA[0]*nB[0]))+
				nAB[1]*Math.log(nAB[1]/(nA[1]*nB[0]))+
				nAB[2]*Math.log(nAB[2]/(nA[0]*nB[1]))+
				nAB[3]*Math.log(nAB[3]/(nA[1]*nB[1]));
		System.out.println(sum);
		return sum;
	}
	
	public List<Entry<String, Double>> resultMI(BiCorpus bc, String motSrc){
		HashMap<String, Double> result=new HashMap<String, Double>();
		for (String mot :this.cooc.get(motSrc).comptes.keySet()){
			result.put(mot, calculMI(bc, motSrc, mot));
			int n=bc.corpus.size();
			int n1A=bc.compteMotSrc.get(motSrc);
			int n1B=bc.compteMotCible.get(mot);
			int n11=this.getCompte(motSrc, mot);
			System.out.println(n);
			System.out.println(n1A);
			System.out.println(n1B);
			System.out.println(n11);
		}
		return MapUtil.sortByValue(result);
	}
	
	
	public String toString(){
		String result="";
		for (String mot : this.cooc.keySet()){
			result+="Pour le mot \""+mot+"\" :\n"+this.cooc.get(mot).toString()+"\n\n";
		}
		return result;
	}
		
	
		
}
