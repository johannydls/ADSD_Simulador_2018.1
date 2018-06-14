package simulator;

import java.util.Random;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_from_p;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_random_obj;

public class Processador extends Sim_entity {
	
	private Sim_port in, out1, out2;
	private double delay;
	private Sim_random_obj prob;
	
	Processador(String name, double delay) {
		super(name);
		
		this.delay = delay;
		
		//Porta para receber eventos da Fonte
		in = new Sim_port("In");
		
		//Porta para enviar eventos ao disco 1
		out1 = new Sim_port("Out1");
		
		//Porta para enviar eventos ao disco 2
		out2 = new Sim_port("Out2");
		
		//Adicionando as portas para a entidade
		add_port(in);
		add_port(out1);
		add_port(out2);
		
		//add_generator(prob);
	}
	
	//Comportamento do Processador
	public void body() {
		
		int i = 0;
		
		while (Sim_system.running()) {
			//Cria o evento
			Sim_event e = new Sim_event();
			
			// Accept events only from the source entity
            sim_get_next(new Sim_from_p(Sim_system.get_entity_id("Fonte")), e);
			
			//Pega o próximo evento
			sim_get_next(e);
			
			//Processa o evento
			sim_process(delay);
			
			//O evento completou o serviço
			sim_completed(e);
			
			//double p = prob.sample();
			
			/*if ((i % 2) == 0) {
				sim_schedule(out1, 0.0, 1);
			} else {
				sim_schedule(out2, 0.0, 1);
			}*/
			double p = new Random().nextDouble();
			
			if (p > 40.0) {
				sim_schedule(out1, 0.0, 1);
			} else {
				sim_schedule(out2, 0.0, 1);
			}
			
			
			//i++;
		}
	}
}
