package chapter.aop.shared.transform;

import chapter.aop.shared.constant.ResponseEnum;
import chapter.aop.shared.template.ResponseSchema;
import chapter.aop.shared.template.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class ResponseEntityTransform {

    private final ResponseTemplateTransform responseTemplateTransform;

    public <T> ResponseEntity<ResponseTemplate<T>> response() {
        return response(ResponseEnum.SUCCESS, null);
    }

    public <T> ResponseEntity<ResponseTemplate<T>> response(T body) {
        return response(ResponseEnum.SUCCESS, body);
    }

    public <T> ResponseEntity<ResponseTemplate<T>> response(ResponseEnum responseEnum) {
        return response(responseEnum, null);
    }

    public <T> ResponseEntity<ResponseTemplate<T>> response(ResponseEnum responseEnum, T body) {
        return response(
                (ResponseTemplate<T>) responseTemplateTransform.templateDetail(ResponseSchema.builder()
                                .responseCode(responseEnum.getCode())
                                .responseMessage(responseEnum.getMessage())
                                .build(),
                        body),
                responseEnum.getHttpStatus());
    }

    public <T> ResponseEntity<ResponseTemplate<T>> response(ResponseTemplate<T> responseTemplate, HttpStatus httpStatus) {
        return new ResponseEntity<>(responseTemplate, httpStatus);
    }

}
