package cn.itcast.ssh.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.struts2.ServletActionContext;

import cn.itcast.ssh.domain.LeaveBill;
import cn.itcast.ssh.service.ILeaveBillService;
import cn.itcast.ssh.service.IWorkflowService;
import cn.itcast.ssh.utils.SessionContext;
import cn.itcast.ssh.utils.ValueContext;
import cn.itcast.ssh.web.form.WorkflowBean;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class WorkflowAction extends ActionSupport implements ModelDriven<WorkflowBean> {

	private WorkflowBean workflowBean = new WorkflowBean();
	
	@Override
	public WorkflowBean getModel() {
		return workflowBean;
	}
	private IWorkflowService workflowService;
	private ILeaveBillService leaveBillService;
	public void setLeaveBillService(ILeaveBillService leaveBillService) {
		this.leaveBillService = leaveBillService;
	}

	public void setWorkflowService(IWorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	/**
	 * 部署管理首页显示
	 */
	public String deployHome(){
		//查询部署对象信息
		List<Deployment> depList = workflowService.findDeploymentList();
		//查询流程定义的信息
		List<ProcessDefinition> pdList = workflowService.findProcessDefinitionList();
		//放置到上下文对象中
		ValueContext.putValueContext("depList", depList);
		ValueContext.putValueContext("pdList", pdList);
		return "deployHome";
	}
	

	public String newdeploy(){
		File file = workflowBean.getFile();
		String filename = workflowBean.getFilename();
		workflowService.saveNewDeploye(file,filename);
		return "list";
	}
	
	public String delDeployment(){
		String deploymentId = workflowBean.getDeploymentId();
		workflowService.deleteProcessDefinitionByDeploymentId(deploymentId);
		return "list";
	}
	
	/**
	 * 查看流程图
	 */
	public String viewImage() throws Exception{
		//获取页面传递的部署对象ID和资源图片名称
		String deploymentId = workflowBean.getDeploymentId();
		String imageName = workflowBean.getImageName();
		//获取资源文件表（act_ge_bytearray）中资源图片输入流InputStream
		InputStream in = workflowService.findImageInputStream(deploymentId,imageName);
		//从response对象获取输出流
		OutputStream out = ServletActionContext.getResponse().getOutputStream();
		//将输入流中的数据读取出来，写到输出流中
		for(int b=-1;(b=in.read())!=-1;){
			out.write(b);
		}
		out.close();
		in.close();
		//将图写到页面上，用输出流写
		return null;
	}
	
	// 启动流程
	public String startProcess(){
		workflowService.saveStartProcess(workflowBean);
		return "listTask";
	}
	
	public String listTask(){
		String name = SessionContext.get().getName();
		List<Task> list = workflowService.findTaskListByName(name); 
		ValueContext.putValueContext("list", list);
		return "task";
	}
	
	/**
	 * 打开任务表单
	 */
	public String viewTaskForm(){
		String taskId = workflowBean.getTaskId();
		String url = workflowService.findTaskFormKeyByTaskId(taskId);
		url += "?taskId="+taskId;
		ValueContext.putValueContext("url", url);
		return "viewTaskForm";
	}
	
	// 准备表单数据
	public String audit(){
		String taskId = workflowBean.getTaskId();
		//使用任务ID，查找请假单ID，从而获取请假单信息
		LeaveBill leaveBill = workflowService.findLeaveBillByTaskId(taskId);
		ValueContext.putValueStack(leaveBill);
		List<String> outcomeList = workflowService.findOutComeListByTaskId(taskId);
		ValueContext.putValueContext("outcomeList", outcomeList);
		//查询所有历史审核人的审核信息，帮助当前人完成审核，返回List<Comment>
		List<Comment> commentList = workflowService.findCommentByTaskId(taskId);
		ValueContext.putValueContext("commentList", commentList);
		return "taskForm";
	}
	
	public String submitTask(){
		workflowService.saveSubmitTask(workflowBean);
		return "listTask";
	}
	
	public String viewCurrentImage(){
		String taskId = workflowBean.getTaskId();

		ProcessDefinition pd = workflowService.findProcessDefinitionByTaskId(taskId);
		ValueContext.putValueContext("deploymentId", pd.getDeploymentId());
		ValueContext.putValueContext("imageName", pd.getDiagramResourceName());
		Map<String, Object> map = workflowService.findCoordingByTask(taskId);
		ValueContext.putValueContext("acs", map);
		return "image";
	}
	
	// 查看历史的批注信息
	public String viewHisComment(){
		Long id = workflowBean.getId();
		LeaveBill leaveBill = leaveBillService.findLeaveBillById(id);
		ValueContext.putValueStack(leaveBill);
		List<Comment> commentList = workflowService.findCommentByLeaveBillId(id);
		ValueContext.putValueContext("commentList", commentList);
		return "viewHisComment";
	}
}
