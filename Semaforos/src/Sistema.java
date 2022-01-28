
public class Sistema {
	
	public static void main(String[] args) {

		new Reserva(); 	//	objeto que genera unidades de recursos y reserva espacio en el buffer 
						//	esperando a que sean liberados antes de poder generar nuevos recursos
		new Libera();	//	objeto que libera los recursos generados por la reserva y almacenados en el buffer	
	
	}

}
