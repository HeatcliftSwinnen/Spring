package be.technifutur.spring.demo.other;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DemoCDI {
    private final DemoIOC dependance;
    private final Scanner scanner ;

    public DemoCDI(
            DemoIOC dependance,
            Scanner scanner
            ) {
        this.dependance = dependance;
        this.scanner= scanner ;

        System.out.println("instanciation de DemoCDI. dependence "+ dependance);
    }
}
