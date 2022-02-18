
package homework3;
import java.util.Date;

public class Message {
    private String messageContent;
    private Date time;
    private boolean seen;
    private User sender;
    private User receiver;

    public Message(String messageContent, User sender, User receiver){
        this.messageContent=messageContent;
        this.sender=sender;
        this.receiver=receiver;
        time=new Date();
        this.seen=false;
    }
    public boolean sendMessage(){
        return this.receiver.receiverMessage(this);
    }

    public void setSeen(){

        this.seen=true;
    }
    public String getMessageContent(){
        return this.messageContent;
    }
    public User getSender(){
        return this.sender;
    }
}
