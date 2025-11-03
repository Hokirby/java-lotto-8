package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class Application {
    public static void main(String[] args) {
        ConsolePrinter.printInitMessage();
        String input = Console.readLine();

        InputParser inputParser = new InputParser(new InputValidator());
        int money = inputParser.parseLottoMoney(input);
        int count = inputParser.parseCount(money);
        ConsolePrinter.printLottoCountsMessage(count);

        // count 만큼 random 구하기
        ArrayList<TreeSet<Integer>> randomList = RandomGenerator.generateNumbers(count);

        // count 만큼의 난수 출력
        ConsolePrinter.printRandomNumbers(count, randomList);

        // 당첨 번호
        ConsolePrinter.printInputMessage();
        String inputNumber = Console.readLine();
        ArrayList<Integer> lottoList = inputParser.parseChosenNumber(inputNumber);

        // 보너스 번호
        ConsolePrinter.printBonusMessage();
        String bonus = Console.readLine();
        int bonusNumber = inputParser.parseBonusNumber(bonus, lottoList);


        // 입력값 로또 당첨번호 객체
        Lotto lotto = new Lotto(lottoList, bonusNumber);
        HashMap<LottoRank, Integer> prizeCounts = lotto.initPrizeCounts();
        // 집계 결과
        prizeCounts = lotto.getWinningResult(prizeCounts, randomList);
        ConsolePrinter.printWinningResults(prizeCounts);

        // 총 수익
        BigDecimal totalProfit = lotto.calculateTotalProfit(prizeCounts);
        // 수익률
        BigDecimal rate = lotto.calculateTotalRate(totalProfit, money);
        ConsolePrinter.printEarningRateMessage(rate);
    }
}


