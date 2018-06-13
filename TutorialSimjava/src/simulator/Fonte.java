package simulator;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;

public class Fonte extends Sim_entity {
	
	private Sim_port out;
	private double delay;

	public Fonte(String name, double delay) {
		
		//Chamada ao construtor de Sim_entity passando o nome da entidade
		//O nome de cada entidade deve ser único
		super(name);
		
		this.delay = delay;
		
		//Cria a porta
		out = new Sim_port("Out");
		
		//Adiciona a porta à entidade Source
		add_port(out);
		
	}
	
	public void body() {
		
		for (int i = 0; i < 100; i++) {
			
			//Envia uma tarefa para o processador
			sim_schedule(out, 0.0, 0);
			sim_trace(1, "Nova solicitação do processador");
			
			//Pausa
			sim_pause(delay);
			
		}
	}

}
