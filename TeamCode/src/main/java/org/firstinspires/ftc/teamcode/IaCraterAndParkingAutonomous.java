package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="Crater And Parking", group="group")
//@Disabled
public class IaCraterAndParkingAutonomous extends LinearOpMode {


    private ElapsedTime runtime = new ElapsedTime();
        private DcMotor leftFront;
        private DcMotor leftBack;
        private DcMotor rightFront;
        private DcMotor rightBack;
        private DcMotor intake;
        private DcMotor lift;
        private Servo intakeDeployer;

         @Override
         public void runOpMode() {
             telemetry.addData("Status", "Initialized");
             telemetry.update();

             leftFront = hardwareMap.get(DcMotor.class, "LF");
             leftBack = hardwareMap.get(DcMotor.class, "LB");
             rightFront = hardwareMap.get(DcMotor.class, "RF");
             rightBack = hardwareMap.get(DcMotor.class, "RB");

             lift = hardwareMap.get(DcMotor.class, "LIFT");
             intake = hardwareMap.get(DcMotor.class, "INTAKE");
             intakeDeployer = hardwareMap.get(Servo.class, "SERVO");

             leftFront.setDirection(DcMotor.Direction.REVERSE);
             leftBack.setDirection(DcMotor.Direction.REVERSE);
             rightBack.setDirection(DcMotor.Direction.FORWARD);
             rightFront.setDirection(DcMotor.Direction.FORWARD);

             lift.setPower(-.1);

             waitForStart();
             runtime.reset();


             while (opModeIsActive()) {

//This might collide with teammate's robot

                 lift.setPower(0.3);
                 sleep(1000);
                 lift.setPower(0);
                 turnRight(0.5,1000);
                 sleep(1000);
                 turnLeft(0.5,500);
                 intakeDeployer.setPosition(-.5);
                 straight(0.5, 720);
                 sleep(1000);
                 straight(-0.5, 430);
                 sleep(1000);
                 //turn and go to wall
                 turnLeft(0.5, 630);
                 sleep(1000);
                 straight(0.5, 1210);
                 sleep(1000);
                 //turn and go to depot
                 turnLeft(0.5, 362);
                 sleep(1000);
                 straight(0.5, 1420);
                 sleep(1000);
                 intake.setPower(0.6);
                 sleep(3000);
                 intake.setPower(0);
                 //turn and go to crater
                 turnRight(0.5,1590);
                 sleep(1000);
                 straight(0.6, 3000);
                 turnLeft(.6, 500);
                 straight(.6, 700);
                 sleep(1000);


                 telemetry.addData("Status", "Run Time: " + runtime.toString());
                 telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftFront.getPower(), rightFront.getPower());
                 telemetry.update();
                 break;

             }
         }

        public void straight(double power, int time) {
        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        rightBack.setPower(power);

        sleep(time);

        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

    }

        public void turnRight(double power, int time) {
        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(-power);
        rightBack.setPower(-power);

        sleep(time);

        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

    }

        public void turnLeft(double power, int time) {
        leftFront.setPower(-power);
        leftBack.setPower(-power);
        rightFront.setPower(power);
        rightBack.setPower(power);

        sleep(time);

        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

    }

}
