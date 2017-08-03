import ij.*;
import ij.process.*;
import ij.gui.*;
import ij.plugin.*;
import ij.text.TextWindow;
import ij.io.Opener;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.image.IndexColorModel;


public class KinemaJ_ implements PlugIn {
  String path = "/Macros/";
  static boolean showArgs = true;

  public void run(String arg) {
    String msg = "";
    if (arg.equals("Calibrate"))
    {Calibrate(); return;}
    if (arg.equals("Preprocess"))
    {Preprocess(); return;}
    else if (arg.equals("Get ROI and Threshold Values"))
    {GetROIandThreshold(); return;}
    else if (arg.equals("Crop and Threshold Videos"))
    {CropandThresholdVideos(); return;}
    else if (arg.equals("Track"))
    {TrackVideos(); return;}
    else if (arg.equals("Toggle Autostart"))
    {ToggleAutostart(); return;}
  }


  void Calibrate() {
    IJ.runMacro(getText(path+"1_Calibrate_Videos.txt"), "");
  }
  void Preprocess() {
    IJ.runMacro(getText(path+"2_Preprocess_Videos.txt"), "");
  }
  void GetROIandThreshold() {
    IJ.runMacro(getText(path+"2a_Get_ROI_and_Threshold_Values.txt"), "");
  }
  void CropandThresholdVideos() {
    IJ.runMacro(getText(path+"2b_Crop_and_Threshold_Videos.txt"), "");
  }
  void TrackVideos() {
    IJ.runMacro(getText(path+"3_Track_Markers.txt"), "");
  }
  void ToggleAutostart() {
    IJ.runMacro(getText(path+"Toggle_KinemaJ_Toolbar_Autostart.txt"), "");
  }



  //  Loads a text file from within a JAR file using getResourceAsStream().
  String getText(String path) {
    String text = "";
    try {
      // get the text resource as a stream
      InputStream is = getClass().getResourceAsStream(path);
      if (is==null) {
        IJ.showMessage("KinemaJ_", "File not found in JAR at "+path);
        return "";
      }
      InputStreamReader isr = new InputStreamReader(is);
      StringBuffer sb = new StringBuffer();
      char [] b = new char [8192];
      int n;
      //read a block and append any characters
      while ((n = isr.read(b)) > 0)
      sb.append(b,0, n);
      // display the text in a TextWindow
      text = sb.toString();
    }
    catch (IOException e) {
      String msg = e.getMessage();
      if (msg==null || msg.equals(""))
      msg = "" + e;
      IJ.showMessage("KinemaJ_", msg);
    }
    return text;
  }

}
