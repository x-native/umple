/*

Copyright 2010 Andrew Forward, Timothy C. Lethbridge

This file is made available subject to the open source license found at:
http://cruise.site.uottawa.ca/UmpleMITLicense.html

*/

package cruise.umple.stats;

import java.io.*;
import org.junit.*;

import cruise.umple.util.FileManager;

public class MainTest
{

  @After
  public void tearDown()
  {
    (new File("myapp.ump")).delete();
    (new File("results.xls")).delete();
    (new File("myapps.txt")).delete();
  }
  
  @Test
  public void NoFileProvided()
  {
    StatsMain.main(new String[] {});
    Assert.assertEquals(true,StatsMain.console.startsWith("Usage"));
  }
  
  @Test
  public void SaveFile()
  {
    FileManager.writeFileToDisk("myapp.ump", "namespace Airline\nclass Airline{1 -- * RegularFlight;}class RegularFlight {}");
    FileManager.writeFileToDisk("myapps.txt", "p1\nmyapp.ump\n");
    StatsMain.main(new String[] {"results.xls","myapps.txt"});
    Assert.assertEquals(true, (new File("results.xls")).exists());
  }
  
}


