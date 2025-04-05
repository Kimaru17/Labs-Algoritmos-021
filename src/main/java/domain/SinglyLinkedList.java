package domain;

public class SinglyLinkedList implements List {
    private Node first; //apuntador al inicio de la lista

    //Constructor
    public SinglyLinkedList(){
        this.first = null;
    }

    @Override
    public int size() throws ListException {
        if(isEmpty())
            throw new ListException("Singly Linked List is empty");
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
            throw new ListException("Singly Linked List is empty");
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
        }
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if(isEmpty())
            first = newNode;
        else
            newNode.next = first;
        first = newNode;
    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override
    public void addInSortedList(Object element) throws ListException {
        sort();

        Node newNode = new Node(element);
        if (isEmpty())
            first = newNode;
        else {
            Node prev = first;
            Node aux = first.next;

            while (util.Utility.compare(aux.data, element) < 0) {
                prev = aux;
                aux = aux.next;
            }

            prev.next = newNode;
            newNode.next = aux;
        }
        sort();
    }

    @Override
    public void remove(Object element) throws ListException {
        if(isEmpty())
            throw new ListException("Singly Linked List is empty");
        //Caso 1: El elemento a suprimir es el primero de la lista
        if(util.Utility.compare(first.data, element)==0)
            first = first.next;
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
            }
        }
    }

    @Override
    public Object removeFirst() throws ListException {
        if(isEmpty())
            throw new ListException("Singly Linked List is empty");
        Object value = first.data;
        first = first.next; //movemos el apuntador al nodo sgte
        return value;
    }

    @Override
    public Object removeLast() throws ListException {
        if (isEmpty())
            throw new ListException("Singly Linked List is Empty");

        Node aux = first;

        while (aux.next.next != null) {
            aux = aux.next;
        }

        aux.next = null;
        return aux.data;
    }

    @Override
    public void sort() throws ListException {
        if (isEmpty())
            throw new ListException("Singly Linked List is Empty");

        for (int i = 1; i <= size() ; i++) {
            for (int j = i+1; j <= size(); j++) {
                if (util.Utility.compare(getNode(j).data, getNode(i).data) < 0){
                    Object aux = getNode(i).data;
                    getNode(i).data = getNode(j).data;
                    getNode(j).data = aux;
                }
            }
        }
    }

    public void sortByName() throws ListException {
        if (isEmpty())
            throw new ListException("Singly Linked List is Empty");

        for (int i = 1; i <= size() ; i++) {
            for (int j = i+1; j <= size(); j++) {
                String nI = ((Student) getNode(i).data).getName();
                String nJ = ((Student) getNode(j).data).getName();
                if (nJ.compareTo(nI) < 0){
                    Object aux = getNode(i).data;
                    getNode(i).data = getNode(j).data;
                    getNode(j).data = aux;
                }
            }
        }
    }

    @Override
    public int indexOf(Object element) throws ListException {
        if(isEmpty())
            throw new ListException("Singly Linked List is empty");
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
            throw new ListException("Singly Linked List is empty");
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if (isEmpty())
            throw new ListException("Singly Linked List is Empty");
        else{
            Node aux = first;
            while (aux.next != null)
                aux = aux.next; //se sale del while cuando aux está en eñ ultimo nodo

            return aux.data;
        }
    }

    @Override
    public Object getPrev(Object element) throws ListException {
        if (isEmpty())
            throw new ListException("Singly Linked List is Empty");

        if (util.Utility.compare(first.data, element) == 0)
            return "It's the first, it has no previous";

        Node aux = first;
        while (aux.next != null){
            if (util.Utility.compare(aux.next.data, element) == 0)
                return aux.data; //retorna la data del nodo actual
            aux = aux.next;
        }
        return "Does not exist in the List";
    }

    @Override
    public Object getNext(Object element) throws ListException {
        if (isEmpty())
            throw new ListException("Singly Linked List is Empty");

        Node aux = first;
        while (aux.next != null){
            if (util.Utility.compare(aux.data, element) == 0)
                return aux.next.data;
            aux = aux.next;
        }
        return "Does not exist in the List";
    }

    @Override
    public Node getNode(int index) throws ListException {
        if(isEmpty())
            throw new ListException("Singly Linked List is empty");
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
        if(isEmpty()) return "Singly Linked List is empty";
        String result = "Singly Linked List Content\n";
        Node aux = first; //aux para moverme por la lista y no perder el puntero al inicio
        while(aux!=null){
            result+=aux.data+"\n";
            aux = aux.next; //lo muevo al sgte nodo
        }
        return result;
    }

    public int countNames(SinglyLinkedList list, String name) throws ListException {
        int count = 0;
        Node aux = list.first;

        while (aux != null) {
            Student student = (Student) aux.data;
            if (student.getName().equals(name)) {
                count++;
            }
            aux = aux.next;
        }
        return count;
    }

    public boolean findNames(SinglyLinkedList list, String name) throws ListException {
        Node aux = list.first;

        while (aux != null) {
            Student student = (Student) aux.data;
            if (student.getName().equals(name)) {
                return true;
            }
            aux = aux.next;
        }
        return false;
    }
}


