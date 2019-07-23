/**
 * A Controller class that handles text conversion and sends it back
 * to the ConverterView class to be displayed.
 * @author Travis Abendshien (https://github.com/CyanVoxel)
 */

 // @TODO Include support for escaped characters
 // @TODO Include Color and Link tags support
 // @TODO Include list support, and possibly tables
 // @TODO Expand on the different flavors of markdown (ex. GitHub, Discord, Reddit)

package markupconverter;

public class ConverterController {

	// // Used to temporarily hold link info
	// private String tempUrl = new String();
	// private String tempUrlText = new String();

	public String convertMarkup(String input, String inputType, String outputType, boolean forceUl, boolean cullTags,
			boolean incStrongEm) {
		String output = new String();

		// Converts the text to a common style based on the inputType
		switch (inputType) {
		case "Markdown":
			// Since the internal common style is extended from markdown,
			// there is no need to convert!
			output = input;
			break;
		case "HTML5":
			output = convertHtml5ToCommon(input, forceUl, cullTags, incStrongEm);
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
		case "HTML5":
			if (input != "HTML5")
				output = convertCommonToHtml5(output, forceUl);
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

		// // Link -------------------------------------------
		// output = output.replace("[url]", "<url>");
		// output = output.replace("[/url]", "</url>");

		return output;
	}

	/**
	 * Converts HTML5 to the internal common internal markup style.
	 *
	 * @param input The input text to convert
	 * @return A string formatted to the common internal markup style.
	 *
	 */
	private String convertHtml5ToCommon(String input, boolean forceUl, boolean cullTags, boolean incStrongEm) {
		String output = input;

		// Bold -------------------------------------------
		output = output.replace("<b>", "**");
		output = output.replace("</b>", "**");
		if (incStrongEm) {
			output = output.replace("<strong>", "**");
			output = output.replace("</strong>", "**");
		}

		if (!forceUl) {
			output = output.replace("<b>", "__");
			output = output.replace("</b>", "__");
		}

		// Italic -----------------------------------------
		output = output.replace("<i>", "*");
		output = output.replace("</i>", "*");
		if (incStrongEm) {
			output = output.replace("<em>", "*");
			output = output.replace("</em>", "*");
		}

		if (!forceUl) {
			output = output.replace("<i>", "_");
			output = output.replace("</i>", "_");
		}

		// Underline --------------------------------------
		if (forceUl) {
			output = output.replace("<u>", "_");
			output = output.replace("</u>", "_");
		} else if (cullTags) {
			output = output.replace("<u>", "");
			output = output.replace("</u>", "");
		}

		// Strikethrough ----------------------------------
		output = output.replace("<s>", "~");
		output = output.replace("</s>", "~");

		// Blockquote -------------------------------------
		output = output.replace("<blockquote>", "```");
		output = output.replace("</blockquote>", "```");

		// Code -------------------------------------------
		output = output.replace("<code>", "`");
		output = output.replace("</code>", "`");

		// Link -------------------------------------------

		return output;

	} // convertHtml5ToCommon()

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

		// @TODO Process lists here, looking for a newline + asterisks + space

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

		// // Link -------------------------------------------
		// while (output.indexOf("<url>") >= 0) {
		// 	output = output.replaceFirst("<url>", "[url]");
		// 	if (output.indexOf("</url>") >= 0) {
		// 		output = output.replaceFirst("</url>", "[/url]");
		// 	}
		// }

		return output;

	} // convertCommonToBbcode()

	/**
	 * Converts the internal common internal markup style to HTML5.
	 *
	 * @param input The input text to convert
	 * @return A string formatted to BBCode
	 *
	 */
	private String convertCommonToHtml5(String input, boolean forceUl) {
		String output = input;

		// Bold -------------------------------------------
		while (output.indexOf("**") >= 0) {
			output = output.replaceFirst("\\*\\*", "<b>");
			if (output.indexOf("**") >= 0) {
				output = output.replaceFirst("\\*\\*", "</b>");
			}
		}
		if (!forceUl) {
			while (output.indexOf("__") >= 0) {
				output = output.replaceFirst("__", "<b>");
				if (output.indexOf("__") >= 0) {
					output = output.replaceFirst("__", "</b>");
				}
			}
		} // if (forceUl)

		// @TODO Process lists here, looking for a newline + asterisks + space

		// Italic -----------------------------------------
		while (output.indexOf("*") >= 0) {
			output = output.replaceFirst("\\*", "<i>");
			if (output.indexOf("*") >= 0) {
				output = output.replaceFirst("\\*", "</i>");
			}
		}
		if (!forceUl) {
			while (output.indexOf("_") >= 0) {
				output = output.replaceFirst("_", "<i>");
				if (output.indexOf("_") >= 0) {
					output = output.replaceFirst("_", "</i>");
				}
			}
		} // if (forceUl)

		// Underline --------------------------------------
		if (forceUl) {
			while (output.indexOf("_") >= 0) {
				output = output.replaceFirst("_", "<u>");
				if (output.indexOf("_") >= 0) {
					output = output.replaceFirst("_", "</u>");
				}
			}
		} // if (forceUl)

		// Strikethrough ----------------------------------
		while (output.indexOf("~") >= 0) {
			output = output.replaceFirst("~", "<s>");
			if (output.indexOf("~") >= 0) {
				output = output.replaceFirst("~", "</s>");
			}
		}

		// Blockquote -------------------------------------
		while (output.indexOf("```") >= 0) {
			output = output.replaceFirst("```", "<blockquote>");
			if (output.indexOf("```") >= 0) {
				output = output.replaceFirst("```", "</blockquote>");
			}
		}

		// Code -------------------------------------------
		while (output.indexOf("`") >= 0) {
			output = output.replaceFirst("`", "<code>");
			if (output.indexOf("`") >= 0) {
				output = output.replaceFirst("`", "</code>");
			}
		}

		// // Link -------------------------------------------
		// while (output.indexOf("<url>") >= 0) {
		// 	output = output.replaceFirst("<url>", "<a href=\"" + tempUrl + "\">" + tempUrlText);
		// 	if (output.indexOf("</url>") >= 0) {
		// 		output = output.replaceFirst("</url>", "</a>");
		// 	}
		// }

		return output;

	} // convertCommonToHtml5()

} // ConverterController class