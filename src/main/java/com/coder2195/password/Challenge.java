package com.coder2195.password;

@FunctionalInterface
public interface Challenge {
    boolean execute(String password);  
}
