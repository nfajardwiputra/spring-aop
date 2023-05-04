package chapter.aop.shared.transform;

import chapter.aop.shared.template.ResponseDetail;
import chapter.aop.shared.template.ResponseSchema;
import chapter.aop.shared.template.ResponseTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ResponseTemplateTransform {

    @Named("createDetail")
    @Mapping(target = "detail", source = "body")
    ResponseDetail<Object> createDetail(Object body);

    @Mapping(target = "responseOutput", source = "body", qualifiedByName = "createDetail")
    ResponseTemplate<Object> templateDetail(ResponseSchema responseSchema, Object body);

}
