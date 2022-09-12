package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// n'est pas utilisé ? à delete ?

public class RestPreconditions {

    private static final Logger log = LoggerFactory.getLogger(RestPreconditions.class);

    public static <T> T checkFound(T resource) throws Exception {
        if (resource == null) {
            throw new Exception();
        }
        return resource;
    }
}
