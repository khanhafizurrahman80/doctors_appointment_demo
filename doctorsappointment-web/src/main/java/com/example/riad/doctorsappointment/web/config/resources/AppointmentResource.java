package com.example.riad.doctorsappointment.web.config.resources;

import com.example.riad.doctorsappointment.data.domains.AppointmentBook;
import com.example.riad.doctorsappointment.web.controllers.AppointmentController;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Getter
public class AppointmentResource extends ResourceSupport {
    private final AppointmentBook appointmentBook;

    public AppointmentResource(AppointmentBook appointmentBook) {
        this.appointmentBook = appointmentBook;
        final long id = appointmentBook.getId();
        add(linkTo(AppointmentController.class).withSelfRel());
    }
}
