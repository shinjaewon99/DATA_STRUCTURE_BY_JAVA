package compare;


import list_exam.ArrayListPrac;
import list_exam.LinkedListPrac;


public class ArrayList_LinkedList_Compare {
    public static void main(String[] args) {

        // ArrayList 에서의 add 기능은 add(0, 1) 을 하게 되면 기존에 add 되어있던 element들의 인덱스를 뒤로 미뤄줘야 한다.
        // 성능상 좋지 않음 (element가 100개면 100개를 뒤로 미뤄주고 해야함)
        ArrayListPrac arrayList = new ArrayListPrac();



        // LikedList 에서의 add는 노드의 연결된 연결점만 바꿔주면 된다.
        LinkedListPrac linkedList = new LinkedListPrac();


    }
}
