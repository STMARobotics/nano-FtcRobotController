package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutonomousDrivingCommand {
    private final LinearOpMode opMode;
    private final DriveSubSystem driveSystem;

    public AutonomousDrivingCommand(LinearOpMode opMode, DriveSubSystem driveSubSystem) {
        this.opMode = opMode;
        this.driveSystem = driveSubSystem;
    }

    public void driveForward(double speed, double distanceInInches, int timeoutSeconds) {
        ElapsedTime runtime = new ElapsedTime();
        driveSystem.driveWithEncoders();

        driveSystem.driveForward(speed, distanceInInches);

        waitUntilDone(timeoutSeconds, runtime);
    }

    public void strafeLeft(double speed, double distanceInInches, int timeoutSeconds) {
        ElapsedTime runtime = new ElapsedTime();
        driveSystem.driveWithEncoders();

        driveSystem.strafeLeft(speed, distanceInInches);

        waitUntilDone(timeoutSeconds, runtime);
    }

    public void turnLeft(double speed, double degrees, int timeoutSeconds) {
        ElapsedTime runtime = new ElapsedTime();
        driveSystem.driveWithEncoders();

        driveSystem.turnLeft(speed, degrees);

        waitUntilDone(timeoutSeconds, runtime);
    }

    private void waitUntilDone(int timeoutSeconds, ElapsedTime runtime) {
        while (opMode.opModeIsActive() &&
                (runtime.seconds() < timeoutSeconds) && driveSystem.isMoving()){
            driveSystem.logPosition();
        }
    }
}