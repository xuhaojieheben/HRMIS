package com.data.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.data.iservice.ISysMenuService;
import model.sysmodel.entity.Sys_Menu;
import orm.mybatis.idao.ISysMenuMapper;

@Repository("sysMenuService")
public class SysMenuService implements ISysMenuService{
	@Autowired
	private ISysMenuMapper _iSysMenuMapper;
	@Override
	public int Add(Sys_Menu t) {
		// TODO �Զ����ɵķ������
		return _iSysMenuMapper.Add(t);
	}

	@Override
	public int Modify(Sys_Menu t) {
		// TODO �Զ����ɵķ������
		return _iSysMenuMapper.Modify(t);
	}

	@Override
	public int Del(Sys_Menu t) {
		// TODO �Զ����ɵķ������
		return _iSysMenuMapper.Del(t);
	}

	@Override
	public int Delete(int Id) {
		// TODO �Զ����ɵķ������
		return _iSysMenuMapper.Delete(Id);
	}

	@Override
	public List<Sys_Menu> Select(Sys_Menu t) {
		// TODO �Զ����ɵķ������
		return _iSysMenuMapper.Select(t);
	}

	@Override
	public int Delete(String menuId) {
		// TODO �Զ����ɵķ������
		return _iSysMenuMapper.Delete(menuId);
	}

	@Override
	public List<Sys_Menu> SelectAll() {
		// TODO �Զ����ɵķ������
		return _iSysMenuMapper.SelectAll();
	}

	@Override
	public List<Sys_Menu> QuerySysUserByPage(Sys_Menu t) {
		// TODO �Զ����ɵķ������
		return _iSysMenuMapper.QuerySysUserByPage(t);
	}
}
