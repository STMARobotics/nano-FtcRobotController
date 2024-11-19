package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

public abstract class DriveCommand implements ConfigurableCommand {
    protected DriveSubsystem driveSystem;
    protected int timeout;

    @Override
    public boolean isFinished() {
        return !driveSystem.isMoving();
    }

    @Override
    public void logMessage() {
        driveSystem.logPosition();
    }

    @Override
    public void onComplete() {
        System.out.println("*****************  COMPLETE ************");
        driveSystem.shutOffMotors();
    }

    @Override
    public int getTimeout() {
        return this.timeout;
    }

    @Override
    public void init(){
        driveSystem.driveWithEncoders();
    }
}
