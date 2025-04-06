package domain;

public class DoublyLinkedList implements List {
    private Node first; //apuntador al inicio de la lista

    //Constructor
    public DoublyLinkedList(){
        this.first = null;
    }

    @Override
    public int size() throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");
        int counter = 0; //contador de nodos
        Node aux = first; //aux para moverme por la lista y no perder el puntero al inicio
        while(aux!=null){
            counter++;
            aux = aux.next;
        }
        return counter;
    }

    @Override
    public void clear() {
        this.first = null; //anula la lista
    }

    @Override
    public boolean isEmpty() {
        return first ==null;
    }

    @Override
    public boolean contains(Object element) throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");
        Node aux = first;
        while(aux!=null){
            if(util.Utility.compare(aux.data, element)==0) return true; //ya lo encontro
            aux = aux.next; //muevo aux al nodo sgte
        }
        return false; //significa que no encontro el elemento
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if(isEmpty())
            first = newNode;
        else{
            Node aux = first; //aux para moverme por la lista y no perder el puntero al inicio
            while(aux.next!=null){
                aux = aux.next; //mueve aux al nodo sgte
            }
            //se sale del while cuando aux esta en el ult nodo
            aux.next = newNode;
            //hago el doble enlace
            newNode.prev = aux;
        }
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if(isEmpty())
            first = newNode;
        else{
            newNode.next = first;
            //hago el doble enlace
            first.prev = newNode;
            first = newNode;
        }
    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override
    public void addInSortedList(Object element){
        Node newNode = new Node(element);

        if (isEmpty()) {
            first = newNode;
        } else {
            Node current = first;
            Node prev = null;

            // Buscar donde insertarlo
            while (current != null && util.Utility.compare(current.data, element) < 0) {
                prev = current;
                current = current.next;
            }

            // Si sigue siendo nulo despues del recorrido se inserta al inicio
            if (prev == null) {
                newNode.next = first;
                first.prev = newNode;
                first = newNode;
            }
            // Insertar en la posicion que encontro si no va al inicio
            else {
                prev.next = newNode;
                newNode.prev = prev;
                newNode.next = current;
                if (current != null) {
                    current.prev = newNode;
                }
            }
        }
    }

    @Override
    public void remove(Object element) throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");
        //Caso 1: El elemento a suprimir es el primero de la lista
        if(util.Utility.compare(first.data, element)==0) {
            first = first.next;
            first.prev = null; //actualizo el enlace al nodo anteior
        }
        //Caso 2. El elemento puede estar en el medio o al final
        else{
            Node prev = first; //nodo anterior
            Node aux = first.next; //nodo sgte
            while(aux!=null && !(util.Utility.compare(aux.data, element)==0)){
                prev = aux;
                aux = aux.next;
            }
            //se sale del while cuanda alcanza nulo
            //o cuando encuentra el elemento
            if(aux!=null && util.Utility.compare(aux.data, element)==0){
                //debo desenlazar  el nodo
                prev.next = aux.next;
                //mantengo el doble enlace
                if(aux.next!=null)
                    aux.next.prev = prev;
            }
        }
    }

    @Override
    public Object removeFirst() throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");
        Object value = first.data;
        first = first.next; //movemos el apuntador al nodo sgte
        //rompo el doble enlace
        if(first!=null)
            first.prev = null;
        return value;
    }

    @Override
    public Object removeLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("Doubly Linked List is empty");
        }

        Node lastNode = first;

        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }

        lastNode.prev.next = null; //Cambiar el next del nodo antes del ultimo
        lastNode.prev = null;   //Limpiar el prev del nodo que se elimina

        return lastNode.data;
    }

    @Override
    public void sort() throws ListException {
        if (isEmpty()) {
            throw new ListException("Doubly Linked List is Empty");
        }

        try {
            Node current = first;
            Node index;
            Course temp;  // Cambiamos Product por Course

            while (current != null) {
                index = current.next;

                while (index != null) {
                    // Comparación por nombre del curso (podrías cambiar el criterio)
                    if (((Course) current.data).getName()
                            .compareTo(((Course) index.data).getName()) > 0) {

                        // Swap de datos entre nodos
                        temp = (Course) current.data;
                        current.data = index.data;
                        index.data = temp;
                    }
                    index = index.next;
                }
                current = current.next;
            }
        } catch (Exception e) {
            throw new ListException("Error while sorting: " + e.getMessage());
        }
    }

    //sortById para la interfaz register
    public void sortById() throws ListException {
        if (isEmpty()) {
            throw new ListException("Doubly Linked List is empty");
        }

        try {
            Node current = first;
            Node index;
            Register temp;

            while (current != null) {
                index = current.next;

                while (index != null) {
                    // Comparación numérica (int)
                    if (((Register) current.data).getId() > ((Register) index.data).getId()) {
                        // Intercambiar los datos
                        temp = (Register) current.data;
                        current.data = index.data;
                        index.data = temp;
                    }
                    index = index.next;
                }
                current = current.next;
            }
        } catch (Exception e) {
            throw new ListException("Error while sorting registers by ID: " + e.getMessage());
        }
    }

    //sortByName para la interfaz register
    public void sortByName() throws ListException {
        if (isEmpty()) {
            throw new ListException("Singly Linked List is empty");
        }

        try {
            Node current = first;
            Node index;
            Register temp;

            while (current != null) {
                index = current.next;

                while (index != null) {
                    // Comparar nombres (alfabéticamente)
                    if (((Register) current.data).getStudentName()
                            .compareToIgnoreCase(((Register) index.data).getStudentName()) > 0) {

                        // Intercambiar los datos
                        temp = (Register) current.data;
                        current.data = index.data;
                        index.data = temp;
                    }
                    index = index.next;
                }

                current = current.next;
            }

        } catch (Exception e) {
            throw new ListException("Error while sorting students by name: " + e.getMessage());
        }
    }

    @Override
    public int indexOf(Object element) throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");
        Node aux = first;
        int index = 1; //el primer indice de la lista es 1
        while(aux!=null){
            if(util.Utility.compare(aux.data, element)==0) return index;
            index++;
            aux = aux.next;
        }
        return -1; //significa q el elemento no existe en la lista
    }

    @Override
    public Object getFirst() throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if (isEmpty()) {
            throw new ListException("Doubly Linked List is empty");
        }

        Node aux = first;
        while (aux.next != null) {
            aux = aux.next;
        }
        return aux.data;
    }

    @Override
    public Object getPrev(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Doubly Linked List is empty");
        }

        Node aux = first;
        while (aux != null) {
            if (util.Utility.compare(aux.data, element) == 0) {

                return aux.prev.data;
            }
            aux = aux.next;
        }
        throw new ListException("Element not found in list");
    }

    @Override
    public Object getNext(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Doubly Linked List is empty");
        }

        Node aux = first;
        while (aux != null) {
            if (util.Utility.compare(aux.data, element) == 0) {
                if (aux.next == null) {
                    throw new ListException("No next element for the last node");
                }
                return aux.next.data;
            }
            aux = aux.next;
        }
        throw new ListException("Element not found in list");
    }

    @Override
    public Node getNode(int index) throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");
        Node aux = first;
        int i = 1; //posicion del primer nodo
        while(aux!=null){
            if(util.Utility.compare(i, index)==0){
                return aux;
            }
            i++;
            aux = aux.next; //lo movemos al sgte nodo
        }
        return null;
    }

    @Override
    public String toString() {
        if(isEmpty()) return "Doubly Linked List is empty";
        String result = "Doubly Linked List Content\n";
        Node aux = first; //aux para moverme por la lista y no perder el puntero al inicio
        while(aux!=null){
            result+=aux.data+" ";
            aux = aux.next; //lo muevo al sgte nodo
        }
        return result;
    }

    public Object getObject(int i){
        Node aux = first;
        Register r = (Register) aux.data;
        while(aux!=null){
            if (i == ((Register) aux.data).getId()) return aux.data;

            aux = aux.next; //lo muevo al sgte nodo
        }

        return null;
    }
}