package gm.zona_fit.gui;

import gm.zona_fit.service.ClienteService;
import gm.zona_fit.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@Component

public class ZonaFitForm extends JFrame{
    private JPanel panelPrincipal;
    private JTable clientesTabla;
    private JScrollPane scrollPane;
    IClienteService clienteService;
    private DefaultTableModel tablaModeloClientes;

    @Autowired
    public ZonaFitForm(ClienteService clienteServicio){
        this.clienteService = clienteServicio;
        createUIComponents();
        iniciarForma();
        SwingUtilities.invokeLater(this::listarClientes);
    }

    private void iniciarForma(){
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        String[] cabeceros = {"Id", "Nombre", "Apellido", "Membresia"};
        this.tablaModeloClientes = new DefaultTableModel(cabeceros, 0);
        this.clientesTabla = new JTable(this.tablaModeloClientes);

        if (scrollPane != null) {
            scrollPane.setViewportView(clientesTabla);
        }
    }

    private void listarClientes(){
        this.tablaModeloClientes.setRowCount(0);
        var clientes = this.clienteService.listarClientes();
        clientes.forEach(cliente -> {
            Object[] renglonCliente = new Object[]{
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getMembresia()
            };
            this.tablaModeloClientes.addRow(renglonCliente);
        });
    }
}

