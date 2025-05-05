package gm.zona_fit;

import gm.zona_fit.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;


@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteService clienteService;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion");
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Finalizada");
	}

	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp(){
		logger.info(nl + "*** Aplicacion zonaFitGym ***" +nl);
		var salir =false;
		var consola = new Scanner(System.in);
		while(!salir){
			var opcion = mostrarMenu(consola);
			//salir = ejecutarOpciones(consola, opcion);
			logger.info(" ");
		}
	}
	private int mostrarMenu(Scanner consola){
		logger.info("""
				1. Listar clientes
				2. Buscar cliente
				3. Agregar cliente
				4. Modificar cleinte
				5. Eliminar cleinte
				6. Salir 
				Elige una opcion:\s
				""");
		return  Integer.parseInt(consola.nextLine());
	}
}
