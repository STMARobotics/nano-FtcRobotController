package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

import java.util.HashMap;
import java.util.Map;

public class MoveArmToPositionCommand implements ConfigurableCommand {
    private ArmSubsystem armSystem;
    private int timeout;

    private int position;


    public MoveArmToPositionCommand(ArmSubsystem armSystem, int position, int timeout) {
        this.armSystem = armSystem;
        this.position = position;
        this.timeout = timeout;
    }

    @Override
    public boolean isFinished() {
        return !armSystem.isMoving();
    }

    @Override
    public void logMessage() {
        armSystem.addTelemetry();
    }

    @Override
    public void onComplete() {
        System.out.println("*****************  COMPLETE ************");
        armSystem.holdPosition();
    }

    @Override
    public int getTimeout() {
        return this.timeout;
    }

    @Override
    public void execute() {
        armSystem.setArmPosition(position);
    }

    @Override
    public void init(){
    }

    @Override
    public String getName() {
        return "Move Arm To Position";
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
