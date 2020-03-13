package controller;

import java.util.concurrent.Semaphore;

public class ThreadEscuderia extends Thread {
	private int idEscuderia;
	private Semaphore semaforoEscudo;
	private Semaphore semaforoCarros;
	private CorridaController corrida;
	private int idCarro;

	public ThreadEscuderia(int idEscuderia, CorridaController corrida, Semaphore sema) {
		super();
		this.idEscuderia = idEscuderia;
		this.corrida = corrida;
		this.semaforoCarros = sema;
		this.semaforoEscudo = new Semaphore(1);
		this.idCarro = (int) ((Math.random() * 2) + 1);
	}

	private void chamaCarro() {
		Thread car = new ThreadCarros(idCarro, this.idEscuderia, this.corrida, this.semaforoCarros);
		car.start();
		while(car.isAlive()) {
			//espera
		}
		if(idCarro == 1) {
			idCarro = 2;
		}else {
			idCarro = 1;
		}
	}

	@Override
	public void run() {

		try {
			semaforoEscudo.acquire();
			for (int x = 1; x <= 2; x++) {
				chamaCarro();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforoEscudo.release();

		}

	}

}
