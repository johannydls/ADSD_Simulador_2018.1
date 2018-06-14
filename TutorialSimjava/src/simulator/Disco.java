package simulator;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;

//Classe para os dois discos
public class Disco extends Sim_entity {
	
	private Sim_port in;
	private double delay;
	
	Disco (String name, double delay) {
		
		super(name);
		
		this.delay = delay;
		
		//Porta para receber eventos do processador
		in = new Sim_port("In");
		
		//Adicionando a porta in à entidade Disco
		add_port(in);
	}
	
	public void body() {
		
		while (Sim_system.running()) {
			
			Sim_event e = new Sim_event();
			
			//Pega o próximo evento
			sim_get_next(e);
			sim_trace(1, "Requisição do disco iniciada\n");
			
			//Processa o evento
			sim_process(delay);
			
			//O evento completou o serviço
			sim_completed(e);
			
		}
	}

}