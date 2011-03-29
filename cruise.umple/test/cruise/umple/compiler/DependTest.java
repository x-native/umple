package cruise.umple.compiler;

import org.junit.*;

public class DependTest
{
  
  @Test
  public void defaultIsNotInternal()
  {
    Depend d = new Depend("org.junit.*");
    Assert.assertEquals("org.junit.*",d.getName());
    Assert.assertEquals(false,d.getIsInternal());
  }
  
  @Test
  public void equalsOnName()
  {
    Depend d = new Depend("org.junit.*");
    Assert.assertEquals(false,d.equals(new Depend("x")));
    Assert.assertEquals(true,d.equals(new Depend("org.junit.*")));
    
    Depend same = new Depend("org.junit.*");
    same.setIsInternal(false);
    Assert.assertEquals(true,d.equals(same));
  }
  
  @Test
  public void packageName()
  {
    
    Depend d = new Depend("org.junit.*");
    Assert.assertEquals("org.junit",d.getPackageName());
    
    d = new Depend("org.junit.Assert");
    Assert.assertEquals("org.junit",d.getPackageName());
    
    d = new Depend(null);
    Assert.assertEquals("",d.getPackageName());

    d = new Depend("");
    Assert.assertEquals("",d.getPackageName());

    d = new Depend("blah");
    Assert.assertEquals("blah",d.getPackageName());
  }

}
