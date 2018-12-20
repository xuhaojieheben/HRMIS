/**
 * 
 */
package com.custom.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
/**
 * @author john
 *ǰ�˲�ѯ������Ӧpojo�������ݿ��ֶ�һ���������Ա�ƴװ��ѯ���
 */
public @interface FieldSqlWhereAnnotation {
	String DbField() default "";
}
