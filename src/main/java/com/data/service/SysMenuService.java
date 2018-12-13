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
		// TODO 自动生成的方法存根
		return _iSysMenuMapper.Add(t);
	}

	@Override
	public int Modify(Sys_Menu t) {
		// TODO 自动生成的方法存根
		return _iSysMenuMapper.Modify(t);
	}

	@Override
	public int Del(Sys_Menu t) {
		// TODO 自动生成的方法存根
		return _iSysMenuMapper.Del(t);
	}

	@Override
	public int Delete(int Id) {
		// TODO 自动生成的方法存根
		return _iSysMenuMapper.Delete(Id);
	}

	@Override
	public List<Sys_Menu> Select(Sys_Menu t) {
		// TODO 自动生成的方法存根
		return _iSysMenuMapper.Select(t);
	}

	@Override
	public int Delete(String menuId) {
		// TODO 自动生成的方法存根
		return _iSysMenuMapper.Delete(menuId);
	}

	@Override
	public List<Sys_Menu> SelectAll() {
		// TODO 自动生成的方法存根
		return _iSysMenuMapper.SelectAll();
	}

	@Override
	public List<Sys_Menu> QuerySysUserByPage(Sys_Menu t) {
		// TODO 自动生成的方法存根
		return _iSysMenuMapper.QuerySysUserByPage(t);
	}
}
