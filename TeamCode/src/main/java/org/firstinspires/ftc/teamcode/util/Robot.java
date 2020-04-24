package org.firstinspires.ftc.teamcode.util;

import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.CapstoneDropper;
import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Gripper;
import org.firstinspires.ftc.teamcode.subsystems.Lights;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.subsystems.TapeExtender;

import java.util.HashMap;

/**
 * Used to store and run all Subsystems and Sensors within the Robot
 */
public class Robot extends RobotBase {


    public Gripper gripper;
    public DriveTrain driveTrain;
    public Arm arm;
    public CapstoneDropper capstoneDropper;
    public TapeExtender tapeExtender;

    public Lights lights;

    public Robot(HardwareMap hardwareMap, Telemetry t, Gamepad gamepad1, Gamepad gamepad2, LinearOpMode opMode) {
        super(hardwareMap, t, gamepad1, gamepad2, opMode);
    }
    public Robot(HardwareMap hardwareMap, Telemetry t, Gamepad gamepad1, Gamepad gamepad2) {
        super(hardwareMap, t, gamepad1, gamepad2);
    }

    @Override
    public void initSubsystems() {
        gripper = new Gripper();
        driveTrain = new DriveTrain();
        arm = new Arm();
        capstoneDropper = new CapstoneDropper();
        tapeExtender = new TapeExtender();
        lights = new Lights();
    }
}
