package main.tables;

import java.sql.SQLException;

import static main.tables.UserTable.*;

public class AddDeleteTables {

    public static void delete() throws SQLException {
        JournalInfoTable.DeleteTable();
        ArticleInfoTable.DeleteTable();
        ErrorTable.DeleteTable();
        ResponseTable.DeleteTable();
        ArticleTable.DeleteTable();
        EditionTable.DeleteTable();
        VolumeTable.DeleteTable();
        JournalTable.DeleteTable();
        EditorInfoTable.DeleteTable();
        EditorTable.DeleteTable();
        QuestionTable.DeleteTable();
        ReviewTable.DeleteTable();
        ReviewerTable.DeleteTable();
        AuthorTable.DeleteTable();
    }

    public static void adding() throws SQLException {
//        //Creates all tables
        CreateUserTable();
        EditorTable.CreateEditorTable();
        ReviewerTable.CreateReviewerTable();
        JournalTable.CreateJournalTable();
        JournalInfoTable.CreateJournalInfoTable();
        VolumeTable.CreateVolumeTable();
        EditionTable.CreateEditionTable();
        AuthorTable.CreateAuthorTable();
        ArticleTable.CreateArticleTable();
        ArticleInfoTable.CreateArticleInfoTable();
        ReviewTable.CreateReviewTable();
        ErrorTable.CreateErrorTable();
        QuestionTable.CreateQuestionTable();
        ResponseTable.CreateResponseTable();
        EditorInfoTable.CreateEditorInfoTable();
    }
    public static void main (String args[]) throws SQLException {

        AddDeleteTables.adding();
        //AddDeleteTables.delete();
    }
}
