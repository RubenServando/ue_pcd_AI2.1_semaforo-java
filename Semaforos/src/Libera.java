import java.util.Random;

public class Libera extends Thread {
	
	public Libera() {
		start();
	}
	
	public void liberar() {
		
		Random rdmNum = new Random();
		int sleepTime = rdmNum.nextInt(250 - 25 + 1) + 25;	//	Rango (max - min + 1) + min
		
		try {
			sleep(sleepTime);	//	método de espera, utilizando como atributo el rango anterior
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//	liberar los recursos
		int l = Buffer.getStore().poll();	//	aplicar método poll, el primero en cola 
											//	ha de salir (FIFO) (l = recursoLiberado)
		System.out.println("Liberación de Reservas: " + l + " unidades de Reservas liberadas.");
	}
	@Override
	public void run() {
		while(true) {
			if(Buffer.getStore().size() == 0) {	//	comprobar que el buffer está vacío -> igual a 0
				System.out.println("Reservas libres: El buffer está vacío,"
									+ "esperando a que se concedan reservas.");				
			}
			
			try {
				Buffer.getsNoVacio().acquire();	//	comprobar el estado del buffer y adquirir recursos liberados
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			liberar();
			Buffer.getsNoLleno().release();	//	comprobar el estado del buffer y liberar recursos reservados	
		}
	}
}
