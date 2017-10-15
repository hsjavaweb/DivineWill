# 1.servlet初体验
* a.写一个java类，实现servlet接口
```
//接受用户信息，并作出响应
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

# 2.Servlet生命周期
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

# 3.Servlet的三种创建方式
* 1.见servlet初体验
* 2.继承javax.servet.GenericServlet类(适配器模式)
```
public class ServletDemo2 extends GenericServlet{

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("servletDemo2");
	}

}
```
* 3.继承javax.servlet.http.HttpServlet类（模板方法设计模式）(开发中常用方式)
```
public class ServletDemo3 extends HttpServlet {

	//不要重写父类中的方法
	//以get方式提交执行doGet方法
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("------doGet--------");
		System.out.println(req.getRemoteAddr());
	}
	//以post方式提交执行doPost方法
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("------doPost--------");
	}
}

//优化，不管是get方式还是posh方式提交都执行doGet的方法
public class ServletDemo3 extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("------doGet--------");
		System.out.println(req.getRemoteAddr());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doGet(request, response)
	}
}	
```
# 4.servet映射细节
* 通配符* 代表任意字符串
* url-pattern: *.do  以*.字符串的请求都可以访问 注：不要加/
* url-pattern: /*  任意字符串都可以访问
* url-pattern： /action/* 以/action开头的请求都可以访问
* 匹配规则： 绝对匹配>/开头匹配>扩展名方式匹配

# 5.Servlet获取配置信息
* ServletConfig的使用(可以获取servlet的配置信息)
```
	//web.xml的配置
	<servlet>
		<servlet-name>ServletDemo4</servlet-name>
	    <servlet-class>com.alibaba.servlet.ServletDemo4</servlet-class>
	    <init-param>
	  		<param-name>encoding</param-name>
	  		<param-value>UTF-8</param-value>
	  	 </init-param>
		</servlet>

   * 方式一
```
```
public class ServletDemo4 extends HttpServlet {

	private ServletConfig config;
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String encoding = config.getInitParameter("enconding");
		System.out.println(encoding);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
```
   * 方式二
```
public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String encoding = this.getServletConfig().getInitParameter("enconding");
		System.out.println(encoding);
	}
```
   * 方式三
```
public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	String encoding = super.getInitParameter("encoding");
	System.out.println(encoding);
}
```

# 6.ServletContext
ServletContext: 代表的是整个应用。一个应用只有一个ServletContext对象。单实例。
* 域对象：在一定范围内（当前应用），使多个Servlet共享数据。
   * 常用方法
```
void setAttribute(String name,object value);//向ServletContext对象的map中添加数据
Object getAttribute(String name);//从ServletContext对象的map中取数据
void rmoveAttribute(String name);//根据name去移除数据

```