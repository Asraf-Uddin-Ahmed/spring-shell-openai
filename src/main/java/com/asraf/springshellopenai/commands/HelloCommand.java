package com.asraf.springshellopenai.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class HelloCommand {

    @ShellMethod(key = "hello", value = "Say hello")
    public String hello(@ShellOption(defaultValue = "World") String name) {
        return "Hello " + name + "!";
    }

    @ShellMethod("Say bye")
    public String goodbye(@ShellOption(defaultValue = "World") String name) {
        return "Goodbye " + name + "!";
    }

}
