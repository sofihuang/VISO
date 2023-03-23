


//import fundamentos.*;
/**
 * Representa la persona 
 */
public class Persona extends Thread
{
	// Atributos de la persona en movimiento por el edificio
	private int plantaOrigen;   // Planta de partida
	private int plantaDestino;  // Planta destino
	private int retraso;        // Tiempo que transcurre en el cambio de posicion
	private ColorFig color;     // Color que representa a la persona en el dibujo
	private Ascensor ascensor;  // Ascensor que se utiliza

	// Estado del movimiento y tiempo de espera real
	int estado=0;
	int tiempo;

	
	public Persona(int plantaOrigen, int plantaDestino,int retraso,ColorFig color, Ascensor ascensor)
	{
		this.plantaOrigen=plantaOrigen;
		this.plantaDestino=plantaDestino;
		this.retraso=retraso;
		this.color=color;
		this.ascensor=ascensor;
		ascensor.insertaEnPasillo(this,plantaOrigen);
	}

	
	public int plantaOrigen ()
	{
		return plantaOrigen;
	}

	
	public int plantaDestino ()
	{
		return plantaDestino;
	}

	
	public int retraso ()
	{
		return retraso;
	}

	
	public ColorFig color ()
	{
		return color;
	}

	
	public Ascensor ascensor ()
	{
		return ascensor;
	}

	
	private void camina () throws InterruptedException
	{

		//Si la persona esta en el pasillo
		sleep(retraso);
		ascensor.borraDePasillo(this, plantaOrigen);
		ascensor.insertaEnEspera(this, plantaOrigen);

		//Si la persona esta en espera

		sleep(retraso);
		//Si el ascensor ha llegado a la planta actual y esta parado
		
		ascensor.subePersona();
		ascensor.pulsaBoton();
		ascensor.subirAscensor(plantaOrigen);
		ascensor.atendido();
		//Se sube al ascensor
		
		ascensor.borraDeEspera(this, plantaOrigen);
		ascensor.insertaEnAscensor(this);
		
		
		//Si esta en el ascensor
		//sleep(retraso);
		ascensor.bajarAscensor(plantaDestino);
			
		ascensor.borraDeAscensor(this);
		ascensor.insertaEnEspera(this, plantaDestino);

		//Si la persona ha llegado a la sala de espera destino
		sleep(retraso);
		ascensor.borraDeEspera(this, plantaDestino);
		ascensor.insertaEnPasillo(this, plantaDestino);
		int planta = plantaOrigen;
		plantaOrigen = plantaDestino;
		plantaDestino = planta;

	}



	public void run(){

		try{
			while(true){
				camina();
			}
		}catch (InterruptedException e){
			return;
		}

	}
}













