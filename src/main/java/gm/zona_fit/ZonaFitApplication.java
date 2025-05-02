package gm.zona_fit;

import gm.zona_fit.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ZonaFitApplication {

	@Autowired
	private IClienteService clienteService;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(ZonaFitApplication.class, args);
	}

}
