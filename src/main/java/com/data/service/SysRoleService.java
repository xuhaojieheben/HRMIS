package com.data.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.data.iservice.ISysRoleService;
import model.sysmodel.entity.Sys_Role;
import orm.mybatis.idao.ISysRoleMapper;

@Repository("sysRoleService")
public class SysRoleService implements ISysRoleService{
	@Autowired
	private ISysRoleMapper _iSysRoleMapper;
	@Override
	public int Add(Sys_Role t) {
		// TODO �Զ����ɵķ������
		return _iSysRoleMapper.Add(t);
	}

	@Override
	public int Modify(Sys_Role t) {
		// TODO �Զ����ɵķ������
		return _iSysRoleMapper.Modify(t);
	}

	@Override
	public int Del(Sys_Role t) {
		// TODO �Զ����ɵķ������
		return _iSysRoleMapper.Del(t);
	}

	@Override
	public int Delete(int Id) {
		// TODO �Զ����ɵķ������
		return _iSysRoleMapper.Delete(Id);
	}

	@Override
	public List<Sys_Role> Select(Sys_Role t) {
		// TODO �Զ����ɵķ������
		return _iSysRoleMapper.Select(t);
	}

	@Override
	public List<Sys_Role> SelectAll() {
		// TODO �Զ����ɵķ������
		return _iSysRoleMapper.SelectAll();
	}

}
