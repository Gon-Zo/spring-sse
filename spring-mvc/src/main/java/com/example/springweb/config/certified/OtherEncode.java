package com.example.springweb.config.certified;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("B")
@Component("certified")
public class OtherEncode extends Sha256 {}
