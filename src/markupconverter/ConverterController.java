package markupconverter;

public class ConverterController {

	public String convertMarkup(String input, String inputType, String outputType) {
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
			output = convertBbcodeToCommon(input);
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
			if (input != "BBCode") output = convertCommonToBbcode(output);
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
	private String convertBbcodeToCommon(String input) {
		String output = input;

		// Bold -------------------------------------------
		output = output.replace("[b]", "**");
		output = output.replace("[/b]", "**");

		// Italic -----------------------------------------
		output = output.replace("[i]", "*");
		output = output.replace("[/i]", "*");

		return output;
	}

	/**
	 * Converts the internal common internal markup style to BBCode.
	 *
	 * @param input The input text to convert
	 * @return A string formatted to BBCode
	 *
	 */
	private String convertCommonToBbcode(String input) {
		String output = input;

		// Bold -------------------------------------------
		while (output.indexOf("**") >= 0) {
			output = output.replaceFirst("\\*\\*", "[b]");
			if (output.indexOf("**") >= 0) {
				output = output.replaceFirst("\\*\\*", "[/b]");
			}
		}

		// Italic -----------------------------------------
		while (output.indexOf("*") >= 0) {
			output = output.replaceFirst("\\*", "[i]");
			if (output.indexOf("*") >= 0) {
				output = output.replaceFirst("\\*", "[/i]");
			}
		}

		return output;
	}

} // ConverterController class