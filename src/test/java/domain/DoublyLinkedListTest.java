package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    @Test
    void test() {
        DoublyLinkedList list = new DoublyLinkedList();
        list.add(new Course("IF-3001", "Algoritmos y Estructuras de Datos", 4));
        list.add(new Course("IF-4001", "Sistemas Operativos", 4));
        list.add(new Course("IF-2000", "Programación 1", 4));
        list.add(new Course("IF-3000", "Programación 2", 4));
        list.add(new Course("IF-4000", "Arquitectura", 3));
        list.add(new Course("IF-5000", "Redes", 4));
        list.add(new Course("IF-5100", "Bases de Datos", 4));
        list.add(new Course("IF-4101", "Lenguajes app Comerciales", 4));
        list.add(new Course("IF-3100", "Sistemas de Información", 3));

        try {
            list.sort();
            System.out.println("¿Existe Informática Aplicada, Id=IF-6201? " + list.contains(new Course("IF-6201", "Informática Aplicada", 0)));
            System.out.println("¿Existe Algoritmos y Estructuras de Datos, Id=IF-3001? " + list.contains(new Course("IF-3001", "Algoritmos y Estructuras de Datos", 4)));
            System.out.println("¿Existe Sistemas Operativos, Id=IF-4001? " + list.contains(new Course("IF-4001", "Sistemas Operativos", 4)));
            System.out.println("¿Existe Análisis y Diseño de Sistemas, Id=IF-6100? " + list.contains(new Course("IF-6100", "Análisis y Diseño de Sistemas", 0)));

            for (int i = 1; i <= list.size(); i++) {
                Node aux = list.getNode(i);
                Course c = (Course) aux.data;
                System.out.println("Posición " + i + ": " + c.getId() +
                        " - " + c.getName() +
                        " (" + c.getCredits() + " créditos)");
            }

            // Pruebas de indexOf()
            System.out.println("\n=== Posiciones de cursos ===");
            Course algoritmos = new Course("IF-3001", "Algoritmos y Estructuras de Datos", 4);
            Course languajes = new Course("IF-4101", "Lenguajes app Comerciales", 4);
            System.out.println("El curso Algoritmos y Estructuras de Datos Id=IF-3001 se encuentra en la posición: "+ list.indexOf(algoritmos));
            System.out.println("El curso Lenguajes app Comerciales Id=IF-4101 se encuentra en la posición: "+ list.indexOf(languajes));

            //eliminar los cursos
            System.out.println("\n===Eliminamos los cursos IF-5000 - IF-5100 ===");
            list.remove(new Course("IF-5000", "Redes", 4));

            list.remove(new Course("IF-5100", "Bases de Datos", 4));
            //volver a ordenar
            list.sort();
            //mostrar la lista de nuevo
            for (int i = 1; i <= list.size(); i++) {
                Node aux = list.getNode(i);
                Course c = (Course) aux.data;
                System.out.println("Posición " + i + ": " + c.getId() +
                        " - " + c.getName() +
                        " (" + c.getCredits() + " créditos)");
            }


        } catch (ListException e) {
            throw new RuntimeException(e);
        }


    }
}