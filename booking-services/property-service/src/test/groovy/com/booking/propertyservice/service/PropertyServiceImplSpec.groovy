package com.booking.propertyservice.service

import EntityNotFoundException

import com.booking.propertyservice.repository.propertyrepository.PropertyRepository
import com.booking.propertyservice.service.propertyservice.PropertyService
import com.booking.propertyservice.service.propertyservice.PropertyServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class PropertyServiceImplSpec extends Specification {

    @Autowired
    PropertyRepository propertyRepository

    @Autowired
    PropertyService propertyService

    def "get the propertyDTO from the property with code P1"() {
        given:
        String code = "P1"

        when:
        PropertyDto propertyDto = propertyService.getPropertyByCode(code)

        then:
        propertyDto.code == code
    }

    def "when findByCode return null the getPropertyByCode should return an Exception"() {
        given:
        propertyRepository = Stub(PropertyRepository.class)
        propertyService = new PropertyServiceImpl(propertyRepository);
        propertyRepository.findByCode(_ as String) >> null

        when:
        propertyService.getPropertyByCode(_ as String)

        then:
        thrown(EntityNotFoundException)
    }

    def "findByCode should be executed only one time"() {
        given:
        String code = "P1"
        propertyRepository = Mock(PropertyRepository.class)
        propertyService = new PropertyServiceImpl(propertyRepository);

        when:
        propertyService.getPropertyByCode(code)

        then:
        thrown(EntityNotFoundException)
        1 * propertyRepository.findByCode(code)
    }
}
