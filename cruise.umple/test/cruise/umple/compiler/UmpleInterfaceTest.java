package cruise.umple.compiler;

import org.junit.*;

public class UmpleInterfaceTest
{
  private UmpleInterface umpleClassInterface;
  private UmpleClass umpleClass;
  

  @Before
  public void setUp() throws Exception
  {
	  createInterface();
	  createClass();
  }

  @After
  public void tearDown() 
  {
    umpleClassInterface = null;
  }

  private void createInterface()
  {
	umpleClassInterface = new UmpleInterface("Lion");
	// Creating a Method
	Method aMethod = new Method("","getLion","Lion",false);
	aMethod.setMethodBody(new MethodBody("return null"));
	MethodParameter aParameter  = new MethodParameter("id", "int",null,null, false);
	aMethod.addMethodParameter(aParameter);
	
	// Creating a single constant Member
    Constant aConstant = new Constant("MAX_LIFE", "int", "public", "100");
    
    // Adding Members to the Interface
    umpleClassInterface.addConstant(aConstant);
    umpleClassInterface.addMethod(aMethod);

    Assert.assertEquals(1, umpleClassInterface.getConstants().size());
    Assert.assertEquals(1, umpleClassInterface.getMethods().size());
    
  }

  private void createClass()
  {
	umpleClass = new UmpleClass("Lion");
	// Creating a Method
	Method aMethod = new Method("","getLion","Lion",false);
	aMethod.setMethodBody(new MethodBody("return something"));
	MethodParameter aParameter  = new MethodParameter("id", "int",null,null, false);
	aMethod.addMethodParameter(aParameter);
	umpleClass.addMethod(aMethod);

	//Assert.assertEquals(1, umpleClass.getMethods().size());
    
  }

  
  @Test
  public void testInterfaceContents_METHODHEADER()
  {
	Method aMethod = umpleClassInterface.getMethod(0);
    String methodName = aMethod.getName();
    String returnType = aMethod.getType();
    String methodBody = aMethod.getMethodBody().getExtraCode();
    
    MethodParameter param = aMethod.getMethodParameter(0);
    String paramName = param.getName();
    String paramType = param.getType();
    String paramValue = param.getValue();
       
    System.out.print(aMethod.toString());
    Assert.assertEquals("getLion", methodName);
    Assert.assertEquals("Lion", returnType);
    Assert.assertEquals("return null", methodBody);
   
    Assert.assertEquals("id",paramName );
    Assert.assertEquals("int",paramType );
    Assert.assertEquals(null,paramValue );
 }


  @Test
  public void testInterfaceContents_CONSTANTS()
  {
    Constant constant = umpleClassInterface.getConstant(0);
    String constantName = constant.getName();
    String constantModifier = constant.getModifier();
    String constantValue =  constant.getValue();
    String constantType =  constant.getType();
    
    Assert.assertEquals("MAX_LIFE",constantName );
    Assert.assertEquals("public",constantModifier );
    Assert.assertEquals("100",constantValue );
    Assert.assertEquals("int",constantType );
  }

}
