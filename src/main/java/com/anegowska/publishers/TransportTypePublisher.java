package com.anegowska.publishers;

import com.anegowska.model.enums.Transport;

import javax.ejb.Stateless;
import java.util.EnumSet;
import java.util.Map;

@Stateless
public class TransportTypePublisher {

    public void publishAllTransportTypes(Map<String, Object> model) {

        EnumSet<Transport> transportTypes = EnumSet.allOf(Transport.class);
        model.put("transportTypes", transportTypes);
    }
}
