package simulador;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

public class Evento extends Sim_entity {
	
	private Sim_port entrada, camarote, areaComum;
	private Sim_normal_obj delay;
	private Sim_random_obj prob;
	
	//Objeto para as medidas de estatisticas
	private Sim_stat stat;
	
	Evento (String nome, double media, double variancia) {
		
		super(nome);
		
		//Criando porta de entrada
		entrada = new Sim_port("Entrada");
		
		//Criando porta para Camarote
		camarote = new Sim_port("Camarote");
		
		//Criando porta para Area Comum
		areaComum = new Sim_port("Area Comum");
		
		//Adicionando portas para Evento
		add_port(entrada);
		add_port(camarote);
		add_port(areaComum);
		
		//Gerando distribuilcao de probabilidade
		delay = new Sim_normal_obj("Delay", media, variancia);
		prob = new Sim_random_obj("Probability");
		add_generator(delay);
		add_generator(prob);
		
		//Medidas de estatistica
		stat = new Sim_stat();
		
		stat.add_measure(Sim_stat.ARRIVAL_RATE); //Taxa de chegada
		stat.add_measure(Sim_stat.QUEUE_LENGTH); //Tamanho da fila
		stat.add_measure(Sim_stat.WAITING_TIME); //Tempo de espera
		stat.add_measure(Sim_stat.UTILISATION);  //Utilização
		stat.add_measure(Sim_stat.RESIDENCE_TIME); //Tempo de resposta
		
		set_stat(stat);
		
	}
	
	public void body() {
		
		while (Sim_system.running()) {
			
			//Cria o evento
			Sim_event e = new Sim_event();
			
			//Pega o proximo evento
			sim_get_next(e);
			
			//Processa o evento com a amostra da distribuicao
			sim_process(delay.sample());
			
			//Completa a execução do evento
			sim_completed(e);
			
			double p = prob.sample();
			
			if (p <= 0.35) {
				//35% vai para o camarote
				sim_trace(1, "Pessoa vai para o camarote");
				sim_schedule(camarote, 0.0, 1);
				
			} else {
				//65% vai para a area comum
				sim_trace(1, "Pessoa vai para area comum");
				sim_schedule(areaComum, 0.0, 1);
			}
			
		}
	}

}
