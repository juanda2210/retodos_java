package com.apex.util;

import com.apex.dao.ParticipanteDAO;
import com.apex.models.Participante;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ScannerUtils {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static String capturarTexto(String mensaje) {
        String dato;

        while (true) {
            System.out.println(mensaje + ": ");
            dato = SCANNER.nextLine();

            if (!dato.isBlank()) {
                return dato;
            }

            System.err.println("Error: el dato ingresado no puede estar vacio");
        }
    }

    public static int capturarNumero(String mensaje) {
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextInt()) {
            System.out.println("Dato no aceptado " + mensaje + ": ");
            SCANNER.next();
        }

        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

    public static double capturarDecimal(String mensaje) {
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextDouble()) {
            System.out.println("Dato no aceptado " + mensaje + ": ");
            SCANNER.next();
        }

        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }

    public static String capturarCorreo(String mensaje, List<Participante> participantes) {
        String correo;

        while (true) {
            System.out.println(mensaje + ": ");
            correo = SCANNER.nextLine();

            if (correo.isBlank()) {
                System.err.println("Error: el correo no puede estar vacío.");
                continue;
            }

            String correoVerificado = ParticipanteDAO.verificarCorreo(participantes, correo);

            if (correoVerificado == null) {
                return correo;
            }

            System.err.println("Error: el correo ya ha sido utilizado en otro participante. Ingrese uno nuevo.");
        }
    }

    public static int eliminarParticipante(String mensaje, List<Participante> participantes) {
        int id;

        while (true) {
            System.out.println(mensaje + ": ");

            try {
                id = SCANNER.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Error: el dato ingresado no es correcto. " + mensaje + ": ");
                SCANNER.nextLine();
                continue;
            }

            if (ParticipanteDAO.verificarId(participantes, id)) {
                return id;
            }

            System.err.println("Error: el id no existe. Ingrese uno nuevo.");
        }
    }

}
