package array_exam;

import java.util.Arrays;

public class ArrayPrac {
    public static void main(String[] args) {

        // 배열은 크기가 고정적임
        int[] store = new int[4];

        // 배열의 길이를 알 수 있는 length 키워드를 지원
        for (int i = 0; i < store.length; i++) {
            store[i] = i + 1;
        }

        System.out.println(Arrays.toString(store));

        // 문자열 배열에서는 해당 index(인덱스) 에 값을 할당 하지 않으면 null로 할당
        // → 정수열 배열에서는 0이 할당
        String[] bucket = new String[5];
        bucket[0] = "a";
        bucket[1] = "b";
        bucket[2] = "c";
        bucket[3] = "d";

        System.out.println(Arrays.toString(bucket));
    }
}
