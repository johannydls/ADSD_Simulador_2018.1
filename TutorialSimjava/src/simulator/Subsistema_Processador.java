package simulator;

import eduni.simjava.Sim_system;

public class Subsistema_Processador {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		//Inicializa o Sim_system
		Sim_system.initialise();
		
		//Adiciona a Fonte
		Fonte fonte = new Fonte("Fonte", 50);
		
		//Adiciona o processador
		Processador processador = new Processador("Processador", 30);
		
		//Adiciona o disco 1
		Disco disco1 = new Disco("Disco1", 60);
		
		//Adiciona o disco 2
		Disco disco2 = new Disco("Disco2", 110);
		
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
