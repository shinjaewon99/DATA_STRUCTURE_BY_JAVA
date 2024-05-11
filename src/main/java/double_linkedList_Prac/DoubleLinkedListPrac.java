package double_linkedList_Prac;


// LinkedList와 달리 양방향의 관계를 가지고 있는 List
// Java의 Collection 프레임워크의 LinkedList 는 DoubleLinkedList 기반
public class DoubleLinkedListPrac {

    public static void main(String[] args) {
        DoubleLinkedList store = new DoubleLinkedList();

        store.addFirst(30); // data = 30, next = null

        store.addLast(60);
        store.add(1, 15);

        System.out.println(store.removeFirst());
        store.remove(0);

        System.out.println(store);

        DoubleLinkedList.ListIterator iterator = store.listIterator();

        System.out.println(iterator.next());

    }
}

class DoubleLinkedList {
    private Node head; // 첫번째 Node를 위한 head 변수
    private Node tail; // 마지막 Node를 위한 tail 변수
    private int size = 0;

    public void addFirst(final int value) {
        Node newNode = new Node(value);
        newNode.next = head; // 새로 만들어지는 노드 값으로 head 값을 지정

        if (head != null) {
            head.prev = newNode; // head에 이전 노드를 저장하기 위한 prev
        }
        head = newNode;
        size++;

        // 다음 노드가 존재 하지 않은 경우 (노드 혼자 존재)
        if (head.next == null) {
            tail = head;
        }
    }

    public void addLast(final int value) {
        Node newNode = new Node(value);

        if (size == 0) {
            addFirst(value);
        } else {
            tail.next = newNode;
            newNode.prev = tail; // tail이 마지막 노드 이므로, 새로운 노드 (newNode) 를 tail과 연결
            tail = newNode;
            size++;
        }
    }

    Node node(int index) {
        // 이중 탐색을 위한 if 문
        if (index < size / 2) {
            Node num = head;

            for (int i = 0; i < index; i++) {
                num = num.next;
            }
            return num;
        }
        // tail 부터 접근하는것이 속도에 이점
        else {
            Node num = tail;
            for (int i = size - 1; i > index; i--) {
                num = num.prev;
            }
            return num;
        }
    }

    public void add(final int index, final int value) {
        if (index == 0) {
            addFirst(value);
        } else {
            Node findNode1 = node(index - 1);
            Node findNode2 = findNode1.next;
            Node newNode = new Node(value);
            findNode1.next = newNode;
            newNode.next = findNode2;

            if (findNode2 != null) {
                findNode2.prev = newNode;
            }
            newNode.prev = findNode1;
            size++;

            if (newNode.next == null) {
                tail = newNode;
            }
        }
    }

    public Object removeFirst() {
        Node node = head;
        head = head.next;
        Object returnData = node.data;
        node = null;

        if (head != null) {
            head.prev = null;
        }
        size--;
        return returnData;
    }

    public Object remove(final int index) {
        if (index == 0) {
            return removeFirst();
        }

        Node node = node(index - 1);
        Node todoDeleted = node.next;
        node.next = node.next.next;

        if (node.next != null) {
            node.next.prev = node;
        }

        Object returnData = todoDeleted.data;

        if (returnData == tail) {
            tail = node;
        }

        todoDeleted = null;
        size--;

        return returnData;
    }

    public Object removeLast() {
        return remove(size - 1);
    }

    private class Node {

        private Object data;

        private Node next;

        public Node prev;

        public Node(final Object input) {
            this.data = input;
            this.next = null;
        }

        public String toString() {
            if (head == null) {
                return "[]";
            }
            Node node = head;
            String result = "[";

            while (node.next != null) {
                result += node.data + ", ";
                // head를 바꿔준다.
                node = next.next;
            }

            result += node.data;
            return result + "]";
        }

        public int size() {
            return size;
        }

        public Object get(final int index) {
            return node(index).data;
        }

        public int indexOf(final Object data) {
            Node node = head;
            int index = 0;
            while (node.data != data) {

                node = node.next;
                index++;

                if (node == null) {
                    return -1;
                }
            }
            return index;
        }
    }

    public ListIterator listIterator() {
        return new ListIterator();
    }

    class ListIterator {
        private Node next;
        private Node lastReturned;
        private int nextIndex;

        ListIterator() {
            next = head;
        }

        public Object next() {
            lastReturned = next;
            next = next.next;
            nextIndex++;

            return lastReturned.data;
        }

        // 이전노드가 남아있는지 검증하는 메소드
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public Object previous() {

            // next가 null 이라는 것은 노드가 끝에 도달 했다라는 것
            if (next == null) {
                lastReturned = next = tail;
            } else {
                lastReturned = next = next.prev;
            }
            nextIndex--;
            return lastReturned.data;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public void add(final Object value) {
            Node newNode = new Node(value);

            if (lastReturned == null) {
                head = newNode;
                newNode.next = next;
            } else {
                lastReturned.next = newNode;
                newNode.prev = lastReturned;
                if (next != null) {
                    // 양방향 관계를 위한 바인딩
                    newNode.next = next;
                    next.prev = newNode;
                }
                // 가장 끝에 있는 노드라면 tail이 newNode가 될 것 이다.
                else {
                    tail = newNode;
                }
                newNode.next = next;
            }

            lastReturned = newNode;
            nextIndex++;
            size++;
        }

        public void remove() {
            if (nextIndex == 0) {
                throw new IllegalArgumentException();
            }
            DoubleLinkedList.this.remove(nextIndex - 1);
            nextIndex--;
        }
    }
}
