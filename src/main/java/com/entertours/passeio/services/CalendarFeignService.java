package com.entertours.passeio.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.entertours.passeio.models.Day;

@FeignClient("calendar-service")
public interface CalendarFeignService {
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/calendario",
        consumes = "application/json"
    )
    Day getDays();
}
