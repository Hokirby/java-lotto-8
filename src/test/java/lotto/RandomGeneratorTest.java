package lotto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class RandomGeneratorTest {
    @Test
    void testGenerateNumbersCount() {
        int count = 5;
        ArrayList<TreeSet<Integer>> result = RandomGenerator.generateNumbers(count);

        // 리스트의 크기가 count와 같은지 확인
        assertEquals(count, result.size(), "리스트의 크기가 요청한 개수와 같아야 합니다.");
    }

    @Test
    void testEachSetHasSixUniqueNumbers() {
        ArrayList<TreeSet<Integer>> result = RandomGenerator.generateNumbers(10);

        for (TreeSet<Integer> set : result) {
            // 각 세트는 6개의 숫자를 가져야 함
            assertEquals(6, set.size(), "각 세트는 6개의 고유한 숫자를 가져야 합니다.");

            // 모든 숫자가 1~45 사이인지 확인
            for (int num : set) {
                assertTrue(num >= 1 && num <= 45, "숫자는 1~45 사이여야 합니다: " + num);
            }
        }
    }

    @Test
    void testRandomness() {
        ArrayList<TreeSet<Integer>> first = RandomGenerator.generateNumbers(5);
        ArrayList<TreeSet<Integer>> second = RandomGenerator.generateNumbers(5);

        // 두 결과가 완전히 동일할 확률은 매우 낮으므로, 같지 않아야 함
        assertNotEquals(first, second, "두 번 생성된 결과가 완전히 같을 가능성은 낮습니다.");
    }
}
