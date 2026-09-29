package com.apex.views;

import com.apex.dao.ParticipanteDAO;
import com.apex.models.Participante;
import com.apex.util.ScannerUtils;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static final int INSCRIBIR_PARTICIPANTE = 1;
    public static final int LISTAR_PARTICIPANTES = 2;
    public static final int BUSCAR_POR_EMPRESA = 3;
    public static final int CONTAR_PARTICIPANTES = 4;
    public static final int ELIMINAR_PARTICIPANTE = 5;
    public static final int SALIR = 6;

    public static void main(String[] args) {
        ParticipanteDAO dao = new ParticipanteDAO();
        while (true) {
            System.out.println("\n===== MENÚ DE PARTICIPANTES =====");
            System.out.println("1. Inscribir un participante");
            System.out.println("2. Listar todos los participantes");
            System.out.println("3. Buscar participantes por empresa");
            System.out.println("4. Contar participantes inscritos");
            System.out.println("5. Eliminar inscripción por ID");
            System.out.println("6. Salir");

            int opcion = ScannerUtils.capturarNumero("Seleccione una opción: ");

            switch (opcion) {

                case INSCRIBIR_PARTICIPANTE -> {
                    // Lógica para inscribir participante
                    try {
                        List<Participante> participantes = dao.listarTodos();

                        String nombre = ScannerUtils.capturarTexto("nombre");
                        String correo = ScannerUtils.capturarCorreo("correo", participantes);
                        String empresa = ScannerUtils.capturarTexto("empresa");

                        Participante participante = new Participante(nombre, correo, empresa);
                        dao.crear(participante);
                        System.out.println("Participante inscrito correctamente");
                    } catch (SQLException e) {
                        System.err.println("Error inscribiendo participante");
                    }
                }

                case LISTAR_PARTICIPANTES -> {
                    // Lógica para listar participantes
                    try {
                        System.out.println("-------------PARTICIPANTES--------------");
                        List<Participante> participantes = dao.listarTodos();
                        participantes.forEach(participante -> participante.mostrarInfo());
                    } catch (SQLException e) {
                        System.err.println("Error al listar los participantes");
                    }
                }

                case BUSCAR_POR_EMPRESA -> {
                    // Lógica para buscar por empresa

                    String empresa = ScannerUtils.capturarTexto("empresa");
                    try {
                        List<Participante> participantes =
                                dao.buscarPorEmpresa(empresa);

                        participantes.forEach(participante -> participante.mostrarInfo());

                    } catch (SQLException e) {
                        System.out.println("Error al buscar participantes de la empresa " + empresa);
                    }
                }

                case CONTAR_PARTICIPANTES -> {
                    // Lógica para contar participantes
                    try {
                        int total = dao.contarParticipantes();

                        System.out.println("Total de participantes: " + total);

                    } catch (SQLException e) {
                        System.err.println("Error al contar los participantes.");
                    }
                }

                case ELIMINAR_PARTICIPANTE -> {
                    // Lógica para eliminar por ID
                    System.out.println("\n------------------------");
                    System.out.println("--Eliminar participante--");
                    System.out.println("-------------------------");
                    try {
                        List<Participante> participantes = dao.listarTodos();

                        int id = ScannerUtils.eliminarParticipante("id", participantes);
                        dao.eliminar(id);
                        System.out.println("Participante eliminado satisfactoriamente");
                    } catch (SQLException e) {
                        System.err.println("Error al eliminar participante");
                    }

                }

                case SALIR -> System.exit(0);

                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
