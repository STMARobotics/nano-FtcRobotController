package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.SlideSubsystem;

import java.util.HashMap;
import java.util.Map;

public class SlideToPositionCommand implements ConfigurableCommand {
    private final SlideSubsystem slideSubsystem;
    private int position;
    private final int timeout;

    public SlideToPositionCommand(SlideSubsystem slideSubsystem, int position, int timeout) {
        this.slideSubsystem = slideSubsystem;
        this.position = position;
        this.timeout = timeout;
    }

    @Override
    public void execute() {
        slideSubsystem.setPosition(position);
    }

    @Override
    public void init() {

    }

    @Override
    public boolean isFinished() {
        return ! slideSubsystem.isMoving();
    }

    @Override
    public void logMessage() {
        slideSubsystem.addTelemetry();
    }

    @Override
    public void onComplete() {
        slideSubsystem.holdPosition();
    }

    @Override
    public int getTimeout() {
        return timeout;
    }

    @Override
    public String getName() {
        return "Slide To Position";
    }

    @Override
    public Map<String, String> getValues() {
        HashMap<String, String> map = new HashMap<>();
        map.put("position", Integer.toString(position));
        return map;
    }

    @Override
    public void increment() {
        position = position + 1;
    }

    @Override
    public void decrement() {
        position = position - 1;
    }
}
