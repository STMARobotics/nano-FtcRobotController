package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.HashMap;
import java.util.Map;

public class TurnLeftCommand extends DriveCommand{


    private double speed;
    private double degrees;

    public TurnLeftCommand(DriveSubsystem driveSystem, double degrees, int timeout) {
        this.speed = .5;
        this.driveSystem = driveSystem;
        this.degrees = degrees;
        this.timeout = timeout;
    }

    @Override
    public void execute() {
        driveSystem.turnLeft(speed, degrees);
    }

    @Override
    public String getName() {
        return "Turn";
    }

    @Override
    public Map<String, String> getValues() {
        HashMap<String, String> map = new HashMap<>();
        map.put("degrees", Double.toString(degrees));
        return map;
    }

    @Override
    public void increment() {
        degrees = degrees + 15;
    }

    @Override
    public void decrement() {
        degrees = degrees - 15;
    }
}
