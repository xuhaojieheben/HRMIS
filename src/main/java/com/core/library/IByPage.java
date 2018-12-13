package com.core.library;
import java.util.List;
public interface IByPage<T> {
	List<T> QuerySysUserByPage(T t);
}
