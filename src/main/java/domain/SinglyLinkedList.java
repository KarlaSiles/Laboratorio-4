package domain;

import util.Utility;

public class SinglyLinkedList implements List{
    private Node first; //apuntador al inicio de la lista

    public SinglyLinkedList() {
        this.first = null; //la lista no existe
    }

    @Override
    public int size() throws ListException {
        int count = 0;

        for(Node current = this.first; current != null; current = current.next) {
            ++count;
        }

        return count;
    }

    @Override
    public void clear() {
        this.first = null; //anulamos la lista
    }

    @Override
    public boolean isEmpty() {
        return this.first == null; //si es nulo está vacía
    }

    @Override
    public boolean contains(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("Singly Linked List is empty");
        }
        Node aux = first;
        while(aux!=null){
            if(util.Utility.compare(aux.data, element)==0){
                return true;
            }
            aux = aux.next; //lo movemos al sgte nodo
        }
        return false; //indica q el elemento no existe
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if(isEmpty()){
            first = newNode;
        }else{
            Node aux = first;
            //mientras no llegue al ult nodo
            while(aux.next!=null){
                aux=aux.next;
            }
            //una vez que se sale del while, quiere decir q
            //aux esta en el ult nodo, por lo q lo podemos enlazar
            //con el nuevo nodo
            aux.next = newNode;
        }

    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if(isEmpty()){
            first = newNode;
        }else{
            newNode.next = first;
            first = newNode;
        }

    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override
    public void addInSortedList(Object element) {

    }

    @Override
    public void remove(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("Singly Linked List is Empty");
        }
        //Caso 1. El elemento a suprimir esta al inicio
        if(util.Utility.compare(first.data, element)==0){
            first = first.next; //saltamos el primer nodo
        }else{  //Caso 2. El elemento a suprimir puede estar al medio o final
            Node prev = first; //dejo un apuntador al nodo anterior
            Node aux = first.next;
            while(aux!=null && !(util.Utility.compare(aux.data, element)==0)){
                prev = aux;
                aux = aux.next;
            }
            //se sale cuando alcanza nulo o cuando encuentra el elemento
            if(aux!=null && util.Utility.compare(aux.data, element)==0){
                //ya lo encontro, procedo a desenlazar el nodo
                prev.next = aux.next;
            }
        }
    }

    @Override
    public Object removeFirst() throws ListException {
        return null;
    }

    @Override
    public Object removeLast() throws ListException {
        return null;
    }

    @Override
    public void sort() throws ListException {
        if(isEmpty()){
            throw new ListException("Singly Linked List is Empty");
        }
        for (int i = 1; i <= size() ; i++) {
            for (int j = i+1; j <= size() ; j++) {
                if(util.Utility.compare(getNode(j).data, getNode(i).data)<0){
                    Object aux = getNode(i).data;
                    getNode(i).data = getNode(j).data;
                    getNode(j).data = aux;
                }
            }
        }
    }

    public void sortName() throws ListException {
        if (isEmpty()) {
            throw new ListException("Singly linked list is empty");
        }

        for (int i = 1; i <= size(); i++) {
            for (int j = i + 1; j <= size(); j++) {
                Node nodeI = getNode(i); //Se declaran los nodos, lo que permite compararlos, ya que el Nodo en la posicion j es una posicion despues del nodo i
                Node nodeJ = getNode(j);

                // Verificar si los nodos son nulos antes de acceder a los elementos de cada nodo
                if (nodeI != null && nodeJ != null) {

                    // Obtener los nombres de los clientes en los nodos i y j

                    String nameI = ((Customer) nodeI.data).getName();
                    String nameJ = ((Customer) nodeJ.data).getName();

                    //Se comparan los nombres, ignorando si esta en mayuscula o minuscula la letra, y se cambian de posicion en caso de que el segundo nombre este antes que el primero
                    if (nameI.compareToIgnoreCase(nameJ) > 0) {
                        // Intercambiar los elementos si el nombre en el índice i es mayor que el nombre en el índice j
                        Object temp = nodeI.data;// Se crea una variable temporal en donde se almacenan los datos del primer Nodo
                        nodeI.data = nodeJ.data; //A los datos del primer nodo se les asignan los datos del segundo nodo
                        nodeJ.data = temp; //Los datos del primer nodo original se les asigna al segundo nodo, y con esto se hace el intercambio de elementos entre los nodos
                    }
                } else {
                    // Manejar el caso en el que se obtiene un nodo nulo
                    throw new ListException("Node at index " + i + " or " + j + " is null");
                }
            }
        }
    }

    public int indexOf(Object element) throws ListException {
        if (this.isEmpty()) {
            throw new ListException("Singly Linked List is Empty");
        } else {
            Node aux = this.first;

            for(int index = 1; aux != null; aux = aux.next) {
                if (Utility.compare(aux.data, element) == 0) {
                    return index;
                }

                ++index;
            }

            return -1;
        }
    }

    @Override
    public Object getFirst() throws ListException {
        if(isEmpty()){
            throw new ListException("Singly Linked List is Empty");
        }
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if(isEmpty()){
            throw new ListException("Singly Linked List is Empty");
        }
        Node aux = first;
        //mientras no llegue al ult nodo
        while(aux.next!=null){
            aux=aux.next;
        }
        //se sale del while cuando aux esta en el ult nodo
        return aux.data;
    }

    @Override
    public Object getPrev(Object element) throws ListException {
        if(isEmpty()){
            throw new ListException("Singly Linked List is Empty");
        }
        if(util.Utility.compare(first.data, element)==0){
            return "It's the first, it has no previous";
        }
        Node aux = first;
        //mientras no llegue al ult nodo
        while(aux.next!=null){
            if(util.Utility.compare(aux.next.data, element)==0){
                return aux.data; //retornamos la data del nodo actual
            }
            aux=aux.next;
        }
        return "Does not exist in Single Linked List";
    }

    @Override
    public Object getNext(Object element) throws ListException {
        return null;
    }

    @Override
    public Node getNode(int index) throws ListException {
        if(isEmpty()){
            throw new ListException("Singly Linked List is Empty");
        }
        Node aux = first;
        int i = 1; // pos del primer nodo
        while(aux!=null){
            if(util.Utility.compare(i, index)==0) {  //ya encontro el indice
                return aux;
            }
            i++; //incremento la var local
            aux = aux.next; //muevo aux al sgte nodo
        }
        return null; //si llega aqui es xq no encontro el index
    }

    @Override
    public String toString() {
        String result = "Singly Linked List Content\n\n";
        Node aux = first;
        while(aux!=null){
            result+= STR."\{aux.data}\n";
            aux = aux.next;
        }
        return result;
    }
}
