package simulator;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

public class Processador extends Sim_entity {
	
	private Sim_port in, out1, out2;
	private Sim_normal_obj delay;
	private Sim_random_obj prob;
	
	//Objeto para as medidas de estatisticas
	private Sim_stat stat;
	
	Processador(String name, double media, double variancia) {
		
		super(name);
		
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
		
		//Cria a distribuição e gerador do processador
		delay = new Sim_normal_obj("Delay", media, variancia);
		prob = new Sim_random_obj("Probability");
		add_generator(delay);
		add_generator(prob);
		
		//Medidas de estatistica para taxa de transferencia e 
		//tempo médio de permanencia do processador
		stat = new Sim_stat();
		stat.add_measure(Sim_stat.THROUGHPUT);
		stat.add_measure(Sim_stat.RESIDENCE_TIME);
		
		//Medida de estado nao continuo, para uso de thread do processador
		stat.add_measure("Uso de Thread", Sim_stat.STATE_BASED, false);;
		stat.calc_proportions("Uso de Thread", new double[] {0, 1, 2, 3, 4});
		
		set_stat(stat);
		
	}
	
	//Comportamento do Processador
	public void body() {
		
		//int i = 0;
		
		while (Sim_system.running()) {
			//Cria o evento
			Sim_event e = new Sim_event();
			
			// Predicado - Aceita eventos apenas da entidade Fonte
            //sim_get_next(new Sim_from_p(Sim_system.get_entity_id("Fonte")), e);
			
			//Pega o próximo evento
			sim_get_next(e);
			
			double antes = Sim_system.sim_clock();
			
			//Processa o evento com a amostra da distribuição
			sim_process(delay.sample());
			
			//O evento completou o serviço
			sim_completed(e);
			
			//double p = prob.sample();
			/*
			if ((i % 2) == 0) {
				sim_trace(1, "Disco1 selecionado para trabalho E/S.");
				sim_schedule(out1, 0.0, 1);
			} else {
				sim_trace(1, "Disco2 selecionado para trabalho E/S.");
				sim_schedule(out2, 0.0, 1);
			}
			*/
			
			/*double p = prob.sample();
			
			if (p < 60.0) {
				sim_schedule(out1, 0.0, 1);
			} else {
				sim_schedule(out2, 0.0, 1);
			}*/
			
			double p = prob.sample();
			
			//Dividindo o processador em 3 segmentos
			if (p < 0.15) {
				stat.update("Uso de Thread", 1, antes, Sim_system.sim_clock());
			} else if (p < 0.75) {
				stat.update("Uso de Thread", 2, antes, Sim_system.sim_clock());
			} else {
				stat.update("Uso de Thread", 3, antes, Sim_system.sim_clock());
			}
			
			p = prob.sample();
			
			if (p < 0.60) {
				sim_schedule(out1, 0.0, 1);
			} else {
				sim_schedule(out2, 0.0, 1);
			}
			
			//i++;
		}
	}
}
