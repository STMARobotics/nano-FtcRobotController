/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.function.Function;


/*
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name="Config Ticks OpMode", group="Linear OpMode")
//@Disabled
public class ConfigValuesOpMode extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    DriveSubsystem driveSubsystem;
//    SlideSubsystem slideSubsystem;
//    ArmSubsystem arm;
//    WristSubsystem wrist;
//    IntakeSubsystem intake;


    int forwardAmount = 12;
    int strafeAmount = 12;
    int turnDegrees = 90;

    boolean rightBumperDown = false;
    boolean leftBumperDown = false;
    boolean aDown = false;
    boolean bDown = false;
    boolean xDown = false;
    boolean yDown = false;

    boolean dpadUpPressed = false;
    boolean dpadDownPressed = false;

    private String mode = "forward";
    private String subMode = "ticks";
    @Override
    public void runOpMode() {
        DriveSubsystem driveSubsystem = new DriveSubsystem(hardwareMap, telemetry);
        CommandFactory.InitFactory(driveSubsystem);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses START)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            rightBumperDown = toggleAndRun(gamepad1.right_bumper, rightBumperDown, this::changeMode);

            leftBumperDown = toggleAndRun(gamepad1.left_bumper, leftBumperDown, this::changeSubmode);

            dpadUpPressed = toggleAndRun(gamepad1.dpad_up, dpadUpPressed, this::increaseValue);

            dpadDownPressed = toggleAndRun(gamepad1.dpad_down, dpadDownPressed, this::decreaseValue);

            yDown = toggleAndRun(gamepad1.y, yDown, this::Forward);
            xDown = toggleAndRun(gamepad1.x, xDown, this::Strafe);
            bDown = toggleAndRun(gamepad1.b, bDown, this::Turn);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Commands", "Right Bumper: Mode, Left Bumper: Submode");
            telemetry.addData("Commands", "D-Pad Up: Increase Values, D-Pad Down: Decrease Value");
            telemetry.addData("Commands", "Y: Forward, X: Strafe Left, B: Turn Right");
            telemetry.addData("Forward Ticks Per Inch", driveSubsystem.getCountsPerInch());
            telemetry.addData("Strafe Ticks per Inch", driveSubsystem.getStrafeTicksPerInch());
            telemetry.addData("Degree Ticks per Inch", driveSubsystem.getDegreeTicks());
            telemetry.addData("Forward Ticks", driveSubsystem.getCountsPerInch());
            telemetry.addData("Strafe Ticks", driveSubsystem.getStrafeTicksPerInch());
            telemetry.addData("Turn Ticks", driveSubsystem.getDegreeTicks());
            telemetry.addData("Forward Amount", forwardAmount + " inches");
            telemetry.addData("Strafe Amount", strafeAmount + " inches");
            telemetry.addData("Turn Degrees", turnDegrees + " degrees");

            telemetry.update();
        }
    }

    private boolean toggleAndRun(boolean buttonState, boolean isPressed, Executable f) {
        if (buttonState && ! rightBumperDown)  {
            f.execute();
            return true;
        } else if (!buttonState && rightBumperDown){
             return false;
        } else {
            return isPressed;
        }
    }

    private void increaseValue() {
        changeValue(1);
    }

    private void decreaseValue() {
        changeValue(-1);
    }

    private void changeValue(int change) {
        if (mode.equalsIgnoreCase("forward")){
            if (subMode.equalsIgnoreCase("ticks")){
                double currentVal = driveSubsystem.getCountsPerInch();
                driveSubsystem.setCountsPerInch(currentVal + change);
            } else {
                forwardAmount = forwardAmount + change;
            }
        } else if (mode.equalsIgnoreCase("strafe")){
            if(subMode.equalsIgnoreCase("ticks")){
                double currentVal = driveSubsystem.getStrafeTicksPerInch();
                driveSubsystem.setStrafeTicksPerInch(currentVal + change);
            } else {
                strafeAmount = strafeAmount + change;
            }
        } else {
            if(subMode.equalsIgnoreCase("ticks")){
                double currentVal = driveSubsystem.getDegreeTicks();
                driveSubsystem.setDegreeTicks(currentVal + change);
            } else {
                turnDegrees = turnDegrees + (15 * change);
            }
        }
    }

    private void changeMode() {
        switch (mode){
            case "forward":
                mode = "strafe";
                break;
            case "strafe":
                mode = "turn";
                break;
            default:
                mode = "forward";
                break;
        }
    }

    private void changeSubmode(){
        switch (subMode){
            case "inches":
                mode = "ticks";
                break;
            case "ticks":
                mode = "inches";
                break;
            default:
                mode = "inches";
                break;
        }
    }

    private void Forward(){
        CommandFactory.Forward(forwardAmount, .5, 3);
    }
    private void Strafe(){
        CommandFactory.StrafeLeft(strafeAmount, .5, 3);
    }

    private void Turn(){
        CommandFactory.TurnRight(turnDegrees, 3);
    }
}
