package list_exam;


// LinkedList 는 기준 노드에서 다음 노드의 정보는 알 수 있지만 기준 노드의 이전 노드의 정보를 알 수가 없다
// O - O - O : 두번째 노드는 세번째 노드의 정보를 알 수 있지만 두번째 노드는 첫번째 노드의 정보를 알 수 없다.
public class LinkedListPrac {

    public static void main(String[] args) {
        LinkedList store = new LinkedList();

        store.addFirst(30); // data = 30, next = null

        store.addLast(60);
        store.add(1, 15);

        System.out.println(store.removeFirst());
        store.remove(0);

        System.out.println(store);

        LinkedList.ListIterator iterator = store.listIterator();

        System.out.println(iterator.next());

    }
}

/**
 * LinkedList에서 데이터를 추가할때 앞쪽의 노드와 뒷쪽의 노드의 관계를 확인
 * LinkedList에서는 데이터 삽입 삭제가 ArrayList보다 빠름
 * 노드와 노드가 next로 연결 되어 있다.
 **/
class LinkedList {
    private Node head; // 첫번째 Node를 위한 head 변수
    private Node tail; // 마지막 Node를 위한 tail 변수
    private int size = 0;

    public void addFirst(final int value) {
        Node newNode = new Node(value);
        newNode.next = head; // 새로 만들어지는 노드 값으로 head 값을 지정
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
            tail = newNode;
            size++;
        }
    }

    Node node(int index) {
        Node num = head;

        for (int i = 0; i < index; i++) {
            num = num.next;
        }
        return num;
    }

    // index에 맞춰서 value값을 추가 하기 위해서는 이전 노드의 "링크"를 끊고 새로운 value와 "링크"를 맺어야함
    public void add(final int index, final int value) {
        if (index == 0) {
            addFirst(value);
        } else {
            // 이전 노드를 반환
            Node findNode1 = node(index - 1);
            Node findNode2 = findNode1.next;
            Node newNode = new Node(value);
            findNode1.next = newNode;
            newNode.next = findNode2;
            size++;

            // 제일 마지막 노드가 null 이라면, tail은 newNode이다.
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
        size--;
        return returnData;
    }

    public Object remove(final int index) {
        if (index == 0) {
            return removeFirst();
        }
        // index의 이전 노드
        Node node = node(index - 1);
        Node todoDeleted = node.next;
        node.next = node.next.next;
        Object returnData = todoDeleted.data;

        // 삭제할 데이터가 마지막 노드일 경우
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

        // 1. 데이터를 저장
        private Object data;
        // 2. 다음 노드를 저장
        private Node next;

        // 3. Node가 생성될때 값을 가지고 있어야함
        // 아직 다음 노드는 모름 null
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

            // head의 다음 링크가 null이 아닐 경우 data가 존재 하다는 것
            while (node.next != null) {
                result += node.data + ", ";
                // head를 바꿔준다.
                node = next.next;
            }

            // 마지막 노드에서는 null 이므로, data를 수동으로 넣어준다.
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
                // 다음 노드로 이동 시킨다.
                node = node.next;
                index++;
                // node가 null 이라면 찾는 값이 없다는 것
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

        // ListIterator 가 생성 될때 next를 초기화 한다.
        ListIterator() {
            next = head;
        }

        public Object next() {
            lastReturned = next;
            next = next.next;
            nextIndex++;

            return lastReturned.data;
        }

        // nextIndex가 size 보다 크게 되면 null 이므로 false 이다.
        public boolean hasNext() {
            return nextIndex < size;
        }
    }
}
