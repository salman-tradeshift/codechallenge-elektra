package rest;

/**
 * Created by ksp on 22/06/15.
 */
public class ResponseMessage {
    public Message message;

    public void setMessage(Message message){
        this.message = message;
    }

    public Message getMessage(){
        return message;
    }
}
