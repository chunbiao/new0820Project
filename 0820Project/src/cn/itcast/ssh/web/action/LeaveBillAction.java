package cn.itcast.ssh.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.itcast.ssh.domain.LeaveBill;
import cn.itcast.ssh.service.ILeaveBillService;
import cn.itcast.ssh.utils.ValueContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class LeaveBillAction extends ActionSupport implements ModelDriven<LeaveBill> {

	private LeaveBill leaveBill = new LeaveBill();
	
	@Override
	public LeaveBill getModel() {
		//System.out.println(leaveBill.getMeetingDate()+"\n\n"); 
		return leaveBill; 
	}
	
	private ILeaveBillService leaveBillService;

	public void setLeaveBillService(ILeaveBillService leaveBillService) {
		this.leaveBillService = leaveBillService;
	}

	public String home(){
		//查询所有的请假信息（对应a_leavebill），返回List<LeaveBill>
		List<LeaveBill> list = leaveBillService.findLeaveBillList(); 
		
		//放置到上下文对象中
		ValueContext.putValueContext("list", list);
		return "home";
	}
	
	 //添加请假申请
	public String input(){ 
		Long id = leaveBill.getId();
		//修改
		if(id!=null){
			LeaveBill bill = leaveBillService.findLeaveBillById(id);
			ValueContext.putValueStack(bill);
		}
		//新增
		return "input";
	}
	
	public String save() {
		//执行保存     
		System.out.println(leaveBill.getMeetingDate()+"\n\n"); 
		leaveBillService.saveLeaveBill(leaveBill);
		return "save";
	}
	
	public String delete(){
		//获取请假单ID
		Long id = leaveBill.getId();
		//执行删除
		leaveBillService.deleteLeaveBillById(id);
		return "save";
	}
}
