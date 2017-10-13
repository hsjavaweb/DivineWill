package com.alibaba.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import org.junit.Test;

import com.alibaba.Bean.User;

public class Test4 {

	@Test
	public void login() throws Exception{
		//模拟登录功能
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入用户名！");
		String username = sc.nextLine();
		System.out.println("请输入密码！");
		String password = sc.nextLine();
		//1.注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//2.创建连接
		Connection conn = DriverManager.getConnection("jdbc:mysql:///yideng","root","root");
		//3.创建操作数据的对象
		Statement stmt = conn.createStatement();
		//4.执行sql语句
		String sql = "select * from users where username='"+username+"' and password='"+password+"'";
		//5.返回结果集
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			System.out.println("欢迎您:"+username);
		}else{
			System.out.println("用户名或密码错误！");
		}
		//关闭资源
		conn.close();
		stmt.close();
		rs.close();
	}
}
