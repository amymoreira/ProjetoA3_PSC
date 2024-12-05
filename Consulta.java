import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Consulta {
    private String paciente;
    private String tipoConsulta;
    private String data;

    public Consulta(String paciente, String tipoConsulta, String data) {
        this.paciente = paciente;
        this.tipoConsulta = tipoConsulta;
        this.data = data;
    }

    public String getPaciente() {
        return paciente;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public String getData() {
        return data;
    }
}

class CrudBD {
    private ArrayList<Consulta> consultas = new ArrayList<>();

    public void incluirReg(Consulta consulta) {
        consultas.add(consulta);
    }

    public ArrayList<Consulta> listarConsultas() {
        return consultas;
    }

    public Consulta consultarReg(String paciente) {
        for (Consulta c : consultas) {
            if (c.getPaciente().equalsIgnoreCase(paciente)) {
                return c;
            }
        }
        return null;
    }
}

class OdontologiaApp {
    private CrudBD cBD = new CrudBD();
    private JFrame frame;

    public OdontologiaApp() {
      // Configuração da Janela Principal
      frame = new JFrame("Sistema de Consultas Odontológicas");
      frame.setSize(1200, 800);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLayout(null);
      frame.getContentPane().setBackground(new Color(34, 239, 206)); // Turquesa

      // Tamanho dos botões
      int buttonWidth = 400;
      int buttonHeight = 50;

      // Coordenadas para centralizar os botões
      int centerX = (frame.getWidth() - buttonWidth) / 2;
      int startY = (frame.getHeight() - (4 * buttonHeight + 30)) / 2; // Inclui espaço entre botões

      // Botões principais
      JButton btnRegistrar = createButton("Registrar Consulta", centerX, startY, buttonWidth, buttonHeight);
      JButton btnConsultar = createButton("Consultar Consulta", centerX, startY + 60, buttonWidth, buttonHeight);
      JButton btnListar = createButton("Listar Consultas", centerX, startY + 120, buttonWidth, buttonHeight);
      JButton btnSair = createButton("Sair", centerX, startY + 180, buttonWidth, buttonHeight);
   
      // Ações dos botões
      btnRegistrar.addActionListener(e -> abrirTelaRegistrar());
      btnConsultar.addActionListener(e -> abrirTelaConsultar());
      btnListar.addActionListener(e -> abrirTelaListar());
      btnSair.addActionListener(e -> System.exit(0));

      // Adicionar botões à janela
      frame.add(btnRegistrar);
      frame.add(btnConsultar);
      frame.add(btnListar);
      frame.add(btnSair);

      frame.setVisible(true);
}  

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        return button;
    }

    private void abrirTelaRegistrar() {
        JFrame registrarFrame = new JFrame("Registrar Consulta");
        registrarFrame.setSize(600, 400);
        registrarFrame.setLayout(null);

        JLabel lblPaciente = new JLabel("Paciente:");
        lblPaciente.setBounds(20, 20, 100, 30);
        JTextField txtPaciente = new JTextField();
        txtPaciente.setBounds(120, 20, 400, 30);

        JLabel lblTipoConsulta = new JLabel("Tipo:");
        lblTipoConsulta.setBounds(20, 70, 100, 30);
        JComboBox<String> cbTipoConsulta = new JComboBox<>(new String[]{"Exame", "Manutenção"});
        cbTipoConsulta.setBounds(120, 70, 400, 30);

        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(20, 120, 100, 30);
        JTextField txtData = new JTextField();
        txtData.setBounds(120, 120, 400, 30);

        JLabel lblMensagem = new JLabel("");
        lblMensagem.setBounds(120, 200, 400, 30);
        lblMensagem.setForeground(Color.RED);

        JButton btnSalvar = createButton("Salvar", 250, 250, 100, 30);
        btnSalvar.addActionListener(e -> {
            String paciente = txtPaciente.getText();
            String tipo = (String) cbTipoConsulta.getSelectedItem();
            String data = txtData.getText();

            if (paciente.isEmpty() || data.isEmpty()) {
                lblMensagem.setText("Preencha todos os campos!");
                return;
            }

            cBD.incluirReg(new Consulta(paciente, tipo, data));
            lblMensagem.setForeground(new Color(0, 128, 0));
            lblMensagem.setText("Consulta registrada com sucesso!");
            txtPaciente.setText("");
            txtData.setText("");
        });

        registrarFrame.add(lblPaciente);
        registrarFrame.add(txtPaciente);
        registrarFrame.add(lblTipoConsulta);
        registrarFrame.add(cbTipoConsulta);
        registrarFrame.add(lblData);
        registrarFrame.add(txtData);
        registrarFrame.add(btnSalvar);
        registrarFrame.add(lblMensagem);

        registrarFrame.setVisible(true);
    }

    private void abrirTelaConsultar() {
        JFrame consultarFrame = new JFrame("Consultar Consulta");
        consultarFrame.setSize(600, 400);
        consultarFrame.setLayout(null);

        JLabel lblPaciente = new JLabel("Paciente:");
        lblPaciente.setBounds(20, 20, 100, 30);
        JTextField txtPaciente = new JTextField();
        txtPaciente.setBounds(120, 20, 400, 30);

        JLabel lblResultado = new JLabel("");
        lblResultado.setBounds(20, 120, 500, 100);
        lblResultado.setVerticalAlignment(SwingConstants.TOP);

        JButton btnBuscar = createButton("Buscar", 250, 80, 100, 30);
        btnBuscar.addActionListener(e -> {
            String paciente = txtPaciente.getText();
            Consulta consulta = cBD.consultarReg(paciente);

            if (consulta != null) {
                lblResultado.setText("<html>Consulta encontrada:<br>Paciente: " + consulta.getPaciente() + 
                                     "<br>Tipo: " + consulta.getTipoConsulta() + 
                                     "<br>Data: " + consulta.getData() + "</html>");
            } else {
                lblResultado.setText("Consulta não encontrada.");
            }
        });

        consultarFrame.add(lblPaciente);
        consultarFrame.add(txtPaciente);
        consultarFrame.add(btnBuscar);
        consultarFrame.add(lblResultado);

        consultarFrame.setVisible(true);
    }

    private void abrirTelaListar() {
        JFrame listarFrame = new JFrame("Listar Consultas");
        listarFrame.setSize(600, 600);
        listarFrame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        ArrayList<Consulta> consultas = cBD.listarConsultas();
        if (consultas.isEmpty()) {
            textArea.setText("Não há consultas registradas.");
        } else {
            for (Consulta c : consultas) {
                textArea.append("Paciente: " + c.getPaciente() + " - Tipo: " + c.getTipoConsulta() + " - Data: " + c.getData() + "\n");
            }
        }

        listarFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        listarFrame.setVisible(true);
    }

class LogoFrame extends JFrame 
{
    public LogoFrame() {
        // Configurações da janela
        setTitle("Exemplo com Logotipo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(null); // Layout absoluto para posicionamento manual

        // Caminho da imagem
        String imagePath = "C:\\Users\\Amy\\Downloads\\ProjetoA3\\dentalicon.webp";

        // Carrega e adiciona o logotipo
        addLogo(imagePath);

        // Torna a janela visível
        setVisible(true);
    }

    private void addLogo(String imagePath) {
        ImageIcon logoIcon = new ImageIcon(imagePath);
        if (logoIcon.getIconWidth() > 0) {
            JLabel lblLogo = new JLabel(logoIcon);

            // Centraliza a imagem no topo
            int x = (getWidth() - logoIcon.getIconWidth()) / 2;
            lblLogo.setBounds(x, 10, logoIcon.getIconWidth(), logoIcon.getIconHeight());

            add(lblLogo);
            repaint(); // Atualiza a interface
        } else {
            System.err.println("Erro ao carregar a imagem: " + imagePath);
        }
    }
}

    public static void main(String[] args) {
        new OdontologiaApp();
    }
}
