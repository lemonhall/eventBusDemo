import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created by lemonhall on 15/7/23.
 */
public class hello {

    public static class TestEvent {
        private final int message;
        public TestEvent(int message) {
            this.message = message;
            System.out.println("event message:"+message);
        }
        public int getMessage() {
            return message;
        }
    }

    public static class EventListener1 {
        public int lastMessage = 0;

        @Subscribe
        public void listen(TestEvent event) {
            lastMessage = event.getMessage();
            System.out.println("Message C1:"+lastMessage);
        }

        public int getLastMessage() {
            return lastMessage;
        }
    }

    public static class EventListener2 {
        public int lastMessage = 0;

        @Subscribe
        public void listen(TestEvent event) {
            lastMessage = event.getMessage();
            System.out.println("Message C2:"+lastMessage);
        }

        public int getLastMessage() {
            return lastMessage;
        }
    }


    public static void main(String[] args){

        EventBus eventBus = new EventBus("test");
        EventListener1 listener1 = new EventListener1();
        EventListener2 listener2 = new EventListener2();

        eventBus.register(listener1);
        eventBus.register(listener2);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        //        event message:200
        //        Message C1:200
        //        Message C2:200
        //        event message:300
        //        Message C1:300
        //        Message C2:300
        //        event message:400
        //        Message C1:400
        //        Message C2:400

    }
}
