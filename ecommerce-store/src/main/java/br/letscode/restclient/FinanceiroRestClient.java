package br.letscode.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(
        value = "financeiroRestClient",
        url = "https://dummyjson.com")
public interface FinanceiroRestClient {

    @RequestMapping(path="/products/categories", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    List<String> findAll();
}
