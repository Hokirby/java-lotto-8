package lotto;

import java.util.Arrays;

public enum LottoRank {
    FIFTH(3, "5000", "3개 일치 (5,000원)"),
    FOURTH(4, "50000", "4개 일치 (50,000원)"),
    THIRD(5, "1500000", "5개 일치 (1,500,000원)"),
    SECOND(5, "30000000", "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    FIRST(6, "2000000000", "6개 일치 (2,000,000,000원)");

    final int count;
    final String winning;
    final String label;

    LottoRank(int count, String winning, String label) {
        this.winning = winning;
        this.count = count;
        this.label = label;
    }

    public static LottoRank from(int matchCount) {
        return Arrays.stream(LottoRank.values())
                .filter(r -> r.count == matchCount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 당첨 등수 입니다."));
    }
}
