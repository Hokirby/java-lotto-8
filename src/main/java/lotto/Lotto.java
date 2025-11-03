package lotto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static lotto.ConstantMessages.LOTT0_NUMBER_SIZE_MUST_BE_SIX_ERROR;
import static lotto.ConstantMessages.LOTTO_NUMBER_DUPLICATED_ERROR;
import static lotto.InputParser.ERROR_PREFIX;

public class Lotto {
    private final List<Integer> numbers;
    private final int bonus;

    public Lotto(List<Integer> numbers, int bonus) {
        validate(numbers);
        distinct(numbers);
        // 불변
        this.numbers = Collections.unmodifiableList(numbers);
        this.bonus = bonus;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ERROR_PREFIX + LOTT0_NUMBER_SIZE_MUST_BE_SIX_ERROR.getMessage());
        }
    }

    private void distinct(List<Integer> numbers) {
        // 중복 제거
        if (numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException(ERROR_PREFIX + LOTTO_NUMBER_DUPLICATED_ERROR.getMessage());
        }
    }

    public HashMap<LottoRank, Integer> initPrizeCounts() {
        // 등수와 개수를 저장하는 map
        HashMap<LottoRank, Integer> prizeCounts = new HashMap<>();
        // map 에  lottoRank 등록
        Arrays.stream(LottoRank.values()).forEach(r -> prizeCounts.put(r, 0));
        return prizeCounts;
    }

    // 당첨 정보 저장
    public HashMap<LottoRank, Integer> getWinningResult(
            HashMap<LottoRank, Integer> prizeCounts,
            ArrayList<TreeSet<Integer>> randomList) {

        int count = 0;
        for (TreeSet<Integer> set : randomList) {
            set.retainAll(numbers);
            count = set.size();

            // 당첨이 된 경우
            if (count >= 3) {
                if (count == 5 && set.contains(bonus)) {
                    prizeCounts.put(LottoRank.SECOND, prizeCounts.get(LottoRank.SECOND) + 1);
                }
                LottoRank rank = LottoRank.from(count);
                prizeCounts.put(rank, prizeCounts.get(rank) + 1);
            }
        }
        return prizeCounts;
    }

    // 총 수익
    public BigDecimal calculateTotalProfit(HashMap<LottoRank, Integer> prizeCounts) {
        BigDecimal totalProfit = BigDecimal.ZERO;

        for (LottoRank lottoRank : LottoRank.values()) {
            // null-safe
            int counts = prizeCounts.getOrDefault(lottoRank, 0);

            BigDecimal winnings = new BigDecimal(lottoRank.winning);
            totalProfit = totalProfit.add(winnings.multiply(new BigDecimal(counts)));
        }
        return totalProfit;
    }

    // 수익률 = (총 상금 - 총 투자금) / 총 투자금 * 100
    public BigDecimal calculateTotalRate(BigDecimal totalProfit, int money) {
        BigDecimal totalCost = new BigDecimal(String.valueOf(money));

        BigDecimal rate = totalProfit
                // 정확도를 높이기위해 정밀도 4 설정
                .divide(totalCost, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal("100"));

        // 소수점 첫째자리 반올림
        return rate.setScale(1, RoundingMode.HALF_UP);
    }
}
