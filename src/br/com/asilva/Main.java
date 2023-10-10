package br.com.asilva;

import br.com.asilva.dao.ClienteMapDAO;
import br.com.asilva.dao.IClienteDAO;
import br.com.asilva.domain.Cliente;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        IClienteDAO clienteDAO = new ClienteMapDAO();

        while (true) {
            String input = JOptionPane.showInputDialog("Escolha uma operação:\n" +
                    "1. Cadastrar Cliente\n" +
                    "2. Consultar Cliente\n" +
                    "3. Excluir Cliente\n" +
                    "4. Listar Todos os Clientes\n" +
                    "5. Sair");

            if (input == null) {
                break;
            }

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
                continue;
            }

            switch (choice) {
                case 1:
                    cadastrarCliente(clienteDAO);
                    break;
                case 2:
                    consultarCliente(clienteDAO);
                    break;
                case 3:
                    excluirCliente(clienteDAO);
                    break;
                case 4:
                    listarClientes(clienteDAO);
                    break;
                case 5:
                    // O usuário escolheu sair, então saímos do loop
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        }
    }

    private static void cadastrarCliente(IClienteDAO clienteDAO) {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
        String cpfStr = JOptionPane.showInputDialog("Digite o CPF do cliente:");
        String telefoneStr = JOptionPane.showInputDialog("Digite o número de telefone do cliente:");
        String endereco = JOptionPane.showInputDialog("Digite o endereço do cliente:");
        String numeroStr = JOptionPane.showInputDialog("Digite o número do endereço:");
        String cidade = JOptionPane.showInputDialog("Digite a cidade do cliente:");
        String estado = JOptionPane.showInputDialog("Digite o estado do cliente:");

        try {
            Long cpf = Long.parseLong(cpfStr);
            Long telefone = Long.parseLong(telefoneStr);
            int numero = Integer.parseInt(numeroStr);
            Cliente cliente = new Cliente(nome, cpf, telefone, endereco, numero, cidade, estado);
            if (clienteDAO.cadastrar(cliente)) {
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente já cadastrado com o CPF fornecido.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira números válidos para CPF, telefone e número do endereço.");
        }
    }

    private static void consultarCliente(IClienteDAO clienteDAO) {
        String cpfStr = JOptionPane.showInputDialog("Digite o CPF do cliente:");
        try {
            Long cpf = Long.parseLong(cpfStr);
            Cliente cliente = clienteDAO.consultar(cpf);
            if (cliente != null) {
                JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.getNome());
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado para o CPF: " + cpf);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um número de CPF válido.");
        }
    }

    private static void excluirCliente(IClienteDAO clienteDAO) {
        String cpfStr = JOptionPane.showInputDialog("Digite o CPF do cliente a ser excluído:");
        try {
            Long cpf = Long.parseLong(cpfStr);
            clienteDAO.excluir(cpf);
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, insira um número de CPF válido.");
        }
    }

    private static void listarClientes(IClienteDAO clienteDAO) {
        StringBuilder clientesStr = new StringBuilder("Lista de clientes:\n");
        for (Cliente cliente : clienteDAO.buscarTodos()) {
            clientesStr.append(cliente.getNome()).append(" - CPF: ").append(cliente.getCpf()).append(" - Telefone: ").append(cliente.getTel()).append("\n");
        }
        JOptionPane.showMessageDialog(null, clientesStr.toString());
    }
}
