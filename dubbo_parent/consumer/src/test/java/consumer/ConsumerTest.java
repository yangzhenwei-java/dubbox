package consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.RpcContext;
import com.github.dto.StudentDTO;
import com.github.dto.UserDTO;
import com.github.listener.CallBackListener;
import com.github.rest.StudentService;
import com.github.service.AnimalService;
import com.github.service.UserService;

public class ConsumerTest {
	/**
	 * 无注册中心,快速启动,体验dubbo
	 */
	@Test
	public void quickStart() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"quickstart_consumer.xml");
		context.start();
		UserService service = (UserService) context.getBean("userService");
		System.err.println(service.get(1));
	}

	/**
	 * 常规体验，通过注册中心实现
	 */
	@Test
	public void common() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"common_consumer.xml");
		context.start();
		UserService service = (UserService) context.getBean("userService");
		System.err.println(service.get(1));
	}


	/**
	 * 服务分组
	 */
	@Test
	public void group() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"group_consumer.xml");
		context.start();
		AnimalService service = (AnimalService) context
				.getBean("animalService");
		System.err.println(service.eat());
	}

	/**
	 * 需要查看参照管理控制台
	 */
	@Test
	public void diffversions() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"diffversions_consumer.xml");
		context.start();
		UserService service1 = (UserService) context.getBean("userService_1");
		UserService service2 = (UserService) context.getBean("userService_2");
		UserService service3 = (UserService) context.getBean("userService_3");
		System.err.println(service1.get(1));
		RpcContext context1 = RpcContext.getContext();
		System.out.println(context1.getUrl());
		System.err.println(service2.get(2));
		RpcContext context2 = RpcContext.getContext();
		System.out.println(context2.getUrl());
		System.err.println(service3.get(3));
		RpcContext context3 = RpcContext.getContext();
		System.out.println(context3.getUrl());
	}

	/**
	 * 异步调用
	 *
	 * @throws ExecutionException
	 * @throws InterruptedException
	 *
	 */
	@Test
	public void asynCall() throws InterruptedException, ExecutionException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"asyncall_consumer.xml");
		context.start();
		UserService userService = (UserService) context.getBean("userService");
		AnimalService animalService = (AnimalService) context
				.getBean("animalService");

		UserDTO dto = userService.get(1);
		/* 此调用会立即返回null */
		Assert.assertNull(dto);
		/* 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future。 */
		Future<UserDTO> dtoFuture = RpcContext.getContext().getFuture();
		String eat = animalService.eat();
		/* 拿到调用的Future引用，当结果返回后，会被通知和设置到此Future。 */
		Future<String> eatFuture = RpcContext.getContext().getFuture();
		/* 此调用会立即返回null */
		Assert.assertNull(eat);
		/*
		 * 此时get(1)和eat()的请求同时在执行，客户端不需要启动多线程来支持并行，而是借助NIO的非阻塞完成。
		 * 如果userDTO已返回，直接拿到返回值，否则线程wait住，等待userDTO返回后，线程会被notify唤醒。
		 */
		UserDTO userDTO = dtoFuture.get();
		/* 同理等待eatStr返回。 */
		String eatStr = eatFuture.get();
		/* 如果userDTO需要5秒返回，eatStr需要6秒返回，实际只需等6秒，即可获取到userDTO和eatStr，进行接下来的处理。 */
		System.out.println(userDTO);
		System.out.println(eatStr);
	}

	/**
	 * 参数回调
	 *
	 * @throws Exception
	 */
	@Test
	public void paramCallback() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"paramcallback_consumer.xml");
		context.start();
		UserService userService = (UserService) context.getBean("userService");
		userService.batchAddUsers(new CallBackListener() {
			@Override
			public void changed(String msg) {
				System.out.println("进行到:" + msg);
			}
		}, new ArrayList<UserDTO>());
		System.in.read();
	}

	/**
	 * 集群容错
	 */
	@Test
	public void clusterFail() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"clusterfail_consumer.xml");
		context.start();
		UserService service = (UserService) context.getBean("userService");
		System.err.println(service.get(1));
	}

	/**
	 * 负载均衡
	 */
	@Test
	public void loadBalance() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"loadbalance_consumer.xml");
		context.start();
		UserService service = (UserService) context.getBean("userService");
		for (int i = 0; i < 50; i++) {
			System.err.println(service.loadbalance());
		}
	}

	/**
	 * 事件通知
	 *
	 * @throws IOException
	 */
	@Test
	public void eventNotice() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"eventnotice_consumer.xml");
		context.start();
		UserService service = (UserService) context.getBean("userService");
		/* System.err.println(service.get(1)); */
		System.err.println(service.get(100000));
		System.in.read();
	}


	/**
	 *  调用API
	 */
	@Test
	public void callingAPIServer(){


		// 当前应用配置
		ApplicationConfig application = new ApplicationConfig();
		application.setName("calling_api_demo");
		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://server1:2181");


		// 注意：ReferenceConfig，内部封装了与注册中心的连接，以及开启服务端口


		// 引用远程服务
		ReferenceConfig<UserService> reference = new ReferenceConfig<UserService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
		reference.setApplication(application);
		reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
		reference.setInterface(UserService.class);
		reference.setVersion("1.0.0");


		// 和本地bean一样使用xxxService
		UserService userService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
		System.err.println(userService.get(1));

	}
	
	
	/**
	 * 调用rest(一)
	 * @throws IOException 
	 */
	@Test
	public void restful1() throws IOException{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"rest_consumer.xml");
		StudentDTO dto = new StudentDTO();
		dto.setName("zhangsan");
		dto.setStuNo("123456");
		context.start();
		StudentService service = (StudentService) context.getBean("studentService");
		service.registerUser(dto);
		System.in.read();
	}
	
	
	/**
	 * 调用rest(二)
	 */
	@Test
	public void restful2(){
		StudentDTO dto = new StudentDTO();
		dto.setName("zhangsan");
		dto.setStuNo("123456");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://172.16.153.156:30000/service/student/register");
        Response response = target.request().post(Entity.entity(dto, MediaType.APPLICATION_JSON_TYPE));
        try {
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
            }
            System.out.println("Successfully got result: " + response.readEntity(String.class));
        } finally {
            response.close();
            client.close();
        }
	}

}
