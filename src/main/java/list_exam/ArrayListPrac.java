package list_exam;

public class ArrayListPrac {
    public static void main(String[] args) {
        /**
         SOLID 규칙인 L (리스코프 치환법칙)에 의해 List<Integer> store = new ArrayList<>() 가 권장 되지만
         예제에서는 커스텀한 ArrayList 클래스로 진행 하겠음
         **/
        ArrayList store = new ArrayList();
        store.addLast(1);
        store.addLast(2);
        store.addLast(3);
        store.addLast(4);
        store.add(1, 10);
        store.addFirst(15);
        System.out.println(store.toString());
    }
}

class ArrayList {
    // List 길이를 위한 길이 초기화
    private int size = 0;

    // 최상위 컬렉션인 Object를 초기화
    private final Object[] store = new Object[100];

    // List 자료구조의 add는 파라미터로 들어온 index에 value값을 할당하고, 기존에 있던 값을 뒤로 미뤄야 한다.
    // EX : [1,2,3,4] 일 경우 → add(1, 10), 1번 인덱스에 10을 할당하여 [1,10,2,3,4] 가 된다.
    public boolean add(int index, Object value){
        // size 위치부터 index까지 거꾸로 돌면서 요소를 하나씩 뒤로 밀어낸다.
        for (int i = size - 1; i >= index; i--) {
            store[i + 1] = store[i];
        }
        store[index] = value;
        size++;
        return true;
    }

    public boolean addFirst(Object value) {
        return add(0, value);
    }

    public boolean addLast(Object value) {
        // 마지막 index인 size 변수
        store[size] = value;
        size++;

        return true;
    }

    // 출력을 위한 toString 구현
    public String toString() {
        String prefix = "[";

        for (int i = 0; i < size; i++) {
            prefix += store[i];

            if(i < size - 1){
                prefix += ",";
            }
        }
        return prefix + "]";
    }
}
