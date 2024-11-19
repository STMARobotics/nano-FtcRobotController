package org.firstinspires.ftc.teamcode.commands;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PauseCommand implements ConfigurableCommand {
    private double seconds;
    private long startTime;


    public PauseCommand(double seconds) {
        this.seconds = seconds;
    }

    @Override
    public void execute() {

    }

    @Override
    public void init() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public boolean isFinished() {
        long currentTimeMillis = System.currentTimeMillis();
        long elapsedTimMillis = currentTimeMillis - startTime;

        double elapsedTimeSeconds = (double) elapsedTimMillis /1000;
        return elapsedTimeSeconds >= this.seconds;
    }

    @Override
    public void logMessage() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public int getTimeout() {
        return (int) this.seconds + 1;
    }

    @Override
    public String getName() {
        return "Pause";
    }

    @Override
    public Map<String, String> getValues() {
        HashMap<String, String> map = new HashMap<>();
        map.put("seconds", Double.toString(seconds));
        return map;
    }

    @Override
    public void increment() {
        seconds = seconds + .25;
    }

    @Override
    public void decrement() {
        seconds = seconds - .25;
    }
}
