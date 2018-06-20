package com.github.bmaggi.sysml14.gendoc.queries;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.papyrus.sysml14.deprecatedelements.FlowPort;
import org.eclipse.papyrus.sysml14.portsandflows.FlowDirection;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.util.UMLUtil;

public class SysML14Queries {

	public static String displayComment(NamedElement namedElement) {
		return namedElement.getOwnedComments().stream()
				.map(Comment::getBody)
				.map(body -> body + System.getProperty("line.separator"))
				.collect(Collectors.joining());
	}

	public static String displayPart(NamedElement e) {
		StringBuilder stringBuilder = new StringBuilder();
		LinkedList<NamedElement> parts = new LinkedList<>();
		for (Property prop : ((Classifier) e).getAllAttributes()) {
			if (prop.getType() instanceof org.eclipse.uml2.uml.Class) {
				parts.add((NamedElement) prop);
			}
		}
		
		for (NamedElement ne : parts) {
			stringBuilder.append("Part : ");
			stringBuilder.append(ne.getName());
			if (((Property) ne).getType() != null) {
				stringBuilder.append(" : ");
				stringBuilder.append(((Property) ne).getType().getName());
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	public static String displayAttribute(NamedElement namedElement) {
		StringBuilder stringBuilder = new StringBuilder();
		if (namedElement instanceof Classifier) {
			LinkedList<NamedElement> attributes = new LinkedList<>();
			for (Property prop : ((Classifier) namedElement).getAllAttributes()) {
				if (!(prop instanceof Port) && !(prop.getType() instanceof org.eclipse.uml2.uml.Class)) {
					attributes.add((NamedElement) prop);
				}
			}
			for (NamedElement ne : attributes) {
				stringBuilder.append(ne.getName());
				stringBuilder.append("\n");
			}
		}
		return stringBuilder.toString();
	}

	public static String displayOperation(NamedElement namedElement) {
		StringBuilder stringBuilder = new StringBuilder();
		if (namedElement instanceof Classifier) {
			for (NamedElement ne : ((Classifier) namedElement).getAllOperations()) {
				stringBuilder.append(ne.getName());
				stringBuilder.append("\n");
			}

		}
		return stringBuilder.toString();
	}	
	
	
	public static String displayPort(NamedElement e) {
		StringBuilder stringBuilder = new StringBuilder();
		for (NamedElement ne : getPort(e)) {
			// flow port in
			if (!ne.getAppliedStereotypes().isEmpty()) {
				FlowPort f = UMLUtil.getStereotypeApplication(ne, FlowPort.class);
				if (f.getDirection().equals(FlowDirection.IN)) {
					stringBuilder.append("Flow port in : ");
					stringBuilder.append(" : ");
					stringBuilder.append(((org.eclipse.uml2.uml.Port) ne).getType().getName());
					stringBuilder.append(ne.getName());
					stringBuilder.append("\n");
				}
			}
		}
		for (NamedElement ne : getPort(e)) {
			// flow port out
			if (!ne.getAppliedStereotypes().isEmpty()) {
				FlowPort f = UMLUtil.getStereotypeApplication(ne, FlowPort.class);
				if (f.getDirection().equals(FlowDirection.OUT)) {
					stringBuilder.append("Flow port out : ");
					stringBuilder.append(" : ");
					stringBuilder.append(((org.eclipse.uml2.uml.Port) ne).getType().getName());
					stringBuilder.append(ne.getName());
					stringBuilder.append("\n");
				}
			}
		}
		for (NamedElement ne : getPort(e)) {
			// flow port inout
			if (!ne.getAppliedStereotypes().isEmpty()) {
				FlowPort f = UMLUtil.getStereotypeApplication(ne, FlowPort.class);
				if (f.getDirection().equals(FlowDirection.INOUT)) {
					stringBuilder.append("Flow port inout : ");
					stringBuilder.append(ne.getName());
					stringBuilder.append(" : ");
					stringBuilder.append(((org.eclipse.uml2.uml.Port) ne).getType().getName());
					stringBuilder.append("\n");
				}
			}
		}
		for (NamedElement ne : getPort(e)) {
			// other
			if (ne.getAppliedStereotypes().isEmpty()) {
				stringBuilder.append("Port");
				stringBuilder.append(" : ");
				stringBuilder.append(ne.getName());
				stringBuilder.append(" : ");
				stringBuilder.append(((org.eclipse.uml2.uml.Port) ne).getType().getName());
				stringBuilder.append("\n");
			}
		}
		return stringBuilder.toString();
	}



	public static List<NamedElement> getPort(NamedElement c) {
		LinkedList<NamedElement> ports = new LinkedList<>();
		for (Element ne : c.getOwnedElements()) {
			if (ne instanceof org.eclipse.uml2.uml.Port) {
				ports.add((NamedElement) ne);
			}
		}
		return ports;
	}

}
