package br.letscode.endpoints;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloEndpoints {

    @RequestMapping(path="/hello", method = RequestMethod.GET)
    public ResponseEntity hello() {

        return new ResponseEntity("Hello world!", HttpStatus.OK);
    }
}
