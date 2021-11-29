// Generated from /Users/guhan/workspace/opensource/cave/cave-components/cave-sql/antlr4/CSqlParser.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CSqlParser}.
 */
public interface CSqlParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CSqlParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(CSqlParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(CSqlParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#sqlStatements}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatements(CSqlParser.SqlStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#sqlStatements}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatements(CSqlParser.SqlStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatement(CSqlParser.SqlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatement(CSqlParser.SqlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(CSqlParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(CSqlParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#ddlStatement}.
	 * @param ctx the parse tree
	 */
	void enterDdlStatement(CSqlParser.DdlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#ddlStatement}.
	 * @param ctx the parse tree
	 */
	void exitDdlStatement(CSqlParser.DdlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dmlStatement}.
	 * @param ctx the parse tree
	 */
	void enterDmlStatement(CSqlParser.DmlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dmlStatement}.
	 * @param ctx the parse tree
	 */
	void exitDmlStatement(CSqlParser.DmlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#transactionStatement}.
	 * @param ctx the parse tree
	 */
	void enterTransactionStatement(CSqlParser.TransactionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#transactionStatement}.
	 * @param ctx the parse tree
	 */
	void exitTransactionStatement(CSqlParser.TransactionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#replicationStatement}.
	 * @param ctx the parse tree
	 */
	void enterReplicationStatement(CSqlParser.ReplicationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#replicationStatement}.
	 * @param ctx the parse tree
	 */
	void exitReplicationStatement(CSqlParser.ReplicationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#preparedStatement}.
	 * @param ctx the parse tree
	 */
	void enterPreparedStatement(CSqlParser.PreparedStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#preparedStatement}.
	 * @param ctx the parse tree
	 */
	void exitPreparedStatement(CSqlParser.PreparedStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(CSqlParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(CSqlParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#administrationStatement}.
	 * @param ctx the parse tree
	 */
	void enterAdministrationStatement(CSqlParser.AdministrationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#administrationStatement}.
	 * @param ctx the parse tree
	 */
	void exitAdministrationStatement(CSqlParser.AdministrationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#utilityStatement}.
	 * @param ctx the parse tree
	 */
	void enterUtilityStatement(CSqlParser.UtilityStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#utilityStatement}.
	 * @param ctx the parse tree
	 */
	void exitUtilityStatement(CSqlParser.UtilityStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createDatabase}.
	 * @param ctx the parse tree
	 */
	void enterCreateDatabase(CSqlParser.CreateDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createDatabase}.
	 * @param ctx the parse tree
	 */
	void exitCreateDatabase(CSqlParser.CreateDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createEvent}.
	 * @param ctx the parse tree
	 */
	void enterCreateEvent(CSqlParser.CreateEventContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createEvent}.
	 * @param ctx the parse tree
	 */
	void exitCreateEvent(CSqlParser.CreateEventContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void enterCreateIndex(CSqlParser.CreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void exitCreateIndex(CSqlParser.CreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createLogfileGroup}.
	 * @param ctx the parse tree
	 */
	void enterCreateLogfileGroup(CSqlParser.CreateLogfileGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createLogfileGroup}.
	 * @param ctx the parse tree
	 */
	void exitCreateLogfileGroup(CSqlParser.CreateLogfileGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createProcedure}.
	 * @param ctx the parse tree
	 */
	void enterCreateProcedure(CSqlParser.CreateProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createProcedure}.
	 * @param ctx the parse tree
	 */
	void exitCreateProcedure(CSqlParser.CreateProcedureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createFunction}.
	 * @param ctx the parse tree
	 */
	void enterCreateFunction(CSqlParser.CreateFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createFunction}.
	 * @param ctx the parse tree
	 */
	void exitCreateFunction(CSqlParser.CreateFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createServer}.
	 * @param ctx the parse tree
	 */
	void enterCreateServer(CSqlParser.CreateServerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createServer}.
	 * @param ctx the parse tree
	 */
	void exitCreateServer(CSqlParser.CreateServerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code copyCreateTable}
	 * labeled alternative in {@link CSqlParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterCopyCreateTable(CSqlParser.CopyCreateTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code copyCreateTable}
	 * labeled alternative in {@link CSqlParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitCopyCreateTable(CSqlParser.CopyCreateTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code queryCreateTable}
	 * labeled alternative in {@link CSqlParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterQueryCreateTable(CSqlParser.QueryCreateTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code queryCreateTable}
	 * labeled alternative in {@link CSqlParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitQueryCreateTable(CSqlParser.QueryCreateTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code columnCreateTable}
	 * labeled alternative in {@link CSqlParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterColumnCreateTable(CSqlParser.ColumnCreateTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code columnCreateTable}
	 * labeled alternative in {@link CSqlParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitColumnCreateTable(CSqlParser.ColumnCreateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createTablespaceInnodb}.
	 * @param ctx the parse tree
	 */
	void enterCreateTablespaceInnodb(CSqlParser.CreateTablespaceInnodbContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createTablespaceInnodb}.
	 * @param ctx the parse tree
	 */
	void exitCreateTablespaceInnodb(CSqlParser.CreateTablespaceInnodbContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createTablespaceNdb}.
	 * @param ctx the parse tree
	 */
	void enterCreateTablespaceNdb(CSqlParser.CreateTablespaceNdbContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createTablespaceNdb}.
	 * @param ctx the parse tree
	 */
	void exitCreateTablespaceNdb(CSqlParser.CreateTablespaceNdbContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createTrigger}.
	 * @param ctx the parse tree
	 */
	void enterCreateTrigger(CSqlParser.CreateTriggerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createTrigger}.
	 * @param ctx the parse tree
	 */
	void exitCreateTrigger(CSqlParser.CreateTriggerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createView}.
	 * @param ctx the parse tree
	 */
	void enterCreateView(CSqlParser.CreateViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createView}.
	 * @param ctx the parse tree
	 */
	void exitCreateView(CSqlParser.CreateViewContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createDatabaseOption}.
	 * @param ctx the parse tree
	 */
	void enterCreateDatabaseOption(CSqlParser.CreateDatabaseOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createDatabaseOption}.
	 * @param ctx the parse tree
	 */
	void exitCreateDatabaseOption(CSqlParser.CreateDatabaseOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#ownerStatement}.
	 * @param ctx the parse tree
	 */
	void enterOwnerStatement(CSqlParser.OwnerStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#ownerStatement}.
	 * @param ctx the parse tree
	 */
	void exitOwnerStatement(CSqlParser.OwnerStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code preciseSchedule}
	 * labeled alternative in {@link CSqlParser#scheduleExpression}.
	 * @param ctx the parse tree
	 */
	void enterPreciseSchedule(CSqlParser.PreciseScheduleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code preciseSchedule}
	 * labeled alternative in {@link CSqlParser#scheduleExpression}.
	 * @param ctx the parse tree
	 */
	void exitPreciseSchedule(CSqlParser.PreciseScheduleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intervalSchedule}
	 * labeled alternative in {@link CSqlParser#scheduleExpression}.
	 * @param ctx the parse tree
	 */
	void enterIntervalSchedule(CSqlParser.IntervalScheduleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intervalSchedule}
	 * labeled alternative in {@link CSqlParser#scheduleExpression}.
	 * @param ctx the parse tree
	 */
	void exitIntervalSchedule(CSqlParser.IntervalScheduleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#timestampValue}.
	 * @param ctx the parse tree
	 */
	void enterTimestampValue(CSqlParser.TimestampValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#timestampValue}.
	 * @param ctx the parse tree
	 */
	void exitTimestampValue(CSqlParser.TimestampValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#intervalExpr}.
	 * @param ctx the parse tree
	 */
	void enterIntervalExpr(CSqlParser.IntervalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#intervalExpr}.
	 * @param ctx the parse tree
	 */
	void exitIntervalExpr(CSqlParser.IntervalExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#intervalType}.
	 * @param ctx the parse tree
	 */
	void enterIntervalType(CSqlParser.IntervalTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#intervalType}.
	 * @param ctx the parse tree
	 */
	void exitIntervalType(CSqlParser.IntervalTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#enableType}.
	 * @param ctx the parse tree
	 */
	void enterEnableType(CSqlParser.EnableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#enableType}.
	 * @param ctx the parse tree
	 */
	void exitEnableType(CSqlParser.EnableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#indexType}.
	 * @param ctx the parse tree
	 */
	void enterIndexType(CSqlParser.IndexTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#indexType}.
	 * @param ctx the parse tree
	 */
	void exitIndexType(CSqlParser.IndexTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#indexOption}.
	 * @param ctx the parse tree
	 */
	void enterIndexOption(CSqlParser.IndexOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#indexOption}.
	 * @param ctx the parse tree
	 */
	void exitIndexOption(CSqlParser.IndexOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#procedureParameter}.
	 * @param ctx the parse tree
	 */
	void enterProcedureParameter(CSqlParser.ProcedureParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#procedureParameter}.
	 * @param ctx the parse tree
	 */
	void exitProcedureParameter(CSqlParser.ProcedureParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#functionParameter}.
	 * @param ctx the parse tree
	 */
	void enterFunctionParameter(CSqlParser.FunctionParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#functionParameter}.
	 * @param ctx the parse tree
	 */
	void exitFunctionParameter(CSqlParser.FunctionParameterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routineComment}
	 * labeled alternative in {@link CSqlParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void enterRoutineComment(CSqlParser.RoutineCommentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routineComment}
	 * labeled alternative in {@link CSqlParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void exitRoutineComment(CSqlParser.RoutineCommentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routineLanguage}
	 * labeled alternative in {@link CSqlParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void enterRoutineLanguage(CSqlParser.RoutineLanguageContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routineLanguage}
	 * labeled alternative in {@link CSqlParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void exitRoutineLanguage(CSqlParser.RoutineLanguageContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routineBehavior}
	 * labeled alternative in {@link CSqlParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void enterRoutineBehavior(CSqlParser.RoutineBehaviorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routineBehavior}
	 * labeled alternative in {@link CSqlParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void exitRoutineBehavior(CSqlParser.RoutineBehaviorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routineData}
	 * labeled alternative in {@link CSqlParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void enterRoutineData(CSqlParser.RoutineDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routineData}
	 * labeled alternative in {@link CSqlParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void exitRoutineData(CSqlParser.RoutineDataContext ctx);
	/**
	 * Enter a parse tree produced by the {@code routineSecurity}
	 * labeled alternative in {@link CSqlParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void enterRoutineSecurity(CSqlParser.RoutineSecurityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code routineSecurity}
	 * labeled alternative in {@link CSqlParser#routineOption}.
	 * @param ctx the parse tree
	 */
	void exitRoutineSecurity(CSqlParser.RoutineSecurityContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#serverOption}.
	 * @param ctx the parse tree
	 */
	void enterServerOption(CSqlParser.ServerOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#serverOption}.
	 * @param ctx the parse tree
	 */
	void exitServerOption(CSqlParser.ServerOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createDefinitions}.
	 * @param ctx the parse tree
	 */
	void enterCreateDefinitions(CSqlParser.CreateDefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createDefinitions}.
	 * @param ctx the parse tree
	 */
	void exitCreateDefinitions(CSqlParser.CreateDefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code columnDeclaration}
	 * labeled alternative in {@link CSqlParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void enterColumnDeclaration(CSqlParser.ColumnDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code columnDeclaration}
	 * labeled alternative in {@link CSqlParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void exitColumnDeclaration(CSqlParser.ColumnDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constraintDeclaration}
	 * labeled alternative in {@link CSqlParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void enterConstraintDeclaration(CSqlParser.ConstraintDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constraintDeclaration}
	 * labeled alternative in {@link CSqlParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void exitConstraintDeclaration(CSqlParser.ConstraintDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indexDeclaration}
	 * labeled alternative in {@link CSqlParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void enterIndexDeclaration(CSqlParser.IndexDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indexDeclaration}
	 * labeled alternative in {@link CSqlParser#createDefinition}.
	 * @param ctx the parse tree
	 */
	void exitIndexDeclaration(CSqlParser.IndexDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterColumnDefinition(CSqlParser.ColumnDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitColumnDefinition(CSqlParser.ColumnDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterNullColumnConstraint(CSqlParser.NullColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitNullColumnConstraint(CSqlParser.NullColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defaultColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterDefaultColumnConstraint(CSqlParser.DefaultColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defaultColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitDefaultColumnConstraint(CSqlParser.DefaultColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code visibilityColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterVisibilityColumnConstraint(CSqlParser.VisibilityColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code visibilityColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitVisibilityColumnConstraint(CSqlParser.VisibilityColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code autoIncrementColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterAutoIncrementColumnConstraint(CSqlParser.AutoIncrementColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code autoIncrementColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitAutoIncrementColumnConstraint(CSqlParser.AutoIncrementColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryKeyColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryKeyColumnConstraint(CSqlParser.PrimaryKeyColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryKeyColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryKeyColumnConstraint(CSqlParser.PrimaryKeyColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code uniqueKeyColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterUniqueKeyColumnConstraint(CSqlParser.UniqueKeyColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code uniqueKeyColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitUniqueKeyColumnConstraint(CSqlParser.UniqueKeyColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code commentColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterCommentColumnConstraint(CSqlParser.CommentColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code commentColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitCommentColumnConstraint(CSqlParser.CommentColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code formatColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterFormatColumnConstraint(CSqlParser.FormatColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code formatColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitFormatColumnConstraint(CSqlParser.FormatColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code storageColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterStorageColumnConstraint(CSqlParser.StorageColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code storageColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitStorageColumnConstraint(CSqlParser.StorageColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code referenceColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterReferenceColumnConstraint(CSqlParser.ReferenceColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code referenceColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitReferenceColumnConstraint(CSqlParser.ReferenceColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code collateColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterCollateColumnConstraint(CSqlParser.CollateColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code collateColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitCollateColumnConstraint(CSqlParser.CollateColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code generatedColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterGeneratedColumnConstraint(CSqlParser.GeneratedColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code generatedColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitGeneratedColumnConstraint(CSqlParser.GeneratedColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code serialDefaultColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterSerialDefaultColumnConstraint(CSqlParser.SerialDefaultColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code serialDefaultColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitSerialDefaultColumnConstraint(CSqlParser.SerialDefaultColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code checkColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterCheckColumnConstraint(CSqlParser.CheckColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code checkColumnConstraint}
	 * labeled alternative in {@link CSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitCheckColumnConstraint(CSqlParser.CheckColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryKeyTableConstraint}
	 * labeled alternative in {@link CSqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryKeyTableConstraint(CSqlParser.PrimaryKeyTableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryKeyTableConstraint}
	 * labeled alternative in {@link CSqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryKeyTableConstraint(CSqlParser.PrimaryKeyTableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code uniqueKeyTableConstraint}
	 * labeled alternative in {@link CSqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void enterUniqueKeyTableConstraint(CSqlParser.UniqueKeyTableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code uniqueKeyTableConstraint}
	 * labeled alternative in {@link CSqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void exitUniqueKeyTableConstraint(CSqlParser.UniqueKeyTableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code foreignKeyTableConstraint}
	 * labeled alternative in {@link CSqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void enterForeignKeyTableConstraint(CSqlParser.ForeignKeyTableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code foreignKeyTableConstraint}
	 * labeled alternative in {@link CSqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void exitForeignKeyTableConstraint(CSqlParser.ForeignKeyTableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code checkTableConstraint}
	 * labeled alternative in {@link CSqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void enterCheckTableConstraint(CSqlParser.CheckTableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code checkTableConstraint}
	 * labeled alternative in {@link CSqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void exitCheckTableConstraint(CSqlParser.CheckTableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#referenceDefinition}.
	 * @param ctx the parse tree
	 */
	void enterReferenceDefinition(CSqlParser.ReferenceDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#referenceDefinition}.
	 * @param ctx the parse tree
	 */
	void exitReferenceDefinition(CSqlParser.ReferenceDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#referenceAction}.
	 * @param ctx the parse tree
	 */
	void enterReferenceAction(CSqlParser.ReferenceActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#referenceAction}.
	 * @param ctx the parse tree
	 */
	void exitReferenceAction(CSqlParser.ReferenceActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#referenceControlType}.
	 * @param ctx the parse tree
	 */
	void enterReferenceControlType(CSqlParser.ReferenceControlTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#referenceControlType}.
	 * @param ctx the parse tree
	 */
	void exitReferenceControlType(CSqlParser.ReferenceControlTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleIndexDeclaration}
	 * labeled alternative in {@link CSqlParser#indexColumnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSimpleIndexDeclaration(CSqlParser.SimpleIndexDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleIndexDeclaration}
	 * labeled alternative in {@link CSqlParser#indexColumnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSimpleIndexDeclaration(CSqlParser.SimpleIndexDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code specialIndexDeclaration}
	 * labeled alternative in {@link CSqlParser#indexColumnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSpecialIndexDeclaration(CSqlParser.SpecialIndexDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code specialIndexDeclaration}
	 * labeled alternative in {@link CSqlParser#indexColumnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSpecialIndexDeclaration(CSqlParser.SpecialIndexDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionEngine}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionEngine(CSqlParser.TableOptionEngineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionEngine}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionEngine(CSqlParser.TableOptionEngineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionAutoIncrement}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionAutoIncrement(CSqlParser.TableOptionAutoIncrementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionAutoIncrement}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionAutoIncrement(CSqlParser.TableOptionAutoIncrementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionAverage}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionAverage(CSqlParser.TableOptionAverageContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionAverage}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionAverage(CSqlParser.TableOptionAverageContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionCharset}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionCharset(CSqlParser.TableOptionCharsetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionCharset}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionCharset(CSqlParser.TableOptionCharsetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionChecksum}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionChecksum(CSqlParser.TableOptionChecksumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionChecksum}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionChecksum(CSqlParser.TableOptionChecksumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionCollate}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionCollate(CSqlParser.TableOptionCollateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionCollate}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionCollate(CSqlParser.TableOptionCollateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionComment}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionComment(CSqlParser.TableOptionCommentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionComment}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionComment(CSqlParser.TableOptionCommentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionCompression}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionCompression(CSqlParser.TableOptionCompressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionCompression}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionCompression(CSqlParser.TableOptionCompressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionConnection}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionConnection(CSqlParser.TableOptionConnectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionConnection}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionConnection(CSqlParser.TableOptionConnectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionDataDirectory}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionDataDirectory(CSqlParser.TableOptionDataDirectoryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionDataDirectory}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionDataDirectory(CSqlParser.TableOptionDataDirectoryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionDelay}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionDelay(CSqlParser.TableOptionDelayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionDelay}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionDelay(CSqlParser.TableOptionDelayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionEncryption}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionEncryption(CSqlParser.TableOptionEncryptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionEncryption}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionEncryption(CSqlParser.TableOptionEncryptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionIndexDirectory}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionIndexDirectory(CSqlParser.TableOptionIndexDirectoryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionIndexDirectory}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionIndexDirectory(CSqlParser.TableOptionIndexDirectoryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionInsertMethod}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionInsertMethod(CSqlParser.TableOptionInsertMethodContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionInsertMethod}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionInsertMethod(CSqlParser.TableOptionInsertMethodContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionKeyBlockSize}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionKeyBlockSize(CSqlParser.TableOptionKeyBlockSizeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionKeyBlockSize}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionKeyBlockSize(CSqlParser.TableOptionKeyBlockSizeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionMaxRows}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionMaxRows(CSqlParser.TableOptionMaxRowsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionMaxRows}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionMaxRows(CSqlParser.TableOptionMaxRowsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionMinRows}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionMinRows(CSqlParser.TableOptionMinRowsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionMinRows}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionMinRows(CSqlParser.TableOptionMinRowsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionPackKeys}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionPackKeys(CSqlParser.TableOptionPackKeysContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionPackKeys}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionPackKeys(CSqlParser.TableOptionPackKeysContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionPassword}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionPassword(CSqlParser.TableOptionPasswordContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionPassword}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionPassword(CSqlParser.TableOptionPasswordContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionRowFormat}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionRowFormat(CSqlParser.TableOptionRowFormatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionRowFormat}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionRowFormat(CSqlParser.TableOptionRowFormatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionRecalculation}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionRecalculation(CSqlParser.TableOptionRecalculationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionRecalculation}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionRecalculation(CSqlParser.TableOptionRecalculationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionPersistent}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionPersistent(CSqlParser.TableOptionPersistentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionPersistent}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionPersistent(CSqlParser.TableOptionPersistentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionSamplePage}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionSamplePage(CSqlParser.TableOptionSamplePageContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionSamplePage}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionSamplePage(CSqlParser.TableOptionSamplePageContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionTablespace}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionTablespace(CSqlParser.TableOptionTablespaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionTablespace}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionTablespace(CSqlParser.TableOptionTablespaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionTableType}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionTableType(CSqlParser.TableOptionTableTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionTableType}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionTableType(CSqlParser.TableOptionTableTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableOptionUnion}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOptionUnion(CSqlParser.TableOptionUnionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableOptionUnion}
	 * labeled alternative in {@link CSqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOptionUnion(CSqlParser.TableOptionUnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#tableType}.
	 * @param ctx the parse tree
	 */
	void enterTableType(CSqlParser.TableTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#tableType}.
	 * @param ctx the parse tree
	 */
	void exitTableType(CSqlParser.TableTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#tablespaceStorage}.
	 * @param ctx the parse tree
	 */
	void enterTablespaceStorage(CSqlParser.TablespaceStorageContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#tablespaceStorage}.
	 * @param ctx the parse tree
	 */
	void exitTablespaceStorage(CSqlParser.TablespaceStorageContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#partitionDefinitions}.
	 * @param ctx the parse tree
	 */
	void enterPartitionDefinitions(CSqlParser.PartitionDefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#partitionDefinitions}.
	 * @param ctx the parse tree
	 */
	void exitPartitionDefinitions(CSqlParser.PartitionDefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionFunctionHash}
	 * labeled alternative in {@link CSqlParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionFunctionHash(CSqlParser.PartitionFunctionHashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionFunctionHash}
	 * labeled alternative in {@link CSqlParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionFunctionHash(CSqlParser.PartitionFunctionHashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionFunctionKey}
	 * labeled alternative in {@link CSqlParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionFunctionKey(CSqlParser.PartitionFunctionKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionFunctionKey}
	 * labeled alternative in {@link CSqlParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionFunctionKey(CSqlParser.PartitionFunctionKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionFunctionRange}
	 * labeled alternative in {@link CSqlParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionFunctionRange(CSqlParser.PartitionFunctionRangeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionFunctionRange}
	 * labeled alternative in {@link CSqlParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionFunctionRange(CSqlParser.PartitionFunctionRangeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionFunctionList}
	 * labeled alternative in {@link CSqlParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionFunctionList(CSqlParser.PartitionFunctionListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionFunctionList}
	 * labeled alternative in {@link CSqlParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionFunctionList(CSqlParser.PartitionFunctionListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subPartitionFunctionHash}
	 * labeled alternative in {@link CSqlParser#subpartitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSubPartitionFunctionHash(CSqlParser.SubPartitionFunctionHashContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subPartitionFunctionHash}
	 * labeled alternative in {@link CSqlParser#subpartitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSubPartitionFunctionHash(CSqlParser.SubPartitionFunctionHashContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subPartitionFunctionKey}
	 * labeled alternative in {@link CSqlParser#subpartitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSubPartitionFunctionKey(CSqlParser.SubPartitionFunctionKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subPartitionFunctionKey}
	 * labeled alternative in {@link CSqlParser#subpartitionFunctionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSubPartitionFunctionKey(CSqlParser.SubPartitionFunctionKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionComparison}
	 * labeled alternative in {@link CSqlParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionComparison(CSqlParser.PartitionComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionComparison}
	 * labeled alternative in {@link CSqlParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionComparison(CSqlParser.PartitionComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionListAtom}
	 * labeled alternative in {@link CSqlParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionListAtom(CSqlParser.PartitionListAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionListAtom}
	 * labeled alternative in {@link CSqlParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionListAtom(CSqlParser.PartitionListAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionListVector}
	 * labeled alternative in {@link CSqlParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionListVector(CSqlParser.PartitionListVectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionListVector}
	 * labeled alternative in {@link CSqlParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionListVector(CSqlParser.PartitionListVectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionSimple}
	 * labeled alternative in {@link CSqlParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPartitionSimple(CSqlParser.PartitionSimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionSimple}
	 * labeled alternative in {@link CSqlParser#partitionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPartitionSimple(CSqlParser.PartitionSimpleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#partitionDefinerAtom}.
	 * @param ctx the parse tree
	 */
	void enterPartitionDefinerAtom(CSqlParser.PartitionDefinerAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#partitionDefinerAtom}.
	 * @param ctx the parse tree
	 */
	void exitPartitionDefinerAtom(CSqlParser.PartitionDefinerAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#partitionDefinerVector}.
	 * @param ctx the parse tree
	 */
	void enterPartitionDefinerVector(CSqlParser.PartitionDefinerVectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#partitionDefinerVector}.
	 * @param ctx the parse tree
	 */
	void exitPartitionDefinerVector(CSqlParser.PartitionDefinerVectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#subpartitionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSubpartitionDefinition(CSqlParser.SubpartitionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#subpartitionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSubpartitionDefinition(CSqlParser.SubpartitionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionEngine}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionEngine(CSqlParser.PartitionOptionEngineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionEngine}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionEngine(CSqlParser.PartitionOptionEngineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionComment}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionComment(CSqlParser.PartitionOptionCommentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionComment}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionComment(CSqlParser.PartitionOptionCommentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionDataDirectory}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionDataDirectory(CSqlParser.PartitionOptionDataDirectoryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionDataDirectory}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionDataDirectory(CSqlParser.PartitionOptionDataDirectoryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionIndexDirectory}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionIndexDirectory(CSqlParser.PartitionOptionIndexDirectoryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionIndexDirectory}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionIndexDirectory(CSqlParser.PartitionOptionIndexDirectoryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionMaxRows}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionMaxRows(CSqlParser.PartitionOptionMaxRowsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionMaxRows}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionMaxRows(CSqlParser.PartitionOptionMaxRowsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionMinRows}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionMinRows(CSqlParser.PartitionOptionMinRowsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionMinRows}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionMinRows(CSqlParser.PartitionOptionMinRowsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionTablespace}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionTablespace(CSqlParser.PartitionOptionTablespaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionTablespace}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionTablespace(CSqlParser.PartitionOptionTablespaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code partitionOptionNodeGroup}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void enterPartitionOptionNodeGroup(CSqlParser.PartitionOptionNodeGroupContext ctx);
	/**
	 * Exit a parse tree produced by the {@code partitionOptionNodeGroup}
	 * labeled alternative in {@link CSqlParser#partitionOption}.
	 * @param ctx the parse tree
	 */
	void exitPartitionOptionNodeGroup(CSqlParser.PartitionOptionNodeGroupContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterSimpleDatabase}
	 * labeled alternative in {@link CSqlParser#alterDatabase}.
	 * @param ctx the parse tree
	 */
	void enterAlterSimpleDatabase(CSqlParser.AlterSimpleDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterSimpleDatabase}
	 * labeled alternative in {@link CSqlParser#alterDatabase}.
	 * @param ctx the parse tree
	 */
	void exitAlterSimpleDatabase(CSqlParser.AlterSimpleDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterUpgradeName}
	 * labeled alternative in {@link CSqlParser#alterDatabase}.
	 * @param ctx the parse tree
	 */
	void enterAlterUpgradeName(CSqlParser.AlterUpgradeNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterUpgradeName}
	 * labeled alternative in {@link CSqlParser#alterDatabase}.
	 * @param ctx the parse tree
	 */
	void exitAlterUpgradeName(CSqlParser.AlterUpgradeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#alterEvent}.
	 * @param ctx the parse tree
	 */
	void enterAlterEvent(CSqlParser.AlterEventContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#alterEvent}.
	 * @param ctx the parse tree
	 */
	void exitAlterEvent(CSqlParser.AlterEventContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#alterFunction}.
	 * @param ctx the parse tree
	 */
	void enterAlterFunction(CSqlParser.AlterFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#alterFunction}.
	 * @param ctx the parse tree
	 */
	void exitAlterFunction(CSqlParser.AlterFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#alterInstance}.
	 * @param ctx the parse tree
	 */
	void enterAlterInstance(CSqlParser.AlterInstanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#alterInstance}.
	 * @param ctx the parse tree
	 */
	void exitAlterInstance(CSqlParser.AlterInstanceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#alterLogfileGroup}.
	 * @param ctx the parse tree
	 */
	void enterAlterLogfileGroup(CSqlParser.AlterLogfileGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#alterLogfileGroup}.
	 * @param ctx the parse tree
	 */
	void exitAlterLogfileGroup(CSqlParser.AlterLogfileGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#alterProcedure}.
	 * @param ctx the parse tree
	 */
	void enterAlterProcedure(CSqlParser.AlterProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#alterProcedure}.
	 * @param ctx the parse tree
	 */
	void exitAlterProcedure(CSqlParser.AlterProcedureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#alterServer}.
	 * @param ctx the parse tree
	 */
	void enterAlterServer(CSqlParser.AlterServerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#alterServer}.
	 * @param ctx the parse tree
	 */
	void exitAlterServer(CSqlParser.AlterServerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void enterAlterTable(CSqlParser.AlterTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void exitAlterTable(CSqlParser.AlterTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#alterTablespace}.
	 * @param ctx the parse tree
	 */
	void enterAlterTablespace(CSqlParser.AlterTablespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#alterTablespace}.
	 * @param ctx the parse tree
	 */
	void exitAlterTablespace(CSqlParser.AlterTablespaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#alterView}.
	 * @param ctx the parse tree
	 */
	void enterAlterView(CSqlParser.AlterViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#alterView}.
	 * @param ctx the parse tree
	 */
	void exitAlterView(CSqlParser.AlterViewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByTableOption}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByTableOption(CSqlParser.AlterByTableOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByTableOption}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByTableOption(CSqlParser.AlterByTableOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddColumn}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddColumn(CSqlParser.AlterByAddColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddColumn}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddColumn(CSqlParser.AlterByAddColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddColumns}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddColumns(CSqlParser.AlterByAddColumnsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddColumns}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddColumns(CSqlParser.AlterByAddColumnsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddIndex}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddIndex(CSqlParser.AlterByAddIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddIndex}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddIndex(CSqlParser.AlterByAddIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddPrimaryKey}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddPrimaryKey(CSqlParser.AlterByAddPrimaryKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddPrimaryKey}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddPrimaryKey(CSqlParser.AlterByAddPrimaryKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddUniqueKey}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddUniqueKey(CSqlParser.AlterByAddUniqueKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddUniqueKey}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddUniqueKey(CSqlParser.AlterByAddUniqueKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddSpecialIndex}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddSpecialIndex(CSqlParser.AlterByAddSpecialIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddSpecialIndex}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddSpecialIndex(CSqlParser.AlterByAddSpecialIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddForeignKey}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddForeignKey(CSqlParser.AlterByAddForeignKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddForeignKey}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddForeignKey(CSqlParser.AlterByAddForeignKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddCheckTableConstraint}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddCheckTableConstraint(CSqlParser.AlterByAddCheckTableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddCheckTableConstraint}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddCheckTableConstraint(CSqlParser.AlterByAddCheckTableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterBySetAlgorithm}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterBySetAlgorithm(CSqlParser.AlterBySetAlgorithmContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterBySetAlgorithm}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterBySetAlgorithm(CSqlParser.AlterBySetAlgorithmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByChangeDefault}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByChangeDefault(CSqlParser.AlterByChangeDefaultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByChangeDefault}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByChangeDefault(CSqlParser.AlterByChangeDefaultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByChangeColumn}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByChangeColumn(CSqlParser.AlterByChangeColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByChangeColumn}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByChangeColumn(CSqlParser.AlterByChangeColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByRenameColumn}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByRenameColumn(CSqlParser.AlterByRenameColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByRenameColumn}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByRenameColumn(CSqlParser.AlterByRenameColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByLock}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByLock(CSqlParser.AlterByLockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByLock}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByLock(CSqlParser.AlterByLockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByModifyColumn}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByModifyColumn(CSqlParser.AlterByModifyColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByModifyColumn}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByModifyColumn(CSqlParser.AlterByModifyColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDropColumn}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDropColumn(CSqlParser.AlterByDropColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDropColumn}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDropColumn(CSqlParser.AlterByDropColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDropConstraintCheck}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDropConstraintCheck(CSqlParser.AlterByDropConstraintCheckContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDropConstraintCheck}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDropConstraintCheck(CSqlParser.AlterByDropConstraintCheckContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDropPrimaryKey}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDropPrimaryKey(CSqlParser.AlterByDropPrimaryKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDropPrimaryKey}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDropPrimaryKey(CSqlParser.AlterByDropPrimaryKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByRenameIndex}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByRenameIndex(CSqlParser.AlterByRenameIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByRenameIndex}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByRenameIndex(CSqlParser.AlterByRenameIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAlterIndexVisibility}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAlterIndexVisibility(CSqlParser.AlterByAlterIndexVisibilityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAlterIndexVisibility}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAlterIndexVisibility(CSqlParser.AlterByAlterIndexVisibilityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDropIndex}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDropIndex(CSqlParser.AlterByDropIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDropIndex}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDropIndex(CSqlParser.AlterByDropIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDropForeignKey}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDropForeignKey(CSqlParser.AlterByDropForeignKeyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDropForeignKey}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDropForeignKey(CSqlParser.AlterByDropForeignKeyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDisableKeys}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDisableKeys(CSqlParser.AlterByDisableKeysContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDisableKeys}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDisableKeys(CSqlParser.AlterByDisableKeysContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByEnableKeys}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByEnableKeys(CSqlParser.AlterByEnableKeysContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByEnableKeys}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByEnableKeys(CSqlParser.AlterByEnableKeysContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByRename}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByRename(CSqlParser.AlterByRenameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByRename}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByRename(CSqlParser.AlterByRenameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByOrder}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByOrder(CSqlParser.AlterByOrderContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByOrder}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByOrder(CSqlParser.AlterByOrderContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByConvertCharset}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByConvertCharset(CSqlParser.AlterByConvertCharsetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByConvertCharset}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByConvertCharset(CSqlParser.AlterByConvertCharsetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDefaultCharset}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDefaultCharset(CSqlParser.AlterByDefaultCharsetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDefaultCharset}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDefaultCharset(CSqlParser.AlterByDefaultCharsetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDiscardTablespace}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDiscardTablespace(CSqlParser.AlterByDiscardTablespaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDiscardTablespace}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDiscardTablespace(CSqlParser.AlterByDiscardTablespaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByImportTablespace}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByImportTablespace(CSqlParser.AlterByImportTablespaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByImportTablespace}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByImportTablespace(CSqlParser.AlterByImportTablespaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByForce}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByForce(CSqlParser.AlterByForceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByForce}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByForce(CSqlParser.AlterByForceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByValidate}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByValidate(CSqlParser.AlterByValidateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByValidate}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByValidate(CSqlParser.AlterByValidateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAddPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAddPartition(CSqlParser.AlterByAddPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAddPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAddPartition(CSqlParser.AlterByAddPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDropPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDropPartition(CSqlParser.AlterByDropPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDropPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDropPartition(CSqlParser.AlterByDropPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByDiscardPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByDiscardPartition(CSqlParser.AlterByDiscardPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByDiscardPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByDiscardPartition(CSqlParser.AlterByDiscardPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByImportPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByImportPartition(CSqlParser.AlterByImportPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByImportPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByImportPartition(CSqlParser.AlterByImportPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByTruncatePartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByTruncatePartition(CSqlParser.AlterByTruncatePartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByTruncatePartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByTruncatePartition(CSqlParser.AlterByTruncatePartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByCoalescePartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByCoalescePartition(CSqlParser.AlterByCoalescePartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByCoalescePartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByCoalescePartition(CSqlParser.AlterByCoalescePartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByReorganizePartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByReorganizePartition(CSqlParser.AlterByReorganizePartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByReorganizePartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByReorganizePartition(CSqlParser.AlterByReorganizePartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByExchangePartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByExchangePartition(CSqlParser.AlterByExchangePartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByExchangePartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByExchangePartition(CSqlParser.AlterByExchangePartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByAnalyzePartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByAnalyzePartition(CSqlParser.AlterByAnalyzePartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByAnalyzePartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByAnalyzePartition(CSqlParser.AlterByAnalyzePartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByCheckPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByCheckPartition(CSqlParser.AlterByCheckPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByCheckPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByCheckPartition(CSqlParser.AlterByCheckPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByOptimizePartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByOptimizePartition(CSqlParser.AlterByOptimizePartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByOptimizePartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByOptimizePartition(CSqlParser.AlterByOptimizePartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByRebuildPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByRebuildPartition(CSqlParser.AlterByRebuildPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByRebuildPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByRebuildPartition(CSqlParser.AlterByRebuildPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByRepairPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByRepairPartition(CSqlParser.AlterByRepairPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByRepairPartition}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByRepairPartition(CSqlParser.AlterByRepairPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByRemovePartitioning}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByRemovePartitioning(CSqlParser.AlterByRemovePartitioningContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByRemovePartitioning}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByRemovePartitioning(CSqlParser.AlterByRemovePartitioningContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterByUpgradePartitioning}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterAlterByUpgradePartitioning(CSqlParser.AlterByUpgradePartitioningContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterByUpgradePartitioning}
	 * labeled alternative in {@link CSqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitAlterByUpgradePartitioning(CSqlParser.AlterByUpgradePartitioningContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dropDatabase}.
	 * @param ctx the parse tree
	 */
	void enterDropDatabase(CSqlParser.DropDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dropDatabase}.
	 * @param ctx the parse tree
	 */
	void exitDropDatabase(CSqlParser.DropDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dropEvent}.
	 * @param ctx the parse tree
	 */
	void enterDropEvent(CSqlParser.DropEventContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dropEvent}.
	 * @param ctx the parse tree
	 */
	void exitDropEvent(CSqlParser.DropEventContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dropIndex}.
	 * @param ctx the parse tree
	 */
	void enterDropIndex(CSqlParser.DropIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dropIndex}.
	 * @param ctx the parse tree
	 */
	void exitDropIndex(CSqlParser.DropIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dropLogfileGroup}.
	 * @param ctx the parse tree
	 */
	void enterDropLogfileGroup(CSqlParser.DropLogfileGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dropLogfileGroup}.
	 * @param ctx the parse tree
	 */
	void exitDropLogfileGroup(CSqlParser.DropLogfileGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dropProcedure}.
	 * @param ctx the parse tree
	 */
	void enterDropProcedure(CSqlParser.DropProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dropProcedure}.
	 * @param ctx the parse tree
	 */
	void exitDropProcedure(CSqlParser.DropProcedureContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dropFunction}.
	 * @param ctx the parse tree
	 */
	void enterDropFunction(CSqlParser.DropFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dropFunction}.
	 * @param ctx the parse tree
	 */
	void exitDropFunction(CSqlParser.DropFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dropServer}.
	 * @param ctx the parse tree
	 */
	void enterDropServer(CSqlParser.DropServerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dropServer}.
	 * @param ctx the parse tree
	 */
	void exitDropServer(CSqlParser.DropServerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dropTable}.
	 * @param ctx the parse tree
	 */
	void enterDropTable(CSqlParser.DropTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dropTable}.
	 * @param ctx the parse tree
	 */
	void exitDropTable(CSqlParser.DropTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dropTablespace}.
	 * @param ctx the parse tree
	 */
	void enterDropTablespace(CSqlParser.DropTablespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dropTablespace}.
	 * @param ctx the parse tree
	 */
	void exitDropTablespace(CSqlParser.DropTablespaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dropTrigger}.
	 * @param ctx the parse tree
	 */
	void enterDropTrigger(CSqlParser.DropTriggerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dropTrigger}.
	 * @param ctx the parse tree
	 */
	void exitDropTrigger(CSqlParser.DropTriggerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dropView}.
	 * @param ctx the parse tree
	 */
	void enterDropView(CSqlParser.DropViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dropView}.
	 * @param ctx the parse tree
	 */
	void exitDropView(CSqlParser.DropViewContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#renameTable}.
	 * @param ctx the parse tree
	 */
	void enterRenameTable(CSqlParser.RenameTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#renameTable}.
	 * @param ctx the parse tree
	 */
	void exitRenameTable(CSqlParser.RenameTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#renameTableClause}.
	 * @param ctx the parse tree
	 */
	void enterRenameTableClause(CSqlParser.RenameTableClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#renameTableClause}.
	 * @param ctx the parse tree
	 */
	void exitRenameTableClause(CSqlParser.RenameTableClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#truncateTable}.
	 * @param ctx the parse tree
	 */
	void enterTruncateTable(CSqlParser.TruncateTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#truncateTable}.
	 * @param ctx the parse tree
	 */
	void exitTruncateTable(CSqlParser.TruncateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#callStatement}.
	 * @param ctx the parse tree
	 */
	void enterCallStatement(CSqlParser.CallStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#callStatement}.
	 * @param ctx the parse tree
	 */
	void exitCallStatement(CSqlParser.CallStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeleteStatement(CSqlParser.DeleteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeleteStatement(CSqlParser.DeleteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#doStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoStatement(CSqlParser.DoStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#doStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoStatement(CSqlParser.DoStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#handlerStatement}.
	 * @param ctx the parse tree
	 */
	void enterHandlerStatement(CSqlParser.HandlerStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#handlerStatement}.
	 * @param ctx the parse tree
	 */
	void exitHandlerStatement(CSqlParser.HandlerStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void enterInsertStatement(CSqlParser.InsertStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void exitInsertStatement(CSqlParser.InsertStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#loadDataStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoadDataStatement(CSqlParser.LoadDataStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#loadDataStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoadDataStatement(CSqlParser.LoadDataStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#loadXmlStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoadXmlStatement(CSqlParser.LoadXmlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#loadXmlStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoadXmlStatement(CSqlParser.LoadXmlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#replaceStatement}.
	 * @param ctx the parse tree
	 */
	void enterReplaceStatement(CSqlParser.ReplaceStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#replaceStatement}.
	 * @param ctx the parse tree
	 */
	void exitReplaceStatement(CSqlParser.ReplaceStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleSelect}
	 * labeled alternative in {@link CSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleSelect(CSqlParser.SimpleSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleSelect}
	 * labeled alternative in {@link CSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleSelect(CSqlParser.SimpleSelectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesisSelect}
	 * labeled alternative in {@link CSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterParenthesisSelect(CSqlParser.ParenthesisSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesisSelect}
	 * labeled alternative in {@link CSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitParenthesisSelect(CSqlParser.ParenthesisSelectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unionSelect}
	 * labeled alternative in {@link CSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterUnionSelect(CSqlParser.UnionSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unionSelect}
	 * labeled alternative in {@link CSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitUnionSelect(CSqlParser.UnionSelectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unionParenthesisSelect}
	 * labeled alternative in {@link CSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterUnionParenthesisSelect(CSqlParser.UnionParenthesisSelectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unionParenthesisSelect}
	 * labeled alternative in {@link CSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitUnionParenthesisSelect(CSqlParser.UnionParenthesisSelectContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void enterUpdateStatement(CSqlParser.UpdateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void exitUpdateStatement(CSqlParser.UpdateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#insertStatementValue}.
	 * @param ctx the parse tree
	 */
	void enterInsertStatementValue(CSqlParser.InsertStatementValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#insertStatementValue}.
	 * @param ctx the parse tree
	 */
	void exitInsertStatementValue(CSqlParser.InsertStatementValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#updatedElement}.
	 * @param ctx the parse tree
	 */
	void enterUpdatedElement(CSqlParser.UpdatedElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#updatedElement}.
	 * @param ctx the parse tree
	 */
	void exitUpdatedElement(CSqlParser.UpdatedElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#assignmentField}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentField(CSqlParser.AssignmentFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#assignmentField}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentField(CSqlParser.AssignmentFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#lockClause}.
	 * @param ctx the parse tree
	 */
	void enterLockClause(CSqlParser.LockClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#lockClause}.
	 * @param ctx the parse tree
	 */
	void exitLockClause(CSqlParser.LockClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#singleDeleteStatement}.
	 * @param ctx the parse tree
	 */
	void enterSingleDeleteStatement(CSqlParser.SingleDeleteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#singleDeleteStatement}.
	 * @param ctx the parse tree
	 */
	void exitSingleDeleteStatement(CSqlParser.SingleDeleteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#multipleDeleteStatement}.
	 * @param ctx the parse tree
	 */
	void enterMultipleDeleteStatement(CSqlParser.MultipleDeleteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#multipleDeleteStatement}.
	 * @param ctx the parse tree
	 */
	void exitMultipleDeleteStatement(CSqlParser.MultipleDeleteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#handlerOpenStatement}.
	 * @param ctx the parse tree
	 */
	void enterHandlerOpenStatement(CSqlParser.HandlerOpenStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#handlerOpenStatement}.
	 * @param ctx the parse tree
	 */
	void exitHandlerOpenStatement(CSqlParser.HandlerOpenStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#handlerReadIndexStatement}.
	 * @param ctx the parse tree
	 */
	void enterHandlerReadIndexStatement(CSqlParser.HandlerReadIndexStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#handlerReadIndexStatement}.
	 * @param ctx the parse tree
	 */
	void exitHandlerReadIndexStatement(CSqlParser.HandlerReadIndexStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#handlerReadStatement}.
	 * @param ctx the parse tree
	 */
	void enterHandlerReadStatement(CSqlParser.HandlerReadStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#handlerReadStatement}.
	 * @param ctx the parse tree
	 */
	void exitHandlerReadStatement(CSqlParser.HandlerReadStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#handlerCloseStatement}.
	 * @param ctx the parse tree
	 */
	void enterHandlerCloseStatement(CSqlParser.HandlerCloseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#handlerCloseStatement}.
	 * @param ctx the parse tree
	 */
	void exitHandlerCloseStatement(CSqlParser.HandlerCloseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#singleUpdateStatement}.
	 * @param ctx the parse tree
	 */
	void enterSingleUpdateStatement(CSqlParser.SingleUpdateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#singleUpdateStatement}.
	 * @param ctx the parse tree
	 */
	void exitSingleUpdateStatement(CSqlParser.SingleUpdateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#multipleUpdateStatement}.
	 * @param ctx the parse tree
	 */
	void enterMultipleUpdateStatement(CSqlParser.MultipleUpdateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#multipleUpdateStatement}.
	 * @param ctx the parse tree
	 */
	void exitMultipleUpdateStatement(CSqlParser.MultipleUpdateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void enterOrderByClause(CSqlParser.OrderByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void exitOrderByClause(CSqlParser.OrderByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#orderByExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrderByExpression(CSqlParser.OrderByExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#orderByExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrderByExpression(CSqlParser.OrderByExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#tableSources}.
	 * @param ctx the parse tree
	 */
	void enterTableSources(CSqlParser.TableSourcesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#tableSources}.
	 * @param ctx the parse tree
	 */
	void exitTableSources(CSqlParser.TableSourcesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link CSqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableSourceBase(CSqlParser.TableSourceBaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link CSqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableSourceBase(CSqlParser.TableSourceBaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableSourceNested}
	 * labeled alternative in {@link CSqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableSourceNested(CSqlParser.TableSourceNestedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableSourceNested}
	 * labeled alternative in {@link CSqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableSourceNested(CSqlParser.TableSourceNestedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomTableItem}
	 * labeled alternative in {@link CSqlParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void enterAtomTableItem(CSqlParser.AtomTableItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomTableItem}
	 * labeled alternative in {@link CSqlParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void exitAtomTableItem(CSqlParser.AtomTableItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subqueryTableItem}
	 * labeled alternative in {@link CSqlParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void enterSubqueryTableItem(CSqlParser.SubqueryTableItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subqueryTableItem}
	 * labeled alternative in {@link CSqlParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void exitSubqueryTableItem(CSqlParser.SubqueryTableItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableSourcesItem}
	 * labeled alternative in {@link CSqlParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void enterTableSourcesItem(CSqlParser.TableSourcesItemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableSourcesItem}
	 * labeled alternative in {@link CSqlParser#tableSourceItem}.
	 * @param ctx the parse tree
	 */
	void exitTableSourcesItem(CSqlParser.TableSourcesItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#indexHint}.
	 * @param ctx the parse tree
	 */
	void enterIndexHint(CSqlParser.IndexHintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#indexHint}.
	 * @param ctx the parse tree
	 */
	void exitIndexHint(CSqlParser.IndexHintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#indexHintType}.
	 * @param ctx the parse tree
	 */
	void enterIndexHintType(CSqlParser.IndexHintTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#indexHintType}.
	 * @param ctx the parse tree
	 */
	void exitIndexHintType(CSqlParser.IndexHintTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code innerJoin}
	 * labeled alternative in {@link CSqlParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void enterInnerJoin(CSqlParser.InnerJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code innerJoin}
	 * labeled alternative in {@link CSqlParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void exitInnerJoin(CSqlParser.InnerJoinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code straightJoin}
	 * labeled alternative in {@link CSqlParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void enterStraightJoin(CSqlParser.StraightJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code straightJoin}
	 * labeled alternative in {@link CSqlParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void exitStraightJoin(CSqlParser.StraightJoinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code outerJoin}
	 * labeled alternative in {@link CSqlParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void enterOuterJoin(CSqlParser.OuterJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code outerJoin}
	 * labeled alternative in {@link CSqlParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void exitOuterJoin(CSqlParser.OuterJoinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code naturalJoin}
	 * labeled alternative in {@link CSqlParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void enterNaturalJoin(CSqlParser.NaturalJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code naturalJoin}
	 * labeled alternative in {@link CSqlParser#joinPart}.
	 * @param ctx the parse tree
	 */
	void exitNaturalJoin(CSqlParser.NaturalJoinContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#queryExpression}.
	 * @param ctx the parse tree
	 */
	void enterQueryExpression(CSqlParser.QueryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#queryExpression}.
	 * @param ctx the parse tree
	 */
	void exitQueryExpression(CSqlParser.QueryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#queryExpressionNointo}.
	 * @param ctx the parse tree
	 */
	void enterQueryExpressionNointo(CSqlParser.QueryExpressionNointoContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#queryExpressionNointo}.
	 * @param ctx the parse tree
	 */
	void exitQueryExpressionNointo(CSqlParser.QueryExpressionNointoContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#querySpecification}.
	 * @param ctx the parse tree
	 */
	void enterQuerySpecification(CSqlParser.QuerySpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#querySpecification}.
	 * @param ctx the parse tree
	 */
	void exitQuerySpecification(CSqlParser.QuerySpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#querySpecificationNointo}.
	 * @param ctx the parse tree
	 */
	void enterQuerySpecificationNointo(CSqlParser.QuerySpecificationNointoContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#querySpecificationNointo}.
	 * @param ctx the parse tree
	 */
	void exitQuerySpecificationNointo(CSqlParser.QuerySpecificationNointoContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#unionParenthesis}.
	 * @param ctx the parse tree
	 */
	void enterUnionParenthesis(CSqlParser.UnionParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#unionParenthesis}.
	 * @param ctx the parse tree
	 */
	void exitUnionParenthesis(CSqlParser.UnionParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#unionStatement}.
	 * @param ctx the parse tree
	 */
	void enterUnionStatement(CSqlParser.UnionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#unionStatement}.
	 * @param ctx the parse tree
	 */
	void exitUnionStatement(CSqlParser.UnionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#selectSpec}.
	 * @param ctx the parse tree
	 */
	void enterSelectSpec(CSqlParser.SelectSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#selectSpec}.
	 * @param ctx the parse tree
	 */
	void exitSelectSpec(CSqlParser.SelectSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void enterSelectElements(CSqlParser.SelectElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void exitSelectElements(CSqlParser.SelectElementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectStarElement}
	 * labeled alternative in {@link CSqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStarElement(CSqlParser.SelectStarElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectStarElement}
	 * labeled alternative in {@link CSqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStarElement(CSqlParser.SelectStarElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectColumnElement}
	 * labeled alternative in {@link CSqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectColumnElement(CSqlParser.SelectColumnElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectColumnElement}
	 * labeled alternative in {@link CSqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectColumnElement(CSqlParser.SelectColumnElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectFunctionElement}
	 * labeled alternative in {@link CSqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectFunctionElement(CSqlParser.SelectFunctionElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectFunctionElement}
	 * labeled alternative in {@link CSqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectFunctionElement(CSqlParser.SelectFunctionElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectExpressionElement}
	 * labeled alternative in {@link CSqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectExpressionElement(CSqlParser.SelectExpressionElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectExpressionElement}
	 * labeled alternative in {@link CSqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectExpressionElement(CSqlParser.SelectExpressionElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectIntoVariables}
	 * labeled alternative in {@link CSqlParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 */
	void enterSelectIntoVariables(CSqlParser.SelectIntoVariablesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectIntoVariables}
	 * labeled alternative in {@link CSqlParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 */
	void exitSelectIntoVariables(CSqlParser.SelectIntoVariablesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectIntoDumpFile}
	 * labeled alternative in {@link CSqlParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 */
	void enterSelectIntoDumpFile(CSqlParser.SelectIntoDumpFileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectIntoDumpFile}
	 * labeled alternative in {@link CSqlParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 */
	void exitSelectIntoDumpFile(CSqlParser.SelectIntoDumpFileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectIntoTextFile}
	 * labeled alternative in {@link CSqlParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 */
	void enterSelectIntoTextFile(CSqlParser.SelectIntoTextFileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectIntoTextFile}
	 * labeled alternative in {@link CSqlParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 */
	void exitSelectIntoTextFile(CSqlParser.SelectIntoTextFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#selectFieldsInto}.
	 * @param ctx the parse tree
	 */
	void enterSelectFieldsInto(CSqlParser.SelectFieldsIntoContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#selectFieldsInto}.
	 * @param ctx the parse tree
	 */
	void exitSelectFieldsInto(CSqlParser.SelectFieldsIntoContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#selectLinesInto}.
	 * @param ctx the parse tree
	 */
	void enterSelectLinesInto(CSqlParser.SelectLinesIntoContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#selectLinesInto}.
	 * @param ctx the parse tree
	 */
	void exitSelectLinesInto(CSqlParser.SelectLinesIntoContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void enterFromClause(CSqlParser.FromClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#fromClause}.
	 * @param ctx the parse tree
	 */
	void exitFromClause(CSqlParser.FromClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void enterGroupByClause(CSqlParser.GroupByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void exitGroupByClause(CSqlParser.GroupByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#havingClause}.
	 * @param ctx the parse tree
	 */
	void enterHavingClause(CSqlParser.HavingClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#havingClause}.
	 * @param ctx the parse tree
	 */
	void exitHavingClause(CSqlParser.HavingClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#windowClause}.
	 * @param ctx the parse tree
	 */
	void enterWindowClause(CSqlParser.WindowClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#windowClause}.
	 * @param ctx the parse tree
	 */
	void exitWindowClause(CSqlParser.WindowClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#groupByItem}.
	 * @param ctx the parse tree
	 */
	void enterGroupByItem(CSqlParser.GroupByItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#groupByItem}.
	 * @param ctx the parse tree
	 */
	void exitGroupByItem(CSqlParser.GroupByItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void enterLimitClause(CSqlParser.LimitClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void exitLimitClause(CSqlParser.LimitClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#limitClauseAtom}.
	 * @param ctx the parse tree
	 */
	void enterLimitClauseAtom(CSqlParser.LimitClauseAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#limitClauseAtom}.
	 * @param ctx the parse tree
	 */
	void exitLimitClauseAtom(CSqlParser.LimitClauseAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#startTransaction}.
	 * @param ctx the parse tree
	 */
	void enterStartTransaction(CSqlParser.StartTransactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#startTransaction}.
	 * @param ctx the parse tree
	 */
	void exitStartTransaction(CSqlParser.StartTransactionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#beginWork}.
	 * @param ctx the parse tree
	 */
	void enterBeginWork(CSqlParser.BeginWorkContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#beginWork}.
	 * @param ctx the parse tree
	 */
	void exitBeginWork(CSqlParser.BeginWorkContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#commitWork}.
	 * @param ctx the parse tree
	 */
	void enterCommitWork(CSqlParser.CommitWorkContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#commitWork}.
	 * @param ctx the parse tree
	 */
	void exitCommitWork(CSqlParser.CommitWorkContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#rollbackWork}.
	 * @param ctx the parse tree
	 */
	void enterRollbackWork(CSqlParser.RollbackWorkContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#rollbackWork}.
	 * @param ctx the parse tree
	 */
	void exitRollbackWork(CSqlParser.RollbackWorkContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#savepointStatement}.
	 * @param ctx the parse tree
	 */
	void enterSavepointStatement(CSqlParser.SavepointStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#savepointStatement}.
	 * @param ctx the parse tree
	 */
	void exitSavepointStatement(CSqlParser.SavepointStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#rollbackStatement}.
	 * @param ctx the parse tree
	 */
	void enterRollbackStatement(CSqlParser.RollbackStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#rollbackStatement}.
	 * @param ctx the parse tree
	 */
	void exitRollbackStatement(CSqlParser.RollbackStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#releaseStatement}.
	 * @param ctx the parse tree
	 */
	void enterReleaseStatement(CSqlParser.ReleaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#releaseStatement}.
	 * @param ctx the parse tree
	 */
	void exitReleaseStatement(CSqlParser.ReleaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#lockTables}.
	 * @param ctx the parse tree
	 */
	void enterLockTables(CSqlParser.LockTablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#lockTables}.
	 * @param ctx the parse tree
	 */
	void exitLockTables(CSqlParser.LockTablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#unlockTables}.
	 * @param ctx the parse tree
	 */
	void enterUnlockTables(CSqlParser.UnlockTablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#unlockTables}.
	 * @param ctx the parse tree
	 */
	void exitUnlockTables(CSqlParser.UnlockTablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#setAutocommitStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetAutocommitStatement(CSqlParser.SetAutocommitStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#setAutocommitStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetAutocommitStatement(CSqlParser.SetAutocommitStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#setTransactionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetTransactionStatement(CSqlParser.SetTransactionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#setTransactionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetTransactionStatement(CSqlParser.SetTransactionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#transactionMode}.
	 * @param ctx the parse tree
	 */
	void enterTransactionMode(CSqlParser.TransactionModeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#transactionMode}.
	 * @param ctx the parse tree
	 */
	void exitTransactionMode(CSqlParser.TransactionModeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#lockTableElement}.
	 * @param ctx the parse tree
	 */
	void enterLockTableElement(CSqlParser.LockTableElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#lockTableElement}.
	 * @param ctx the parse tree
	 */
	void exitLockTableElement(CSqlParser.LockTableElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#lockAction}.
	 * @param ctx the parse tree
	 */
	void enterLockAction(CSqlParser.LockActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#lockAction}.
	 * @param ctx the parse tree
	 */
	void exitLockAction(CSqlParser.LockActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#transactionOption}.
	 * @param ctx the parse tree
	 */
	void enterTransactionOption(CSqlParser.TransactionOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#transactionOption}.
	 * @param ctx the parse tree
	 */
	void exitTransactionOption(CSqlParser.TransactionOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#transactionLevel}.
	 * @param ctx the parse tree
	 */
	void enterTransactionLevel(CSqlParser.TransactionLevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#transactionLevel}.
	 * @param ctx the parse tree
	 */
	void exitTransactionLevel(CSqlParser.TransactionLevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#changeMaster}.
	 * @param ctx the parse tree
	 */
	void enterChangeMaster(CSqlParser.ChangeMasterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#changeMaster}.
	 * @param ctx the parse tree
	 */
	void exitChangeMaster(CSqlParser.ChangeMasterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#changeReplicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterChangeReplicationFilter(CSqlParser.ChangeReplicationFilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#changeReplicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitChangeReplicationFilter(CSqlParser.ChangeReplicationFilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#purgeBinaryLogs}.
	 * @param ctx the parse tree
	 */
	void enterPurgeBinaryLogs(CSqlParser.PurgeBinaryLogsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#purgeBinaryLogs}.
	 * @param ctx the parse tree
	 */
	void exitPurgeBinaryLogs(CSqlParser.PurgeBinaryLogsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#resetMaster}.
	 * @param ctx the parse tree
	 */
	void enterResetMaster(CSqlParser.ResetMasterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#resetMaster}.
	 * @param ctx the parse tree
	 */
	void exitResetMaster(CSqlParser.ResetMasterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#resetSlave}.
	 * @param ctx the parse tree
	 */
	void enterResetSlave(CSqlParser.ResetSlaveContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#resetSlave}.
	 * @param ctx the parse tree
	 */
	void exitResetSlave(CSqlParser.ResetSlaveContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#startSlave}.
	 * @param ctx the parse tree
	 */
	void enterStartSlave(CSqlParser.StartSlaveContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#startSlave}.
	 * @param ctx the parse tree
	 */
	void exitStartSlave(CSqlParser.StartSlaveContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#stopSlave}.
	 * @param ctx the parse tree
	 */
	void enterStopSlave(CSqlParser.StopSlaveContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#stopSlave}.
	 * @param ctx the parse tree
	 */
	void exitStopSlave(CSqlParser.StopSlaveContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#startGroupReplication}.
	 * @param ctx the parse tree
	 */
	void enterStartGroupReplication(CSqlParser.StartGroupReplicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#startGroupReplication}.
	 * @param ctx the parse tree
	 */
	void exitStartGroupReplication(CSqlParser.StartGroupReplicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#stopGroupReplication}.
	 * @param ctx the parse tree
	 */
	void enterStopGroupReplication(CSqlParser.StopGroupReplicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#stopGroupReplication}.
	 * @param ctx the parse tree
	 */
	void exitStopGroupReplication(CSqlParser.StopGroupReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code masterStringOption}
	 * labeled alternative in {@link CSqlParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void enterMasterStringOption(CSqlParser.MasterStringOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code masterStringOption}
	 * labeled alternative in {@link CSqlParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void exitMasterStringOption(CSqlParser.MasterStringOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code masterDecimalOption}
	 * labeled alternative in {@link CSqlParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void enterMasterDecimalOption(CSqlParser.MasterDecimalOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code masterDecimalOption}
	 * labeled alternative in {@link CSqlParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void exitMasterDecimalOption(CSqlParser.MasterDecimalOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code masterBoolOption}
	 * labeled alternative in {@link CSqlParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void enterMasterBoolOption(CSqlParser.MasterBoolOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code masterBoolOption}
	 * labeled alternative in {@link CSqlParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void exitMasterBoolOption(CSqlParser.MasterBoolOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code masterRealOption}
	 * labeled alternative in {@link CSqlParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void enterMasterRealOption(CSqlParser.MasterRealOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code masterRealOption}
	 * labeled alternative in {@link CSqlParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void exitMasterRealOption(CSqlParser.MasterRealOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code masterUidListOption}
	 * labeled alternative in {@link CSqlParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void enterMasterUidListOption(CSqlParser.MasterUidListOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code masterUidListOption}
	 * labeled alternative in {@link CSqlParser#masterOption}.
	 * @param ctx the parse tree
	 */
	void exitMasterUidListOption(CSqlParser.MasterUidListOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#stringMasterOption}.
	 * @param ctx the parse tree
	 */
	void enterStringMasterOption(CSqlParser.StringMasterOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#stringMasterOption}.
	 * @param ctx the parse tree
	 */
	void exitStringMasterOption(CSqlParser.StringMasterOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#decimalMasterOption}.
	 * @param ctx the parse tree
	 */
	void enterDecimalMasterOption(CSqlParser.DecimalMasterOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#decimalMasterOption}.
	 * @param ctx the parse tree
	 */
	void exitDecimalMasterOption(CSqlParser.DecimalMasterOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#boolMasterOption}.
	 * @param ctx the parse tree
	 */
	void enterBoolMasterOption(CSqlParser.BoolMasterOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#boolMasterOption}.
	 * @param ctx the parse tree
	 */
	void exitBoolMasterOption(CSqlParser.BoolMasterOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#channelOption}.
	 * @param ctx the parse tree
	 */
	void enterChannelOption(CSqlParser.ChannelOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#channelOption}.
	 * @param ctx the parse tree
	 */
	void exitChannelOption(CSqlParser.ChannelOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doDbReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterDoDbReplication(CSqlParser.DoDbReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doDbReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitDoDbReplication(CSqlParser.DoDbReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ignoreDbReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterIgnoreDbReplication(CSqlParser.IgnoreDbReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ignoreDbReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitIgnoreDbReplication(CSqlParser.IgnoreDbReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doTableReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterDoTableReplication(CSqlParser.DoTableReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doTableReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitDoTableReplication(CSqlParser.DoTableReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ignoreTableReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterIgnoreTableReplication(CSqlParser.IgnoreTableReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ignoreTableReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitIgnoreTableReplication(CSqlParser.IgnoreTableReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code wildDoTableReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterWildDoTableReplication(CSqlParser.WildDoTableReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code wildDoTableReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitWildDoTableReplication(CSqlParser.WildDoTableReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code wildIgnoreTableReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterWildIgnoreTableReplication(CSqlParser.WildIgnoreTableReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code wildIgnoreTableReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitWildIgnoreTableReplication(CSqlParser.WildIgnoreTableReplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rewriteDbReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void enterRewriteDbReplication(CSqlParser.RewriteDbReplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rewriteDbReplication}
	 * labeled alternative in {@link CSqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 */
	void exitRewriteDbReplication(CSqlParser.RewriteDbReplicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#tablePair}.
	 * @param ctx the parse tree
	 */
	void enterTablePair(CSqlParser.TablePairContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#tablePair}.
	 * @param ctx the parse tree
	 */
	void exitTablePair(CSqlParser.TablePairContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#threadType}.
	 * @param ctx the parse tree
	 */
	void enterThreadType(CSqlParser.ThreadTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#threadType}.
	 * @param ctx the parse tree
	 */
	void exitThreadType(CSqlParser.ThreadTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code gtidsUntilOption}
	 * labeled alternative in {@link CSqlParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void enterGtidsUntilOption(CSqlParser.GtidsUntilOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code gtidsUntilOption}
	 * labeled alternative in {@link CSqlParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void exitGtidsUntilOption(CSqlParser.GtidsUntilOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code masterLogUntilOption}
	 * labeled alternative in {@link CSqlParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void enterMasterLogUntilOption(CSqlParser.MasterLogUntilOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code masterLogUntilOption}
	 * labeled alternative in {@link CSqlParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void exitMasterLogUntilOption(CSqlParser.MasterLogUntilOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relayLogUntilOption}
	 * labeled alternative in {@link CSqlParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void enterRelayLogUntilOption(CSqlParser.RelayLogUntilOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relayLogUntilOption}
	 * labeled alternative in {@link CSqlParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void exitRelayLogUntilOption(CSqlParser.RelayLogUntilOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sqlGapsUntilOption}
	 * labeled alternative in {@link CSqlParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void enterSqlGapsUntilOption(CSqlParser.SqlGapsUntilOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sqlGapsUntilOption}
	 * labeled alternative in {@link CSqlParser#untilOption}.
	 * @param ctx the parse tree
	 */
	void exitSqlGapsUntilOption(CSqlParser.SqlGapsUntilOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userConnectionOption}
	 * labeled alternative in {@link CSqlParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void enterUserConnectionOption(CSqlParser.UserConnectionOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userConnectionOption}
	 * labeled alternative in {@link CSqlParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void exitUserConnectionOption(CSqlParser.UserConnectionOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code passwordConnectionOption}
	 * labeled alternative in {@link CSqlParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void enterPasswordConnectionOption(CSqlParser.PasswordConnectionOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code passwordConnectionOption}
	 * labeled alternative in {@link CSqlParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void exitPasswordConnectionOption(CSqlParser.PasswordConnectionOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defaultAuthConnectionOption}
	 * labeled alternative in {@link CSqlParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void enterDefaultAuthConnectionOption(CSqlParser.DefaultAuthConnectionOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defaultAuthConnectionOption}
	 * labeled alternative in {@link CSqlParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void exitDefaultAuthConnectionOption(CSqlParser.DefaultAuthConnectionOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pluginDirConnectionOption}
	 * labeled alternative in {@link CSqlParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void enterPluginDirConnectionOption(CSqlParser.PluginDirConnectionOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pluginDirConnectionOption}
	 * labeled alternative in {@link CSqlParser#connectionOption}.
	 * @param ctx the parse tree
	 */
	void exitPluginDirConnectionOption(CSqlParser.PluginDirConnectionOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#gtuidSet}.
	 * @param ctx the parse tree
	 */
	void enterGtuidSet(CSqlParser.GtuidSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#gtuidSet}.
	 * @param ctx the parse tree
	 */
	void exitGtuidSet(CSqlParser.GtuidSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#xaStartTransaction}.
	 * @param ctx the parse tree
	 */
	void enterXaStartTransaction(CSqlParser.XaStartTransactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#xaStartTransaction}.
	 * @param ctx the parse tree
	 */
	void exitXaStartTransaction(CSqlParser.XaStartTransactionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#xaEndTransaction}.
	 * @param ctx the parse tree
	 */
	void enterXaEndTransaction(CSqlParser.XaEndTransactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#xaEndTransaction}.
	 * @param ctx the parse tree
	 */
	void exitXaEndTransaction(CSqlParser.XaEndTransactionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#xaPrepareStatement}.
	 * @param ctx the parse tree
	 */
	void enterXaPrepareStatement(CSqlParser.XaPrepareStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#xaPrepareStatement}.
	 * @param ctx the parse tree
	 */
	void exitXaPrepareStatement(CSqlParser.XaPrepareStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#xaCommitWork}.
	 * @param ctx the parse tree
	 */
	void enterXaCommitWork(CSqlParser.XaCommitWorkContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#xaCommitWork}.
	 * @param ctx the parse tree
	 */
	void exitXaCommitWork(CSqlParser.XaCommitWorkContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#xaRollbackWork}.
	 * @param ctx the parse tree
	 */
	void enterXaRollbackWork(CSqlParser.XaRollbackWorkContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#xaRollbackWork}.
	 * @param ctx the parse tree
	 */
	void exitXaRollbackWork(CSqlParser.XaRollbackWorkContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#xaRecoverWork}.
	 * @param ctx the parse tree
	 */
	void enterXaRecoverWork(CSqlParser.XaRecoverWorkContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#xaRecoverWork}.
	 * @param ctx the parse tree
	 */
	void exitXaRecoverWork(CSqlParser.XaRecoverWorkContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#prepareStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrepareStatement(CSqlParser.PrepareStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#prepareStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrepareStatement(CSqlParser.PrepareStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#executeStatement}.
	 * @param ctx the parse tree
	 */
	void enterExecuteStatement(CSqlParser.ExecuteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#executeStatement}.
	 * @param ctx the parse tree
	 */
	void exitExecuteStatement(CSqlParser.ExecuteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#deallocatePrepare}.
	 * @param ctx the parse tree
	 */
	void enterDeallocatePrepare(CSqlParser.DeallocatePrepareContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#deallocatePrepare}.
	 * @param ctx the parse tree
	 */
	void exitDeallocatePrepare(CSqlParser.DeallocatePrepareContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#routineBody}.
	 * @param ctx the parse tree
	 */
	void enterRoutineBody(CSqlParser.RoutineBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#routineBody}.
	 * @param ctx the parse tree
	 */
	void exitRoutineBody(CSqlParser.RoutineBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(CSqlParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(CSqlParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void enterCaseStatement(CSqlParser.CaseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#caseStatement}.
	 * @param ctx the parse tree
	 */
	void exitCaseStatement(CSqlParser.CaseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(CSqlParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(CSqlParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#iterateStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterateStatement(CSqlParser.IterateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#iterateStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterateStatement(CSqlParser.IterateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#leaveStatement}.
	 * @param ctx the parse tree
	 */
	void enterLeaveStatement(CSqlParser.LeaveStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#leaveStatement}.
	 * @param ctx the parse tree
	 */
	void exitLeaveStatement(CSqlParser.LeaveStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoopStatement(CSqlParser.LoopStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoopStatement(CSqlParser.LoopStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#repeatStatement}.
	 * @param ctx the parse tree
	 */
	void enterRepeatStatement(CSqlParser.RepeatStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#repeatStatement}.
	 * @param ctx the parse tree
	 */
	void exitRepeatStatement(CSqlParser.RepeatStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(CSqlParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(CSqlParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(CSqlParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(CSqlParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CloseCursor}
	 * labeled alternative in {@link CSqlParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void enterCloseCursor(CSqlParser.CloseCursorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CloseCursor}
	 * labeled alternative in {@link CSqlParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void exitCloseCursor(CSqlParser.CloseCursorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FetchCursor}
	 * labeled alternative in {@link CSqlParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void enterFetchCursor(CSqlParser.FetchCursorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FetchCursor}
	 * labeled alternative in {@link CSqlParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void exitFetchCursor(CSqlParser.FetchCursorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OpenCursor}
	 * labeled alternative in {@link CSqlParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void enterOpenCursor(CSqlParser.OpenCursorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OpenCursor}
	 * labeled alternative in {@link CSqlParser#cursorStatement}.
	 * @param ctx the parse tree
	 */
	void exitOpenCursor(CSqlParser.OpenCursorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#declareVariable}.
	 * @param ctx the parse tree
	 */
	void enterDeclareVariable(CSqlParser.DeclareVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#declareVariable}.
	 * @param ctx the parse tree
	 */
	void exitDeclareVariable(CSqlParser.DeclareVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#declareCondition}.
	 * @param ctx the parse tree
	 */
	void enterDeclareCondition(CSqlParser.DeclareConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#declareCondition}.
	 * @param ctx the parse tree
	 */
	void exitDeclareCondition(CSqlParser.DeclareConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#declareCursor}.
	 * @param ctx the parse tree
	 */
	void enterDeclareCursor(CSqlParser.DeclareCursorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#declareCursor}.
	 * @param ctx the parse tree
	 */
	void exitDeclareCursor(CSqlParser.DeclareCursorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#declareHandler}.
	 * @param ctx the parse tree
	 */
	void enterDeclareHandler(CSqlParser.DeclareHandlerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#declareHandler}.
	 * @param ctx the parse tree
	 */
	void exitDeclareHandler(CSqlParser.DeclareHandlerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code handlerConditionCode}
	 * labeled alternative in {@link CSqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void enterHandlerConditionCode(CSqlParser.HandlerConditionCodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code handlerConditionCode}
	 * labeled alternative in {@link CSqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void exitHandlerConditionCode(CSqlParser.HandlerConditionCodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code handlerConditionState}
	 * labeled alternative in {@link CSqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void enterHandlerConditionState(CSqlParser.HandlerConditionStateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code handlerConditionState}
	 * labeled alternative in {@link CSqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void exitHandlerConditionState(CSqlParser.HandlerConditionStateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code handlerConditionName}
	 * labeled alternative in {@link CSqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void enterHandlerConditionName(CSqlParser.HandlerConditionNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code handlerConditionName}
	 * labeled alternative in {@link CSqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void exitHandlerConditionName(CSqlParser.HandlerConditionNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code handlerConditionWarning}
	 * labeled alternative in {@link CSqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void enterHandlerConditionWarning(CSqlParser.HandlerConditionWarningContext ctx);
	/**
	 * Exit a parse tree produced by the {@code handlerConditionWarning}
	 * labeled alternative in {@link CSqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void exitHandlerConditionWarning(CSqlParser.HandlerConditionWarningContext ctx);
	/**
	 * Enter a parse tree produced by the {@code handlerConditionNotfound}
	 * labeled alternative in {@link CSqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void enterHandlerConditionNotfound(CSqlParser.HandlerConditionNotfoundContext ctx);
	/**
	 * Exit a parse tree produced by the {@code handlerConditionNotfound}
	 * labeled alternative in {@link CSqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void exitHandlerConditionNotfound(CSqlParser.HandlerConditionNotfoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code handlerConditionException}
	 * labeled alternative in {@link CSqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void enterHandlerConditionException(CSqlParser.HandlerConditionExceptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code handlerConditionException}
	 * labeled alternative in {@link CSqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 */
	void exitHandlerConditionException(CSqlParser.HandlerConditionExceptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#procedureSqlStatement}.
	 * @param ctx the parse tree
	 */
	void enterProcedureSqlStatement(CSqlParser.ProcedureSqlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#procedureSqlStatement}.
	 * @param ctx the parse tree
	 */
	void exitProcedureSqlStatement(CSqlParser.ProcedureSqlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#caseAlternative}.
	 * @param ctx the parse tree
	 */
	void enterCaseAlternative(CSqlParser.CaseAlternativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#caseAlternative}.
	 * @param ctx the parse tree
	 */
	void exitCaseAlternative(CSqlParser.CaseAlternativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#elifAlternative}.
	 * @param ctx the parse tree
	 */
	void enterElifAlternative(CSqlParser.ElifAlternativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#elifAlternative}.
	 * @param ctx the parse tree
	 */
	void exitElifAlternative(CSqlParser.ElifAlternativeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterUserMysqlV56}
	 * labeled alternative in {@link CSqlParser#alterUser}.
	 * @param ctx the parse tree
	 */
	void enterAlterUserMysqlV56(CSqlParser.AlterUserMysqlV56Context ctx);
	/**
	 * Exit a parse tree produced by the {@code alterUserMysqlV56}
	 * labeled alternative in {@link CSqlParser#alterUser}.
	 * @param ctx the parse tree
	 */
	void exitAlterUserMysqlV56(CSqlParser.AlterUserMysqlV56Context ctx);
	/**
	 * Enter a parse tree produced by the {@code alterUserMysqlV57}
	 * labeled alternative in {@link CSqlParser#alterUser}.
	 * @param ctx the parse tree
	 */
	void enterAlterUserMysqlV57(CSqlParser.AlterUserMysqlV57Context ctx);
	/**
	 * Exit a parse tree produced by the {@code alterUserMysqlV57}
	 * labeled alternative in {@link CSqlParser#alterUser}.
	 * @param ctx the parse tree
	 */
	void exitAlterUserMysqlV57(CSqlParser.AlterUserMysqlV57Context ctx);
	/**
	 * Enter a parse tree produced by the {@code createUserMysqlV56}
	 * labeled alternative in {@link CSqlParser#createUser}.
	 * @param ctx the parse tree
	 */
	void enterCreateUserMysqlV56(CSqlParser.CreateUserMysqlV56Context ctx);
	/**
	 * Exit a parse tree produced by the {@code createUserMysqlV56}
	 * labeled alternative in {@link CSqlParser#createUser}.
	 * @param ctx the parse tree
	 */
	void exitCreateUserMysqlV56(CSqlParser.CreateUserMysqlV56Context ctx);
	/**
	 * Enter a parse tree produced by the {@code createUserMysqlV57}
	 * labeled alternative in {@link CSqlParser#createUser}.
	 * @param ctx the parse tree
	 */
	void enterCreateUserMysqlV57(CSqlParser.CreateUserMysqlV57Context ctx);
	/**
	 * Exit a parse tree produced by the {@code createUserMysqlV57}
	 * labeled alternative in {@link CSqlParser#createUser}.
	 * @param ctx the parse tree
	 */
	void exitCreateUserMysqlV57(CSqlParser.CreateUserMysqlV57Context ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dropUser}.
	 * @param ctx the parse tree
	 */
	void enterDropUser(CSqlParser.DropUserContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dropUser}.
	 * @param ctx the parse tree
	 */
	void exitDropUser(CSqlParser.DropUserContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#grantStatement}.
	 * @param ctx the parse tree
	 */
	void enterGrantStatement(CSqlParser.GrantStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#grantStatement}.
	 * @param ctx the parse tree
	 */
	void exitGrantStatement(CSqlParser.GrantStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#roleOption}.
	 * @param ctx the parse tree
	 */
	void enterRoleOption(CSqlParser.RoleOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#roleOption}.
	 * @param ctx the parse tree
	 */
	void exitRoleOption(CSqlParser.RoleOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#grantProxy}.
	 * @param ctx the parse tree
	 */
	void enterGrantProxy(CSqlParser.GrantProxyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#grantProxy}.
	 * @param ctx the parse tree
	 */
	void exitGrantProxy(CSqlParser.GrantProxyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#renameUser}.
	 * @param ctx the parse tree
	 */
	void enterRenameUser(CSqlParser.RenameUserContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#renameUser}.
	 * @param ctx the parse tree
	 */
	void exitRenameUser(CSqlParser.RenameUserContext ctx);
	/**
	 * Enter a parse tree produced by the {@code detailRevoke}
	 * labeled alternative in {@link CSqlParser#revokeStatement}.
	 * @param ctx the parse tree
	 */
	void enterDetailRevoke(CSqlParser.DetailRevokeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code detailRevoke}
	 * labeled alternative in {@link CSqlParser#revokeStatement}.
	 * @param ctx the parse tree
	 */
	void exitDetailRevoke(CSqlParser.DetailRevokeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code shortRevoke}
	 * labeled alternative in {@link CSqlParser#revokeStatement}.
	 * @param ctx the parse tree
	 */
	void enterShortRevoke(CSqlParser.ShortRevokeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code shortRevoke}
	 * labeled alternative in {@link CSqlParser#revokeStatement}.
	 * @param ctx the parse tree
	 */
	void exitShortRevoke(CSqlParser.ShortRevokeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code roleRevoke}
	 * labeled alternative in {@link CSqlParser#revokeStatement}.
	 * @param ctx the parse tree
	 */
	void enterRoleRevoke(CSqlParser.RoleRevokeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code roleRevoke}
	 * labeled alternative in {@link CSqlParser#revokeStatement}.
	 * @param ctx the parse tree
	 */
	void exitRoleRevoke(CSqlParser.RoleRevokeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#revokeProxy}.
	 * @param ctx the parse tree
	 */
	void enterRevokeProxy(CSqlParser.RevokeProxyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#revokeProxy}.
	 * @param ctx the parse tree
	 */
	void exitRevokeProxy(CSqlParser.RevokeProxyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#setPasswordStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetPasswordStatement(CSqlParser.SetPasswordStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#setPasswordStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetPasswordStatement(CSqlParser.SetPasswordStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#userSpecification}.
	 * @param ctx the parse tree
	 */
	void enterUserSpecification(CSqlParser.UserSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#userSpecification}.
	 * @param ctx the parse tree
	 */
	void exitUserSpecification(CSqlParser.UserSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code passwordAuthOption}
	 * labeled alternative in {@link CSqlParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void enterPasswordAuthOption(CSqlParser.PasswordAuthOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code passwordAuthOption}
	 * labeled alternative in {@link CSqlParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void exitPasswordAuthOption(CSqlParser.PasswordAuthOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringAuthOption}
	 * labeled alternative in {@link CSqlParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void enterStringAuthOption(CSqlParser.StringAuthOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringAuthOption}
	 * labeled alternative in {@link CSqlParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void exitStringAuthOption(CSqlParser.StringAuthOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hashAuthOption}
	 * labeled alternative in {@link CSqlParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void enterHashAuthOption(CSqlParser.HashAuthOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hashAuthOption}
	 * labeled alternative in {@link CSqlParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void exitHashAuthOption(CSqlParser.HashAuthOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleAuthOption}
	 * labeled alternative in {@link CSqlParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void enterSimpleAuthOption(CSqlParser.SimpleAuthOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleAuthOption}
	 * labeled alternative in {@link CSqlParser#userAuthOption}.
	 * @param ctx the parse tree
	 */
	void exitSimpleAuthOption(CSqlParser.SimpleAuthOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#tlsOption}.
	 * @param ctx the parse tree
	 */
	void enterTlsOption(CSqlParser.TlsOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#tlsOption}.
	 * @param ctx the parse tree
	 */
	void exitTlsOption(CSqlParser.TlsOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#userResourceOption}.
	 * @param ctx the parse tree
	 */
	void enterUserResourceOption(CSqlParser.UserResourceOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#userResourceOption}.
	 * @param ctx the parse tree
	 */
	void exitUserResourceOption(CSqlParser.UserResourceOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#userPasswordOption}.
	 * @param ctx the parse tree
	 */
	void enterUserPasswordOption(CSqlParser.UserPasswordOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#userPasswordOption}.
	 * @param ctx the parse tree
	 */
	void exitUserPasswordOption(CSqlParser.UserPasswordOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#userLockOption}.
	 * @param ctx the parse tree
	 */
	void enterUserLockOption(CSqlParser.UserLockOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#userLockOption}.
	 * @param ctx the parse tree
	 */
	void exitUserLockOption(CSqlParser.UserLockOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#privelegeClause}.
	 * @param ctx the parse tree
	 */
	void enterPrivelegeClause(CSqlParser.PrivelegeClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#privelegeClause}.
	 * @param ctx the parse tree
	 */
	void exitPrivelegeClause(CSqlParser.PrivelegeClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#privilege}.
	 * @param ctx the parse tree
	 */
	void enterPrivilege(CSqlParser.PrivilegeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#privilege}.
	 * @param ctx the parse tree
	 */
	void exitPrivilege(CSqlParser.PrivilegeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code currentSchemaPriviLevel}
	 * labeled alternative in {@link CSqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void enterCurrentSchemaPriviLevel(CSqlParser.CurrentSchemaPriviLevelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code currentSchemaPriviLevel}
	 * labeled alternative in {@link CSqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void exitCurrentSchemaPriviLevel(CSqlParser.CurrentSchemaPriviLevelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code globalPrivLevel}
	 * labeled alternative in {@link CSqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void enterGlobalPrivLevel(CSqlParser.GlobalPrivLevelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code globalPrivLevel}
	 * labeled alternative in {@link CSqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void exitGlobalPrivLevel(CSqlParser.GlobalPrivLevelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code definiteSchemaPrivLevel}
	 * labeled alternative in {@link CSqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void enterDefiniteSchemaPrivLevel(CSqlParser.DefiniteSchemaPrivLevelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code definiteSchemaPrivLevel}
	 * labeled alternative in {@link CSqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void exitDefiniteSchemaPrivLevel(CSqlParser.DefiniteSchemaPrivLevelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code definiteFullTablePrivLevel}
	 * labeled alternative in {@link CSqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void enterDefiniteFullTablePrivLevel(CSqlParser.DefiniteFullTablePrivLevelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code definiteFullTablePrivLevel}
	 * labeled alternative in {@link CSqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void exitDefiniteFullTablePrivLevel(CSqlParser.DefiniteFullTablePrivLevelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code definiteFullTablePrivLevel2}
	 * labeled alternative in {@link CSqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void enterDefiniteFullTablePrivLevel2(CSqlParser.DefiniteFullTablePrivLevel2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code definiteFullTablePrivLevel2}
	 * labeled alternative in {@link CSqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void exitDefiniteFullTablePrivLevel2(CSqlParser.DefiniteFullTablePrivLevel2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code definiteTablePrivLevel}
	 * labeled alternative in {@link CSqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void enterDefiniteTablePrivLevel(CSqlParser.DefiniteTablePrivLevelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code definiteTablePrivLevel}
	 * labeled alternative in {@link CSqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 */
	void exitDefiniteTablePrivLevel(CSqlParser.DefiniteTablePrivLevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#renameUserClause}.
	 * @param ctx the parse tree
	 */
	void enterRenameUserClause(CSqlParser.RenameUserClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#renameUserClause}.
	 * @param ctx the parse tree
	 */
	void exitRenameUserClause(CSqlParser.RenameUserClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#analyzeTable}.
	 * @param ctx the parse tree
	 */
	void enterAnalyzeTable(CSqlParser.AnalyzeTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#analyzeTable}.
	 * @param ctx the parse tree
	 */
	void exitAnalyzeTable(CSqlParser.AnalyzeTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#checkTable}.
	 * @param ctx the parse tree
	 */
	void enterCheckTable(CSqlParser.CheckTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#checkTable}.
	 * @param ctx the parse tree
	 */
	void exitCheckTable(CSqlParser.CheckTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#checksumTable}.
	 * @param ctx the parse tree
	 */
	void enterChecksumTable(CSqlParser.ChecksumTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#checksumTable}.
	 * @param ctx the parse tree
	 */
	void exitChecksumTable(CSqlParser.ChecksumTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#optimizeTable}.
	 * @param ctx the parse tree
	 */
	void enterOptimizeTable(CSqlParser.OptimizeTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#optimizeTable}.
	 * @param ctx the parse tree
	 */
	void exitOptimizeTable(CSqlParser.OptimizeTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#repairTable}.
	 * @param ctx the parse tree
	 */
	void enterRepairTable(CSqlParser.RepairTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#repairTable}.
	 * @param ctx the parse tree
	 */
	void exitRepairTable(CSqlParser.RepairTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#checkTableOption}.
	 * @param ctx the parse tree
	 */
	void enterCheckTableOption(CSqlParser.CheckTableOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#checkTableOption}.
	 * @param ctx the parse tree
	 */
	void exitCheckTableOption(CSqlParser.CheckTableOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#createUdfunction}.
	 * @param ctx the parse tree
	 */
	void enterCreateUdfunction(CSqlParser.CreateUdfunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#createUdfunction}.
	 * @param ctx the parse tree
	 */
	void exitCreateUdfunction(CSqlParser.CreateUdfunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#installPlugin}.
	 * @param ctx the parse tree
	 */
	void enterInstallPlugin(CSqlParser.InstallPluginContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#installPlugin}.
	 * @param ctx the parse tree
	 */
	void exitInstallPlugin(CSqlParser.InstallPluginContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#uninstallPlugin}.
	 * @param ctx the parse tree
	 */
	void enterUninstallPlugin(CSqlParser.UninstallPluginContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#uninstallPlugin}.
	 * @param ctx the parse tree
	 */
	void exitUninstallPlugin(CSqlParser.UninstallPluginContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setVariable}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetVariable(CSqlParser.SetVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setVariable}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetVariable(CSqlParser.SetVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setCharset}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetCharset(CSqlParser.SetCharsetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setCharset}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetCharset(CSqlParser.SetCharsetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setNames}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetNames(CSqlParser.SetNamesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setNames}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetNames(CSqlParser.SetNamesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setPassword}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetPassword(CSqlParser.SetPasswordContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setPassword}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetPassword(CSqlParser.SetPasswordContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setTransaction}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetTransaction(CSqlParser.SetTransactionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setTransaction}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetTransaction(CSqlParser.SetTransactionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setAutocommit}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetAutocommit(CSqlParser.SetAutocommitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setAutocommit}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetAutocommit(CSqlParser.SetAutocommitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setNewValueInsideTrigger}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetNewValueInsideTrigger(CSqlParser.SetNewValueInsideTriggerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setNewValueInsideTrigger}
	 * labeled alternative in {@link CSqlParser#setStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetNewValueInsideTrigger(CSqlParser.SetNewValueInsideTriggerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showMasterLogs}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowMasterLogs(CSqlParser.ShowMasterLogsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showMasterLogs}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowMasterLogs(CSqlParser.ShowMasterLogsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showLogEvents}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowLogEvents(CSqlParser.ShowLogEventsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showLogEvents}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowLogEvents(CSqlParser.ShowLogEventsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showObjectFilter}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowObjectFilter(CSqlParser.ShowObjectFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showObjectFilter}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowObjectFilter(CSqlParser.ShowObjectFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showColumns}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowColumns(CSqlParser.ShowColumnsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showColumns}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowColumns(CSqlParser.ShowColumnsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showCreateDb}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowCreateDb(CSqlParser.ShowCreateDbContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showCreateDb}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowCreateDb(CSqlParser.ShowCreateDbContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showCreateFullIdObject}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowCreateFullIdObject(CSqlParser.ShowCreateFullIdObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showCreateFullIdObject}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowCreateFullIdObject(CSqlParser.ShowCreateFullIdObjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showCreateUser}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowCreateUser(CSqlParser.ShowCreateUserContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showCreateUser}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowCreateUser(CSqlParser.ShowCreateUserContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showEngine}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowEngine(CSqlParser.ShowEngineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showEngine}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowEngine(CSqlParser.ShowEngineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showGlobalInfo}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowGlobalInfo(CSqlParser.ShowGlobalInfoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showGlobalInfo}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowGlobalInfo(CSqlParser.ShowGlobalInfoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showErrors}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowErrors(CSqlParser.ShowErrorsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showErrors}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowErrors(CSqlParser.ShowErrorsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showCountErrors}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowCountErrors(CSqlParser.ShowCountErrorsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showCountErrors}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowCountErrors(CSqlParser.ShowCountErrorsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showSchemaFilter}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowSchemaFilter(CSqlParser.ShowSchemaFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showSchemaFilter}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowSchemaFilter(CSqlParser.ShowSchemaFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showRoutine}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowRoutine(CSqlParser.ShowRoutineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showRoutine}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowRoutine(CSqlParser.ShowRoutineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showGrants}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowGrants(CSqlParser.ShowGrantsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showGrants}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowGrants(CSqlParser.ShowGrantsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showIndexes}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowIndexes(CSqlParser.ShowIndexesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showIndexes}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowIndexes(CSqlParser.ShowIndexesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showOpenTables}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowOpenTables(CSqlParser.ShowOpenTablesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showOpenTables}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowOpenTables(CSqlParser.ShowOpenTablesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showProfile}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowProfile(CSqlParser.ShowProfileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showProfile}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowProfile(CSqlParser.ShowProfileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code showSlaveStatus}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowSlaveStatus(CSqlParser.ShowSlaveStatusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code showSlaveStatus}
	 * labeled alternative in {@link CSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowSlaveStatus(CSqlParser.ShowSlaveStatusContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#variableClause}.
	 * @param ctx the parse tree
	 */
	void enterVariableClause(CSqlParser.VariableClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#variableClause}.
	 * @param ctx the parse tree
	 */
	void exitVariableClause(CSqlParser.VariableClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#showCommonEntity}.
	 * @param ctx the parse tree
	 */
	void enterShowCommonEntity(CSqlParser.ShowCommonEntityContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#showCommonEntity}.
	 * @param ctx the parse tree
	 */
	void exitShowCommonEntity(CSqlParser.ShowCommonEntityContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#showFilter}.
	 * @param ctx the parse tree
	 */
	void enterShowFilter(CSqlParser.ShowFilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#showFilter}.
	 * @param ctx the parse tree
	 */
	void exitShowFilter(CSqlParser.ShowFilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#showGlobalInfoClause}.
	 * @param ctx the parse tree
	 */
	void enterShowGlobalInfoClause(CSqlParser.ShowGlobalInfoClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#showGlobalInfoClause}.
	 * @param ctx the parse tree
	 */
	void exitShowGlobalInfoClause(CSqlParser.ShowGlobalInfoClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#showSchemaEntity}.
	 * @param ctx the parse tree
	 */
	void enterShowSchemaEntity(CSqlParser.ShowSchemaEntityContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#showSchemaEntity}.
	 * @param ctx the parse tree
	 */
	void exitShowSchemaEntity(CSqlParser.ShowSchemaEntityContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#showProfileType}.
	 * @param ctx the parse tree
	 */
	void enterShowProfileType(CSqlParser.ShowProfileTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#showProfileType}.
	 * @param ctx the parse tree
	 */
	void exitShowProfileType(CSqlParser.ShowProfileTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#binlogStatement}.
	 * @param ctx the parse tree
	 */
	void enterBinlogStatement(CSqlParser.BinlogStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#binlogStatement}.
	 * @param ctx the parse tree
	 */
	void exitBinlogStatement(CSqlParser.BinlogStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#cacheIndexStatement}.
	 * @param ctx the parse tree
	 */
	void enterCacheIndexStatement(CSqlParser.CacheIndexStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#cacheIndexStatement}.
	 * @param ctx the parse tree
	 */
	void exitCacheIndexStatement(CSqlParser.CacheIndexStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#flushStatement}.
	 * @param ctx the parse tree
	 */
	void enterFlushStatement(CSqlParser.FlushStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#flushStatement}.
	 * @param ctx the parse tree
	 */
	void exitFlushStatement(CSqlParser.FlushStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#killStatement}.
	 * @param ctx the parse tree
	 */
	void enterKillStatement(CSqlParser.KillStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#killStatement}.
	 * @param ctx the parse tree
	 */
	void exitKillStatement(CSqlParser.KillStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#loadIndexIntoCache}.
	 * @param ctx the parse tree
	 */
	void enterLoadIndexIntoCache(CSqlParser.LoadIndexIntoCacheContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#loadIndexIntoCache}.
	 * @param ctx the parse tree
	 */
	void exitLoadIndexIntoCache(CSqlParser.LoadIndexIntoCacheContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#resetStatement}.
	 * @param ctx the parse tree
	 */
	void enterResetStatement(CSqlParser.ResetStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#resetStatement}.
	 * @param ctx the parse tree
	 */
	void exitResetStatement(CSqlParser.ResetStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#shutdownStatement}.
	 * @param ctx the parse tree
	 */
	void enterShutdownStatement(CSqlParser.ShutdownStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#shutdownStatement}.
	 * @param ctx the parse tree
	 */
	void exitShutdownStatement(CSqlParser.ShutdownStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#tableIndexes}.
	 * @param ctx the parse tree
	 */
	void enterTableIndexes(CSqlParser.TableIndexesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#tableIndexes}.
	 * @param ctx the parse tree
	 */
	void exitTableIndexes(CSqlParser.TableIndexesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleFlushOption}
	 * labeled alternative in {@link CSqlParser#flushOption}.
	 * @param ctx the parse tree
	 */
	void enterSimpleFlushOption(CSqlParser.SimpleFlushOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleFlushOption}
	 * labeled alternative in {@link CSqlParser#flushOption}.
	 * @param ctx the parse tree
	 */
	void exitSimpleFlushOption(CSqlParser.SimpleFlushOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code channelFlushOption}
	 * labeled alternative in {@link CSqlParser#flushOption}.
	 * @param ctx the parse tree
	 */
	void enterChannelFlushOption(CSqlParser.ChannelFlushOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code channelFlushOption}
	 * labeled alternative in {@link CSqlParser#flushOption}.
	 * @param ctx the parse tree
	 */
	void exitChannelFlushOption(CSqlParser.ChannelFlushOptionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tableFlushOption}
	 * labeled alternative in {@link CSqlParser#flushOption}.
	 * @param ctx the parse tree
	 */
	void enterTableFlushOption(CSqlParser.TableFlushOptionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tableFlushOption}
	 * labeled alternative in {@link CSqlParser#flushOption}.
	 * @param ctx the parse tree
	 */
	void exitTableFlushOption(CSqlParser.TableFlushOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#flushTableOption}.
	 * @param ctx the parse tree
	 */
	void enterFlushTableOption(CSqlParser.FlushTableOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#flushTableOption}.
	 * @param ctx the parse tree
	 */
	void exitFlushTableOption(CSqlParser.FlushTableOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#loadedTableIndexes}.
	 * @param ctx the parse tree
	 */
	void enterLoadedTableIndexes(CSqlParser.LoadedTableIndexesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#loadedTableIndexes}.
	 * @param ctx the parse tree
	 */
	void exitLoadedTableIndexes(CSqlParser.LoadedTableIndexesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#simpleDescribeStatement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleDescribeStatement(CSqlParser.SimpleDescribeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#simpleDescribeStatement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleDescribeStatement(CSqlParser.SimpleDescribeStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#fullDescribeStatement}.
	 * @param ctx the parse tree
	 */
	void enterFullDescribeStatement(CSqlParser.FullDescribeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#fullDescribeStatement}.
	 * @param ctx the parse tree
	 */
	void exitFullDescribeStatement(CSqlParser.FullDescribeStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#helpStatement}.
	 * @param ctx the parse tree
	 */
	void enterHelpStatement(CSqlParser.HelpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#helpStatement}.
	 * @param ctx the parse tree
	 */
	void exitHelpStatement(CSqlParser.HelpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#useStatement}.
	 * @param ctx the parse tree
	 */
	void enterUseStatement(CSqlParser.UseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#useStatement}.
	 * @param ctx the parse tree
	 */
	void exitUseStatement(CSqlParser.UseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#signalStatement}.
	 * @param ctx the parse tree
	 */
	void enterSignalStatement(CSqlParser.SignalStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#signalStatement}.
	 * @param ctx the parse tree
	 */
	void exitSignalStatement(CSqlParser.SignalStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#resignalStatement}.
	 * @param ctx the parse tree
	 */
	void enterResignalStatement(CSqlParser.ResignalStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#resignalStatement}.
	 * @param ctx the parse tree
	 */
	void exitResignalStatement(CSqlParser.ResignalStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#signalConditionInformation}.
	 * @param ctx the parse tree
	 */
	void enterSignalConditionInformation(CSqlParser.SignalConditionInformationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#signalConditionInformation}.
	 * @param ctx the parse tree
	 */
	void exitSignalConditionInformation(CSqlParser.SignalConditionInformationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#diagnosticsStatement}.
	 * @param ctx the parse tree
	 */
	void enterDiagnosticsStatement(CSqlParser.DiagnosticsStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#diagnosticsStatement}.
	 * @param ctx the parse tree
	 */
	void exitDiagnosticsStatement(CSqlParser.DiagnosticsStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#diagnosticsConditionInformationName}.
	 * @param ctx the parse tree
	 */
	void enterDiagnosticsConditionInformationName(CSqlParser.DiagnosticsConditionInformationNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#diagnosticsConditionInformationName}.
	 * @param ctx the parse tree
	 */
	void exitDiagnosticsConditionInformationName(CSqlParser.DiagnosticsConditionInformationNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code describeStatements}
	 * labeled alternative in {@link CSqlParser#describeObjectClause}.
	 * @param ctx the parse tree
	 */
	void enterDescribeStatements(CSqlParser.DescribeStatementsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code describeStatements}
	 * labeled alternative in {@link CSqlParser#describeObjectClause}.
	 * @param ctx the parse tree
	 */
	void exitDescribeStatements(CSqlParser.DescribeStatementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code describeConnection}
	 * labeled alternative in {@link CSqlParser#describeObjectClause}.
	 * @param ctx the parse tree
	 */
	void enterDescribeConnection(CSqlParser.DescribeConnectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code describeConnection}
	 * labeled alternative in {@link CSqlParser#describeObjectClause}.
	 * @param ctx the parse tree
	 */
	void exitDescribeConnection(CSqlParser.DescribeConnectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#fullId}.
	 * @param ctx the parse tree
	 */
	void enterFullId(CSqlParser.FullIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#fullId}.
	 * @param ctx the parse tree
	 */
	void exitFullId(CSqlParser.FullIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(CSqlParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(CSqlParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#fullColumnName}.
	 * @param ctx the parse tree
	 */
	void enterFullColumnName(CSqlParser.FullColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#fullColumnName}.
	 * @param ctx the parse tree
	 */
	void exitFullColumnName(CSqlParser.FullColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#indexColumnName}.
	 * @param ctx the parse tree
	 */
	void enterIndexColumnName(CSqlParser.IndexColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#indexColumnName}.
	 * @param ctx the parse tree
	 */
	void exitIndexColumnName(CSqlParser.IndexColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#userName}.
	 * @param ctx the parse tree
	 */
	void enterUserName(CSqlParser.UserNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#userName}.
	 * @param ctx the parse tree
	 */
	void exitUserName(CSqlParser.UserNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#mysqlVariable}.
	 * @param ctx the parse tree
	 */
	void enterMysqlVariable(CSqlParser.MysqlVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#mysqlVariable}.
	 * @param ctx the parse tree
	 */
	void exitMysqlVariable(CSqlParser.MysqlVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#charsetName}.
	 * @param ctx the parse tree
	 */
	void enterCharsetName(CSqlParser.CharsetNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#charsetName}.
	 * @param ctx the parse tree
	 */
	void exitCharsetName(CSqlParser.CharsetNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#collationName}.
	 * @param ctx the parse tree
	 */
	void enterCollationName(CSqlParser.CollationNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#collationName}.
	 * @param ctx the parse tree
	 */
	void exitCollationName(CSqlParser.CollationNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#engineName}.
	 * @param ctx the parse tree
	 */
	void enterEngineName(CSqlParser.EngineNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#engineName}.
	 * @param ctx the parse tree
	 */
	void exitEngineName(CSqlParser.EngineNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#uuidSet}.
	 * @param ctx the parse tree
	 */
	void enterUuidSet(CSqlParser.UuidSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#uuidSet}.
	 * @param ctx the parse tree
	 */
	void exitUuidSet(CSqlParser.UuidSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#xid}.
	 * @param ctx the parse tree
	 */
	void enterXid(CSqlParser.XidContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#xid}.
	 * @param ctx the parse tree
	 */
	void exitXid(CSqlParser.XidContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#xuidStringId}.
	 * @param ctx the parse tree
	 */
	void enterXuidStringId(CSqlParser.XuidStringIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#xuidStringId}.
	 * @param ctx the parse tree
	 */
	void exitXuidStringId(CSqlParser.XuidStringIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#authPlugin}.
	 * @param ctx the parse tree
	 */
	void enterAuthPlugin(CSqlParser.AuthPluginContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#authPlugin}.
	 * @param ctx the parse tree
	 */
	void exitAuthPlugin(CSqlParser.AuthPluginContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#uid}.
	 * @param ctx the parse tree
	 */
	void enterUid(CSqlParser.UidContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#uid}.
	 * @param ctx the parse tree
	 */
	void exitUid(CSqlParser.UidContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#simpleId}.
	 * @param ctx the parse tree
	 */
	void enterSimpleId(CSqlParser.SimpleIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#simpleId}.
	 * @param ctx the parse tree
	 */
	void exitSimpleId(CSqlParser.SimpleIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dottedId}.
	 * @param ctx the parse tree
	 */
	void enterDottedId(CSqlParser.DottedIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dottedId}.
	 * @param ctx the parse tree
	 */
	void exitDottedId(CSqlParser.DottedIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#decimalLiteral}.
	 * @param ctx the parse tree
	 */
	void enterDecimalLiteral(CSqlParser.DecimalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#decimalLiteral}.
	 * @param ctx the parse tree
	 */
	void exitDecimalLiteral(CSqlParser.DecimalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#fileSizeLiteral}.
	 * @param ctx the parse tree
	 */
	void enterFileSizeLiteral(CSqlParser.FileSizeLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#fileSizeLiteral}.
	 * @param ctx the parse tree
	 */
	void exitFileSizeLiteral(CSqlParser.FileSizeLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(CSqlParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(CSqlParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(CSqlParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(CSqlParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#hexadecimalLiteral}.
	 * @param ctx the parse tree
	 */
	void enterHexadecimalLiteral(CSqlParser.HexadecimalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#hexadecimalLiteral}.
	 * @param ctx the parse tree
	 */
	void exitHexadecimalLiteral(CSqlParser.HexadecimalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#nullNotnull}.
	 * @param ctx the parse tree
	 */
	void enterNullNotnull(CSqlParser.NullNotnullContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#nullNotnull}.
	 * @param ctx the parse tree
	 */
	void exitNullNotnull(CSqlParser.NullNotnullContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(CSqlParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(CSqlParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterStringDataType(CSqlParser.StringDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitStringDataType(CSqlParser.StringDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nationalStringDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterNationalStringDataType(CSqlParser.NationalStringDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nationalStringDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitNationalStringDataType(CSqlParser.NationalStringDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nationalVaryingStringDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterNationalVaryingStringDataType(CSqlParser.NationalVaryingStringDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nationalVaryingStringDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitNationalVaryingStringDataType(CSqlParser.NationalVaryingStringDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dimensionDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDimensionDataType(CSqlParser.DimensionDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dimensionDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDimensionDataType(CSqlParser.DimensionDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterSimpleDataType(CSqlParser.SimpleDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitSimpleDataType(CSqlParser.SimpleDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code collectionDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterCollectionDataType(CSqlParser.CollectionDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code collectionDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitCollectionDataType(CSqlParser.CollectionDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code spatialDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterSpatialDataType(CSqlParser.SpatialDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code spatialDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitSpatialDataType(CSqlParser.SpatialDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code longVarcharDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterLongVarcharDataType(CSqlParser.LongVarcharDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code longVarcharDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitLongVarcharDataType(CSqlParser.LongVarcharDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code longVarbinaryDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterLongVarbinaryDataType(CSqlParser.LongVarbinaryDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code longVarbinaryDataType}
	 * labeled alternative in {@link CSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitLongVarbinaryDataType(CSqlParser.LongVarbinaryDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#collectionOptions}.
	 * @param ctx the parse tree
	 */
	void enterCollectionOptions(CSqlParser.CollectionOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#collectionOptions}.
	 * @param ctx the parse tree
	 */
	void exitCollectionOptions(CSqlParser.CollectionOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#convertedDataType}.
	 * @param ctx the parse tree
	 */
	void enterConvertedDataType(CSqlParser.ConvertedDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#convertedDataType}.
	 * @param ctx the parse tree
	 */
	void exitConvertedDataType(CSqlParser.ConvertedDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#lengthOneDimension}.
	 * @param ctx the parse tree
	 */
	void enterLengthOneDimension(CSqlParser.LengthOneDimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#lengthOneDimension}.
	 * @param ctx the parse tree
	 */
	void exitLengthOneDimension(CSqlParser.LengthOneDimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#lengthTwoDimension}.
	 * @param ctx the parse tree
	 */
	void enterLengthTwoDimension(CSqlParser.LengthTwoDimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#lengthTwoDimension}.
	 * @param ctx the parse tree
	 */
	void exitLengthTwoDimension(CSqlParser.LengthTwoDimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#lengthTwoOptionalDimension}.
	 * @param ctx the parse tree
	 */
	void enterLengthTwoOptionalDimension(CSqlParser.LengthTwoOptionalDimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#lengthTwoOptionalDimension}.
	 * @param ctx the parse tree
	 */
	void exitLengthTwoOptionalDimension(CSqlParser.LengthTwoOptionalDimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#uidList}.
	 * @param ctx the parse tree
	 */
	void enterUidList(CSqlParser.UidListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#uidList}.
	 * @param ctx the parse tree
	 */
	void exitUidList(CSqlParser.UidListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#tables}.
	 * @param ctx the parse tree
	 */
	void enterTables(CSqlParser.TablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#tables}.
	 * @param ctx the parse tree
	 */
	void exitTables(CSqlParser.TablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#indexColumnNames}.
	 * @param ctx the parse tree
	 */
	void enterIndexColumnNames(CSqlParser.IndexColumnNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#indexColumnNames}.
	 * @param ctx the parse tree
	 */
	void exitIndexColumnNames(CSqlParser.IndexColumnNamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#expressions}.
	 * @param ctx the parse tree
	 */
	void enterExpressions(CSqlParser.ExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#expressions}.
	 * @param ctx the parse tree
	 */
	void exitExpressions(CSqlParser.ExpressionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#expressionsWithDefaults}.
	 * @param ctx the parse tree
	 */
	void enterExpressionsWithDefaults(CSqlParser.ExpressionsWithDefaultsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#expressionsWithDefaults}.
	 * @param ctx the parse tree
	 */
	void exitExpressionsWithDefaults(CSqlParser.ExpressionsWithDefaultsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#constants}.
	 * @param ctx the parse tree
	 */
	void enterConstants(CSqlParser.ConstantsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#constants}.
	 * @param ctx the parse tree
	 */
	void exitConstants(CSqlParser.ConstantsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#simpleStrings}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStrings(CSqlParser.SimpleStringsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#simpleStrings}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStrings(CSqlParser.SimpleStringsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#userVariables}.
	 * @param ctx the parse tree
	 */
	void enterUserVariables(CSqlParser.UserVariablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#userVariables}.
	 * @param ctx the parse tree
	 */
	void exitUserVariables(CSqlParser.UserVariablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void enterDefaultValue(CSqlParser.DefaultValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#defaultValue}.
	 * @param ctx the parse tree
	 */
	void exitDefaultValue(CSqlParser.DefaultValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#currentTimestamp}.
	 * @param ctx the parse tree
	 */
	void enterCurrentTimestamp(CSqlParser.CurrentTimestampContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#currentTimestamp}.
	 * @param ctx the parse tree
	 */
	void exitCurrentTimestamp(CSqlParser.CurrentTimestampContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#expressionOrDefault}.
	 * @param ctx the parse tree
	 */
	void enterExpressionOrDefault(CSqlParser.ExpressionOrDefaultContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#expressionOrDefault}.
	 * @param ctx the parse tree
	 */
	void exitExpressionOrDefault(CSqlParser.ExpressionOrDefaultContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#ifExists}.
	 * @param ctx the parse tree
	 */
	void enterIfExists(CSqlParser.IfExistsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#ifExists}.
	 * @param ctx the parse tree
	 */
	void exitIfExists(CSqlParser.IfExistsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#ifNotExists}.
	 * @param ctx the parse tree
	 */
	void enterIfNotExists(CSqlParser.IfNotExistsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#ifNotExists}.
	 * @param ctx the parse tree
	 */
	void exitIfNotExists(CSqlParser.IfNotExistsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code specificFunctionCall}
	 * labeled alternative in {@link CSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterSpecificFunctionCall(CSqlParser.SpecificFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code specificFunctionCall}
	 * labeled alternative in {@link CSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitSpecificFunctionCall(CSqlParser.SpecificFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code aggregateFunctionCall}
	 * labeled alternative in {@link CSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterAggregateFunctionCall(CSqlParser.AggregateFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code aggregateFunctionCall}
	 * labeled alternative in {@link CSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitAggregateFunctionCall(CSqlParser.AggregateFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nonAggregateFunctionCall}
	 * labeled alternative in {@link CSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterNonAggregateFunctionCall(CSqlParser.NonAggregateFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nonAggregateFunctionCall}
	 * labeled alternative in {@link CSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitNonAggregateFunctionCall(CSqlParser.NonAggregateFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code scalarFunctionCall}
	 * labeled alternative in {@link CSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterScalarFunctionCall(CSqlParser.ScalarFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code scalarFunctionCall}
	 * labeled alternative in {@link CSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitScalarFunctionCall(CSqlParser.ScalarFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code udfFunctionCall}
	 * labeled alternative in {@link CSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterUdfFunctionCall(CSqlParser.UdfFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code udfFunctionCall}
	 * labeled alternative in {@link CSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitUdfFunctionCall(CSqlParser.UdfFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code passwordFunctionCall}
	 * labeled alternative in {@link CSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterPasswordFunctionCall(CSqlParser.PasswordFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code passwordFunctionCall}
	 * labeled alternative in {@link CSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitPasswordFunctionCall(CSqlParser.PasswordFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterSimpleFunctionCall(CSqlParser.SimpleFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitSimpleFunctionCall(CSqlParser.SimpleFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dataTypeFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterDataTypeFunctionCall(CSqlParser.DataTypeFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dataTypeFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitDataTypeFunctionCall(CSqlParser.DataTypeFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valuesFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterValuesFunctionCall(CSqlParser.ValuesFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valuesFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitValuesFunctionCall(CSqlParser.ValuesFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code caseExpressionFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpressionFunctionCall(CSqlParser.CaseExpressionFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code caseExpressionFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpressionFunctionCall(CSqlParser.CaseExpressionFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code caseFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterCaseFunctionCall(CSqlParser.CaseFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code caseFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitCaseFunctionCall(CSqlParser.CaseFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterCharFunctionCall(CSqlParser.CharFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitCharFunctionCall(CSqlParser.CharFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code positionFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterPositionFunctionCall(CSqlParser.PositionFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code positionFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitPositionFunctionCall(CSqlParser.PositionFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code substrFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterSubstrFunctionCall(CSqlParser.SubstrFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code substrFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitSubstrFunctionCall(CSqlParser.SubstrFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trimFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterTrimFunctionCall(CSqlParser.TrimFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trimFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitTrimFunctionCall(CSqlParser.TrimFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code weightFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterWeightFunctionCall(CSqlParser.WeightFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code weightFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitWeightFunctionCall(CSqlParser.WeightFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code extractFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterExtractFunctionCall(CSqlParser.ExtractFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code extractFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitExtractFunctionCall(CSqlParser.ExtractFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getFormatFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterGetFormatFunctionCall(CSqlParser.GetFormatFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getFormatFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitGetFormatFunctionCall(CSqlParser.GetFormatFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jsonValueFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void enterJsonValueFunctionCall(CSqlParser.JsonValueFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jsonValueFunctionCall}
	 * labeled alternative in {@link CSqlParser#specificFunction}.
	 * @param ctx the parse tree
	 */
	void exitJsonValueFunctionCall(CSqlParser.JsonValueFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#caseFuncAlternative}.
	 * @param ctx the parse tree
	 */
	void enterCaseFuncAlternative(CSqlParser.CaseFuncAlternativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#caseFuncAlternative}.
	 * @param ctx the parse tree
	 */
	void exitCaseFuncAlternative(CSqlParser.CaseFuncAlternativeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code levelWeightList}
	 * labeled alternative in {@link CSqlParser#levelsInWeightString}.
	 * @param ctx the parse tree
	 */
	void enterLevelWeightList(CSqlParser.LevelWeightListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code levelWeightList}
	 * labeled alternative in {@link CSqlParser#levelsInWeightString}.
	 * @param ctx the parse tree
	 */
	void exitLevelWeightList(CSqlParser.LevelWeightListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code levelWeightRange}
	 * labeled alternative in {@link CSqlParser#levelsInWeightString}.
	 * @param ctx the parse tree
	 */
	void enterLevelWeightRange(CSqlParser.LevelWeightRangeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code levelWeightRange}
	 * labeled alternative in {@link CSqlParser#levelsInWeightString}.
	 * @param ctx the parse tree
	 */
	void exitLevelWeightRange(CSqlParser.LevelWeightRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#levelInWeightListElement}.
	 * @param ctx the parse tree
	 */
	void enterLevelInWeightListElement(CSqlParser.LevelInWeightListElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#levelInWeightListElement}.
	 * @param ctx the parse tree
	 */
	void exitLevelInWeightListElement(CSqlParser.LevelInWeightListElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#aggregateWindowedFunction}.
	 * @param ctx the parse tree
	 */
	void enterAggregateWindowedFunction(CSqlParser.AggregateWindowedFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#aggregateWindowedFunction}.
	 * @param ctx the parse tree
	 */
	void exitAggregateWindowedFunction(CSqlParser.AggregateWindowedFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#nonAggregateWindowedFunction}.
	 * @param ctx the parse tree
	 */
	void enterNonAggregateWindowedFunction(CSqlParser.NonAggregateWindowedFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#nonAggregateWindowedFunction}.
	 * @param ctx the parse tree
	 */
	void exitNonAggregateWindowedFunction(CSqlParser.NonAggregateWindowedFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#overClause}.
	 * @param ctx the parse tree
	 */
	void enterOverClause(CSqlParser.OverClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#overClause}.
	 * @param ctx the parse tree
	 */
	void exitOverClause(CSqlParser.OverClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#windowSpec}.
	 * @param ctx the parse tree
	 */
	void enterWindowSpec(CSqlParser.WindowSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#windowSpec}.
	 * @param ctx the parse tree
	 */
	void exitWindowSpec(CSqlParser.WindowSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#windowName}.
	 * @param ctx the parse tree
	 */
	void enterWindowName(CSqlParser.WindowNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#windowName}.
	 * @param ctx the parse tree
	 */
	void exitWindowName(CSqlParser.WindowNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#frameClause}.
	 * @param ctx the parse tree
	 */
	void enterFrameClause(CSqlParser.FrameClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#frameClause}.
	 * @param ctx the parse tree
	 */
	void exitFrameClause(CSqlParser.FrameClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#frameUnits}.
	 * @param ctx the parse tree
	 */
	void enterFrameUnits(CSqlParser.FrameUnitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#frameUnits}.
	 * @param ctx the parse tree
	 */
	void exitFrameUnits(CSqlParser.FrameUnitsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#frameExtent}.
	 * @param ctx the parse tree
	 */
	void enterFrameExtent(CSqlParser.FrameExtentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#frameExtent}.
	 * @param ctx the parse tree
	 */
	void exitFrameExtent(CSqlParser.FrameExtentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#frameBetween}.
	 * @param ctx the parse tree
	 */
	void enterFrameBetween(CSqlParser.FrameBetweenContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#frameBetween}.
	 * @param ctx the parse tree
	 */
	void exitFrameBetween(CSqlParser.FrameBetweenContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#frameRange}.
	 * @param ctx the parse tree
	 */
	void enterFrameRange(CSqlParser.FrameRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#frameRange}.
	 * @param ctx the parse tree
	 */
	void exitFrameRange(CSqlParser.FrameRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#partitionClause}.
	 * @param ctx the parse tree
	 */
	void enterPartitionClause(CSqlParser.PartitionClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#partitionClause}.
	 * @param ctx the parse tree
	 */
	void exitPartitionClause(CSqlParser.PartitionClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#scalarFunctionName}.
	 * @param ctx the parse tree
	 */
	void enterScalarFunctionName(CSqlParser.ScalarFunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#scalarFunctionName}.
	 * @param ctx the parse tree
	 */
	void exitScalarFunctionName(CSqlParser.ScalarFunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#passwordFunctionClause}.
	 * @param ctx the parse tree
	 */
	void enterPasswordFunctionClause(CSqlParser.PasswordFunctionClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#passwordFunctionClause}.
	 * @param ctx the parse tree
	 */
	void exitPasswordFunctionClause(CSqlParser.PasswordFunctionClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#functionArgs}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArgs(CSqlParser.FunctionArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#functionArgs}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArgs(CSqlParser.FunctionArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#functionArg}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArg(CSqlParser.FunctionArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#functionArg}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArg(CSqlParser.FunctionArgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isExpression}
	 * labeled alternative in {@link CSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIsExpression(CSqlParser.IsExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isExpression}
	 * labeled alternative in {@link CSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIsExpression(CSqlParser.IsExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link CSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(CSqlParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link CSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(CSqlParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link CSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpression(CSqlParser.LogicalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link CSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpression(CSqlParser.LogicalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code predicateExpression}
	 * labeled alternative in {@link CSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPredicateExpression(CSqlParser.PredicateExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code predicateExpression}
	 * labeled alternative in {@link CSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPredicateExpression(CSqlParser.PredicateExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code soundsLikePredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterSoundsLikePredicate(CSqlParser.SoundsLikePredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code soundsLikePredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitSoundsLikePredicate(CSqlParser.SoundsLikePredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionAtomPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterExpressionAtomPredicate(CSqlParser.ExpressionAtomPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionAtomPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitExpressionAtomPredicate(CSqlParser.ExpressionAtomPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subqueryComparisonPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterSubqueryComparisonPredicate(CSqlParser.SubqueryComparisonPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subqueryComparisonPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitSubqueryComparisonPredicate(CSqlParser.SubqueryComparisonPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jsonMemberOfPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterJsonMemberOfPredicate(CSqlParser.JsonMemberOfPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jsonMemberOfPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitJsonMemberOfPredicate(CSqlParser.JsonMemberOfPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryComparisonPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterBinaryComparisonPredicate(CSqlParser.BinaryComparisonPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryComparisonPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitBinaryComparisonPredicate(CSqlParser.BinaryComparisonPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterInPredicate(CSqlParser.InPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitInPredicate(CSqlParser.InPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code betweenPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterBetweenPredicate(CSqlParser.BetweenPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code betweenPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitBetweenPredicate(CSqlParser.BetweenPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isNullPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterIsNullPredicate(CSqlParser.IsNullPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isNullPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitIsNullPredicate(CSqlParser.IsNullPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code likePredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterLikePredicate(CSqlParser.LikePredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code likePredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitLikePredicate(CSqlParser.LikePredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code regexpPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterRegexpPredicate(CSqlParser.RegexpPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code regexpPredicate}
	 * labeled alternative in {@link CSqlParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitRegexpPredicate(CSqlParser.RegexpPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpressionAtom(CSqlParser.UnaryExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpressionAtom(CSqlParser.UnaryExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code collateExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterCollateExpressionAtom(CSqlParser.CollateExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code collateExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitCollateExpressionAtom(CSqlParser.CollateExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mysqlVariableExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterMysqlVariableExpressionAtom(CSqlParser.MysqlVariableExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mysqlVariableExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitMysqlVariableExpressionAtom(CSqlParser.MysqlVariableExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nestedExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterNestedExpressionAtom(CSqlParser.NestedExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nestedExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitNestedExpressionAtom(CSqlParser.NestedExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nestedRowExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterNestedRowExpressionAtom(CSqlParser.NestedRowExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nestedRowExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitNestedRowExpressionAtom(CSqlParser.NestedRowExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mathExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterMathExpressionAtom(CSqlParser.MathExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mathExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitMathExpressionAtom(CSqlParser.MathExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code existsExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterExistsExpressionAtom(CSqlParser.ExistsExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code existsExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitExistsExpressionAtom(CSqlParser.ExistsExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intervalExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterIntervalExpressionAtom(CSqlParser.IntervalExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intervalExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitIntervalExpressionAtom(CSqlParser.IntervalExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jsonExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterJsonExpressionAtom(CSqlParser.JsonExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jsonExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitJsonExpressionAtom(CSqlParser.JsonExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subqueryExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterSubqueryExpressionAtom(CSqlParser.SubqueryExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subqueryExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitSubqueryExpressionAtom(CSqlParser.SubqueryExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constantExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpressionAtom(CSqlParser.ConstantExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constantExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpressionAtom(CSqlParser.ConstantExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpressionAtom(CSqlParser.FunctionCallExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpressionAtom(CSqlParser.FunctionCallExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpressionAtom(CSqlParser.BinaryExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpressionAtom(CSqlParser.BinaryExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fullColumnNameExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterFullColumnNameExpressionAtom(CSqlParser.FullColumnNameExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fullColumnNameExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitFullColumnNameExpressionAtom(CSqlParser.FullColumnNameExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void enterBitExpressionAtom(CSqlParser.BitExpressionAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitExpressionAtom}
	 * labeled alternative in {@link CSqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 */
	void exitBitExpressionAtom(CSqlParser.BitExpressionAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperator(CSqlParser.UnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperator(CSqlParser.UnaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(CSqlParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(CSqlParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperator(CSqlParser.LogicalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperator(CSqlParser.LogicalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#bitOperator}.
	 * @param ctx the parse tree
	 */
	void enterBitOperator(CSqlParser.BitOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#bitOperator}.
	 * @param ctx the parse tree
	 */
	void exitBitOperator(CSqlParser.BitOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#mathOperator}.
	 * @param ctx the parse tree
	 */
	void enterMathOperator(CSqlParser.MathOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#mathOperator}.
	 * @param ctx the parse tree
	 */
	void exitMathOperator(CSqlParser.MathOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#jsonOperator}.
	 * @param ctx the parse tree
	 */
	void enterJsonOperator(CSqlParser.JsonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#jsonOperator}.
	 * @param ctx the parse tree
	 */
	void exitJsonOperator(CSqlParser.JsonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#charsetNameBase}.
	 * @param ctx the parse tree
	 */
	void enterCharsetNameBase(CSqlParser.CharsetNameBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#charsetNameBase}.
	 * @param ctx the parse tree
	 */
	void exitCharsetNameBase(CSqlParser.CharsetNameBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#transactionLevelBase}.
	 * @param ctx the parse tree
	 */
	void enterTransactionLevelBase(CSqlParser.TransactionLevelBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#transactionLevelBase}.
	 * @param ctx the parse tree
	 */
	void exitTransactionLevelBase(CSqlParser.TransactionLevelBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#privilegesBase}.
	 * @param ctx the parse tree
	 */
	void enterPrivilegesBase(CSqlParser.PrivilegesBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#privilegesBase}.
	 * @param ctx the parse tree
	 */
	void exitPrivilegesBase(CSqlParser.PrivilegesBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#intervalTypeBase}.
	 * @param ctx the parse tree
	 */
	void enterIntervalTypeBase(CSqlParser.IntervalTypeBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#intervalTypeBase}.
	 * @param ctx the parse tree
	 */
	void exitIntervalTypeBase(CSqlParser.IntervalTypeBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#dataTypeBase}.
	 * @param ctx the parse tree
	 */
	void enterDataTypeBase(CSqlParser.DataTypeBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#dataTypeBase}.
	 * @param ctx the parse tree
	 */
	void exitDataTypeBase(CSqlParser.DataTypeBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#keywordsCanBeId}.
	 * @param ctx the parse tree
	 */
	void enterKeywordsCanBeId(CSqlParser.KeywordsCanBeIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#keywordsCanBeId}.
	 * @param ctx the parse tree
	 */
	void exitKeywordsCanBeId(CSqlParser.KeywordsCanBeIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link CSqlParser#functionNameBase}.
	 * @param ctx the parse tree
	 */
	void enterFunctionNameBase(CSqlParser.FunctionNameBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CSqlParser#functionNameBase}.
	 * @param ctx the parse tree
	 */
	void exitFunctionNameBase(CSqlParser.FunctionNameBaseContext ctx);
}