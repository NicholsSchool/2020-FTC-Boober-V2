package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.Robot;

@TeleOp(name="Ok Boober Teleop", group="Iterative Opmode")
public class Teleop extends OpMode {
    /**
     * Intializes the objects within the Robot class
     */
    @Override
    public void init() {
        Robot.init(hardwareMap, telemetry, gamepad1, gamepad2);
    }

    /**
     * Runs the robot movements
     */
    @Override
    public void loop() {
        Robot.run();
    }

    /**
     * Stops all Robot movements
     */
    @Override
    public void stop() {
        Robot.stop();
    }
}
