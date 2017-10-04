package com.itheima.web.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts2.ServletActionContext;

import com.itheima.dao.TopicDao;
import com.itheima.dao.TopicDaoImpl;
import com.itheima.domain.Reply;
import com.itheima.domain.Topic;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TopicAction extends ActionSupport implements ModelDriven<Topic>{

	/**
	 * 
	 */
	private TopicDao ud = new TopicDaoImpl();
	private Topic topic = new Topic();
	private Reply reply;
	private String key;
	private int tid;
	public Topic getModel() {
		return topic;
	}
	
	public String addh(){
		//1 ip
		reply.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		//2 创建/回帖时间
		reply.setCreateDate(new Date());
		//3tid
		//保存
		ud .save1(reply);
		return "addh";
	}
//根据标题查找帖子
	public String find(){
		String tid = ServletActionContext.getRequest().getParameter("tid");
		int id = Integer.parseInt(tid);
		Topic find = ud.find(id);
		/*Set<Reply> set = find.getReplySet();
		Iterator<Reply> it = set.iterator();
		while(it.hasNext()){
			Reply next = it.next();
			System.out.println(next);
		}*/
		//将帖子放到Actioncontext中
		ActionContext.getContext().put("find", find);
		return "find";
	}
	
	public String find1(){
		
		Topic find = ud.find(1);
		//将帖子放到Actioncontext中
		ActionContext.getContext().put("find", find);
		return "find1";
	}
	
	//帖子列表
	public String list(){
		//1调用dao获得帖子列表
		List<Topic> list = ud.getAll(key);
		//2 将帖子列表放入ActionContext中
		ActionContext.getContext().put("list", list);
		
		return "list";
	}
	
//------------------------------------------------------
	//发帖
	private File photo;
	//struts2在文件上传时提供的属性
	private String photoFileName;//上传的文件名。上传字段名称+FileName  注意大小写
	public String add(){
		
		//1 ip
		topic.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		//2 创建/回帖时间
		topic.setCreateDate(new Date());
		topic.setLastReplyDate(topic.getCreateDate());
		//3保存
		//1.拿到ServletContext
		ServletContext application = ServletActionContext.getServletContext();
		//2.调用realPath方法，获取根据一个虚拟目录得到的真实目录
		String filePath = application.getRealPath("/files");
		//3.如果这个真实的目录不存在，需要创建
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		//4.把photo存过去
		//剪切：把临时文件剪切指定的位置，并且给他重命名。 注意：临时文件没有了
		photo.renameTo(new File(file,photoFileName));
		String path = application.getRealPath(photoFileName);
		String str = path.substring(path.length()-5, path.length());
		topic.setUrl(str);
		ud .save(topic);
		
		return "toList";
	}
//---------------------------------------------------------------
	
	public int getTid() {
		return tid;
	}
	public File getPhoto() {
		return photo;
	}

	
	
	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	public Topic getTopic() {
		return topic;
	}

	public Reply getReply() {
		return reply;
	}
	
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}



}
