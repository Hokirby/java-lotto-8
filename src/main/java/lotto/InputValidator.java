package lotto;

import java.util.ArrayList;

import static lotto.ConstantMessages.*;
import static lotto.InputParser.ERROR_PREFIX;

public class InputValidator {

    // 공백 체크
    public void checkHasBlank(String input) {
        if (input.contains(" ")) {
            throw new IllegalArgumentException(ERROR_PREFIX + HAS_NO_INPUT_VALUE_ERROR.getMessage());
        }
    }

    // Integer 형태인가
    public int checkIsInteger(String input) {
        int money;
        try {
            money = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_PREFIX + LOTTO_MONEY_FORMATION_ERROR.getMessage());
        }
        return money;
    }

    // 음수 validate
    public void checkIsNegative(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(ERROR_PREFIX + LOTTO_MONEY_NEGATIVE_ERROR.getMessage());
        }
    }

    // 1000원 단위 validate
    public void checkUnitOfThousand(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException(ERROR_PREFIX + LOTTO_MONEY_NOT_IN_UNIT_OF_1000_ERROR.getMessage());
        }
    }

    // 1 ~ 45 로또 범위인가
    public void checkLottoRange(int number) {
        if (number <= 0 || number > 45) {
            throw new IllegalArgumentException(ERROR_PREFIX + INPUT_NUMBER_IS_OUT_OF_LOTTO_RANGE_ERROR.getMessage());
        }
    }

    // 당첨번호와 보너스 번호가 중복되는가
    public void checkDuplicatedNumber(int bonusNumber, ArrayList<Integer> lottoList) {
        if (lottoList.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_PREFIX + LOTTO_AND_BONUS_NUMBER_DUPLICATED_ERROR.getMessage());
        }
    }

}
