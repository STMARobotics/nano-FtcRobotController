package org.firstinspires.ftc.teamcode.commands;

import java.util.Map;

public interface ConfigurableCommand extends Command{
    String getName();
    Map<String, String> getValues();
    void increment();
    void decrement();
}
