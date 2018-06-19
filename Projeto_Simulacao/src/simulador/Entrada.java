package simulador;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

public class Entrada extends Sim_entity {
	
	Sim_port entrada, evento, areaConveniencia;
	Sim_normal_obj delay;
	Sim_random_obj prob;
	
	Entrada (String nome, double media, double variancia) {
		
		super(nome);
		
		entrada = new Sim_port("Entrada");
		evento = new Sim_port("Evento");
		areaConveniencia = new Sim_port("Area de Conveniencia");
		
		add_port(entrada);
		add_port(evento);
		add_port(areaConveniencia);
		
		delay = new Sim_normal_obj("Delay", media, variancia);
		prob = new Sim_random_obj("Probability");
		
		add_generator(delay);
		add_generator(prob);
		
	}
	
	public void body() {
		
		while (Sim_system.running()) {
			
			Sim_event e = new Sim_event();
			sim_get_next(e);
			
			sim_process(delay.sample());
			
			sim_completed(e);
			
			double p = prob.sample();
			
			if (p <= 0.10) {
				//10% vai direto para o evento
				sim_schedule(evento, 0.0, 1);
			} else {
				//90% vai para area de conveniencia antes do evento
				sim_schedule(areaConveniencia, 0.0, 1);
			}
		}
	}

}
