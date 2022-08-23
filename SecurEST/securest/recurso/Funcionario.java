package securest.recurso;

public class Funcionario {
	private int cod;
	private String nome;
	private int nAcesso;
	private Instalacao instalacao;
	
	//construtor
	public Funcionario(int ide,String nom,int niv) {
		setnAcesso(niv);
		setNome(nom);
		setCod(ide);
		instalacao=null;
	}
	
	public int getCod() {
		return cod;
	}
	
	public void setCod(int cod) {
		this.cod = cod;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getnAcesso() {
		return nAcesso;
	}
	
	public void setnAcesso(int nAcesso) {
		this.nAcesso = nAcesso;
	}
	
	public Instalacao getInstalacao() {
		return instalacao;
	}

	public void setInstalacao(Instalacao instalacao) {
		this.instalacao = instalacao;
	}
	
	public String toString() {
		return "ID Funcionario: "+cod+" Nome: "+nome+" Nivel: "+nAcesso+" Instalação: "+instalacao;
	}
	
	public boolean equals(Funcionario f) {
		return this.cod==f.cod;
	}
	
	public boolean estaPresente() {
		if(instalacao!=null) {
			return true;
		}
		return false;
	}
	
	public void sair() {
		instalacao=null;
	}
	
	
	
	
}
