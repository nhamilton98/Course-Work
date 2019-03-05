package acg.project.cli.parser;

public class Tester {

	public static void main(String[] args) throws ParseException {
		String cmd1 = "CREATE FIGHTER f1 FROM ft WITH OLS ols BOOM boom TAILHOOK th OVERRIDING ft.speedmin WITH 45 ft.speedmax WITH 400 AT COORDINATES 10*20'30\"/30*20'10\" ALTITUDE 4500 HEADING 24 SPEED 360";
		CommandParser parse = new CommandParser(null, cmd1);
		parse.interpret();
	}
}
