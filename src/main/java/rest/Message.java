package rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;


@Component
public class Message {
    private String content;
    private Calendar timestamp;

    public Message(String content, Calendar timestamp){
        this.content = content;
        this.timestamp = timestamp;
    }

    public Message(){}

    public String getContent(){
        return content;
    }

    @JsonIgnore
    public String getTimestamp(){
        if(timestamp != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            sdf.setCalendar(timestamp);
            return sdf.format(timestamp.getTime());
        }else{
            return null;
        }
    }
}
