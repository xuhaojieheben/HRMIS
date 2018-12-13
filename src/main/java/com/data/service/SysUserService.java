package com.data.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.data.iservice.ISysUserService;
import model.sysmodel.entity.Sys_User;
import orm.mybatis.idao.ISysUserMapper;
@Repository("sysUserService")
public class SysUserService implements ISysUserService{
	@Autowired
	private ISysUserMapper _iSysUserMapper;
	
	@Override
	public int Add(Sys_User t) {
		// TODO �Զ����ɵķ������
		return _iSysUserMapper.Add(t);
	}

	@Override
	public int Modify(Sys_User t) {
		// TODO �Զ����ɵķ������
		return _iSysUserMapper.Modify(t);
	}

	@Override
	public int Del(Sys_User t) {
		// TODO �Զ����ɵķ������
		return _iSysUserMapper.Del(t);
	}

	@Override
	public int Delete(int Id) {
		// TODO �Զ����ɵķ������
		return _iSysUserMapper.Delete(Id);
	}

	@Override
	public List<Sys_User> Select(Sys_User t) {
		// TODO �Զ����ɵķ������
		return _iSysUserMapper.Select(t);
	}

	@Override
	public List<Sys_User> ValidateUser(Map<String, Object> map) {
		// TODO �Զ����ɵķ������
		return _iSysUserMapper.ValidateUser(map);
	}

	@Override
	public List<Sys_User> SelectAll() {
		// TODO �Զ����ɵķ������
		return _iSysUserMapper.SelectAll();
	}

	@Override
	public List<Sys_User> QuerySysUserByPage(Sys_User t) {
		// TODO �Զ����ɵķ������
		return _iSysUserMapper.QuerySysUserByPage(t);
	}
}
