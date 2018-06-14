package simulador;

import eduni.simjava.Sim_system;

public class Simulador {

	public static void main(String[] args) {
		
		//Inicializa o Sim_system
		Sim_system.initialise();
		
		//Adiciona a Fonte
		ImpressaoDigital digital = new ImpressaoDigital("Digital", 40);
		
		//Adiciona o processador
		Servidor servidor = new Servidor("Servidor", 70);
		
		//Adiciona o disco 1
		Registra registra = new Registra("Registra", 50);
		
		//Liga as portas das entidades
		Sim_system.link_ports("Digital", "Out", "Servidor", "In");
		Sim_system.link_ports("Servidor", "Registrar", "Registra", "In");
		Sim_system.link_ports("Servidor", "Out", "Registra", "In");
		
		//Configura o rastreio para o simulador (default, entity, event)
		Sim_system.set_trace_detail(false, true, false);
		
		//Executa a simulação
		Sim_system.run();
		
	}
}
