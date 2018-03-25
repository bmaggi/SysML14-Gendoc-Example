package com.github.bmaggi.sysml14.gendoc.template;

import java.net.URL;

import org.eclipse.gendoc.wizard.IGendocTemplate;

public class SysML14DocxGendocTemplate implements IGendocTemplate{
	
	public String getOutPutExtension() {
		return "docx";
	}

	public URL getTemplate() {
		return Activator.getDefault().getBundle().getEntry("/resource/templatePapyrus_sysML.docx");
	}

	public String getModelKey() {
		return "generic_generation_model";
	}

	public String getOutputKey() {
		return "generic_generation_output";
	}

	public String getDescription() {
		return "SysML MS Word 2010 generation template";
	}

	public String getName() {
		return "SysML";
	}

	public boolean isSavable() {
		return false;
	}
}
