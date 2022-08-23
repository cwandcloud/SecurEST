package securest.recurso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import p2.tempo.Hora;
import p2.tempo.Horario;

public class Instalacao {
	private int id;
	private String descricao;
	private int nAcesso;
	private Horario funcionamento;
	private ArrayList<Funcionario> autorizados;
	private ArrayList<Funcionario> presentes;
	
	//construtor
	public Instalacao(int id, String descricao, int nivel, Horario funcionamento) {
		this.id = id;			
		this.descricao = descricao;
		this.nAcesso = nivel;
		this.funcionamento = funcionamento;
		presentes=new ArrayList();
		autorizados=new ArrayList();
	}
	
	
	//getters e setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getnAcesso() {
		return nAcesso;
	}
	
	public void setnAcesso(int nAcesso) {
		this.nAcesso = nAcesso;
	}
	
	public Horario getFuncionamento() {
		return funcionamento;
	}
	
	public void setFuncionamento(Horario funcionamento) {
		this.funcionamento= funcionamento;
	}
	
	public List<Funcionario> getPresentes(){
		return Collections.unmodifiableList(presentes);
	}
	
	public List<Funcionario> getAutorizados(){
		return Collections.unmodifiableList(autorizados);
	}
	
	public void addAutorizado(Funcionario f) {
		autorizados.add(f);
	}
	
	public void removeAutorizado(Funcionario f) {
		autorizados.remove(f);
	}
	public boolean podeEntrar(Funcionario f, Hora h) {
		//se não está dentro do horario entao nao pode entrar
		if(!funcionamento.estaDentro(h))
			return false;
		
		//se o funcionario esta noutra instalacao entao nao pode entrar
		if(f.estaPresente())
			return false;
		
		//se esta na lista de autorizados entao pode
		if(autorizados.contains(f))
			return true;
		
		//se o nivel do funcionario é maior ou igual ao da instalacao entao pode entrar
		return (f.getnAcesso()>=nAcesso);
	}
	
	public boolean entrar(Funcionario f, Hora h){
		if(!podeEntrar(f,h)) {
			return false;
		}
		
		presentes.add(f);
		f.setInstalacao(this);
		
		return true;
		
	}
	
	public void sair(Funcionario f) {
		if(presentes.contains(f)) {
			presentes.remove(f);
		}
		
		f.sair();
	}
	
	public boolean equals(Instalacao i) {
		return this.id==i.id;
	}
	
	public void removeAutorizados(Funcionario f) {
		autorizados.remove(f);
	}

	
}

