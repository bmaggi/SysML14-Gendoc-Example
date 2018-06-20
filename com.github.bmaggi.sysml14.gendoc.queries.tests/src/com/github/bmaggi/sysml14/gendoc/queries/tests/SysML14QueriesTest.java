package com.github.bmaggi.sysml14.gendoc.queries.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;
import org.junit.Test;

import com.github.bmaggi.sysml14.gendoc.queries.SysML14Queries;

public class SysML14QueriesTest {

	@Test
	public void testDisplay0Comment() {
		Package pkge = UMLFactory.eINSTANCE.createPackage();
		assertEquals("", SysML14Queries.displayComment(pkge));
	}

	
	@Test
	public void testDisplay1Comment() {
		Package pkge = UMLFactory.eINSTANCE.createPackage();
		pkge.createOwnedComment().setBody("Hello");
		assertEquals("Hello"+System.getProperty("line.separator"), SysML14Queries.displayComment(pkge));
	}

	@Test
	public void testDisplay2Comment() {
		Package pkge = UMLFactory.eINSTANCE.createPackage();
		pkge.createOwnedComment().setBody("Hello");
		pkge.createOwnedComment().setBody("Bye!");
		assertEquals("Hello"+System.getProperty("line.separator")+"Bye!"+System.getProperty("line.separator"), SysML14Queries.displayComment(pkge));
	}	
	
	@Test
	public void testDisplayOperation() {
		Class clazz = UMLFactory.eINSTANCE.createClass();
		clazz.createOwnedOperation("operation", null, null);
		assertEquals("operation\n", SysML14Queries.displayOperation(clazz));
	}	
	
}
