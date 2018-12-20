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
 *前端查询参数对应pojo类与数据库字段一致性设置以便拼装查询语句
 */
public @interface FieldSqlWhereAnnotation {
	String DbField() default "";
}
