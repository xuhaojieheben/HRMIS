package orm.mybatis.idao;

import com.core.library.IByPage;
import com.core.library.IDbServiceReposity;
import model.sysmodel.entity.Sys_Menu;

public interface ISysMenuMapper extends IDbServiceReposity<Sys_Menu>, IByPage<Sys_Menu>{
	int Delete(String menuId);
}
