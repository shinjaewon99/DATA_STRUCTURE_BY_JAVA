package list_exam;

public class LinkedListPrac {

    public static void main(String[] args) {
        LinkedList store = new LinkedList();

        store.addFirst(30); // data = 30, next = null
        store.addFirst(20);
        store.addFirst(10);

        store.addLast(60);
        store.addLast(50);
        store.addLast(40);

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
            return String.valueOf(this.data);
        }
    }
}
