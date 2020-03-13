package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarros extends Thread {
	
	private int idCar;
	private int escuderia;
	private CorridaController corrida;
	private Semaphore semaforo;
	private Semaphore adiciona = new Semaphore(1);
	
	

	
	public ThreadCarros(int idCar, int escuderia, CorridaController corrida, Semaphore sema) {
		super();
		this.idCar = idCar;
		this.escuderia = escuderia;
		this.corrida = corrida;
		semaforo = sema;
	}


	private void carroNaPista() {
		int distanciaTotal = 1000;
		int distanciaPercorrida = 0;
		int deslocamento = (int) ((Math.random() * 25) + 15);
		int tempoTotal = 0;
		int tempo = 100;
		System.out.println("Carro #" + idCar + " Escuderia: "+ escuderia +" começou a correr " + distanciaPercorrida + "m.");
		while (distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += deslocamento;
			try {
				sleep(tempo);
				tempoTotal += 1;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Carro #" + idCar + " Escuderia: "+ escuderia +" já percorreu " + distanciaPercorrida + "m.");
		}
		System.out.println("Carro #" + idCar + " Escuderia: "+ escuderia +" completou o circuito, tempo: " + tempoTotal);
		
		corrida.adicionaColocacao(tempoTotal,idCar,escuderia);

	}

	
	@Override
	public void run() {
		try {
			semaforo.acquire();
			carroNaPista();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
			if(corrida.getIndice() == 14) {
				corrida.MostraPlacar();
			}
		}
	}

}
