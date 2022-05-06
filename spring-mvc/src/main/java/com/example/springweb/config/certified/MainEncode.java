package com.example.springweb.config.certified;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test1")
@Component("certified")
public class MainEncode extends Sha512 {}
