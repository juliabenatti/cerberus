package br.letscode.endpoints;

import br.letscode.models.Cliente;
import br.letscode.services.HelloService;
import br.letscode.services.impl.HelloServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloEndpoints {

    @Autowired
    HelloService helloService;

    Logger log = LoggerFactory.getLogger(HelloEndpoints.class);

    @RequestMapping(path="/hello/{nome}", method = RequestMethod.GET)
    public ResponseEntity hello() {
        log.info("Insercao feita com sucesso");
        return new ResponseEntity("Hello world!", HttpStatus.OK);
    }
}
