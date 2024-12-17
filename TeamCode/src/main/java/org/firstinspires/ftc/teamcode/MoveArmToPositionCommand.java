package org.firstinspires.ftc.teamcode;

public class MoveArmToPositionCommand implements Command{
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
        System.out.println("******************************  checking moving " + armSystem.isMoving());
        System.out.println("******************************  ARM POSITION " + armSystem.getPosition());
        System.out.println("******************************  ARM TARGET " + armSystem.getTargetPosition());
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
        System.out.println("******************************  EXECUTING ARM " + armSystem.isMoving());
        armSystem.moveToPosition(position);
    }

    @Override
    public void init(){
    }
}
