import java.util.Random;

public class Reserva extends Thread {
	
	public Reserva() {
		start();
	}
	
	private void reservar() {
		
		Random rdmNum = new Random();
		int r = rdmNum.nextInt(999) + 1;	//	generar un número aleatorio entre 1 y 1000
											//	sirve para dinamizar el método
		int sleepTime = rdmNum.nextInt(250 - 25 + 1) + 25;	//	Rango de espera (max - min +1) + min
		
		try {
			sleep(sleepTime);	//	método de espera, utilizando como atributo el rango anterior
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Concesión de Reservas: " + r + " unidades de Reservas concedidas.");
		
		//	alamcenar en el buffer añadiendo los recursos r reservados
		Buffer.getStore().add(r);
	}
	@Override
	public void run() {
		
		while(true) {
			
			if(Buffer.getStore().size() == Buffer.bufferSize) {	//	comparar la cantidad de reservas contra  
																//	la capacidad de almacenamiento del buffer
				System.out.println("Reservas completas: El buffer está lleno,"
									+ " esperando a que se liberen reservas.");
			}
			try {
				Buffer.getsNoLleno().acquire();	//	comprobar el estado del buffer y adquirir recursos reservados
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			reservar();
			
			Buffer.getsNoVacio().release();	//	comprobar el estado del buffer y liberar recursos reservados
		}
	}
}
