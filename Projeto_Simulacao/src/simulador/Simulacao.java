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
	}
}
