package org.firstinspires.ftc.teamcode.util;

import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

import java.util.HashMap;

public abstract class RobotBase {

    private static RobotBase instance;
    private static HashMap<String, Subsystem> subsystems;

    public static Telemetry telemetry;
    public static Gamepad g1, g2;
    public static HardwareMap hw;
    public static LinearOpMode opMode;
    public static ElapsedTime timer;
    public static String filePath, replayFileName;

    public RobotBase(HardwareMap hardwareMap, Telemetry t, Gamepad gamepad1, Gamepad gamepad2)
    {
        this(hardwareMap, t, gamepad1, gamepad2, null );
    }

    public RobotBase(HardwareMap hardwareMap, Telemetry t, Gamepad gamepad1, Gamepad gamepad2, LinearOpMode opMode)
    {
        hw = hardwareMap;
        telemetry = t;
        g1 = gamepad1;
        g2 = gamepad2;
        this.opMode = opMode;
        timer = new ElapsedTime();
        filePath = Environment.getExternalStorageDirectory().getPath();
        replayFileName = "ReplayFile";
        initSubsystems();
    }

    public abstract void initSubsystems();


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

    public static HashMap<String, Subsystem> getSubsystems()
    {
        return subsystems;
    }

    public void run()
    {
        for(Subsystem s : subsystems.values())
            s.run();
    }

    public void stop()
    {
        for(Subsystem s : subsystems.values())
            s.stop();
    }

}
