package com.booking.propertyservice.controller

import EntityNotFoundException
import com.booking.propertyservice.service.propertyservice.PropertyService
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import spock.lang.Subject

class PropertyResourceSpec extends Specification {


    PropertyService propertyService

    @Subject
    PropertyResource propertyResource

    def mockMvc

    def setup() {
        propertyService = Mock(PropertyService)
        propertyResource = new PropertyResource(propertyService)
        mockMvc = MockMvcBuilders.standaloneSetup(propertyResource).build()
    }

    def "get propertyDto"() {
        when:
        def response =  mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/property/{code}", "P1"))
                .andReturn().response

        then:
        response.status == HttpStatus.OK.value()
    }
}
