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
public class Robot {

    public static LinearOpMode opMode;
    public static String filePath;
    public static String fileName;
    public static Telemetry telemetry;
    public static Gamepad g1, g2;
    public static HardwareMap hw;

    public static Gripper gripper;
    public static DriveTrain driveTrain;
    public static Arm arm;
    public static CapstoneDropper capstoneDropper;
    public static TapeExtender tapeExtender;

    public static Lights lights;
    private static HashMap<String, Subsystem> subsystems;

    /**
     * Instantiates the RobotMap and all our Subsystem and Sensor classes used by the Robot
     * @param hardwareMap - the HardwareMap created by the OpMode
     * @param t - the Telemetry created by the OpMode
     * @param gamepad1 - The gamepad1 created by the OpMode
     * @param gamepad2 - The gamepad2 created by the OpMode
     */
    public static void init(HardwareMap hardwareMap, Telemetry t, Gamepad gamepad1, Gamepad gamepad2)
    {

        telemetry = t;
        g1 = gamepad1;
        g2 = gamepad2;
        hw = hardwareMap;

        filePath = Environment.getExternalStorageDirectory().getPath();
        fileName = "SimpleBuildSide";

        gripper = new Gripper();
        driveTrain = new DriveTrain();
        arm = new Arm();
        capstoneDropper = new CapstoneDropper();
        tapeExtender = new TapeExtender();
        lights = new Lights();
    }


    /**
     * Instantiates the RobotMap and all our Subsystem and Sensor classes used by the Robot, along
     * with storing the LinearOpMode for autos
     * @param hw - the HardwareMap created by the OpMode
     * @param t - the Telemetry created by the OpMode
     * @param g1 - The gamepad1 created by the OpMode
     * @param g2 - The gamepad2 created by the OpMode
     * @param mode - the LinearOpMode for autos
     */
    public static void init(HardwareMap hw, Telemetry t, Gamepad g1, Gamepad g2, LinearOpMode mode)
    {
        init(hw, t, g1, g2);
        opMode = mode;
    }

    /**
     * Puts the inputted Subsystem in the storage HashMap, with the subsystem's name as the key.
     * @param s - the Subsystem to add to the storage HashMap
     */
    public static void registerSubsystem(Subsystem s)
    {
        if(subsystems == null)
            subsystems = new HashMap<>();
        subsystems.put(s.getName(), s);
    }

    /**
     * Returns the storage HashMap of Subsystems
     * @return the storage HashMap of Subsystems
     */
    public static HashMap<String, Subsystem> getSubsystems()
    {
        return subsystems;
    }

    /**
     * Loops through all Subsystems and runs them
     */
    public static void run()
    {
        for(Subsystem s : subsystems.values())
            s.run();
    }

    /**
     * Loops through all Subsystems and stops them
     */
    public static void stop()
    {
        for(Subsystem s : subsystems.values())
            s.stop();
    }

}
