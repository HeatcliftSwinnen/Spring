package be.technifutur.spring.demo.controllers;

import be.technifutur.spring.demo.services.MessageService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DemoController {

    private final MessageService messageService;

    public DemoController(MessageService messageService) {
        this.messageService = messageService;
    }

    //get - http://localhost:8080/hello

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello world";
    }

    @GetMapping("/addition")
    public Integer add(@RequestParam("memberA") int a, @RequestParam("memberB") int b) {
       return a+b;
    }

    //attention la variable message met à mal le concepte de STATELESSNESS
    @GetMapping("/message/last")
    @ApiResponse(
            description = "Retourne la valeur de la variable message",
            responseCode = "200"
    )
    public ResponseEntity<String> getLastMessage(){
        String body = messageService.getLastMessage();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("headerName","headerValue");
        HttpStatus status = HttpStatus.I_AM_A_TEAPOT;
//        return new ResponseEntity<>(messageService.getLastMessage(),HttpStatus.OK);
        return ResponseEntity.status(418).header("headerName","headerValue1","headerValue2").body(body);
    }

    //permet de poster sur plusieurs URI
    @PostMapping({"/message","/message/add"})
    public void addMessage (@RequestBody String toAdd){
      messageService.addMessage(toAdd);
    }

    //{pathVar:regex} :
    // pathVar : permet de recouper l'info dans le segment de l'URI (à utiliser avec @PathVariable)
    // - regex : permet de n'accepter la requetes que si la pathVar correspond au regex

    @DeleteMapping("/message/{index:[0-9]+}")
    public void deleteMessage(@PathVariable("index") int i){
        messageService.deleteMEssage(i);
    }
    @PutMapping("/message/{index:[0-9]+}")

    public void changeMessage(@PathVariable("index") int i,@RequestBody String toModify){
        messageService.changeMessage(i,toModify);
    }

    @GetMapping("/message/{index:[0-9]+}")
    public String getMessage(@PathVariable("index") int i){
        return messageService.getMessage(i);
    }
    @GetMapping({"/messages","/message/all"})
    public List<String> getMessages(){
        return messageService.getMessages();
    }
    @RequestMapping(method =RequestMethod.HEAD,path="/test/header")
    public void testGetHeader(@RequestHeader String testHeader){
        System.out.println(testHeader);
    }
}
