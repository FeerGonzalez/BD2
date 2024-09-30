package ar.unrn.tp.ui;
import javax.swing.*;

import ar.unrn.tp.jpa.servicios.JPAClienteService;
import ar.unrn.tp.jpa.servicios.JPADescuentoService;
import ar.unrn.tp.jpa.servicios.JPAProductoService;
import ar.unrn.tp.jpa.servicios.JPAVentaService;
import ar.unrn.tp.modelo.Descuento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class TiendaUI extends JFrame {

    private JList<String> productList;
    private JList<String> cardList;
    private JTextArea discountArea;
    private JButton calculateButton;
    private JButton purchaseButton;
    
    private JPAClienteService clienteService;
    private JPADescuentoService descuentoService;
    private JPAProductoService productoService;
    private JPAVentaService ventaService;

    public TiendaUI(EntityManager em, Long idCliente) {
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
        productList = new JList<>(productoService.listarProductos().toArray(new String[0]).);
        productList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panel.add(new JScrollPane(productList));

        // Área de descuentos
        discountArea = new JTextArea();
        discountArea.setText(String.join("\n", 
        	    descuentoService.listarDescuentosActivos().stream().map(Descuento::toString).toList()));
        discountArea.setEditable(false);
        panel.add(new JScrollPane(discountArea));

        // Lista de tarjetas
        cardList = new JList<>(clienteService.listarTarjetas(idCliente).toArray(new String[0]));
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
        List<String> seleccionados = productList.getSelectedValuesList();
        if (seleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona al menos un producto.");
        } 
        JOptionPane.showMessageDialog(this, ventaService.calcularMonto(, null));
        
    }

    private void realizarCompra() {
        String tarjetaSeleccionada = cardList.getSelectedValue();
        if (tarjetaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una tarjeta de crédito.");
        } else {
            // Aquí se simularía la compra
            JOptionPane.showMessageDialog(this, "Compra realizada con éxito.");
        }
    }

    public static void main(String[] args) {
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

        // Crear y mostrar la UI
        TiendaUI tienda = new TiendaUI(productos, descuentos, tarjetas);
        tienda.setVisible(true);
    }
}
