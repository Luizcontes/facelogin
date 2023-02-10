/* package com.entertours.passeio.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.node.ObjectNode;

@FeignClient("calendar-service")
public interface CalendarFeignService {
    
    @RequestMapping(
        method = RequestMethod.POST,
        value = "api/calendario/passeio/{passeioId}/create",
        consumes = "application/json"
    )
    void createPasseioRule(@RequestParam String passeioId, @RequestBody ObjectNode bookingRules);
    
    @RequestMapping(
        method = RequestMethod.GET,
        value = "api/calendario/passeio/{passeioId}/create",
        consumes = "application/json"
    )
    void getPasseioRules(@RequestParam String passeioId);
} */
