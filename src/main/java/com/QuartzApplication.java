package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@MapperScan("com.pbs.job.**.mapper")
@EnableTransactionManagement
//@EnableScheduling//开启定时器
@SpringBootApplication
public class QuartzApplication {

    @PostConstruct
    void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        //TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        //TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
        System.out.println("____________  _____  _____ _   _  ___  ______ _____ ______");
        System.out.println("| ___ \\ ___ \\/  ___||  _  | | | |/ _ \\ | ___ \\_   _|___  /");
        System.out.println("| |_/ / |_/ /\\ `--. | | | | | | / /_\\ \\| |_/ / | |    / /");
        System.out.println("|  __/| ___ \\ `--. \\| | | | | | |  _  ||    /  | |   / /");
        System.out.println("| |   | |_/ //\\__/ /\\ \\/' / |_| | | | || |\\ \\  | | ./ /___");
        System.out.println("\\_|   \\____/ \\____/  \\_/\\_\\\\___/\\_| |_/\\_| \\_| \\_/ \\_____/ ");
        System.out.println("============================QuartzApplication====start successfully===================================");

    }

}
