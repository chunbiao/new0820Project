package cn.itcast.ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.ssh.dao.ILeaveBillDao;
import cn.itcast.ssh.domain.Employee;
import cn.itcast.ssh.domain.LeaveBill;
import cn.itcast.ssh.utils.SessionContext;

public class LeaveBillDaoImpl extends HibernateDaoSupport implements ILeaveBillDao {

	/**查询自己的通知单的信息*/
	@Override
	public List<LeaveBill> findLeaveBillList() {
		//从Session中获取当前用户
		Employee employee = SessionContext.get();
		String hql = "from LeaveBill o where o.user=?";//指定当前用户的通知单
		List<LeaveBill> list = this.getHibernateTemplate().find(hql,employee);
		return list;
	}
	
	
	/**保存通知单*/
	@Override
	public void saveLeaveBill(LeaveBill leaveBill) {  
		//System.out.println(leaveBill.getMeetingDate());   
		this.getHibernateTemplate().save(leaveBill);
	}
	
	/**使用通知单ID，查询通知单的对象*/
	@Override
	public LeaveBill findLeaveBillById(Long id) {
		return this.getHibernateTemplate().get(LeaveBill.class, id);
	}
	
	/**更新通知单*/
	@Override
	public void updateLeaveBill(LeaveBill leaveBill) {
		this.getHibernateTemplate().update(leaveBill);
	}
	
	/**使用通知单ID，删除通知单*/
	@Override
	public void deleteLeaveBillById(Long id) {
		//2：使用通知单ID，查询通知单信息，获取对象LeaveBill
		LeaveBill bill = this.findLeaveBillById(id);
		//3：执行删除
		this.getHibernateTemplate().delete(bill);
	}
}
