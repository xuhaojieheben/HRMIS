package com.data.iservice;

import com.core.library.IByPage;
import com.core.library.IDbServiceReposity;
import model.sysmodel.entity.Sys_Menu;

public interface ISysMenuService extends IDbServiceReposity<Sys_Menu>, IByPage<Sys_Menu>{
	int Delete(String menuId);
}
