package com.github.bmaggi.sysml14.gendoc.queries.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.UMLFactory;
import org.junit.Test;

import com.github.bmaggi.sysml14.gendoc.queries.SysML14Queries;

public class SysML14QueriesTest {

	@Test
	public void testDisplayComment() {
		Package createPackage = UMLFactory.eINSTANCE.createPackage();
		createPackage.createOwnedComment().setBody("Hello");
		assertEquals("Hello"+System.getProperty("line.separator"), SysML14Queries.displayComment(createPackage));
	}

}
