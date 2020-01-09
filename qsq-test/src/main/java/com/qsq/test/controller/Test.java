package com.qsq.test.controller;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author QSQ
 * @create 2020/1/6 15:16
 * No, again
 * 〈〉
 */
public class Test {

    /**
     * ExpressionParser 解析表达式对象的类
     *
     */


    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = null ;
        Person person = new Person("1", "jack","admin");
        exp = parser.parseExpression("hasRole('role')");
        StandardEvaluationContext ctx = new StandardEvaluationContext(person);
        CheckPerson value = exp.getValue(ctx, CheckPerson.class);

        /*
         * ExpressionParser 表达式解析器
         * StandardEvaluationContext 这个是对象的操作数据
         * Expression 表达式
         * 1.创建一个表达式解析器
         * 2.创建StandardEvaluationContext 操作对象体 ， 可以用构造器传入一个对象 ， 编辑逻辑
         * 3.创建一个表达式获取执行的结果
         */

        //true
        System.out.println(value.getMsg());
    }


}