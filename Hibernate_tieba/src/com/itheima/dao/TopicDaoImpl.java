package com.itheima.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.itheima.domain.Reply;
import com.itheima.domain.Topic;
import com.itheima.utils.HibernateUtils;

public class TopicDaoImpl implements TopicDao {

	public void save(Topic t) {
		Session session = HibernateUtils.getCurrentSession();
		session.save(t);
	}

	public List<Topic> getAll(String key) {
		
		//1 获得session
		Session session = HibernateUtils.getCurrentSession();
		//2 创建query对象 
		Query query = session.createQuery("from Topic where title like :key ");
		//3 设置参数
		if(key!=null && !"".equals(key.trim())){//页面传条件了
			query.setString("key", "%"+key+"%");
		}else{//页面没传条件=>查询所有
			query.setString("key", "%");
		}
		//4 查询 list
		return query.list();
	}
	public Topic find(int id){
		Session session = HibernateUtils.getCurrentSession();
		return (Topic) session.get(Topic.class, id);
	}

	public void save1(Reply reply) {
		Session session = HibernateUtils.getCurrentSession();
		session.save(reply);
	}
	
	

}
