package p2.tempo;

import java.util.ArrayList;

public class Horario {
	private ArrayList<Periodo> periodos;
	
	public Horario() {
		periodos=new ArrayList<Periodo>();
	}
	
	//ver solucao
	public ArrayList<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(ArrayList<Periodo> periodos) {
		this.periodos = periodos;
	}
	
	public void addPeriodo(Periodo p) {
		for(int i=periodos.size()-1;i>=0;i--) {
			Periodo p2=periodos.get(i);
			if(p.interseta(p2)) {
				periodos.remove(i);
				p.junta(p2);
			}
		}
		periodos.add(p);
	}
	
	public void removePeriodo (int idx) {
		periodos.remove(idx);
	}
	
	public boolean estaDentro(Hora h) {
		//percorrer os periodos todos
		for(int i=0;i<periodos.size();i++) {
			// se estiver dentro de algum devolve true
			if(periodos.get(i).estaDentro(h))return true;
		}		
		return false;
	}

	public String toString() {
		String print=new String();
		for(int i=0;i<periodos.size();i++) {
			print=print+"\nPeriodo: "+(i+1)+periodos.get(i).toString();
		}
		return print;
	}
	
	
}
