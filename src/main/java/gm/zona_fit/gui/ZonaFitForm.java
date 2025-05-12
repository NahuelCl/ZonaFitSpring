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
    private JScrollPane scrollPane1;
    IClienteService clienteService;
    private DefaultTableModel tablaModeloCliente;

    @Autowired
    public ZonaFitForm(ClienteService clienteServicio){
        $$$setupUI$$$();
        this.clienteService = clienteServicio;
        iniciarForma();
    }

    private void iniciarForma(){
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tablaModeloCliente = new DefaultTableModel(0,4);
        String[] cabeceros =  {"Id", "Nombre", "Apellido", "Membresia"};
        this.tablaModeloCliente.setColumnIdentifiers(cabeceros);
        this.clientesTabla = new JTable(tablaModeloCliente);
    }
}
