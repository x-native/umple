/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.11.1.3376 modeling language!*/

package cruise.umple.compiler;
import java.io.*;
import cruise.umple.util.*;
import java.util.*;

public class UmpleParser extends Parser
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UmpleParser Attributes
  private String currentPackageName;
  private List<AssociationVariable> unlinkedAssociationVariables;
  private List<Association> unlinkedAssociations;
  private Map<Position,String> positionToClassNameReference;
  private List<String> unparsedUmpleFiles;
  private List<String> parsedUmpleFiles;
  private Map<UmpleClass,List<String>> unlinkedExtends;
  private Map<UmpleClass,Pair> umpleClassToStateMachineDefinition;
  private Map<String,Token> stateMachineNameToToken;
  private UmpleModel model;
  private StateMachine placeholderStateMachine;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UmpleParser(String aName, UmpleModel aModel)
  {
    super(aName);
    currentPackageName = "";
    unlinkedAssociationVariables = new ArrayList<AssociationVariable>();
    unlinkedAssociations = new ArrayList<Association>();
    positionToClassNameReference = new HashMap<Position, String>();
    unparsedUmpleFiles = new ArrayList<String>();
    parsedUmpleFiles = new ArrayList<String>();
    unlinkedExtends = new HashMap<UmpleClass,List<String>>();
    umpleClassToStateMachineDefinition = new HashMap<UmpleClass, Pair>();
    stateMachineNameToToken = new HashMap<String, Token>();
    model = aModel;
    placeholderStateMachine = null;
    init();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCurrentPackageName(String aCurrentPackageName)
  {
    boolean wasSet = false;
    currentPackageName = aCurrentPackageName;
    wasSet = true;
    return wasSet;
  }

  public boolean setModel(UmpleModel aModel)
  {
    boolean wasSet = false;
    model = aModel;
    wasSet = true;
    return wasSet;
  }

  public String getCurrentPackageName()
  {
    return currentPackageName;
  }

  public UmpleModel getModel()
  {
    return model;
  }

  public void delete()
  {
    super.delete();
  }

  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  public UmpleParser()
  {
    this("UmpleParser",new UmpleModel(null));
  }

  public UmpleParser(UmpleModel aModel)
  {
    this("UmpleParser",aModel);
  }

	private void init()
	{
		addCouple(new Couple("\"","\""));
		addCouple(new Couple("{","}"));

		addRule("program- : ( [[comment]] | [[directive]] )*");
		addRule("directive- : [[glossary]] | [[generate]] | [[useStatement]] | [[namespace]] | [[entity]]");
		addRule("glossary : glossary { [[word]]* }");
		addRule("word : [singular] : [plural] ;");
		addRule("generate- : generate [=generate:Java|Php|Ruby|Json|Yuml|Violet|Umlet|Simulate|TextUml|Papyrus|Ecore|Xmi] ;");
		addRule("useStatement- : use [use] ;");
		addRule("namespace- : namespace [namespace] ;");
		addRule("entity- : [[classDefinition]] | [[interfaceDefinition]] | [[externalDefinition]] | [[associationDefinition]] | [[associationClassDefinition]] | [[stateMachineDefinition]]");
		addGrammarRule("");

		addRule("classDefinition : class [name] { [[classContent]]* }");
		addRule("externalDefinition : external [name] { [[classContent]]* }");
		addRule("interfaceDefinition : interface [name] { [[depend]]* [[interfaceBody]] }");
		addRule("associationDefinition : association [name]? { [[association]]* }");
		addRule("associationClassDefinition : associationClass [name] { [[associationClassContent]]* }");
		addRule("classContent- : [[comment]] | [[classDefinition]] | [[trace]] | [[position]] | [[softwarePattern]] | [[depend]] | [[symmetricReflexiveAssociation]] | [[attribute]] | [[stateMachine]] | [[inlineAssociation]] | [[concreteMethodDeclaration]] | [[constantDeclaration]] | [[extraCode]]");
		addRule("associationClassContent- :  [[comment]] | [[classDefinition]] | [[position]] | [[softwarePattern]] | [[depend]] | [[singleAssociationEnd]] [[singleAssociationEnd]] | [[stateMachine]] | [[attribute]] | [[inlineAssociation]] | [[extraCode]]");
		addGrammarRule("");

        //addRule("trace : trace [**trace_code] ( from [**trace_from] )? execute [**trace_execute] ( where [**trace_where] )?");
        addRule("trace : trace [**trace_code] execute { [**trace_execute] } ( where [**trace_where] ; )?");


		// Section for Members in Interfaces
		// NOTE: We are considering type as simple String
		// Interface Body: An interface can have CONSTANTS, ATTRIBUTES and METHODS
		addRule("interfaceBody- : [[interfaceMemberDeclaration]]*");
		addRule("interfaceMemberDeclaration : [[constantDeclaration]] | [[abstractMethodDeclaration]] | [[position]] | [[extraCode]] ");
		// Constants in interfaces (e.g. constant String ACONSTANT="aValue";)
		addRule("constantDeclaration : constant ([=list:[]] [name] | [type] [=list:[]] [name] | [type,name>1,0]) (= [**value]) ;");
		// Methods in classes and Interfaces
		// Should we use modifier for concrete methods [=modifier:public|protected|private]?
		// Should we use modifier for abstract methods [=modifier:public|protected|abstract|final]?
		addRule("concreteMethodDeclaration :  [type] [[methodDeclarator]] { [**code] } ");
		addRule("abstractMethodDeclaration :  [type] [[methodDeclarator]] ; ");  
		addRule("methodDeclarator : [methodName] [[parameterList]] | [methodName] OPEN_ROUND_BRACKET CLOSE_ROUND_BRACKET ");
		addRule("parameterList : OPEN_ROUND_BRACKET [[parameter]] ( , [[parameter]] )* CLOSE_ROUND_BRACKET ");
		addRule("parameter : ([=list:[]] [name] | [type] [=list:[]] [name] | [type,name>1,0]) ");
		addGrammarRule("");

		addRule("association : [[associationEnd]] [=arrow:--|->|<-|><] [[associationEnd]] ;");
		addRule("symmetricReflexiveAssociation : [[multiplicity]] self [roleName] ;");
		addRule("inlineAssociation : [[inlineAssociationEnd]] [=arrow:--|->|<-|><] [[associationEnd]] ;");
		addRule("inlineAssociationEnd : [[multiplicity]] [roleName]?");
		addRule("singleAssociationEnd : [[multiplicity]] [type,roleName] ;");
		addRule("associationEnd : [[multiplicity]] [type,roleName]");
		addRule("multiplicity- : [=bound:*] | [lowerBound] .. [upperBound] | [bound]");
		addGrammarRule("");

		addRule("softwarePattern- : [[isA]] | [[singleton]] | [[keyDefinition]] | [[codeInjection]]");
		addRule("isA- : [[singleIsA]] | [[multipleIsA]]");
		addRule("singleIsA- : isA [extendsName] ( , isA [extendsName] )*  ;");
		addRule("multipleIsA- : isA [extendsName] ( , [extendsName] )* ;");
		addRule("singleton- : [=singleton] ;");
		addRule("keyDefinition- : [[defaultKey]] | [[key]]");
		addRule("codeInjection- : [[beforeCode]] | [[afterCode]]");
		addRule("attribute : [[derivedAttribute]] | [=autounique] [name] ; | [=unique]? [=lazy]? [=modifier:immutable|settable|internal|defaulted|const]? ([=list:[]] [name] | [type] [=list:[]] [name] | [type,name>1,0]) (= [**value])? ;");
		addRule("derivedAttribute- : [=modifier:immutable|settable|internal|defaulted|const]? ([=list:[]] [name] | [type] [=list:[]] [name] | [type,name>1,0]) = { [**derivedValue] }");
		addGrammarRule("");

		addRule("beforeCode : before [operationName] { [**code] }");
		addRule("afterCode : after [operationName] { [**code] }");
		addRule("defaultKey : key { }");
		addRule("key : key { [keyId] ( , [keyId] )* }");
		addRule("depend- : depend [depend] ;");
		addRule("extraCode- : [**extraCode]");
		addGrammarRule("");

		addRule("comment- : [[inlineComment]] | [[multilineComment]]");
		addRule("inlineComment- : // [*inlineComment]");
		addRule("multilineComment- : /* [**multilineComment] */");
		addGrammarRule("");

		addRule("stateMachineDefinition : statemachine [name] { [[state]]* }");
		addRule("stateMachine : [[enum]] | [[inlineStateMachine]] | [[referencedStateMachine]] ");
		addRule("inlineStateMachine : [name] { [[state]]* }");
		addRule("referencedStateMachine : [name] as [definitionName] ; | [name] as [definitionName] { [[state]]* } ");
		addRule("enum : [name] { } | [name] { [stateName] (, [stateName])* }");
		addRule("state : [stateName] { ( [=changeType:-|*]? [[stateEntity]] )* }");
		addRule("stateEntity- : [=-||] | [[transition]] | [[entryOrExitAction]] | [[activity]] | [[state]]");
		addRule("transition : [[guard]] [[eventDefinition]] -> [[action]]? [stateName] ; | [[eventDefinition]] [[guard]]? -> [[action]]? [stateName] ; | [[activity]] -> [stateName]");
		addRule("eventDefinition- : [[afterEveryEvent]] | [[afterEvent]] | [event]");
		addRule("afterEveryEvent- : afterEvery -( [timer] -)");
		addRule("afterEvent- : after -( [timer] -)");
		addRule("action : / { [**actionCode] }");
		addRule("entryOrExitAction : [=type:entry|exit] / { [**actionCode] }");
		addRule("activity : do { [**activityCode] }");
		addRule("guard : [ [**guardCode] ] ");
		addGrammarRule("");

		addRule("position- : [[associationPosition]] | [[elementPosition]] ");
		addRule("elementPosition : position [x] [y] [width] [height] ;");
		addRule("associationPosition : position.association [name] [[coordinate]] [[coordinate]] ;");
		addRule("coordinate : [x] , [y]");
		addGrammarRule("");

	}


	public ParseResult parse(String ruleName, String input)
	{
		return super.parse(ruleName,input);
	}

	public ParseResult analyze(boolean shouldGenerate)
	{
		addNecessaryFiles();

		while (!unparsedUmpleFiles.isEmpty())
		{
			String nextFile = unparsedUmpleFiles.get(0);
			unparsedUmpleFiles.remove(0);
			parsedUmpleFiles.add(nextFile);
			String input = SampleFileWriter.readContent(new File(nextFile));
			parse("program", input);
			addNecessaryFiles();
		}    

		for(Token t : getRootToken().getSubTokens())
		{
			if (t.is("generate"))
			{
				model.addGenerate(t.getValue());
			}
			else if (t.is("glossary"))
			{
				analyzeGlossary(t);
			}
			else if (t.is("namespace"))
			{
				currentPackageName = t.getValue();
				if (model.getDefaultNamespace() == null)
				{
					model.setDefaultNamespace(currentPackageName);  
				}
			}
			else if (t.is("classDefinition"))
			{
				analyzeClass(t);
			}
			else if (t.is("externalDefinition"))
			{
				analyzeExternal(t);
			}
			else if (t.is("interfaceDefinition"))
			{
				UmpleInterface newInterface = new UmpleInterface(t.getValue("name"));
				model.addUmpleInterface(newInterface);
				newInterface.setPackageName(currentPackageName);
				analyzeInterface(t,newInterface);  
			}
			else if (t.is("associationClassDefinition"))
			{
				analyzeAssociationClass(t);
			}
			else if (t.is("associationDefinition"))
			{
				analyzeAllAssociations(t);
			}
			else if (t.is("stateMachineDefinition"))
			{
				analyzeStateMachineDefinition(t);
			}

			if (!getParseResult().getWasSuccess())
			{
				return getParseResult();
			}
		}

		addReferencedStateMachines();


		if (model.getDefaultGenerate() == null)
		{
			model.addGenerate("Java");
		}

		if (verifyClassesInUse())
		{
			addUnlinkedAssociationVariables();
			addUnlinkedAssociations();
			addUnlinkedExtends();
			layoutNewElements();

			if (shouldGenerate)
			{
				generateClasses();  
			}
		}
		return getParseResult();
	}

	//------------------------
	// PRIVATE METHODS
	//------------------------

	private void addNecessaryFiles()
	{
		for(Token t : getRootToken().getSubTokens())
		{
			if (t.is("use"))
			{
				String filename = model.getUmpleFile().getPath() + File.separator + t.getValue();

				if (!parsedUmpleFiles.contains(filename) && !unparsedUmpleFiles.contains(filename))
				{
					unparsedUmpleFiles.add(filename);
				}
			}
		}
	}

	private void generateClasses()
	{
		model.generate();
	}

	private boolean verifyClassesInUse()
	{
		for(Map.Entry<Position, String> e : positionToClassNameReference.entrySet())
		{
			if (model.getUmpleClass(e.getValue()) == null)
			{
				UmpleClass aClass = model.addUmpleClass(e.getValue());
				aClass.setPackageName(model.getDefaultNamespace());
				setFailedPosition(e.getKey());
				return false;
			}
		}
		return true;
	}

	private void addUnlinkedAssociationVariables()
	{
		for (AssociationVariable av : unlinkedAssociationVariables)
		{
			UmpleClass aClass = model.getUmpleClass(av.getType());
			UmpleClass bClass = model.getUmpleClass(av.getRelatedAssociation().getType()); 
			aClass.addAssociationVariable(av.getRelatedAssociation());
			aClass.addAssociation(bClass.getAssociation(bClass.indexOfAssociationVariable(av)));

			if (av.getIsNavigable())
			{
				bClass.addReferencedPackage(aClass.getPackageName());
			}

			if (av.getRelatedAssociation().getIsNavigable())
			{
				aClass.addReferencedPackage(bClass.getPackageName());
			}

		}
	}

	private void addReferencedStateMachines()
	{
		for (UmpleClass clazz : umpleClassToStateMachineDefinition.keySet())
		{
			Pair nameToDefinition = umpleClassToStateMachineDefinition.get(clazz);
			Token stateMachineToken = stateMachineNameToToken.get(nameToDefinition.getValue());
			if (stateMachineToken == null)
			{
				continue;
			}

			StateMachine sm = new StateMachine(nameToDefinition.getName());
			sm.setUmpleClass(clazz);

			// analyze meta states

			populateStateMachine(stateMachineToken,sm);
		}
	}


	private void addUnlinkedExtends()
	{	
		for (UmpleClass child : unlinkedExtends.keySet())
		{
			List<String> extendsNames = unlinkedExtends.get(child);

			if (extendsNames == null)
			{
				continue;
			}

			for (int i=0; i < extendsNames.size();i++){
				String extendName= extendsNames.get(i);
				if (isUmpleClass(extendName))
				{
					UmpleClass parent = model.getUmpleClass(extendName); 
					child.setExtendsClass(parent);
				}
				else {
					UmpleInterface uInterface=  model.getUmpleInterface(extendName);
					child.addParentInterface(uInterface);
					addImplementedMethodsFromInterface(uInterface, child);
				}
			}
		}
	}

	private boolean isUmpleClass(String elementName){
		return (model.getUmpleInterface(elementName) != null) ? false: true;
	}


	private void addImplementedMethodsFromInterface(UmpleInterface parentInterface, UmpleClass uClass)
	{
		//GET AND SET METHODS CHECK?
				if (parentInterface.hasMethods()){
					for (Method aMethod : parentInterface.getMethods())
					{
						boolean shouldAddMethod = verifyIfMethodIsConstructorOrGetSet(uClass, aMethod);
						if (!(uClass.hasMethod(aMethod)) && shouldAddMethod){
							aMethod.setIsImplemented(true);
							uClass.addMethod(aMethod);
						}
					}
				}
	}

	private boolean verifyIfMethodIsConstructorOrGetSet(UmpleClass uClass, Method aMethod){
		String methodName = aMethod.getName();
		//1. Verify if method to be added is a setter or a getter
		String accessorName = methodName.substring(0,3);
		if ((accessorName.equals("get")) || (accessorName.equals("set"))){
			String possibleAttributeName =   methodName.substring(3,methodName.length()).toLowerCase();
			Attribute attr = uClass.getAttribute(possibleAttributeName);
			if (attr != null){
				return false;
			}
		}
		//2. Verify if method to be added is a constructor
		if (aMethod.getType().equals("public")){
			String extraCode = uClass.getExtraCode()  + System.getProperty("line.separator")  + aMethod.toString();
			uClass.setExtraCode(extraCode);
			return false;
		}  
		//3. Verify if method from interface is already part of the Class extracode
		String match = "public " + aMethod.getType() + " " + aMethod.getName();	  
		if (uClass.getExtraCode().contains(match)){
			return false;
		}
		return true;
	}

	private void addUnlinkedAssociations()
	{
		for (Association association : unlinkedAssociations)
		{
			AssociationEnd myEnd = association.getEnd(0);
			AssociationEnd yourEnd = association.getEnd(1);

			UmpleClass myClass = model.getUmpleClass(myEnd.getClassName());
			UmpleClass yourClass = model.getUmpleClass(yourEnd.getClassName());

			AssociationVariable myAs = new AssociationVariable(myEnd.getRoleName(),myEnd.getClassName(),myEnd.getModifier(),null,myEnd.getMultiplicity(),association.getIsLeftNavigable());
			AssociationVariable yourAs = new AssociationVariable(yourEnd.getRoleName(),yourEnd.getClassName(),yourEnd.getModifier(),null,yourEnd.getMultiplicity(),association.getIsRightNavigable());
			myAs.setRelatedAssociation(yourAs);
			myClass.addAssociationVariable(yourAs);
			myClass.addAssociation(association);

			yourClass.addAssociationVariable(myAs);
			yourClass.addAssociation(association);


			if (myAs.getIsNavigable())
			{
				myClass.addReferencedPackage(yourClass.getPackageName());
			}

			if (yourAs.getIsNavigable())
			{
				yourClass.addReferencedPackage(myClass.getPackageName());
			}      

		}
	}

	private void layoutNewElements()
	{
		/* layout classes */
		// int numDefaults = 0;
		for (int i=0; i<model.numberOfUmpleClasses(); i++)
		{
			UmpleClass c = model.getUmpleClass(i);

			if (c.getPosition().getStatus() == Coordinate.Status.Defaulted)
			{
				// numDefaults += 1;
			}
			else if (c.getPosition().getStatus() == Coordinate.Status.Undefined)
			{
				// numDefaults += 1;
				// c.setPosition(model.getDefaultClassPosition(numDefaults));
				c.setPosition(model.getDefaultClassPosition(i+1));
				c.getPosition().setStatus(Coordinate.Status.Defaulted);
			}
		}

		/* layout interfaces */
		// int numDefaults = 0;
		for (int i=0; i<model.numberOfUmpleInterfaces(); i++)
		{
			UmpleInterface c = model.getUmpleInterface(i);

			if (c.getPosition().getStatus() == Coordinate.Status.Defaulted)
			{
				// numDefaults += 1;
			}
			else if (c.getPosition().getStatus() == Coordinate.Status.Undefined)
			{
				c.setPosition(model.getDefaultClassPosition(i+1));
				c.getPosition().setStatus(Coordinate.Status.Defaulted);
			}
		}

		/* layout associations */
		for (int i=0; i<model.numberOfAssociations(); i++)
		{
			Association a = model.getAssociation(i);
			int numberOfPositions = a.numberOfPositions();

			if (numberOfPositions < 2)
			{
				Coordinate[] defaults = model.getDefaultAssociationPosition(a);

				a.addPosition(defaults[0]);
				a.addPosition(defaults[1]);
				a.getPosition(0).setStatus(Coordinate.Status.Defaulted);
				a.getPosition(1).setStatus(Coordinate.Status.Defaulted);
			}
		}    
	}

	private void analyzeAllAssociations(Token associationToken)
	{
		String name = associationToken.getValue("name");
		for(Token token : associationToken.getSubTokens())
		{
			if (token.is("association"))
			{
				Association association = analyzeAssociation(token, "");
				association.setName(name);
				unlinkedAssociations.add(association);
			}
		}
	}

	private void analyzeAssociationClass(Token classToken)
	{
		AssociationClass aClass = model.addAssociationClass(classToken.getValue("name"));
		List<String> extendsList = new ArrayList<String>();
		
		for (Token extendsToken : classToken.getSubTokens()){
			if (extendsToken.getValue("extendsName") != null)
			{ 
				extendsList.add(extendsToken.getValue("extendsName"));
				unlinkedExtends.put(aClass, extendsList);
			}	
		}
		aClass.setPackageName(currentPackageName);

		Token leftAssociationToken = null;
		Token rightAssociationToken = null;

		analyzeAllClassContent(classToken,aClass,0);
		analyzeAllClassContent(classToken,aClass,1);

		for(Token token : classToken.getSubTokens())
		{
			if (token.is("singleAssociationEnd"))
			{
				if (leftAssociationToken == null)
				{
					leftAssociationToken = token;
				}
				else
				{
					rightAssociationToken = token;
				}
			}
		}

		if (leftAssociationToken == null || rightAssociationToken == null)
		{
			setFailedPosition(classToken.getPosition());
			return;
		}

		String innerName = null;
		String innerType = aClass.getName();
		String innerModifier = null;
		Multiplicity innerMult = new Multiplicity();
		innerMult.setRange("0","1"); 

		String leftName = leftAssociationToken.getValue("roleName");
		String leftType = leftAssociationToken.getValue("type");
		String leftModifier = leftAssociationToken.getValue("modifier");
		String leftBound = leftAssociationToken.getValue("bound");
		String leftLowerBound = leftAssociationToken.getValue("lowerBound");
		String leftUpperBound = leftAssociationToken.getValue("upperBound");
		Multiplicity leftMult = new Multiplicity();
		leftMult.setBound(leftBound);
		leftMult.setRange(leftLowerBound,leftUpperBound);

		String rightName = rightAssociationToken.getValue("roleName");
		String rightType = rightAssociationToken.getValue("type");
		String rightModifier = rightAssociationToken.getValue("modifier");
		String rightBound = rightAssociationToken.getValue("bound");
		String rightLowerBound = rightAssociationToken.getValue("lowerBound");
		String rightUpperBound = rightAssociationToken.getValue("upperBound");
		Multiplicity rightMult = new Multiplicity();
		rightMult.setBound(rightBound);
		rightMult.setRange(rightLowerBound,rightUpperBound);

		AssociationEnd leftFirstEnd = new AssociationEnd(innerName,innerType,innerModifier,leftType,leftMult);
		AssociationEnd leftSecondEnd = new AssociationEnd(leftName,leftType,leftModifier,innerType,innerMult);

		AssociationEnd rightFirstEnd = new AssociationEnd(innerName,innerType,innerModifier,rightType,rightMult);
		AssociationEnd rightSecondEnd = new AssociationEnd(rightName,rightType,rightModifier,innerType,innerMult);    

		updateAssociationEnds(leftFirstEnd,leftSecondEnd);
		updateAssociationEnds(rightFirstEnd,rightSecondEnd);

		Association leftAssociation = new Association(true,true,leftFirstEnd,leftSecondEnd);
		Association rightAssociation = new Association(true,true,rightFirstEnd,rightSecondEnd);

		model.addAssociation(leftAssociation);
		model.addAssociation(rightAssociation);

		unlinkedAssociations.add(leftAssociation);
		unlinkedAssociations.add(rightAssociation);
	}

	private void setFailedPosition(Position position)
	{
		getParseResult().setWasSuccess(false);
		getParseResult().setPosition(position);
	}

	private void analyzeInterface(Token interfaceToken, UmpleInterface aInterface)
	{
		for(Token token : interfaceToken.getSubTokens())
		{
			if (token.is("depend"))
			{
				Depend d = new Depend(token.getValue());
				aInterface.addDepend(d);
			}
			if (token.is("interfaceMemberDeclaration"))
			{
				analyzeInterfaceMembers(token, aInterface);
			}
			else if (token.is("elementPosition"))
			{
				aInterface.setPosition(new Coordinate(token.getIntValue("x"),token.getIntValue("y"), token.getIntValue("width"), token.getIntValue("height")));
			}

		}
	}

	private void analyzeInterfaceMembers(Token interfaceMemberToken, UmpleInterface aInterface)
	{
		for(Token childToken : interfaceMemberToken.getSubTokens())
		{
			if(childToken.is("abstractMethodDeclaration"))
			{
				analyzeMethod(childToken, aInterface);	 
			}	
			else if (childToken.is("constantDeclaration"))
			{
				analyzeConstant(childToken, aInterface);	  
			}
			else if (childToken.is("extraCode"))
			{
				aInterface.setExtraCode(childToken.getValue("extraCode"));
			}


		}
	}

	private void analyzeMethod(Token method, UmpleElement uElement)
	{
		String modifier = "";
		Method aMethod = new Method("","","",false);
		for(Token token : method.getSubTokens())
		{
			if (token.is("modifier"))
			{
				modifier += " " + (token.getValue());
				aMethod.setModifier(modifier);
			}
			else if (token.is("type"))
			{
				aMethod.setType(token.getValue());
			}
			else if (token.is("methodDeclarator"))
			{
				analyzeMethodDeclarator(token, aMethod);
			}
			else if (token.is("code"))
			{
				aMethod.setMethodBody(new MethodBody(token.getValue()));
			}
		}  

		// Add method to Class or Interface
		if (uElement instanceof UmpleClass)
		{
			UmpleClass uClass = (UmpleClass) uElement;
			boolean shouldAddMethod = verifyIfMethodIsConstructorOrGetSet(uClass, aMethod);
			if (!uClass.hasMethod(aMethod) && shouldAddMethod ){
				uClass.addMethod(aMethod); 
			}
		}
		else if (uElement instanceof UmpleInterface)
		{
			UmpleInterface uInterface = (UmpleInterface) uElement;
			if (!uInterface.hasMethod(aMethod)){
				uInterface.addMethod(aMethod); 
			}
		}  
	}

	private void analyzeMethodDeclarator(Token token, Method aMethod)
	{
		for(Token methodToken : token.getSubTokens())
		{
			if (methodToken.is("methodName")){
				aMethod.setName(methodToken.getValue());
			}
			if (methodToken.is("parameterList")){
				for(Token parameterToken : methodToken.getSubTokens())
				{
					boolean isList = false;
					if (parameterToken.is("parameter")){
						String paramType="";
						if (parameterToken.getSubToken("type") != null){
							paramType = parameterToken.getSubToken("type").getValue();
						}
						if (parameterToken.getSubToken("list") != null){
							isList = parameterToken.getSubToken("list").getValue() != null;				
						}
						String paramName = parameterToken.getSubToken("name").getValue();
						MethodParameter aParameter  = new MethodParameter(paramName,paramType,null,null, false);
						aParameter.setIsList(isList);
						aMethod.addMethodParameter(aParameter);
					}
				}
			}
		}
	}

	private void analyzeGlossary(Token glossaryToken)
	{
		for(Token wordToken : glossaryToken.getSubTokens())
		{
			if (!wordToken.is("word"))
			{
				continue;
			}
			Word word = new Word(wordToken.getValue("singular"),wordToken.getValue("plural"));
			model.getGlossary().addWord(word);
		}
	}

	private UmpleClass analyzeExternal(Token externalToken)
	{
		UmpleClass aClass = analyzeClass(externalToken);
		aClass.setModifier("external");
		return aClass;
	}

	private UmpleClass analyzeClass(Token classToken)
	{
		UmpleClass aClass = model.addUmpleClass(classToken.getValue("name"));
		List<String> extendsList = new ArrayList<String>();
			
		for (Token extendsToken : classToken.getSubTokens()){
			if (extendsToken.getValue("extendsName") != null)
			{ 
				extendsList.add(extendsToken.getValue("extendsName"));
				unlinkedExtends.put(aClass, extendsList);
			}	
		}
		if (classToken.getValue("singleton") != null)
		{
			aClass.setIsSingleton(true);
		}
		aClass.setPackageName(currentPackageName);

		analyzeAllClassContent(classToken,aClass,0);
		analyzeAllClassContent(classToken,aClass,1);
		return aClass;
	}

	private void analyzeAllClassContent(Token classToken, UmpleClass aClass, int level)
	{
		for(Token token : classToken.getSubTokens())
		{
			analyzeClassContent(token,aClass,level);

			if (!getParseResult().getWasSuccess())
			{
				break;
			}
		}
	}

	private void analyzeClassContent(Token token, UmpleClass aClass, int level)
	{
		if (level == 0)
		{
			if (token.is("classDefinition"))
			{
				UmpleClass childClass = analyzeClass(token);
				childClass.setExtendsClass(aClass);
			}
			else if (token.is("attribute"))
			{
				analyzeAttribute(token,aClass);
			}
			else if (token.is("stateMachine"))
			{
				Token subToken = token.getSubToken(0);
				if (subToken.is("enum") || subToken.is("inlineStateMachine"))
				{
					analyzeStateMachine(subToken,aClass);
				}
				else if (subToken.is("referencedStateMachine"))
				{
					analyzedReferencedStateMachine(subToken,aClass);
				}
			}
			else if (token.is("depend"))
			{
				Depend d = new Depend(token.getValue());
				aClass.addDepend(d);
			}
			else if (token.is("inlineAssociation"))
			{
				analyzeinlineAssociation(token,aClass);
			}
			else if (token.is("symmetricReflexiveAssociation"))
			{
				analyzeSymmetricReflexiveAssociation(token,aClass);
			}
			else if (token.is("beforeCode") || token.is("afterCode"))
			{
				analyzeInjectionCode(token,aClass);
			}
			else if (token.is("key") || token.is("defaultKey")) 
			{
				analyzeKey(token,aClass);
			}
			else if (token.is("extraCode"))
			{
				String previousExtraCode = aClass.getExtraCode().length() > 0 ? aClass.getExtraCode() + System.getProperty("line.separator"): "";
				aClass.setExtraCode(previousExtraCode + token.getValue());
			}
			else if (token.is("constantDeclaration"))
			{
				analyzeConstant(token,aClass);
			}
			else if (token.is("concreteMethodDeclaration"))
			{
				analyzeMethod(token,aClass);
			}
		}
		else if (level == 1)
		{

			if (token.is("elementPosition"))
			{
				aClass.setPosition(new Coordinate(token.getIntValue("x"),token.getIntValue("y"), token.getIntValue("width"), token.getIntValue("height")));
			}

			else if (token.is("associationPosition"))
			{
				String name = token.getValue("name");
				Association assoc = model.getAssociation(name);

				if (assoc != null)
				{
					assoc.setName(name);
					for(Token position : token.getSubTokens())
					{
						if (position.is("coordinate"))
						{
							assoc.addPosition(new Coordinate(position.getIntValue("x"),position.getIntValue("y"),0,0));
						}
					}
				}
			}
		}
	}

	private void analyzeConstant(Token constantToken, UmpleElement uElement)
	{
		Constant aConstant = new Constant("","","","");
		String modifier = "";
		// Create the Constant Object
		for(Token token : constantToken.getSubTokens())
		{
			if (token.is("modifier"))
			{
				modifier += " " + (token.getSubToken(0).getName());
				aConstant.setModifier(modifier);
			}
			else if (token.is("name"))
			{
				aConstant.setName(token.getValue());
			}
			else  if (token.is("type"))
			{
				aConstant.setType(token.getValue());
			}
			else  if (token.is("value"))
			{
				aConstant.setValue(token.getValue());
			}
		}  
		// Add constant to Class or Interface
		if (uElement instanceof UmpleClass)
		{
			UmpleClass uClass = (UmpleClass) uElement;
			uClass.addConstant(aConstant);
		}
		else if (uElement instanceof UmpleInterface)
		{
			UmpleInterface uInterface = (UmpleInterface) uElement;
			uInterface.addConstant(aConstant);
		}  
	}


	private void analyzeInjectionCode(Token injectToken, UmpleClass aClass)
	{
		String type = injectToken.is("beforeCode") ? "before" : "after";
		aClass.addCodeInjection(new CodeInjection(type,injectToken.getValue("operationName"),injectToken.getValue("code")));
	}

	private void analyzeKey(Token keyToken, UmpleClass aClass)
	{
		if (aClass.getKey().isProvided())
		{
			setFailedPosition(keyToken.getPosition());
		}

		if (keyToken.is("defaultKey"))
		{
			aClass.getKey().setIsDefault(true);
			return;
		}

		for(Token token : keyToken.getSubTokens())
		{
			if (!token.is("keyId"))
			{
				continue;
			}
			aClass.getKey().addMember(token.getValue());
		}
	}

	private void analyzeSymmetricReflexiveAssociation(Token symmetricReflexiveAssociationToken, UmpleClass aClass)
	{
		String myName = symmetricReflexiveAssociationToken.getValue("roleName");
		String myType = aClass.getName();
		String myModifier = "symmetricreflexive";
		String myBound = symmetricReflexiveAssociationToken.getValue("bound");
		String myLowerBound = symmetricReflexiveAssociationToken.getValue("lowerBound");
		String myUpperBound = symmetricReflexiveAssociationToken.getValue("upperBound");
		Multiplicity myMult = new Multiplicity();
		myMult.setBound(myBound);
		myMult.setRange(myLowerBound,myUpperBound);

		AssociationVariable myAs = new AssociationVariable(myName,myType,myModifier,null,myMult,true);
		AssociationVariable yourAs = new AssociationVariable(myName,myType,myModifier,null,myMult,true);

		myAs.setRelatedAssociation(yourAs);
		aClass.addAssociationVariable(yourAs);
	}

	private Association createAssociation(String navigation, AssociationEnd firstEnd, AssociationEnd secondEnd)
	{
		boolean isNavigable = "--".equals(navigation);
		boolean isFirstNavigable = "<-".equals(navigation) || isNavigable;
		boolean isSecondNavigable = "->".equals(navigation) || isNavigable;
		return new Association(isFirstNavigable,isSecondNavigable,firstEnd,secondEnd);
	}

	private Association analyzeAssociation(Token associationToken, String defaultMyType)
	{
		Token myMultToken = associationToken.getSubToken(0);

		String navigation = associationToken.getValue("arrow");
		Token yourMultToken = associationToken.getSubToken(2);

		String myName = myMultToken.getValue("roleName");
		String myType = myMultToken.getValue("type") == null ? defaultMyType : myMultToken.getValue("type");
		String myModifier = myMultToken.getValue("modifier");
		String myBound = myMultToken.getValue("bound");
		String myLowerBound = myMultToken.getValue("lowerBound");
		String myUpperBound = myMultToken.getValue("upperBound");
		Multiplicity myMult = new Multiplicity(); 
		myMult.setBound(myBound);
		myMult.setRange(myLowerBound,myUpperBound);

		if (!myMult.isValid())
		{
			setFailedPosition(myMultToken.getPosition());
			return null;
		}

		String yourName = yourMultToken.getValue("roleName");
		String yourType = yourMultToken.getValue("type");
		String yourModifier = yourMultToken.getValue("modifier");
		String yourBound = yourMultToken.getValue("bound");
		String yourLowerBound = yourMultToken.getValue("lowerBound");
		String yourUpperBound = yourMultToken.getValue("upperBound");
		Multiplicity yourMult = new Multiplicity();
		yourMult.setBound(yourBound);
		yourMult.setRange(yourLowerBound,yourUpperBound);

		AssociationEnd firstEnd = new AssociationEnd(myName,myType,myModifier,yourType,myMult);
		AssociationEnd secondEnd = new AssociationEnd(yourName,yourType,yourModifier,myType,yourMult);
		updateAssociationEnds(firstEnd,secondEnd);

		Association association = createAssociation(navigation,firstEnd,secondEnd);

		if (!association.isValid())
		{
			Token atFaultToken = association.whoIsInvalid() == 0 ? myMultToken : yourMultToken;
			setFailedPosition(atFaultToken.getPosition());
			return null;
		}

		positionToClassNameReference.put(yourMultToken.getPosition("type"),yourType);
		model.addAssociation(association);
		return association;
	}

	private void updateAssociationEnds(AssociationEnd firstEnd, AssociationEnd secondEnd)
	{
		if (firstEnd.getRoleName().length() == 0)
		{ 
			String rawName = StringFormatter.toCamelCase(firstEnd.getClassName());
			String name = firstEnd.getMultiplicity().isMany() ? model.getGlossary().getPlural(rawName) : rawName;
			firstEnd.setRoleName(name);
			firstEnd.setIsDefaultRoleName(true);
		}

		if (secondEnd.getRoleName().length() == 0)
		{
			String rawName = StringFormatter.toCamelCase(secondEnd.getClassName());
			String name = secondEnd.getMultiplicity().isMany() ? model.getGlossary().getPlural(rawName) : rawName;
			secondEnd.setRoleName(name);
			secondEnd.setIsDefaultRoleName(true);
		}
	}

	private void analyzeinlineAssociation(Token inlineAssociationToken, UmpleClass aClass)
	{
		Association association = analyzeAssociation(inlineAssociationToken,aClass.getName());

		if (!getParseResult().getWasSuccess())
		{
			return;
		}

		AssociationEnd myEnd = association.getEnd(0);
		AssociationEnd yourEnd = association.getEnd(1);

		AssociationVariable myAs = new AssociationVariable(myEnd.getRoleName(),myEnd.getClassName(),myEnd.getModifier(),null,myEnd.getMultiplicity(),association.getIsLeftNavigable());
		AssociationVariable yourAs = new AssociationVariable(yourEnd.getRoleName(),yourEnd.getClassName(),yourEnd.getModifier(),null,yourEnd.getMultiplicity(),association.getIsRightNavigable());
		myAs.setRelatedAssociation(yourAs);

		unlinkedAssociationVariables.add(yourAs);
		aClass.addAssociationVariable(yourAs);
		aClass.addAssociation(association);
	}

	private void analyzeStateMachineDefinition(Token stateMachineDefinitionToken)
	{
		StateMachine smd = analyzeStateMachine(stateMachineDefinitionToken,null);
		model.addStateMachineDefinition(smd);
	}

	private void analyzedReferencedStateMachine(Token stateMachineToken, UmpleClass aClass)
	{
		String name = stateMachineToken.getValue("name");
		String value = stateMachineToken.getValue("definitionName");
		umpleClassToStateMachineDefinition.put(aClass,new Pair(name,value));


		// analyze meta states
	}

	private StateMachine analyzeStateMachine(Token stateMachineToken, UmpleClass aClass)
	{
		placeholderStateMachine = new StateMachine("PLACE_HOLDER");
		String name = stateMachineToken.getValue("name");
		stateMachineNameToToken.put(name,stateMachineToken);
		StateMachine sm = new StateMachine(name);
		sm.setUmpleClass(aClass);
		populateStateMachine(stateMachineToken, sm);

		while (placeholderStateMachine.numberOfStates() > 0)
		{
			placeholderStateMachine.getState(0).setStateMachine(sm);
		}
		return sm;
	}

	private State createStateFromTransition(Token transitionToken, StateMachine sm)
	{
		String name = transitionToken.getValue("stateName");
		State nextState = sm.findState(name);
		if (nextState == null)
		{
			nextState = placeholderStateMachine.findState(name);
		}

		if (nextState == null)
		{
			nextState = new State(transitionToken.getValue("stateName"),placeholderStateMachine);
		}
		return nextState;
	}

	private State createStateFromDefinition(Token stateToken, StateMachine sm)
	{
		State s = sm.findState(stateToken.getValue("stateName"),false);
		if (s == null)
		{
			s = placeholderStateMachine.findState(stateToken.getValue("stateName"));
			if (s != null)
			{
				s.setStateMachine(sm);
			}
		}
		if (s == null)
		{
			s = new State(stateToken.getValue("stateName"),sm);
		}
		return s;
	}

	private void populateStateMachine(Token stateMachineToken, StateMachine sm)
	{
		boolean isFirst = true;
		for(Token stateToken : stateMachineToken.getSubTokens())
		{
			if (!stateToken.is("state") && !stateToken.is("stateName"))
			{
				continue;
			}

			State s = createStateFromDefinition(stateToken,sm);
			if (isFirst)
			{
				s.setIsStartState(true);
			}
			isFirst = false;
			analyzeState(stateToken, s);
		}
	}

	private void analyzeState(Token stateToken, State fromState)
	{
		for(Token subToken : stateToken.getSubTokens())
		{
			if (subToken.is("transition"))
			{
				analyzeTransition(subToken, fromState); 
				continue;
			}
			else if (subToken.is("activity"))
			{
				analyzeActivity(subToken, fromState);
			}
			else if (subToken.is("entryOrExitAction"))
			{
				Action action = new Action(subToken.getValue("actionCode"));
				action.setActionType(subToken.getValue("type"));
				fromState.addAction(action);
			}
			else if (subToken.is("state"))
			{
				StateMachine nestedStateMachine = null;
				boolean isFirst = fromState.numberOfNestedStateMachines() == 0; 
				if (isFirst)
				{
					nestedStateMachine = new StateMachine(fromState.getName());
					fromState.addNestedStateMachine(nestedStateMachine);
				}
				else
				{
				  int lastIndex = fromState.numberOfNestedStateMachines() - 1;
				  nestedStateMachine = fromState.getNestedStateMachine(lastIndex);
				}
				State s = createStateFromDefinition(subToken,nestedStateMachine);
				//alignStateDefinitionWithStateMachine(s,nestedStateMachine);
				if (isFirst)
				{
					s.setIsStartState(true);
				}
				analyzeState(subToken, s);
			}
		}
	}

	private void analyzeActivity(Token activityToken, State fromState)
	{
		new Activity(activityToken.getValue("activityCode"),fromState);
	}


	private void analyzeTransition(Token transitionToken, State fromState)
	{
		State nextState = createStateFromTransition(transitionToken,fromState.getStateMachine());
		Transition t = new Transition(fromState, nextState);

		String eventName = transitionToken.getValue("event");
		String eventTimerAmount = transitionToken.getValue("timer");

		if (eventName == null && eventTimerAmount != null)
		{
			eventName = fromState.newTimedEventName(nextState);
		}

		Token guardToken = transitionToken.getSubToken("guard");
		if (guardToken != null)
		{
			t.setGuard(new Guard(guardToken.getValue("guardCode")));
		}

		Token actionToken = transitionToken.getSubToken("action");
		if (actionToken != null)
		{
			t.setAction(new Action(actionToken.getValue("actionCode")));
		}

		if (eventName != null)
		{
			StateMachine sm = fromState.getStateMachine();
			UmpleClass uClass = sm.getUmpleClass();
			Event event = uClass == null ? sm.findOrCreateEvent(eventName) : uClass.findOrCreateEvent(eventName);
			if (eventTimerAmount != null)
			{
				event.setIsTimer(true);
				event.setTimerInSeconds(eventTimerAmount);
			}
			t.setEvent(event);
		}

	}  

	private void analyzeAttribute(Token attributeToken, UmpleClass aClass)
	{
		boolean isAutounique = attributeToken.getValue("autounique") != null;
		boolean isUnique = attributeToken.getValue("unique") != null;
		boolean isLazy = attributeToken.getValue("lazy") != null;
		String modifier = attributeToken.getValue("modifier");
		String type = attributeToken.getValue("type");
		String name = attributeToken.getValue("name");
		String value = attributeToken.getValue("value");
		String derivedValue = attributeToken.getValue("derivedValue");

		if (derivedValue != null)
		{
			value = derivedValue;
		}

		if ("defaulted".equals(modifier) && value == null)
		{
			setFailedPosition(attributeToken.getPosition());
			return;
		}

		if (isUnique)
		{
			UniqueIdentifier uniqueIdentifier = new UniqueIdentifier(name,type,modifier,value);
			aClass.setUniqueIdentifier(uniqueIdentifier);
			return;
		}

		if (isAutounique)
		{
			type = "Integer";
		}

		if (type == null)
		{
			type = "String";
		}

		Attribute attribute = new Attribute(name,type,modifier,value,isAutounique);
		attribute.setIsLazy(isLazy);
		boolean isList = attributeToken.getValue("list") != null;

		if (name == null)
		{
			String rawName = StringFormatter.toCamelCase(type); 
			name = isList ? model.getGlossary().getPlural(rawName) : rawName;
		}

		if (derivedValue != null)
		{
			attribute.setIsDerived(true);
		}

		attribute.setIsList(isList);
		aClass.addAttribute(attribute);

	}
}