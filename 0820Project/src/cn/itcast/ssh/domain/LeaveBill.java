package cn.itcast.ssh.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通知单  
 */
public class LeaveBill {
	private Long id;//主键ID
	private Integer days;// 会议时长 
	private String Mtheme;  // 会议主题 
	private String address;// 会议地址 	
	private Date leaveDate = new Date();// 通知单生成时间  
	private Date meetingDate;  // 会议时间      
	private String remark;// 备注
	private Employee user;// 参会人员 
	
	private Integer state=0;// 通知 状态 0初始录入,1.开始审批,2为审批完成

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
	
	public String getMtheme() {
		return Mtheme;
	}

	public void setMtheme(String Mtheme) {
		this.Mtheme = Mtheme;
	}  

	public String getAddress() {
		return address;
	}

	public void setAddress(String addr) {
		this.address = addr;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) throws ParseException {

		this.leaveDate = leaveDate;
	} 
	
	public void setMeetingDate(Date meetingDate) throws ParseException{  
		//System.out.println(meetingDate+"\n\n\n\n");  
		this.meetingDate = meetingDate; 
	}
	
	public Date getMeetingDate(){
		return meetingDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Employee getUser() {
		return user;
	}

	public void setUser(Employee user) {
		this.user = user;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
