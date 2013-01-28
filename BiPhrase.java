import java.util.HashSet;


public class BiPhrase {
	
	String[] source;
	String[] cible;
	
	public BiPhrase(String[] src, String[] cible){
		this.source=src;
		this.cible=cible;
	}
	
	public String[] getSource(){
		return this.source;
	}
	
	public String[] getCible(){
		return this.cible;
	}
	
	public String toString(){
		String src="Source : ";
		String cbl="Cible : ";
		for (String mot : this.source){
			src+=mot+" ";
		}
		
		for (String mot : this.cible){
			cbl+=mot+" ";
		}
		String result=src+"\n"+cbl;
		System.out.println(result);
		return result;
	}
	
	private double calculMI(BiCorpus bc, Cooccurence co, String motSrc, String motCible){
		int n=bc.corpus.size();
		int n1A=bc.compteMotSrc.get(motSrc);
		int n1B=bc.compteMotCible.get(motCible);
		int n11=co.getCompte(motSrc, motCible);
		int[] nA={n-n1A,n1A};
		int[] nB={n-n1B,n1B};
		int[] nAB={n-n1A-n1B,n1A-n11,n1B-n11,n11};
		double sum=nAB[0]*Math.log(nAB[0]/(nA[0]*nB[0]))+
				nAB[1]*Math.log(nAB[1]/(nA[1]*nB[0]))+
				nAB[2]*Math.log(nAB[2]/(nA[0]*nB[1]))+
				nAB[3]*Math.log(nAB[3]/(nA[1]*nB[1]));
		return sum;
	}
	
	
	public Cooccurence getCoocurrence(Cooccurence c){
		Cooccurence result=new Cooccurence();
		HashSet<String> setSource=new HashSet<String>();
		HashSet<String> setCible=new HashSet<String>();
		for(String mot : this.source){
			setSource.add(mot);
		}
		for(String mot_cible : this.cible){
			setCible.add(mot_cible);
		}
		for(String mot : setSource){
			for(String mot_cible : setCible){
						
								if (c.cooc.containsKey(mot)){
									
									Compte cpte=c.cooc.get(mot);
									if (cpte.comptes.containsKey(mot_cible)){
										cpte.comptes.put(mot_cible, (cpte.comptes.get(mot_cible)+1));
									}
									else{
										cpte.comptes.put(mot_cible, 1);
									}
									
								}
								else{
									
									Compte cpt=new Compte();
									cpt.comptes.put(mot_cible, 1);
									c.cooc.put(mot, cpt);
								}
							}
					
			
		}
		
		for(String mot : setSource){
			for(String mot_cible : setCible){
						
								if (c.cooc.containsKey(mot)){
									
									Compte cpte=c.cooc.get(mot);
									if (cpte.comptes.containsKey(mot_cible)){
										cpte.comptes.put(mot_cible, (cpte.comptes.get(mot_cible)+1));
									}
									else{
										cpte.comptes.put(mot_cible, 1);
									}
									
								}
								else{
									
									Compte cpt=new Compte();
									cpt.comptes.put(mot_cible, 1);
									c.cooc.put(mot, cpt);
								}
							}
					
			
		}
		return c;
	}

}
