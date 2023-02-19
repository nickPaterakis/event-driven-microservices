package com.booking.propertyservice.repository

import com.booking.propertyservice.repository.propertyrepository.PropertyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import spock.lang.Specification

@DataMongoTest
class PropertyRepositorySpec extends Specification {

    @Autowired
    private PropertyRepository propertyRepository;

    def "check if the findByCode return the right result"() {
        given:
            String code = "P1"

        when:
            def property = propertyRepository.findByCode(code)

        then:
            property.code == code
    }
}
