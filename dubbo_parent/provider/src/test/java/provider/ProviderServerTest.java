package provider;

import java.io.IOException;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.github.impl.UserServiceImpl;
import com.github.service.UserService;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author yzw 利用测试类来模拟服务器 启动服务
 *
 */
public class ProviderServerTest {

	/**
	 * 无注册中心，快速启动服务
	 * 
	 * @throws IOException
	 */

	@Test
	public void quitStartServer() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"quickstart_provider.xml");
		context.start();

		// 阻塞线程退出

		System.in.read();
	}

	/**
	 * 常规体验，通过注册中心实现 启动服务
	 */
	@Test
	public void commonServer() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"common_provider.xml");
		context.start();

		// 阻塞线程退出

		System.in.read();
	}

	/**
	 * 不同分组的服务
	 * 
	 * @throws IOException
	 */
	@Test
	public void groupServer() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"group_provider.xml");
		context.start();

		System.in.read();
	}

	/**
	 * 多版本服务
	 * 
	 * @throws IOException
	 */
	@Test
	public void diffVersionsServer() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"diffversions_provider.xml");
		context.start();

		System.in.read();
	}

	/**
	 * 异步调用
	 * 
	 * @throws IOException
	 */
	@Test
	public void asynCallServer() throws IOException {
		System.out.println("............");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"asyncall_provider.xml");
		context.start();

		System.in.read();
	}

	/**
	 * 参数回调
	 * 
	 * @throws IOException
	 */
	@Test
	public void paramCallbackServer() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"paramcallback_provider.xml");
		context.start();

		System.in.read();
	}

	/**
	 * 集群容错 服务一
	 * 
	 * @throws IOException
	 */
	@Test
	public void clusterfailServer1() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"clusterfail_provider1.xml");
		context.start();

		System.err.println("clusterfailServer1 启动。。");
		System.in.read();
	}

	/**
	 * 集群容错 服务二
	 * 
	 * @throws IOException
	 */
	@Test
	public void clusterfailServer2() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"clusterfail_provider2.xml");
		context.start();

		System.in.read();
	}

	/**
	 * 负载均衡 服务一
	 * 
	 * @throws IOException
	 */
	@Test
	public void loadbalanceServer1() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"loadbalance_provider1.xml");
		context.start();

		System.in.read();
	}

	/**
	 * 负载均衡 服务二
	 * 
	 * @throws IOException
	 */
	@Test
	public void loadbalanceServer2() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"loadbalance_provider2.xml");
		context.start();

		System.in.read();
	}

	/**
	 * 负载均衡 服务三
	 * 
	 * @throws IOException
	 */
	@Test
	public void loadbalanceServer3() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"loadbalance_provider3.xml");
		context.start();

		System.in.read();
	}

	/**
	 * 事件通知
	 * 
	 * @throws IOException
	 */
	@Test
	public void eventNoticeServer() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"eventnotice_provider.xml");
		context.start();

		System.in.read();
	}

	// /**
	// * 运行所有测试类,看监控系统
	// * @param args
	// */
	// public static void main(String[] args) {
	// TestSuite suite = new TestSuite();
	// suite.addTest(new ProviderServerTest());
	// // suite.tests();
	// junit.textui.TestRunner.run(suite);
	// }

	/**
	 *  提供API服务
	 */
	@Test
	public void callingAPIServer() throws IOException {

		UserService userService = new UserServiceImpl();
		// 当前应用配置
		ApplicationConfig application = new ApplicationConfig();
		application.setName("calling_api_provider_demo");
		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://server1:2181");

		// 服务提供者协议配置
		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		protocol.setPort(20991);
		protocol.setThreads(200);

		// 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

		// 服务提供者暴露服务配置
		ServiceConfig<UserService> service = new ServiceConfig<UserService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
		service.setApplication(application);
		service.setRegistry(registry); // 多个注册中心可以用setRegistries()
		service.setProtocol(protocol); // 多个协议可以用setProtocols()
		service.setInterface(UserService.class);
		service.setRef(userService);
		service.setVersion("1.0.0");


		//暴露服务
		service.export();
		System.in.read();
	}
	
	
	/**
	 * rest风格
	 * 
	 * @throws IOException
	 */
	@Test
	public void restServer() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"rest_provider.xml");
		context.start();

		System.in.read();
	}
	
}
