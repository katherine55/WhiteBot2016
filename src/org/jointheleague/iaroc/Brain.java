package org.jointheleague.iaroc;

import android.os.SystemClock;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import org.wintrisstech.irobot.ioio.IRobotCreateAdapter;
import org.wintrisstech.irobot.ioio.IRobotCreateInterface;
import org.jointheleague.iaroc.sensors.UltraSonicSensors;

public class Brain extends IRobotCreateAdapter {
    private final Dashboard dashboard;
    public UltraSonicSensors sonar;

    public Brain(IOIO ioio, IRobotCreateInterface create, Dashboard dashboard)
            throws ConnectionLostException {
        super(create);
        sonar = new UltraSonicSensors(ioio);
        this.dashboard = dashboard;
    }

    /* This method is executed when the robot first starts up. */
    public void initialize() throws ConnectionLostException {
        dashboard.log("Hello! I'm a Clever Robot!");
        //what would you like me to do, Clever Human?
        driveDirect(200, 200);
    }
    /* This method is called repeatedly. */
    public void loop() throws ConnectionLostException {
        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);

//        if(isBumpRight()){
//            driveDirect(-100, -200);
//            SystemClock.sleep(500);
//            driveDirect(200, 200);
//
//        }
//        if(isBumpLeft()){
//            driveDirect(-100, -200);
//            SystemClock.sleep(500);
//            driveDirect(200, 200);
//        }

        if(isBumpLeft() == false || isBumpRight() == false){
            driveDirect(200, 100);
        }
        if(isBumpLeft() || isBumpRight()){
            driveDirect(-200, -100);
            SystemClock.sleep(500);
            driveDirect(200, 200);
            SystemClock.sleep(500);
        }
    }

}