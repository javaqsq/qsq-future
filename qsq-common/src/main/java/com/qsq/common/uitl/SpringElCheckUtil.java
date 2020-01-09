package com.qsq.common.uitl;

import com.qsq.common.auth.annotation.el.AuthExpressionResult;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author qsq
 */
@UtilityClass
@Slf4j
public class SpringElCheckUtil {
    private static ExpressionParser PARSER = new SpelExpressionParser();

    /**
     * 校验expression是否能通过rootObject的检测
     *
     * @param context    上下文
     * @param expression 表达式
     * @return 是否通过
     */
    public static AuthExpressionResult check(EvaluationContext context, String expression) {
        AuthExpressionResult result = PARSER.parseExpression(expression)
                .getValue(context, AuthExpressionResult.class);
        log.info("expression = {}, eval result = {}", expression, result);
        return result ;
    }
}
