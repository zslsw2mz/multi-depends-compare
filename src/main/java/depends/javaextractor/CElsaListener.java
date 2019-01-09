// Generated from CElsa.g4 by ANTLR 4.4
package depends.javaextractor;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CElsaParser}.
 */
public interface CElsaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CElsaParser#handler}.
	 * @param ctx the parse tree
	 */
	void enterHandler(@NotNull CElsaParser.HandlerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#handler}.
	 * @param ctx the parse tree
	 */
	void exitHandler(@NotNull CElsaParser.HandlerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#forInitStatement}.
	 * @param ctx the parse tree
	 */
	void enterForInitStatement(@NotNull CElsaParser.ForInitStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#forInitStatement}.
	 * @param ctx the parse tree
	 */
	void exitForInitStatement(@NotNull CElsaParser.ForInitStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#asmDefinition}.
	 * @param ctx the parse tree
	 */
	void enterAsmDefinition(@NotNull CElsaParser.AsmDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#asmDefinition}.
	 * @param ctx the parse tree
	 */
	void exitAsmDefinition(@NotNull CElsaParser.AsmDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#templateArgumentList}.
	 * @param ctx the parse tree
	 */
	void enterTemplateArgumentList(@NotNull CElsaParser.TemplateArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#templateArgumentList}.
	 * @param ctx the parse tree
	 */
	void exitTemplateArgumentList(@NotNull CElsaParser.TemplateArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#namespaceAliasDefinition}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceAliasDefinition(@NotNull CElsaParser.NamespaceAliasDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#namespaceAliasDefinition}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceAliasDefinition(@NotNull CElsaParser.NamespaceAliasDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#baseSpecifierList}.
	 * @param ctx the parse tree
	 */
	void enterBaseSpecifierList(@NotNull CElsaParser.BaseSpecifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#baseSpecifierList}.
	 * @param ctx the parse tree
	 */
	void exitBaseSpecifierList(@NotNull CElsaParser.BaseSpecifierListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberOther}
	 * labeled alternative in {@link CElsaParser#memberDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterMemberOther(@NotNull CElsaParser.MemberOtherContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberOther}
	 * labeled alternative in {@link CElsaParser#memberDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitMemberOther(@NotNull CElsaParser.MemberOtherContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#functionPtr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionPtr(@NotNull CElsaParser.FunctionPtrContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#functionPtr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionPtr(@NotNull CElsaParser.FunctionPtrContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#classOrNamespaceName}.
	 * @param ctx the parse tree
	 */
	void enterClassOrNamespaceName(@NotNull CElsaParser.ClassOrNamespaceNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#classOrNamespaceName}.
	 * @param ctx the parse tree
	 */
	void exitClassOrNamespaceName(@NotNull CElsaParser.ClassOrNamespaceNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#functionSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterFunctionSpecifier(@NotNull CElsaParser.FunctionSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#functionSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitFunctionSpecifier(@NotNull CElsaParser.FunctionSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#simpleTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterSimpleTypeSpecifier(@NotNull CElsaParser.SimpleTypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#simpleTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitSimpleTypeSpecifier(@NotNull CElsaParser.SimpleTypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#initDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterInitDeclarator(@NotNull CElsaParser.InitDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#initDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitInitDeclarator(@NotNull CElsaParser.InitDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#ctorInitializer}.
	 * @param ctx the parse tree
	 */
	void enterCtorInitializer(@NotNull CElsaParser.CtorInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#ctorInitializer}.
	 * @param ctx the parse tree
	 */
	void exitCtorInitializer(@NotNull CElsaParser.CtorInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#idExpression}.
	 * @param ctx the parse tree
	 */
	void enterIdExpression(@NotNull CElsaParser.IdExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#idExpression}.
	 * @param ctx the parse tree
	 */
	void exitIdExpression(@NotNull CElsaParser.IdExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#declSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterDeclSpecifier(@NotNull CElsaParser.DeclSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#declSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitDeclSpecifier(@NotNull CElsaParser.DeclSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(@NotNull CElsaParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(@NotNull CElsaParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#initializerClause}.
	 * @param ctx the parse tree
	 */
	void enterInitializerClause(@NotNull CElsaParser.InitializerClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#initializerClause}.
	 * @param ctx the parse tree
	 */
	void exitInitializerClause(@NotNull CElsaParser.InitializerClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#throwExpression}.
	 * @param ctx the parse tree
	 */
	void enterThrowExpression(@NotNull CElsaParser.ThrowExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#throwExpression}.
	 * @param ctx the parse tree
	 */
	void exitThrowExpression(@NotNull CElsaParser.ThrowExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#typeIdList}.
	 * @param ctx the parse tree
	 */
	void enterTypeIdList(@NotNull CElsaParser.TypeIdListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#typeIdList}.
	 * @param ctx the parse tree
	 */
	void exitTypeIdList(@NotNull CElsaParser.TypeIdListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#simpleDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSimpleDeclaration(@NotNull CElsaParser.SimpleDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#simpleDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSimpleDeclaration(@NotNull CElsaParser.SimpleDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#classHead}.
	 * @param ctx the parse tree
	 */
	void enterClassHead(@NotNull CElsaParser.ClassHeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#classHead}.
	 * @param ctx the parse tree
	 */
	void exitClassHead(@NotNull CElsaParser.ClassHeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(@NotNull CElsaParser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(@NotNull CElsaParser.IterationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#newPlacement}.
	 * @param ctx the parse tree
	 */
	void enterNewPlacement(@NotNull CElsaParser.NewPlacementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#newPlacement}.
	 * @param ctx the parse tree
	 */
	void exitNewPlacement(@NotNull CElsaParser.NewPlacementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#newExpression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpression(@NotNull CElsaParser.NewExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#newExpression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpression(@NotNull CElsaParser.NewExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#functionTryBlock}.
	 * @param ctx the parse tree
	 */
	void enterFunctionTryBlock(@NotNull CElsaParser.FunctionTryBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#functionTryBlock}.
	 * @param ctx the parse tree
	 */
	void exitFunctionTryBlock(@NotNull CElsaParser.FunctionTryBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#exceptionSpecification}.
	 * @param ctx the parse tree
	 */
	void enterExceptionSpecification(@NotNull CElsaParser.ExceptionSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#exceptionSpecification}.
	 * @param ctx the parse tree
	 */
	void exitExceptionSpecification(@NotNull CElsaParser.ExceptionSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#explicitInstantiation}.
	 * @param ctx the parse tree
	 */
	void enterExplicitInstantiation(@NotNull CElsaParser.ExplicitInstantiationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#explicitInstantiation}.
	 * @param ctx the parse tree
	 */
	void exitExplicitInstantiation(@NotNull CElsaParser.ExplicitInstantiationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#usingDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterUsingDeclaration(@NotNull CElsaParser.UsingDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#usingDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitUsingDeclaration(@NotNull CElsaParser.UsingDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#cvQualifier}.
	 * @param ctx the parse tree
	 */
	void enterCvQualifier(@NotNull CElsaParser.CvQualifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#cvQualifier}.
	 * @param ctx the parse tree
	 */
	void exitCvQualifier(@NotNull CElsaParser.CvQualifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#qualifiedNamespaceSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedNamespaceSpecifier(@NotNull CElsaParser.QualifiedNamespaceSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#qualifiedNamespaceSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedNamespaceSpecifier(@NotNull CElsaParser.QualifiedNamespaceSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#handlerSeq}.
	 * @param ctx the parse tree
	 */
	void enterHandlerSeq(@NotNull CElsaParser.HandlerSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#handlerSeq}.
	 * @param ctx the parse tree
	 */
	void exitHandlerSeq(@NotNull CElsaParser.HandlerSeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void enterTranslationUnit(@NotNull CElsaParser.TranslationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void exitTranslationUnit(@NotNull CElsaParser.TranslationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(@NotNull CElsaParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(@NotNull CElsaParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#templateArgument}.
	 * @param ctx the parse tree
	 */
	void enterTemplateArgument(@NotNull CElsaParser.TemplateArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#templateArgument}.
	 * @param ctx the parse tree
	 */
	void exitTemplateArgument(@NotNull CElsaParser.TemplateArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#qualifiedId}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedId(@NotNull CElsaParser.QualifiedIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#qualifiedId}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedId(@NotNull CElsaParser.QualifiedIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberDeclarationDontKnow2}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclarationDontKnow2(@NotNull CElsaParser.MemberDeclarationDontKnow2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code memberDeclarationDontKnow2}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclarationDontKnow2(@NotNull CElsaParser.MemberDeclarationDontKnow2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code memberDeclarationDontKnow1}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclarationDontKnow1(@NotNull CElsaParser.MemberDeclarationDontKnow1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code memberDeclarationDontKnow1}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclarationDontKnow1(@NotNull CElsaParser.MemberDeclarationDontKnow1Context ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#typeId}.
	 * @param ctx the parse tree
	 */
	void enterTypeId(@NotNull CElsaParser.TypeIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#typeId}.
	 * @param ctx the parse tree
	 */
	void exitTypeId(@NotNull CElsaParser.TypeIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberDeclarationTemplate}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclarationTemplate(@NotNull CElsaParser.MemberDeclarationTemplateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberDeclarationTemplate}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclarationTemplate(@NotNull CElsaParser.MemberDeclarationTemplateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#explicitSpecialization}.
	 * @param ctx the parse tree
	 */
	void enterExplicitSpecialization(@NotNull CElsaParser.ExplicitSpecializationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#explicitSpecialization}.
	 * @param ctx the parse tree
	 */
	void exitExplicitSpecialization(@NotNull CElsaParser.ExplicitSpecializationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration(@NotNull CElsaParser.ParameterDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration(@NotNull CElsaParser.ParameterDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#initDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterInitDeclaratorList(@NotNull CElsaParser.InitDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#initDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitInitDeclaratorList(@NotNull CElsaParser.InitDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(@NotNull CElsaParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(@NotNull CElsaParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#className}.
	 * @param ctx the parse tree
	 */
	void enterClassName(@NotNull CElsaParser.ClassNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#className}.
	 * @param ctx the parse tree
	 */
	void exitClassName(@NotNull CElsaParser.ClassNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#tryBlock}.
	 * @param ctx the parse tree
	 */
	void enterTryBlock(@NotNull CElsaParser.TryBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#tryBlock}.
	 * @param ctx the parse tree
	 */
	void exitTryBlock(@NotNull CElsaParser.TryBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull CElsaParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull CElsaParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#linkageSpecification}.
	 * @param ctx the parse tree
	 */
	void enterLinkageSpecification(@NotNull CElsaParser.LinkageSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#linkageSpecification}.
	 * @param ctx the parse tree
	 */
	void exitLinkageSpecification(@NotNull CElsaParser.LinkageSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#blockDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterBlockDeclaration(@NotNull CElsaParser.BlockDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#blockDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitBlockDeclaration(@NotNull CElsaParser.BlockDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#templateParameter}.
	 * @param ctx the parse tree
	 */
	void enterTemplateParameter(@NotNull CElsaParser.TemplateParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#templateParameter}.
	 * @param ctx the parse tree
	 */
	void exitTemplateParameter(@NotNull CElsaParser.TemplateParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void enterLabeledStatement(@NotNull CElsaParser.LabeledStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void exitLabeledStatement(@NotNull CElsaParser.LabeledStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberDeclarationUsing}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclarationUsing(@NotNull CElsaParser.MemberDeclarationUsingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberDeclarationUsing}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclarationUsing(@NotNull CElsaParser.MemberDeclarationUsingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberWithBitField}
	 * labeled alternative in {@link CElsaParser#memberDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterMemberWithBitField(@NotNull CElsaParser.MemberWithBitFieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberWithBitField}
	 * labeled alternative in {@link CElsaParser#memberDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitMemberWithBitField(@NotNull CElsaParser.MemberWithBitFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(@NotNull CElsaParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(@NotNull CElsaParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#ptrOperator}.
	 * @param ctx the parse tree
	 */
	void enterPtrOperator(@NotNull CElsaParser.PtrOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#ptrOperator}.
	 * @param ctx the parse tree
	 */
	void exitPtrOperator(@NotNull CElsaParser.PtrOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull CElsaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull CElsaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#pureSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterPureSpecifier(@NotNull CElsaParser.PureSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#pureSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitPureSpecifier(@NotNull CElsaParser.PureSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#pseudoDestructorName}.
	 * @param ctx the parse tree
	 */
	void enterPseudoDestructorName(@NotNull CElsaParser.PseudoDestructorNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#pseudoDestructorName}.
	 * @param ctx the parse tree
	 */
	void exitPseudoDestructorName(@NotNull CElsaParser.PseudoDestructorNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(@NotNull CElsaParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(@NotNull CElsaParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#parameterDeclarationClause}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclarationClause(@NotNull CElsaParser.ParameterDeclarationClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#parameterDeclarationClause}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclarationClause(@NotNull CElsaParser.ParameterDeclarationClauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberDeclarationMacro}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclarationMacro(@NotNull CElsaParser.MemberDeclarationMacroContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberDeclarationMacro}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclarationMacro(@NotNull CElsaParser.MemberDeclarationMacroContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeSpecifier(@NotNull CElsaParser.TypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeSpecifier(@NotNull CElsaParser.TypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(@NotNull CElsaParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(@NotNull CElsaParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#accessSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterAccessSpecifier(@NotNull CElsaParser.AccessSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#accessSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitAccessSpecifier(@NotNull CElsaParser.AccessSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer(@NotNull CElsaParser.InitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer(@NotNull CElsaParser.InitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#baseSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterBaseSpecifier(@NotNull CElsaParser.BaseSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#baseSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitBaseSpecifier(@NotNull CElsaParser.BaseSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#memInitializer}.
	 * @param ctx the parse tree
	 */
	void enterMemInitializer(@NotNull CElsaParser.MemInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#memInitializer}.
	 * @param ctx the parse tree
	 */
	void exitMemInitializer(@NotNull CElsaParser.MemInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#operatorFunc}.
	 * @param ctx the parse tree
	 */
	void enterOperatorFunc(@NotNull CElsaParser.OperatorFuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#operatorFunc}.
	 * @param ctx the parse tree
	 */
	void exitOperatorFunc(@NotNull CElsaParser.OperatorFuncContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberFunction}
	 * labeled alternative in {@link CElsaParser#memberDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterMemberFunction(@NotNull CElsaParser.MemberFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberFunction}
	 * labeled alternative in {@link CElsaParser#memberDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitMemberFunction(@NotNull CElsaParser.MemberFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#constantInitializer}.
	 * @param ctx the parse tree
	 */
	void enterConstantInitializer(@NotNull CElsaParser.ConstantInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#constantInitializer}.
	 * @param ctx the parse tree
	 */
	void exitConstantInitializer(@NotNull CElsaParser.ConstantInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#classKey}.
	 * @param ctx the parse tree
	 */
	void enterClassKey(@NotNull CElsaParser.ClassKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#classKey}.
	 * @param ctx the parse tree
	 */
	void exitClassKey(@NotNull CElsaParser.ClassKeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#unqualifiedId}.
	 * @param ctx the parse tree
	 */
	void enterUnqualifiedId(@NotNull CElsaParser.UnqualifiedIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#unqualifiedId}.
	 * @param ctx the parse tree
	 */
	void exitUnqualifiedId(@NotNull CElsaParser.UnqualifiedIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(@NotNull CElsaParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(@NotNull CElsaParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#originalNamespaceName}.
	 * @param ctx the parse tree
	 */
	void enterOriginalNamespaceName(@NotNull CElsaParser.OriginalNamespaceNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#originalNamespaceName}.
	 * @param ctx the parse tree
	 */
	void exitOriginalNamespaceName(@NotNull CElsaParser.OriginalNamespaceNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#namespaceAlias}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceAlias(@NotNull CElsaParser.NamespaceAliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#namespaceAlias}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceAlias(@NotNull CElsaParser.NamespaceAliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#templateId}.
	 * @param ctx the parse tree
	 */
	void enterTemplateId(@NotNull CElsaParser.TemplateIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#templateId}.
	 * @param ctx the parse tree
	 */
	void exitTemplateId(@NotNull CElsaParser.TemplateIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#pmExpression}.
	 * @param ctx the parse tree
	 */
	void enterPmExpression(@NotNull CElsaParser.PmExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#pmExpression}.
	 * @param ctx the parse tree
	 */
	void exitPmExpression(@NotNull CElsaParser.PmExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#namespaceName}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceName(@NotNull CElsaParser.NamespaceNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#namespaceName}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceName(@NotNull CElsaParser.NamespaceNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#typedefName}.
	 * @param ctx the parse tree
	 */
	void enterTypedefName(@NotNull CElsaParser.TypedefNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#typedefName}.
	 * @param ctx the parse tree
	 */
	void exitTypedefName(@NotNull CElsaParser.TypedefNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#stringBlock}.
	 * @param ctx the parse tree
	 */
	void enterStringBlock(@NotNull CElsaParser.StringBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#stringBlock}.
	 * @param ctx the parse tree
	 */
	void exitStringBlock(@NotNull CElsaParser.StringBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#classSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterClassSpecifier(@NotNull CElsaParser.ClassSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#classSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitClassSpecifier(@NotNull CElsaParser.ClassSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#macroInvocation}.
	 * @param ctx the parse tree
	 */
	void enterMacroInvocation(@NotNull CElsaParser.MacroInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#macroInvocation}.
	 * @param ctx the parse tree
	 */
	void exitMacroInvocation(@NotNull CElsaParser.MacroInvocationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberDeclarationFunctionDefine}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclarationFunctionDefine(@NotNull CElsaParser.MemberDeclarationFunctionDefineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberDeclarationFunctionDefine}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclarationFunctionDefine(@NotNull CElsaParser.MemberDeclarationFunctionDefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#exceptionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterExceptionDeclaration(@NotNull CElsaParser.ExceptionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#exceptionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitExceptionDeclaration(@NotNull CElsaParser.ExceptionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#newInitializer}.
	 * @param ctx the parse tree
	 */
	void enterNewInitializer(@NotNull CElsaParser.NewInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#newInitializer}.
	 * @param ctx the parse tree
	 */
	void exitNewInitializer(@NotNull CElsaParser.NewInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#memberSpecification}.
	 * @param ctx the parse tree
	 */
	void enterMemberSpecification(@NotNull CElsaParser.MemberSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#memberSpecification}.
	 * @param ctx the parse tree
	 */
	void exitMemberSpecification(@NotNull CElsaParser.MemberSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#expressions}.
	 * @param ctx the parse tree
	 */
	void enterExpressions(@NotNull CElsaParser.ExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#expressions}.
	 * @param ctx the parse tree
	 */
	void exitExpressions(@NotNull CElsaParser.ExpressionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#memInitializerId}.
	 * @param ctx the parse tree
	 */
	void enterMemInitializerId(@NotNull CElsaParser.MemInitializerIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#memInitializerId}.
	 * @param ctx the parse tree
	 */
	void exitMemInitializerId(@NotNull CElsaParser.MemInitializerIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(@NotNull CElsaParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(@NotNull CElsaParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#conversionFunctionId}.
	 * @param ctx the parse tree
	 */
	void enterConversionFunctionId(@NotNull CElsaParser.ConversionFunctionIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#conversionFunctionId}.
	 * @param ctx the parse tree
	 */
	void exitConversionFunctionId(@NotNull CElsaParser.ConversionFunctionIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#enumName}.
	 * @param ctx the parse tree
	 */
	void enterEnumName(@NotNull CElsaParser.EnumNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#enumName}.
	 * @param ctx the parse tree
	 */
	void exitEnumName(@NotNull CElsaParser.EnumNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(@NotNull CElsaParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(@NotNull CElsaParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#memberDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclaratorList(@NotNull CElsaParser.MemberDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#memberDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclaratorList(@NotNull CElsaParser.MemberDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameter(@NotNull CElsaParser.TypeParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#typeParameter}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameter(@NotNull CElsaParser.TypeParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#enumeratorDefinition}.
	 * @param ctx the parse tree
	 */
	void enterEnumeratorDefinition(@NotNull CElsaParser.EnumeratorDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#enumeratorDefinition}.
	 * @param ctx the parse tree
	 */
	void exitEnumeratorDefinition(@NotNull CElsaParser.EnumeratorDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#enumeratorList}.
	 * @param ctx the parse tree
	 */
	void enterEnumeratorList(@NotNull CElsaParser.EnumeratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#enumeratorList}.
	 * @param ctx the parse tree
	 */
	void exitEnumeratorList(@NotNull CElsaParser.EnumeratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(@NotNull CElsaParser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(@NotNull CElsaParser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberDeclarationGeneral}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclarationGeneral(@NotNull CElsaParser.MemberDeclarationGeneralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberDeclarationGeneral}
	 * labeled alternative in {@link CElsaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclarationGeneral(@NotNull CElsaParser.MemberDeclarationGeneralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#storageClassSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterStorageClassSpecifier(@NotNull CElsaParser.StorageClassSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#storageClassSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitStorageClassSpecifier(@NotNull CElsaParser.StorageClassSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#newTypeId}.
	 * @param ctx the parse tree
	 */
	void enterNewTypeId(@NotNull CElsaParser.NewTypeIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#newTypeId}.
	 * @param ctx the parse tree
	 */
	void exitNewTypeId(@NotNull CElsaParser.NewTypeIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#usingDirective}.
	 * @param ctx the parse tree
	 */
	void enterUsingDirective(@NotNull CElsaParser.UsingDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#usingDirective}.
	 * @param ctx the parse tree
	 */
	void exitUsingDirective(@NotNull CElsaParser.UsingDirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#templateDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTemplateDeclaration(@NotNull CElsaParser.TemplateDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#templateDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTemplateDeclaration(@NotNull CElsaParser.TemplateDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#nestedNameSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterNestedNameSpecifier(@NotNull CElsaParser.NestedNameSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#nestedNameSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitNestedNameSpecifier(@NotNull CElsaParser.NestedNameSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull CElsaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull CElsaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#baseClause}.
	 * @param ctx the parse tree
	 */
	void enterBaseClause(@NotNull CElsaParser.BaseClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#baseClause}.
	 * @param ctx the parse tree
	 */
	void exitBaseClause(@NotNull CElsaParser.BaseClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#namespaceDefinition}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceDefinition(@NotNull CElsaParser.NamespaceDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#namespaceDefinition}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceDefinition(@NotNull CElsaParser.NamespaceDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#templateParameterList}.
	 * @param ctx the parse tree
	 */
	void enterTemplateParameterList(@NotNull CElsaParser.TemplateParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#templateParameterList}.
	 * @param ctx the parse tree
	 */
	void exitTemplateParameterList(@NotNull CElsaParser.TemplateParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterDeclarator(@NotNull CElsaParser.DeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitDeclarator(@NotNull CElsaParser.DeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#elaboratedTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterElaboratedTypeSpecifier(@NotNull CElsaParser.ElaboratedTypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#elaboratedTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitElaboratedTypeSpecifier(@NotNull CElsaParser.ElaboratedTypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#templateName}.
	 * @param ctx the parse tree
	 */
	void enterTemplateName(@NotNull CElsaParser.TemplateNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#templateName}.
	 * @param ctx the parse tree
	 */
	void exitTemplateName(@NotNull CElsaParser.TemplateNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#declaratorId}.
	 * @param ctx the parse tree
	 */
	void enterDeclaratorId(@NotNull CElsaParser.DeclaratorIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#declaratorId}.
	 * @param ctx the parse tree
	 */
	void exitDeclaratorId(@NotNull CElsaParser.DeclaratorIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#deleteExpression}.
	 * @param ctx the parse tree
	 */
	void enterDeleteExpression(@NotNull CElsaParser.DeleteExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#deleteExpression}.
	 * @param ctx the parse tree
	 */
	void exitDeleteExpression(@NotNull CElsaParser.DeleteExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#cvQualifierSeq}.
	 * @param ctx the parse tree
	 */
	void enterCvQualifierSeq(@NotNull CElsaParser.CvQualifierSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#cvQualifierSeq}.
	 * @param ctx the parse tree
	 */
	void exitCvQualifierSeq(@NotNull CElsaParser.CvQualifierSeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#operatorFunctionId}.
	 * @param ctx the parse tree
	 */
	void enterOperatorFunctionId(@NotNull CElsaParser.OperatorFunctionIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#operatorFunctionId}.
	 * @param ctx the parse tree
	 */
	void exitOperatorFunctionId(@NotNull CElsaParser.OperatorFunctionIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void enterFunctionBody(@NotNull CElsaParser.FunctionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#functionBody}.
	 * @param ctx the parse tree
	 */
	void exitFunctionBody(@NotNull CElsaParser.FunctionBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#enumSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterEnumSpecifier(@NotNull CElsaParser.EnumSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#enumSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitEnumSpecifier(@NotNull CElsaParser.EnumSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CElsaParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement(@NotNull CElsaParser.JumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CElsaParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement(@NotNull CElsaParser.JumpStatementContext ctx);
}