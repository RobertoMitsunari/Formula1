package controller;

import java.util.concurrent.Semaphore;

public class CorridaController {
	private Integer[] tempoGrid;
	private String[] placar;
	private int indice;

	public CorridaController() {
		tempoGrid = new Integer[14];
		placar = new String[14];
		indice = 0;
	}

	public void adicionaColocacao(Integer tempo, int idCar, int escuderia) {

		placar[indice] = "Carro: " + idCar + " Escuderia: " + escuderia;
		tempoGrid[indice] = tempo;
		indice++;
	}

	public void comecarCorrida() {
		Thread escuderia = null;
		Semaphore samaforo = new Semaphore(5);
		for (int i = 1; i <= 7; i++) {
			escuderia = new ThreadEscuderia(i, this, samaforo);
			escuderia.start();
		}
	}

	public void MostraPlacar() {
		int lenght = tempoGrid.length - 1;

		int auxInt;
		int x;
		String auxStr;
		

		for (x = 0; x <= lenght - 1; x++) {
			for (int y = (x + 1); y <= lenght; y++) {
				if (tempoGrid[x] > tempoGrid[y]) {
					auxInt = tempoGrid[x];
					tempoGrid[x] = tempoGrid[y];
					tempoGrid[y] = auxInt;
					auxStr = placar[x];
					placar[x] = placar[y];
					placar[y] = auxStr;
				}
			}
		}

		for ( x = 0; x <= lenght; x++) {

			System.out.println("Lugar: " + (x + 1) + " " + placar[x] + " tempo: " + tempoGrid[x]);
		}
	}

	public int getIndice() {
		return this.indice;
	}

}
