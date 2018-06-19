package simulator;

import eduni.simjava.Sim_system;

public class Subsistema_Processador {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		//Inicializa o Sim_system
		Sim_system.initialise();
		
		//Adiciona a Fonte com probabilidade de média 150.45
		Fonte fonte = new Fonte("Fonte", 150.45);
		
		//Adiciona o processador com distrib. normal média 110.5 e variância 90.5
		Processador processador = new Processador("Processador", 110.5, 90.5);
		
		//Adiciona o disco 1 com distrib. normal média 130 e variância 65
		Disco disco1 = new Disco("Disco1", 130.0, 65.0);
		
		//Adiciona o disco 2 com distrib. normal média 350.55 e variância 200.5
		Disco disco2 = new Disco("Disco2", 350.5, 200.5);
		
		//Liga as portas das entidades
		Sim_system.link_ports("Fonte", "Out", "Processador", "In");
		Sim_system.link_ports("Processador", "Out1", "Disco1", "In");
		Sim_system.link_ports("Processador", "Out2", "Disco2", "In");
		
		//Configura o rastreio para o simulador (default, entity, event)
		Sim_system.set_trace_detail(false, true, false);
		
		//Executa a simulação
		Sim_system.run();
	}

}
