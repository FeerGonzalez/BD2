package ar.unrn.tp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Db2Tp3Application {

	public static void main(String[] args) {
		SpringApplication.run(Db2Tp3Application.class, args);
		/*
		EventQueue.invokeLater(() -> {
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
	        EntityManager em = emf.createEntityManager();
	        TiendaUI tienda = new TiendaUI(em, 1L);
	        tienda.setVisible(true);
	    });*/
	}

}
