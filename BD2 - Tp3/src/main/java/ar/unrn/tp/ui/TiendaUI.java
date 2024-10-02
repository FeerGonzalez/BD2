package ar.unrn.tp.ui;
import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.unrn.tp.dto.ProductoDTO;
import ar.unrn.tp.dto.TarjetaDTO;
import ar.unrn.tp.jpa.servicios.JPAClienteService;
import ar.unrn.tp.jpa.servicios.JPADescuentoService;
import ar.unrn.tp.jpa.servicios.JPAProductoService;
import ar.unrn.tp.jpa.servicios.JPAVentaService;
import ar.unrn.tp.modelo.Descuento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

@Component
public class TiendaUI extends JFrame {

    private JList<ProductoDTO> productList;
    private JList<TarjetaDTO> cardList;
    private JTextArea discountArea;
    private JButton calculateButton;
    private JButton purchaseButton;
    
    @PersistenceContext
    private EntityManager em;
    private JPAClienteService clienteService;
    private JPADescuentoService descuentoService;
    private JPAProductoService productoService;
    private JPAVentaService ventaService;

    public TiendaUI(Long idCliente) {
    	this.clienteService = new JPAClienteService(em);
    	this.productoService = new JPAProductoService(em);
    	this.descuentoService = new JPADescuentoService(em);
    	this.ventaService = new JPAVentaService(em, clienteService, productoService);
    	
    	
        setTitle("Tienda Virtual");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 1));

        // Lista de productos
        DefaultListModel<ProductoDTO> productModel = new DefaultListModel<>();
        
        for (ProductoDTO producto : productoService.listarProductosDTO()) {
            productModel.addElement(producto);
        }
        productList = new JList<>(productModel);
        productList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panel.add(new JScrollPane(productList));

        // Área de descuentos
        // DTO?
        discountArea = new JTextArea();
        discountArea.setText(String.join("\n", 
        	    descuentoService.listarDescuentosActivos().stream().map(Descuento::toString).toList()));
        discountArea.setEditable(false);
        panel.add(new JScrollPane(discountArea));

        // Lista de tarjetas
        DefaultListModel<TarjetaDTO> tarjetaModel = new DefaultListModel<>();
        for (TarjetaDTO tarjeta : clienteService.listarTarjetasDTO(idCliente)) {
        	tarjetaModel.addElement(tarjeta);
        }
        
        cardList = new JList<>(tarjetaModel);
        panel.add(new JScrollPane(cardList));

        // Botón para calcular el precio total
        calculateButton = new JButton("Precio Total");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPrecioTotal();
            }
        });
        panel.add(calculateButton);

        // Botón para realizar la compra
        purchaseButton = new JButton("Realizar Compra");
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarCompra();
            }
        });
        panel.add(purchaseButton);

        // Añadir panel al frame
        add(panel, BorderLayout.CENTER);
    }

    private void calcularPrecioTotal() {
    	
    	List<ProductoDTO> productosSeleccionados = productList.getSelectedValuesList();
        if (productosSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona al menos un producto.");
        }
        List<Long> listaDeIdsDeProductos = new ArrayList<>();
        for (ProductoDTO productoDTO : productosSeleccionados) {
        	listaDeIdsDeProductos.add(productoDTO.getId());
		}
        TarjetaDTO tarjetaSeleccionada = cardList.getSelectedValue();
        JOptionPane.showMessageDialog(this, ventaService.calcularMonto(listaDeIdsDeProductos, tarjetaSeleccionada.getId()));
        
    }

    private void realizarCompra() {
        TarjetaDTO tarjetaSeleccionada = cardList.getSelectedValue();
        if (tarjetaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una tarjeta de crédito.");
        } else {
            JOptionPane.showMessageDialog(this, "Compra realizada con éxito.");
        }
    }

   /* public static void main(String[] args) {
        // Datos simulados
        List<String> productos = new ArrayList<>();
        productos.add("Producto 1");
        productos.add("Producto 2");
        productos.add("Producto 3");

        List<String> descuentos = new ArrayList<>();
        descuentos.add("Descuento 1: 10%");
        descuentos.add("Descuento 2: 15%");

        List<String> tarjetas = new ArrayList<>();
        tarjetas.add("Tarjeta 1");
        tarjetas.add("Tarjeta 2");
        
        // Crear el EntityManagerFactory a partir del persistence unit
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

        // Crear el EntityManager
        EntityManager em = emf.createEntityManager();

        // Crear y mostrar la UI
        TiendaUI tienda = new TiendaUI(em, 1L);
        tienda.setVisible(true);
    }*/
}
