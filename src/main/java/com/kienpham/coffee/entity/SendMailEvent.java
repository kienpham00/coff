package com.kienpham.coffee.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Builder
@Data
public class SendMailEvent extends ApplicationEvent {
    public SendMailEvent(Object source, Object data) {
        super(source);
        this.data = data;
    }
    public SendMailEvent(Object source) {
        super(source);
    }
    private Object data;
}
