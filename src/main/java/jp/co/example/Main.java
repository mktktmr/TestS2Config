package jp.co.example;

import org.seasar.config.core.container.ConfigContainer;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * Test for S2Config
 * @author Makoto Kitamura
 */
public class Main {
	public static void main(String[] args) {
		
		SingletonS2ContainerFactory.init();
		ConfigContainer config = SingletonS2Container.getComponent(ConfigContainer.class);
		
		// S2Configの設定やクラスパスの設定が正しければ、〜.propertiesの値が取得できます。
		System.out.println(config.getConfigValue(String.class, "sample1"));
		System.out.println(config.getConfigValue(String.class, "sample2"));
	}
}
