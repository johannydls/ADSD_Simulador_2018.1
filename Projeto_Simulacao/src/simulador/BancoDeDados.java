package simulador;

import java.util.ArrayList;

public class BancoDeDados {
	
	private ArrayList<String> bd;
	
	BancoDeDados() {
		bd = new ArrayList<>();
	}
	
	public ArrayList<String> leitura() {
		return bd;
	}
	
	public void escrita(String dado) {
		bd.add(dado);
	}
	
	public String leitura(String query) {
		
		for (String dado : bd) {
			if (query.equals(dado)) return dado;
		}
		
		return null;
	}

}
