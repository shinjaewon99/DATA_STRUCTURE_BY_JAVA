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

        System.out.println(store.get(1));
        System.out.println(store.toString());
        System.out.println(store.indexOf(20));

        // ArrayList.ListIterator = ArrayList 클래스의 내부 클래스읜 ListIterator를 가르킴
        ArrayList.ListIterator iteratorOperation = store.listIterator();

        while (iteratorOperation.hasNext()) {
            System.out.println(iteratorOperation.next());
        }

        while (iteratorOperation.hasPrevious()) {
            System.out.println(iteratorOperation.previous());
        }
    }
}

class ArrayList {
    // List 길이를 위한 길이 초기화
    private int size = 0;

    // 최상위 컬렉션인 Object를 초기화
    private final Object[] store = new Object[100];

    // List 자료구조의 add는 파라미터로 들어온 index에 value값을 할당하고, 기존에 있던 값을 뒤로 미뤄야 한다.
    // EX : [1,2,3,4] 일 경우 → add(1, 10), 1번 인덱스에 10을 할당하여 [1,10,2,3,4] 가 된다.
    public boolean add(final int index, final Object value) {
        // size 위치부터 index까지 거꾸로 돌면서 요소를 하나씩 뒤로 밀어낸다.
        for (int i = size - 1; i >= index; i--) {
            store[i + 1] = store[i];
        }
        store[index] = value;
        size++;
        return true;
    }

    public boolean addFirst(final Object value) {
        return add(0, value);
    }

    public boolean addLast(final Object value) {
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

            if (i < size - 1) {
                prefix += ",";
            }
        }
        return prefix + "]";
    }

    // Collection 에서 remove는 삭제한 값을 추적하기 위해 Object를 리턴한다.
    public Object remove(final int index) {
        Object removed = store[index];
        // 삭제할 인덱스는 뒤에 있는 인덱스를 끌어와서 중첩시킨다.
        for (int i = index + 1; i <= size + 1; i++) {
            store[i - 1] = store[i];
        }
        size--;
        store[size] = null;

        return removed;
    }

    public Object removeFirst() {
        return remove(0);
    }

    public Object removeLast() {
        // 인덱스는 0부터 시작하므로 -1 을 해줘야 마지막 인덱스 이다.
        return remove(size - 1);
    }

    /**
     * ArrayList는 입력, 삭제시 LinkedList 보다 성능이 떨어지지만
     * 조회 (get) 을 할 경우 성능이 LinkedList 보다 좋음 (index로 접근)
     */
    public Object get(final int index) {
        return store[index];
    }

    // 지역변수를 private으로 막고 메소드로 접근하는 이유는 외부에서 변경하는걸 막기 위해
    public int size() {
        return size;
    }

    // 해당 value의 index 값을 반환

    public int indexOf(final Object value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(store[i])) {
                return i;
            }
        }
        return -1;
    }

    public ListIterator listIterator() {
        return new ListIterator();
    }

    class ListIterator {
        public int nextIndex = 0;

        public boolean hasNext() {
            return nextIndex < size();
        }

        // store 객체에 인덱스 값을 반환
        public Object next() {
            return store[nextIndex++];
        }

        // 이전 nextIndex에 대한 store객체를 반환
        // 후위 표기식이 아닌 전위 표기식으로 하는 이유는 nextIndex에 대해 즉각적인 감소가 필요하기 때문
        public Object previous() {
            return store[--nextIndex];
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }
    }
}
