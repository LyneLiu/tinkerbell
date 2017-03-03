package org.mauritius.controller.tinkerbell;

import org.mauritius.domain.tinkerbell.Owner;
import org.mauritius.repository.tinkerbell.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @PathVariable vs @RequestParam:
 * http://stackoverflow.com/questions/13715811/requestparam-vs-pathvariable
 *
 * Created by nn_liu on 2017/3/2.
 */
@RestController
public class HomeController {

    @Autowired
    private OwnerRepository ownerRepository;

    @RequestMapping(value = "owner_info", method = RequestMethod.GET)
    public List<Owner> findOwnerInfo() {
        return ownerRepository.findAll();
    }

    @RequestMapping(value = "owner_info/{id}", method = RequestMethod.GET)
    public Owner findOwnerById(@PathVariable("id") Integer id) {
        return ownerRepository.findByOwnerId(id);
    }
}