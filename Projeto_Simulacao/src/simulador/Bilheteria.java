package simulador;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.distributions.Sim_negexp_obj;

public class Bilheteria extends Sim_entity {

	private Sim_port saida;
	private Sim_negexp_obj delay;
	
	Bilheteria (String nome, double media) {
		
		super(nome);
		
		saida = new Sim_port("Saida");
		add_port(saida);
		
		delay = new Sim_negexp_obj("Delay", media);
		add_generator(delay);
		
	}
	
	public void body() {
		
		for (int i = 0; i < 100; i++) {
			
			sim_schedule(saida, 0.0, 1);
			
			sim_trace(1, "Compra de ingresso de nova pessoa\n");
			
			sim_pause(delay.sample());
		}
	}
}
