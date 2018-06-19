package simulator;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;

//Classe para os dois discos
public class Disco extends Sim_entity {
	
	private Sim_port in;
	private Sim_normal_obj delay;
	
	//Objeto para as medidas de estatística
	private Sim_stat stat;
	
	Disco (String name, double media, double variancia) {
		
		super(name);
		
		//Porta para receber eventos do processador
		in = new Sim_port("In");
		add_port(in);
		
		//Cria a distribuição do disco
		delay = new Sim_normal_obj("Delay", media, variancia);
		add_generator(delay);
		
		//Medidas de estatística para taxa de utilização do disco
		stat = new Sim_stat();
		stat.add_measure(Sim_stat.UTILISATION);
		set_stat(stat);
	}
	
	public void body() {
		
		while (Sim_system.running()) {
			
			Sim_event e = new Sim_event();
			
			//Pega o próximo evento
			sim_get_next(e);
			sim_trace(1, "Requisição do disco iniciada");
			
			//Processa o evento com a amostra da distribuição
			sim_process(delay.sample());
			
			//O evento completou o serviço
			sim_completed(e);
			sim_trace(1, "Tarefa do disco concluída");
			
		}
	}

}