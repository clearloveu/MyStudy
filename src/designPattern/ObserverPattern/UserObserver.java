package designPattern.ObserverPattern;

import java.util.Observable;
import java.util.Observer;

/**
 * @author zg
 * @create 2020-02-21 18:56
 * <p>
 * 用户是具体观察对象
 */
public class UserObserver implements Observer{

        private String name;
        public UserObserver(String name){
            this.name = name;
        }

        @Override
        public void update(Observable o, Object arg) {
            System.out.println("微信用户："+name+"，您的微信公众号更新这些内容："+arg);
        }

}
