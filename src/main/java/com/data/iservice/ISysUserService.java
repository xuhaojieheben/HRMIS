package com.data.iservice;

import java.util.List;
import java.util.Map;

import com.core.library.IByPage;
import com.core.library.IDbServiceReposity;
import model.sysmodel.entity.Sys_User;

public interface ISysUserService extends IDbServiceReposity<Sys_User>, IByPage<Sys_User>{
	List<Sys_User> ValidateUser(Map<String, Object> map);
}
