package markupconverter;

public class ConverterController {

	public String convertMarkup(String input, String inputType, String outputType, boolean forceUl, boolean cullTags) {
		String output = new String();

		// Converts the text to a common style based on the inputType
		switch (inputType) {
		case "Markdown":
			// Since the internal common style is extended from markdown,
			// there is no need to convert!
			output = input;
			break;
		case "HTML":
			break;
		case "BBCode":
			output = convertBbcodeToCommon(input, forceUl, cullTags);
			break;
		default:
			break;
		}

		// Converts the text to a final style based on the outputType
		switch (outputType) {
		case "Markdown":
			// Currently there's no need to convert!
			break;
		case "HTML":
			break;
		case "BBCode":
			if (input != "BBCode")
				output = convertCommonToBbcode(output, forceUl);
			break;
		default:
			break;
		}

		return output;
	}

	/**
	 * Converts BBCode to the internal common internal markup style.
	 *
	 * @param input The input text to convert
	 * @return A string formatted to the common internal markup style.
	 *
	 */
	private String convertBbcodeToCommon(String input, boolean forceUl, boolean cullTags) {
		String output = input;

		// Bold -------------------------------------------
		output = output.replace("[b]", "**");
		output = output.replace("[/b]", "**");
		if (!forceUl) {
			output = output.replace("[b]", "__");
			output = output.replace("[/b]", "__");
		}

		// Italic -----------------------------------------
		output = output.replace("[i]", "*");
		output = output.replace("[/i]", "*");
		if (!forceUl) {
			output = output.replace("[i]", "_");
			output = output.replace("[/i]", "_");
		}

		// Underline --------------------------------------
		if (forceUl) {
			output = output.replace("[u]", "_");
			output = output.replace("[/u]", "_");
		} else if (cullTags) {
			output = output.replace("[u]", "");
			output = output.replace("[/u]", "");
		}

		// Strikethrough ----------------------------------
		output = output.replace("[s]", "~");
		output = output.replace("[/s]", "~");

		// Blockquote -------------------------------------
		output = output.replace("[quote]", "```");
		output = output.replace("[/quote]", "```");

		// Code -------------------------------------------
		output = output.replace("[code]", "`");
		output = output.replace("[/code]", "`");

		return output;
	}

	/**
	 * Converts the internal common internal markup style to BBCode.
	 *
	 * @param input The input text to convert
	 * @return A string formatted to BBCode
	 *
	 */
	private String convertCommonToBbcode(String input, boolean forceUl) {
		String output = input;

		// Bold -------------------------------------------
		while (output.indexOf("**") >= 0) {
			output = output.replaceFirst("\\*\\*", "[b]");
			if (output.indexOf("**") >= 0) {
				output = output.replaceFirst("\\*\\*", "[/b]");
			}
		}
		if (!forceUl) {
			while (output.indexOf("__") >= 0) {
				output = output.replaceFirst("__", "[b]");
				if (output.indexOf("__") >= 0) {
					output = output.replaceFirst("__", "[/b]");
				}
			}
		} // if (forceUl)

		// Italic -----------------------------------------
		while (output.indexOf("*") >= 0) {
			output = output.replaceFirst("\\*", "[i]");
			if (output.indexOf("*") >= 0) {
				output = output.replaceFirst("\\*", "[/i]");
			}
		}
		if (!forceUl) {
			while (output.indexOf("_") >= 0) {
				output = output.replaceFirst("_", "[i]");
				if (output.indexOf("_") >= 0) {
					output = output.replaceFirst("_", "[/i]");
				}
			}
		} // if (forceUl)

		// Underline --------------------------------------
		if (forceUl) {
			while (output.indexOf("_") >= 0) {
				output = output.replaceFirst("_", "[u]");
				if (output.indexOf("_") >= 0) {
					output = output.replaceFirst("_", "[/u]");
				}
			}
		} // if (forceUl)

		// Strikethrough ----------------------------------
		while (output.indexOf("~") >= 0) {
			output = output.replaceFirst("~", "[s]");
			if (output.indexOf("~") >= 0) {
				output = output.replaceFirst("~", "[/s]");
			}
		}

		// Blockquote -------------------------------------
		while (output.indexOf("```") >= 0) {
			output = output.replaceFirst("```", "[quote]");
			if (output.indexOf("```") >= 0) {
				output = output.replaceFirst("```", "[/quote]");
			}
		}

		// Code -------------------------------------------
		while (output.indexOf("`") >= 0) {
			output = output.replaceFirst("`", "[code]");
			if (output.indexOf("`") >= 0) {
				output = output.replaceFirst("`", "[/code]");
			}
		}

		return output;
	}

} // ConverterController class