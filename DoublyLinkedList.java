public class DoublyLinkedList implements LinkedList{
    private Node2P head,tail;
    int size=0;

    public DoublyLinkedList(){
        head = tail = null;
    }

    public boolean isEmpty(){
        return(size==0);
    }

    public int size(){
        return size;
    }

    public void addFirst(Object inputData){
        Node2P baru = new Node2P(inputData);
        if (isEmpty()){
            head = baru;
            tail = baru;
            size++;
        }
        else{
            baru.next = head;
            head.prev = baru;
            head = baru;
            size++;
        }
    }
    public void addLast(Object inputData){
        Node2P baru = new Node2P(inputData);
        if (isEmpty()){
            head = baru;
            tail = baru;
            size++;
        }
        else{
            tail.next = baru;
            baru.prev = tail;
            tail = baru;
            size++;
        }
    }

    public void addAfter(int index,Object inputData){
        Node2P baru = new Node2P(inputData);
        if (isEmpty()){
            head = baru;
            tail = baru;
            size++;
        }
        else{
            Node2P temp = head;
            for(int i=0;i<index;i++){
                temp = temp.next;
            }
            baru.prev = temp;
            baru.next = temp.next;
            temp.next = baru;
            baru.next.prev = baru;
            size++;
        }
    }
    
    public void deleteFirst(){
        Node2P temp = head;
        if (!isEmpty()){ 
            if (head == tail)
                head = tail = null;
            else
            {
                head.next.prev = null;
                head = temp.next;
            } size--;
        }
        else
            System.out.println("List kosong!");
    }

    public void deleteLast(){
        Node2P temp = tail;
        if (!isEmpty()){
            if (tail == head){
                head = tail = null;
            }
            else {
                tail.prev.next = null;
                tail=temp.prev;
            } size--;
        }
        else System.out.println("List kosong!");

    }
    public void deleteAfter(int index){
        Node2P temp = head;
        if(index < size-2){
            for(int i=0;i<index;i++){
                temp = temp.next;
            }
            temp.next.next.prev = temp;
            temp.next = temp.next.next;
            size--;
        }
        else if(index==size-2){//menghapus tail
            this.deleteLast();
        }
        else{
            System.out.println("Index lebih besar atau sama dari ukuran list");
        }

    }

    public void print(){
        Node2P currentNode = head;
        for(int i =0;i<size;i++){
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }
    }

    @Override
    public Object get(int index) {
        // TODO digunakan untuk mengembalikan data pada index ke-i dimulai dari head. Head memiliki index 0
        if(index < 0 || index >= size) {
            System.err.println("Index tidak valid");
        }

        Node2P currentNode = head;
        for(int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode.data;
    }
    @Override
    public int indexOf(Object targetData) {
        // TODO digunakan mencari kemunculan pertama targetData pada linked list dan mengembalikan indeksnya. Indeks dari head adalah 0. Jika tidak ada targetData pada linked list, kembalikan nilai -1
        Node2P currentNode = head;
        int index = 0;
        
        while(currentNode != null) {
            if(targetData == null) {
                if(currentNode.data == null) {
                    return index;
                }
            } else {
                if(targetData.equals(currentNode.data)) {
                    return index;
                }
            }
            currentNode = currentNode.next;
            index++;
        }

        return -1;
    }
    @Override
    public void printReverse() {
        // TODO digunakan untuk mencetak data pada linked list dengan urutan terbalik, dari tail ke head.
         Node2P currentNode = tail;
        while (currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.prev;
        }
    }
    @Override
    public boolean remove(Object targetData) {
        // TODO digunakan untuk mencari dan menghapus node dengan data=targetData pada linked list serta mengembalikan nilai True jika berhasil, dan False jika targetData tidak ada di linkedList
        if (isEmpty()) {
            System.err.println("Linked list kosong");
            return false;
        } 

        Node2P currentNode = head;

        while (currentNode != null) {
            if (currentNode.data.equals(targetData)) {
                if (currentNode == head) {
                    head = currentNode.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null;
                    }

                } 
                
                else if (currentNode == tail) {
                    tail = currentNode.prev;
                    if (tail != null) {
                        tail.next = null;
                    } else {
                        head = null;
                    }
                }
                else {
                    currentNode.prev.next = currentNode.next;
                    currentNode.next.prev = currentNode.prev;
                }

                size--;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }
    @Override
    public Object[] toArray() {
        // TODO digunakan untuk mendapatkan keseluruhan data pada node-node di linked list dalam bentuk array. Data-data pada array disusun secara urut mulai dari head sampai dengan tail.
         Object[] result = new Object[size];
        Node2P currentNode = head;
        for (int i = 0; i < size; i++) {
            result[i] = currentNode.data;
            currentNode = currentNode.next;
        }
        return result;
    }
}
