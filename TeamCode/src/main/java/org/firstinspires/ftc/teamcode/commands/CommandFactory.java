package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DistanceSensorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SlideSubsystem;

public class CommandFactory {
    private static CommandFactory commandFactory;
    private final DriveSubsystem driveSubsystem;
    private final DistanceSensorSubsystem distanceSensor;
    private final ArmSubsystem arm;
    private final SlideSubsystem slideSubsystem;


    public enum Commands {
        FORWARD,
        BACKWARD,
        STRAFE_LEFT,
        STRAFE_RIGHT,
        TURN_LEFT,
        TURN_RIGHT,
        PAUSE,
        MOVE_TO_PICKUP,
        MOVE_ARM_TO_POSITION,
        RESET_ARM,
        MOVE_SLIDE_TO_POSITION
    }
    private CommandFactory(DriveSubsystem driveSubsystem, DistanceSensorSubsystem distanceSensor, ArmSubsystem arm, SlideSubsystem slideSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.distanceSensor = distanceSensor;
        this.arm = arm;
        this.slideSubsystem = slideSubsystem;
    }

    public static ConfigurableCommand CreateDefaultCommand(Commands commandType){
        switch (commandType){
            case FORWARD:
                return new ForwardCommand(commandFactory.driveSubsystem, 12, .5, 3);
                break;
            case BACKWARD:
                return new ForwardCommand(commandFactory.driveSubsystem, 12, -.5, 3);
                break;
            case STRAFE_LEFT:
                return new StrafeLeftCommand(commandFactory.driveSubsystem, 12, .5, 3);
                break;
            case STRAFE_RIGHT:
                return new StrafeLeftCommand(commandFactory.driveSubsystem, 12, -.5, 3);
                break;
            case TURN_LEFT:
                return new TurnLeftCommand(commandFactory.driveSubsystem, 45, 3);
                break;
            case TURN_RIGHT:
                return new TurnLeftCommand(commandFactory.driveSubsystem, -45, 3);
                break;
            case PAUSE:
                return new PauseCommand(.25);
                break;
            case MOVE_TO_PICKUP:
                return new MoveToPickupCommand(commandFactory.distanceSensor);
                break;
            case MOVE_ARM_TO_POSITION:
                return new MoveArmToPositionCommand(commandFactory.arm, 10, 3);
                break;
            case RESET_ARM:
                return new ResetArmCommand(commandFactory.arm);
                break;
            case MOVE_SLIDE_TO_POSITION:
                return new SlideToPositionCommand(commandFactory.slideSubsystem, 10, 3);
                break;
        }
    }

    public static void InitFactory(DriveSubsystem driveSubSystem, DistanceSensorSubsystem distanceSensor, ArmSubsystem arm, SlideSubsystem slideSubsystem) {
        commandFactory = new CommandFactory(driveSubSystem, distanceSensor, arm, slideSubsystem);
    }


    public static Command Forward(double inchesForward, double speed, int timeout) {
        return new ForwardCommand(commandFactory.driveSubsystem, inchesForward, speed, timeout);
    }

    public static Command Backward(double inchesBackward, double speed, int timeout) {
        return new ForwardCommand(commandFactory.driveSubsystem, -inchesBackward, speed, timeout);
    }

    public static Command StrafeLeft(double inchesLeft, double speed, int timeout) {
        return new StrafeLeftCommand(commandFactory.driveSubsystem, inchesLeft, speed, timeout);
    }

    public static Command StrafeRight(double inchesRight, double speed, int timeout) {
        return new StrafeLeftCommand(commandFactory.driveSubsystem, -inchesRight, speed, timeout);
    }

    public static Command TurnLeft(double degrees, int timeout) {
        return new TurnLeftCommand(commandFactory.driveSubsystem, degrees, timeout);
    }

    public static Command TurnRight(double degrees, int timeout) {
        return new TurnLeftCommand(commandFactory.driveSubsystem, -degrees, timeout);
    }

    public static Command Pause(double seconds) {
        return new PauseCommand(seconds);
    }

    public static Command MoveToPickup() {
        return new MoveToPickupCommand(commandFactory.distanceSensor);
    }

    public static Command MoveArmToPosition(int position, int timeout){
        return new MoveArmToPositionCommand(commandFactory.arm, position, timeout);
    }

    public static Command ResetArmPosition(){
        return new ResetArmCommand(commandFactory.arm);
    }

    public static Command MoveSlideToPosition(int position, int timeout){
        return new SlideToPositionCommand(commandFactory.slideSubsystem, position, timeout);
    }
}
