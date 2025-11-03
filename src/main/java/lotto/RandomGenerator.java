package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class RandomGenerator {

    // TreeSet 에 저장 - 자동 정렬, 중복 없음
    // arrayList 에 집합을 count 개수 만큼 저장
    public static ArrayList<TreeSet<Integer>> generateNumbers(int count) {
        ArrayList<TreeSet<Integer>> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            TreeSet<Integer> numbers = new TreeSet<>(randomNumbers);
            list.add(numbers);
        }
        return list;
    }
}
