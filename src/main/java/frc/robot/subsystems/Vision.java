// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.estimator.PoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.counter.UpDownCounter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.resources.Navx;

public class Vision extends SubsystemBase {
  PhotonCamera camera;
  AprilTagFieldLayout aprilTagFieldLayout = AprilTagFields.k2024Crescendo.loadAprilTagLayoutField();
  PhotonPoseEstimator photonPoseEstimator;
  Optional<EstimatedRobotPose> updatedPose; 



  public Vision() {
    camera = new PhotonCamera("limelight");
    Transform3d robotToCam = new Transform3d(new Translation3d(-.30, 0, 0.3), new Rotation3d(0, 0,Math.PI));
    photonPoseEstimator = new PhotonPoseEstimator(aprilTagFieldLayout, PoseStrategy.MULTI_TAG_PNP_ON_RIO, camera, robotToCam);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updatedPose = photonPoseEstimator.update();

  
  }

  public Optional<EstimatedRobotPose> getEstimateGlobalPose(){

    return updatedPose;
  }
  
public void setReferencePose2d(Pose2d pose){
  photonPoseEstimator.setReferencePose(pose);

}
  

}