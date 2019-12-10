package main.tables;

import java.sql.SQLException;

import static main.tables.UserTable.*;

public class AddDeleteTables {

    public static void delete() throws SQLException {
        JournalInfoTable.DeleteTable();
        ArticleInfoTable.DeleteTable();
        ErrorTable.DeleteTable();
        ResponseTable.DeleteTable();
        QuestionTable.DeleteTable();
        EditorTable.DeleteTable();
        AuthorTable.DeleteTable();
        ReviewTable.DeleteTable();
        ArticleTable.DeleteTable();
        ReviewerTable.DeleteTable();
        EditionTable.DeleteTable();
        VolumeTable.DeleteTable();
        JournalTable.DeleteTable();
        UserTable.DeleteTable();

    }

    public static void add() throws SQLException {
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
    }
    public static void main (String args[]) throws SQLException {
       // AddDeleteTables.delete();
        //AddDeleteTables.add();
    }
}
