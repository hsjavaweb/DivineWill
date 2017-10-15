# servlet初体验
* a.写一个java类，实现servlet接口
```
-- 接受用户信息，并作出响应
public void service(ServletRequest arg0, ServletResponse arg1) 
		throws ServletException, IOException {
	System.out.println("service");
}
```
* b.修改web.xml文件，给servlet提供一个可访问的URI地址
```
 <servlet>
    <servlet-name>ServletDemo1</servlet-name>
    <servlet-class>com.alibaba.servlet.ServletDemo1</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>ServletDemo1</servlet-name>
    <url-pattern>/servletDemo1</url-pattern>
  </servlet-mapping>
```

* c.将web项目部署到服务器上，开启服务器，在浏览器上输入http://localhost:8080/Servlet/servletDemo1，访问服务器，控制台输出：service

# Servlet生命周期
* 实例化-->初始化-->服务->销毁
 *  出生：（实例化-->初始化）第一次访问Servlet就出生（默认情况下）
 *  活着：（服务）应用活着，servlet就活着
 *  死亡：（销毁）应用卸载了servlet就销毁
```

public class ServletDemo1 implements Servlet{

	//实例化：第一次被访问时调用
	public ServletDemo1() {
		System.out.println("----------Servlet别调用了！------------");
	}

	//初始化方法:第一次被访问时调用
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("----------init被调用了！----------");
	}

	//服务方法：接受用户请求，并作出响应，每次请求都调用
	public void service(ServletRequest arg0, ServletResponse arg1) 
			throws ServletException, IOException {
		System.out.println("----------service被调用了！------------");
	}
	//销毁方法:应用被卸载时调用(如服务器关闭)
	public void destroy() {
		System.out.println("------------destroy被调用了------------");
	}
}
```
![图片](https://github.com/XCgratitude/test/raw/master/imge/1.jpg)
