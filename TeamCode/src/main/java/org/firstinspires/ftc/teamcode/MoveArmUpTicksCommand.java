package org.firstinspires.ftc.teamcode;

public class MoveArmUpTicksCommand implements Command{
    private final ArmSubsystem armSystem;
    private final int timeout;
    private final int ticks;


    public MoveArmUpTicksCommand(ArmSubsystem armSystem, int ticks, int timeout) {
        this.armSystem = armSystem;
        this.ticks = ticks;
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
        armSystem.moveUpTicks(ticks);
    }

    @Override
    public void init(){
    }
}
