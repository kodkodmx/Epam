package com.epam.rd.contactbook;

public class Main {

    public static void main(String[] args) {
        // Crear un nuevo contacto
        Contact contact = new Contact("Albert Einstein");

        // Agregar correos electrónicos
        contact.addEmail("albert", "einstein.com");
        contact.addEmail("albert", "relativity.org");

        // Agregar un correo electrónico especial de Epam
        contact.addEpamEmail("albert", "einsteins");

        // Agregar número de teléfono
        contact.addPhoneNumber(1, "123-456-7890");

        // Agregar enlaces sociales
        contact.addTwitter("@einstein_physics");
        contact.addInstagram("@albert_photons");

        // Obtener información del contacto
        ContactInfo[] info = contact.getInfo();

        // Mostrar la información del contacto
        for (ContactInfo entry : info) {
            System.out.println(entry.getTitle() + ": " + entry.getValue());
        }
    }
}
