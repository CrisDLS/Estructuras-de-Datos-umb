import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

// SIMULADOR VISUAL: sala de urgencias con ventana, colores y animacion automatica.
public class SimuladorVisual extends JFrame {

    // Dato que guardamos dentro de la cola
    static class Paciente {
        String nombre;
        int prioridad;
        int ficha;
        Paciente(String nombre, int prioridad, int ficha) {
            this.nombre = nombre;
            this.prioridad = prioridad;
            this.ficha = ficha;
        }
    }

    private ColaPrioridad<Paciente> cola = new ColaPrioridadNodos<>();
    private int contador = 0;
    private Paciente enAtencion = null;
    private final Random azar = new Random();
    private final String[] NOMBRES = {"Ana", "Luis", "Marta", "Pedro", "Sofia", "Carlos", "Lucia", "Diego"};

    private final JTextField txtNombre = new JTextField(9);
    private final JComboBox<String> cmbPrioridad =
            new JComboBox<>(new String[]{"1 - Critico", "2 - Urgente", "3 - Leve"});
    private final JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Nodos", "Arreglo"});
    private final JLabel lblEstado = new JLabel("En espera: 0");
    private final JTextArea log = new JTextArea(6, 30);
    private final PanelCola panel = new PanelCola();
    private javax.swing.Timer timerLlegadas;
    private javax.swing.Timer timerAtencion;

    public SimuladorVisual() {
        setTitle("Simulador de cola de prioridad - Urgencias");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---- Parte de arriba: controles para registrar ----
        JPanel arriba = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRegistrar = new JButton("Registrar");
        arriba.add(new JLabel("Implementacion:"));
        arriba.add(cmbTipo);
        arriba.add(new JLabel("Nombre:"));
        arriba.add(txtNombre);
        arriba.add(new JLabel("Prioridad:"));
        arriba.add(cmbPrioridad);
        arriba.add(btnRegistrar);
        add(arriba, BorderLayout.NORTH);

        // ---- Centro: dibujo de la cola ----
        add(panel, BorderLayout.CENTER);

        // ---- Abajo: botones, estado y bitacora ----
        JPanel abajo = new JPanel(new BorderLayout());
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAtender = new JButton("Atender siguiente");
        JToggleButton btnAuto = new JToggleButton("Simulacion automatica");
        botones.add(btnAtender);
        botones.add(btnAuto);
        botones.add(lblEstado);
        abajo.add(botones, BorderLayout.NORTH);
        log.setEditable(false);
        abajo.add(new JScrollPane(log), BorderLayout.CENTER);
        add(abajo, BorderLayout.SOUTH);

        // ---- Eventos ----
        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) nombre = NOMBRES[azar.nextInt(NOMBRES.length)];
            registrar(nombre, cmbPrioridad.getSelectedIndex() + 1);
            txtNombre.setText("");
        });
        btnAtender.addActionListener(e -> atender());
        cmbTipo.addActionListener(e -> {
            if (cmbTipo.getSelectedIndex() == 0) cola = new ColaPrioridadNodos<>();
            else cola = new ColaPrioridadArreglo<>(100);
            enAtencion = null;
            contador = 0;
            log.append("Cambio a implementacion: " + cmbTipo.getSelectedItem() + "\n");
            refrescar();
        });

        // Timers: llegadas y atencion automaticas
        timerLlegadas = new javax.swing.Timer(1200, e ->
                registrar(NOMBRES[azar.nextInt(NOMBRES.length)], azar.nextInt(3) + 1));
        timerAtencion = new javax.swing.Timer(2500, e -> atender());
        btnAuto.addActionListener(e -> {
            if (btnAuto.isSelected()) {
                timerLlegadas.start();
                timerAtencion.start();
            } else {
                timerLlegadas.stop();
                timerAtencion.stop();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void registrar(String nombre, int prioridad) {
        if (cola.tamano() >= 30) {
            log.append("Sala llena, no se registra a " + nombre + "\n");
            return;
        }
        int ficha = ++contador; // prefijo: sube y luego asigna
        cola.insertar(new Paciente(nombre, prioridad, ficha), prioridad);
        log.append("Llego #" + ficha + " " + nombre + " (prioridad " + prioridad + ")\n");
        refrescar();
    }

    private void atender() {
        Paciente p = cola.extraer();
        if (p == null) {
            log.append("No hay pacientes en espera\n");
        } else {
            enAtencion = p;
            log.append("Atendiendo #" + p.ficha + " " + p.nombre + "\n");
        }
        refrescar();
    }

    private void refrescar() {
        lblEstado.setText("En espera: " + cola.tamano());
        log.setCaretPosition(log.getDocument().getLength());
        panel.repaint();
    }

    private static Color colorDe(int prioridad) {
        if (prioridad == 1) return new Color(220, 60, 60);   // rojo
        if (prioridad == 2) return new Color(240, 150, 30);  // naranja
        return new Color(70, 170, 90);                       // verde
    }

    // Panel que dibuja la cola
    class PanelCola extends JPanel {
        PanelCola() { setPreferredSize(new Dimension(760, 330)); }

        @Override
        protected void paintComponent(Graphics g0) {
            super.paintComponent(g0);
            Graphics2D g = (Graphics2D) g0;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Zona "En atencion"
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 14));
            g.drawString("En atencion:", 20, 25);
            if (enAtencion != null) {
                g.setColor(colorDe(enAtencion.prioridad));
                g.fillRoundRect(20, 35, 160, 70, 20, 20);
                g.setColor(Color.WHITE);
                g.drawString("#" + enAtencion.ficha + " " + enAtencion.nombre, 32, 65);
                g.drawString("Prioridad " + enAtencion.prioridad, 32, 90);
            } else {
                g.setColor(Color.GRAY);
                g.drawString("(libre)", 32, 65);
            }

            // Leyenda
            String[] textos = {"1 Critico", "2 Urgente", "3 Leve"};
            for (int i = 0; i < 3; i++) {
                g.setColor(colorDe(i + 1));
                g.fillRect(230 + i * 120, 40, 18, 18);
                g.setColor(Color.BLACK);
                g.drawString(textos[i], 254 + i * 120, 54);
            }

            // Cola de espera
            g.setColor(Color.BLACK);
            g.drawString("Cola de espera (el primero de la izquierda sale primero):", 20, 140);
            List<Paciente> lista = cola.listar();
            int columnas = Math.max(1, (getWidth() - 20) / 100);
            for (int i = 0; i < lista.size(); i++) {
                Paciente p = lista.get(i);
                int x = 20 + (i % columnas) * 100;
                int y = 155 + (i / columnas) * 75;
                g.setColor(colorDe(p.prioridad));
                g.fillRoundRect(x, y, 90, 62, 16, 16);
                if (i == 0) {
                    g.setColor(Color.BLACK);
                    g.setStroke(new BasicStroke(3));
                    g.drawRoundRect(x, y, 90, 62, 16, 16);
                    g.setStroke(new BasicStroke(1));
                }
                g.setColor(Color.WHITE);
                g.setFont(new Font("SansSerif", Font.BOLD, 12));
                g.drawString("#" + p.ficha + " " + p.nombre, x + 8, y + 25);
                g.drawString("Prio " + p.prioridad, x + 8, y + 47);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimuladorVisual().setVisible(true));
    }
}
