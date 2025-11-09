class Node<T> {
    T data;          // поле для хранения данных
    Node<T> next;    // ссылка на следующий узел
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList<T> {
    private Node<T> head;  // head = голова списка
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }
    // добавление элемента в начало списка
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }
    // добавление элемента в конец списка
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {  // ищем последний элемент
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    // удаление первого элемента
    public T removeFirst() {
        if (head == null) {
            return null;
        }
        T removedData = head.data;
        head = head.next;
        size--;
        return removedData;
    }
// удаление последнего элемента
     public T removeLast() {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            T removedData = head.data;
            head = null;
            size--;
            return removedData;
        }

        Node<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        T removedData = current.next.data;
        current.next = null;
        size--;
        return removedData;
    }

    //удаление элемента по значению
     public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            size--;
            return true;
        }
        return false;
    }
// проверка наличия элемента
    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // озврат размера списка
    public int size() {
        return size;
    }

    // проверка на пустоту
    public boolean isEmpty() {
        return head == null;
    }

    public void display() {
        if (head == null) {
            System.out.println("empty list");
            return;
        }

        Node<T> current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    // oчистка списка
    public void clear() {
        head = null;
        size = 0;
    }
}
class Print {
    public static void main(String[] args) {
        System.out.println("1 часть - односвязный список");
        SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();

        System.out.println("  пусто?: " + sll.isEmpty());

        System.out.println("  добавляем в нач 3,2,1:");
        sll.addFirst(3);
        sll.addFirst(2);
        sll.addFirst(1);
        sll.display();

        System.out.println("  добавляем в конец 4,5:");
        sll.addLast(4);
        sll.addLast(5);
        sll.display();

        System.out.println("  какой размер??:" + sll.size());

        System.out.println("  содержит 3??: " + sll.contains(3));
        System.out.println("  содержит 10??: " + sll.contains(10));

        Integer first = sll.removeFirst();
        System.out.println("  удален " + first);
        sll.display();

        Integer last = sll.removeLast();
        System.out.println("  удален " + last);
        sll.display();

        boolean removed = sll.remove(3);
        System.out.println("  удален 3?? : " + removed);
        sll.display();

        System.out.println("  делаем очистку:");
        sll.clear();
        sll.display();

        System.out.println("  пусто после очистки?? : " + sll.isEmpty());
    }
}
