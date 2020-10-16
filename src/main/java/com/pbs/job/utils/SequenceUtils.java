package com.pbs.job.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class SequenceUtils {

    public String getUUID(){
        UUID uuid = UUID.randomUUID();
        String res = uuid.toString().replaceAll("-","");
        return res;
    }

}
