package simulador;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_from_p;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;

public class Registra extends Sim_entity {

	private Sim_port in;
	private double delay;
	
	Registra(String name, double delay) {
		super(name);
		
		this.delay = delay;
		
		//Porta para receber eventos do servidor
		in = new Sim_port("In");
		
		//Adicionando a porta in à entidade Registra
		add_port(in);
	}
	
	public void body() {
		int id = 0;
		while (Sim_system.running()) {
			
			Sim_event e = new Sim_event();
			
			// Aceita eventos apenas da entidade Servidor
            sim_get_next(new Sim_from_p(Sim_system.get_entity_id("Servidor")), e);
            
			//Pega o próximo evento
			sim_get_next(e);
			sim_trace(1, "Registro da leitura iniciada - " + id);
			
			//Processa o evento
			sim_process(delay);
			
			//O evento completou a tarefa
			sim_completed(e);
			sim_trace(1, "Registro da leitura concluído - " + id + "\n");
			
			id++;
			
		}
	}
}
