package com.chency.spring.aop.advisor;

import com.chency.spring.aop.proxy.EchoService;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

import java.lang.reflect.Method;

/**
 * {@link IntroductionAdvisor} 示例
 *
 * @author cchangy
 * @date 2023/08/29 20:58
 */
public class IntroductionAdvisorDemo implements EchoService, Comparable<IntroductionAdvisorDemo> {

    public static void main(String[] args) {
        IntroductionAdvisorDemo target = new IntroductionAdvisorDemo();
        // 使用该构造器会使得 IntroductionInfo失效
        // ProxyFactory proxyFactory = new ProxyFactory(target);

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);

        // 添加 IntroductionAdvisor
        proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("BeforeAdvice: " + method);
            }
        }, new IntroductionInfo() {
            @Override
            public Class<?>[] getInterfaces() {
                // 此处指定的 interface 最好不要超过 target 的实现范围
                return new Class[]{EchoService.class, Comparable.class};
            }
        }));

        Object proxy = proxyFactory.getProxy();
        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello, World");

        Comparable comparable = (Comparable) proxy;
        comparable.compareTo(null);
    }

    @Override
    public String echo(String message) throws NullPointerException {
        return "IntroductionAdvisorDemo: " + message;
    }

    @Override
    public int compareTo(IntroductionAdvisorDemo o) {
        return 0;
    }
}
