package simulador;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_from_p;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_random_obj;

public class Servidor extends Sim_entity {

	private Sim_port in, reg, out;
	
	private Sim_random_obj prob;
	
	private double delay;
	
	Servidor(String name, double delay) {
		super(name);
		
		this.delay = delay;
		
		//Porta para receber eventos da Fonte
		in = new Sim_port("In");
		
		//Porta para enviar eventos ao registrador
		reg = new Sim_port("Registrar");
		
		//Porta para sair do fluxo
		out = new Sim_port("Out");

		//Adicionando as portas para a entidade
		add_port(in);
		add_port(reg);
		add_port(out);
		
		//Probabilidade da leitura dar certo
		add_generator(prob);
	}
	
	public void body() {
		
		while (Sim_system.running()) {
			//Cria o evento
			Sim_event e = new Sim_event();
			
			// Aceita eventos apenas da entidade Fonte
            sim_get_next(new Sim_from_p(Sim_system.get_entity_id("Digital")), e);
			
			//Pega o próximo evento
			sim_get_next(e);
			
			//Processa o evento
			sim_process(delay);
			
			//O evento completou o serviço
			sim_completed(e);
			
			double p = prob.sample();
			
			if (p > 0.20) {//80% da leitura dar certo
				sim_schedule(reg, 0.0, 1);
				
			} else {//20% da leitura dar errado
				sim_schedule(out, 0.0, 1);
				sim_trace(1, "Erro de leitura");
			}
		
		}
	}
}
