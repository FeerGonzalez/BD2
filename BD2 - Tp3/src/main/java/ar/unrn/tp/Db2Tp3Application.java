package ar.unrn.tp;

import java.awt.EventQueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.unrn.tp.ui.TiendaUI;

@SpringBootApplication
public class Db2Tp3Application {

	public static void main(String[] args) {
		SpringApplication.run(Db2Tp3Application.class, args);
		
		/*
		EventQueue.invokeLater(() -> {
	        
	        TiendaUI tienda = new TiendaUI();
	        tienda.setVisible(true);
	    });
	    */
		
	}
    
	/*
	@Bean
    public CommandLineRunner run(ApplicationContext ctx) {
        return args -> {
            // Obtener el bean de TiendaUI del contexto de Spring
            TiendaUI tienda = ctx.getBean(TiendaUI.class);
            // Pasar el idCliente a la ventana
            // Hacer visible la ventana
            tienda.setVisible(true);
        };
    }*/
        
		/*
		EventQueue.invokeLater(() -> {
	        
	        TiendaUI tienda = new TiendaUI(1L);
	        tienda.setVisible(true);
	    });
	    */
}


