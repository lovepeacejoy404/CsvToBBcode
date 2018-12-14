import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GestoreCsv {
	
	int numColonne;
	/* Indicare il numero di riga della matrice in base a cui fare il raggruppamento
	 * dei dati...ad esempio i dati si possono raggruppare per regione...*/
	private final int rigaRaggruppamento=6;
	
	public GestoreCsv() {
		
	}

	
	public String [][] getMatriceArray (String csv) {
		String [] righe = csv.split("\n");
		numColonne = righe[0].split("\",\"").length;
		String [][] matrice = new String [numColonne][righe.length];
		for (int i=0;i<righe.length;i++) {
			for (int j=0;j<numColonne;j++) {
				String [] riga = righe[i].split("\",\"");
				matrice[j][i]=riga[j];
				
			}
		}
		return matrice;
	}
	
	public String getMatrice (String [][] matrice) {
		StringBuilder sb = new StringBuilder();
		for (int j=0;j<numColonne;j++)  {
			for  (int i=0;i<matrice[0].length;i++) {
				sb.append(matrice[j][i].replace('"', ' ').trim()+"\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	
	private String getBBCode (String [][] matrice) {
		StringBuilder sb = new StringBuilder();
		String text;
		sb.append("[table TEXT1]\n");
		for (int j=0;j<numColonne;j++) {
			sb.append("\t[tr TEXT1]\n");
			for  (int i=0;i<matrice[0].length;i++) {
				text= matrice[j][i].replace('"', ' ').trim();
				if (i==0) {
					if (text.equals("Timestamp")) text="Data questionario: ";
					else if (text.equals("Username")) text="Email: ";
					sb.append("\t\t[th 1]"+text+"[/th]\n");
				} else {
					sb.append("\t\t[td 1]"+text+"[/td]\n");
				}
					
			}
			sb.append("\t[/tr]\n");
		}
		sb.append("[/table]\n");
		return sb.toString();
	}
	
	
	public String getBBCodePerRegioni(String [][] matrice) {
		HashMap<String,List<Integer>> map = new HashMap<String,List<Integer>>();
		String campo;
		for (int j=1;j<matrice[0].length;j++) {
			campo =matrice[rigaRaggruppamento][j].toUpperCase();
			List<Integer> list = map.get(campo);
			if (list==null) {
				list = new ArrayList<Integer>();
			} 
			list.add(j);
			map.put(campo, list);
			
		}
			
		Iterator<String> i1 = map.keySet().iterator();
		StringBuilder sb = new StringBuilder();
		while (i1.hasNext()) {
			campo = i1.next();
			List<Integer> temp = map.get(campo);
			String [][] array = new String [matrice.length][temp.size()+1];
			copyColonna (matrice,array,0,0);
			Iterator<Integer> i2 = temp.iterator();
			int j=1;
			while (i2.hasNext()) {
				Integer col = i2.next();
				copyColonna (matrice,array,col,j++);
			}
			
			sb.append(campo + "\n\n"+ getBBCode(array)+ "\n\n");
		}
		return sb.toString();
	}
	
	
	private void copyColonna (String [][] matrice,String [][] array, int colMatrice, int colArray) {
		for (int row =0; row<matrice.length;row++) {
			array[row][colArray]= matrice[row][colMatrice];
		}
	}
	
}
