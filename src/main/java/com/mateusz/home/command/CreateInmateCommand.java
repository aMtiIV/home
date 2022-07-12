package com.mateusz.home.command;

import lombok.Value;

@Value
public class CreateInmateCommand {
    String name;
    String surname;
}
