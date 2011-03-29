package cruise.umple.compiler;


import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cruise.umple.util.SampleFileWriter;

/**
 * Tests for CommonFileTest
 * <p>
 * <a href="http://cruise.site.uottawa.ca">CRuiSE lab, University of Ottawa</a>
 * <p>
 * Modification History: none.
 *
 * @author Dusan Brestovansky
 */
public class UmpleFileTest {

  
  @Before
  public void setUp()
  {
    SampleFileWriter.createFile("aha.txt","class Teacher {}");
    SampleFileWriter.createFile("aha2.txt","class Student {}");
  }
  
  @After
  public void tearDown()
  {
    SampleFileWriter.destroy("aha.txt");
    SampleFileWriter.destroy("aha2.txt");
  }

  @Test
  public void appendMultipleFiles()
  {
    UmpleFile file = new UmpleFile("aha.txt");
    file.append("aha2.txt");
    
    Assert.assertEquals("aha.txt", file.getFileName());
    Assert.assertEquals("class Teacher {}class Student {}", file.getFileContent());
  }  

  @Test
  public void constructorFileOnlyMakeSureThePathIsProperlyResolved()
  {
    File f = new File("aha.txt");
    String fullName = f.getAbsolutePath();
    UmpleFile file = new UmpleFile(fullName);
    Assert.assertEquals(f.getAbsoluteFile().getParentFile().getAbsolutePath(), file.getPath());
    
    Assert.assertEquals("aha.txt", file.getFileName());
    Assert.assertEquals("class Teacher {}", file.getFileContent());
  }
  
  
  @Test
  public void constructorFileOnly()
  {
    UmpleFile file = new UmpleFile("aha.txt");
    Assert.assertEquals("aha.txt", file.getFileName());
    Assert.assertEquals("class Teacher {}", file.getFileContent());
  }
  
  @Test
  public void constructorUnknownFilenameIsOkay()
  {
    UmpleFile file = new UmpleFile("blah.txt");
    Assert.assertEquals("blah.txt", file.getFileName());
    Assert.assertEquals("", file.getFileContent());
  }  

  @Test
  public void constructorWithPathAndFilenameSeparate()
  {
    File ahaFile = new File("aha.txt");
    String path = ahaFile.getAbsoluteFile().getParent();

    UmpleFile file = new UmpleFile(path,"aha.txt");
    Assert.assertEquals("aha.txt", file.getFileName());
    Assert.assertEquals("class Teacher {}", file.getFileContent());
  }   

  @Test
  public void constructorWithRelativeButComplexPath()
  {
    String path = SampleFileWriter.rationalize("test/data/Election");
    UmpleFile file = new UmpleFile(path,"ElectionSystem.ump");
    Assert.assertEquals("ElectionSystem.ump", file.getFileName());
    Assert.assertEquals(true,file.getFileContent().length() > 0);
  }   

  @Test
  public void getSimpleFileName_manyExtensions()
  {
    UmpleFile file = new UmpleFile("blah.moreBlah.txt");
    Assert.assertEquals("blah.moreBlah", file.getSimpleFileName());
  }   
  
  @Test
  public void getSimpleFileName_noExtension()
  {
    UmpleFile file = new UmpleFile("blah2");
    Assert.assertEquals("blah2", file.getSimpleFileName());
  }     
  
  
  @Test
  public void getSimpleFileName_hasExtension()
  {
    UmpleFile file = new UmpleFile("blah.txt");
    Assert.assertEquals("blah", file.getSimpleFileName());
  }     
 
   
}
