package fun.langel.cql.antlr;

/**
 * @author jiangchuanwei.jcw@alibaba-inc.com(GuHan)
 * @since 2021/11/9 4:07 下午
 **/
public class CSqlWalkListener extends CSqlParserBaseListener {

    @Override
    public void enterSqlStatements(CSqlParser.SqlStatementsContext ctx) {
        super.enterSqlStatements(ctx);
    }

    @Override
    public void enterSimpleSelect(CSqlParser.SimpleSelectContext ctx) {
        super.enterSimpleSelect(ctx);
    }

    @Override
    public void enterColumnDeclaration(CSqlParser.ColumnDeclarationContext ctx) {
        super.enterColumnDeclaration(ctx);
    }

    @Override
    public void exitSelectColumnElement(CSqlParser.SelectColumnElementContext ctx) {
        super.exitSelectColumnElement(ctx);
    }

    @Override
    public void exitSelectElements(CSqlParser.SelectElementsContext ctx) {
        super.exitSelectElements(ctx);
    }
}
