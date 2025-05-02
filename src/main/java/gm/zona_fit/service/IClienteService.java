package gm.zona_fit.service;
import gm.zona_fit.modelo.Cliente;
import java.util.List;

public interface IClienteService {
    public List<Cliente> listarClientes();

    public Cliente buscarClientePorId(Integer idCliente);

    public void guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);
}
