package com.github.bmaggi.sysml14.gendoc.queries;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.papyrus.sysml14.deprecatedelements.FlowPort;
import org.eclipse.papyrus.sysml14.portsandflows.FlowDirection;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.util.UMLUtil;

public class SysML14Queries {

	// TODO in Java 8...
	public static String displayComment(NamedElement e) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Comment com : e.getOwnedComments()) {
			stringBuilder.append(com.getBody());
			stringBuilder.append(System.getProperty("line.separator"));
		}
		return stringBuilder.toString();
	}

	public static String displayPart(NamedElement e) {
		StringBuilder stringBuilder = new StringBuilder();
		for (NamedElement ne : getParts((Classifier) e)) {
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

	public static String displayAttribute(NamedElement e) {
		StringBuilder stringBuilder = new StringBuilder();
		for (NamedElement ne : getAttributs((Classifier) e)) {
			stringBuilder.append(ne.getName());
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	public static String displayOperation(NamedElement e) {
		StringBuilder stringBuilder = new StringBuilder();
		for (NamedElement ne : getOperations((Classifier) e)) {
			stringBuilder.append(ne.getName());
			stringBuilder.append("\n");
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

	public static List<NamedElement> getParts(Classifier c) {
		LinkedList<NamedElement> parts = new LinkedList<>();
		for (Property prop : c.getAllAttributes()) {
			if (prop.getType() instanceof org.eclipse.uml2.uml.Class) {
				parts.add((NamedElement) prop);
			}
		}
		return parts;
	}

	public static List<NamedElement> getAttributs(Classifier c) {
		LinkedList<NamedElement> attributes = new LinkedList<>();
		for (Property prop : c.getAllAttributes()) {
			if (!(prop instanceof Port) && !(prop.getType() instanceof org.eclipse.uml2.uml.Class)) {
				attributes.add((NamedElement) prop);
			}
		}
		return attributes;
	}

	public static List<NamedElement> getOperations(Classifier c) {
		LinkedList<NamedElement> operations = new LinkedList<>();
		for (Operation op : c.getAllOperations()) {
			operations.add((NamedElement) op);
		}
		return operations;
	}

	public static List<NamedElement> getPackages(Model model) {
		LinkedList<NamedElement> pakages = new LinkedList<>();
		for (Element ne : model.getOwnedElements()) {
			if (ne instanceof org.eclipse.uml2.uml.Package) {
				pakages.add((NamedElement) ne);
			}
		}

		return pakages;
	}

	public static List<NamedElement> getChilds(Model model) {

		LinkedList<NamedElement> childs = new LinkedList<>();
		for (Element ne : model.getOwnedElements()) {
			if ((ne instanceof org.eclipse.uml2.uml.Class || ne instanceof org.eclipse.uml2.uml.Package)
					&& !contains(childs, (NamedElement) ne)) {
				childs.add((NamedElement) ne);
			}
		}

		return childs;
	}

	public static boolean contains(List<NamedElement> childs, NamedElement n) {
		boolean ok = false;
		for (NamedElement ne : childs) {
			if (ne.getQualifiedName().equals(n.getQualifiedName())) {
				ok = true;
			}
		}

		return ok;
	}

}
