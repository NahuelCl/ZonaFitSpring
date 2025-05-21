package gm.zona_fit.gui;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.service.ClienteService;
import gm.zona_fit.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component

public class ZonaFitForm extends JFrame{
    private JPanel panelPrincipal;
    private JTable clientesTabla;
    private JScrollPane scrollPane;
    private JTextField nombreTexto;
    private JTextField apellidoTexto;
    private JTextField membresiaNumero;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton clearButton;
    IClienteService clienteService;
    private DefaultTableModel tablaModeloClientes;
    private Integer idCliente;

    @Autowired
    public ZonaFitForm(ClienteService clienteServicio){
        this.clienteService = clienteServicio;
        createUIComponents();
        iniciarForma();
        SwingUtilities.invokeLater(this::listarClientes);
        saveButton.addActionListener( e ->guardarCliente());
        clientesTabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarClienteSeleccionado();
            }
        });
        deleteButton.addActionListener(e -> eliminarCliente());
        clearButton.addActionListener(e -> limpiarFormulario());
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
        this.tablaModeloClientes = new DefaultTableModel(cabeceros, 0){
            @Override
            public boolean isCellEditable(int row, int cell){
                return false;
            }
        };
        this.clientesTabla = new JTable(this.tablaModeloClientes);

        this.clientesTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

    private void guardarCliente(){
        if(nombreTexto.getText().equals("")){
            mostrarMensaje("Proporciona un nombre");
            nombreTexto.requestFocusInWindow();
            return;
        }
        if(membresiaNumero.getText().equals("")){
            mostrarMensaje("Proporciona membresia");
            membresiaNumero.requestFocusInWindow();
            return;
        }

        var nombre = nombreTexto.getText();
        var apellido = apellidoTexto.getText();
        var membresia = Integer.parseInt(membresiaNumero.getText());
        var cliente = new Cliente(this.idCliente,nombre,apellido,membresia);
        this.clienteService.guardarCliente(cliente);
        if(this.idCliente == null){
            mostrarMensaje("Se agrego el nuevo cliente");
        }else {
            mostrarMensaje("Se actualiizo el cliente");
        }

        limpiarFormulario();
        listarClientes();

    }

    private void cargarClienteSeleccionado(){
        var renglon = clientesTabla.getSelectedRow();
        if (renglon != -1){
            var id = clientesTabla.getModel().getValueAt(renglon, 0).toString();
            this.idCliente = Integer.parseInt(id);
            var nombre = clientesTabla.getModel().getValueAt(renglon, 1).toString();
            this.nombreTexto.setText(nombre);
            var apellido = clientesTabla.getModel().getValueAt(renglon, 2).toString();
            this.apellidoTexto.setText(apellido);
            var membresia = clientesTabla.getModel().getValueAt(renglon, 3).toString();
            this.membresiaNumero.setText(membresia);
        }
    }

    private void eliminarCliente(){
        var renglon = clientesTabla.getSelectedRow();
        if (renglon != -1){
            var idClienteStr = clientesTabla.getModel().getValueAt(renglon,0).toString();
            this.idCliente = Integer.parseInt(idClienteStr);
            var cliente = new Cliente();
            cliente.setId(this.idCliente);
            clienteService.eliminarCliente(cliente);
            mostrarMensaje("Cliente con id: "+ this.idCliente +" eliminado.");
            limpiarFormulario();
            listarClientes();
        }
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje);
    }

    private void limpiarFormulario(){
        nombreTexto.setText("");
        apellidoTexto.setText("");
        membresiaNumero.setText("");
        this.idCliente = null;
        this.clientesTabla.getSelectionModel().clearSelection();
    }



}

