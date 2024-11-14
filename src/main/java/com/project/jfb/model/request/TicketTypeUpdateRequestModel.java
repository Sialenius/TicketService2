package com.project.jfb.model.request;

import com.project.jfb.io.entity.enums.TicketType;
import lombok.Data;

@Data
public class TicketTypeUpdateRequestModel {
    private TicketType newType;
}
