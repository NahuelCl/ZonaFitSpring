package gm.zona_fit;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
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
			salir = ejecutarOpciones(consola, opcion);
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

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		var salir = false;
		switch (opcion){
			case 1 -> {
				logger.info(nl + "--- Listado de Clientes ---" + nl);
				List<Cliente> clientes = clienteService.listarClientes();
				clientes.forEach(cliente -> logger.info(cliente.toString() + nl));
			}
			case 2 -> {
				logger.info(nl + "--- Buscar Cliente ---" + nl);
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente c = clienteService.buscarClientePorId(idCliente);
				if (c == null){
					logger.info("Cliente no encontrado"+ nl);
				}else{
					logger.info("Cliente encontrado:" + c + nl);
				}
			}
			case 3 ->{
				logger.info(nl + "--- Agregar Cliente ---" + nl);
				logger.info("Nombre: ");
				var nombre = consola.nextLine();
				logger.info("Apellido:");
				var apellido = consola.nextLine();
				logger.info("Membresia: ");
				var membresia = Integer.parseInt(consola.nextLine());
				var cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setMembresia(membresia);
				clienteService.guardarCliente(cliente);
				logger.info(nl + "Cliente agregado:" + cliente + nl);
			}
			case 4 ->{
				logger.info(nl + "--- Modificar Cliente ---" + nl);
				logger.info("Id cliente:");
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente c = clienteService.buscarClientePorId(idCliente);
				if(c != null){
					logger.info("Nombre:");
					var nombre = consola.nextLine();
					logger.info("Apellido:");
					var apellido = consola.nextLine();
					logger.info("Membresia:");
					var membresia = Integer.parseInt(consola.nextLine());
					c.setNombre(nombre);
					c.setApellido(apellido);
					c.setMembresia(membresia);
					clienteService.guardarCliente(c);
					logger.info("Cliente modificado" + c + nl);
				}else{
					logger.info("Cliente no encontrado" + nl);
				}
			}
			case 5 ->{
				logger.info(nl + "--- Eliminar Cliente ---" + nl);
				logger.info("Id cliente:");
				var idCliente = Integer.parseInt(consola.nextLine());
				Cliente c = clienteService.buscarClientePorId(idCliente);
				if(c != null){
					clienteService.eliminarCliente(c);
				}else{
					logger.info("El cliente no existe" + nl);
				}
			}
			case 6 ->{
				logger.info("Hasta pronto" + nl);
				salir = true;
			}
			default -> logger.info("Opcion incorrecta" + nl);
		}
		return salir;
	}
}
