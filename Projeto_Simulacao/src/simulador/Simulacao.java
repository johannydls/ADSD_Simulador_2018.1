package simulador;

import eduni.simjava.Sim_system;

public class Simulacao {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Sim_system.initialise();
		
		Bilheteria bilheteria = new Bilheteria("Bilheteria", 100);
		
		Entrada entrada = new Entrada("Entrada", 50, 10);
		
		Evento evento = new Evento("Evento", 100, 20);
		
		Camarote camarote = new Camarote("Camarote", 35);
		
		AreaComum areaComum = new AreaComum("Area Comum", 65);
		
		AreaConveniencia areaConveniencia = new AreaConveniencia("Area de Conveniencia", 60, 20);
		
		LojaArtesanato loja = new LojaArtesanato("Loja de Artesanato", 15, 6);
		
		Lanchonete lanchonete = new Lanchonete ("Lanchonete", 40, 10);
		
		Bar bar = new Bar("Bar", 55, 5);
		
		Banheiro banheiro = new Banheiro("Banheiro", 70, 10);
		
		Saida saida = new Saida("Saida", 30);
		
		//Configuração das portas (Entidade de Origem, Porta de Origem, Entidade de Destino, PortaDestino)
		
		Sim_system.link_ports("Bilheteria", "Saida", "Entrada", "Entrada"); //Bilheteria -> Entrada
		
		Sim_system.link_ports("Entrada", "Evento", "Evento", "Entrada"); //Entrada -> Evento
		Sim_system.link_ports("Evento", "Camarote", "Camarote", "Entrada"); //Evento -> Camarote
		Sim_system.link_ports("Evento", "Area Comum", "Area Comum", "Entrada"); //Evento -> Area Comum
		
		Sim_system.link_ports("Camarote", "Saida", "Saida", "In"); //Camarote -> Saida
		Sim_system.link_ports("Area Comum", "Saida", "Saida", "In"); //Area Comum -> Saida
		
		Sim_system.link_ports("Entrada", "Area de Conveniencia", "Area de Conveniencia", "Entrada"); //Entrada -> Area de Conveniencia
		Sim_system.link_ports("Area de Conveniencia", "Loja", "Loja de Artesanato", "Entrada"); //Area de Conveniencia -> Loja de Artesanato
		Sim_system.link_ports("Area de Conveniencia", "Lanchonete", "Lanchonete", "Entrada"); //Area de Conveniencia -> Lanchonete
		Sim_system.link_ports("Area de Conveniencia", "Bar", "Bar", "Entrada"); //Area de Conveniencia -> Bar
		Sim_system.link_ports("Area de Conveniencia", "Banheiro", "Banheiro", "Entrada"); //Area de COnveniencia -> Banheiro
		
		Sim_system.link_ports("Loja de Artesanato", "Evento", "Evento", "Entrada"); //Loja de Artesanato -> Evento
		Sim_system.link_ports("Loja de Artesanato", "Saida", "Saida", "In"); //Loja de Artesanato -> Saida
		
		Sim_system.link_ports("Lanchonete", "Evento", "Evento", "Entrada"); //Loja de Artesanato -> Evento
		Sim_system.link_ports("Lanchonete", "Saida", "Saida", "In"); //Loja de Artesanato -> Saida
		
		Sim_system.link_ports("Bar", "Evento", "Evento", "Entrada"); //Loja de Artesanato -> Evento
		Sim_system.link_ports("Bar", "Saida", "Saida", "In"); //Loja de Artesanato -> Saida
		
		Sim_system.link_ports("Banheiro", "Evento", "Evento", "Entrada"); //Loja de Artesanato -> Evento
		Sim_system.link_ports("Banheiro", "Saida", "Saida", "In"); //Loja de Artesanato -> Saida
		
		//Configura o rastreio para o simulador (default, entity, event)
		Sim_system.set_trace_detail(false, true, false);
				
		Sim_system.run();		
	}
}
