package com.key.demo.controller;

import com.key.demo.model.Foo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import java.time.Duration;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class FooController {

    @GetMapping(value = "/foo", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Foo> getFoo(){
      var interval = Flux.interval(Duration.ofSeconds(1));
      var fooGenerate = Flux.fromStream(Stream.generate(()-> Foo.builder().id(1).name("key").build()));
      return Flux.zip(interval,fooGenerate).map(Tuple2::getT2);
    }

}
