package com.core.library;

import java.util.List;

public interface IDbServiceReposity<T> {
	int Add(T t);
	
	int Modify(T t);
	
	int Del(T t);
	
	int Delete(int Id);
	
	List<T> Select(T t);
	
	List<T> SelectAll();
}
