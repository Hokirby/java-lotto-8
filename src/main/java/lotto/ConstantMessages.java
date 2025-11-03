package lotto;

public enum ConstantMessages {
    INIT_MESSAGE("구입금액을 입력해 주세요."),
    INPUT_WINNING_NUMBERS("당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBERS("보너스 번호를 입력해 주세요."),

    HAS_NO_INPUT_VALUE_ERROR("입력값이 존재하지 않습니다."),
    NUMBER_RANGE_ERROR("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    LOTT0_NUMBER_SIZE_MUST_BE_SIX_ERROR("로또 번호는 6개여야 합니다."),
    LOTTO_MONEY_FORMATION_ERROR("로또 금액이 정수 형식이 아닙니다."),
    LOTTO_MONEY_NOT_IN_UNIT_OF_1000_ERROR("로또 금액이 1000원 단위가 아닙니다."),
    LOTTO_MONEY_NEGATIVE_ERROR("입력값이 양의 정수가 아닙니다."),
    LOTTO_MONEY_IS_NOT_DIVIDABLE_ERROR("1000원으로 나눌 수 없늠 금액입니다."),

    DELIMITER_IS_NOT_DEFAULT_ERROR("구분자값이 , 이 아닙니다."),
    INPUT_NUMBER_IS_OUT_OF_LOTTO_RANGE_ERROR("입력값이 로또 범위의 숫자값이 아닙니다."),
    INPUT_NUMBER_IS_NOT_INTEGER_ERROR("입력한 값이 정수 형태가 아닙니다."),
    LOTTO_NUMBER_DUPLICATED_ERROR("로또번호는 서로 중복값일 수 없습니다."),
    LOTTO_AND_BONUS_NUMBER_DUPLICATED_ERROR("당첨번호와 보너스 번호는 중복값일 수 없습니다.");


    private final String message;

    ConstantMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

