// Generated from /Users/guhan/workspace/opensource/cave/cave-components/cave-sql/antlr4/CqlParser.g4 by ANTLR 4.10.1
package fun.langel.cql.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CqlParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CqlParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(CqlParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#sqlStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatements(CqlParser.SqlStatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatement(CqlParser.SqlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#emptyStatement_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement_(CqlParser.EmptyStatement_Context ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#ddlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDdlStatement(CqlParser.DdlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dmlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDmlStatement(CqlParser.DmlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#transactionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransactionStatement(CqlParser.TransactionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#replicationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReplicationStatement(CqlParser.ReplicationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#preparedStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreparedStatement(CqlParser.PreparedStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(CqlParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#administrationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdministrationStatement(CqlParser.AdministrationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#utilityStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUtilityStatement(CqlParser.UtilityStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateDatabase(CqlParser.CreateDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createEvent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateEvent(CqlParser.CreateEventContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateIndex(CqlParser.CreateIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createLogfileGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateLogfileGroup(CqlParser.CreateLogfileGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateProcedure(CqlParser.CreateProcedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateFunction(CqlParser.CreateFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createRole}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateRole(CqlParser.CreateRoleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createServer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateServer(CqlParser.CreateServerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code copyCreateTable}
	 * labeled alternative in {@link CqlParser#createTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCopyCreateTable(CqlParser.CopyCreateTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code queryCreateTable}
	 * labeled alternative in {@link CqlParser#createTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryCreateTable(CqlParser.QueryCreateTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnCreateTable}
	 * labeled alternative in {@link CqlParser#createTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnCreateTable(CqlParser.ColumnCreateTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createTablespaceInnodb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTablespaceInnodb(CqlParser.CreateTablespaceInnodbContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createTablespaceNdb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTablespaceNdb(CqlParser.CreateTablespaceNdbContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createTrigger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTrigger(CqlParser.CreateTriggerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#withClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithClause(CqlParser.WithClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#commonTableExpressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommonTableExpressions(CqlParser.CommonTableExpressionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#cteName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCteName(CqlParser.CteNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#cteColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCteColumnName(CqlParser.CteColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createView}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateView(CqlParser.CreateViewContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createDatabaseOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateDatabaseOption(CqlParser.CreateDatabaseOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#charSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharSet(CqlParser.CharSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#ownerStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOwnerStatement(CqlParser.OwnerStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code preciseSchedule}
	 * labeled alternative in {@link CqlParser#scheduleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreciseSchedule(CqlParser.PreciseScheduleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intervalSchedule}
	 * labeled alternative in {@link CqlParser#scheduleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalSchedule(CqlParser.IntervalScheduleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#timestampValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimestampValue(CqlParser.TimestampValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#intervalExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalExpr(CqlParser.IntervalExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#intervalType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalType(CqlParser.IntervalTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#enableType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnableType(CqlParser.EnableTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#indexType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexType(CqlParser.IndexTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#indexOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexOption(CqlParser.IndexOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#procedureParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureParameter(CqlParser.ProcedureParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#functionParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionParameter(CqlParser.FunctionParameterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routineComment}
	 * labeled alternative in {@link CqlParser#routineOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutineComment(CqlParser.RoutineCommentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routineLanguage}
	 * labeled alternative in {@link CqlParser#routineOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutineLanguage(CqlParser.RoutineLanguageContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routineBehavior}
	 * labeled alternative in {@link CqlParser#routineOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutineBehavior(CqlParser.RoutineBehaviorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routineData}
	 * labeled alternative in {@link CqlParser#routineOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutineData(CqlParser.RoutineDataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code routineSecurity}
	 * labeled alternative in {@link CqlParser#routineOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutineSecurity(CqlParser.RoutineSecurityContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#serverOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitServerOption(CqlParser.ServerOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createDefinitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateDefinitions(CqlParser.CreateDefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnDeclaration}
	 * labeled alternative in {@link CqlParser#createDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDeclaration(CqlParser.ColumnDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constraintDeclaration}
	 * labeled alternative in {@link CqlParser#createDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraintDeclaration(CqlParser.ConstraintDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indexDeclaration}
	 * labeled alternative in {@link CqlParser#createDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexDeclaration(CqlParser.IndexDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDefinition(CqlParser.ColumnDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullColumnConstraint(CqlParser.NullColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defaultColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultColumnConstraint(CqlParser.DefaultColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code visibilityColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVisibilityColumnConstraint(CqlParser.VisibilityColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code autoIncrementColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAutoIncrementColumnConstraint(CqlParser.AutoIncrementColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryKeyColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryKeyColumnConstraint(CqlParser.PrimaryKeyColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code uniqueKeyColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniqueKeyColumnConstraint(CqlParser.UniqueKeyColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code commentColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommentColumnConstraint(CqlParser.CommentColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code formatColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormatColumnConstraint(CqlParser.FormatColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code storageColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStorageColumnConstraint(CqlParser.StorageColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code referenceColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceColumnConstraint(CqlParser.ReferenceColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code collateColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollateColumnConstraint(CqlParser.CollateColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code generatedColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGeneratedColumnConstraint(CqlParser.GeneratedColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code serialDefaultColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSerialDefaultColumnConstraint(CqlParser.SerialDefaultColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code checkColumnConstraint}
	 * labeled alternative in {@link CqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckColumnConstraint(CqlParser.CheckColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primaryKeyTableConstraint}
	 * labeled alternative in {@link CqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryKeyTableConstraint(CqlParser.PrimaryKeyTableConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code uniqueKeyTableConstraint}
	 * labeled alternative in {@link CqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniqueKeyTableConstraint(CqlParser.UniqueKeyTableConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code foreignKeyTableConstraint}
	 * labeled alternative in {@link CqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeignKeyTableConstraint(CqlParser.ForeignKeyTableConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code checkTableConstraint}
	 * labeled alternative in {@link CqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckTableConstraint(CqlParser.CheckTableConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#referenceDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceDefinition(CqlParser.ReferenceDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#referenceAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceAction(CqlParser.ReferenceActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#referenceControlType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceControlType(CqlParser.ReferenceControlTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleIndexDeclaration}
	 * labeled alternative in {@link CqlParser#indexColumnDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleIndexDeclaration(CqlParser.SimpleIndexDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code specialIndexDeclaration}
	 * labeled alternative in {@link CqlParser#indexColumnDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecialIndexDeclaration(CqlParser.SpecialIndexDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionEngine}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionEngine(CqlParser.TableOptionEngineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionAutoIncrement}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionAutoIncrement(CqlParser.TableOptionAutoIncrementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionAverage}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionAverage(CqlParser.TableOptionAverageContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionCharset}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionCharset(CqlParser.TableOptionCharsetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionChecksum}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionChecksum(CqlParser.TableOptionChecksumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionCollate}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionCollate(CqlParser.TableOptionCollateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionComment}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionComment(CqlParser.TableOptionCommentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionCompression}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionCompression(CqlParser.TableOptionCompressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionConnection}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionConnection(CqlParser.TableOptionConnectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionDataDirectory}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionDataDirectory(CqlParser.TableOptionDataDirectoryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionDelay}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionDelay(CqlParser.TableOptionDelayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionEncryption}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionEncryption(CqlParser.TableOptionEncryptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionIndexDirectory}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionIndexDirectory(CqlParser.TableOptionIndexDirectoryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionInsertMethod}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionInsertMethod(CqlParser.TableOptionInsertMethodContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionKeyBlockSize}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionKeyBlockSize(CqlParser.TableOptionKeyBlockSizeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionMaxRows}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionMaxRows(CqlParser.TableOptionMaxRowsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionMinRows}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionMinRows(CqlParser.TableOptionMinRowsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionPackKeys}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionPackKeys(CqlParser.TableOptionPackKeysContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionPassword}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionPassword(CqlParser.TableOptionPasswordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionRowFormat}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionRowFormat(CqlParser.TableOptionRowFormatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionRecalculation}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionRecalculation(CqlParser.TableOptionRecalculationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionPersistent}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionPersistent(CqlParser.TableOptionPersistentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionSamplePage}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionSamplePage(CqlParser.TableOptionSamplePageContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionTablespace}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionTablespace(CqlParser.TableOptionTablespaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionTableType}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionTableType(CqlParser.TableOptionTableTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableOptionUnion}
	 * labeled alternative in {@link CqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptionUnion(CqlParser.TableOptionUnionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#tableType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableType(CqlParser.TableTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#tablespaceStorage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTablespaceStorage(CqlParser.TablespaceStorageContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#partitionDefinitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionDefinitions(CqlParser.PartitionDefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionFunctionHash}
	 * labeled alternative in {@link CqlParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionFunctionHash(CqlParser.PartitionFunctionHashContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionFunctionKey}
	 * labeled alternative in {@link CqlParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionFunctionKey(CqlParser.PartitionFunctionKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionFunctionRange}
	 * labeled alternative in {@link CqlParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionFunctionRange(CqlParser.PartitionFunctionRangeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionFunctionList}
	 * labeled alternative in {@link CqlParser#partitionFunctionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionFunctionList(CqlParser.PartitionFunctionListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subPartitionFunctionHash}
	 * labeled alternative in {@link CqlParser#subpartitionFunctionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubPartitionFunctionHash(CqlParser.SubPartitionFunctionHashContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subPartitionFunctionKey}
	 * labeled alternative in {@link CqlParser#subpartitionFunctionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubPartitionFunctionKey(CqlParser.SubPartitionFunctionKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionComparison}
	 * labeled alternative in {@link CqlParser#partitionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionComparison(CqlParser.PartitionComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionListAtom}
	 * labeled alternative in {@link CqlParser#partitionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionListAtom(CqlParser.PartitionListAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionListVector}
	 * labeled alternative in {@link CqlParser#partitionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionListVector(CqlParser.PartitionListVectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionSimple}
	 * labeled alternative in {@link CqlParser#partitionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionSimple(CqlParser.PartitionSimpleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#partitionDefinerAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionDefinerAtom(CqlParser.PartitionDefinerAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#partitionDefinerVector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionDefinerVector(CqlParser.PartitionDefinerVectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#subpartitionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubpartitionDefinition(CqlParser.SubpartitionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionEngine}
	 * labeled alternative in {@link CqlParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionEngine(CqlParser.PartitionOptionEngineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionComment}
	 * labeled alternative in {@link CqlParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionComment(CqlParser.PartitionOptionCommentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionDataDirectory}
	 * labeled alternative in {@link CqlParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionDataDirectory(CqlParser.PartitionOptionDataDirectoryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionIndexDirectory}
	 * labeled alternative in {@link CqlParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionIndexDirectory(CqlParser.PartitionOptionIndexDirectoryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionMaxRows}
	 * labeled alternative in {@link CqlParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionMaxRows(CqlParser.PartitionOptionMaxRowsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionMinRows}
	 * labeled alternative in {@link CqlParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionMinRows(CqlParser.PartitionOptionMinRowsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionTablespace}
	 * labeled alternative in {@link CqlParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionTablespace(CqlParser.PartitionOptionTablespaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partitionOptionNodeGroup}
	 * labeled alternative in {@link CqlParser#partitionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionOptionNodeGroup(CqlParser.PartitionOptionNodeGroupContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterSimpleDatabase}
	 * labeled alternative in {@link CqlParser#alterDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterSimpleDatabase(CqlParser.AlterSimpleDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterUpgradeName}
	 * labeled alternative in {@link CqlParser#alterDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterUpgradeName(CqlParser.AlterUpgradeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#alterEvent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterEvent(CqlParser.AlterEventContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#alterFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterFunction(CqlParser.AlterFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#alterInstance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterInstance(CqlParser.AlterInstanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#alterLogfileGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterLogfileGroup(CqlParser.AlterLogfileGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#alterProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterProcedure(CqlParser.AlterProcedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#alterServer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterServer(CqlParser.AlterServerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#alterTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterTable(CqlParser.AlterTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#alterTablespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterTablespace(CqlParser.AlterTablespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#alterView}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterView(CqlParser.AlterViewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByTableOption}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByTableOption(CqlParser.AlterByTableOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddColumn}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddColumn(CqlParser.AlterByAddColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddColumns}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddColumns(CqlParser.AlterByAddColumnsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddIndex}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddIndex(CqlParser.AlterByAddIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddPrimaryKey}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddPrimaryKey(CqlParser.AlterByAddPrimaryKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddUniqueKey}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddUniqueKey(CqlParser.AlterByAddUniqueKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddSpecialIndex}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddSpecialIndex(CqlParser.AlterByAddSpecialIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddForeignKey}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddForeignKey(CqlParser.AlterByAddForeignKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddCheckTableConstraint}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddCheckTableConstraint(CqlParser.AlterByAddCheckTableConstraintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterBySetAlgorithm}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterBySetAlgorithm(CqlParser.AlterBySetAlgorithmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByChangeDefault}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByChangeDefault(CqlParser.AlterByChangeDefaultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByChangeColumn}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByChangeColumn(CqlParser.AlterByChangeColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByRenameColumn}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByRenameColumn(CqlParser.AlterByRenameColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByLock}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByLock(CqlParser.AlterByLockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByModifyColumn}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByModifyColumn(CqlParser.AlterByModifyColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDropColumn}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDropColumn(CqlParser.AlterByDropColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDropConstraintCheck}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDropConstraintCheck(CqlParser.AlterByDropConstraintCheckContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDropPrimaryKey}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDropPrimaryKey(CqlParser.AlterByDropPrimaryKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByRenameIndex}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByRenameIndex(CqlParser.AlterByRenameIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAlterIndexVisibility}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAlterIndexVisibility(CqlParser.AlterByAlterIndexVisibilityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDropIndex}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDropIndex(CqlParser.AlterByDropIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDropForeignKey}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDropForeignKey(CqlParser.AlterByDropForeignKeyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDisableKeys}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDisableKeys(CqlParser.AlterByDisableKeysContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByEnableKeys}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByEnableKeys(CqlParser.AlterByEnableKeysContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByRename}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByRename(CqlParser.AlterByRenameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByOrder}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByOrder(CqlParser.AlterByOrderContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByConvertCharset}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByConvertCharset(CqlParser.AlterByConvertCharsetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDefaultCharset}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDefaultCharset(CqlParser.AlterByDefaultCharsetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDiscardTablespace}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDiscardTablespace(CqlParser.AlterByDiscardTablespaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByImportTablespace}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByImportTablespace(CqlParser.AlterByImportTablespaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByForce}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByForce(CqlParser.AlterByForceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByValidate}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByValidate(CqlParser.AlterByValidateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAddPartition}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAddPartition(CqlParser.AlterByAddPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDropPartition}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDropPartition(CqlParser.AlterByDropPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByDiscardPartition}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByDiscardPartition(CqlParser.AlterByDiscardPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByImportPartition}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByImportPartition(CqlParser.AlterByImportPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByTruncatePartition}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByTruncatePartition(CqlParser.AlterByTruncatePartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByCoalescePartition}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByCoalescePartition(CqlParser.AlterByCoalescePartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByReorganizePartition}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByReorganizePartition(CqlParser.AlterByReorganizePartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByExchangePartition}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByExchangePartition(CqlParser.AlterByExchangePartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByAnalyzePartition}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByAnalyzePartition(CqlParser.AlterByAnalyzePartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByCheckPartition}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByCheckPartition(CqlParser.AlterByCheckPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByOptimizePartition}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByOptimizePartition(CqlParser.AlterByOptimizePartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByRebuildPartition}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByRebuildPartition(CqlParser.AlterByRebuildPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByRepairPartition}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByRepairPartition(CqlParser.AlterByRepairPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByRemovePartitioning}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByRemovePartitioning(CqlParser.AlterByRemovePartitioningContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterByUpgradePartitioning}
	 * labeled alternative in {@link CqlParser#alterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterByUpgradePartitioning(CqlParser.AlterByUpgradePartitioningContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dropDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropDatabase(CqlParser.DropDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dropEvent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropEvent(CqlParser.DropEventContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dropIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropIndex(CqlParser.DropIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dropLogfileGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropLogfileGroup(CqlParser.DropLogfileGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dropProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropProcedure(CqlParser.DropProcedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dropFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropFunction(CqlParser.DropFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dropServer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropServer(CqlParser.DropServerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dropTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropTable(CqlParser.DropTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dropTablespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropTablespace(CqlParser.DropTablespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dropTrigger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropTrigger(CqlParser.DropTriggerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dropView}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropView(CqlParser.DropViewContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dropRole}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropRole(CqlParser.DropRoleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#setRole}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetRole(CqlParser.SetRoleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#renameTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameTable(CqlParser.RenameTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#renameTableClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameTableClause(CqlParser.RenameTableClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#truncateTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTruncateTable(CqlParser.TruncateTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#callStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallStatement(CqlParser.CallStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#deleteStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteStatement(CqlParser.DeleteStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#doStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoStatement(CqlParser.DoStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#handlerStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerStatement(CqlParser.HandlerStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#insertStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertStatement(CqlParser.InsertStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#loadDataStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadDataStatement(CqlParser.LoadDataStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#loadXmlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadXmlStatement(CqlParser.LoadXmlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#replaceStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReplaceStatement(CqlParser.ReplaceStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleSelect}
	 * labeled alternative in {@link CqlParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleSelect(CqlParser.SimpleSelectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesisSelect}
	 * labeled alternative in {@link CqlParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisSelect(CqlParser.ParenthesisSelectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unionSelect}
	 * labeled alternative in {@link CqlParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionSelect(CqlParser.UnionSelectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unionParenthesisSelect}
	 * labeled alternative in {@link CqlParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionParenthesisSelect(CqlParser.UnionParenthesisSelectContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#updateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateStatement(CqlParser.UpdateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#insertStatementValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertStatementValue(CqlParser.InsertStatementValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#updatedElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdatedElement(CqlParser.UpdatedElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#assignmentField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentField(CqlParser.AssignmentFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#lockClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLockClause(CqlParser.LockClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#singleDeleteStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleDeleteStatement(CqlParser.SingleDeleteStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#multipleDeleteStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleDeleteStatement(CqlParser.MultipleDeleteStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#handlerOpenStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerOpenStatement(CqlParser.HandlerOpenStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#handlerReadIndexStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerReadIndexStatement(CqlParser.HandlerReadIndexStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#handlerReadStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerReadStatement(CqlParser.HandlerReadStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#handlerCloseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerCloseStatement(CqlParser.HandlerCloseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#singleUpdateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleUpdateStatement(CqlParser.SingleUpdateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#multipleUpdateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleUpdateStatement(CqlParser.MultipleUpdateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#orderByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByClause(CqlParser.OrderByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#orderByExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByExpression(CqlParser.OrderByExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#tableSources}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSources(CqlParser.TableSourcesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableSourceBase}
	 * labeled alternative in {@link CqlParser#tableSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSourceBase(CqlParser.TableSourceBaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableSourceNested}
	 * labeled alternative in {@link CqlParser#tableSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSourceNested(CqlParser.TableSourceNestedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomTableItem}
	 * labeled alternative in {@link CqlParser#tableSourceItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomTableItem(CqlParser.AtomTableItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subqueryTableItem}
	 * labeled alternative in {@link CqlParser#tableSourceItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubqueryTableItem(CqlParser.SubqueryTableItemContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableSourcesItem}
	 * labeled alternative in {@link CqlParser#tableSourceItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSourcesItem(CqlParser.TableSourcesItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#indexHint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexHint(CqlParser.IndexHintContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#indexHintType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexHintType(CqlParser.IndexHintTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code innerJoin}
	 * labeled alternative in {@link CqlParser#joinPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInnerJoin(CqlParser.InnerJoinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code straightJoin}
	 * labeled alternative in {@link CqlParser#joinPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStraightJoin(CqlParser.StraightJoinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code outerJoin}
	 * labeled alternative in {@link CqlParser#joinPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOuterJoin(CqlParser.OuterJoinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code naturalJoin}
	 * labeled alternative in {@link CqlParser#joinPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNaturalJoin(CqlParser.NaturalJoinContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#queryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryExpression(CqlParser.QueryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#queryExpressionNointo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQueryExpressionNointo(CqlParser.QueryExpressionNointoContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#querySpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuerySpecification(CqlParser.QuerySpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#querySpecificationNointo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuerySpecificationNointo(CqlParser.QuerySpecificationNointoContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#unionParenthesis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionParenthesis(CqlParser.UnionParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#unionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionStatement(CqlParser.UnionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#selectSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectSpec(CqlParser.SelectSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#selectElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElements(CqlParser.SelectElementsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectStarElement}
	 * labeled alternative in {@link CqlParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStarElement(CqlParser.SelectStarElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectColumnElement}
	 * labeled alternative in {@link CqlParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectColumnElement(CqlParser.SelectColumnElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectFunctionElement}
	 * labeled alternative in {@link CqlParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectFunctionElement(CqlParser.SelectFunctionElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectExpressionElement}
	 * labeled alternative in {@link CqlParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectExpressionElement(CqlParser.SelectExpressionElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectIntoVariables}
	 * labeled alternative in {@link CqlParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectIntoVariables(CqlParser.SelectIntoVariablesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectIntoDumpFile}
	 * labeled alternative in {@link CqlParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectIntoDumpFile(CqlParser.SelectIntoDumpFileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectIntoTextFile}
	 * labeled alternative in {@link CqlParser#selectIntoExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectIntoTextFile(CqlParser.SelectIntoTextFileContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#selectFieldsInto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectFieldsInto(CqlParser.SelectFieldsIntoContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#selectLinesInto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectLinesInto(CqlParser.SelectLinesIntoContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#fromClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromClause(CqlParser.FromClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#groupByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupByClause(CqlParser.GroupByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#havingClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHavingClause(CqlParser.HavingClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#windowClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWindowClause(CqlParser.WindowClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#groupByItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupByItem(CqlParser.GroupByItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#limitClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitClause(CqlParser.LimitClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#limitClauseAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitClauseAtom(CqlParser.LimitClauseAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#startTransaction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartTransaction(CqlParser.StartTransactionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#beginWork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginWork(CqlParser.BeginWorkContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#commitWork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommitWork(CqlParser.CommitWorkContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#rollbackWork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRollbackWork(CqlParser.RollbackWorkContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#savepointStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSavepointStatement(CqlParser.SavepointStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#rollbackStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRollbackStatement(CqlParser.RollbackStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#releaseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReleaseStatement(CqlParser.ReleaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#lockTables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLockTables(CqlParser.LockTablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#unlockTables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnlockTables(CqlParser.UnlockTablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#setAutocommitStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetAutocommitStatement(CqlParser.SetAutocommitStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#setTransactionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetTransactionStatement(CqlParser.SetTransactionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#transactionMode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransactionMode(CqlParser.TransactionModeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#lockTableElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLockTableElement(CqlParser.LockTableElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#lockAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLockAction(CqlParser.LockActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#transactionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransactionOption(CqlParser.TransactionOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#transactionLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransactionLevel(CqlParser.TransactionLevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#changeMaster}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChangeMaster(CqlParser.ChangeMasterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#changeReplicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChangeReplicationFilter(CqlParser.ChangeReplicationFilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#purgeBinaryLogs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPurgeBinaryLogs(CqlParser.PurgeBinaryLogsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#resetMaster}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResetMaster(CqlParser.ResetMasterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#resetSlave}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResetSlave(CqlParser.ResetSlaveContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#startSlave}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartSlave(CqlParser.StartSlaveContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#stopSlave}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStopSlave(CqlParser.StopSlaveContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#startGroupReplication}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartGroupReplication(CqlParser.StartGroupReplicationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#stopGroupReplication}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStopGroupReplication(CqlParser.StopGroupReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code masterStringOption}
	 * labeled alternative in {@link CqlParser#masterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMasterStringOption(CqlParser.MasterStringOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code masterDecimalOption}
	 * labeled alternative in {@link CqlParser#masterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMasterDecimalOption(CqlParser.MasterDecimalOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code masterBoolOption}
	 * labeled alternative in {@link CqlParser#masterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMasterBoolOption(CqlParser.MasterBoolOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code masterRealOption}
	 * labeled alternative in {@link CqlParser#masterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMasterRealOption(CqlParser.MasterRealOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code masterUidListOption}
	 * labeled alternative in {@link CqlParser#masterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMasterUidListOption(CqlParser.MasterUidListOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#stringMasterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringMasterOption(CqlParser.StringMasterOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#decimalMasterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalMasterOption(CqlParser.DecimalMasterOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#boolMasterOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolMasterOption(CqlParser.BoolMasterOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#channelOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelOption(CqlParser.ChannelOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doDbReplication}
	 * labeled alternative in {@link CqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoDbReplication(CqlParser.DoDbReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ignoreDbReplication}
	 * labeled alternative in {@link CqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgnoreDbReplication(CqlParser.IgnoreDbReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doTableReplication}
	 * labeled alternative in {@link CqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoTableReplication(CqlParser.DoTableReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ignoreTableReplication}
	 * labeled alternative in {@link CqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgnoreTableReplication(CqlParser.IgnoreTableReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code wildDoTableReplication}
	 * labeled alternative in {@link CqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildDoTableReplication(CqlParser.WildDoTableReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code wildIgnoreTableReplication}
	 * labeled alternative in {@link CqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildIgnoreTableReplication(CqlParser.WildIgnoreTableReplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rewriteDbReplication}
	 * labeled alternative in {@link CqlParser#replicationFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRewriteDbReplication(CqlParser.RewriteDbReplicationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#tablePair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTablePair(CqlParser.TablePairContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#threadType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThreadType(CqlParser.ThreadTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code gtidsUntilOption}
	 * labeled alternative in {@link CqlParser#untilOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGtidsUntilOption(CqlParser.GtidsUntilOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code masterLogUntilOption}
	 * labeled alternative in {@link CqlParser#untilOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMasterLogUntilOption(CqlParser.MasterLogUntilOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relayLogUntilOption}
	 * labeled alternative in {@link CqlParser#untilOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelayLogUntilOption(CqlParser.RelayLogUntilOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sqlGapsUntilOption}
	 * labeled alternative in {@link CqlParser#untilOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlGapsUntilOption(CqlParser.SqlGapsUntilOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userConnectionOption}
	 * labeled alternative in {@link CqlParser#connectionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserConnectionOption(CqlParser.UserConnectionOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code passwordConnectionOption}
	 * labeled alternative in {@link CqlParser#connectionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasswordConnectionOption(CqlParser.PasswordConnectionOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defaultAuthConnectionOption}
	 * labeled alternative in {@link CqlParser#connectionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultAuthConnectionOption(CqlParser.DefaultAuthConnectionOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pluginDirConnectionOption}
	 * labeled alternative in {@link CqlParser#connectionOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPluginDirConnectionOption(CqlParser.PluginDirConnectionOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#gtuidSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGtuidSet(CqlParser.GtuidSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#xaStartTransaction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXaStartTransaction(CqlParser.XaStartTransactionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#xaEndTransaction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXaEndTransaction(CqlParser.XaEndTransactionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#xaPrepareStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXaPrepareStatement(CqlParser.XaPrepareStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#xaCommitWork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXaCommitWork(CqlParser.XaCommitWorkContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#xaRollbackWork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXaRollbackWork(CqlParser.XaRollbackWorkContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#xaRecoverWork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXaRecoverWork(CqlParser.XaRecoverWorkContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#prepareStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrepareStatement(CqlParser.PrepareStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#executeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecuteStatement(CqlParser.ExecuteStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#deallocatePrepare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeallocatePrepare(CqlParser.DeallocatePrepareContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#routineBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutineBody(CqlParser.RoutineBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(CqlParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#caseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStatement(CqlParser.CaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(CqlParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#iterateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterateStatement(CqlParser.IterateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#leaveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeaveStatement(CqlParser.LeaveStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStatement(CqlParser.LoopStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#repeatStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatStatement(CqlParser.RepeatStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(CqlParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(CqlParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CloseCursor}
	 * labeled alternative in {@link CqlParser#cursorStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCloseCursor(CqlParser.CloseCursorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FetchCursor}
	 * labeled alternative in {@link CqlParser#cursorStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFetchCursor(CqlParser.FetchCursorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OpenCursor}
	 * labeled alternative in {@link CqlParser#cursorStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpenCursor(CqlParser.OpenCursorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#declareVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareVariable(CqlParser.DeclareVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#declareCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareCondition(CqlParser.DeclareConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#declareCursor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareCursor(CqlParser.DeclareCursorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#declareHandler}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareHandler(CqlParser.DeclareHandlerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code handlerConditionCode}
	 * labeled alternative in {@link CqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerConditionCode(CqlParser.HandlerConditionCodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code handlerConditionState}
	 * labeled alternative in {@link CqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerConditionState(CqlParser.HandlerConditionStateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code handlerConditionName}
	 * labeled alternative in {@link CqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerConditionName(CqlParser.HandlerConditionNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code handlerConditionWarning}
	 * labeled alternative in {@link CqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerConditionWarning(CqlParser.HandlerConditionWarningContext ctx);
	/**
	 * Visit a parse tree produced by the {@code handlerConditionNotfound}
	 * labeled alternative in {@link CqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerConditionNotfound(CqlParser.HandlerConditionNotfoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code handlerConditionException}
	 * labeled alternative in {@link CqlParser#handlerConditionValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHandlerConditionException(CqlParser.HandlerConditionExceptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#procedureSqlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureSqlStatement(CqlParser.ProcedureSqlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#caseAlternative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseAlternative(CqlParser.CaseAlternativeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#elifAlternative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElifAlternative(CqlParser.ElifAlternativeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterUserMysqlV56}
	 * labeled alternative in {@link CqlParser#alterUser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterUserMysqlV56(CqlParser.AlterUserMysqlV56Context ctx);
	/**
	 * Visit a parse tree produced by the {@code alterUserMysqlV57}
	 * labeled alternative in {@link CqlParser#alterUser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterUserMysqlV57(CqlParser.AlterUserMysqlV57Context ctx);
	/**
	 * Visit a parse tree produced by the {@code createUserMysqlV56}
	 * labeled alternative in {@link CqlParser#createUser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateUserMysqlV56(CqlParser.CreateUserMysqlV56Context ctx);
	/**
	 * Visit a parse tree produced by the {@code createUserMysqlV57}
	 * labeled alternative in {@link CqlParser#createUser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateUserMysqlV57(CqlParser.CreateUserMysqlV57Context ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dropUser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropUser(CqlParser.DropUserContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#grantStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrantStatement(CqlParser.GrantStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#roleOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleOption(CqlParser.RoleOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#grantProxy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrantProxy(CqlParser.GrantProxyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#renameUser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameUser(CqlParser.RenameUserContext ctx);
	/**
	 * Visit a parse tree produced by the {@code detailRevoke}
	 * labeled alternative in {@link CqlParser#revokeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDetailRevoke(CqlParser.DetailRevokeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code shortRevoke}
	 * labeled alternative in {@link CqlParser#revokeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortRevoke(CqlParser.ShortRevokeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code roleRevoke}
	 * labeled alternative in {@link CqlParser#revokeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleRevoke(CqlParser.RoleRevokeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#revokeProxy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRevokeProxy(CqlParser.RevokeProxyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#setPasswordStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetPasswordStatement(CqlParser.SetPasswordStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#userSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserSpecification(CqlParser.UserSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code passwordAuthOption}
	 * labeled alternative in {@link CqlParser#userAuthOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasswordAuthOption(CqlParser.PasswordAuthOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringAuthOption}
	 * labeled alternative in {@link CqlParser#userAuthOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringAuthOption(CqlParser.StringAuthOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hashAuthOption}
	 * labeled alternative in {@link CqlParser#userAuthOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHashAuthOption(CqlParser.HashAuthOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleAuthOption}
	 * labeled alternative in {@link CqlParser#userAuthOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleAuthOption(CqlParser.SimpleAuthOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#tlsOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTlsOption(CqlParser.TlsOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#userResourceOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserResourceOption(CqlParser.UserResourceOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#userPasswordOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserPasswordOption(CqlParser.UserPasswordOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#userLockOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserLockOption(CqlParser.UserLockOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#privelegeClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrivelegeClause(CqlParser.PrivelegeClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#privilege}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrivilege(CqlParser.PrivilegeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code currentSchemaPriviLevel}
	 * labeled alternative in {@link CqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrentSchemaPriviLevel(CqlParser.CurrentSchemaPriviLevelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code globalPrivLevel}
	 * labeled alternative in {@link CqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalPrivLevel(CqlParser.GlobalPrivLevelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code definiteSchemaPrivLevel}
	 * labeled alternative in {@link CqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefiniteSchemaPrivLevel(CqlParser.DefiniteSchemaPrivLevelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code definiteFullTablePrivLevel}
	 * labeled alternative in {@link CqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefiniteFullTablePrivLevel(CqlParser.DefiniteFullTablePrivLevelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code definiteFullTablePrivLevel2}
	 * labeled alternative in {@link CqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefiniteFullTablePrivLevel2(CqlParser.DefiniteFullTablePrivLevel2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code definiteTablePrivLevel}
	 * labeled alternative in {@link CqlParser#privilegeLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefiniteTablePrivLevel(CqlParser.DefiniteTablePrivLevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#renameUserClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameUserClause(CqlParser.RenameUserClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#analyzeTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnalyzeTable(CqlParser.AnalyzeTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#checkTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckTable(CqlParser.CheckTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#checksumTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChecksumTable(CqlParser.ChecksumTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#optimizeTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptimizeTable(CqlParser.OptimizeTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#repairTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepairTable(CqlParser.RepairTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#checkTableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckTableOption(CqlParser.CheckTableOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#createUdfunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateUdfunction(CqlParser.CreateUdfunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#installPlugin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstallPlugin(CqlParser.InstallPluginContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#uninstallPlugin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUninstallPlugin(CqlParser.UninstallPluginContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setVariable}
	 * labeled alternative in {@link CqlParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetVariable(CqlParser.SetVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setCharset}
	 * labeled alternative in {@link CqlParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetCharset(CqlParser.SetCharsetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setNames}
	 * labeled alternative in {@link CqlParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetNames(CqlParser.SetNamesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setPassword}
	 * labeled alternative in {@link CqlParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetPassword(CqlParser.SetPasswordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setTransaction}
	 * labeled alternative in {@link CqlParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetTransaction(CqlParser.SetTransactionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setAutocommit}
	 * labeled alternative in {@link CqlParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetAutocommit(CqlParser.SetAutocommitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setNewValueInsideTrigger}
	 * labeled alternative in {@link CqlParser#setStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetNewValueInsideTrigger(CqlParser.SetNewValueInsideTriggerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showMasterLogs}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowMasterLogs(CqlParser.ShowMasterLogsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showLogEvents}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowLogEvents(CqlParser.ShowLogEventsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showObjectFilter}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowObjectFilter(CqlParser.ShowObjectFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showColumns}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowColumns(CqlParser.ShowColumnsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showCreateDb}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCreateDb(CqlParser.ShowCreateDbContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showCreateFullIdObject}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCreateFullIdObject(CqlParser.ShowCreateFullIdObjectContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showCreateUser}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCreateUser(CqlParser.ShowCreateUserContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showEngine}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowEngine(CqlParser.ShowEngineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showGlobalInfo}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowGlobalInfo(CqlParser.ShowGlobalInfoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showErrors}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowErrors(CqlParser.ShowErrorsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showCountErrors}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCountErrors(CqlParser.ShowCountErrorsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showSchemaFilter}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowSchemaFilter(CqlParser.ShowSchemaFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showRoutine}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowRoutine(CqlParser.ShowRoutineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showGrants}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowGrants(CqlParser.ShowGrantsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showIndexes}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowIndexes(CqlParser.ShowIndexesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showOpenTables}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowOpenTables(CqlParser.ShowOpenTablesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showProfile}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowProfile(CqlParser.ShowProfileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code showSlaveStatus}
	 * labeled alternative in {@link CqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowSlaveStatus(CqlParser.ShowSlaveStatusContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#variableClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableClause(CqlParser.VariableClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#showCommonEntity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowCommonEntity(CqlParser.ShowCommonEntityContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#showFilter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowFilter(CqlParser.ShowFilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#showGlobalInfoClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowGlobalInfoClause(CqlParser.ShowGlobalInfoClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#showSchemaEntity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowSchemaEntity(CqlParser.ShowSchemaEntityContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#showProfileType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowProfileType(CqlParser.ShowProfileTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#binlogStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinlogStatement(CqlParser.BinlogStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#cacheIndexStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCacheIndexStatement(CqlParser.CacheIndexStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#flushStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlushStatement(CqlParser.FlushStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#killStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKillStatement(CqlParser.KillStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#loadIndexIntoCache}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadIndexIntoCache(CqlParser.LoadIndexIntoCacheContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#resetStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResetStatement(CqlParser.ResetStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#shutdownStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShutdownStatement(CqlParser.ShutdownStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#tableIndexes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableIndexes(CqlParser.TableIndexesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleFlushOption}
	 * labeled alternative in {@link CqlParser#flushOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleFlushOption(CqlParser.SimpleFlushOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code channelFlushOption}
	 * labeled alternative in {@link CqlParser#flushOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChannelFlushOption(CqlParser.ChannelFlushOptionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tableFlushOption}
	 * labeled alternative in {@link CqlParser#flushOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableFlushOption(CqlParser.TableFlushOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#flushTableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlushTableOption(CqlParser.FlushTableOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#loadedTableIndexes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoadedTableIndexes(CqlParser.LoadedTableIndexesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#simpleDescribeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleDescribeStatement(CqlParser.SimpleDescribeStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#fullDescribeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullDescribeStatement(CqlParser.FullDescribeStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#helpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHelpStatement(CqlParser.HelpStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#useStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUseStatement(CqlParser.UseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#signalStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignalStatement(CqlParser.SignalStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#resignalStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResignalStatement(CqlParser.ResignalStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#signalConditionInformation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignalConditionInformation(CqlParser.SignalConditionInformationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#diagnosticsStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiagnosticsStatement(CqlParser.DiagnosticsStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#diagnosticsConditionInformationName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiagnosticsConditionInformationName(CqlParser.DiagnosticsConditionInformationNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code describeStatements}
	 * labeled alternative in {@link CqlParser#describeObjectClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescribeStatements(CqlParser.DescribeStatementsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code describeConnection}
	 * labeled alternative in {@link CqlParser#describeObjectClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescribeConnection(CqlParser.DescribeConnectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#fullId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullId(CqlParser.FullIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(CqlParser.TableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#roleName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleName(CqlParser.RoleNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#fullColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullColumnName(CqlParser.FullColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#indexColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexColumnName(CqlParser.IndexColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#userName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserName(CqlParser.UserNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#mysqlVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMysqlVariable(CqlParser.MysqlVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#charsetName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharsetName(CqlParser.CharsetNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#collationName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollationName(CqlParser.CollationNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#engineName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEngineName(CqlParser.EngineNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#uuidSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUuidSet(CqlParser.UuidSetContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#xid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXid(CqlParser.XidContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#xuidStringId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXuidStringId(CqlParser.XuidStringIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#authPlugin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAuthPlugin(CqlParser.AuthPluginContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#uid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUid(CqlParser.UidContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#simpleId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleId(CqlParser.SimpleIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dottedId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDottedId(CqlParser.DottedIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#decimalLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalLiteral(CqlParser.DecimalLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#fileSizeLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileSizeLiteral(CqlParser.FileSizeLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(CqlParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(CqlParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#hexadecimalLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexadecimalLiteral(CqlParser.HexadecimalLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#nullNotnull}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullNotnull(CqlParser.NullNotnullContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(CqlParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringDataType}
	 * labeled alternative in {@link CqlParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringDataType(CqlParser.StringDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nationalStringDataType}
	 * labeled alternative in {@link CqlParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNationalStringDataType(CqlParser.NationalStringDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nationalVaryingStringDataType}
	 * labeled alternative in {@link CqlParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNationalVaryingStringDataType(CqlParser.NationalVaryingStringDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dimensionDataType}
	 * labeled alternative in {@link CqlParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionDataType(CqlParser.DimensionDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleDataType}
	 * labeled alternative in {@link CqlParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleDataType(CqlParser.SimpleDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code collectionDataType}
	 * labeled alternative in {@link CqlParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollectionDataType(CqlParser.CollectionDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code spatialDataType}
	 * labeled alternative in {@link CqlParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpatialDataType(CqlParser.SpatialDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code longVarcharDataType}
	 * labeled alternative in {@link CqlParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongVarcharDataType(CqlParser.LongVarcharDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code longVarbinaryDataType}
	 * labeled alternative in {@link CqlParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongVarbinaryDataType(CqlParser.LongVarbinaryDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#collectionOptions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollectionOptions(CqlParser.CollectionOptionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#convertedDataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConvertedDataType(CqlParser.ConvertedDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#lengthOneDimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthOneDimension(CqlParser.LengthOneDimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#lengthTwoDimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthTwoDimension(CqlParser.LengthTwoDimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#lengthTwoOptionalDimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthTwoOptionalDimension(CqlParser.LengthTwoOptionalDimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#uidList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUidList(CqlParser.UidListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#tables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTables(CqlParser.TablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#indexColumnNames}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexColumnNames(CqlParser.IndexColumnNamesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#expressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressions(CqlParser.ExpressionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#expressionsWithDefaults}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionsWithDefaults(CqlParser.ExpressionsWithDefaultsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#constants}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstants(CqlParser.ConstantsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#simpleStrings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleStrings(CqlParser.SimpleStringsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#userVariables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserVariables(CqlParser.UserVariablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#defaultValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultValue(CqlParser.DefaultValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#currentTimestamp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrentTimestamp(CqlParser.CurrentTimestampContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#expressionOrDefault}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionOrDefault(CqlParser.ExpressionOrDefaultContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#ifExists}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExists(CqlParser.IfExistsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#ifNotExists}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfNotExists(CqlParser.IfNotExistsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code specificFunctionCall}
	 * labeled alternative in {@link CqlParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecificFunctionCall(CqlParser.SpecificFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code aggregateFunctionCall}
	 * labeled alternative in {@link CqlParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregateFunctionCall(CqlParser.AggregateFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nonAggregateFunctionCall}
	 * labeled alternative in {@link CqlParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonAggregateFunctionCall(CqlParser.NonAggregateFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code scalarFunctionCall}
	 * labeled alternative in {@link CqlParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarFunctionCall(CqlParser.ScalarFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code udfFunctionCall}
	 * labeled alternative in {@link CqlParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUdfFunctionCall(CqlParser.UdfFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code passwordFunctionCall}
	 * labeled alternative in {@link CqlParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasswordFunctionCall(CqlParser.PasswordFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleFunctionCall}
	 * labeled alternative in {@link CqlParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleFunctionCall(CqlParser.SimpleFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dataTypeFunctionCall}
	 * labeled alternative in {@link CqlParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataTypeFunctionCall(CqlParser.DataTypeFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valuesFunctionCall}
	 * labeled alternative in {@link CqlParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValuesFunctionCall(CqlParser.ValuesFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caseExpressionFunctionCall}
	 * labeled alternative in {@link CqlParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpressionFunctionCall(CqlParser.CaseExpressionFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caseFunctionCall}
	 * labeled alternative in {@link CqlParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseFunctionCall(CqlParser.CaseFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charFunctionCall}
	 * labeled alternative in {@link CqlParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharFunctionCall(CqlParser.CharFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code positionFunctionCall}
	 * labeled alternative in {@link CqlParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositionFunctionCall(CqlParser.PositionFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code substrFunctionCall}
	 * labeled alternative in {@link CqlParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubstrFunctionCall(CqlParser.SubstrFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trimFunctionCall}
	 * labeled alternative in {@link CqlParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrimFunctionCall(CqlParser.TrimFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code weightFunctionCall}
	 * labeled alternative in {@link CqlParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeightFunctionCall(CqlParser.WeightFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code extractFunctionCall}
	 * labeled alternative in {@link CqlParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtractFunctionCall(CqlParser.ExtractFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getFormatFunctionCall}
	 * labeled alternative in {@link CqlParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetFormatFunctionCall(CqlParser.GetFormatFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jsonValueFunctionCall}
	 * labeled alternative in {@link CqlParser#specificFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonValueFunctionCall(CqlParser.JsonValueFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#caseFuncAlternative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseFuncAlternative(CqlParser.CaseFuncAlternativeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code levelWeightList}
	 * labeled alternative in {@link CqlParser#levelsInWeightString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLevelWeightList(CqlParser.LevelWeightListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code levelWeightRange}
	 * labeled alternative in {@link CqlParser#levelsInWeightString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLevelWeightRange(CqlParser.LevelWeightRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#levelInWeightListElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLevelInWeightListElement(CqlParser.LevelInWeightListElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#aggregateWindowedFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregateWindowedFunction(CqlParser.AggregateWindowedFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#nonAggregateWindowedFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonAggregateWindowedFunction(CqlParser.NonAggregateWindowedFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#overClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOverClause(CqlParser.OverClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#windowSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWindowSpec(CqlParser.WindowSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#windowName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWindowName(CqlParser.WindowNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#frameClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrameClause(CqlParser.FrameClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#frameUnits}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrameUnits(CqlParser.FrameUnitsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#frameExtent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrameExtent(CqlParser.FrameExtentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#frameBetween}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrameBetween(CqlParser.FrameBetweenContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#frameRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrameRange(CqlParser.FrameRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#partitionClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionClause(CqlParser.PartitionClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#scalarFunctionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarFunctionName(CqlParser.ScalarFunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#passwordFunctionClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasswordFunctionClause(CqlParser.PasswordFunctionClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#functionArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionArgs(CqlParser.FunctionArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#functionArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionArg(CqlParser.FunctionArgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isExpression}
	 * labeled alternative in {@link CqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsExpression(CqlParser.IsExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link CqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(CqlParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalExpression}
	 * labeled alternative in {@link CqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpression(CqlParser.LogicalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code predicateExpression}
	 * labeled alternative in {@link CqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicateExpression(CqlParser.PredicateExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code soundsLikePredicate}
	 * labeled alternative in {@link CqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSoundsLikePredicate(CqlParser.SoundsLikePredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressionAtomPredicate}
	 * labeled alternative in {@link CqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAtomPredicate(CqlParser.ExpressionAtomPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subqueryComparisonPredicate}
	 * labeled alternative in {@link CqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubqueryComparisonPredicate(CqlParser.SubqueryComparisonPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jsonMemberOfPredicate}
	 * labeled alternative in {@link CqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonMemberOfPredicate(CqlParser.JsonMemberOfPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryComparisonPredicate}
	 * labeled alternative in {@link CqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryComparisonPredicate(CqlParser.BinaryComparisonPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inPredicate}
	 * labeled alternative in {@link CqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInPredicate(CqlParser.InPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code betweenPredicate}
	 * labeled alternative in {@link CqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetweenPredicate(CqlParser.BetweenPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isNullPredicate}
	 * labeled alternative in {@link CqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNullPredicate(CqlParser.IsNullPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code likePredicate}
	 * labeled alternative in {@link CqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLikePredicate(CqlParser.LikePredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code regexpPredicate}
	 * labeled alternative in {@link CqlParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegexpPredicate(CqlParser.RegexpPredicateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpressionAtom(CqlParser.UnaryExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code collateExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCollateExpressionAtom(CqlParser.CollateExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mysqlVariableExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMysqlVariableExpressionAtom(CqlParser.MysqlVariableExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nestedExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedExpressionAtom(CqlParser.NestedExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nestedRowExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedRowExpressionAtom(CqlParser.NestedRowExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mathExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathExpressionAtom(CqlParser.MathExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code existsExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExistsExpressionAtom(CqlParser.ExistsExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intervalExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalExpressionAtom(CqlParser.IntervalExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jsonExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonExpressionAtom(CqlParser.JsonExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subqueryExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubqueryExpressionAtom(CqlParser.SubqueryExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpressionAtom(CqlParser.ConstantExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpressionAtom(CqlParser.FunctionCallExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpressionAtom(CqlParser.BinaryExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fullColumnNameExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullColumnNameExpressionAtom(CqlParser.FullColumnNameExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitExpressionAtom}
	 * labeled alternative in {@link CqlParser#expressionAtom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitExpressionAtom(CqlParser.BitExpressionAtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#unaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(CqlParser.UnaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(CqlParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#logicalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOperator(CqlParser.LogicalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#bitOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitOperator(CqlParser.BitOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#mathOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathOperator(CqlParser.MathOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#jsonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonOperator(CqlParser.JsonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#charsetNameBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharsetNameBase(CqlParser.CharsetNameBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#transactionLevelBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransactionLevelBase(CqlParser.TransactionLevelBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#privilegesBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrivilegesBase(CqlParser.PrivilegesBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#intervalTypeBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalTypeBase(CqlParser.IntervalTypeBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#dataTypeBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataTypeBase(CqlParser.DataTypeBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#keywordsCanBeId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordsCanBeId(CqlParser.KeywordsCanBeIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link CqlParser#functionNameBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionNameBase(CqlParser.FunctionNameBaseContext ctx);
}