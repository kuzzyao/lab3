import java.util.*;

class CircularNode<T> {
    T data;
    CircularNode<T> next;

    public CircularNode(T data) {
        this.data = data;
        this.next = null;
    }
}
class CircularLinkedList<T> {
    private CircularNode<T> tail;
    private int size;

    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }
    // методы из односвязного списка
    public void addFirst(T data) {
        CircularNode<T> newNode = new CircularNode<>(data);
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
        size++;
    }
    public void addLast(T data) {
        addFirst(data);
        tail = tail.next;
    }
    public T removeFirst() {
        if (tail == null) {
            return null;
        }

        CircularNode<T> head = tail.next;
        T removedData = head.data;

        if (head == tail) {
            tail = null;
        } else {
            tail.next = head.next;
        }
        size--;
        return removedData;
    }
    public T removeLast() {
        if (tail == null) {
            return null;
        }

        if (tail.next == tail) {
            T removedData = tail.data;
            tail = null;
            size--;
            return removedData;
        }
        CircularNode<T> current = tail.next;
        while (current.next != tail) {
            current = current.next;
        }

        T removedData = tail.data;
        current.next = tail.next;
        tail = current;
        size--;
        return removedData;
    }

    public boolean remove(T data) {
        if (tail == null) {
            return false;
        }

        if (tail.next.data.equals(data)) {
            removeFirst();
            return true;
        }
        CircularNode<T> current = tail.next;
        CircularNode<T> prev = tail;

        do {
            if (current.data.equals(data)) {
                if (current == tail) {
                    tail = prev;
                }
                prev.next = current.next;
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        } while (current != tail.next);

        return false;
    }

    public boolean contains(T data) {
        return find(data) != null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return tail == null;
    }

    public void display() {
        if (tail == null) {
            System.out.println("empty list");
            return;
        }

        CircularNode<T> current = tail.next;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(current.data).append(" -> ");
            current = current.next;
        } while (current != tail.next);
        sb.append("(back to start)");
        System.out.println(sb.toString());
    }

    public void clear() {
        tail = null;
        size = 0;
    }

    // циклический сдвиг списка
    public void rotate() {
        if (tail != null) {
            tail = tail.next;
        }
    }

    // проверка наличия цикла
    public boolean findCycle() {
        return tail != null;
    }

    // поиск элемента с учетом циклической природы
    public CircularNode<T> find(T data) {
        if (tail == null) {
            return null;
        }

        CircularNode<T> current = tail.next;
        do {
            if (current.data.equals(data)) {
                return current;
            }
            current = current.next;
        } while (current != tail.next);

        return null;
    }

    // разделение списка на два равных циклических списка
    public List<CircularLinkedList<T>> splitIntoTwo() {
        if (size < 2) {
            throw new IllegalStateException("List must have at least 2 elements to split");
        }

        CircularLinkedList<T> firstList = new CircularLinkedList<>();
        CircularLinkedList<T> secondList = new CircularLinkedList<>();

        int splitIndex = size / 2;
        CircularNode<T> current = tail.next;

        // первая половина
        for (int i = 0; i < splitIndex; i++) {
            firstList.addLast(current.data);
            current = current.next;
        }

        // вторая половина
        for (int i = splitIndex; i < size; i++) {
            secondList.addLast(current.data);
            current = current.next;
        }

        return Arrays.asList(firstList, secondList);
    }
}

class PrintCircular {
    public static void main(String[] args) {
        System.out.println("3 часть - циклический односвязный список");
        CircularLinkedList<Integer> cll = new CircularLinkedList<>();

        System.out.println("  методы из SinglyLinkedList:");
        System.out.println("  пусто?? " + cll.isEmpty());

        System.out.println("  добавили 3,2,1 элемент в нач:");
        cll.addFirst(3);
        cll.addFirst(2);
        cll.addFirst(1);
        cll.display();

        System.out.println("  добавили 4 и 5 в конц:");
        cll.addLast(4);
        cll.addLast(5);
        cll.display();

        System.out.println("  размер??: " + cll.size());
        System.out.println("  содержит 3?: " + cll.contains(3));
        System.out.println("  содержит 10?: " + cll.contains(10));

        Integer first = cll.removeFirst();
        System.out.println("  удален:" + first);
        cll.display();

        Integer last = cll.removeLast();
        System.out.println("  удален:" + last);
        cll.display();

        boolean removed = cll.remove(3);
        System.out.println("  удалена 3??:" + removed);
        cll.display();

        System.out.println();
        System.out.println("  методы CircularLinkedList:");

        System.out.println("  добавляем элементы:");
        cll.addLast(6);
        cll.addLast(7);
        cll.addLast(8);
        cll.addLast(9);
        cll.display();

        System.out.println("  проверяем наличие цикла: " + cll.findCycle());

        CircularNode<Integer> found = cll.find(7);
        System.out.println("  ищем 7: " + (found != null ? "найден: " + found.data : "не найден"));


        System.out.println("  циклически сдвигаем список(1 эл-т становится last:");
        cll.rotate();
        cll.display();

        System.out.println();
        System.out.println("  разделяем список на два равных циклических списка:");
        List<CircularLinkedList<Integer>> lists = cll.splitIntoTwo();
        System.out.println("  первая половина:");
        lists.get(0).display();
        System.out.println("  вторая половина:");
        lists.get(1).display();

        System.out.println();
        System.out.println("  размер??: " + cll.size());
        System.out.println("  пусто????: " + cll.isEmpty());

        System.out.println("  очищаем:");
        cll.clear();
        cll.display();
        System.out.println("  есть ли цикл после очистки?????: " + cll.findCycle());
    }
}