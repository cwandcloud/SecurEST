package securest.app;


import consola.SConsola;
import p2.tempo.Hora;
import securest.recurso.CentralControlo;
import securest.recurso.Funcionario;
import securest.recurso.Instalacao;

/**
 * Classe que trata dos menus da central de comandos
 */
public class MenuCentral {
	
	private SConsola consola = new SConsola();
	private CentralControlo central;
	
	public MenuCentral( CentralControlo central ){
		this.central = central;
	}

	/**
	 * menu principal da aplicacao
	 */
	public void menuPrincipal(){
		String menu = "Central de controlo - controlo de acessos\n" + 
				"1- Info sobre instalacao\n"+
				"2- Info sobre funcionario\n" + 
				"3- Entrada de funcionario\n"+
				"4- Saida de funcionario\n"+
				"5- Listar presencas em todas as instalacoes\n"+
				"\n0- Sair";

		char opcao = 0;
		do {
			consola.clear();
			consola.println( menu );
			opcao = consola.readChar();
			switch( opcao ){
			case '1':
				printInstalacao();
				break;
			case '2':
				printFuncionario();
				break;
			case '3':
				entradaFuncionario();
				break;
			case '4':
				saidaFuncionario();
				break;
			case '5':
				listarPresencas();
				consola.readLine();
				break;
			case '0': break;
			default:
				consola.println( "opcao invalida");
				consola.readLine();
			}
		} while( opcao != '0' );

		consola.close();// desligar o menu da central		
	}

	/**
	 * imprime a informacao de uma instalacao 
	 */
	private void printInstalacao( ) {
		Instalacao inst = (Instalacao)getInstalacao( "Codigo da instalacao? ");
		consola.clear();
		consola.println( inst.getDescricao() );		
		consola.println("id: "+inst.getId() );
		consola.println("nivel acesso: "+inst.getnAcesso() );
		consola.print("Autorizados: " );
		
		// para todos os autorizados apresentar
		for(Funcionario f : inst.getAutorizados()) {
			consola.print( f.getNome() );
		}
			
		consola.println();
		consola.print("Presentes: " );
		// para todos os presentes apresentar
		for(Funcionario f : inst.getPresentes())
			consola.print( f.getNome() );
		consola.println();
		consola.println("Horario funcionamento");
		// para todos os periodos do horario apresenta-los
		consola.println(inst.getFuncionamento().toString());
		consola.readLine();
	}

	/**
	 * imprime a informacao de um funcionario 
	 */
	private void printFuncionario( ) {
		Funcionario f = (Funcionario)getFuncionario( "Codigo do funcionario? ");
		consola.clear();
		consola.println( f.getNome() );		
		consola.println("id: "+f.getCod() );
		consola.println("nivel acesso: "+f.getnAcesso() );
		if( f.estaPresente())
			consola.print("Presente em "+f.getInstalacao().getDescricao() );
		else
			consola.print("Nao esta presente em nenhuma instalacao" );
		consola.readLine();
	}
	
	/**
	 * processa a entrada de um funcionario
	 */
	private void entradaFuncionario() {
		Funcionario f = (Funcionario)getFuncionario( "Codigo do funcionario a entrar? ") ;
		Instalacao i = (Instalacao)getInstalacao( "Codigo da instalacao onde vai entrar? ");
		
		consola.println( i.entrar(f, new Hora())? "Pode entrar" : "Impedido de entrar!");
		consola.readLine();
	}

	/**
	 * processa a saida de um funcionario
	 */
	private void saidaFuncionario() {
		Funcionario f = (Funcionario)getFuncionario( "Codigo do funcionario a sair? ") ;
		if( f.estaPresente() /* esta presente numa instalacao */ ){
			// processar saida da instalacao onde esta
			Instalacao inst=f.getInstalacao();
			inst.sair(f);
			consola.println("O funcionario "+f.getNome()+" saiu de "+inst.getDescricao() );
		}
		else {
			consola.println("O funcionario "+f.getNome()+" nao esta em nenhuma instalacao!");
		}
		consola.readLine();
	}


	/**
	 * pergunta ao utilizador qual o id do funcionario que vai ser processado
	 * e retorna o funcionario correspondente
	 * @param msg a mensagem a pedir o funcionario
	 * @return o funcionario pedido
	 */
	private Funcionario getFuncionario( String msg ){
		Funcionario f = null;
		do {
			consola.print( msg );
			int id = consola.readInt();
			f = central.getFuncionario( id );
			if( f == null ){
				consola.println("Esse funcionario nao existe!");
				consola.readLine();
			}
		}while( f == null );
		return f;
	}

	/**
	 * pergunta ao utilizador qual o id da instalacao que vai ser processada
	 * e retorna a instalacao correspondente
	 * @param msg a mensagem a pedir a instalacao
	 * @return a instalacao pedida
	 */
	private Instalacao getInstalacao( String msg ){
		Instalacao i = null;
		do {
			consola.print( msg );
			int id = consola.readInt();
			i = central.getInstalacao( id );
			if( i == null ){
				consola.println("Essa instalacao nao existe!");
				consola.readLine();
			}
		}while( i == null );
		return i;
	}
	
	/**
	 * lista todas as presencas em todas as instalacoes 
	 */
	private void listarPresencas() {
		consola.clear();
		
		/* para todas as instalacoes */ 
		for(Object inst : central.getInstalacoes()){
			consola.println( "presentes em "+((Instalacao)inst).getDescricao()+", nivel de acesso: "+((Instalacao)inst).getnAcesso() );
			/* para todos os funcionarios */
			for(Funcionario f : ((Instalacao)inst).getPresentes())
				consola.println( f.getNome()+" "+f.getCod() );
			consola.println( "-----------------" );
		}		
	}
	
}
