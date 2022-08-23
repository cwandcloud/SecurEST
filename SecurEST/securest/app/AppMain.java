package securest.app;

import p2.tempo.Hora;
import p2.tempo.Horario;
import p2.tempo.Periodo;
import securest.recurso.CentralControlo;
import securest.recurso.Funcionario;
import securest.recurso.Instalacao;

public class AppMain {

	/**
	 * desencadear a aplicacao
	 */
	public static void main( String []args ){
		
		// criar a central de controlo
		CentralControlo central = new CentralControlo( );
		
		/*
		 * Criar aqui as instalacoes e funcionarios indicados no enunciado 
		 */
		
		central.addFuncionario(new Funcionario(1,"Asdrubal",1));
		central.addFuncionario(new Funcionario(2,"Josefina",2));
		central.addFuncionario(new Funcionario(3,"Ambrosio",3));
		central.addFuncionario(new Funcionario(4,"Albertina",4));
		central.addFuncionario(new Funcionario(5,"Ze Bigboss",5));
		central.addFuncionario(new Funcionario(6,"Josefa",3));
		
		Horario horarioSala1=new Horario();
		horarioSala1.addPeriodo(new Periodo(new Hora(8,0,0),new Hora(20,0,0)));		
		Instalacao sala1=new Instalacao(1,"Sala 1",1,horarioSala1);
		central.addInstalacao(sala1);
		
		Horario horarioSala2=new Horario();
		horarioSala2.addPeriodo(new Periodo(new Hora(8,0,0),new Hora(12,0,0)));	
		horarioSala2.addPeriodo(new Periodo(new Hora(14,0,0),new Hora(20,0,0)));
		Instalacao sala2=new Instalacao(2,"Sala 2",2,horarioSala2);
		central.addInstalacao(sala2);
		
		Horario horarioSala3=new Horario();
		horarioSala3.addPeriodo(new Periodo(new Hora(10,0,0),new Hora(14,0,0)));	
		horarioSala3.addPeriodo(new Periodo(new Hora(16,0,0),new Hora(19,0,0)));
		Instalacao sala3=new Instalacao(3,"Sala 3",3,horarioSala3);
		sala3.addAutorizado((Funcionario)central.getFuncionario(1));		
		central.addInstalacao(sala3);
		
		Horario horarioLaboratorioSecreto=new Horario();
		horarioLaboratorioSecreto.addPeriodo(new Periodo(new Hora(0,0,0),new Hora(7,30,0)));	
		horarioLaboratorioSecreto.addPeriodo(new Periodo(new Hora(21,0,0),new Hora(23,59,0)));
		Instalacao laboratorioSecreto=new Instalacao(4,"Laboratorio Secreto",4,horarioLaboratorioSecreto);
		laboratorioSecreto.addAutorizado((Funcionario)central.getFuncionario(2));	
		laboratorioSecreto.addAutorizado((Funcionario)central.getFuncionario(3));
		central.addInstalacao(laboratorioSecreto);
		
		Horario horarioJacuzzi=new Horario();
		horarioJacuzzi.addPeriodo(new Periodo(new Hora(0,0,0),new Hora(23,59,0)));	
		Instalacao jacuzzi=new Instalacao(5,"Jacuzzi",5,horarioJacuzzi);
		jacuzzi.addAutorizado((Funcionario)central.getFuncionario(2));	
		jacuzzi.addAutorizado((Funcionario)central.getFuncionario(4));
		jacuzzi.addAutorizado((Funcionario)central.getFuncionario(6));
		central.addInstalacao(jacuzzi);
		jacuzzi.entrar((Funcionario)central.getFuncionario(5),new Hora(12,0,0));		
		
		Horario horarioLab3=new Horario();
		horarioLab3.addPeriodo(new Periodo(new Hora(9,30,0),new Hora(16,30,0)));
		horarioLab3.addPeriodo(new Periodo(new Hora(17,30,0),new Hora(20,30,0)));
		Instalacao lab3=new Instalacao(6,"Jacuzzi",3,horarioLab3);
		lab3.addAutorizado((Funcionario)central.getFuncionario(1));	
		central.addInstalacao(lab3);
		
		// criar a aplicacao propriamente dita
		MenuCentral app = new MenuCentral( central );
		app.menuPrincipal();
	}

}
