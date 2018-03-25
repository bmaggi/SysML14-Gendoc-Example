package com.github.bmaggi.sysml14.gendoc.template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.gendoc.bundle.acceleo.papyrus.utils.OneFileUMLSelectionConverter;
import org.eclipse.gendoc.wizard.IGendocSelectionConverterRunner;
import org.eclipse.gendoc.wizard.IGendocTemplate;
import org.eclipse.gendoc.wizard.ISelectionConverter;

/**
 * The Class represent the loader for SysML 1.4 Papyrus model generation.
 */
public class SysML14GendocSelectionConverterRunner implements IGendocSelectionConverterRunner {
	
	List<IGendocTemplate> templates = new ArrayList<>();

	public SysML14GendocSelectionConverterRunner() {
		templates.add(new SysML14DocxGendocTemplate());
	}

	/**
	 * @return specify all extension of model that this runner can generate
	 *         the documentation
	 */
	public Pattern getPattern() {
		return Pattern.compile(".*\\.uml");
	}

	public ISelectionConverter getSelectionConverter() {
		return new OneFileUMLSelectionConverter();
	}

	/**
	 * @return all the template format associated to this Papyrus runner
	 */
	public List<IGendocTemplate> getGendocTemplates() {
		return templates;
	}

	public String getLabel() {
		return "SysML 1.4 Gendoc Generation";
	}

	public Map<String, String> getAdditionnalParameters() {
		return Collections.emptyMap();
	}

}
