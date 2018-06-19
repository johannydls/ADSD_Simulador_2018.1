package simulador;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_negexp_obj;

public class Saida extends Sim_entity {

	private Sim_port entrada;
	private Sim_negexp_obj delay;
	
	Saida (String nome, double media) {
		
		super(nome);
		
		entrada = new Sim_port("Entrada");
		add_port(entrada);
		
		delay = new Sim_negexp_obj("Delay", media);
		add_generator(delay);
	}
	
	public void body() {
		
		while (Sim_system.running()) {
			
			Sim_event e = new Sim_event();
			
			sim_get_next(e);
			
			sim_process(delay.sample());
			
			sim_completed(e);
			
			sim_trace(1, "Pessoa vai pra casa");
		}
	}
	
}
