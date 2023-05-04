package chapter.aop.shared.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS("200", "OK", HttpStatus.OK),
    BAD_REQUEST("400", "Bad Request", HttpStatus.BAD_REQUEST);

    private final String code;

    private final String message;

    private final HttpStatus httpStatus;

}
