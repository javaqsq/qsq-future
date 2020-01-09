package com.qsq.common.uitl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 类反射用的工具类
 *
 * @author qsq
 */
public class ClassUtils {


    /**
     * 获取类的属性字段
     *
     * @param objClass
     * @return
     */
    public static List<Field> getAllFields(Class objClass) {
        if (objClass == null) {
            return Collections.emptyList();
        }
        List<Field> fields = new ArrayList<>();
        while (!objClass.isAssignableFrom(Object.class)) {
            fields.addAll(Arrays.asList(objClass.getDeclaredFields()));
            objClass = objClass.getSuperclass();
        }
        return fields;
    }

    /**
     *
     * @param obj
     * @param field
     * @param value
     */
    public static void fillFieldValue(Object obj, Field field, Object value) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), obj.getClass());
            Method method = pd.getWriteMethod();
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getValue(Object obj, String fieldName) {
        if (obj == null || fieldName == null) {
            return null;
        }
        try {
            Class clazz = obj.getClass();
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
            return pd == null ? null : pd.getReadMethod().invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getFieldValue(Field field, Object obj) {
        return field == null ? null : getValue(obj, field.getName());
    }

    public static List<Field> filterFields(Class objClass, Class filter) {
        return filterFields(objClass, field -> filter.equals(field.getType()));
    }

    public static List<Field> filterFields(Class targetClass, Predicate<Field> predicate) {
        return CollectionUtils.filter(getAllFields(targetClass), predicate);
    }

    public static void tranMatchField(Object object, Function func, Class filter) {
        List<Field> fields = ClassUtils.filterFields(object.getClass(), filter);
        fields.forEach(field -> {
            field.setAccessible(true);
            ClassUtils.fillFieldValue(object, field, func.apply(ClassUtils.getFieldValue(field, object)));
        });
    }

    public static boolean match(Object value, Class targetClass) {
        return value == null || value.getClass().equals(targetClass);
    }

    public static boolean isJavaClass(Class clz) {
        String className = clz.getName();
        String[] nameArr = className.split("\\.");
        if (nameArr.length <= 1 || nameArr[0].equalsIgnoreCase("java")) {
            return true;
        }
        return false;
    }
}
