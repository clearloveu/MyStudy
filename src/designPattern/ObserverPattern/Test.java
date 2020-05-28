package designPattern.ObserverPattern;

/**
 * @author zg
 * @create 2020-02-21 19:04
 */

public class Test {
        public static void main(String[] args) {
            //被观察的角色
            JijingObservable jijingObservable = new JijingObservable();
            //观察者
            UserObserver userBob = new UserObserver("WeChat User-Bob");
            UserObserver userTom = new UserObserver("WeChat User-Tom");
            UserObserver userMe = new UserObserver("WeChat User-Me");
            //将观察者注册到可观察对象的观察者列表中
            jijingObservable.addObserver(userBob);
            jijingObservable.addObserver(userTom);
            jijingObservable.addObserver(userMe);
            //发布消息
            jijingObservable.publishNewInfo("...新内容...");
            jijingObservable.deleteObserver(userMe);
            System.out.println("##########################################");
            jijingObservable.publishNewInfo("...新内容123...");
        }
}
