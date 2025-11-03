package lotto;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import static lotto.ConstantMessages.*;
import static lotto.ConstantMessages.LOTTO_MONEY_IS_NOT_DIVIDABLE_ERROR;

public class InputParser {
    public static String ERROR_PREFIX = "[ERROR] ";

    private final InputValidator inputValidator;

    public InputParser(InputValidator inputValidator){
    this.inputValidator = inputValidator;
    }

    // 금액 파싱 및 검증
    public int parseLottoMoney(String input) {
        this.inputValidator.checkHasBlank(input);
        int money = this.inputValidator.checkIsInteger(input);
        this.inputValidator.checkIsNegative(money);
        this.inputValidator.checkUnitOfThousand(money);
        return money;
    }

    // 로또 횟수
    public int parseCount(int money) {
        int count;
        try {
            // 몫 구하기
            count = money / 1000;
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + LOTTO_MONEY_IS_NOT_DIVIDABLE_ERROR.getMessage());
        }
        return count;
    }

    // 정숫값 당첨 번호 리스트
    public ArrayList<Integer> parseChosenNumber(String inputNumber) {
        String[] inputNumbers = parseInputNumber(inputNumber);

        ArrayList<Integer> lottoList = new ArrayList<>();
        try {
            for (String numberString : inputNumbers) {
                int number = Integer.parseInt(numberString);
                this.inputValidator.checkLottoRange(number);
                lottoList.add(number);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + INPUT_NUMBER_IS_NOT_INTEGER_ERROR.getMessage());
        }
        return lottoList;
    }

    // 정규식 "," 이용해 String 파싱
    private String[] parseInputNumber(String inputNumber) {
        String[] inputNumbers;
        try {
            inputNumbers = inputNumber.split(",");
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + DELIMITER_IS_NOT_DEFAULT_ERROR.getMessage());
        }
        return inputNumbers;
    }

    // 보너스 번호
    public int parseBonusNumber(String bonus, ArrayList<Integer> lottoList) {
        int bonusNumber;
        try {
            bonusNumber = Integer.parseInt(bonus);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + INPUT_NUMBER_IS_NOT_INTEGER_ERROR.getMessage());
        }
        this.inputValidator.checkLottoRange(bonusNumber);
        this.inputValidator.checkDuplicatedNumber(bonusNumber, lottoList);

        return bonusNumber;
    }

}
