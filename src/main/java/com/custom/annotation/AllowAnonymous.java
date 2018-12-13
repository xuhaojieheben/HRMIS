/**
 * 
 */
package com.custom.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
/*** 
* @author john
* @version 1.2
* AllowAnonymousType.Allow 匿名
* AllowAnonymousType.Authorize 验证
*/
public @interface AllowAnonymous {
	AllowAnonymousType value() default AllowAnonymousType.Allow;
}