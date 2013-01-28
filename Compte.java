
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;


public class Compte {

	HashMap<String, Integer> comptes;
	
	public Compte(){
		this.comptes=new HashMap<String, Integer>();
	}
	
	public void ajout (String src, int cpt){
		this.comptes.put(src, cpt);
		
	}
	
	public void supprimer (String src){
		this.comptes.remove(src);
	}
	
	public boolean contain(String src){
		return this.comptes.containsKey(src);
	}
	
	public String toString(){
		String result="";
		for(String key : this.comptes.keySet()){
			result+="Mot \""+key+"\" ---> "+this.comptes.get(key)+"\n";
		}
		return result;
	}
	
	public List trier(){
		/*
		 * Trier la Map par valeurs
		 */

		return MapUtil.sortByValue(this.comptes);
		
	}
	
	
}
