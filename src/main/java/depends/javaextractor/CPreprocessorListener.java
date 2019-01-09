// Generated from CPreprocessor.g4 by ANTLR 4.4
package depends.javaextractor;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CPreprocessorParser}.
 */
public interface CPreprocessorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CPreprocessorParser#pp_ignore}.
	 * @param ctx the parse tree
	 */
	void enterPp_ignore(@NotNull CPreprocessorParser.Pp_ignoreContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPreprocessorParser#pp_ignore}.
	 * @param ctx the parse tree
	 */
	void exitPp_ignore(@NotNull CPreprocessorParser.Pp_ignoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPreprocessorParser#pp_include}.
	 * @param ctx the parse tree
	 */
	void enterPp_include(@NotNull CPreprocessorParser.Pp_includeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPreprocessorParser#pp_include}.
	 * @param ctx the parse tree
	 */
	void exitPp_include(@NotNull CPreprocessorParser.Pp_includeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPreprocessorParser#pp_define}.
	 * @param ctx the parse tree
	 */
	void enterPp_define(@NotNull CPreprocessorParser.Pp_defineContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPreprocessorParser#pp_define}.
	 * @param ctx the parse tree
	 */
	void exitPp_define(@NotNull CPreprocessorParser.Pp_defineContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPreprocessorParser#non_preprocessor}.
	 * @param ctx the parse tree
	 */
	void enterNon_preprocessor(@NotNull CPreprocessorParser.Non_preprocessorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPreprocessorParser#non_preprocessor}.
	 * @param ctx the parse tree
	 */
	void exitNon_preprocessor(@NotNull CPreprocessorParser.Non_preprocessorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPreprocessorParser#preprocessor}.
	 * @param ctx the parse tree
	 */
	void enterPreprocessor(@NotNull CPreprocessorParser.PreprocessorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPreprocessorParser#preprocessor}.
	 * @param ctx the parse tree
	 */
	void exitPreprocessor(@NotNull CPreprocessorParser.PreprocessorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPreprocessorParser#token_sequence}.
	 * @param ctx the parse tree
	 */
	void enterToken_sequence(@NotNull CPreprocessorParser.Token_sequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPreprocessorParser#token_sequence}.
	 * @param ctx the parse tree
	 */
	void exitToken_sequence(@NotNull CPreprocessorParser.Token_sequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPreprocessorParser#ignore}.
	 * @param ctx the parse tree
	 */
	void enterIgnore(@NotNull CPreprocessorParser.IgnoreContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPreprocessorParser#ignore}.
	 * @param ctx the parse tree
	 */
	void exitIgnore(@NotNull CPreprocessorParser.IgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPreprocessorParser#translation_unit}.
	 * @param ctx the parse tree
	 */
	void enterTranslation_unit(@NotNull CPreprocessorParser.Translation_unitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPreprocessorParser#translation_unit}.
	 * @param ctx the parse tree
	 */
	void exitTranslation_unit(@NotNull CPreprocessorParser.Translation_unitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPreprocessorParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull CPreprocessorParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPreprocessorParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull CPreprocessorParser.ProgramContext ctx);
}