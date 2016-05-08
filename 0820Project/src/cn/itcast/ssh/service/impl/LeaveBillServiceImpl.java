package cn.itcast.ssh.service.impl;

import java.util.List;

import cn.itcast.ssh.dao.ILeaveBillDao;
import cn.itcast.ssh.domain.LeaveBill;
import cn.itcast.ssh.service.ILeaveBillService;
import cn.itcast.ssh.utils.SessionContext;

public class LeaveBillServiceImpl implements ILeaveBillService {

	private ILeaveBillDao leaveBillDao;

	public void setLeaveBillDao(ILeaveBillDao leaveBillDao) {
		this.leaveBillDao = leaveBillDao;
	}
	
	/**查询自己的通知的信息*/
	@Override
	public List<LeaveBill> findLeaveBillList() {
		List<LeaveBill> list = leaveBillDao.findLeaveBillList();
		return list;
	}
	
	/**保存通知*/
	@Override
	public void saveLeaveBill(LeaveBill leaveBill) {
		Long id = leaveBill.getId();
		if(id==null){
			leaveBill.setUser(SessionContext.get());
			leaveBillDao.saveLeaveBill(leaveBill);
		}
		else{
			leaveBillDao.updateLeaveBill(leaveBill);
		}
	}
	
	/**使用通知ID，查询通知的对象*/
	@Override
	public LeaveBill findLeaveBillById(Long id) {
		LeaveBill bill = leaveBillDao.findLeaveBillById(id);
		return bill;
	}
	
	/**使用通知ID，删除通知*/
	@Override
	public void deleteLeaveBillById(Long id) {
		leaveBillDao.deleteLeaveBillById(id);
	}
}
