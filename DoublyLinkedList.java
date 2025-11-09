class DoublyNode<T> {
    T data;
    DoublyNode<T> prev;
    DoublyNode<T> next;

    public DoublyNode(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList<T> {
    private DoublyNode<T> head;
    private DoublyNode<T> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // методы из односвязного списка
    public void addFirst(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        DoublyNode<T> newNode = new DoublyNode<>(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
    public T removeFirst() {
        if (head == null) {
            return null;
        }
        T removedData = head.data;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return removedData;
    }

    public T removeLast() {
        if (tail == null) {
            return null;
        }
        T removedData = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return removedData;
    }

    public boolean removeByValue(T data) {
        if (head == null) {
            return false;
        }
        DoublyNode<T> current = head;
        while (current != null && !current.data.equals(data)) {
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        if (current == head) {
            removeFirst();
        } else if (current == tail) {
            removeLast();
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
        }
        return true;
    }
    public boolean contains(T data) {
        DoublyNode<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void display() {
        if (head == null) {
            System.out.println("empty list");
            return;
        }
        DoublyNode<T> current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.data).append(" <-> ");
            current = current.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }
    // методы для двусвязногл списка
    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            DoublyNode<T> newNode = new DoublyNode<>(data);
            DoublyNode<T> current = getNode(index);
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            DoublyNode<T> current = getNode(index);
            T removedData = current.data;
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
            return removedData;
        }
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public void displayReverse() {
        if (tail == null) {
            System.out.println("empty list");
            return;
        }
        DoublyNode<T> current = tail;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.data).append(" <-> ");
            current = current.prev;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    public T getFirst() {
        return head != null ? head.data : null;
    }

    public T getLast() {
        return tail != null ? tail.data : null;
    }

    private DoublyNode<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        DoublyNode<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }
}
class PrintDoubly {
    public static void main(String[] args) {
        System.out.println("2 часть - двусвязный список");
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();

        System.out.println("  методы из SinglyLinkedList:");
        System.out.println("  добавляем в начало 1 и в конец 3 и 5:");
        dll.addFirst(1);
        dll.addLast(3);
        dll.addLast(5);
        dll.display();

        System.out.println("  размер?: " + dll.size());
        System.out.println("  пусто??: " + dll.isEmpty());
        System.out.println("  содержит 3?: " + dll.contains(3));

        System.out.println("  делаем removeFirst():");
        dll.removeFirst();
        dll.display();

        System.out.println("  делаем removeLast():");
        dll.removeLast();
        dll.display();

        System.out.println("  делаем удаление по значению (3):");
        dll.removeByValue(3);
        dll.display();


        System.out.println();
        System.out.println("  методы DoublyLinkedList:");
        System.out.println("  добавили элементы 2,4,6:");
        dll.addLast(2);
        dll.addLast(4);
        dll.addLast(6);
        dll.display();

        System.out.println("   добавили элементы 1 и 3:");
        dll.add(1, 3);
        dll.display();

        System.out.println("  получаем(гетаем) элемент по индексу : " + dll.get(2));
        System.out.println("  getFirst(): " + dll.getFirst());
        System.out.println("  getLast(): " + dll.getLast());

        System.out.println("  элементы в обратном порядке:");
        dll.displayReverse();


        System.out.println("  удаляем 1 элемент по индексу:");
        dll.remove(1);
        dll.display();

        System.out.println();
        System.out.println("  размер?? " + dll.size());
        System.out.println("  пусто??: " + dll.isEmpty());

        System.out.println("  делаем очистку:");
        dll.clear();
        dll.display();
    }
}