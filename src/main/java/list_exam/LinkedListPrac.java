package list_exam;

public class LinkedListPrac {

    public static void main(String[] args) {
        LinkedList store = new LinkedList();


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


    private class Node {

        // 1. 데이터를 저장
        private Object data;
        // 2. 다음 노드를 저장
        private Node next;

        // 3. Node가 생성될때 값을 가지고 있어야함
        // 아직 다음 노드는 모름 null
        public Node(Object input) {
            this.data = input;
            this.next = null;
        }

        public String toString() {
            return String.valueOf(this.data);
        }
    }
}
