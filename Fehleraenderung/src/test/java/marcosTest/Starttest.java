package marcosTest;

public class Starttest
{

	public static void main(String[] args)
	{
		Testfaelle test = new Testfaelle();
	//	test.fehlerDatetest();
	//	test.fehlerSaveVerweis();
	//	test.fehlerNeuerFehlerTest();
		test.fehlerVerwieseneTest(10);
		test.fehlerNichtVerwieseneTest(10);
	//	test.statusFolgestatusTest(4);
	//	test.manyToManyTest();
	//	test.projektTest();
	}
}
