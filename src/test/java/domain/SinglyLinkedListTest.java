package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    @Test
    void test1(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.add(new Student("1", "Maria", 20, "Cartago"));
        list.add(new Student("2", "Carlos", 22, "San José"));
        list.add(new Student("3", "Laura", 20, "Paraíso"));
        list.add(new Student("4", "Paula", 18, "Turrialba"));
        list.add(new Student("5", "Carlos", 21, "Limón"));
        list.add(new Student("6", "Fabiana", 19, "Paraíso"));
        list.add(new Student("7", "María", 23, "Gunacaste"));
        list.add(new Student("8", "Carlos", 25, "San Carlos"));
        list.add(new Student("9", "Laura", 20, "Turrialba"));
        list.add(new Student("10", "Pedro", 24, "Heredia"));
        //Prueba de los metodos addFirst y addLast
        list.addLast(new Student("118740974","Alejandro",22,"Curridabat"));
        list.addFirst(new Student("302400353","Donelia",65,"Palmares"));

        System.out.println("Lista de estudiantes:");
        System.out.println(list); //muestra la lista de estudiantes

        // i. Test contains
        System.out.println("\ni.Test contains\n");
        try {
            System.out.println("¿Existe Pedro, Id=20?: "+list.contains(new Student("20","Pedro",0,"")));
            System.out.println("¿Existe Paula, Id=4?: "+list.contains(new Student("4","Paula",0,"")));
            System.out.println("¿Existe Carlos, Id=5?: "+list.contains(new Student("5","Carlos",0,"")));
            System.out.println("¿Existe Carlos, Id=8?: "+list.contains(new Student("8","Carlos",0,"")));

        } catch (ListException e) {
            throw new RuntimeException(e);
        }

        //ii. Test getNode
        System.out.println("\nii.Test getNode\n");
        try {
            for (int i = 1; i <= list.size(); i++) {
                Object student = list.getNode(i).data;
                System.out.println("El elemento en la posición " + i + " es: " + student);
            }
        } catch (ListException e) {
            e.printStackTrace();
        }

        //iii. Test indexOF
        System.out.println("\niii.Test indexOF\n");
        try {
            System.out.println("El estudiante Carlos con Id=8 se encuentra en la posición: " + list.indexOf(new Student("8", "Carlos", 0, "")));
            System.out.println("El estudiante Carlos con Id=100 se encuentra en la posición: " + list.indexOf(new Student("100", "Carlos", 0, "")));

        } catch (ListException e) {
            e.printStackTrace();
        }

        //iv. Test sortByName
        System.out.println("\niv.Test sortByName\n");
        try {
            System.out.println("List Sorted by name \n");
            list.sortByName();
            System.out.println(list);

        } catch (ListException e) {
            throw new RuntimeException(e);
        }

        //v. Test Remove
        System.out.println("\nv.Test Remove\n");
        try {
            list.remove(new Student("1"));
            list.remove(new Student("3"));
            list.remove(new Student("5"));

            System.out.println("Lista despues de eliminar a los estudiantes con los ID: 1, 3, 5: \n" +list);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }

        //vi. Test countNames
        System.out.println("\nv.Test countNames\n");
        try {
            System.out.println("Buscamos cuantos Carlos tenemos en la lista: "
                    +list.countNames(list, "Carlos"));
        } catch (ListException e) {
            throw new RuntimeException(e);
        }

        //vii. Test findNames
        System.out.println("\nv.Test findNames\n");
        try {
            System.out.println("¿En la lista existe una estudiante con el nombre Karla? "
                    + list.findNames(list, "Karla"));
            System.out.println("¿En la lista existe una estudiante con el nombre Fabiana? "
                    + list.findNames(list, "Fabiana"));
        } catch (ListException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void test2() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(20);
        list.addFirst(10);
        list.addFirst(30);
        list.addFirst(50);
        list.addFirst(40);
        list.add(70);
        list.add(5);
        System.out.println(list);
        try {
            System.out.println("List size: "+list.size());
            System.out.println("Removed first item: "+list.removeFirst());
            System.out.println("List size: "+list.size());
            System.out.println(list);
            System.out.println("Removed first item: "+list.removeFirst());
            System.out.println("List size: "+list.size());
            /*for (int i = 0; i < 6 ; i++) {
                list.removeFirst();
            }*/
            for (int i = 0; i < 50; i++) {
                list.add(util.Utility.random(50));
            }
            System.out.println(list);

            for (int i = 0; i <10 ; i++) {
                int value = util.Utility.random(50);
                System.out.println(
                        list.contains(value)
                                ? "The element ["+value+"] exists in the list. " +
                                "Index: "+list.indexOf(value)
                                :"The element ["+value+" does not exist in the list"
                );

                //probamos remove
                if(list.contains(value)){
                    list.remove(value);
                    System.out.println("The element ["+value+"] has been deleted");
                }
            }

            System.out.println(list);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }
}