package simulator;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.distributions.Sim_negexp_obj;

public class Fonte extends Sim_entity {
	
	private Sim_port out;
	private Sim_negexp_obj delay;

	public Fonte(String name, double media) {
		
		//Chamada ao construtor de Sim_entity passando o nome da entidade
		//O nome de cada entidade deve ser único
		super(name);
		
		//Cria a porta e adiciona à entidade Source
		out = new Sim_port("Out");
		add_port(out);
		
		//Cria a distribuição da fonte e adiciona-a
		delay = new Sim_negexp_obj("Delay", media);
		add_generator(delay);
		
	}
	
	public void body() {
		
		for (int i = 0; i < 100; i++) {
			
			//Envia uma tarefa para o processador
			sim_schedule(out, 0.0, 0);
			sim_trace(1, "Nova solicitação do processador\n");
			
			//Pausa
			sim_pause(delay.sample());
			
		}
	}

}
