package acg.project.cli.parser;

import acg.architecture.datatype.*;
import acg.project.action.ActionBehavioral;
import acg.project.action.ActionSet;
import acg.project.action.command.ParameterAssignment;
import acg.project.action.command.behavioral.*;
import acg.project.action.command.creational.create.*;
import acg.project.action.command.miscellaneous.*;
import acg.project.action.command.structural.*;
import acg.project.action.command.creational.define.*;

import java.util.*;


public class CommandParser{

    private ActionSet actions;
    private String command;


public CommandParser(ActionSet actions, String command){
    this.actions = actions;
    this.command = command;

}

public void interpret() throws RuntimeException{
    //multiple commands
    String[] splt = command.split(";");

    for (String cmd : splt) {
        cmd = cmd.trim(); //remove trailing and leading whitespace from commands
        cmd = parseRemoveComments(cmd); //remove comments from command string
        String[] args = cmd.split(" ");
        switch (args[0].toLowerCase()) {
            case "":
                //Base case, line contained only a comment or was blank
                break;
            case "define":
                //TODO your helper function
                break;
            case "show":
                //TODO your helper function
                break;
            case "create":
                createParser(args);
                break;
            case "populate":
                populateParser(args);
                break;
            case "commit":
                commitParser(args);
                break;
            case "do":
                doParser(args);
                break;
            case "@do":
                atDoParser(args);
                break;
            case "@clock":
                clockParser(args);
                break;
            case "@run":
                runParser(cmd);
                break;
            case "@wait":
                waitParser(args);
                break;
            default:
                throw new RuntimeException("Unknown identifier (" + args[0] + ") in command.");
        }
    }
}

private String parseRemoveComments(String command){
    String ret = command;
    if (command.contains("//")){
            ret = command.split("//")[0];
        }
    return ret;
}

private void defineParser(String argString[]) throws RuntimeException{
   //used for DEFINE type commands.
   //Not completely sure about input formatting at this time.
   //but we'll start parsing it.
   //assuming the entire space delimited string gets passed.
   String toggleString = argString[1].toLowerCase();


      if (toggleString.equals("trap")){
       //0     1     2     3     4        5        6        7        8        9     A        B     C     D        E     F                
   //1 DEFINE TRAP <tid> ORIGIN <origin> AZIMUTH <azimuth> WIDTH <distance> LIMIT WEIGHT <weight> SPEED <speed> MISS <percent>
 //  CommandCreationalDefineTrap trap = new CommandCreationalDefineTrap(new Identifier(argString[2]), argString[4], argString[6], argString[8], argString[11], argString[13], argString[15]);
      }
      else if (toggleString.equals("catapult")){
       //0        1     2     3        4        5        6        7        8        9              A        
   //2 DEFINE CATAPULT <tid> ORIGIN <origin> AZIMUTH <azimuth> LENGTH <distance> ACCELERATION <acceleration> 
         //B     C      D     E        F     10    11
      //LIMIT WEIGHT <weight> SPEED <speed> RESET <time>
      }
      else if (toggleString.equals("ols_xmt")){
       //0        1     2     3        4        5        6        7        8        9              A      B       C      
   //3 DEFINE OLS_XMT <tid> ORIGIN <origin> AZIMUTH <azimuth> ELEVATION <elevation> RANGE <distance1> DIAMETER <distance2>
      }
      else if (toggleString.equals("carrier")){
       //0        1     2     3   4      5       6        7        8        9      A      B     C        D        E       
   //4 DEFINE CARRIER <tid> SPEED MAX <speed1> DELTA INCREASE <speed2> DECREASE <speed3> TURN <azimuth> LAYOUT <string>
      }
      else if (toggleString.equals("fighter")){
         //0     1      2     3     4     5             6      7              8     9      
   //5 DEFINE FIGHTER <tid> SPEED MIN speedmin<speed1> MAX speedmax<speed2> DELTA INCREASE
         //A               B           C              D        E           F     10
      //dspeedinc<speed3> DECREASE dspeeddec<speed4> TURN dturn<azimuth> CLIMB dclimb<altitude1>
         //11        12             12       14       15          16    17       18
      //DESCENT ddescent<altitude2> EMPTY WEIGHT weight<weight1> FUEL INITIAL fuelinit<weight2>
       // 19      1A
      //DELTA dfuel<weight3> 
      }
      else if (toggleString.equals("tanker")){
       //0     1        2     3  4     5       6      7     8     9         A          B      C        
   //6 DEFINE TANKER <tid> SPEED MIN <speed1> MAX <speed2> DELTA INCREASE <speed3> DECREASE <speed4>
       //D     E        F        10       11       12         13     14
      //TURN <azimuth> CLIMB <altitude1> DESCENT <altitude2> TANK <weight>
      }
      else if (toggleString.equals("boom")){
               String gender = argString[2].toLowerCase();
               if (gender.equals("male")){
                // 0    1     2    3      4        5        6        7           8     9
            //7 DEFINE BOOM MALE <tid> LENGTH <distance1> DIAMETER <distance2> FLOW <weight>
               }
               else if (gender.equals("female")){
                // 0    1     2      3      4        5        6        7           8            9
            //8 DEFINE BOOM FEMALE <tid> LENGTH <distance1> DIAMETER <distance2> ELEVATION <elevation>
            //  A     B
            //FLOW <weight>
               }
              
       }
       else if (toggleString.equals("barrier")){
        //0      1      2     3       4        5        6      7        8       9      A     
   //9 DEFINE BARRIER <tid> ORIGIN <origin> AZIMUTH <azimuth> WIDTH <distance> TIME <time>
       }
       else
       {
         throw new RuntimeException();
       }
   //THIS IS SO THE COMPILER DOESN'T HATE ME.\
    //TODO
    //This is where you will submit your command to the ActionSet action processor
}

private void showParser(String argString[]){
   //parses SHOW commands
   //A SHOW TEMPLATE <tid>
}


//Parses all DO commands
private void doParser(String args[]) throws RuntimeException{
    ActionBehavioral processor = actions.getActionBehavioral(); //used to submit commands
    String cmd = args[2].toLowerCase();
    Identifier id = new Identifier(args[1]);
    try {
        switch (cmd) {
            case "ask":
		        doAskParser(args);
                break;
            case "catapult":
                doCatapultParser(args);
                break;
            case "set":
                parseSubmitSetHeading(args);
                break;
            case "boom":
                parseSubmitBoom(args);
                break;
            default:
                throw new RuntimeException("Unknown parameter in DO command (" + cmd + ")");
        }
    }
    catch (Exception e){
        throw new RuntimeException("Error parsing parameters for DO command: " + Arrays.toString(args) +
                "\nException MSG: " + e.getMessage());
    }
}




    //parses all @DO commands
private void atDoParser(String args[]) throws RuntimeException{
    String toBeForced = args[3].toLowerCase();
    Identifier id = new Identifier(args[1]);
    ActionBehavioral processor = actions.getActionBehavioral();
    try {
        switch (toBeForced) {
            case "coordinates":
                parseForceCoordinates(args, id, processor);
                break;
            case "heading":
                AngleNavigational a = parseHeading(args[4]);
                CommandBehavioralDoForceHeading sbmt = new CommandBehavioralDoForceHeading(id, a);
                actions.getActionBehavioral().submit(sbmt);
                break;
            default:
                throw new RuntimeException("Unknown parameter in @DO command (" + toBeForced + ")");
        }
    }
    catch (Exception e){
        e.printStackTrace();
        throw new RuntimeException("Error parsing parameters for @DO command: " + Arrays.toString(args) + "\nException Message: " + e.getMessage() );
    }
}

//TODO check other params for spelling etc
private void parseForceCoordinates(String args[], Identifier id, ActionBehavioral processor) throws RuntimeException{
    String[] latlon = args[4].split("/");
    Latitude lat = (Latitude)parseLatitudeLongitude(latlon[0], true);
    Longitude lon =(Longitude)parseLatitudeLongitude(latlon[1], false);
    CoordinateWorld cw = new CoordinateWorld(lat, lon);
    if(args.length == 5){
        CommandBehavioralDoForceCoordinates sbmt = new CommandBehavioralDoForceCoordinates(id, cw);
        processor.submit(sbmt);
    }
    else if(args.length == 9){
        AngleNavigational a = parseHeading(args[6]);
        Speed s = new Speed(Integer.parseUnsignedInt(args[8]));
        CommandBehavioralDoForceAll sbmt = new CommandBehavioralDoForceAll(id, cw, a, s);
        processor.submit(sbmt);
    }
    else if(args.length == 11){
        Altitude alt = new Altitude(Integer.parseUnsignedInt(args[6]));
        AngleNavigational ang = parseHeading(args[8]);
        Speed s = new Speed(Integer.parseUnsignedInt(args[10]));
        CommandBehavioralDoForceAll sbmt = new CommandBehavioralDoForceAll(id, cw, alt, ang, s);
        processor.submit(sbmt);
    }
}

//Pass it a single lat/long coordinate and it will parse it and return supertype
    //E.G. '45*30'15'
private A_LatitudeLongitude parseLatitudeLongitude(String toParse, Boolean isLat) throws RuntimeException{
    try {
        String[] args = toParse.split("\\D");;
        int deg = Integer.parseInt(args[0]);
        int min = Integer.parseInt(args[1]);
        double sec = Double.parseDouble(args[2]);
        return (isLat) ? new Latitude(deg, min, sec) : new Longitude(deg, min, sec);
    }
    catch (Exception e){
        throw new RuntimeException("Failed to parse coordinates: " + toParse);
    }
}



//Parses all @CLOCK commands
private void clockParser(String[] args) throws RuntimeException{
    // @CLOCK
    if(args.length == 1){
        CommandMiscDoShowClock sbmt = new CommandMiscDoShowClock();
        actions.getActionMisc().submit(sbmt);
    }
    //@CLOCK PAUSE|RESUME|UPDATE
    else if(args.length == 2){
        if(args[1].equalsIgnoreCase("PAUSE")){
            CommandMiscDoSetClockRunning sbmt = new CommandMiscDoSetClockRunning(false);
            actions.getActionMisc().submit(sbmt);
        }
        else if(args[1].equalsIgnoreCase("RESUME")){
            CommandMiscDoSetClockRunning sbmt = new CommandMiscDoSetClockRunning(true);
            actions.getActionMisc().submit(sbmt);
        }
        else if(args[1].equalsIgnoreCase("UPDATE")){
            CommandMiscDoClockUpdate sbmt = new CommandMiscDoClockUpdate();
            actions.getActionMisc().submit(sbmt);
        }
        // @CLOCK <rate>
        else{
            try{
                Rate r = new Rate(Integer.parseUnsignedInt(args[1]));
                CommandMiscDoSetClockRate sbmt = new CommandMiscDoSetClockRate(r);
                actions.getActionMisc().submit(sbmt);
            }
            catch (NumberFormatException e) {
                throw new RuntimeException("Error parsing rate for '@CLOCK' command, parameter (" + args[1] + ")");
            }
        }
    }
    else {
        throw new RuntimeException("Error parsing for '@CLOCK' command, too many parameters");
    }
}




//This method is unique in that it takes the original String
private void runParser(String command) throws RuntimeException{
    try {
        String[] args = command.split("'");
        CommandMiscDoRun sbmt = new CommandMiscDoRun(args[1]);
        actions.getActionMisc().submit(sbmt);
    }
    catch (NumberFormatException e){
        throw new RuntimeException("Error parsing for '@RUN' command");
    }
}


private void waitParser(String[] args) throws RuntimeException{
    if(args.length < 2){
        throw new RuntimeException("Not enough parameters for '@WAIT'");
    }
    try {
        Rate r = new Rate(Integer.parseUnsignedInt(args[1]));
        CommandMiscDoWait sbmt = new CommandMiscDoWait(r);
        actions.getActionMisc().submit(sbmt);
    }
    catch (NumberFormatException e){
        throw new RuntimeException("error parsing rate for '@WAIT'");
    }
}

	private void createParser(String[] args) throws RuntimeException
	{
		String specifier = args[1];
		
		//try
		//{
			if (specifier.equalsIgnoreCase("carrier"))
				createCarrier(args);
			else if (specifier.equalsIgnoreCase("fighter"))
				createFighter(args);
			else if (specifier.equalsIgnoreCase("tanker"))
				createTanker(args);
			else if (specifier.equalsIgnoreCase("ols_xmt"))
				createOLS(args);
			else if (specifier.equalsIgnoreCase("tailhook"))
				createTailhook(args);
			else
				throw new RuntimeException("Unkown specifier within CREATE command: " + specifier);
		//}
		/*catch (Exception e)
		{
			throw new RuntimeException("Invalid format of CREATE command: " + Arrays.toString(args) +
                    "\nException MSG: " + e.getMessage() + "\nException Class: " +e.getClass());
		}*/
	}
	
	private void populateParser(String[] args) throws RuntimeException
	{
		String specifier = args[1];
		try
		{
			if (specifier.equalsIgnoreCase("carrier"))
				populateCarrier(args);
			else if (specifier.equalsIgnoreCase("world"))
				populateWorld(args);
			else
				throw new RuntimeException("Unknown specifier within POPULATE command: " + specifier);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Invalid format of CREATE command: " + Arrays.toString(args) +
                    "\nException MSG: " + e.getMessage());
		}
	}
	
	private void commitParser(String[] args) throws RuntimeException
	{
		if (args.length != 1)
			throw new RuntimeException("Invalid format of COMMIT command, too many params: " + Arrays.toString(args));
		
		this.actions.getActionStructural().submit(new CommandStructuralCommit());
	}
	
	private void createCarrier(String[] args) throws RuntimeException
	{
		if (args.length != 21)
			throw new RuntimeException("Invalid format of CREATE CARRIER command: " + Arrays.toString(args));
		
		Identifier aid1 = new Identifier(args[2]);
		Identifier tid = new Identifier(args[4]);
		Identifier aid2 = new Identifier(args[7]);
		Identifier aid3 = new Identifier(args[9]);
		Identifier aid4 = new Identifier(args[11]);
		Identifier aid5 = new Identifier(args[13]);
		
		String[] sCoords = args[16].split("/");
		Latitude lat = (Latitude) parseLatitudeLongitude(sCoords[0], true);
		Longitude lon = (Longitude) parseLatitudeLongitude(sCoords[1], false);
		CoordinateWorld coords = new CoordinateWorld(lat, lon);
		
		AngleNavigational heading = parseHeading(args[18]);
		unsignedIntCheck(args[20], "speed");
		Speed speed = new Speed((double) Integer.parseUnsignedInt(args[20]));
		
		this.actions.getActionCreationalCreate().submit(new CommandCreationalCreateCarrier(aid1, tid, aid2, aid3, aid4, aid5, coords, heading, speed));
	}
	
	private void createFighter(String[] args) throws RuntimeException
	{
		if (args.length < 12)
			throw new RuntimeException("Invalid format of CREATE FIGHTER command: " + Arrays.toString(args));
		
		Identifier aid1 = new Identifier(args[2]);
		Identifier tid = new Identifier(args[4]);
		Identifier aid2 = new Identifier(args[7]);
		Identifier aid3 = new Identifier(args[9]);
		Identifier aid4 = new Identifier(args[11]);
		
		List<Identifier> tanks = new ArrayList<>();
		List<ParameterAssignment> params = new ArrayList<>();
		
		if (args[12].equalsIgnoreCase("tanks"))
		{
			int cur = -1;
			TANKS:
			for (cur = 13; cur < args.length; cur++)
			{
				if (args[cur].equalsIgnoreCase("overriding") || args[cur].equalsIgnoreCase("at"))
					break TANKS;
				
				tanks.add(new Identifier(args[cur]));
			}
			
			if (cur < args.length && args[cur].equalsIgnoreCase("overriding"))
			{
				PARAMS:
				for (cur += 1; cur < args.length; cur += 3)
				{
					if (args[cur].equalsIgnoreCase("at"))
						break PARAMS;
					
					params.add(new ParameterAssignment(new Identifier(args[cur]), args[cur + 2]));
				}
				
				if (cur < args.length && args[cur].equalsIgnoreCase("at")) // CASE: TANKS, OVERRIDING, AT COORDINATES
				{
					cur += 2;
					String[] sCoords = args[cur].split("/");
					Latitude lat = (Latitude) parseLatitudeLongitude(sCoords[0], true);
					Longitude lon = (Longitude) parseLatitudeLongitude(sCoords[1], false);
					CoordinateWorld coords = new CoordinateWorld(lat, lon);
					
					cur += 2;
					unsignedIntCheck(args[cur], "altitude");
					Altitude alt = new Altitude(Integer.parseInt(args[cur]));
					
					cur += 2;
					AngleNavigational course = parseHeading(args[cur]);
					
					cur += 2;
					unsignedIntCheck(args[cur], "speed");
					Speed speed = new Speed(Integer.parseInt(args[cur]));
					
					this.actions.getActionCreationalCreate().submit(new CommandCreationalCreateFighter(aid1, tid, aid2, aid3, aid4, tanks, params, coords, alt, course, speed));
				}
				else // CASE: TANKS, OVERRIDING
					this.actions.getActionCreationalCreate().submit(new CommandCreationalCreateFighter(aid1, tid, aid2, aid3, aid4, tanks, params));
			}
			else if (cur < args.length && args[cur].equalsIgnoreCase("at")) // CASE: TANKS, AT COORDINATES
			{
				cur += 2;
				String[] sCoords = args[cur].split("/");
				Latitude lat = (Latitude) parseLatitudeLongitude(sCoords[0], true);
				Longitude lon = (Longitude) parseLatitudeLongitude(sCoords[1], false);
				CoordinateWorld coords = new CoordinateWorld(lat, lon);
				
				cur += 2;
				unsignedIntCheck(args[cur], "altitude");
				Altitude alt = new Altitude(Integer.parseInt(args[cur]));
				
				cur += 2;
				AngleNavigational course = parseHeading(args[cur]);
				
				cur += 2;
				unsignedIntCheck(args[cur], "speed");
				Speed speed = new Speed(Integer.parseInt(args[cur]));
				
				String ret = aid1.toString() + "\n" + tid.toString() + "\n" + aid2.toString() + "\n" + aid3.toString() + "\n" + aid4.toString() + "\n";
				for (int x = 0; x < tanks.size(); x++)
					ret += tanks.get(x).toString() + "\n";
				for (int x = 0; x < params.size(); x++)
					ret += params.get(x).toString() + "\n";
				ret += coords.toString() + "\n" + alt.toString() + "\n" + course.toString() + "\n" + speed.toString();
				System.out.println(ret);
				
				this.actions.getActionCreationalCreate().submit(new CommandCreationalCreateFighter(aid1, tid, aid2, aid3, aid4, tanks, params, coords, alt, course, speed));
			}
			else // CASE: TANKS
				this.actions.getActionCreationalCreate().submit(new CommandCreationalCreateFighter(aid1, tid, aid2, aid3, aid4, tanks, params));
		}
		else if (args[12].equalsIgnoreCase("overriding"))
		{
			int cur = -1;
			PARAMS:
			for (cur = 13; cur < args.length; cur += 3)
			{
				if (args[cur].equalsIgnoreCase("at"))
					break PARAMS;
				
				params.add(new ParameterAssignment(new Identifier(args[cur]), args[cur + 2]));
			}
			
			if (cur < args.length && args[cur].equalsIgnoreCase("at")) // CASE: OVERRIDING, AT COORDINATES
			{
				cur += 2;
				String[] sCoords = args[cur].split("/");
				Latitude lat = (Latitude) parseLatitudeLongitude(sCoords[0], true);
				Longitude lon = (Longitude) parseLatitudeLongitude(sCoords[1], false);
				CoordinateWorld coords = new CoordinateWorld(lat, lon);
				
				cur += 2;
				unsignedIntCheck(args[cur], "altitude");
				Altitude alt = new Altitude(Integer.parseInt(args[cur]));
				
				cur += 2;
				AngleNavigational course = parseHeading(args[cur]);
				
				cur += 2;
				unsignedIntCheck(args[cur], "speed");
				Speed speed = new Speed(Integer.parseInt(args[cur]));
				
				String ret = aid1.toString() + "\n" + tid.toString() + "\n" + aid2.toString() + "\n" + aid3.toString() + "\n" + aid4.toString() + "\n";
				for (int x = 0; x < tanks.size(); x++)
					ret += tanks.get(x).toString() + "\n";
				for (int x = 0; x < params.size(); x++)
					ret += params.get(x).toString() + "\n";
				ret += coords.toString() + "\n" + alt.toString() + "\n" + course.toString() + "\n" + speed.toString();
				System.out.println(ret);
				this.actions.getActionCreationalCreate().submit(new CommandCreationalCreateFighter(aid1, tid, aid2, aid3, aid4, tanks, params, coords, alt, course, speed));
			}
			else // CASE: OVERRIDING
				this.actions.getActionCreationalCreate().submit(new CommandCreationalCreateFighter(aid1, tid, aid2, aid3, aid4, tanks, params));
		}
		else if (args[12].equalsIgnoreCase("at")) // CASE: AT COORDINATES
		{
			String[] sCoords = args[14].split("/");
			Latitude lat = (Latitude) parseLatitudeLongitude(sCoords[0], true);
			Longitude lon = (Longitude) parseLatitudeLongitude(sCoords[1], false);
			CoordinateWorld coords = new CoordinateWorld(lat, lon);
			
			unsignedIntCheck(args[16], "altitude");
			Altitude alt = new Altitude(Integer.parseInt(args[16]));
			AngleNavigational course = parseHeading(args[18]);
			unsignedIntCheck(args[20], "speed");
			Speed speed = new Speed(Integer.parseInt(args[20]));
			
			this.actions.getActionCreationalCreate().submit(new CommandCreationalCreateFighter(aid1, tid, aid2, aid3, aid4, tanks, params, coords, alt, course, speed));
		}
		else // CASE: no optional commands
			this.actions.getActionCreationalCreate().submit(new CommandCreationalCreateFighter(aid1, tid, aid2, aid3, aid4, tanks, params));
	}
	
	private void createTanker(String[] args) throws RuntimeException
	{
		if (args.length != 17)
			throw new RuntimeException("Invalid format CREATE TANKER command: " + Arrays.toString(args));
		
		Identifier aid1 = new Identifier(args[2]);
		Identifier tid = new Identifier(args[4]);
		Identifier aid2 = new Identifier(args[7]);
		
		String[] sCoords = args[10].split("/");
		Latitude lat = (Latitude) parseLatitudeLongitude(sCoords[0], true);
		Longitude lon = (Longitude) parseLatitudeLongitude(sCoords[1], false);
		CoordinateWorld coords = new CoordinateWorld(lat, lon);
		
		unsignedIntCheck(args[12], "altitude");
		Altitude alt = new Altitude(Integer.parseInt(args[12]));
		AngleNavigational heading = parseHeading(args[14]);
		Speed speed = new Speed((double) Integer.parseInt(args[16]));
		
		this.actions.getActionCreationalCreate().submit(new CommandCreationalCreateTanker(aid1, tid, aid2, coords, alt, heading, speed));
	}
	
	private void createOLS(String[] args) throws RuntimeException
	{
		if (args.length < 5)
			throw new RuntimeException("Invalid formate of CREATE OLS_XMT command: " + Arrays.toString(args));
		
		Identifier aid = new Identifier(args[2]);
		Identifier tid = new Identifier(args[4]);
		
		this.actions.getActionCreationalCreate().submit(new CommandCreationalCreateOLSTransmitter(aid, tid));
	}
	
	private void createTailhook(String[] args) throws RuntimeException
	{
		if (args.length < 5)
			throw new RuntimeException("Invalid formate of CREATE TAILHOOK command: " + Arrays.toString(args));
		
		Identifier aid = new Identifier(args[2]);
		Identifier tid = new Identifier(args[4]);
		
		this.actions.getActionCreationalCreate().submit(new CommandCreationalCreateTailhook(aid, tid));
	}
	
	private AngleNavigational parseHeading(String arg) throws RuntimeException
	{
		unsignedIntCheck(arg, "course");
		
		String sHeading = arg;
		if (sHeading.charAt(0) == '0')
			sHeading = sHeading.substring(1);
		double dHeading = (double) Integer.parseInt(sHeading);
		
		if (dHeading < 0.0 || !(dHeading < 360.0))
			throw new RuntimeException("Invalid course value (must be on the interval [0, 360)): " + dHeading);
		
		return new AngleNavigational(dHeading);
	}
	
	private void populateCarrier(String[] args) throws RuntimeException
	{
		if (args.length < 6)
			throw new RuntimeException("Invalid format of POPULATE CARRIER command: " + Arrays.toString(args));
		
		Identifier carrierID = new Identifier(args[3]);
		
		List<Identifier> aids = new ArrayList<>();
		for (int x = 6; x < args.length; x++)
			aids.add(new Identifier(args[x]));
		
		this.actions.getActionStructural().submit(new CommandStructuralPopulateCarrier(carrierID, aids));
	}
	
	private void populateWorld(String[] args) throws RuntimeException
	{
		if (args.length < 4)
			throw new RuntimeException("Invalid format of POPULATE WORLD command: " + Arrays.toString(args));
		
		List<Identifier> aids = new ArrayList<>();
		for (int x = 4; x < args.length; x++)
			aids.add(new Identifier(args[x]));
		
		this.actions.getActionStructural().submit(new CommandStructuralPopulateWorld(aids));
	}
	
	private void doAskParser(String[] args) throws RuntimeException
	{
		if (args.length != 4 || !args[2].equalsIgnoreCase("ask"))
			throw new RuntimeException("Invalid format of DO command: " + Arrays.toString(args));
		
		Identifier aid = new Identifier(args[1]);
		
		String specifier = args[3];
		CommandBehavioralDoAsk.E_Parameter param;
		if (specifier.equalsIgnoreCase("all"))
			param = CommandBehavioralDoAsk.E_Parameter.ALL;
		else if (specifier.equalsIgnoreCase("coordinates"))
			param = CommandBehavioralDoAsk.E_Parameter.COORDINATES;
		else if (specifier.equalsIgnoreCase("altitude"))
			param = CommandBehavioralDoAsk.E_Parameter.ALTITUDE;
		else if (specifier.equalsIgnoreCase("heading"))
			param = CommandBehavioralDoAsk.E_Parameter.HEADING;
		else if (specifier.equalsIgnoreCase("speed"))
			param = CommandBehavioralDoAsk.E_Parameter.SPEED;
		else if (specifier.equalsIgnoreCase("weight"))
			param = CommandBehavioralDoAsk.E_Parameter.WEIGHT;
		else if (specifier.equalsIgnoreCase("fuel"))
			param = CommandBehavioralDoAsk.E_Parameter.FUEL;
		else
			throw new RuntimeException("Unknown specifier following ASK: " + specifier);
		
		this.actions.getActionBehavioral().submit(new CommandBehavioralDoAsk(aid, param));
	}
	
	private void doCatapultParser(String[] args) throws RuntimeException
	{
		if (args.length != 7 || !args[2].equalsIgnoreCase("catapult"))
			throw new RuntimeException("Invalid format of DO command: " + Arrays.toString(args));
		
		Identifier aid = new Identifier(args[1]);
		
		unsignedIntCheck(args[6], "speed");
		Speed speed = new Speed((double) Integer.parseInt(args[6]));
		
		this.actions.getActionBehavioral().submit(new CommandBehavioralDoCatapult(aid, speed));
	}
	
	private void unsignedIntCheck(String arg, String type)
	{
		if (arg.contains("+") || arg.contains("-") || arg.contains("."))
			throw new RuntimeException("Invalid " + type + " value (must be unsigned int): " + arg);
	}

    private void parseSubmitSetHeading(String[] args) {
        if(!args[3].equalsIgnoreCase("HEADING")){
            throw new RuntimeException("Invalid parameter on creation of SET HEADING.");
        }

        Identifier aid = new Identifier(args[1]);

        AngleNavigational course = parseHeading(args[4]);
        CommandBehavioralDoSetHeading.E_Direction dir = CommandBehavioralDoSetHeading.E_Direction.SHORTEST;
        if(args.length > 5){
            if(args[5].equalsIgnoreCase("LEFT"))
                dir =  CommandBehavioralDoSetHeading.E_Direction.LEFT;
            else if(args[5].equalsIgnoreCase("RIGHT"))
                dir = CommandBehavioralDoSetHeading.E_Direction.RIGHT;
            else
                throw new RuntimeException("Unknown identifier when parsing SET HEADING: " + Arrays.toString(args));
        }
        CommandBehavioralDoSetHeading sbmt = new CommandBehavioralDoSetHeading(aid, course, dir);
        actions.getActionBehavioral().submit(sbmt);
    }

    private void parseSubmitBoom(String[] args) {
        if(!args[2].equalsIgnoreCase("BOOM")){
            throw new RuntimeException("Invalid parameter on creation of DO BOOM.");
        }

        Identifier aid = new Identifier(args[1]);
        boolean ext;
        if(args[3].equalsIgnoreCase("EXTEND"))
            ext = true;
        else if(args[3].equalsIgnoreCase("RETRACT"))
            ext = false;
        else
            throw new RuntimeException("Error parsing parameters for DO BOOM: " + Arrays.toString(args));

        CommandBehavioralDoBoom sbmt  = new CommandBehavioralDoBoom(aid, ext);
    }


    public String getCommand() {
        return command;
    }


}//Class
