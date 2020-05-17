import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Z2_RemoteActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                 // TODO: respond to string messages by changing them to uppercase
                .match(String.class,s->{
                    System.out.println(s);
                    getSender().tell(s.toUpperCase(),getSelf());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
