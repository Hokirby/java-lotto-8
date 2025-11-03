package lotto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import static lotto.ConstantMessages.*;

public class ConsolePrinter {
    private ConsolePrinter() {
    }

    public static void printInitMessage() {
        System.out.println(INIT_MESSAGE.getMessage());
    }
    public static void printLottoCountsMessage(int count) {
        System.out.println("\n" + count + "개를 구매했습니다.");
    }
    public static void printInputMessage() {
        System.out.println(INPUT_WINNING_NUMBERS.getMessage());
    }
    public static void printBonusMessage() {
        System.out.println("\n" + INPUT_BONUS_NUMBERS.getMessage());
    }

    public static void printEarningRateMessage(BigDecimal rate) {
        System.out.print("총 수익률은 " + rate + "%입니다.");
    }

    public static void printRandomNumbers(int count, ArrayList<TreeSet<Integer>> numberList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            TreeSet<Integer> numbers = numberList.get(i);
            stringBuilder.append("[");
            for (Integer number : numbers) {
                stringBuilder.append(number).append(", ");
            }
            // 쉼표제거
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append("]").append("\n");
        }
        System.out.println(stringBuilder);
    }

    public static void printWinningResults(HashMap<LottoRank, Integer> prizeCounts) {
        StringBuilder strings = new StringBuilder();
        strings.append("\n").append("당첨 통계").append("\n").append("---").append("\n");
        for (LottoRank rank : LottoRank.values()) {
            strings.append(rank.label)
                    .append(" - ")
                    .append(prizeCounts.getOrDefault(rank, 0))
                    .append("개")
                    .append("\n");
        }
        System.out.print(strings);
    }
}
