package org.siu.myboot.core.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @Author Siu
 * @Date 2020/3/16 14:00
 * @Version 0.0.1
 */
public class CustomOnPropertyCondition implements Condition {

    @Override
    @NotNull
    public boolean matches(@NotNull ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Map<String, Object> annotationAttributes = annotatedTypeMetadata.getAnnotationAttributes(CustomConditionalOnProperty.class.getName());
        String propertyName = (String) annotationAttributes.get("name");
        String[] values = (String[]) annotationAttributes.get("havingValue");
        if (0 == values.length) {
            return false;
        }
        String propertyValue = conditionContext.getEnvironment().getProperty(propertyName);
        // 有一个匹配上就ok
        for (String havingValue : values) {
            if (propertyValue.equalsIgnoreCase(havingValue)) {
                return true;
            }
        }
        return false;
    }
}
